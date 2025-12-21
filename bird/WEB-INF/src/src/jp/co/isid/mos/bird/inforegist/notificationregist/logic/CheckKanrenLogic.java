/*
 * 作成日: 2006/01/23
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.logic;

import java.util.List;

/**
 * 関連文書の重複チェック
 * @author m.onodera
 */
public interface CheckKanrenLogic {
    /**
     * 関連文書の重複チェック
     * @param List 登録済み関連文書リスト
     * @param List 追加関連文書リスト
    */
    public List execute(List listKanren, List listAddKanren);
}