/*
 * 作成日:2010/03/16 xkinu
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserOner;

/**
 * ユーザー対応オーナー(BM06UONR)Dao
 * 
 * 作成日:2010/03/16
 * @author xkinu
 *
 */
public interface MstUserOnerDao {
	
	public static final Class BEAN = MstUserOner.class;
    public static final String deleteByUserId_SQL = "delete from BM06UONR where USER_ID = ?";

	public int deleteByUserId(String userId);
	
	public int delete(MstUserOner entity);
	
	public int insert(MstUserOner entity);
	
	public int update(MstUserOner entity);
}
