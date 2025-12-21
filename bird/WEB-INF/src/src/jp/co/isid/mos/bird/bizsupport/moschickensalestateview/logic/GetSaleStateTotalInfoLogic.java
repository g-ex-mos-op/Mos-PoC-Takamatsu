/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dto.MosChickenSaleStateViewDto;

/**
 * 各日付とメニューに対する販売数・予約数の情報や
 * 各日付・メニューに対する販売数・予約数の情報取得ロジック
 * 
 * @author xlee
 */
public interface GetSaleStateTotalInfoLogic {

    /**
     * 各日付とメニューに対する販売数・予約数の情報や
 　　　　* 各日付・メニューに対する販売数・予約数の情報取得取得
     * @param mosChickenSaleStateViewDto DTO
     * @param sysDate システム日付
     * @param ckanriNo 管理番号
     * @param miseCd 店舗コード
     * @param menuGroup メニューグループコード
     * @param taishoKikanFr 対象期間FROM
     * @param taishoKikanTo 対象期間TO
     * @return　全体情報リスト
     */
    public List execute(MosChickenSaleStateViewDto mosChickenSaleStateViewDto,String sysDate);
}
