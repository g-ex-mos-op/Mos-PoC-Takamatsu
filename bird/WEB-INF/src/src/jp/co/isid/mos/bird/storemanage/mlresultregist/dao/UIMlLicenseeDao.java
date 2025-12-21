/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlLicensee;

/**
 * ライセンス保持者管理DAO
 * 
 * @author xyuchida
 */
public interface UIMlLicenseeDao {

    public static final Class BEAN = UIMlLicensee.class;

    public static final String insertLicense_ARGS = "examNo, entryYear, entryKai, entity";
    public static final String updateIssueLicense_ARGS = "examNo, entryYear, entryKai, entity";
    public static final String selectLicenseNoList_QUERY = "order by LICENSE_NO";

    /**
     * ライセンス発行
     * 
     * @param examNo 受験番号
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param entity ライセンス保持者管理Entity
     */
    public void insertLicense(String examNo, String entryYear, String entryKai, UIMlLicensee entity);

    /**
     * ライセンス発行
     * 
     * @param examNo 受験番号
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param entity ライセンス保持者管理Entity
     * @return 更新件数
     */
    public int updateIssueLicense(String examNo, String entryYear, String entryKai, UIMlLicensee entity);

    /**
     * ライセンス更新
     * 
     * @param entity ライセンス保持者管理Entity
     */
    public void updateLicense(UIMlLicensee entity);

    /**
     * ライセンス番号リスト取得
     * 
     * @return ライセンス番号リスト
     */
    public List selectLicenseNoList();
}
