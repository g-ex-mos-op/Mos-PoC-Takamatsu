/**
 * 
 */
package jp.co.isid.mos.bird.pmossles.login.dao;

import java.util.List;

import jp.co.isid.mos.bird.pmossles.login.entity.UIMiseUser;

/**
 * 店舗対象ユーザーDao
 * 
 * 作成日:2010/12/01
 * @author xkinu
 *
 */
public interface UIMiseUserDao {

    public static final Class BEAN = UIMiseUser.class;

    public static final String select_ARGS = "companyCd, miseCd";

    /**
     * 対象ユーザー情報取得
     * 
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @return 店舗対象ユーザー情報
     */
    public List select(String companyCd, String miseCd);

}
