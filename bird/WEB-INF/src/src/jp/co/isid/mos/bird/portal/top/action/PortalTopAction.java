/*
 * 作成日: 2006/1/27
 */
package jp.co.isid.mos.bird.portal.top.action;

/**
 * ポータルトップアクションインターフェース
 * @author itamoto
 */
public interface PortalTopAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * マイメニュー登録
     * @return
     */
    public String addMyMenu();
    /**
     * マイメニュー削除
     * @return
     */
    public String deleteMyMenu();
}
