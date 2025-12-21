/*
 * ì¬“ú: 2006/03/02
 */
package jp.co.isid.mos.bird.bizadmin.accountref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountref.entity.CtlUserOner;

/**
 * @author —¶
 */
public interface CtlUserOnerDao {
	public static final Class BEAN = CtlUserOner.class;
	
	public static final String getCltUserOner_ARGS = "user_id";
	
	public List getCltUserOner(String user_id);

}
