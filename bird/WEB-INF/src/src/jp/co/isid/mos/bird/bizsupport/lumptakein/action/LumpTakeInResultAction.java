/*
 * 作成日: 2006/03/10
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.action;

/**
 * P/LデータCSV一括取込結果アクション
 * 
 * @author xyuchida
 */
public interface LumpTakeInResultAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * P/Lデータ入力画面呼出
     * 
     * @return 画面遷移情報
     */
    public String callInput();

    /**
     * リフレッシュ
     * 
     * @return 画面遷移情報
     */
    public String refresh();

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back();
}
