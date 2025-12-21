package jp.co.isid.mos.bird.entry.projectplanoffer.logic;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferJoinPersonInfo;

/**
 * 事業方針説明会　入力データチェック
 * @author xlee
 */

public interface CheckOfferInputParamLogic {
    
    /**
     * 
     * @param dto
     */
	public void validater(ProjectPlanOfferDto projectPlanOfferDto, Map paramMap);
    
	/**
	 * 
	 * @param str
	 */
    public boolean isNull(String str);
    
    /**
	 * 
	 * @param str
	 */
    public boolean chkKyoeiFlg(List joinPersInfo);
    
    /**
	 * 
	 * @param str
	 */
    public boolean chkJoinPersInfo(List joinPersInfo);
    
    /**
     * パラメータのエンティティが登録対象か判断する
     * @param UIOfferJoinPersonInfo
     * @return boolean true:登録対象
     */
    public boolean checkRegistValidtity(UIOfferJoinPersonInfo entity);
}
