/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.action;

/**
 * 【データ分析】時間帯別売上画面
 * 照会アクションインターフェース
 * 作成日:2008/09/25
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
	 * 曜日別ダウンロード処理
	 */
	public void downloadWeek();
	/**
	 * 日別推移ダウンロード処理
	 */
	public void downloadSuiiNipo();
	/**
	 * メニュー別時間帯別売上表ダウンロード処理
	 */
	public void downloadJikanbetu();
	/**
	 * 売上種別ダウンロード処理
	 */
	public void downloadShubetu();
    /**
     * 売上種別期間ダウンロード処理
     */
    public void downloadKikanShubetu();
}
