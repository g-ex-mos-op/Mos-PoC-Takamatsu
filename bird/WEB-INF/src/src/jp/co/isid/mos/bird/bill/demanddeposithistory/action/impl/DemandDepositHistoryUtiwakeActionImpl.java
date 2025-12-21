/*
 * 作成日: 2006/08/22
 *
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.action.impl;

import jp.co.isid.mos.bird.bill.demanddeposithistory.action.DemandDepositHistoryUtiwakeAction;

/**
 * ご請求ご入金履歴、内訳画面アクション
 * 
 * @author xwatanabe
 */
public class DemandDepositHistoryUtiwakeActionImpl implements DemandDepositHistoryUtiwakeAction {

    /** アクションID */
    public static final String initialize_ACTION_ID     = "BBS009A07";
    
    /** パラメータ */
    String seikyushoId;     //請求書ID

    
    /**
     * 初期化処理
     * @return 画面遷移情報
     */
    public String initialize() {

        //自画面に遷移
        return null;
    }
    

    /**
     * 請求書IDを設定します。
     * @param  seikyushoId 請求書ID
     */
    public void setSeikyushoId(String seikyushoId) {
        this.seikyushoId = seikyushoId;
    }
    /**
     * 請求書IDを取得します。
     * @return 請求書ID
     */
    public String getSeikyushoId() {
        return seikyushoId;
    }
}
