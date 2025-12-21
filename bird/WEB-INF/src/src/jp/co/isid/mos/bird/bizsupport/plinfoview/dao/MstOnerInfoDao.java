package jp.co.isid.mos.bird.bizsupport.plinfoview.dao;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MstOnerInfo;


/**
 * オーナー情報
 * @author kusama
 */
public interface MstOnerInfoDao {

    public static final Class BEAN = MstOnerInfo.class;

    public static final String getOnerInfo_ARGS  = "companyCd, onerCd";
    
    /**
     * 店情報の取得
     * @param companyCd
     * @param onerCd
     * @return MstOnerInfo
     */
    public MstOnerInfo getOnerInfo(String companyCd, String onerCd);
    
}