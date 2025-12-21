/*
 * 作成日:2007/02/07
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.action;

/**
 * POS集信エラー店舗一覧 アクション
 * @author xkonishi
 *
 */
public interface PosErrorRefAction {
    
    /**
     * 初期処理
     * @return
     */
    public String initialize();
    
    /**
     * 実行
     * @return
     */
    public String execute();
    
    /**
     * CSVダウンロード
     * @return
     */
    public String csvDownload();

}