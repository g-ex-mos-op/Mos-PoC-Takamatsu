/*
 * 作成日: 2006/1/27
 */
package jp.co.isid.mos.bird.portal.top.action.inside;

import jp.co.isid.mos.bird.framework.action.FileDownloadAction;

/**
 * 売り上げ速報アクションインターフェース
 * 
 * 作成日:2009/01/08
 * @author xkinu
 *
 */
public interface SokuhoInsideaction  extends FileDownloadAction {
    /**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize();
	/**
     * ダウンロード メイン処理
     * 
     */
    public void download();
}
