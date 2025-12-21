/*
 * 作成日: 2006/02/01
 *
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIRole;

/**
 * @author xkhata
 *
 */
public interface UIRoleDao {

	public Class BEAN = UIRole.class;
	
    /**
     * ロール別アクセス権限の設定がされていないロールの取得
     * @param
     * @return
     */
	
	public List getRole();
	
	/**
     * ロール情報を全件取得する
     * @param
     * @return
     */
	
	public List getAllRole(String bunruiCd);
	
}
