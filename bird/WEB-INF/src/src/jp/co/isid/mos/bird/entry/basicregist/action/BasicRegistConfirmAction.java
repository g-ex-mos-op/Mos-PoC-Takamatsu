/*
 * 作成日: 2006/05/30
 *
 */
package jp.co.isid.mos.bird.entry.basicregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * ベーシック研修マスタ登録確認アクション
 * 
 * @author xyuchida
 */
public interface BasicRegistConfirmAction extends CommonAction {

    /**
     * 登録
     * @return 画面遷移情報
     */
    public String regist();

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back();
}
