/*
 * 作成日: 2006/02/16
 *
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIRole;

/**
 * @author xlee
 *
 */
public interface UIRoleDao {

	public Class BEAN = UIRole.class;
	
    public static final String getRole_ARGS = "userId";
    public static final String getRoleKanri_ARGS = "userId, userTypeCd";
	
	/**
     * 対象ユーザに紐付けられているロールの取得
     * @param user_id ユーザＩＤ
     * @return
     */
	
	public List getRole(String userId);
	
	/**
     * 対象ユーザの会社とユーザタイプにより決定されるロールの取得する
     * @param user_id ユーザＩＤ
     * @param user_id ユーザType_Code
     * @return
     */
	
	public List getRoleKanri(String userId, String userTypeCd);
	
}
