/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.moscardsuiiref.action;

/**
 * ACTION【照会画面アクション】インターフェース
 * 
 * 作成日:
 * @author 
 *
 */
public interface MoscardSuiiRefAction {
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
     * アクション【実行】処理
     * @return String 自画面VIEW_ID
     */   
    public String search();
    /**
     * アクション【タブ切替】
     * 
     * @return String 自画面VIEW_ID
     */
    public String changeTab();
    
    /**
     * アクション【売上推移画面遷移】
     * 
     * @return String 売上推移画面 VIEW_ID
     */
    public String callUriageSuii();
    /**
     * アクション【宅配売上推移画面遷移】
     * 
     * @return String 宅配売上推移画面 VIEW_ID
     */
    public String callTakuhaiSuii();
    /**
     * アクション【ネット注文推移画面遷移】
     * @since 
     * @return String ネット注文推移画面 VIEW_ID
     */
    public String callNetorderSuii();
    
}
