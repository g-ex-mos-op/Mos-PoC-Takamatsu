/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.camprank.action.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.camprank.common.CampRankConst;
import jp.co.isid.mos.bird.bizreport.camprank.dto.CampRankCondDto;
import jp.co.isid.mos.bird.bizreport.camprank.dto.CampRankDto;
import jp.co.isid.mos.bird.bizreport.camprank.logic.CampRankInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;


/**
 * CSVダウンロードアクション
 * 
 * @author xnkusama
 *
 */
public class CsvActionImpl extends CsvOutput2ActionImpl {
    /* アクションID：CSVダウンロード処理 */
    public static final String downloadCsv_ACTION_ID = "BBR013A05";
    
    /** LOGIC */
    private CampRankInfoLogic camprankCampRankInfoLogic;
    /** DTO【自画面リクエスト用】*/
    private CampRankCondDto camprankCampRankCondDto;
    /** DTO【自画面セッション用】*/
    private CampRankDto camprankCampRankDto;
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl#downloadCsv()
	 */
	public void downloadCsv() throws IOException, ApplicationException {
        //リクエストDTOへ検索情報をセット
        setCondInfo();
        if (getCamprankCampRankDto().getListCampRankData(getCamprankCampRankCondDto().getWindowId()) == null 
                || getCamprankCampRankDto().getListCampRankData(getCamprankCampRankCondDto().getWindowId()).isEmpty()) {
            //検索結果データがない場合は検索処理実行
            getCampRankData(getCamprankCampRankCondDto());
        }
        else {
            setResultInfo();
        }
        super.setCsvOutputDto(getCamprankCampRankCondDto());
		// ダウンロード処理
		super.downloadCsv();
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
    public CampRankInfoLogic getCamprankCampRankInfoLogic() {
        return camprankCampRankInfoLogic;
    }
    public void setCamprankCampRankInfoLogic(
            CampRankInfoLogic camprankCampRankInfoLogic) {
        this.camprankCampRankInfoLogic = camprankCampRankInfoLogic;
    }
	
    /**
     * 検索情報セット
     */
    private void setCondInfo() {
        // キャンペーンID
        getCamprankCampRankCondDto().setCondCampId(getCamprankCampRankDto().getCondCampId(getCamprankCampRankCondDto().getWindowId()));
        // 順位
        getCamprankCampRankCondDto().setCondRank(getCamprankCampRankDto().getCondRank(getCamprankCampRankCondDto().getWindowId()));
        // 対象日
        getCamprankCampRankCondDto().setCondTargetDt(getCamprankCampRankDto().getCondTargetDt(getCamprankCampRankCondDto().getWindowId()));
        // 検索モード
        getCamprankCampRankCondDto().setCondMode(getCamprankCampRankDto().getCondMode(getCamprankCampRankCondDto().getWindowId()));
        // 対象マスタ
        getCamprankCampRankCondDto().setTargetMstCampDate(getCamprankCampRankDto().getTargetMstCampDate(getCamprankCampRankCondDto().getWindowId()));
        // BirdUserInfo
        getCamprankCampRankCondDto().setBirdUserInfo(getCamprankCampRankDto().getBirdUserInfo());
        // 対象日プルダウン選択Index
        getCamprankCampRankCondDto().setTargetDtSelectedIndex(getCamprankCampRankDto().getTargetDtSelectedIndex(getCamprankCampRankCondDto().getWindowId()));
    }
    private void setResultInfo() {
        // 検索データ
        getCamprankCampRankCondDto().setListResult(getCamprankCampRankDto().getListCampRankData(getCamprankCampRankCondDto().getWindowId()));
        // 対象店舗カウント
        getCamprankCampRankCondDto().setTaishoTenpoCount(getCamprankCampRankDto().getTaishoTenpoCount(getCamprankCampRankCondDto().getWindowId()));
    }
    /**
     * キャンペーンベスト１００情報の取得
     * @param dto
     * @return
     */
    private void getCampRankData(CampRankCondDto dto) {
        // 検索対象の対象期間マスタを取得
        MstCampDate entity = dto.getTargetMstCampDate();
        
        // 期間From、Toを設定
        String fromDt = "";
        String toDt = "";
        if (CampRankConst.SEARCH_MODE_TAISHOBI.equals(dto.getCondMode())) {
            fromDt = dto.getCondTargetDt();
            toDt = dto.getCondTargetDt();
        }
        else {
            fromDt = entity.getCampFrom();
            toDt = dto.getCondTargetDt();
        }
        
        Map retMap = getCamprankCampRankInfoLogic()
                                .execute(entity.getCompanyCd(), dto.getCondCampId(), fromDt, toDt, dto.getCondRank(), dto.getCondMode(), getCamprankCampRankCondDto().getTargetDtSelectedIndex());
        
        // 検索条件、結果をDTOへセット
        setResultInfo();
        List listCampRankData = (List) retMap.get(CampRankConst.SEARCH_LOGIC_RETURN_RANK_DATA);
        if (listCampRankData == null || listCampRankData.isEmpty()) {
            // 該当データなしの場合
            getCamprankCampRankCondDto().setListResult(null);
            getCamprankCampRankCondDto().setTaishoTenpoCount(null);
            // 例外発生
            throw new NoResultException();
        }
        else {
            getCamprankCampRankCondDto().setListResult(listCampRankData);
            getCamprankCampRankCondDto().setTaishoTenpoCount((BigDecimal) retMap.get(CampRankConst.SEARCH_LOGIC_RETURN_TENPO_COUNT));
        }

    }
}
