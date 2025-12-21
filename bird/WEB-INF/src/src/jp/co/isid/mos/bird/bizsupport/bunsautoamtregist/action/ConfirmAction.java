/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.action;



/**
 * バンズ自動設定数量変更
 * 確認画面アクションインターフェース
 * 
 * @author xkinu
 */
public interface ConfirmAction {

    /**
     * 初期化処理
     * 
     * @return null
     */
    public String initialize();
    /**
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back();
    /**
     * 登録・終了　アクション
     * @return
     */
    public String regist();
    /**
     * 終了　アクション
     * @return
     */
    public String end();

}