package jp.co.isid.mos.bird.bizreport.urimaintenance.action.impl;

import jp.co.isid.mos.bird.bizreport.urimaintenance.action.UriMaintenanceConfirmAction;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.RegistUriMaintenanceLogic;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 売上修正（確認画面）
 * 
 * 更新日:2012/07/27 xkinu 会計区分追加対応
 */
public class UriMaintenanceConfirmActionImpl implements UriMaintenanceConfirmAction {
   
    /* アクションID */
    public static final String initialize_ACTION_ID     = "BBR008A14";
    public static final String back_ACTION_ID           = "BBR008A10";
    public static final String changeMainTab_ACTION_ID  = "BBR008A11";
    public static final String changeSubTab_ACTION_ID   = "BBR008A12";
    public static final String regist_ACTION_ID         = "BBR008A13";
    
    /**
     * 売上修正DTO
     */
    private UriMaintenanceDto uriMaintenanceDto;
    
    
    /**
     * 売上修正登録ロジック
     */
    private RegistUriMaintenanceLogic registUriMaintenanceLogic;

    
    
    /**
     * 初期表示
     * @return
     */
    public String initialize() {        
        return null;
    }
    
    
    /**
     * 戻る
     * @return
     */
    public String back() {
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;          
        }
        //参照モードであれば検索画面へ遷移する
        String viewId = UriMaintenanceConstants.VIEWID_EDIT;
        if(!getUriMaintenanceDto().isEditMode()) {
            viewId = UriMaintenanceConstants.VIEWID_CONDITION;
        }
        return viewId;
    }
    
    
    /**
     * 登録
     * @return
     */
    public String regist() {
        //集計区分登録処理
        return registUriageSyusei();
    }
    
    /**
     * メインタブ切替
     * @return
     */
    public String changeMainTab() {
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;          
        }
        //表示タブ設定
        getUriMaintenanceDto().setViewIndex(getNextIndex());
        getUriMaintenanceDto().setSubIndex(UriMaintenanceConstants.TAB_SUB_INDEX_NO1);
        
        return null;
    }
    
    
    /**
     * サブタブ切替
     * @return
     */
    public String changeSubTab() {
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;          
        }
        //表示サブタブ設定
        getUriMaintenanceDto().setSubIndex(getSubIndex());
        
        return null;
    }
    /**
     * 集計区分登録処理
     * 
     * @return
     */
    protected String registUriageSyusei() {
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;          
        }
        //参照モードであれば自画面へ遷移する
        if(!getUriMaintenanceDto().isEditMode()) {
            return null;
        }
        //------------------------
        // DB登録ロジック実行
        //------------------------
        getRegistUriMaintenanceLogic().execute(getUriMaintenanceDto());
        
        getUriMaintenanceDto().clearRegistData();
        //
        MakeSessionKey makeSessionKey = new MakeSessionKey();
        //ユーザーオペレーションチェックの判断値としてセッションキーを再生成します。
        String sessionKey = makeSessionKey._makeSessionKey();
        //セッションキーをDTO【Session情報】セッションキーにセットします。
        getUriMaintenanceDto().setSessioniKey(sessionKey);
        //セッションキーをDTO【Session情報】表示セッションキーにセットします。
        getUriMaintenanceDto().setViewSessionKey(sessionKey);

        return UriMaintenanceConstants.VIEWID_CONDITION;
    }
    
    /**
     * 次表示タブインデックス
     */
    private int nextIndex;
    
    public int getNextIndex() {
        return nextIndex;
    }
    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }
    /**
     * サブタブインデックス
     */
    private int subIndex;
    
    public int getSubIndex() {
        return subIndex;
    }
    public void setSubIndex(int subIndex) {
        this.subIndex = subIndex;
    }
    
    
    public UriMaintenanceDto getUriMaintenanceDto() {
        return uriMaintenanceDto;
    }
    public void setUriMaintenanceDto(UriMaintenanceDto uriMaintenanceDto) {
        this.uriMaintenanceDto = uriMaintenanceDto;
    }


    public RegistUriMaintenanceLogic getRegistUriMaintenanceLogic() {
        return registUriMaintenanceLogic;
    }
    public void setRegistUriMaintenanceLogic(
            RegistUriMaintenanceLogic registUriMaintenanceLogic) {
        this.registUriMaintenanceLogic = registUriMaintenanceLogic;
    }

}
