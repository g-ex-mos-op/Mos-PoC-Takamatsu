/*
 * 作成日: 2006/02/01
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.functionsearch.entity.UIFunction;;

public interface UIBirdMenuDao {
	
	public Class BEAN = UIFunction.class;
	
    /**
     * ロール別アクセス権限の設定がされていないBIRDメニューの取得
     * @param
     * @return
     */
	public List getBirdMenu();
	
	/**
     * BIRDメニュー情報を全件取得
     * @param
     * @return
     */
	public List getMainBirdMenu();
	
	/**
     * BIRDメインメニュー情報取得
     * @param
     * @return
     */
	public List getMainMenu();
	
}
