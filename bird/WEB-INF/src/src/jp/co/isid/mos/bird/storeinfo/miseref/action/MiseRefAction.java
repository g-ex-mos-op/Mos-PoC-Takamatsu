/*
 * 作成日: 2006/03/08
 */
package jp.co.isid.mos.bird.storeinfo.miseref.action;

/**
 * 個店情報メンテナンス画面アクション インターフェイス
 * @author xnkusama
 */
public interface MiseRefAction {

    /**
     * 実行
     * @return
     */
    public String execute();

    /**
     * 店検索フォーム
     * @return
     */
    public String callMiseForm();
    
    /**
     * オーナー照会へ遷移
     * @return
     */
    public String callOnerInfo();
    
    /**
     * タブ切替
     * @return
     */
    public String changeTab();
    
    /**
     * 関連リンクアクション
     * @author xnkusama
     */
    public String startOutLink();
}
