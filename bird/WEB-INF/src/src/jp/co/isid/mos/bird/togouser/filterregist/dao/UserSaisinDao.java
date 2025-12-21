/*
 * 作成日: 2008/11/10
 */
package jp.co.isid.mos.bird.togouser.filterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.togouser.filterregist.entity.UITogoUserSaisin;

/**
 * BIRDユーザ登録
 * @author S.yamauchi
 */
public interface UserSaisinDao {

    public static final Class BEAN = UITogoUserSaisin.class;
    public static final String select_ARGS = "USER_ID";
    /**
	 * 統合ユーザの最新情報取得
	 * 
	 * @param uiTogoUserSaisin
	 */
    public List select(String USER_ID);

}
