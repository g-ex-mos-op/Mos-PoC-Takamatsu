package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnEntryStatus;


/**
 * MLエントリー状況(
 * @author kusama
 */
public interface TrnEntryStatusDao {

    public static final Class BEAN = TrnEntryStatus.class;

    public static final String getEntryStaffList_ARGS = "entryCd, entryYear, entryKai, companyCd, sibuCd, onerCd, misecd, condType";
    public static final String getAllStaffList_ARGS = "entryCd, entryYear, entryKai, companyCd, sibuCd, onerCd, misecd, condType";
    
    /**
     * エントリー者一覧の取得
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param sibuCd
     * @param onerCd
     * @param miseCd
     * @param searchType
     * @return
     */
    public List getEntryStaffList(String entryCd, 
                                   String entryYear, 
                                   String entryKai, 
                                   String companyCd, 
                                   String sibuCd,
                                   String onerCd,
                                   String miseCd,
                                   String condType);
    
    /**
     * 全スタッフ一覧の取得
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param sibuCd
     * @param onerCd
     * @param miseCd
     * @param searchType
     * @return
     */
    public List getAllStaffList(String entryCd, 
                                 String entryYear, 
                                 String entryKai, 
                                 String companyCd, 
                                 String sibuCd,
                                 String onerCd,
                                 String miseCd,
                                 String condType);
}