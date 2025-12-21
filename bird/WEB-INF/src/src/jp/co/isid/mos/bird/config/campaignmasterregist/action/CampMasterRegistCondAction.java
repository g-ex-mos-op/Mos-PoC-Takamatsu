package jp.co.isid.mos.bird.config.campaignmasterregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * キャンペーンマスタ登録 条件画面Action Interface
 *
 */
public interface CampMasterRegistCondAction extends CommonAction{

    /**
     * 新規登録
     * @return
     */
    public String doNew();
    
    /**
     * 編集
     * @return 
     */
    public String doEdit();
    
    /**
     * 過去キャンペーン表示
     * @return
     */
    public String doViewKako();
    
    /**
     * 編集可能キャンペーン表示
     * @return
     */
    public String doViewEditable();
}