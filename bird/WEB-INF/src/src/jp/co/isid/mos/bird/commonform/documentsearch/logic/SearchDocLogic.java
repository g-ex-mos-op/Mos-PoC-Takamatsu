/*
 * çÏê¨ì˙: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.documentsearch.logic;

import java.util.List;

import jp.co.isid.mos.bird.commonform.documentsearch.dto.DocumentSearchConditionDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * @author m.onodera
 *
 */
public interface SearchDocLogic {
	/**
	 * é¿çsèàóù
	 * 
	 * @param dto
	 * @param userInfo
	 * @param dateInfo
	 * @return
	 */
	public List execute(DocumentSearchConditionDto dto, BirdUserInfo userInfo, BirdDateInfo dateInfo);
}
