/*
 * 作成日: 2006/06/08
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * マスタライセンスマスタ登録確認アクション
 * 
 * @author xyuchida
 */
public interface MlRegistConfirmAction extends CommonAction {

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
