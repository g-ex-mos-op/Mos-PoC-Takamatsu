package jp.co.isid.mos.bird.bizsupport.campcheckamount.action;

/**
 * キャンペーン設定数量確認のアクション
 * 
 * @author xlee
 */
public interface CampCheckAmountAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 店検索フォーム：検索
     * @return 画面遷移情報
     */
    public String callMiseForm();
    
    /**
     * 店検索フォーム：検索
     * @return 画面遷移情報
     */
    public String callOnerForm();

    /**
     * 実行ボタン・再検索ボタンの処理
     * @return 画面遷移情報
     */
    public String execute();
    
    /**
     * タブの切替処理
     * @return 画面遷移情報
     */
    public String changeTab();
}
