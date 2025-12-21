/*
 * 作成日: 2006/2/13
 */
package jp.co.isid.mos.bird.inforegist.informregist.logic;

import java.util.List;

/**
 * 既存のお知らせ一覧取得ロジックインターフェース
 * @author itamoto
 */
public interface GetNewsLogic {

    /**
     * 既存のお知らせ一覧取得処理
     * @param uiNews
     */
    public List execute(String regDate, String userId);
}
