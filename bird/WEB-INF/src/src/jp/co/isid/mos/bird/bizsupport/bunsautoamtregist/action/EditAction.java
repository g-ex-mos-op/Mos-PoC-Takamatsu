/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.action;



/**
 * バンズ自動設定数量変更
 * 編集画面アクションインターフェース
 * 
 * @author xkinu
 */
public interface EditAction {

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
     * 確認　アクション
     * @return
     */
    public String confirm();

}