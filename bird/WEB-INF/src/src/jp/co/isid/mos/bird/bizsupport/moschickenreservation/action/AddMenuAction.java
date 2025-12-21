package jp.co.isid.mos.bird.bizsupport.moschickenreservation.action;

/**
 * モスチキン予約
 * 商品追加画面用アクションインターフェース
 * 
 * @author inazawa
 *
 */
public interface AddMenuAction {
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize();
    /**
     * 戻るアクション
     */
    public String back();
    /**
     *検索
     */
    public String exec();
    /**
     * 編集画面からの遷移
     */
    public String addMenuInit();
    /**
     * 決定ボタン
     */
    public String select();
}
