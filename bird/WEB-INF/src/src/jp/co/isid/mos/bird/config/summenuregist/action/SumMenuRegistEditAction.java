package jp.co.isid.mos.bird.config.summenuregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * メニュー集約マスタ設定 編集画面アクション
 * @author xnkusama
 *
 */
public interface SumMenuRegistEditAction extends CommonAction {

    /**
     * 戻る
     * @return
     */
    public String back();
    
    /**
     * 確認
     */
    public String confirm();
    
    /**
     * 親メニュー削除
     */
    public String deleteOya();

    /**
     * 子メニュー削除
     */
    public String deleteKo();

    /**
     * 子メニュー追加
     */
    public String addKo();
}