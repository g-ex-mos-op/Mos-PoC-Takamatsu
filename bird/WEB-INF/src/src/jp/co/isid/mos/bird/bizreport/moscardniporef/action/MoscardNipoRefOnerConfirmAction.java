package jp.co.isid.mos.bird.bizreport.moscardniporef.action;

/**
 * 営業日報の結果画面(オーナー)アクション・インターフェース
 *
 * @author   xjung
 */
public interface MoscardNipoRefOnerConfirmAction {

    /**
     * 初期処理(実行、営業日報タブリンク)
     * @return String 遷移先ビューID
     */
    public String initialize();

    /**
     * 再検索
     * @return String 遷移先ビューID
     */
    public String reSearch();

    /**
     * CSVダウンロード
     * @return String 遷移先ビューID
     */
    public void downloadCsvOner();

    /**
     * CSVダウンロード
     * @return String 遷移先ビューID
     */
    public void downloadCsvGepo();

    /**
     * 店舗リンク
     * @return String 遷移先ビューID
     */
    public String selectedMise();

    /**
     * 営業日報タブリンク
     * @return String 遷移先ビューID
     */
    public String goEigyoNipo();
    
    /**
     * 宅配売上日報タブリンク
     * @return String 遷移先ビューID
     */
    public String goTakuhaiNipo();

    /**
     * 屋号別売上タブリンク
     * @return String 遷移先ビューID
     */
    public String goYago();
    
    /**
     * ネット注文タブリンク
     * @return String 遷移先ビューID
     */
    public String callNetorder();

}
