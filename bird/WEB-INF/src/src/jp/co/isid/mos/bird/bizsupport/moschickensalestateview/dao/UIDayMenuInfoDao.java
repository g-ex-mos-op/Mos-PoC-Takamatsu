/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UIDayMenuSumInfo;

/**
 * メニュー・日付毎の合計計算ロジック
 * 
 * @author xlee
 */
public interface UIDayMenuInfoDao {

    public static final Class BEAN = UIDayMenuSumInfo.class;

    public static final String getDayMenuSumInfo_ARGS = "groupKbn, sysDate, ckanriNo, miseCd, menuGroup, taishoKikanFr, taishoKikanTo";

    /**
     * メニュー・日付毎の合計を取得
     * @param groupKbn　合計するグループの区分：メニューまたは日付
     * @param sysDate システム日付
     * @param ckanriNo 管理番号
     * @param miseCd 店舗コード
     * @param menuGroup メニューグループコード
     * @param taishoKikanFr 対象期間FROM
     * @param taishoKikanTo 対象期間TO
     * @return メニュー・日付毎の合計リスト
     */
    public List getDayMenuSumInfo(String groupKbn, String sysDate, String ckanriNo, String miseCd, String menuGroup, String taishoKikanFr, String taishoKikanTo);
}
