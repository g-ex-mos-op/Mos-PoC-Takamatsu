package jp.co.isid.mos.bird.entry.mlentry.dao;

import jp.co.isid.mos.bird.entry.mlentry.entity.UIStaffLicense;

/**
 * ライセンス保持者管理Dao
 * @author Aspac
 */
public interface UIStaffLicenseDao {

    public static final Class BEAN = UIStaffLicense.class;
    
    public static final String getStaffLicenseInfo_ARGS = "staffId";


    /**
     * ライセンス保持状況取得
     * @param staffId
     * @return
     */
    public UIStaffLicense getStaffLicenseInfo(String staffId);

}