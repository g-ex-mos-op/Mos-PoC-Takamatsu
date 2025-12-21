/*
 * 作成日: 2006/06/07
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.action;



/**
 * 申込状況確認 
 * 確認画面アクションインターフェース
 * 
 * @author xkinu
 */
public interface ConfirmAction {

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