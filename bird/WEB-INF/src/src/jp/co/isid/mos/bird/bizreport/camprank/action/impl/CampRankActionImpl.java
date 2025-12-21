package jp.co.isid.mos.bird.bizreport.camprank.action.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.camprank.action.CampRankAction;
import jp.co.isid.mos.bird.bizreport.camprank.common.CampRankConst;
import jp.co.isid.mos.bird.bizreport.camprank.dto.CampRankCondDto;
import jp.co.isid.mos.bird.bizreport.camprank.dto.CampRankDto;
import jp.co.isid.mos.bird.bizreport.camprank.logic.CampRankInfoLogic;
import jp.co.isid.mos.bird.bizreport.camprank.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

/**
 * キャンペーンベスト１００画面Action
 * @author xnkusama
 *
 */
public class CampRankActionImpl implements CampRankAction {
    /* Action ID */
    public static final String initialize_ACTION_ID = "BBR013A01";
    public static final String doExecute_ACTION_ID = "BBR013A02";
    public static final String doTabChange_ACTION_ID = "BBR013A03";
    public static final String doCsvDownload_ACTION_ID = "BBR013A04";
    
    /** LOGIC */
    private ConditionLogic camprankConditionLogic;
    private CampRankInfoLogic camprankCampRankInfoLogic;

    /** DTO */
    private CampRankDto camprankCampRankDto;
    private CampRankCondDto camprankCampRankCondDto;
    private PullDownMenuDto pullDownMenuDto;
    
    /** ACTION */
    private CsvOutputAction camprankCsvAction;
    
    /** その他 */
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    
    /** Actionパラメータ */
    //検索モード（１：対象日　２：期間合計）
    private String condMode;
    
    /**
     * 実行
     * @return
     */
    public String doExecute() {
        //「別ウィンドウに表示」の場合
        if (getCamprankCampRankCondDto().isFlgTargetWindow()) {
            // Session DTOのウィンドウIDを更新
            getCamprankCampRankCondDto().setWindowId(getCamprankCampRankDto().updateWindowid());
            getCamprankCampRankCondDto().setFlgTargetWindow(false);
        }
        
        // 検索モード設定
        if (CommonUtil.isNull(getCamprankCampRankCondDto().getCondMode())) {
            getCamprankCampRankCondDto().setCondMode(CampRankConst.SEARCH_MODE_TAISHOBI);
        }
        // 検索対象の対象期間マスタを取得
        MstCampDate entity = null;
        int index = 0;
        for (Iterator ite = getCamprankCampRankDto().getListCamp().iterator(); ite.hasNext();) {
            entity = (MstCampDate) ite.next();
            if (entity.getCampId().equals(getCamprankCampRankCondDto().getCondCampId())) {
                break;
            }
            index++;
        }
        
        // 選択されている対象日プルダウンから選択されている対象日を取得する
        List listTargetDt = (List) getCamprankCampRankDto().getListTargetDtPulldown().get(index);
        SelectItem selectItem = (SelectItem) listTargetDt.get(getCamprankCampRankCondDto().getTargetDtSelectedIndex());
        getCamprankCampRankCondDto().setCondTargetDt((String) selectItem.getValue());

        // 期間From、Toを設定
        String fromDt = "";
        String toDt = "";
        if (CampRankConst.SEARCH_MODE_TAISHOBI.equals(getCamprankCampRankCondDto().getCondMode())) {
            fromDt = getCamprankCampRankCondDto().getCondTargetDt();
            toDt = getCamprankCampRankCondDto().getCondTargetDt();
        }
        else {
            fromDt = entity.getCampFrom();
            toDt = getCamprankCampRankCondDto().getCondTargetDt();
        }
        
        Map retMap = getCamprankCampRankInfoLogic()
                                .execute(entity.getCompanyCd(), 
                                         getCamprankCampRankCondDto().getCondCampId(), 
                                         fromDt, 
                                         toDt, 
                                         getCamprankCampRankCondDto().getCondRank(),
                                         getCamprankCampRankCondDto().getCondMode(),
                                         getCamprankCampRankCondDto().getTargetDtSelectedIndex());
        
        // 検索条件、結果をDTOへセット
        setCondInfo();
        List listCampRankData = (List) retMap.get(CampRankConst.SEARCH_LOGIC_RETURN_RANK_DATA);
        if (listCampRankData == null || listCampRankData.isEmpty()) {
            // 該当データなしの場合
            getCamprankCampRankCondDto().setListResult(null);
            getCamprankCampRankDto().setListCampRankData(null, getCamprankCampRankCondDto().getWindowId());
            getCamprankCampRankCondDto().setTaishoTenpoCount(null);
            getCamprankCampRankDto().setTaishoTenpoCount(null, getCamprankCampRankCondDto().getWindowId());
            // 例外発生
            throw new NoResultException();
        }
        else {
            getCamprankCampRankCondDto().setListResult(listCampRankData);
            getCamprankCampRankDto().setListCampRankData(listCampRankData, getCamprankCampRankCondDto().getWindowId());
            getCamprankCampRankCondDto().setTaishoTenpoCount((BigDecimal) retMap.get(CampRankConst.SEARCH_LOGIC_RETURN_TENPO_COUNT));
            getCamprankCampRankDto().setTaishoTenpoCount((BigDecimal) retMap.get(CampRankConst.SEARCH_LOGIC_RETURN_TENPO_COUNT), getCamprankCampRankCondDto().getWindowId());
            // 検索対象のキャンペーン期間マスタを設定
            getCamprankCampRankDto().setTargetMstCampDate(entity, getCamprankCampRankCondDto().getWindowId());
            getCamprankCampRankCondDto().setTargetMstCampDate(entity);
        }
        
        return null;
    }

    public String doTabChange() {
        //セッションDTOからリクエストDTOへ検索条件情報をセット
        setCondInfoSessionToRequest();
        
        
        // 検索モード
        getCamprankCampRankDto().setCondMode(getCamprankCampRankCondDto().getCondMode(), getCamprankCampRankCondDto().getWindowId());
        // 検索対象の対象期間マスタを取得
        MstCampDate entity = getCamprankCampRankDto().getTargetMstCampDate(getCamprankCampRankCondDto().getWindowId());

        // 期間From、Toを設定
        String fromDt = "";
        String toDt = "";
        if (CampRankConst.SEARCH_MODE_TAISHOBI.equals(getCamprankCampRankCondDto().getCondMode())) {
            fromDt = getCamprankCampRankDto().getCondTargetDt(getCamprankCampRankCondDto().getWindowId());
            toDt = getCamprankCampRankDto().getCondTargetDt(getCamprankCampRankCondDto().getWindowId());
        }
        else {
            fromDt = entity.getCampFrom();
            toDt = getCamprankCampRankDto().getCondTargetDt(getCamprankCampRankCondDto().getWindowId());
        }
        
        Map retMap = getCamprankCampRankInfoLogic()
                                .execute(entity.getCompanyCd(), 
                                         getCamprankCampRankDto().getCondCampId(getCamprankCampRankCondDto().getWindowId()), 
                                         fromDt, 
                                         toDt, 
                                         getCamprankCampRankDto().getCondRank(getCamprankCampRankCondDto().getWindowId()), 
                                         getCamprankCampRankCondDto().getCondMode(),
                                         getCamprankCampRankCondDto().getTargetDtSelectedIndex());

        // 結果をDTOへセット
        getCamprankCampRankCondDto().setListResult((List) retMap.get(CampRankConst.SEARCH_LOGIC_RETURN_RANK_DATA));
        getCamprankCampRankDto().setListCampRankData((List) retMap.get(CampRankConst.SEARCH_LOGIC_RETURN_RANK_DATA), getCamprankCampRankCondDto().getWindowId());
        getCamprankCampRankCondDto().setTaishoTenpoCount((BigDecimal) retMap.get(CampRankConst.SEARCH_LOGIC_RETURN_TENPO_COUNT));
        getCamprankCampRankDto().setTaishoTenpoCount((BigDecimal) retMap.get(CampRankConst.SEARCH_LOGIC_RETURN_TENPO_COUNT), getCamprankCampRankCondDto().getWindowId());
        // 検索対象のキャンペーン期間マスタを設定
        getCamprankCampRankDto().setTargetMstCampDate(entity, getCamprankCampRankCondDto().getWindowId());
        
        return null;
    }

    /**
     * CSVダウンロード
     * @return
     */
    public String doCsvDownload() {
//        // ウィンドウIDをセット
//        getCamprankCampRankDto().setWindowId(getCamprankCampRankCondDto().getWindowId());
        // CSVアクション実行
        try {
            getCamprankCsvAction().downloadCsv();
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSVダウンロード処理", null, ex);
        }
        
        return null;
    }

    /**
     * 初期処理
     */
    public String initialize() {
        
        if (getPullDownMenuDto().isClearFlg()) {
            // ウインドウID生成
            getCamprankCampRankCondDto().setWindowId(getCamprankCampRankDto().updateWindowid());
            getCamprankCampRankDto().setBirdDateInfo(getBirdDateInfo());
            getCamprankCampRankDto().setBirdUserInfo(getBirdUserInfo());
            getCamprankCampRankCondDto().setBirdUserInfo(getBirdUserInfo());
            // 会社コードをセット ※2008/03/21現在　会社選択機能がないためオンコード
            getCamprankCampRankDto().setCondCompanyCd("00", getCamprankCampRankCondDto().getWindowId());
//            getCamprankCampRankCondDto().setWindowId(getCamprankCampRankCondDto().getWindowId());
            // リモート制限フラグ設定
            if (UserType.ONER.equals(getBirdUserInfo().getMstUser().getUserTypeCd())
                    || UserType.TENPO.equals(getBirdUserInfo().getMstUser().getUserTypeCd())
                    || (UserType.HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd()) && getBirdUserInfo().isLimit()))
            {
                getCamprankCampRankDto().setRemoteLimitFlg(true);
            }
            else {
                getCamprankCampRankDto().setRemoteLimitFlg(false);
            }

            getPullDownMenuDto().setClearFlg(false);

            // キャンペーン一覧、対象日一覧
            makeCampInfo();
            
            // 順位プルダウン作成
            makeRankPulldown();
        }
        
        return null;
    }
    
    /**
     * 検索条件をSessionDTOへセット
     */
    private void setCondInfo() {
        // キャンペーンID
        getCamprankCampRankDto().setCondCampId(getCamprankCampRankCondDto().getCondCampId(), getCamprankCampRankCondDto().getWindowId());
        // 順位
        getCamprankCampRankDto().setCondRank(getCamprankCampRankCondDto().getCondRank(), getCamprankCampRankCondDto().getWindowId());
        // 対象日
        getCamprankCampRankDto().setCondTargetDt(getCamprankCampRankCondDto().getCondTargetDt(), getCamprankCampRankCondDto().getWindowId());
        // 検索モード
        getCamprankCampRankDto().setCondMode(getCamprankCampRankCondDto().getCondMode(), getCamprankCampRankCondDto().getWindowId());
        // 対象日プルダウン選択Index
        getCamprankCampRankDto().setTargetDtSelectedIndex(getCamprankCampRankCondDto().getTargetDtSelectedIndex(), getCamprankCampRankCondDto().getWindowId());
    }
    
    /**
     * キャンペーン一覧取得
     */
    private void makeCampInfo() {
        // 【ロジック】条件項目の取得設定の実行
       getCamprankConditionLogic().execute(getCamprankCampRankDto(), getCamprankCampRankCondDto()); 
    }

    /**
     * 順位プルダウン作成
     */
    private void makeRankPulldown() {
        SelectItem item1 = new SelectItem(CampRankConst.COND_RANK_KINGAKUKOSEI, CampRankConst.COND_RANK_KINGAKUKOSEI_NAME);
        SelectItem item2 = new SelectItem(CampRankConst.COND_RANK_KOSU, CampRankConst.COND_RANK_KOSU_NAME);
        SelectItem item3 = new SelectItem(CampRankConst.COND_RANK_ZENNENHI, CampRankConst.COND_RANK_ZENNENHI_NAME);
        List listItems = new ArrayList();
        listItems.add(item1);
        listItems.add(item2);
        listItems.add(item3);
        
        getCamprankCampRankDto().setListRankPulldown(listItems);
    }

    /**
     * セッションDTOからRequestDTOへ検索情報をセット
     */
    private void setCondInfoSessionToRequest() {
        // キャンペーンID
        getCamprankCampRankCondDto().setCondCampId(getCamprankCampRankDto().getCondCampId(getCamprankCampRankCondDto().getWindowId()));
        // 順位
        getCamprankCampRankCondDto().setCondRank(getCamprankCampRankDto().getCondRank(getCamprankCampRankCondDto().getWindowId()));
        // 対象日
        getCamprankCampRankCondDto().setCondTargetDt(getCamprankCampRankDto().getCondTargetDt(getCamprankCampRankCondDto().getWindowId()));
        // 対象マスタ
        getCamprankCampRankCondDto().setTargetMstCampDate(getCamprankCampRankDto().getTargetMstCampDate(getCamprankCampRankCondDto().getWindowId()));
        // BirdUserInfo
        getCamprankCampRankCondDto().setBirdUserInfo(getCamprankCampRankDto().getBirdUserInfo());
        // 対象日プルダウン選択Index
        getCamprankCampRankCondDto().setTargetDtSelectedIndex(getCamprankCampRankDto().getTargetDtSelectedIndex(getCamprankCampRankCondDto().getWindowId()));
    }
    
    public CampRankCondDto getCamprankCampRankCondDto() {
        return camprankCampRankCondDto;
    }

    public void setCamprankCampRankCondDto(CampRankCondDto camprankCampRankCondDto) {
        this.camprankCampRankCondDto = camprankCampRankCondDto;
    }

    public CampRankDto getCamprankCampRankDto() {
        return camprankCampRankDto;
    }

    public void setCamprankCampRankDto(CampRankDto camprankCampRankDto) {
        this.camprankCampRankDto = camprankCampRankDto;
    }

    public ConditionLogic getCamprankConditionLogic() {
        return camprankConditionLogic;
    }

    public void setCamprankConditionLogic(ConditionLogic camprankConditionLogic) {
        this.camprankConditionLogic = camprankConditionLogic;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public CampRankInfoLogic getCamprankCampRankInfoLogic() {
        return camprankCampRankInfoLogic;
    }

    public void setCamprankCampRankInfoLogic(
            CampRankInfoLogic camprankCampRankInfoLogic) {
        this.camprankCampRankInfoLogic = camprankCampRankInfoLogic;
    }

    public String getCondMode() {
        return condMode;
    }

    public void setCondMode(String condMode) {
        this.condMode = condMode;
    }

    public CsvOutputAction getCamprankCsvAction() {
        return camprankCsvAction;
    }

    public void setCamprankCsvAction(CsvOutputAction camprankCsvAction) {
        this.camprankCsvAction = camprankCsvAction;
    }
}
