/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic.impl;

import jp.co.isid.mos.bird.common.entity.MstOner;
import jp.co.isid.mos.bird.common.logic.GetOnerLogic;
import jp.co.isid.mos.bird.entry.ownerchange.common.OwnerChangeConstants;
import jp.co.isid.mos.bird.entry.ownerchange.dao.MstOwnerInfoDao;
import jp.co.isid.mos.bird.entry.ownerchange.entity.MstOwnerInfo;
import jp.co.isid.mos.bird.entry.ownerchange.logic.GetOwnerInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * オーナー情報取得ロジック
 * @author xkonishi
 *
 */
public class GetOwnerInfoLogicImpl implements GetOwnerInfoLogic {
    
    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BEN023L02";

    /**
     * オーナー情報Dao
     */
    private MstOwnerInfoDao mstOwnerInfoDao;
    
    /**
     * オーナーマスタ情報取得ロジック
     */
    private GetOnerLogic getOnerLogic;
    
    /**
     * オーナー情報取得ロジック
     * @param 会社コード
     * @param 条件区分
     * @param 店コード
     * @param オーナーコード
     * @return オーナー情報
     */
    public MstOwnerInfo execute(String companyCd, String kbn, String miseCd,
            String onerCd) {
        
        MstOwnerInfo mstOwnerInfo = new MstOwnerInfo();
        
        // パラメータ.条件区分が0(店舗)の場合
        if(kbn.equals(OwnerChangeConstants.KBN_MISE)){
            // Dao【オーナー情報Dao.オーナー情報取得】を実行。
            mstOwnerInfo = mstOwnerInfoDao.select(companyCd, miseCd);
            
            // オーナー情報を取得できなかった場合
            if(mstOwnerInfo == null || mstOwnerInfo.getOnerCd().equals(null)){
                throw new NotExistException("該当データ", "miseText", 0);
            }
        }
        
        // パラメータ.条件区分が1(オーナー)の場合
        if(kbn.equals(OwnerChangeConstants.KBN_OWNER)){
            // Logic【オーナーマスタ情報取得】を実行。
            MstOner mstOner = getOnerLogic.execute(companyCd, onerCd);

            // オーナーマスタ情報を取得できなかった場合
            if(mstOner == null || mstOner.equals("")){
                throw new NotExistException("該当データ", "ownerText", 0);
            } else {
                mstOwnerInfo.setCompanyCd(mstOner.getCompanyCd());
                mstOwnerInfo.setOnerCd(mstOner.getOnerCd());
                mstOwnerInfo.setOnerNameKj(mstOner.getOnerNameKj());
            }
        }
        return mstOwnerInfo;
    }

    public MstOwnerInfoDao getMstOwnerInfoDao() {
        return mstOwnerInfoDao;
    }

    public void setMstOwnerInfoDao(MstOwnerInfoDao mstOwnerInfoDao) {
        this.mstOwnerInfoDao = mstOwnerInfoDao;
    }

    public GetOnerLogic getGetOnerLogic() {
        return getOnerLogic;
    }

    public void setGetOnerLogic(GetOnerLogic getOnerLogic) {
        this.getOnerLogic = getOnerLogic;
    }
}