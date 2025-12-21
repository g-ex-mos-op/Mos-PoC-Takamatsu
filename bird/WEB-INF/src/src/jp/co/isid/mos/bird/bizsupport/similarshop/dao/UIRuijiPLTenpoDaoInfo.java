/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.dao;

import java.math.BigDecimal;
import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIRuijiPLTenpoInfo;

/**
 * 類似店情報(UIRuijiPLTenpoDaoInfo)
 * @author Nakajima
 */
public interface UIRuijiPLTenpoDaoInfo {

    public static final Class BEAN = UIRuijiPLTenpoInfo.class;
    public static final String getAveragePLData_ARGS  = "zenMonthYYYYMM,areaDai,miseKeitai,locateKbn,uriageSitei,uriMin,uriMax,openDtMin,openDtMax";
    public static final String getPLData_ARGS  = "zenMonthYYYYMM,miseCd,userId,userTypeCd,limitFlg";

    /**
     * 類似店の平均PLデータの取得(getAveragePLData)
     * @param zenMonthYYYYMM,areaDai,miseKeitai,locateKbn,uriMin,uriMax,openDtMin,openDtMax
     * @return UIRuijiPLTenpoInfo 検索結果
     */
    public UIRuijiPLTenpoInfo getAveragePLData(String zenMonthYYYYMM, String areaDai, String miseKeitai, String locateKbn, String uriageSitei, BigDecimal uriMin, BigDecimal uriMax, String openDtMin, String openDtMax);
    
    /**
     * 対象店舗のPLデータの取得(getPLData)
     * @param zenMonthYYYYMM,miseCd,userId,userTypeCd,limitFlg
     * @return UIRuijiPLTenpoInfo 検索結果
     */
    public UIRuijiPLTenpoInfo getPLData(String zenMonthYYYYMM, String miseCd, String userId, String userTypeCd, boolean limitFlg);
}

