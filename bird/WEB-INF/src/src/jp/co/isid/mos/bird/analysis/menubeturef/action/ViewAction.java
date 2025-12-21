/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.action;

/**
 * 【データ分析】メニュー別売上画面
 * 照会アクションインターフェース
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public interface ViewAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 店舗選択フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callMiseForm();
    /**
     * 実行or再検索
     * @return
     */
    public String search();
	/**
	 * ABC分析表【単品】ダウンロード処理
	 */
	public void downloadAbc();
	/**
	 * ABC分析表【集約】ダウンロード処理
	 */
	public void downloadAbcSMenu();
	/**
	 * 売上種別ダウンロード処理
	 */
	public void downloadShubetu();
	/**
	 * メニュー別時間帯別売上表ダウンロード処理
	 */
	public void downloadJikanbetu();
	/**
	 * 食材準備目安表ダウンロード処理
	 */
	public void downloadMeyasu();

}
