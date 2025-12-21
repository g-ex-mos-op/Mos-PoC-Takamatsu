package jp.co.isid.mos.bird.bizsupport.campcheckamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UISpotQuantityInfo;

/**
 * スポット数量情報
 * 
 * @author xlee
 */
public interface UISpotQuantityInfoDao {

    public static final Class BEAN = UISpotQuantityInfo.class;

    public static final String getSpotQuantityInfo_ARGS = "taishoCond, companyCd, cmpNo, miseCd, sibuCd, blockCd, sysDate, onerCdList";

    public static final String getOnerSpotQuantityInfo_ARGS = "companyCd, cmpNo, sysDate, onerCdList";
    
    /**
     *　スポット数量情報の取得
     * @param taishoCond　対象条件
     * @param cmpNo　キャンペーンNO
     * @param companyCd　企業コード
     * @param miseCd　店コード
     * @param sibuCd　支部コード
     * @param blockCd　ブロックコード
     * @param sysDate　システム日付
     * @return スポット数量情報
     */
    public List getSpotQuantityInfo(String taishoCond, String companyCd, String cmpNo, String miseCd, String sibuCd, String blockCd, String sysDate, List onerCdList);
    
    /**
     *　スポット数量情報の取得:オーナーユーザ
     * @param cmpNo　キャンペーンNO
     * @param companyCd　企業コード
     * @param miseList　店List
     * @param sysDate　システム日付
     * @return スポット数量情報
     */
    public List getOnerSpotQuantityInfo(String companyCd, String cmpNo, String sysDate, List onerCdList);
}
