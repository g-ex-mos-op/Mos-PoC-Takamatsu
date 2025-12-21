/*
 * 作成日: 2006/07/19
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * マスターライセンス結果登録 能力チェック対象者選択画面アクション
 * @author kusama
 */
public interface MlResultRegistAbilityCheckListAction extends CommonAction {

    /**
     * 戻る
     */
    public String back();
    
    /**
     * 実行
     */
    public String regist();
    
}