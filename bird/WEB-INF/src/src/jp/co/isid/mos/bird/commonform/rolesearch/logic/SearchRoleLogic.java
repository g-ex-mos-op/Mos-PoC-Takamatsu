/*
 * ì¬“ú: 2006/01/17
 *
*/
package jp.co.isid.mos.bird.commonform.rolesearch.logic;

import java.util.List;

/**
 * @author xyuchida
 *
 */
public interface SearchRoleLogic {

	public List execute(String bunruiCd);

	public List execute(List roleCds);
}
