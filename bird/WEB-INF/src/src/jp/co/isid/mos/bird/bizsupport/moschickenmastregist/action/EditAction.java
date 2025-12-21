package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action;

/**
 * モスチキン管理マスタ登録
 * 編集画面用アクションインターフェース
 * 
 * @author xkinu
 *
 */
public interface EditAction {
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize();
    /**
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back();
    /**
     * メニューグループ追加 アクション
     * 
     * @return null 画面ID
     */
    public String addMenuGroup();
    /**
     * メニュー追加 アクション
     * 
     * @return メニュー追加画面ID
     */
    public String addMenu();
    /**
     * 一括編集 アクション
     * 
     * 食包材を一括で選択を行うためのアクションです。
     * 
     * @return 食包材選択画面ID
     */
    public String lumpChoise();
    /**
     * 編集 アクション
     * 
     * 食包材の選択を行うためのアクションです。
     * 
     * @return 食包材選択画面ID
     */
    public String choise();
    /**
     * 削除(メニューグループ) アクション
     * 
     * @return null 画面ID
     */
    public String deleteMenuGroup();

    /**
     * 削除(メニュー) アクション
     * 
     * @return null 画面ID
     */
    public String deleteMenu();

    /**
     * 削除(食包材) アクション
     * 
     * @return null 画面ID
     */
    public String deleteShokuhouzai();
    /**
     * 確認 アクション
     * 
     * @return 確認画面ID
     */
    public String confirm();

}
