/*
 * 作成日: 2006/12/08
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.action;

/**
 * 申込状況確認 
 * 条件画面アクションインターフェース
 * 
 * @author xkinu
 */
public interface ConditionAction {

    /**
     * 初期化処理
     * 
     * @return null
     */
    public String initialize();
    /**
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back();
    /**
     * SV検索　アクション
     * 
     * @return
     */
    public String callSvForm();
    /**
     * 検索　アクション
     * @return
     */
    public String search();
}