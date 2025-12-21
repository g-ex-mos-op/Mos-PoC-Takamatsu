/*
 * 作成日: 2006/02/08
 */
package jp.co.isid.mos.bird.office.formregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 関連文書の重複チェック
 * @author xytamura
 */
public interface CheckKanrenBunshoLogic {
    /**
     * 関連文書の重複チェック
     * @param List 登録済み関連文書リスト
     * @param List 追加関連文書リスト
     * @exception ApplicationException
     */
    public List execute(List listKanren, List listAddKanren);
}