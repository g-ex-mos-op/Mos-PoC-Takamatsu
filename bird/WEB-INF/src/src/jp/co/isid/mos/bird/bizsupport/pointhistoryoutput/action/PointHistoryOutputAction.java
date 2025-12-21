/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.action;

/**
 * ポイント履歴出力アクションインターフェース
 * @author Yuichi Tamura(ISID-AO)
 */
public interface PointHistoryOutputAction {

    /**
     * 条件画面(初期処理)
     */
    public String initialize();

    /**
     * CSVダウンロード
     */
    public String downloadCsv();


}