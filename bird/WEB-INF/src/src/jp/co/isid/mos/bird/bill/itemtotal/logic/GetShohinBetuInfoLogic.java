/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiDto;

/**
 * 商品別情報報取得ロジック
 * 
 * @author xlee
 */
public interface GetShohinBetuInfoLogic {

    /**
     * 商品別情報取得
     * @param　shobetuGoukeiDto 商品別合計DTO
     * @return　商品別情報
     */
    public List execute(ShobetuGoukeiDto shobetuGoukeiDto);
}
