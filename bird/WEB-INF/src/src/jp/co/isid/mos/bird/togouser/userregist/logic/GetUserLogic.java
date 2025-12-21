/*
 * çÏê¨ì˙: 2008/11/06
 *
 */
package jp.co.isid.mos.bird.togouser.userregist.logic;
/**
 * @author K.Nihonyanagi
 *
 */
import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;;


public interface GetUserLogic {
	
	public UITogoUserDto execute(String user_id, UITogoUserDto uiTogoUserDto);

}
