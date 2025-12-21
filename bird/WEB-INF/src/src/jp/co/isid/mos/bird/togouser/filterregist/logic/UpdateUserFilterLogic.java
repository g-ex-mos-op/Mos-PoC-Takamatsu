/*
 * ì¬“ú: 2009/02/23
 *
 */
package jp.co.isid.mos.bird.togouser.filterregist.logic;
/**
 * @author S.yamauchi
 *
 */
import java.util.List;

public interface UpdateUserFilterLogic {
	
	public List execute(List bumonlist,List userList, String appDate, String loginUser);

}
