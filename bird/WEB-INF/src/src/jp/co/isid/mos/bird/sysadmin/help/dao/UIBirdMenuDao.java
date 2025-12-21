/*
 * 作成日: 2006/02/27
 *
 */
package jp.co.isid.mos.bird.sysadmin.help.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.help.entity.UIBirdMenu;

/**
 * 更新：2006/12/07　ユーザロールデフォルト設定対応にて
 * @author xlee
 *
 */
public interface UIBirdMenuDao {

	public Class BEAN = UIBirdMenu.class;
	
//    public static final String getMenu_ARGS      = "userId";
//    public static final String getActMenu_ARGS   = "userId";
    public static final String getExtraMenu_ARGS = "userId";

    
//	/**
//     * メニュー情報取得
//     * @param user_id ユーザＩＤ
//     * @return
//     */
//	public List getMenu(String userId);
//	
//	/**
//     * 使用不可メニューの取得
//     * @param user_id ユーザＩＤ
//     * @return
//     */
//	public List getActMenu(String userId);
	
    /**
     * 上限拡張可能メニューを取得する
     * @param  userId ユーザＩＤ
     * @return 上限拡張可能なメニュー
     */   
    public List getExtraMenu(String userId);

}
