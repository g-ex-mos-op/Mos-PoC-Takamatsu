package jp.co.isid.mos.bird.bizsupport.budgetregist.logic;

import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;

/**
 * 事業計画予算登録CSV取込ロジック
 * 
 * @author Aspac
 */
public interface BudgetRegistCheckLogic {

    /**
     * 予算データCSV取込ロジック
     * @param BudgetRegistDto CSV取込DTO
     * @return 予算データ
     */
    public void execute(BudgetRegistDto budgetRegistDto);
}
