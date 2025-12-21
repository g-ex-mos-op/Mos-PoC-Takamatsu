/*
 * 作成日: 2006/04/03
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.action;

/**
 * P/L照会画面 アクション インターフェイス
 * @author xnkusama
 */
public interface PlInfoViewAction {

    /**
     * 検索
     * @return
     */
    public String search();

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
}
