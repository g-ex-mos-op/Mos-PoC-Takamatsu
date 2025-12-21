/*
 * 作成日: 2006/02/10
 *
 */
package jp.co.isid.mos.bird.communication.contactreference.action;

/**
 * @author xyuchida
 *
 */
public interface ContactReferenceAction {

    /**
     * 初期処理
     */
    public String initialize();
    /**
     * 検索
     */
    public String search();
    /**
     * 詳細表示
     */
    public String showDetail();
    
//    public String callForm();
    /**
     * ページ切り替え
     */
    public String changePage();
    /**
     * タブ切り替え
     */
    public String changeTab();
}
