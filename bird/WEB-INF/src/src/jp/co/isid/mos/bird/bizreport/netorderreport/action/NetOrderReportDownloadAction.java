/**
 *
 */
package jp.co.isid.mos.bird.bizreport.netorderreport.action;

/**
 * ACTION【ネット注文帳票画面】のインターフェース
 *
 */
public interface NetOrderReportDownloadAction {
    /**
     * 初期処理
     * @return
     */
	public String initialize();

	/**
	 * EXCEL形式でダウンロード
	 */
	public void downloadExcel();

	/**
	 * PDF形式でダウンロード
	 */
	public void downloadPdf() ;
	/**
	 * 対象期間・期間指定入力チェック
	 */
	public void checkParamater() ;

	/**
	 * ダウンロードインタフェースに戻る
	 * @return view_id
	 */
	public String modoru();

}
