/*
 * 作成日: 2006/08/03
 */
package jp.co.isid.mos.bird.bill.detailbilldownload.action;

/**
 * ご請求明細ダウンロード条件設定アクション
 * 
 * @author mwatanabe
 */
public interface DetailBillDownloadListAction {

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 対象検索(オーナー・店舗・SV)
     * 
     * @return 画面遷移情報
     */
    public String callSearchForm();
    
    /**
     * 実行ボタン(1)が押された時の処理
     * 入力されたオーナーコードより請求先(売掛先)一覧のリストを取得する
     * 
     * @return 画面遷移情報
     */
    public String execution01();

    /**
     * 実行ボタン(2)が押された時の処理
     * 選択された「売掛先コード」を元に請求書一覧を取得する。
     * 
     * @return 画面遷移情報
     */
    public String execution02();

}
