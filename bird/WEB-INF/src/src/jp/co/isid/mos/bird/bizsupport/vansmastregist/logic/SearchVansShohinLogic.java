/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic;

import java.util.List;

/**
 * 代表商品情報の取得ロジックインターフェース
 * @author narita
 */
public interface SearchVansShohinLogic {
    
    /**
     * 代表商品情報の取得を行う
     * @param kanriMoto 管理元コード
     * @param kanriList 管理元リスト
     * @return shohinList  代表商品情報のリスト
     */
    public List execute(String kanriMoto,List kanriList);
}
