package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action;

/**
 * 営業日報の結果画面(オーナー)アクション・インターフェース
 *
 * @author   xjung
 */
public interface GroupEigyoNipoRefOnerConfirmAction {

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
     * アクション【MOSCARD画面遷移】
     * 
     * @return String MOSCARD画面(支部一覧) VIEW_ID
     */
    public String callMoscard();
    
    /**
     * アクション【ネット注文画面遷移】
     * 
     * @return String NETORDER画面(支部一覧) VIEW_ID
     */
    public String callNetorder();
}
