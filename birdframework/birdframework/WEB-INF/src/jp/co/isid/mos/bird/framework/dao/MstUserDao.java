/*
 * 作成日: 2005/11/21
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;


/**
 * メニューを取得します
 * @author xytamura
 */
public interface MstUserDao {

    public Class BEAN = MstUser.class;

    public static final String getMstUser_ARGS = "userId";
    
    /**
     * 指定ユーザーのユーザー情報を取得する
     * @param user_id ユーザＩＤ
     * @return メニュー
     */   
    public List getMstUser(String userId);

}
