/**
 *
 */
package jp.co.isid.mos.bird.minisite.login.dao;

import java.util.List;

import jp.co.isid.mos.bird.minisite.login.entity.UIUser;

/**
 * ユーザーDao
 *
 * 作成日:2017/08/05
 * @author zcj
 *
 */
public interface UIUserDao {

    @SuppressWarnings("rawtypes")
	public static final Class BEAN = UIUser.class;

    public static final String select_ARGS = "userId";

    /**
     * ユーザー情報取得
     *
     * @param userId ユーザーコード
     * @return 店舗対象ユーザー情報
     */
    @SuppressWarnings("rawtypes")
	public List select(String userId);

}
