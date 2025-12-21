package jp.co.isid.mos.bird.bizreport.moscardniporef.action;

/**
 * 営業日報の結果画面(店舗別)アクション・インターフェース
 *
 * @author   xkhata
 * @modifier xjung  2006/10/03 総合営業日報タブ連携対応
 */
public interface MoscardNipoRefMiseConfirmAction {

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
     * アクション【SV検索ボタン】処理
     * @return String 遷移先ビューID
     */   
    public String callSvForm();
    
    /**
     * ネット注文タブリンク
     * @return String 遷移先ビューID
     */
    public String callNetorder();
}
