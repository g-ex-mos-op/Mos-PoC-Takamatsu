/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic;

import java.util.List;

/**
 * 商品別履歴情報報取得ロジック
 * 
 * @author xlee
 */
public interface GetRirekiInfoLogic {

    /**
     * 商品別履歴情報を取得します。
     * @param　miseCd 店コード
     * @param　shoJituCd 実商品コード
     * @param　condYm 売上年月（START）
     * @param　condYm 売上年月（END）
     * @return　商品別履歴情報リスト
     */
    public List execute(String miseCd, String shoJituCd, String condYMStr, String condYMEnd);
}
