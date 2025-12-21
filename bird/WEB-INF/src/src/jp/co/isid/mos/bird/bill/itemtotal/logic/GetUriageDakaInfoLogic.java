/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic;

import java.math.BigDecimal;

/**
 * 売上高報取得ロジック
 * 
 * @author xlee
 */
public interface GetUriageDakaInfoLogic {

    /**
     * 売上高を取得します
     * @param　tempoCd 店コード
     * @param　urikakeYm 売上年月
     * @return　売上高
     */
    public BigDecimal execute(String tempoCd, String urikakeYm);
}
