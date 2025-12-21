/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * 上下限設定編集画面アクション
 * 
 * @author xyuchida
 */
public interface PlLimitRegistEditAction extends CommonAction {

    /**
     * 確認
     * 
     * @return 画面遷移情報
     */
    public String confirm();

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back();
}
