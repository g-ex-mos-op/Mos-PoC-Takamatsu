/*
 * ì¬“ú: 2006/11/29
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.logic;

import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefCondDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefYMDto;


/**
 * —\Z“o˜^æ“¾ˆ—ƒƒWƒbƒN
 * 
 * @author inazawa
 */
public interface BudgetRefInfoLogic {

    /**
     * —\Z“o˜^æ“¾ˆ—ƒƒWƒbƒN
     */
    public Map execute(BudgetRefCondDto condDto,BudgetRefYMDto ymdDto);
}
