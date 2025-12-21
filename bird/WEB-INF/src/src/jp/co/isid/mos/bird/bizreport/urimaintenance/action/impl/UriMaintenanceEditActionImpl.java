package jp.co.isid.mos.bird.bizreport.urimaintenance.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UriMainteHeader;
import jp.co.isid.mos.bird.bizreport.urimaintenance.action.UriMaintenanceEditAction;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.util.CheckInputData;

/**
 * 売上修正（編集画面）
 * 更新日:2012/07/27 xkinu 会計区分追加対応
 */
public class UriMaintenanceEditActionImpl implements UriMaintenanceEditAction {
   
    /* アクションID */
    public static final String initialize_ACTION_ID     = "BBR008A04";
    public static final String back_ACTION_ID           = "BBR008A05";
    public static final String changeMainTab_ACTION_ID  = "BBR008A06";
    public static final String changeSubTab_ACTION_ID   = "BBR008A07";
    public static final String calculate_ACTION_ID      = "BBR008A08";
    public static final String confirm_ACTION_ID        = "BBR008A09";
    
    
    /**
     * 売上修正DTO
     */
    private UriMaintenanceDto uriMaintenanceDto;

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
    	//１．売上検索画面ID(BRR008V01)を返す
        return UriMaintenanceConstants.VIEWID_CONDITION;
    }
    
    
    /**
     * 確認
     * @return
     */
    public String confirm() {
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;          
        }
        //集計区分確認処理を行います。
        confirmSyukeiKbn();
        //タブ初期化
        getUriMaintenanceDto().setViewIndex(UriMaintenanceConstants.TAB_INDEX_URIAGEKIN);
        getUriMaintenanceDto().setSubIndex(UriMaintenanceConstants.TAB_SUB_INDEX_NO1);
        
        return UriMaintenanceConstants.VIEWID_CONFIRM;
    }
    
    
    /**
     * 再計算
     * @return
     */
    public String calculate() {       
        //ユーザーオペレーション整合性チェック
        if(UriMaintenanceCommon.isUserOperationError(getUriMaintenanceDto())){
            return UriMaintenanceConstants.VIEW_ID_OPERATION_ERR;          
        }
        //集計区分再計算処理を行います。
        calculateSyukeiKbn();
        
        return null;
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
        //タブ切替時処理
        changeTab();
        //表示タブ設定
        getUriMaintenanceDto().setViewIndex(getNextIndex());        
        //表示サブタブ設定
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
        
        //タブ切替時処理
        changeTab();
        //表示サブタブ設定
        getUriMaintenanceDto().setSubIndex(getSubIndex());
        
        
        return null;
    }
    

    /**
     * タブ切替時処理
     */
    protected void changeTab() {
        List listUri = getUriMaintenanceDto().getListUri();
        int viewIndex = getUriMaintenanceDto().getViewIndex();
        UriMainteHeader header = getUriMaintenanceDto().getHeader();
        //------------------------------
        // 表示タブ入力チェック
        //-----------------------------
        CheckInputData.execute(listUri, viewIndex, header);
        //------------------------------
        // 売上情報リスト生成(計算処理)
        //-----------------------------
        UriMaintenanceCommon.createUriMainteList(listUri);
        
    }
    /**
     * 集計区分確認処理
     *
     */
    protected void confirmSyukeiKbn() {
        int viewIndex = getUriMaintenanceDto().getViewIndex();
        List listUri = getUriMaintenanceDto().getListUri();
        UriMainteHeader header = getUriMaintenanceDto().getHeader();
        
        //------------------------------
        // 表示タブ入力チェック
        //-----------------------------
        CheckInputData.execute(listUri, viewIndex, header);
        
        //------------------------------
        // 売上情報リスト生成(計算処理)
        //-----------------------------
        UriMaintenanceCommon.createUriMainteList(listUri);        
        //--------------------------------
        // 入力チェック(計算結果チェック)
        //--------------------------------
        CheckInputData.execute(listUri,UriMaintenanceConstants.TAB_INDEX_CHK_CALCULATE, header);
    }
    /**
     * 集計区分再計算処理
     *
     */
    protected void calculateSyukeiKbn() {
        List listUri = getUriMaintenanceDto().getListUri();
        int viewIndex = getUriMaintenanceDto().getViewIndex();
        UriMainteHeader header = getUriMaintenanceDto().getHeader();
        
        //------------------------------
        // 表示タブ入力チェック
        //-----------------------------
        CheckInputData.execute(listUri, viewIndex, header);
        
        //------------------------------
        // 売上情報リスト生成(計算処理)
        //-----------------------------
        UriMaintenanceCommon.createUriMainteList(listUri);
    }
   
    public UriMaintenanceDto getUriMaintenanceDto() {
        return uriMaintenanceDto;
    }
    public void setUriMaintenanceDto(UriMaintenanceDto uriMaintenanceDto) {
        this.uriMaintenanceDto = uriMaintenanceDto;
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

}
