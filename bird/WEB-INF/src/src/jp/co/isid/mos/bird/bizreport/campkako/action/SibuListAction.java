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
public interface SibuListAction {
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
     * 支部リンク処理
     *
     */
    public String linkUp();
    /**
     * 年度変更処理
     */
    public String changeNendo();
    
    /**
     * タブ切替
     */
    public String changeTab();
}
