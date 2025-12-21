/*
 * 作成日: 2006/02/09
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;

/**
 * ユーザー情報
 * 
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public interface UIUserDao {

	public static final Class BEAN = UIUser.class;

	public static final String getUser_ARGS = "user_id";

    public static final String updateUser_PERSISTENT_PROPS 
    = "usertypeCd, userNameKj, userNameKana, limitKbn, bumonCd, mailAdd" +
    		", sekyuFlg, appliedDt, seikyuDt, seikyuUpdtDt, stopFlg, lastUser, lastPgm";

    public UIUser getUser(String user_id);

	public int insert(UIUser user);
	public int updateUser(UIUser user);
}
