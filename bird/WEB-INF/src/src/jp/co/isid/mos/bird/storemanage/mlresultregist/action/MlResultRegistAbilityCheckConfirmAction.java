/*
 * 作成日: 2006/07/28
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * マスターライセンス結果登録 能力チェック編集画面アクション
 * @author xyuchida
 */
public interface MlResultRegistAbilityCheckConfirmAction extends CommonAction {

    /**
     * 登録
     * @return 画面遷移情報
     */
    public String regist();

    /**
     * 次の受験者を登録
     * @return 画面遷移情報
     */
    public String next();

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back();
}
