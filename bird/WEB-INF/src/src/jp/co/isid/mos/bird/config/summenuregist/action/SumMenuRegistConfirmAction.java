package jp.co.isid.mos.bird.config.summenuregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * メニュー集約マスタ設定 確認画面アクション
 * @author xnkusama
 *
 */
public interface SumMenuRegistConfirmAction extends CommonAction {

    /**
     * 戻る
     * @return
     */
    public String back();
    
    /**
     * 登録･終了
     */
    public String regist();
    
}