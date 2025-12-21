/*
 * 作成日: 2006/07/05
 */
package jp.co.isid.mos.bird.office.bbsregist.action;

/**
 * 掲示板登録 参照画面アクション インターフェイス
 * @author xytamura
 */
public interface KeijibanReferenceAction {

    /**
     * 初期アクション
     * @return
     */
    public String initialize();

    /**
     * 取消アクション
     * @return
     */
    public String cancel();

    /**
     * 修正アクション
     * @return String
     */
    public String regist();


    /**
     * 削除アクション
     * @return
     */
    public String delete();

}
