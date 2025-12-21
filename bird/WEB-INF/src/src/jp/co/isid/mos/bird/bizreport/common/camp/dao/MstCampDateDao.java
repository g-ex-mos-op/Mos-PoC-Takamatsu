package jp.co.isid.mos.bird.bizreport.common.camp.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;


/**
 * キャンペーンデータDao
 * @author xnkusama
 *
 */
public interface MstCampDateDao {

    public static final Class BEAN = MstCampDate.class;
    
    public static final String select_ARGS = "campId";
    public static final String selectCompany_ARGS = "companyCd";
    public static final String selectOpen_ARGS = "sysDate, userId, userTypeCd, limitFlg, companyCd, sortCol";
    public static final String selectClose_ARGS = "sysDate, userId, userTypeCd, limitFlg, companyCd, nendoFromDt, nendoToDt";
    
    /**
     * 検索
     * @param campId
     * @return
     */
    public List select( String campId);
    
    /**
     * 全件検索
     * @return
     */
    public List selectAll();
    
    /**
     * 会社指定検索
     * @param companyCd
     * @return
     */
    public List selectCompany(String companyCd);
    
    /**
     * 表示公開対象検索
     * 
     * @param sysDate
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param sortCol  1:対象期間From　　2:表示期間From
     * @return
     */
    public List selectOpen(String sysDate, String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, int sortCol);
    
    /**
     * 過去対象検索
     * 
     * @param sysDate
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param nendoFromDt
     * @param nendoToDt
     * @return
     */
    public List selectClose(String sysDate, String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String nendoFromDt, String nendoToDt);
}