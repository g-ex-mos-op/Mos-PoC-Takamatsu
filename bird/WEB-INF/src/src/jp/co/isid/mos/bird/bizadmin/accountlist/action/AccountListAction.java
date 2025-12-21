package jp.co.isid.mos.bird.bizadmin.accountlist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * お申込内容一覧 アクション
 * @author xnkusama
 *
 */
public interface AccountListAction extends CommonAction {

    /**
     * アカウント情報変更リンク
     */
    public String goAccountEdit();
    
}
