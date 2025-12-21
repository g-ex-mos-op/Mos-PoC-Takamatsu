/*
 * 作成日: 2006/02/09
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.action;

/**
 * 個店情報メンテナンス画面アクション インターフェイス
 * @author xnkusama
 */
public interface MiseMaintenanceEditAction {

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
    
    public String addRowBukken();
    public String delRowBukken();
    public String addRowChintai();
    public String delRowChintai();
}
