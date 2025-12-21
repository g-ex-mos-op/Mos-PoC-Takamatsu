/*/
 * 作成日: 2008/10/27
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;


/**
 * ログイン失敗回数DAO
 * @author K.Nihonyanagi
 */
public interface CtlLoginFailKaisuDao {
    public Class BEAN = CtlLoginFailKaisu.class;
    public static final String CtlLoginFailKaisu_ARGS = "userId";

    /**
     * ログイン失敗回数の取得
     * @param userId
     * @return CtlLoginFailKaisu
     */
    public CtlLoginFailKaisu getCtlLoginFailKaisu(String userId);

    /**
     * ログイン失敗回数の更新
     * @param CtlLoginFailKaisu
     */
    public void updateCtlLoginFailKaisu(CtlLoginFailKaisu entity);
    
    /**
     * ログイン失敗回数の新規登録
     * @param CtlLoginFailKaisu entity
     */
    public void insertCtlLoginFailKaisu(CtlLoginFailKaisu entity);
}
