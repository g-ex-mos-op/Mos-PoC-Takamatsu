package jp.co.isid.mos.bird.bizsupport.budgetregist.logic;

import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * ユーザー所属管理会社の検索ロジック インターフェイス
 * @author Aspac
 */
public interface ConditionLogic {
    /**
     * ユーザー所属管理会社の検索を行う
     * @param String userId ユーザーID
     * @return List  
     * @exception ApplicationException
     */
    public void execute(BudgetRegistDto dto) throws ApplicationException;
}