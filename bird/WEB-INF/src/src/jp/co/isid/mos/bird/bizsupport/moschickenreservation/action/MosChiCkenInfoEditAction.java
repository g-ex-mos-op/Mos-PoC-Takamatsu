package jp.co.isid.mos.bird.bizsupport.moschickenreservation.action;

/**
 * モスチキン予約
 * 編集画面用アクションインターフェース
 * @author inazawa
 * 2006/09/19
 */
public interface MosChiCkenInfoEditAction {
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize();
    /**
     * 修正／取り消し
     * @return
     */
    public String revExec();
    /**
     * 新規予約
     */
    public String newReserved();
    /**
     * 戻る
     */
    public String back();
    /**
     * 登録・終了
     */
    public String updateExit();
    /**
     * 登録して次の新規予約
     */
    public String updateNext();
    /**
     * 予約キャンセル／キャンセル取消し
     */
    public String notReserve();
}
