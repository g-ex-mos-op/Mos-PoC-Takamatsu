/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic;

import java.util.List;

/**
 * 商品名セットロジック
 * @author narita
 */
public interface SetShohinNameKjLogic {

	/**
     * 商品名のセットを行う
     * @param shohinList 代表商品リスト 
     * @return shohinList 代表商品リスト 
     */
    public List execute(List shohinList);
}
