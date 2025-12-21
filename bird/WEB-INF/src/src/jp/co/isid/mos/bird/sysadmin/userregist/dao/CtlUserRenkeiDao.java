/*
 * 作成日: 2006/2/20
 */
package jp.co.isid.mos.bird.sysadmin.userregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userregist.entity.CtlUserRenkei;

/**
 * ユーザ連携情報登録
 * @author itamoto
 */
public interface CtlUserRenkeiDao {

    public static final Class BEAN = CtlUserRenkei.class;

    public static final String getUserRenkei_ARGS  = "E_USER_ID";
    public static final String getUserRenkei_QUERY = "E_USER_ID = /*E_USER_ID*/";

    /**
     * ユーザ連携情報の新規登録(insertBirdUser)
     * @param ctlUserRenkei  エンティティ
     * @return int なし
     */
    public int insertBirdUser (CtlUserRenkei ctlUserRenkei);

    /**
     * ユーザ連携情報の取得(getUserRenkei)
     * @param eMosslesUserId  e-mosslesユーザID
     * @return List 検索結果
     */
    public List getUserRenkei (String eMosslesUserId);
}
