/*
 * ì¬“ú: 2006/03/02
 */
package jp.co.isid.mos.bird.bizadmin.accountref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountref.entity.CtlUserGyotai;

/**
 * @author —¶
 */
public interface CtlUserGyotaiDao {
	public static final Class BEAN = CtlUserGyotai.class;
	
	public static final String getCltUserGyotai_ARGS = "user_id";
	
	public List getCltUserGyotai(String user_id);
}
