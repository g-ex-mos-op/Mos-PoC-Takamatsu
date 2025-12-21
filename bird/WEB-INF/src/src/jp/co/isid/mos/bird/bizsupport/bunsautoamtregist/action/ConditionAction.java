/*
 * 作成日: 2006/12/08
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.action;

/**
 * バンズ自動設定数量変更
 * 条件画面アクションインターフェース
 * 
 * @author xkinu
 */
public interface ConditionAction {

    /**
     * 初期化処理
     * 
     * @return null
     */
    public String initialize();
    /**
     * 検索（オーナー選択）　アクション
     * 
     * @return
     */
    public String callOnerForm();
    /**
     * 検索（店舗選択）　アクション
     * 
     * @return
     */
    public String callMiseForm();
    /**
     * 検索（SV選択）　アクション
     * 
     * @return
     */
    public String callSvForm();
    /**
     * 実行　アクション
     * @return
     */
    public String search();
}