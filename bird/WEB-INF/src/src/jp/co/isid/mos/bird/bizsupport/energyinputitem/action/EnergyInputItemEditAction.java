package jp.co.isid.mos.bird.bizsupport.energyinputitem.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * 店舗メーター管理状況メンテナンス 確認画面アクション
 * @author xnkusama
 *
 */
public interface EnergyInputItemEditAction extends CommonAction {

    /**
     * 戻るアクション
     */
    public String back();
    
    /**
     * 実行アクション
     * @return
     */
    public String execute();
}
