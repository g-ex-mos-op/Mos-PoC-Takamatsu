/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic;

import java.util.List;

/**
 *　商品別明細情報取得ロジック
 * 
 * @author xlee
 */
public interface GetMeisaiInfoLogic {

    /**
     * 商品別明細情報を取得します
     * @param　miseCd 店コード
     * @param　shoJituCd 実商品コード
     * @param　condYm 売上年月
     * @return　商品別明細情報リスト
     */
    public List execute(String miseCd, String shoJituCd, String condYm);
}
