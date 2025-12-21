/*
 * 作成日: 2006/11/30
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.action;
/**
 * 事業計画予算確認アクション
 * 
 * @author inazawa
 */
public interface ConditionAction {

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() throws Exception;


    /**
     * 検索
     * @return 画面遷移情報
     */
    public String execute();

}