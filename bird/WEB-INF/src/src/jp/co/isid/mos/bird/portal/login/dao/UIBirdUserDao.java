/*
 * 作成日: 2006/01/11
 *
 */
package jp.co.isid.mos.bird.portal.login.dao;

import java.util.List;

import jp.co.isid.mos.bird.portal.login.entity.UIBirdUser;


/**
 * ログイン画面用BIRDユーザー情報DAO
 * @author xnkusama
 */
public interface UIBirdUserDao {

    public Class BEAN = UIBirdUser.class;

    public static final String getUserInfo_ARGS = "userId";
//    public static final String updatePassword_ARGS = "userId, oldPassword, newPassword, updateDt";
    
    /**
     * ユーザー情報の取得
     * @param userId
     * @return
     */
    public List getUserInfo(String userId);
    
    /**
     * パスワード変更
     * @param userId ユーザＩＤ
     * @param oldPassword 旧パスワード
     * @param newPassword 新パスワード
     * @param updateDt 登録日
     * @return 
     */   
    public void updatePassword(UIBirdUser entity);

}
