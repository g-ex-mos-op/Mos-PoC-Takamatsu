package jp.co.isid.mos.bird.config.summenuregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * メニュー集約マスタ設定 条件画面アクション
 * @author xnkusama
 *
 */
public interface SumMenuRegistCondAction extends CommonAction {

    /**
     * 編集アクション
     * @return
     */
    public String edit();
    
    /**
     * 新規登録
     */
    public String newEdit();
    
    /**
     * CSVダウンロード
     */
    public String downloadCsv();
}
