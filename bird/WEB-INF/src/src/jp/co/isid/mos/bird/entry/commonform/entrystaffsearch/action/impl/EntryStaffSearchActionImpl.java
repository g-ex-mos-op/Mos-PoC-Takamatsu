package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.action.EntryStaffSearchAction;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.common.EntryStaffSearchConst;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto.EntryStaffSearchConditionDto;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto.EntryStaffSearchDto;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto.EntryStaffSearchResultDto;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity.MstStaff;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic.SearchOnerStaffByMlEntryLogic;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic.SearchOnerStaffLogic;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic.SearchStaffInfoLogic;

/**
 * @author xnkusama
 *
 */
public class EntryStaffSearchActionImpl implements EntryStaffSearchAction {

	// アクションID定義
	public static final String initialize_ACTION_ID = EntryStaffSearchConst.VIEW_ID + "A01";
	public static final String select_ACTION_ID = EntryStaffSearchConst.VIEW_ID + "A02";
    public static final String cancel_ACTION_ID = EntryStaffSearchConst.VIEW_ID + "A04";
	
    /**
	 * スタッフ検索DTO
	 */
    private EntryStaffSearchDto entryStaffSearchDto;

	/**
	 * スタッフ検索条件DTO
	 */
    private EntryStaffSearchConditionDto entryStaffSearchConditionDto;

    /**
     * 検索結果用DTO
     */
    private EntryStaffSearchResultDto entryStaffSearchResultDto;

    /**
     * スタッフ一覧取得ロジック
     */
    private SearchOnerStaffLogic searchOnerStaffLogicImpl;
    /**
     * スタッフ一覧取得ロジック モード１(ML受験申込)対応
     */
    private SearchOnerStaffByMlEntryLogic searchOnerStaffByMlEntryLogicImpl;
    /**
     * 指定スタッフ情報取得ロジック
     */
    private SearchStaffInfoLogic searchStaffInfoLogic;
    
    /**
     * 店選択インデックス
     */
    private String selectStaffId;

    /**
     * 選択スタッフIDを取得します。
     * 
     * @return 選択スタッフID
     */
	public String getSelectStaffId() {
		return selectStaffId;
	}

    /**
     * 選択スタッフIDを設定します。
     * 
     * @param selectStaffId 選択スタッフID
     */
	public void setSelectStaffId(String selectStaffId) {
		this.selectStaffId= selectStaffId;
	}

	/**
	 * 初期処理
	 * 
	 * @return 画面遷移情報
	 */
	public String initialize() {
        if (getEntryStaffSearchDto().isInitialFlag()) {
            // ウインドウID採番
            getEntryStaffSearchConditionDto().updateWindowid();
            
            // 検索結果初期化
            getEntryStaffSearchConditionDto().clear();
            
            // 呼出元画面情報保持
            getEntryStaffSearchConditionDto().setParentViewWindowId(getEntryStaffSearchDto().getWindowId());
            getEntryStaffSearchConditionDto().setNavigationCase(getEntryStaffSearchDto().getNavigationCase());
            getEntryStaffSearchConditionDto().setCompanyCd(getEntryStaffSearchDto().getCompanyCd());
            getEntryStaffSearchConditionDto().setOnerCd(getEntryStaffSearchDto().getOnerCd());
            getEntryStaffSearchConditionDto().setMode(getEntryStaffSearchDto().getMode());
            getEntryStaffSearchConditionDto().setEntryYear(getEntryStaffSearchDto().getEntryYear());
            getEntryStaffSearchConditionDto().setEntryKai(getEntryStaffSearchDto().getEntryKai());
            
            
            List listStaff = null;
            
            if(getEntryStaffSearchConditionDto().getMode() == EntryStaffSearchConst.MODE_NORMAL){
                // スタッフ一覧取得 (通常)
                listStaff = getSearchOnerStaffLogicImpl()
                                    .execute(getEntryStaffSearchConditionDto().getCompanyCd(),
                                             getEntryStaffSearchConditionDto().getOnerCd());
            }
            else if(getEntryStaffSearchConditionDto().getMode() == EntryStaffSearchConst.MODE_MLENTRY){
                // スタッフ一覧取得 (ML受験申込)
                listStaff = getSearchOnerStaffByMlEntryLogicImpl()
                                    .execute(getEntryStaffSearchConditionDto().getCompanyCd(),
                                             getEntryStaffSearchConditionDto().getOnerCd(),
                                             getEntryStaffSearchConditionDto().getEntryYear(),
                                             getEntryStaffSearchConditionDto().getEntryKai());
            }
            
            getEntryStaffSearchResultDto().setListStaff(listStaff);
            
  			// 初期化フラグ更新
            getEntryStaffSearchDto().setInitialFlag(false);
        }
        return null;
	}

	/**
	 * 決定
	 * 
	 * @return 画面遷移情報
	 */
	public String select() {
        // 選択されたスタッフIDを取得
        String staffId = getSelectStaffId();
        // スタッフ情報取得
        MstStaff mstStaff = getSearchStaffInfoLogic().execute(staffId);
        
        // リターン情報格納
        getEntryStaffSearchDto().setReturnKind(EntryStaffSearchDto.RETURNKIND_SELECT);
        getEntryStaffSearchDto().setWindowId(getEntryStaffSearchConditionDto().getParentViewWindowId());
        getEntryStaffSearchDto().setNavigationCase(getEntryStaffSearchConditionDto().getNavigationCase());
        getEntryStaffSearchDto().setMstStaff(mstStaff);
        
	    return getEntryStaffSearchConditionDto().getNavigationCase();
	}

	/**
	 * 戻る
	 * 
	 * @return 画面遷移情報
	 */
	public String cancel() {
        // リターン情報格納
        getEntryStaffSearchDto().setReturnKind(EntryStaffSearchDto.RETURNKIND_CANCEL);
        getEntryStaffSearchDto().setWindowId(getEntryStaffSearchConditionDto().getParentViewWindowId());
        getEntryStaffSearchDto().setNavigationCase(getEntryStaffSearchConditionDto().getNavigationCase());
        getEntryStaffSearchDto().setMode(EntryStaffSearchConst.MODE_NORMAL);
        
        return getEntryStaffSearchConditionDto().getNavigationCase();
	}

    public SearchOnerStaffLogic getSearchOnerStaffLogicImpl() {
        return searchOnerStaffLogicImpl;
    }

    public void setSearchOnerStaffLogicImpl(SearchOnerStaffLogic searchOnerStaffLogicImpl) {
        this.searchOnerStaffLogicImpl = searchOnerStaffLogicImpl;
    }
    
    public SearchOnerStaffByMlEntryLogic getSearchOnerStaffByMlEntryLogicImpl() {
        return searchOnerStaffByMlEntryLogicImpl;
    }
    
    public void setSearchOnerStaffByMlEntryLogicImpl(
            SearchOnerStaffByMlEntryLogic searchOnerStaffByMlEntryLogicImpl) {
        this.searchOnerStaffByMlEntryLogicImpl = searchOnerStaffByMlEntryLogicImpl;
    }

    public EntryStaffSearchConditionDto getEntryStaffSearchConditionDto() {
        return entryStaffSearchConditionDto;
    }

    public void setEntryStaffSearchConditionDto(
            EntryStaffSearchConditionDto entryStaffSearchConditionDto) {
        this.entryStaffSearchConditionDto = entryStaffSearchConditionDto;
    }

    public EntryStaffSearchDto getEntryStaffSearchDto() {
        return entryStaffSearchDto;
    }

    public void setEntryStaffSearchDto(EntryStaffSearchDto entryStaffSearchDto) {
        this.entryStaffSearchDto = entryStaffSearchDto;
    }

    public EntryStaffSearchResultDto getEntryStaffSearchResultDto() {
        return entryStaffSearchResultDto;
    }

    public void setEntryStaffSearchResultDto(
            EntryStaffSearchResultDto entryStaffSearchResultDto) {
        this.entryStaffSearchResultDto = entryStaffSearchResultDto;
    }

    public SearchStaffInfoLogic getSearchStaffInfoLogic() {
        return searchStaffInfoLogic;
    }

    public void setSearchStaffInfoLogic(SearchStaffInfoLogic searchStaffInfoLogic) {
        this.searchStaffInfoLogic = searchStaffInfoLogic;
    }
}