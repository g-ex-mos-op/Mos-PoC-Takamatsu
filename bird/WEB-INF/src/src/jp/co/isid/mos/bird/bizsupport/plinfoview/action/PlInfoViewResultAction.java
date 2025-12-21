/*
 * 作成日: 2006/04/11
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.action;

/**
 * P/L照会画面 アクション インターフェイス
 * @author xnkusama
 */
public interface PlInfoViewResultAction {

    /**
     * 検索
     * @return
     */
    public String research();
    
    /**
     * 店検索フォーム
     * @return
     */
    public String callMiseForm();

    /**
     * オーナー検索フォーム
     * @return
     */
    public String callOnerForm();
    
    /**
     * 原価情報表示
     * @return
     */
    public String dispGenkaInfo();
    
    /**
     * 戻るボタン
     * @return
     */
    public String back();

    /**
     * CSVダウンロード
     * @return
     */
    public String downloadCsv();
}
