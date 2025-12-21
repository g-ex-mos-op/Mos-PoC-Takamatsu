/*
 * 作成日: 2006/09/12
 */
package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * 宅配売上推移表・結果画面アクション
 * 
 * @author xwatanabe
 */
public interface TakuhaiSuiiRefAction extends CommonAction {
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
     * アクション【MOSCARD推移画面遷移】
     * @since 2013/05/02
     * @return String MOSCARD推移画面 VIEW_ID
     */
    public String callMoscardSuii();
    /**
     * アクション【売上推移画面遷移】
     * 
     * @return String 売上推移画面 VIEW_ID
     */
    public String callUriageSuii();
    /**
     * アクション【ネット注文推移画面遷移】
     * @since 
     * @return String ネット注文推移画面 VIEW_ID
     */
    public String callNetorderSuii();
}
