/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.action;

/**
 * ポイント情報取込 初期画面アクション
 * @author yushuncheng
 *
 */
public interface PointInfoTakeinCondAction {

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 取込
     * @return 画面遷移情報
     */
    public String upload();

    /**
     * CSVエラーファイルダウンロード
     * @return
     */
    public String errorDownload();

	/**
     * 実行
     * @return 画面遷移情報
     */
	public String execute();

}
