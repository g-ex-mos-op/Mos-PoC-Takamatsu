/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.action;

/**
 * [機能設定]【売上速報前月売上確定登録】確認画面
 * アクションインターフェース
 * 
 * 作成日:2008/08/15
 * @author xkinu
 *
 */
public interface ConfirmAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 登録・終了
     * @return
     */
    public String auth();
    /**
     * 戻る
     * @return
     */
    public String back();
}
