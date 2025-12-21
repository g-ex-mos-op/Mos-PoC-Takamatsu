/*
 * 作成日: 2019/08/16
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.urimaintenanceview.entity.GetKeigenTaxData;

/**
 * 売上高ど消費税確認情報取得
 *
 * @author xwatanabe
 */
public interface KeigenTaxInfoDao {

    public static final Class BEAN = GetKeigenTaxData.class;

    public static final String select_ARGS = "sysDate,companyCd, syuseiDate";
    public static final String selectTotal_ARGS = "companyCd, syuseiDate";

    /**
     * 売上高ど消費税確認情報取得
     * @param String sysDateシステム日付
     * @param String companyCd  会社コード
     * @param String syuseiDate 修正日
     * @return List
     */
    public List select(String sysDate, String companyCd, String syuseiDate);

    /**
     * 売上高ど消費税合計確認情報取得
     * @param String companyCd  会社コード
     * @param String syuseiDate 修正日
     * @return List
     */
    public List selectTotal(String companyCd, String syuseiDate);
}
