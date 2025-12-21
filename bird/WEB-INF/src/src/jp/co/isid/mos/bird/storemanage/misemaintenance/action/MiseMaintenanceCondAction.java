/*
 * 作成日: 2006/02/09
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.action;

/**
 * 個店情報メンテナンス画面アクション インターフェイス
 * @author xnkusama
 */
public interface MiseMaintenanceCondAction {

    /**
     * 実行
     * @return
     */
    public String execute();

    /**
     * 店検索フォーム
     * @return
     */
    public String callMiseForm();
}
