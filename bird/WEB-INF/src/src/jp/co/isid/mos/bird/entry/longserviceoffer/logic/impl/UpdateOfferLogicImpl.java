/*
 * 作成日: 2006/12/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.dao.UIOfferEntryDao;
import jp.co.isid.mos.bird.entry.longserviceoffer.dao.UIOfferMstDao;
import jp.co.isid.mos.bird.entry.longserviceoffer.dao.UIOfferEntryStateDao;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferEntry;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferMst;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferEntryState;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.UpdateOfferLogic;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferConstants;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * エントリーマスタ管理の更新ロジック
 * @author narita
 */
public class UpdateOfferLogicImpl implements UpdateOfferLogic {

    /** ロジックID 定義 */
	public static final String LOGIC_ID = "BEN018L04";
    
    /**
     * エントリー日付管理（UIOfferEntryDao）
     */
    private UIOfferEntryDao uIOfferEntryDao;

    /**
     * エントリーマスタ管理（UIOfferMstDao）
     */
    private UIOfferMstDao uIOfferMstDao;
    
    /**
     * エントリーマスタ管理（UIOfferEntryStateDao）
     */
    private UIOfferEntryStateDao uIOfferEntryStateDao;

    /**
     * エントリーマスタ管理・エントリー日付管理の更新を行う
     * @param LongserviceOfferDto     
     */
    public int execute(LongserviceOfferDto dto) {

        int result = 9;
        int intSeqNo = 0;
        String maxSeqNo = "";
        
        NumericFormatter numFormatter = new NumericFormatter();
        
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // BIRDユーザー情報取得
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        // タイムスタンプ取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        // 永年勤続責任者情報
        UIOfferMst uiOfferMst = dto.getUIOfferMst();
        // エントリー状況
        UIOfferEntryState uiOfferEntryState = new UIOfferEntryState();
    	
        // 責任者情報が未登録の場合の処理
    	if(dto.getMstCount() == LongserviceOfferConstants.TBL_MODE_OFF){
    		
    		// 責任者情報と参加者情報が、共に入力されていること
    		if( !uiOfferMst.isCleaFlg() && !dto.isEntryCleaFlg() ){
	            // 責任者情報エンティティに更新情報をセット
	            this.setUpdateData(birdUserInfo,currentTimestamp,uiOfferMst);
	            // エントリー状況エンティティに更新情報をセット
	            this.setUpdateData(uiOfferMst,birdUserInfo,currentTimestamp,uiOfferEntryState);
	            
	           	// 永年勤続責任者情報の登録
	           	result = getUIOfferMstDao().insertOfferMst(uiOfferMst);
	           	if(result == 0) {
	               	// 登録に失敗
	           		throw new CannotExecuteWithReasonException(
	                   		LongserviceOfferConstants.MSG_EXCLUSION_CHK,
	                   		LongserviceOfferConstants.MSG_UPDATE);
	            }
	           	// エントリー状況の登録
	           	result = getUIOfferEntryStateDao().insertEntryState(uiOfferEntryState);
	           	if(result == 0) {
	               	// 登録に失敗
	           		throw new CannotExecuteWithReasonException(
	                   		LongserviceOfferConstants.MSG_EXCLUSION_CHK,
	                   		LongserviceOfferConstants.MSG_UPDATE);
	            }
    		}else{
    			// 申請者が全てクリアの場合は、削除処理を行わないが EDIT_MODE_DELETE とする。
        		if(dto.isEntryCleaFlg()){
        			return LongserviceOfferConstants.EDIT_MODE_DELETE;
        		}
    		}
        }else{
            // 責任者情報が登録済みの場合の処理
        	
        	// 削除処理の場合　申請責任者：入力、申請者：全件クリア
        	if( (!uiOfferMst.isCleaFlg()) && (dto.isEntryCleaFlg())){
            	
    	        // 責任者情報の削除
               	result = getUIOfferMstDao().deleteOfferMst(
							                   		dto.getEntryCd(),
							                   		dto.getEntryYear(),
							                   		dto.getEntryKai(),
							                   		dto.getCompanyCd(),
							                   		dto.getOnerCd(),
							                   		dto.getAtesakiKbn());
               	// 申請者情報の削除
    	        result = getUIOfferEntryDao().deleteOfferEntry(
							    	           		dto.getEntryCd(),
							    	           		dto.getEntryYear(),
							    	           		dto.getEntryKai(),
							                   		dto.getCompanyCd(),
							                   		dto.getOnerCd(),
							                   		"00");
    	        // 申請者ステータス情報の削除
    	        result = getUIOfferEntryStateDao().deleteEntryState(
							    	           		dto.getEntryCd(),
							    	           		dto.getEntryYear(),
							    	           		dto.getEntryKai(),
							                   		dto.getCompanyCd(),
							                   		dto.getOnerCd());
							    	        
    	        return LongserviceOfferConstants.EDIT_MODE_DELETE;
    	        
            }else{
        	
                // 責任者情報エンティティに更新情報をセット
                uiOfferMst.setLastUser(birdUserInfo.getUserID());
                uiOfferMst.setLastPgm(LongserviceOfferConstants.SCREEN_ID);
                
	           	// 永年勤続責任者情報の更新
	           	result = getUIOfferMstDao().updateOfferMst(uiOfferMst);
	           	if(result == 0) {
	               	// 登録に失敗
	           		throw new CannotExecuteWithReasonException(
	                   		LongserviceOfferConstants.MSG_EXCLUSION_CHK,
	                   		LongserviceOfferConstants.MSG_UPDATE);
	            }
            }
        }
        
        // 永年勤続申請者情報リストを取得
        List infoList = new ArrayList();
        infoList = dto.getUIOfferEntryList();
                
        for(int i = 0;i < infoList.size(); i++){
                
        	// 申請者情報エンティティを取得
        	UIOfferEntry uIOfferEntry = (UIOfferEntry)infoList.get(i);
        	        	    	        
    	    // クリアフラグが立っていないので更新処理を行う
            if(!uIOfferEntry.isCleaFlg()){
            	
                // 申請者情報エンティティに更新情報をセット
            	this.setUpdateData(birdUserInfo,currentTimestamp,uIOfferEntry);
            	
            	// ソート番号のカウントアップ及び文字タイプ（#0）への変換
            	intSeqNo++;
            	maxSeqNo = numFormatter.format(String.valueOf(intSeqNo),
            			LongserviceOfferConstants.SEQ_FORMAT);
            	uIOfferEntry.setSeqNo(maxSeqNo);
            	
            	// 登録チェックのためにカウントを検索
            	int entCount = getUIOfferEntryDao().getOfferEntrySeqNo(
    							        			uIOfferEntry.getEntryCd(),
    								        		uIOfferEntry.getEntryYear(),
    								        		uIOfferEntry.getEntryKai(),
    								        		uIOfferEntry.getCompanyCd(),
    								        		uIOfferEntry.getOnerCd(),
    								        		uIOfferEntry.getSeqNo());
            	
            	// 対象データのソート番号が未登録の場合
            	if(entCount <= LongserviceOfferConstants.TBL_MODE_OFF){

	                // 永年勤続申請者情報の追加登録
	                result = getUIOfferEntryDao().insertOfferEntry(uIOfferEntry);
	                if(result == 0) {
		               	// 登録に失敗
		           		throw new CannotExecuteWithReasonException(
		                   		LongserviceOfferConstants.MSG_EXCLUSION_CHK,
		                   		LongserviceOfferConstants.MSG_UPDATE);
	                }
            		
            	}else{
            		// 対象データのソート番号が登録済みの場合
            		
	               	// 永年勤続申請者情報の更新
	               	result = getUIOfferEntryDao().updateOfferEntry(uIOfferEntry);
	               	if(result == 0) {
		               	// 登録に失敗
		           		throw new CannotExecuteWithReasonException(
		                   		LongserviceOfferConstants.MSG_EXCLUSION_CHK,
		                   		LongserviceOfferConstants.MSG_UPDATE);
	               	}
            	}
        	}   	        
        }

        // 最大ソート番号以上のデータを一括削除する
    	maxSeqNo = numFormatter.format(String.valueOf(intSeqNo),
    			LongserviceOfferConstants.SEQ_FORMAT);
	    result = getUIOfferEntryDao().deleteOfferEntry(
								       	dto.getEntryCd(),
								   	   	dto.getEntryYear(),
								   	   	dto.getEntryKai(),
								   	   	dto.getCompanyCd(),
								   	   	dto.getOnerCd(),
								   	   	maxSeqNo);
	        
	    return LongserviceOfferConstants.EDIT_MODE_UPDATE;
    }
    
    /**
     * 永年勤続責任者情報
     * @param BirdUserInfo    
     * @param Timestamp    
     * @param UIOfferMst 永年勤続責任者情報エンティティ
     * @return UIOfferMst 永年勤続責任者情報エンティティ
     */
    public UIOfferMst setUpdateData(BirdUserInfo birdUserInfo,Timestamp currentTimestamp,UIOfferMst uiOfferMst){
        uiOfferMst.setFirstUser(birdUserInfo.getUserID());
        uiOfferMst.setFirstPgm(LongserviceOfferConstants.SCREEN_ID);
        uiOfferMst.setFirstTmsp(currentTimestamp);
        uiOfferMst.setLastUser(birdUserInfo.getUserID());
        uiOfferMst.setLastPgm(LongserviceOfferConstants.SCREEN_ID);
        uiOfferMst.setLastTmsp(currentTimestamp);
    	return uiOfferMst;
    }
    
    /**
     * エントリー状況
     * @param UIOfferMst
     * @param BirdUserInfo    
     * @param Timestamp    
     * @param UIOfferEntryState エントリー状況エンティティ
     * @return UIOfferEntryState エントリー状況エンティティ
     */
    public UIOfferEntryState setUpdateData(UIOfferMst uiOfferMst,BirdUserInfo birdUserInfo,Timestamp currentTimestamp,UIOfferEntryState uiOfferEntryState){
    	uiOfferEntryState.setEntryCd(uiOfferMst.getEntryCd());
    	uiOfferEntryState.setEntryYear(uiOfferMst.getEntryYear());
    	uiOfferEntryState.setEntryKai(uiOfferMst.getEntryKai());
    	uiOfferEntryState.setCompanyCd(uiOfferMst.getCompanyCd());
    	uiOfferEntryState.setOnerCd(uiOfferMst.getOnerCd());
    	uiOfferEntryState.setEntryFlg(LongserviceOfferConstants.EMPTY);
    	uiOfferEntryState.setFirstUser(birdUserInfo.getUserID());
        uiOfferEntryState.setFirstPgm(LongserviceOfferConstants.SCREEN_ID);
        uiOfferEntryState.setFirstTmsp(currentTimestamp);
        uiOfferEntryState.setLastUser(birdUserInfo.getUserID());
        uiOfferEntryState.setLastPgm(LongserviceOfferConstants.SCREEN_ID);
        uiOfferEntryState.setLastTmsp(currentTimestamp);
    	return uiOfferEntryState;
    }
    
    /**
     * 申請者情報
     * @param BirdUserInfo    
     * @param Timestamp    
     * @param UIOfferEntry 申請者情報エンティティ
     * @return UIOfferEntry 申請者情報エンティティ
     */
    public UIOfferEntry setUpdateData(BirdUserInfo birdUserInfo,Timestamp currentTimestamp,UIOfferEntry uIOfferEntry){
    	uIOfferEntry.setFirstUser(birdUserInfo.getUserID());
    	uIOfferEntry.setFirstPgm(LongserviceOfferConstants.SCREEN_ID);
    	uIOfferEntry.setFirstTmsp(currentTimestamp);
    	uIOfferEntry.setLastUser(birdUserInfo.getUserID());
    	uIOfferEntry.setLastPgm(LongserviceOfferConstants.SCREEN_ID);
    	uIOfferEntry.setLastTmsp(currentTimestamp);
    	return uIOfferEntry;
    }
    
	public UIOfferEntryDao getUIOfferEntryDao() {
		return uIOfferEntryDao;
	}

	public void setUIOfferEntryDao(UIOfferEntryDao offerEntryDao) {
		uIOfferEntryDao = offerEntryDao;
	}

	public UIOfferMstDao getUIOfferMstDao() {
		return uIOfferMstDao;
	}

	public void setUIOfferMstDao(UIOfferMstDao offerMstDao) {
		uIOfferMstDao = offerMstDao;
	}

	public UIOfferEntryStateDao getUIOfferEntryStateDao() {
		return uIOfferEntryStateDao;
	}

	public void setUIOfferEntryStateDao(UIOfferEntryStateDao offerEntryStateDao) {
		uIOfferEntryStateDao = offerEntryStateDao;
	}
}
