/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.action;

/**
 * 支部一覧用アクションインターフェース
 * 
 * 2008/10/15 xkinu タブ構成(対象日・期間合計タブ)の廃止
 * @author xkinu
 *
 */
public interface MiseListAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 実行or再検索
     * @return
     */
    public String search();
    /**
     * 対象会社変更処理
     *
     */
    public String changeCompany();
    /**
     * 対象キャンペーン変更処理
     *
     */
    public String changeCamp();
    /**
     * 店舗リンク処理
     *
     */
    public String linkUp();
}
