/*
 * 作成日: 2008/11/10
 */
package jp.co.isid.mos.bird.togouser.userregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.togouser.userregist.entity.UIBirdUser;

/**
 * BIRDユーザ登録
 * @author K.Nihonyanagi
 */
public interface UIBirdUserDao {

    public static final Class BEAN = UIBirdUser.class;

    public static final String getBirdUser_ARGS  = "USER_ID";
    public static final String getBirdUser_QUERY = "USER_ID = /*USER_ID*/";
    
    /**
	 * BIRDユーザの新規登録(insertBirdUser)
	 * 
	 * @param uiBirdUser
	 */
    public void insertBirdUser (UIBirdUser uiBirdUser);

    /**
     * BIRDユーザの更新(updateBirdUser)
     * 
     * @param uiBirdUser
     */
    public void updateBirdUser (UIBirdUser uiBirdUser);

    /**
     * BIRDユーザの取得(getBirdUser)
     * @param userId  ユーザID
     * @return 検索結果
     */
    public UIBirdUser getBirdUser (String userId);
    
    /**
     * BIRDユーザの取得(getBirdUser)
     * @param userId  ユーザID
     * @return 検索結果
     */
    public List getUserList (String userId);
    
}
