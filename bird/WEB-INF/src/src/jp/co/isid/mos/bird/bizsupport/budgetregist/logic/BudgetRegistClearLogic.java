package jp.co.isid.mos.bird.bizsupport.budgetregist.logic;

import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;

/**
 * –‹ÆŒv‰æ—\Z“o˜^CSVæƒƒWƒbƒN
 * 
 * @author Aspac
 */
public interface BudgetRegistClearLogic {

    /**
     * ‰ºŠú—\ZƒNƒŠƒAƒƒWƒbƒN
     * @param BudgetRegistDto CSVæDTO
     * @return —\Zƒf[ƒ^
     */
    public void execute(BudgetRegistDto budgetRegistDto);
}
