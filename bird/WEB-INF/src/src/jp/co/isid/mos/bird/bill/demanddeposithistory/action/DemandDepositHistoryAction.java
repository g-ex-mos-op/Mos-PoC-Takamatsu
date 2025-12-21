/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * ご請求ご入金履歴・結果画面アクション
 * 
 * @author xwatanabe
 */
public interface DemandDepositHistoryAction extends CommonAction {
        
    /**
     * オーナーフォーム呼び出し
     */
    public String callOnerForm();

    /**
     * 再検索
     */
    public String searchAgain();

    /**
     * 請求内訳情報の取得
     * 内訳画面へ取得と遷移
     */
    public String selectDetail();
}
