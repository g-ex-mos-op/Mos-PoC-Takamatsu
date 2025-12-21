/*
 * ì¬“ú: 2006/03/03
 *
 */
package jp.co.isid.mos.bird.bizadmin.accountref.dao;

import jp.co.isid.mos.bird.bizadmin.accountref.entity.UIViewUser;

/**
 * @author —¶
 *
 */
public interface RefViewUserDao {
	public static final Class BEAN = UIViewUser.class;
	
	public static final String getUser_ARGS = "user_id";
	
	public UIViewUser getUser(String user_id);

}
