/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.action;

/**
 * ロール別アクセス権限設定処理アクションインターフェース
 * @author itamoto
 */
public interface RoleActConditionAction {

    /**
     * 初期処理
     */
    public String initialize();

    /**
     * 機能の追加処理
     */
    public String addFunction();
    
    /**
     * ロールの追加処理 
     */
    public String addRole();
    
    /**
     * checkボックスのチェック 
     *
     */
    public String select();
}
