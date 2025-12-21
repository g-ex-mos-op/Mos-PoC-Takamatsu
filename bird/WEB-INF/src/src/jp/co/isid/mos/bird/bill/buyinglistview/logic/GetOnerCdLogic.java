/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.buyinglistview.logic;

import jp.co.isid.mos.bird.bill.buyinglistview.entity.UIOnerCd;

/**
 * オーナーコード報取得ロジック
 * 
 * @author xlee
 */
public interface GetOnerCdLogic {

    /**
     * オーナーコード取得
     * @param　userId ログインユーザーID
     * @return　オーナーコード
     */
    public UIOnerCd execute(String userId);
}
