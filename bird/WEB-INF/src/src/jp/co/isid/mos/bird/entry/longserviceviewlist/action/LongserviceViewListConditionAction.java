/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.action;

/**
 * 永年勤続申請状況確認 アクションインターフェース
 * 
 * @author xamaruyama
 */
public interface LongserviceViewListConditionAction {

    /**
     * 初期化処理
     * 
     * @return null
     */
    public String initialize();
    /**
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back();
    /**
     * SV検索　アクション
     * 
     * @return
     */
    public String callSvForm();
    /**
     * 検索　アクション
     * @return
     */
    public String search() ;
}