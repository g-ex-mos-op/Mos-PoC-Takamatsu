/*
 * ì¬“ú: 2006/03/02
 */
package jp.co.isid.mos.bird.bizadmin.accountref.dao;

import jp.co.isid.mos.bird.bizadmin.accountref.entity.CtlUserMise;

/**
 * @author —¶
 */
public interface CtlUserMiseDao {
	public static final Class BEAN = CtlUserMise.class;
	
	public static final String getCltUserMise_ARGS = "user_id";
	
	public CtlUserMise getCltUserMise(String user_id); 
}
