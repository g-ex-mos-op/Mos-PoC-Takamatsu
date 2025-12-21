/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campkako.action;

/**
 * 支部一覧用アクションインターフェース
 * 
 * @author xnkusama
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
    /**
     * タブ変更処理
     *
     */
    public String changeTab();
    /**
     * 年度変更処理
     */
    public String changeNendo();
    
}
