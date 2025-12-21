/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.logic;

import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferDto;

/**
 * 申込情報取得ロジック
 * 
 * @author xlee
 */
public interface GetOfferInfoLogic {

	/**
	 * 申込情報取得
	 * @param projectPlanOfferDto
	 * @return
	 */
    public ProjectPlanOfferDto execute(ProjectPlanOfferDto projectPlanOfferDto);
}
