/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UISaleStateInfo;

/**
 * 各日付とメニューに対する販売数・予約数の情報
 * 
 * @author xlee
 */
public interface UISaleStateInfoDao {

    public static final Class BEAN = UISaleStateInfo.class;

    public static final String getSaleStateInfo_ARGS = "sysDate, ckanriNo, miseCd, menuGroup, taishoKikanFr, taishoKikanTo";

    /**
     * 各日付とメニューに対する販売数・予約数の情報を取得
     * @param sysDate システム日付
     * @param ckanriNo 管理番号
     * @param miseCd 店舗コード
     * @param menuGroup メニューグループコード
     * @param taishoKikanFr 対象期間FROM
     * @param taishoKikanTo 対象期間TO
     * @return 各日付とメニューに対する販売数・予約数の情報リスト
     */
    public List getSaleStateInfo(String sysDate, String ckanriNo, String miseCd, String menuGroupCd,  String taishoKikanFr, String taishoKikanTo);
}
