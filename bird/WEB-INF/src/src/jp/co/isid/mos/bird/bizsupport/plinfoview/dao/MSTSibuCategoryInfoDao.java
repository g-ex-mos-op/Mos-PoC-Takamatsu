package jp.co.isid.mos.bird.bizsupport.plinfoview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MSTSibuCategoryInfo;


/**
 * 支部情報
 * @author kusama
 */
public interface MSTSibuCategoryInfoDao {

    public static final Class BEAN = MSTSibuCategoryInfo.class;

    public static final String getSibuInfo_ARGS = "companyCd, userId, isLimit";
    public static final String getJigyouInfo_ARGS = "companyCd, userId, isLimit";
    public static final String getSlareaInfo_ARGS = "companyCd, userId, isLimit";
    
    /**
     * 支部情報の取得
     * @param rCompanyCd 企業コード
     * @return List
     */
    public List getSibuInfo(String companyCd, String userId, boolean isLimit);
    
    /**
     * 事業本部情報の取得
     * @param rCompanyCd 企業コード
     * @return List
     */
    public List getJigyouInfo(String companyCd, String userId, boolean isLimit);
    
    /**
     *営業エリア情報の取得
     * @param rCompanyCd 企業コード
     * @return List
     */
    public List getSlareaInfo(String companyCd, String userId, boolean isLimit);
    
}