/*
 * 作成日: 2009/02/19
 *
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.UIBirdUser;


/**
 * 同一部門コードが設定されているBIRDユーザ情報を取得します
 * @author xnkusama
 */
public interface UIBirdUserDao {

    public Class BEAN = UIBirdUser.class;

    public static final String getUserInfo_ARGS = "userId";
    public static final String getBumonUserInfo_ARGS = "userId, bumonCd";
 
    /**
     * ユーザー情報の取得
     * @param userId
     * @return
     */
    public List getUserInfo(String userId);
    
    /**
     * 同一部門のBIRDユーザ情報を取得する（指定ユーザー以外）
     * @param userId ユーザＩＤ
     * @param bumonCd 部門C D
     * @return ユーザ情報
     */   
    public List getBumonUserInfo(String userId,String bumonCd);

}
