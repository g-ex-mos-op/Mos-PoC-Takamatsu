package jp.co.isid.mos.bird.bizreport.netorderniporef.action;

/**
 * ネット注文日報の結果画面(店舗別)アクション・インターフェース
 *
 */
public interface NetorderNipoRefMiseConfirmAction {

    /**
     * 初期処理(実行、ネット注文日報タブリンク)
     * @return String 遷移先ビューID
     */
    public String initialize();

    /**
     * 再検索
     * @return String 遷移先ビューID
     */
    public String reSearch();
 
    /**
     * 対象支部リスト取得
     * @return String 遷移先ビューID
     */
    public String reSearchTaishoSibuList();

    /**
     * CSVダウンロード
     * @return String 遷移先ビューID
     */
    public void downloadCsvMise();

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
     * アクション【MOSCARD画面遷移】
     * 
     * @return String MOSCARD画面(店舗一覧) VIEW_ID
     */
    public String callMoscard();
    /**
     * アクション【通貨切替】
     * 
     * @return null
     */
    public String changeCurrency();
    /**
     * アクション【SV検索ボタン】処理
     * @return String 遷移先ビューID
     */   
    public String callSvForm();
}
