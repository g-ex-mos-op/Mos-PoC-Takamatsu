/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.basicentry.action;

/**
 * 汎用研修マスタ登録　編集画面アクションインターフェース
 * @author itamoto
 */
public interface BasicEntryEditAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 戻る
     */
	public String cancel();

    /**
     * 入力欄追加
     */
    public String addRow();
    
    /**
     * 登録・終了
     */
    public String regist();
    
    /**
     * 申込取消
     */
    public String registCancel();
    
    /**
     * 新規スタッフ追加
     */
    public String addStaff();

    /**
     * スタッフ編集
     */
    public String editStaff();
}