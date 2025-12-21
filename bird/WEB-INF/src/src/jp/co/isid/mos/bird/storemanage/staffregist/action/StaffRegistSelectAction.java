/*
 * 作成日: 2006/04/11
 */
package jp.co.isid.mos.bird.storemanage.staffregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * @author Lee
 */
public interface StaffRegistSelectAction extends CommonAction {

    /**
     * 検索
     * @return
     */
    public String search();

    /**
     * 新規編集
     * @return
     */
	public String insert();

    /**
     * 編集
     * @return
     */
	public String edit();

    /**
     * オーナー選択呼出
     * @return
     */
    public String onerSearch();
}
