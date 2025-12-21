package jp.co.isid.mos.bird.entry.mlentry.action.impl;

import jp.co.isid.mos.bird.entry.mlentry.action.MlEntryConfirmAction;
import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;
import jp.co.isid.mos.bird.entry.mlentry.logic.MlEntryUtilLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.SearchOnerLogic;

/**
 * マスターライセンス受験申込　確認画面アクションクラス
 * @author Aspac
 */
public class MlEntryConfirmActionImpl implements MlEntryConfirmAction {


    /* アクションID */
    public static String initialize_ACTION_ID   = "BEN008A21";
    public static String cancel_ACTION_ID       = "BEN008A22";
    public static String regist_ACTION_ID       = "BEN008A23";

    /* DTO */
    // マスターライセンス登録用DTO
    private MlEntryDto mlEntryDto;
    
    /* LOGIC */
    private SearchOnerLogic mlSearchOnerLogic;
    private SearchEntryLogic mlSearchEntryLogic;
    private MlEntryUtilLogic mlEntryUtilLogic;
    

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        
        // エントリー者Noの割り当て
        getMlEntryUtilLogic().addEntryNo(getMlEntryDto().getListEntryStateRegist());
        
        return null;
    }
    
    
    
    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {
        
        getSearchOnerLogic().execute(getMlEntryDto());
        getSearchEntryLogic().execute(getMlEntryDto());
        
        // 新規エントリーレコード生成
        getMlEntryUtilLogic().makeNewEntryRec(getMlEntryDto());
        
    	return MlEntryCommon.VIEW_ID_EDIT;
	}

    /**
     * 終了
     */
    public String terminate() {
        getMlEntryDto().clear();
        return MlEntryCommon.VIEW_ID_SELECT;
    }
    
    

    public MlEntryDto getMlEntryDto() {
        return mlEntryDto;
    }
    public void setMlEntryDto(MlEntryDto mlEntryDto) {
        this.mlEntryDto = mlEntryDto;
    }

    
    public SearchEntryLogic getSearchEntryLogic() {
        return mlSearchEntryLogic;
    }
    public void setSearchEntryLogic(
            SearchEntryLogic mlSearchEntryLogicImpl) {
        this.mlSearchEntryLogic = mlSearchEntryLogicImpl;
    }
    public SearchOnerLogic getSearchOnerLogic() {
        return mlSearchOnerLogic;
    }
    public void setSearchOnerLogic(
            SearchOnerLogic mlSearchOnerLogicImpl) {
        this.mlSearchOnerLogic = mlSearchOnerLogicImpl;
    }
    public MlEntryUtilLogic getMlEntryUtilLogic() {
        return mlEntryUtilLogic;
    }
    public void setMlEntryUtilLogic(
            MlEntryUtilLogic mlEntryUtilLogicImpl) {
        this.mlEntryUtilLogic = mlEntryUtilLogicImpl;
    }
    
}