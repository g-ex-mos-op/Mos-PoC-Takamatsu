/*
 * 作成日: 2006/03/01
 *
 */
package jp.co.isid.mos.bird.communication.notificationreference.action;

/**
 * @author m.onodera
 *
 */
public interface NotificationReferenceAction {

    /**
     * 初期処理
     * @return
     */
    public String initialize();
    
    /**
     * 検索
     * @return
     */
    public String search();
    
    /**
     * ページ変更
     * @return
     */
    public String changePage();
    
    /**
     * タブ切替
     * @return
     */
    public String changeTab();
}
