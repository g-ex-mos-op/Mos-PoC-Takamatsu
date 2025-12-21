/*
 * 作成日: 2006/2/20
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserGyotai;

/**
 * ユーザ管理業態(MstUserGyotaiDao）
 * @author itamoto
 */
public interface MstUserGyotaiDao {

    public static final Class BEAN = MstUserGyotai.class;
    public static final String deleteByUserId_SQL = "delete from BM05USGT where USER_ID = ?";

    /**
     * ユーザ管理業態の新規登録(insertBirdUser)
     * @param mstUserGyotai  エンティティ
     * @return int なし
     */
    public int insert(MstUserGyotai mstUserGyotai);
    /**
     * ユーザ管理業態の削除登録
     * 対象ユーザーの情報を全て削除します。
     * @param userId
     */
	public void deleteByUserId(String userId);
}
