package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action;

/**
 * 営業日報の結果画面(支部別)アクション・インターフェース
 *
 * @author   xkhata
 * @modifier xjung  2006/10/03 総合営業日報タブ連携対応
 */
public interface GroupEigyoNipoRefSibuConfirmAction {

    /**
     * 初期処理(実行、宅配売上タブリンク)
     * @return String 遷移先ビューID
     */
    public String initialize();
    /**
     * アクション【SV検索ボタン】処理
     * @return String 遷移先ビューID
     */   
    public String callSvForm();

    /**
     * 再検索
     * @return String 遷移先ビューID
     */
    public String reSearch();

    /**
     * CSVダウンロード
     * @return String 遷移先ビューID
     */
    public void downloadCsvSibu();
    /**
     * CSVダウンロード
     * @return String 遷移先ビューID
     */
    public void downloadCsvMise();

    /**
     * 支部リンク
     * @return String 遷移先ビューID
     */
    public String selectedSibu();

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
    
    /**
     * アクション【通貨切替】
     * 
     * @return null
     */
    public String changeCurrency();
}
