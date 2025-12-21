/*
 * 作成日: 2006/07/24
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * マスターライセンス結果登録 面接編集画面アクション
 * @author kusama
 */
public interface MlResultRegistInterviewConfirmAction extends CommonAction {

    /**
     * 戻る
     */
    public String back();
    
    /**
     * 実行
     */
    public String regist();
    
}