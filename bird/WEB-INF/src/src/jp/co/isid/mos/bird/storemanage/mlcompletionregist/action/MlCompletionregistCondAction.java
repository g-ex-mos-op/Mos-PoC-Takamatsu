/*
 * 作成日: 2006/04/10
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.util.MlCompletionregistUtil;

/**
 * マスタライセンス研修修了登録
 * 条件画面アクション インターフェイス
 * 
 * @author xkinu
 */
public interface MlCompletionregistCondAction extends CommonAction {

    /* アクションID:BSM006A0 */
    public static final String ACTIONID  = MlCompletionregistUtil.SCREEN_ID+"A0";
    /**
     * 実行
     * @return
     */
    public String execute();
    /**
     * 対象会社変更処理
     *
     */
    public String changeCompany();
    /**
     * 対象会社変更処理
     *
     */
    public String allClear();
}
