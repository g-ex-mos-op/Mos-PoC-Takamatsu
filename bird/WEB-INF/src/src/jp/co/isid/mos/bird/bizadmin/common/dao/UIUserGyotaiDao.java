/*
 * 作成日: 2006/02/09
 *
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserGyotai;

/**
 * ユーザー管理業態情報DAO
 * 
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public interface UIUserGyotaiDao {
	
	public static final Class BEAN = UIUserGyotai.class;
	
	public static final String select_ARGS = "userId";
	/**
	 * 検索処理を行います。
	 * 
	 * @param userId
	 * @return
	 */
	public List select(String userId);
}
