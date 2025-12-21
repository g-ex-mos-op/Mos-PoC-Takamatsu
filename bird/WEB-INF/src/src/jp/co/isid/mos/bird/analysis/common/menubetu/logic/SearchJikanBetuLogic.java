/**
 * 
 */
package jp.co.isid.mos.bird.analysis.common.menubetu.logic;

import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;

/**
 * ì¬“ú:2008/09/17
 * @author xkinu
 *
 */
public interface SearchJikanBetuLogic {
	/**
	 * ˆ—Às
	 * 
	 * @param requestDto
	 * @return
	 */
    public List execute(MenuBetuReqDto requestDto);

}
