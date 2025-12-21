package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action;

/**
 * モスチキン管理マスタ登録
 * 食包材選択画面用アクションインターフェース
 * 
 * @author xkinu
 *
 */
public interface ChoiseShokuhouzaiAction {
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize();
    /**
     * 戻る アクション
     * 
     * @return 編集画面ID
     */
    public String back();
    /**
     * 検索 アクション
     * 
     * @return null 画面ID
     */
    public String search();
    /**
     * 決定 アクション
     * 
     * @return 編集画面ID
     */
    public String choise();

}
