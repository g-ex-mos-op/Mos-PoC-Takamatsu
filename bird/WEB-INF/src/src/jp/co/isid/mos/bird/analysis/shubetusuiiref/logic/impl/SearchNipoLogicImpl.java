package jp.co.isid.mos.bird.analysis.shubetusuiiref.logic.impl;

import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.shubetusuiiref.dao.UIShubetuSuiiDao;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefReqDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.entity.UIShubetuSuii;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.logic.SearchShubetuSuiiLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;


/**
 * 日次データ取得 ロジック
 * @author xnkusama
 *
 */
public class SearchNipoLogicImpl implements SearchShubetuSuiiLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT003L02";

    /**DAO*/
    private MstSibuDao mstSibuDao;
    private UIShubetuSuiiDao shubetuSuiiRefDao;
    
    public void execute(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq) {
        // 初期処理
        validate(dto, dtoReq);

        // 検索条件情報作成
        String targetDtFrom = dtoReq.getKikan() + "01";
        String targetDtTo   = dtoReq.getKikan() + "31";
        
        // ２．【DAO】種別売上推移日次Dao．日次データの取得を実行
        List listData = getShubetuSuiiRefDao()
                            .selectNitiData(dtoReq.getCompanyCd(),
                                            dtoReq.getHyojiTaishoSibu(),
                                            dtoReq.getHyojiTaishoMise(),
                                            targetDtFrom,
                                            targetDtTo,
                                            dtoReq.getTenpoShu(),
                                            dtoReq.getTaishoJoken(),
                                            dtoReq.getZenDataShu(),
                                            dto.getBirdUserInfo().isLimit(),
                                            dto.getUserTypeCd(),
                                            dto.getUserId());
        
        // ３．該当データなしチェック（集計行があるので結果が1行の場合は、該当データなし）
        if (listData == null || listData.size() == 1) {
            //検索済フラグリセット
            dto.setSearchedFlg(dtoReq.getWindowId(), false);
            //検索結果をクリア
            dto.removeSearchData(dtoReq.getWindowId());
            throw new NoResultException();
        }
        
        // ４．検索結果ヘッダ情報セット
        setHeaderInfo(dto, dtoReq, listData);
        
        // 取得データをDTOへセット
        dtoReq.setListNipoData(listData);
    }

    /**
     * 検索結果ヘッダ情報セット
     * @param dto
     * @param dtoReq
     * @param listData
     */
    private void setHeaderInfo(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq, List listData) {
        // 対象期間
        DateFormatter dtFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, "yyyy'年'MM'月'");
        dtoReq.setTaishoKikan(dtFormatter.format(dtoReq.getKikan(), true));
        
        // 表示対象
        String hyojiTaisho = "";
        if (TaishoJoken.CODE_ALL.equals(dtoReq.getTaishoJoken())) {
            //全社・全店
            hyojiTaisho = TaishoJoken.getName(dto.getUserTypeCd(), dtoReq.getTaishoJoken());
        }
        else if (TaishoJoken.CODE_SIBU.equals(dtoReq.getTaishoJoken())) {
            //支部
            List listSibu = getMstSibuDao().getSibu(dtoReq.getCompanyCd(), dtoReq.getHyojiTaishoSibu());
            hyojiTaisho = dtoReq.getHyojiTaishoSibu() + " " + ((MstSibu) listSibu.get(0)).getSibuName();
        }
        else if (TaishoJoken.CODE_MISE.equals(dtoReq.getTaishoJoken())) {
            //店舗：検索結果のEntityから取得
            hyojiTaisho = dtoReq.getHyojiTaishoMise() + " " + ((UIShubetuSuii) listData.get(0)).getMiseNameKj();
        }
        dtoReq.setHyojiTaishoDisp(hyojiTaisho);
        
        // 対象店舗数：検索結果最終行の店舗カウントをセット
        dtoReq.setTaishoTenpoCnt(((UIShubetuSuii) listData.get(listData.size()-1)).getMiseCnt().toString());
        
        // 店舗種別
        if (UserType.HONBU.equals(dto.getUserTypeCd())) {
            String tenpoShuName = "";
            for (Iterator ite = dto.getListTenpoShu().iterator(); ite.hasNext();) {
                SelectItem item = (SelectItem) ite.next();
                if (item.getValue().equals(dtoReq.getTenpoShu())) {
                    tenpoShuName = item.getLabel();
                }
            }
            dtoReq.setTenpoShuName(tenpoShuName);
        }
        // 前年データ種別
        String zenDataShuName = "";
        for (Iterator ite = dto.getListZenDataShu().iterator(); ite.hasNext();) {
            SelectItem item = (SelectItem) ite.next();
            if (item.getValue().equals(dtoReq.getZenDataShu())) {
                zenDataShuName = item.getLabel();
            }
        }
        dtoReq.setZenDataShuName(zenDataShuName);
        
    }
    
    /**
     * 初期処理
     * @param dto
     * @param dtoReq
     */
    private void validate(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq) {
        //必須チェック
        if (dto == null) {
            throw new NotNullException("画面情報");
        }
        if (dtoReq == null) {
            throw new NotNullException("画面情報");
        }
        //本部ユーザーのみ店コードチェック
        if (UserType.HONBU.equals(dto.getUserTypeCd())) {
            if (TaishoJoken.CODE_MISE.equals(dtoReq.getTaishoJoken())) {
                // 店コード必須チェック
                if (CommonUtil.isNull(dtoReq.getHyojiTaishoMise())) {
                    throw new NotNullException("店コード", "condHyojiTaishoMise", 0);
                }
                // レングスチェック
                if (dtoReq.getHyojiTaishoMise().trim().getBytes().length > 5) {
                    throw new NoResultException();
                }
            }
        }
        
    }
    
    public UIShubetuSuiiDao getShubetuSuiiRefDao() {
        return shubetuSuiiRefDao;
    }

    public void setShubetuSuiiRefDao(UIShubetuSuiiDao shubetuSuiiRefDao) {
        this.shubetuSuiiRefDao = shubetuSuiiRefDao;
    }

    public MstSibuDao getMstSibuDao() {
        return mstSibuDao;
    }

    public void setMstSibuDao(MstSibuDao mstSibuDao) {
        this.mstSibuDao = mstSibuDao;
    }

}
