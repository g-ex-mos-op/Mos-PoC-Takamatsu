package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity.MstStaff;


/**
 * スタッフ一覧情報取得Dao
 * 
 * @author xnkusama
 */
public interface MstStaffDao {

    public static final Class BEAN = MstStaff.class;
    public static final String getOnerStaffList_ARGS = "companyCd, onerCd";
    public static final String getOnerStaffListByMlEntry_ARGS = "companyCd, onerCd, entryYear, entryYearKai";
    public static final String getStaffInfo_ARGS = "staffId";

    /**
     * スタッフ一覧取得
     * @param companyCd
     * @param onerCd
     * @return List（MstStaff)
     */
    public List getOnerStaffList(String companyCd, String onerCd);
    
    /**
     * スタッフ一覧取得
     * ※モード１ (ML受験申込)対応
     * @param companyCd
     * @param onerCd
     * @param entryYear
     * @param entryYearKai
     * @return List（MstStaff)
     */
    public List getOnerStaffListByMlEntry(String companyCd, String onerCd, String entryYear, String entryYearKai);
    
    /**
     * 指定スタッフ情報取得
     * @param staffId
     * @return
     */
    public List getStaffInfo(String staffId);
}
