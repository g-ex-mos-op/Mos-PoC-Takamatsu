/*
 * 作成日: 2006/06/08
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * マスタライセンスマスタ登録編集アクション
 * 
 * @author xyuchida
 */
public interface MlRegistEditAction extends CommonAction {

    /**
     * 確認
     * @return 画面遷移情報
     */
    public String confirm();

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back();
}
