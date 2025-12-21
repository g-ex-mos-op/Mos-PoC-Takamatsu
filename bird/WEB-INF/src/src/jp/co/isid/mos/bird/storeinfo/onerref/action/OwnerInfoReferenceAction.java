/*
 * 作成日: 2006/3/7
 */
package jp.co.isid.mos.bird.storeinfo.onerref.action;

/**
 * オーナ情報照会アクションインターフェース
 * @author itamoto
 */
public interface OwnerInfoReferenceAction {

    /**
     * 条件画面(初期処理)
     */
    public String initialize();

    /**
     * オーナ選択起動
     */
    public String selectOnerSearch();
    
    /**
     * 検索
     */
    public String search();
    
    /**
     * 関連リンク一覧の選択
     */
    public String selectLink();
    
    /**
     * 個店ポータルへリンク
     * @return
     */
    public String callMiseInfo();
}