package jp.co.isid.mos.bird.bizsupport.energyamount.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * エネルギー使用量ダウンロード アクション
 * @author xnkusama
 *
 */
public interface EnergyAmountAction extends CommonAction {

    /**
     * CSVダウンロードアクション
     * @return
     */
    public String downloadCsv();
    /**
     * 店検索フォーム呼び出し処理
     * @return 店検索フォームViewID
     */
    public String callMiseForm();
}