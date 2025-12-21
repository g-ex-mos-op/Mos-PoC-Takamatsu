/*
 * 作成日: 2016/02/14
 */
package jp.co.isid.mos.bird.storemanage.misehistoryextract.action;

import java.io.IOException;

/**
 * 店マスタ履歴抽出画面アクション インターフェイス
 */
public interface MiseHistoryExtractAction {

    /**
     * 初期処理
     */
    public String initialize();

	/**
	 * 店検索フォーム
	 */
	public String callMiseForm();

	/**
	 * ダウンロード(CSV)
	 */
	public void downloadCsv() throws IOException;

	/**
	 * ダウンロード(Excel)
	 */
	public void downloadExcel() throws IOException;
}
