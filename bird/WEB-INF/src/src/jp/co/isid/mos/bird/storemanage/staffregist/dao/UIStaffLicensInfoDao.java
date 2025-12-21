/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.staffregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.staffregist.entity.UIStaffLicensInfo;

/**
 * スタッフライセンス情報検索Dao
 * 
 * 作成日:2009/08/27
 * @author xkinu
 *
 */
public interface UIStaffLicensInfoDao {
    public static final Class BEAN = UIStaffLicensInfo.class;
    public static final String select_ARGS = "sysNendoFrom, sysNendoTo, companyCd";

    /**
     * スタッフ情報の検索
     * 
     * @param sysNendoFrom
     * @param sysNendoTo
     * @param companyCd
     * @return
     */
    public List select(String sysNendoFrom, String sysNendoTo, String companyCd);

}
