/*
 * 作成日: 2008/11/19
 */
package jp.co.isid.mos.bird.togouser.userregist.dao;

import jp.co.isid.mos.bird.togouser.userregist.entity.UITogoUserRireki;

/**
 * BIRDユーザ登録
 * @author K.Nihonyanagi
 */
public interface UITogoUserRirekiDao {

    public static final Class BEAN = UITogoUserRireki.class;
    
    /**
     * 統合ユーザの取得
     * @param userId 
     */
    public UITogoUserRireki getUITogoUserRireki (String userId);
    
    /**
     * 統合ユーザの新規登録
     * @param uiTogoUser
     */
    public void insertTogoUser (UITogoUserRireki uiTogoUser);

    /**
     * 統合ユーザの更新
     */
    public void updateUITogoUserRireki (UITogoUserRireki uiTogoUserRireki);
    
}
