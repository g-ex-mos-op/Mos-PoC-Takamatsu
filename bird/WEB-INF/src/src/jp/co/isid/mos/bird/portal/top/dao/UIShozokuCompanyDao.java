/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.dao;

import java.util.List;

import jp.co.isid.mos.bird.portal.top.entity.UIShozokuCompany;

/**
 * 所属会社情報Dao
 * 
 * 作成日:2009/01/07
 * @author xkinu
 *
 */
public interface UIShozokuCompanyDao {
    public static final Class BEAN = UIShozokuCompany.class;
    public static final String select_ARGS = "userId";

    /**
     * 所属会社情報検索処理
     * 
     * 管理会社(BC05KCOM)に存在する企業コードで属性区分が1:所属の会社情報を検索します。
     * @param userId ログインユーザーID
     * @return List      検索結果
     */
    public List select(String userId);


}
