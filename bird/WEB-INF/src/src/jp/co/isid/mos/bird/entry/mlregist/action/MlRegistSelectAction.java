/*
 * 作成日: 2006/06/08
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * マスタライセンスマスタ登録条件アクション
 * 
 * @author xyuchida
 */
public interface MlRegistSelectAction extends CommonAction {

    /**
     * 新規
     * @return 画面遷移情報
     */
    public String insert();

    /**
     * 編集
     * @return 画面遷移情報
     */
    public String update();

    /**
     * 削除
     * @return 画面遷移情報
     */
    public String delete();

    /**
     * ページ切替
     * @return 画面遷移情報
     */
    public String changePage();
}
