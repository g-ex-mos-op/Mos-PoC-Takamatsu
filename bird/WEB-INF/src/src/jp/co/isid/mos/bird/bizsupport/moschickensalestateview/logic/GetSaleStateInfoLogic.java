/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic;

import java.util.List;

/**
 * 各日付とメニューに対する販売数・予約数の情報取得ロジック
 * 
 * @author xlee
 */
public interface GetSaleStateInfoLogic {

    /**
     * 各日付とメニューに対する販売数・予約数の情報取得
     * @param sysDate システム日付
     * @param ckanriNo 管理番号
     * @param miseCd 店舗コード
     * @param menuGroup メニューグループコード
     * @param taishoKikanFr 対象期間FROM
     * @param taishoKikanTo 対象期間TO
     * @return　各日付とメニューに対する販売数・予約数の情報リスト
     */
    public List execute(String sysDate, String ckanriNo, String miseCd, String menuGroupCd, String taishoKikanFr, String taishoKikanTo);
}
