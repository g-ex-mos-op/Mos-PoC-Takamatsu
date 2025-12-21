/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action;

/**
 * テナント入金明細PDF登録
 * 編集画面アクションインターフェース
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public interface EditAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 戻る処理
     */
    public String back();
    /**
     * 店舗選択フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callMiseForm();
    /**
     * 確認処理
     */
    public String auth();
    /**
     * 決定(PDFアップロード)処理
     */
    public String upload();
    /**
     * 削除(アップロードPDF削除)処理
     */
    public String removePdf();

}
