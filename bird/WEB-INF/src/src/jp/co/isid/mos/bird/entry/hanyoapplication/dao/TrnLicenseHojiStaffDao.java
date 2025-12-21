/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.TrnLicenseHojiStaff;



/**
 * ライセンス保持者管理
 * @author itamoto
 */
public interface TrnLicenseHojiStaffDao {

    public static final Class BEAN = TrnLicenseHojiStaff.class;
    public static final String getCount_ARGS = "staffId";
    public static final String insertEntry_ARGS = "trnLicenseHojiStaff";
    public static final String updateEntry_ARGS = "trnLicenseHojiStaff";

    /**
     * ライセンス保持者管理の新規登録(insertEntry)
     * @param trnLicenseHojiStaff
     */
    public int insertEntry(TrnLicenseHojiStaff trnLicenseHojiStaff);

    /**
     * ライセンス保持者管理の更新
     */
    public int updateEntry(TrnLicenseHojiStaff trnLicenseHojiStaff);
    
    /**
     * ライセンス保持者管理の取得(getEntryDate)
     * @param staffId
     * @return List
     */
    public List getLicenseHojiStaff(String staffid);
}