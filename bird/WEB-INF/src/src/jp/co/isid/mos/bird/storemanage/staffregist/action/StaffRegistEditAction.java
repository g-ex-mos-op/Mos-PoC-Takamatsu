/*
 * 作成日: 2006/04/07
 */
package jp.co.isid.mos.bird.storemanage.staffregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * 個店情報メンテナンス画面アクション インターフェイス
 * @author xylee
 */
public interface StaffRegistEditAction extends CommonAction {

    /**
     * 登録
     * @return
     */
    public String regist();

    /**
     * 戻る
     * @return
     */
    public String back();
}
