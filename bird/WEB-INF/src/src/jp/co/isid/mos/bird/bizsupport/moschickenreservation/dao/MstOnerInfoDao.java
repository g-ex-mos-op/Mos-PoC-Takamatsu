package jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao;

import java.util.List;

import  jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.MstOnerInfo;


/**
 * オーナー情報
 * @author inazawa
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
    public List getOnerInfo(String companyCd, String onerCd);
    
}