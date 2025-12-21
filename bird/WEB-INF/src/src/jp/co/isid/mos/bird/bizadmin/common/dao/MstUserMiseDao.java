/*
 * 作成日: 2006/2/23
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserMise;

/**
 * ユーザ対応店舗(MstUserMiseDao）
 * @author itamoto
 */
public interface MstUserMiseDao {

    public static final Class BEAN = MstUserMise.class;
    public static final String update_PERSISTENT_PROPS = "companyCd, miseCd, lastUser, lastPgm";

    /**
	 * ユーザ対応店舗の新規登録(insert)
	 * @param mstUserMise エンティティ
	 * @return int なし
	 */
    public int insert(MstUserMise mstUserMise);
	public int update(MstUserMise user);
}
