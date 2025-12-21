package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsCommon;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.MstMiseInfoDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.MstMiseInfoLogic;

/**
 * 対象店舗情報取得ロジック
 * @author xjung
 */
public class MstMiseInfoLogicImpl implements MstMiseInfoLogic {
    
    /** 対象店舗情報取得ロジックID **/
    public static final String LOGIC_ID = "BSM013L04";

    /** 対象店舗情報Dao **/
    private MstMiseInfoDao mstMiseInfoDao;
    
    /**
     * 対象店舗情報を取得する
     * @param companyCd 企業コード
     * @param onerCd    オーナーコード
     * @param appDate   アプリ日付
     * @return List 対象店舗プルダウンリスト
     */
    public List execute(String companyCd, String onerCd, String appDate) {
        //入力チェック
        if (companyCd == null || companyCd.trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_COMPANY_CD);
        }
        if (onerCd == null || onerCd.trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_ONER_CD);
        }
        if (appDate == null || appDate.trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_APP_DATE);
        }
      
        // ユーザー対応店舗
        List miseList = getMstMiseInfoDao().select(companyCd, onerCd, appDate);

        // 検索結果が０件の場合
        if (miseList == null || miseList.isEmpty()) {
            throw new NotExistException(ProceedsConstants.MSG_TAISHO_TENPO_INFO);
        }

        return miseList;
    }
    
    /**
     * 店舗(店コード+店名称)情報を取得する
     * @param companyCd 企業コード
     * @param miseCd    店コード
     * @return String   店舗情報(店コード+店名称)
     */
    public String getMiseCdName(String companyCd, String miseCd) {
        // 必須入力チェック
        if (companyCd == null || companyCd.trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_COMPANY_CD);
        }
        if (miseCd == null || miseCd.trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_TAISHO_TENPO);
        }

        // 対象店舗情報取得
        String taishoTenpoInfo = getMstMiseInfoDao().selectMiseCdName(companyCd, miseCd);

        if (ProceedsCommon.isNull(taishoTenpoInfo)) {
            throw new NotExistException(ProceedsConstants.MSG_TAISHO_TENPO);
        }
        return taishoTenpoInfo;
    }
   
    /**
     * 対象店舗情報Daoを取得する
     * @return 対象店舗情報Dao
     */
    public MstMiseInfoDao getMstMiseInfoDao() {
        return mstMiseInfoDao;
    }

    /**
     * 対象店舗情報Daoを設定する
     * @param mstMiseInfoDao 対象店舗情報Dao
     */
    public void setMstMiseInfoDao(MstMiseInfoDao mstMiseInfoDao) {
        this.mstMiseInfoDao = mstMiseInfoDao;
    }
}