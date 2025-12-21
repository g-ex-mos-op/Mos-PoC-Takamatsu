/*
 * ì¬“ú: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.MlResultStatusRefDto;

/**
 * @author Noh
 */
public interface GetConditionLogic {
	/**
	 * ‰Šú‰»ˆ—Às
	 * 
	 * @param birdUserInfo
	 * @param birdDateInfo
	 * @param mlResultStatusRefDto
	 */
	public void execute(
			BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
			, MlResultStatusRefDto mlResultStatusRefDto);
}
