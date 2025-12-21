/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiDto;

/**
 * 請求書分類情報取得ロジック
 * 
 * @author xlee
 */
public interface GetBunruiInfoLogic {

    /**
     * 請求書分類情報取得
     * @return　請求書分類情報リスト
     */
    public List execute(ShobetuGoukeiDto shobetuGoukeiDto);
}
