/*
 * 作成日:2007/02/06
 */
package jp.co.isid.mos.bird.storemanage.posniporef.action;

/**
 * POS日報　画面アクション
 * @author xwatanabe
 *
 */
public interface PosNipoRefAction {
   
    /**
     * 初期処理
     */ 
    public String initialize();

    /**
     * 店舗選択
     */
    public String miseSearch();

    /**
     * オーナー選択
     */
    public String ownerSearch();

    /**
     * 実行（検索処理）
     */
    public String execute();

    /**
     * 表示タブ変更時処理
     */
    public String changeTab();

    /**
     * 会社プルダウン変更時処理
     */
    public String changeCompany();

    /**
     * CSVダウンロード
     */
    public String downloadCsv();
}
