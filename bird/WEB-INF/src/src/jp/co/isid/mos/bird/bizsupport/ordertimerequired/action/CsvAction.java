/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.ordertimerequired.action;

/**
 * オーダー提供時間ダウンロード
 * 
 * 初期画面アクションインターフェース
 * 
 * 作成日:2009/10/15
 * @author xkinu
 *
 */
public interface CsvAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 店舗選択フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callMiseForm();

}
