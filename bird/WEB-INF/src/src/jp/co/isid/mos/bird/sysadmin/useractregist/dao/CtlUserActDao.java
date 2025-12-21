/*
 * 作成日: 2006/02/22
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.dao;

import jp.co.isid.mos.bird.sysadmin.useractregist.entity.CtlUserAct;

/**
 * @author xkhata
 * 更新日：2009/12/22 xkinu UPDATEとDELETE処理を追加
 */
public interface CtlUserActDao {
	
	public Class BEAN = CtlUserAct.class;
	
    public static String insert_ARGS = "entity";
	public static String update_ARGS = "entity";
	public static final String update_PERSISTENT_PROPS = "customizeFlg, lastUser, lastPgm";

	
	/**
	 * ユーザ機能アクセス制限の新規登録
	 * @param entity
	 */
	public void insert(CtlUserAct entity);
	
	/**
	 * ユーザ機能アクセス制限の更新
	 * @param entity
	 */
	public void update(CtlUserAct entity);
	
	
}
