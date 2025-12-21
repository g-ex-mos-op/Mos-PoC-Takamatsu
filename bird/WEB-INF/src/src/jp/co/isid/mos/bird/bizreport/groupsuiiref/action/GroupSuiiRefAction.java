/*
 * 作成日: 2006/0/29
 */
package jp.co.isid.mos.bird.bizreport.groupsuiiref.action;


/**
 * グループ業績売上推移表（日次）
 * 画面アクション インターフェイス
 * 
 * @author xkinu
 */
public interface GroupSuiiRefAction {
    /**
     * アクション【初期化処理】
     * @return String 自画面VIEW_ID
     */   
    public String initialize();
    /**
     * アクション【店舗検索ボタン】処理
     * @return String 店舗検索画面VIEW_ID
     */   
    public String callMiseForm();
    /**
     * アクション【会社切替】処理
     * @return String 自画面VIEW_ID
     */   
    public String changeCompany();
    /**
     * アクション【検索】
     * @return String 自画面VIEW_ID
     */
    public String search();
    /**
     * アクション【ダウンロード】
     */
    public void downloadCsv();
    /**
     * アクション【タブ切替】
     * @return String 自画面VIEW_ID
     */
    public String changeTab();
    /**
     * アクション【ネット注文推移画面遷移】
     * @since 
     * @return String ネット注文推移画面 VIEW_ID
     */
    public String callNetorderSuii();
    /**
     * アクション【MOSCARD推移画面遷移】
     * @since 2013/04/22
     * @return String MOSCARD推移画面 VIEW_ID
     */
    public String callMoscardSuii();
    /**
     * アクション【宅配売上推移画面遷移】
     * 
     * @return String 宅配売上推移画面 VIEW_ID
     */
    public String callTakuhaiSuii();
    /**
     * アクション【通貨切替】
     * 
     * @return null
     */
    public String changeCurrency();
}