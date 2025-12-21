/*
 * 作成日: 2006/02/25
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.action;

/**
 * 個店情報メンテナンス画面アクション インターフェイス
 * @author xnkusama
 */
public interface MiseMaintenanceConfirmAction {

    /**
     * 更新
     * @return
     */
    public String execute();

    /**
     * 戻る
     * @return
     */
    public String back();
    
    /**
     * タブ切替
     * @return
     */
    public String changeTab();
}
