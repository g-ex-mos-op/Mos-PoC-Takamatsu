/*
 * 作成日: 2008/11/10
 */
package jp.co.isid.mos.bird.togouser.filterregist.dao;

import jp.co.isid.mos.bird.togouser.filterregist.entity.UIUserFilter;

/**
 * BIRDユーザ登録
 * @author S.yamauchi
 */
public interface UIUserFilterDao {

    public static final Class BEAN = UIUserFilter.class;
    public static final String insert_ARGS = "USER_ID, GROUP_CD, APPDATE, RENKEI_FLG, LOGIN_USER, PGM, LAST_TIME";
    public static final String delete_ARGS = "USER_ID";
    /**
	 * 統合ユーザの最新情報取得
	 * 
	 * @param uiTogoUserSaisin
	 */
    public int insert(UIUserFilter uiUserFilter);

    public int delete(String userId);
}
