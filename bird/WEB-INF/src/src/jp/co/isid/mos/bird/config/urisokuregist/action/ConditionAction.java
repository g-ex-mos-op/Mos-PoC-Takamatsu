/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.action;

/**
 * 機能設定]【売上速報設定】初期画面
 * アクションインターフェース
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface ConditionAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 実行
     * @return
     */
    public String edit();
}
