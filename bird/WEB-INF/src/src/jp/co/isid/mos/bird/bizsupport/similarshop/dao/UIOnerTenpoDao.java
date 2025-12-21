/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIOnerTenpoInfo;


/**
 * 情報(UIOnerTenpoDao)
 * @author Nakajima
 */
public interface UIOnerTenpoDao {

    public static final Class  BEAN = UIOnerTenpoInfo.class;
    public static final String getOnerTenpoInfo_ARGS = "onerCd,zenMonthYYYYMM";
    public static final String getTenpoInfo_ARGS     = "miseCd,zenMonthYYYYMM,userId,userTypeCd,limitFlg";


    /**
     * 対象オーナーに所属している店舗を取得する(getOnerTenpoInfo)
     * @param oner_cd,zen_month_YYYYMM
     * @return UIOnerTenpoInfo 検索結果
     */
    public List getOnerTenpoInfo(String onerCd, String zenMonthYYYYMM);
    
    /**
     * 対象店舗の情報取得(getTenpoInfo)
     * @param mise_cd,zen_month_YYYYMM,userId,userTypeCd,limitFlg
     * @return UIOnerTenpoInfo 検索結果
     */
    public UIOnerTenpoInfo getTenpoInfo(String miseCd, String zenMonthYYYYMM, String userId, String userTypeCd, boolean limitFlg);
    
}

