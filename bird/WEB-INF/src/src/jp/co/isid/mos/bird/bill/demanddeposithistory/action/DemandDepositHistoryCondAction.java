/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * ご請求ご入金履歴・条件画面アクション
 * 
 * @author xwatanabe
 */
public interface DemandDepositHistoryCondAction extends CommonAction {
        
	
    /**
     * オーナーフォーム呼び出し
     */
    public String callOnerForm();

    /**
     * 実行
     */
    public String execute();
}
