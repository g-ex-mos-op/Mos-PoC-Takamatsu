package jp.co.isid.mos.bird.sysadmin.useractregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.useractregist.entity.UIBirdMenu;

/**
 * @author xkhata
 */
public interface UIBirdMenuDao {

	public Class BEAN = UIBirdMenu.class;

//    public static String getRoleMenu_ARGS = "userId";
    public static String getJogenRoleMenu_ARGS = "userId";

//	/**
//	 * 対象ユーザのロールにより紐付けられているメニューの取得 
//	 * @param userId
//	 * @return
//	 */
//	public List getRoleMenu(String userId);
    
    /**
     * 対象ユーザに付与可能なメニュー(上限)の取得 
     * @param userId
     * @return
     */
    public List getJogenRoleMenu(String userId);
}
