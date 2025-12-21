/*
 * 作成日: 2006/02/01
 *
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.roleacterregist.entity.CtlRoleActer;

/**
 * @author xkhata
 *
 */
public interface CtlRoleActerDao {

	public Class BEAN = CtlRoleActer.class;
	
	public String getRoleMenuAll_ARGS = "menuId, firstRoleId, secondRoleId, thirdRoleId, forthRoleId, fifthRoleId";
	public String getMenuRoleAll_ARGS = "bunruiCd, firstID, secondID, thirdID, forthID, fifthID";
	public String getMenuRoleActer_ARGS = "bunruiCd, firstID, secondID, thirdID, forthID, fifthID";
	public String getRoleMenuActer_ARGS = "menuId, firstRoleId, secondRoleId, thirdRoleId, forthRoleId, fifthRoleId";
	public String deleteActerMenu_ARGS = "bunruiCd, firstID, secondID, thirdID, forthID, fifthID";
	public String deleteActerRole_ARGS = "menuId, firstRoleId, secondRoleId, thirdRoleId, forthRoleId, fifthRoleId";
	public String insetRemakeAllMainMenu_ARGS = "userId";
	/**
	 * メインメニュー更新引数
	 */
	public String updateRoleMainMenu_ARGS = "entity";
	/**
	 * メインメニュー更新対象カラム
	 */
    public static final String updateRoleMainMenu_PERSISTENT_PROPS = "enableFlg, extraFlg, lastUser, lastPgm, lastTmsp";
	/**
	 * メインメニュー更新引数
	 */
	public String deleteRoleMainMenu_ARGS = "entity";

    public String insertRoleActer_ARGS ="roleCd, menuId, subMenuId, enableFlg, extraFlg, firstUser";

	/**
	 * ロール情報とメニュー情報を紐付けた情報の取得
	 * @param menuId
	 * @param firstRoleId
	 * @param secondRoleId
	 * @param thirdRoleId
	 * @param forthRoleId
	 * @param fifthRoleId
	 * @return
	 */
	public List getRoleMenuAll
			( String menuId
			, String firstRoleId
			, String secondRoleId
			, String thirdRoleId
			, String forthRoleId
			, String fifthRoleId
			);

	/**
	 * メニュー情報とロール情報を紐付けた情報の取得
	 * @param bunruiCd
	 * @param firstID
	 * @param secondID
	 * @param thirdID
	 * @param forthID
	 * @param fifthID
	 * @return
	 */
	public List getMenuRoleAll
			( String bunruiCd
			, String firstID
			, String secondID
			, String thirdID
			, String forthID
			, String fifthID );

	/**
	 * 既に登録されているアクセス権限の取得
	 * @param menuId
	 * @param firstRoleId
	 * @param secondRoleId
	 * @param thirdRoleId
	 * @param forthRoleId
	 * @param fifthRoleId
	 * @return
	 */

	public List getRoleMenuActer
			( String menuId
			, String firstRoleId
			, String secondRoleId
			, String thirdRoleId
			, String forthRoleId
			, String fifthRoleId
			);

	/**
	 * メニュー情報に紐付くロールアクセス制御の取得
	 * @param bunruiCd
	 * @param firstID
	 * @param secondID
	 * @param thirdID
	 * @param forthID
	 * @param fifthID
	 * @return
	 */
	public List getMenuRoleActer
			( String bunruiCd
			, String firstID
			, String secondID
			, String thirdID
			, String forthID
			, String fifthID );

	/**
	 * メニュー情報、分類コードに紐付くロールアクセス制御の削除
	 * @param bunruiCd
	 * @param firstID
	 * @param secondID
	 * @param thirdID
	 * @param forthID
	 * @param fifthID
	 */
	public void deleteActerMenu
			( String bunruiCd
			, String firstID
			, String secondID
			, String thirdID
			, String forthID
			, String fifthID );
	
	/**
	 * ロールコード、メニューIDに紐付くロールアクセス制御の削除
	 * @param menuId
	 * @param firstRoleId
	 * @param secondRoleId
	 * @param thirdRoleId
	 * @param forthRoleId
	 * @param fifthRoleId
	 */
	public void deleteActerRole
			( String menuId
			, String firstRoleId
			, String secondRoleId
			, String thirdRoleId
			, String forthRoleId
			, String fifthRoleId
			);

    /**
     * ロールアクセス制御の追加
     * @param roleCd
     * @param menuId
     * @param subMenuId
     * @param enableFlg
     * @param extraFlg
     * @param firstUser
     */
    public void insertRoleActer( 
            String roleCd , String menuId , String subMenuId , String enableFlg , String extraFlg , String firstUser);
	/**
	 * メインメニューの更新
	 * 
	 * @param entity
	 */
	public int updateRoleMainMenu(CtlRoleActer entity);
	
	/**
	 * メインメニューの削除
	 * 
	 * @param entity
	 */
	public int deleteRoleMainMenu(CtlRoleActer entity);
	
	/**
	 * メインメニューに紐付いている全メニューを取得
	 *
	 */
	public List getAllMainMenu();
	/**
	 * 全メインメニューのロール情報を削除します
	 *
	 */
	public int deleteAllMainMenu();

	/**
	 * 全メインメニューのロール情報を再作成し新規登録を行います。
	 * 
	 * 注)メソッドdeleteAllMainMenu()の実行後の処理となります。
	 * @param userId
	 * @param pgm
	 * @return
	 */
	public int insertRemakeAllMainMenu(String userId);

}
