/*
 * 作成日: 2006/2/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.action.impl;

import java.util.List;
import java.util.ArrayList;

import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.action.LongserviceOfferConfirmAction;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferConstants;
import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferEntry;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferMst;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.SearchOfferLogic;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.SearchOfferMiseLogic;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.UpdateOfferLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 永年勤続マスタ登録　確認画面アクションクラス
 * @author itamoto
 */
public class LongserviceOfferConfirmActionImpl implements LongserviceOfferConfirmAction {
 
	/** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN018A11";
    public static final String cancel_ACTION_ID = "BEN018A12";
    public static final String end_ACTION_ID = "BEN018A13";
    
    /** 永年勤続マスタ登録情報DTO */
    private LongserviceOfferDto longserviceOfferDto;
    
    /** 永年勤続マスタ情報更新ロジック */
    private UpdateOfferLogic updateUserLogic;
    
    /** 永年勤続申請情報検索ロジック */
    private SearchOfferLogic searchOfferLogic;
    
    /** 店舗情報取得ロジック */
    private SearchOfferMiseLogic searchOfferMiseLogic;
    
    /** 各種イベント申込 */
    private EventListDto eventListDto;

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {	

		List entryList = getLongserviceOfferDto().getUIOfferEntryList();
		UIOfferMst uiOfferMst = getLongserviceOfferDto().getUIOfferMst();
		String miseName = "";
		List miseList = new ArrayList();
		
		try{
	    	// 対象店舗リスト
	    	miseList = getSearchOfferMiseLogic().execute(
	    			getLongserviceOfferDto().getCompanyCd(),
	    			getLongserviceOfferDto().getOnerCd(),
	    			getLongserviceOfferDto().getSysDate());
	    	// 店舗リスト設定
	    	getLongserviceOfferDto().setUIOfferMiseList(miseList);
    	
		}catch(NotExistException e){
			//throw e;
			return null;
		}
    	
        for(int i = 0;i < entryList.size(); i++){
            
        	UIOfferEntry uIOfferEntry = (UIOfferEntry)entryList.get(i);
        	
    		// 店コードから店舗名を取得する
        	miseName = getSearchOfferMiseLogic().getMiseName(miseList,uIOfferEntry.getMiseCd());
        	uIOfferEntry.setMiseName(miseName);
        }
                
    	// 削除対象の場合
    	if(getLongserviceOfferDto().getEditMode() == LongserviceOfferConstants.EDIT_MODE_DELETE){
    		// デフォルトデータを転記する
    		uiOfferMst.setName(uiOfferMst.getDefName());
        	uiOfferMst.setSoufuName(uiOfferMst.getDefSoufuName());
        	uiOfferMst.setTel(uiOfferMst.getDefTel());
        	uiOfferMst.setJobType(uiOfferMst.getDefJobType());
    	}
    	
    	// 永年勤続申請者情報のカウント数取得
	    int entCount = getSearchOfferLogic().getEntryCount(getLongserviceOfferDto());
	    getLongserviceOfferDto().setEntryCount(entCount);
        		
        return null;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {

    	// エディットモードを設定
    	getLongserviceOfferDto().setEditMode(LongserviceOfferConstants.EDIT_MODE_RETURN);
    	
        if (!getLongserviceOfferDto().isEditFlg()) {
            return LongserviceOfferConstants.VIEW_ID_EVENTLIST;
        }
        else {
            return LongserviceOfferConstants.VIEW_ID_EDIT;
        }
	}
    
    /**
     * 終了
     * @return 画面遷移情報
     */
    public String end() {
    	
		getEventListDto().setReturnKind(EventListDto.RETURNKIND_END);
		getEventListDto().setWindowId(getLongserviceOfferDto().getWindowId());
    	getLongserviceOfferDto().clear();

    	return LongserviceOfferConstants.VIEW_ID_EVENTLIST;
	}
    
	public LongserviceOfferDto getLongserviceOfferDto() {
		return longserviceOfferDto;
	}
	public void setLongserviceOfferDto(LongserviceOfferDto longserviceOfferDto) {
		this.longserviceOfferDto = longserviceOfferDto;
	}

	public UpdateOfferLogic getUpdateUserLogic() {
		return updateUserLogic;
	}

	public void setUpdateUserLogic(UpdateOfferLogic updateUserLogic) {
		this.updateUserLogic = updateUserLogic;
	}

	public SearchOfferMiseLogic getSearchOfferMiseLogic() {
		return searchOfferMiseLogic;
	}

	public void setSearchOfferMiseLogic(SearchOfferMiseLogic searchOfferMiseLogic) {
		this.searchOfferMiseLogic = searchOfferMiseLogic;
	}

	public EventListDto getEventListDto() {
		return eventListDto;
	}

	public void setEventListDto(EventListDto eventListDto) {
		this.eventListDto = eventListDto;
	}

	public SearchOfferLogic getSearchOfferLogic() {
		return searchOfferLogic;
	}

	public void setSearchOfferLogic(SearchOfferLogic searchOfferLogic) {
		this.searchOfferLogic = searchOfferLogic;
	}
}

