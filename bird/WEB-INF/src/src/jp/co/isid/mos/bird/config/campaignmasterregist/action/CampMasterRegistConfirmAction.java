package jp.co.isid.mos.bird.config.campaignmasterregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * キャンペーンマスタ登録 確認画面Action Interface
 *
 */
public interface CampMasterRegistConfirmAction extends CommonAction{

    /**
     * 戻る
     * @return
     */
    public String doBack();
    
    /**
     * 登録・終了
     * @return 
     */
    public String doRegist();
    
}