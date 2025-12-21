package jp.co.isid.mos.bird.bizreport.takuhainiporef.action;

/**
 * 宅配売上日報の結果画面(支部別)アクション・インターフェース
 *
 * @author xjung
 * @modifier xkinu 2013/01/20 MOSCARD画面追加対応
 */
public interface TakuhaiNipoRefSibuAction {

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
     * サブタブ変更
     * @return String 遷移先ビューID
     */
    public String changeSubTab();

    /**
	 * CSVダウンロード
     * @return String 遷移先ビューID
	 */
    public void downloadCsvSibu();

    /**
	 * 支部リンク
     * @return String 遷移先ビューID
	 */
    public String selectedSibu();

    /**
	 * 営業日報タブリンク
     * @return String 遷移先ビューID
	 */
    public String goEigyoNipo();

	/**
	 * 屋号別売上タブリンク
     * @return String 遷移先ビューID
	 */
	public String goYago();
    /**
     * アクション【CSVダウンロード(店舗別)】
     */
    public void downloadCsvMise();
    /**
     * アクション【MOSCARD画面遷移】
     * 
     * @return String MOSCARD画面(支部一覧) VIEW_ID
     */
    public String callMoscard();
    /**
     * アクション【ネット注文画面遷移】
     * 
     * @return String ネット注文画面(支部一覧) VIEW_ID
     */
    public String callNetorder();

}