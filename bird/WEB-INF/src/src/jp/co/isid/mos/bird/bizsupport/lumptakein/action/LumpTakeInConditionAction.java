/*
 * 作成日: 2006/03/10
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.action;

/**
 * P/LデータCSV一括取込条件アクション
 * 
 * @author xyuchida
 */
public interface LumpTakeInConditionAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * CSV一括取込
     * 
     * @return 画面遷移情報
     */
    public String takeCsvFile();

    /**
     * エラー状況確認
     * 
     * @return 画面遷移情報
     */
    public String referError();

    /**
     * オーナー選択画面呼出
     * 
     * @return 画面遷移情報
     */
    public String callOwnerSearch();

    /**
     * オーナー選択
     * 
     * @return 画面遷移情報
     */
    public String selectOwnerCode();

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back();
}
