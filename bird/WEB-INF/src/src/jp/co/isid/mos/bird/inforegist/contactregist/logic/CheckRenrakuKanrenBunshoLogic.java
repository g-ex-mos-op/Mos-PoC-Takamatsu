package jp.co.isid.mos.bird.inforegist.contactregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

public interface CheckRenrakuKanrenBunshoLogic {

    /**
     * 関連文書の重複チェック
     * @param List 登録済み関連文書リスト
     * @param List 追加関連文書リスト
     * @exception ApplicationException
     */
    public abstract List execute(List listKanren, List listAddKanren)
            throws ApplicationException;

}