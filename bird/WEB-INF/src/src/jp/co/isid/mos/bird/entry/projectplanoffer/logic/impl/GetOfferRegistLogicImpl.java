/*
 * 作成日: 2006/11/29
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.logic.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferDutyDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferEntryStateDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferIninDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferJoinDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferDutyPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferEntrustInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferEntryState;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferJoinPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.CheckOfferInputParamLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferRegistLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

/**
 * 事業方針説明会　申込登録処理ロジック
 * 
 * @author xlee
 */
public class GetOfferRegistLogicImpl implements GetOfferRegistLogic{

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN011L03";

    /**
     * 責任者登録処理DAOを取得します。
     */
    private UIOfferDutyDao uiOfferDutyDao;
    
    /**
     * 参加者登録処理DAOを取得します。
     */
    private UIOfferJoinDao uiOfferJoinDao;
    
    /**
     * 委任状登録処理DAOを取得します。
     */
    private UIOfferIninDao uiOfferIninDao;
    
    /**
     * オーナー別エントリー状況DAOを取得します。
     */
    private UIOfferEntryStateDao uiOfferEntryStateDao;
    
    private CheckOfferInputParamLogic checkOfferInputParamLogic;
    
    /**
     * 責任者登録処理DAOを取得します。
     * @return 責任者登録処理DAO
     */
    public UIOfferDutyDao getUIOfferDutyDao() {
        return uiOfferDutyDao;
    }

    /**
     * 責任者登録処理DAOを設定します。
     * @param uiOfferDutyDao 責任者登録処理DAO
     */
    public void setUIOfferDutyDao(UIOfferDutyDao uiOfferDutyDao) {
        this.uiOfferDutyDao = uiOfferDutyDao;
    }
    
    /**
     * 参加者登録処理DAOを取得します。
     * @return 参加者登録処理DAO
     */
    public UIOfferJoinDao getUIOfferJoinDao() {
        return uiOfferJoinDao;
    }

    /**
     * 参加者登録処理DAOを設定します。
     * @param uiOfferJoinDao 参加者登録処理DAO
     */
    public void setUIOfferJoinDao(UIOfferJoinDao uiOfferJoinDao) {
        this.uiOfferJoinDao = uiOfferJoinDao;
    }
    
    /**
     * 委任状登録処理DAOを取得します。
     * @return 委任状登録処理DAO
     */
    public UIOfferIninDao getUIOfferIninDao() {
        return uiOfferIninDao;
    }

    /**
     * 委任状登録処理DAOを設定します。
     * @param uiOfferIninDao 委任状登録処理DAO
     */
    public void setUIOfferIninDao(UIOfferIninDao uiOfferIninDao) {
        this.uiOfferIninDao = uiOfferIninDao;
    }
    
    /**
     * 委任状登録処理DAOを取得します。
     * @return 委任状登録処理DAO
     */
    public UIOfferEntryStateDao getUIOfferEntryStateDao() {
        return uiOfferEntryStateDao;
    }

    /**
     * 委任状登録処理DAOを設定します。
     * @param uiOfferIninDao 委任状登録処理DAO
     */
    public void setUIOfferEntryStateDao(UIOfferEntryStateDao uiOfferEntryStateDao) {
        this.uiOfferEntryStateDao = uiOfferEntryStateDao;
    }
    
    /**
     * 入力データチェックロジックを取得します。
     * @return 　入力データチェックロジック
     */
    public CheckOfferInputParamLogic getCheckOfferInputParamLogic() {
        return checkOfferInputParamLogic;
    }

    /**
     * 入力データチェックロジックを設定します。
     * @param checkOfferInputParamLogic　入力データチェックロジック
     */
    public void setCheckOfferInputParamLogic(CheckOfferInputParamLogic checkOfferInputParamLogic) {
        this.checkOfferInputParamLogic = checkOfferInputParamLogic;
    }
    
	/**
	 * 事業方針説明会　申込登録処理ロジック
	 * 
	 * @param paramMap 入力データ保持マップ
	 * @return null
	 */
    public List execute(Map paramMap) {
    	
    	String[] procKbn = (String[])paramMap.get(ProjectPlanOfferDto.MAP_KEY_PROC);
    	
    	String editKbn = procKbn[0];
    	String ininKbn = procKbn[1];
    	//削除する時、キーになる
    	List uniqKey = (List)paramMap.get(ProjectPlanOfferDto.MAP_KEY_UNIQKEY);
    	
    	if(editKbn.equals(ProjectPlanOfferDto.PROC_KBN_DELETE) &&
    			ininKbn.equals(ProjectPlanOfferDto.PROC_KBN_DELETE)) {
    		//全体レコード削除
    		getUIOfferDutyDao().deleteDuty((String)uniqKey.get(0),(String)uniqKey.get(1),
    				(String)uniqKey.get(2),(String)uniqKey.get(3),(String)uniqKey.get(4), ProjectPlanOfferDto.ATESAKI_KBN);
    		
    		getUIOfferJoinDao().deleteJoin((String)uniqKey.get(0),(String)uniqKey.get(1),
    				(String)uniqKey.get(2),(String)uniqKey.get(3),(String)uniqKey.get(4));
    		
    		getUIOfferIninDao().deleteInin((String)uniqKey.get(0),(String)uniqKey.get(1),
    				(String)uniqKey.get(2),(String)uniqKey.get(3),(String)uniqKey.get(4));
    		
    		getUIOfferEntryStateDao().deleteEntryState((String)uniqKey.get(0),(String)uniqKey.get(1),
    				(String)uniqKey.get(2),(String)uniqKey.get(3),(String)uniqKey.get(4));
    	} else {
	    	String userId = (String)paramMap.get(ProjectPlanOfferDto.MAP_KEY_USERID);
	    	Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
	    	
	    	UIOfferDutyPersonInfo uiOfferDutyPersonInfo = (UIOfferDutyPersonInfo)paramMap.get(ProjectPlanOfferDto.MAP_KEY_DUTY);
	    	List joinList = (List)paramMap.get(ProjectPlanOfferDto.MAP_KEY_JOIN);
	    	UIOfferEntrustInfo uiOfferEntrustInfo = (UIOfferEntrustInfo)paramMap.get(ProjectPlanOfferDto.MAP_KEY_ININ);
	    	
	    	if(editKbn.equals(ProjectPlanOfferDto.PROC_KBN_INSERT)) {
	    		UIOfferEntryState uiOfferEntryState = new UIOfferEntryState();
	    		uiOfferEntryState.setEntryCd((String)uniqKey.get(0));
	    		uiOfferEntryState.setEntryYear((String)uniqKey.get(1));
	    		uiOfferEntryState.setEntryKai((String)uniqKey.get(2));
	    		uiOfferEntryState.setCompanyCd((String)uniqKey.get(3));
	    		uiOfferEntryState.setOnerCd((String)uniqKey.get(4));
	    		uiOfferEntryState.setEntryFlg("");
	    		uiOfferEntryState.setFirstUser(userId);
	    		uiOfferEntryState.setFirstPgm(LOGIC_ID.substring(0, 7));
	    		uiOfferEntryState.setFirstTmsp(currentTimestamp);
	    		uiOfferEntryState.setLastUser(userId);
	    		uiOfferEntryState.setLastPgm(LOGIC_ID.substring(0, 7));
	    		uiOfferEntryState.setLastTmsp(currentTimestamp);
	    		getUIOfferEntryStateDao().insertEntryState(uiOfferEntryState);
	    	}
	    	//エントリーオーナー宛先情報
	    	if(editKbn.equals(ProjectPlanOfferDto.PROC_KBN_INSERT)) {
	    		uiOfferDutyPersonInfo.setFirstUser(userId);
	        	uiOfferDutyPersonInfo.setFirstPgm(LOGIC_ID.substring(0, 7));
	        	uiOfferDutyPersonInfo.setFirstTmsp(currentTimestamp);
	        	uiOfferDutyPersonInfo.setLastUser(userId);
	        	uiOfferDutyPersonInfo.setLastPgm(LOGIC_ID.substring(0, 7));
	        	uiOfferDutyPersonInfo.setLastTmsp(currentTimestamp);
	        	try {
	        		getUIOfferDutyDao().insertDuty(uiOfferDutyPersonInfo);
	        	} catch(SQLRuntimeException se) {
	                SQLException e2 = (SQLException) se.getCause();
	                if( e2.getSQLState().equals("23505") ) {//重複レコードによりインサート不可(今回分のエントリー状況が登録済み)
	    	        	uiOfferDutyPersonInfo.setLastUser(userId);
	    	        	uiOfferDutyPersonInfo.setLastPgm(LOGIC_ID.substring(0, 7));
	    	        	getUIOfferDutyDao().updateDuty(uiOfferDutyPersonInfo);
	                }
	            }
	    	} else if(editKbn.equals(ProjectPlanOfferDto.PROC_KBN_UPDATE)) {
	        	uiOfferDutyPersonInfo.setLastUser(userId);
	        	uiOfferDutyPersonInfo.setLastPgm(LOGIC_ID.substring(0, 7));
	        	getUIOfferDutyDao().updateDuty(uiOfferDutyPersonInfo);
	    	} 
	    	//共栄会系エントリー状況
	    	for(int i = 0; i < joinList.size(); i++) {
	    		UIOfferJoinPersonInfo uiOfferJoinPersonInfo = (UIOfferJoinPersonInfo)joinList.get(i);
	    		if (getCheckOfferInputParamLogic().checkRegistValidtity(uiOfferJoinPersonInfo)) {
	    			uiOfferJoinPersonInfo.setTabNo(String.valueOf(i + 1));
		    		if(editKbn.equals(ProjectPlanOfferDto.PROC_KBN_INSERT)) {
			    		uiOfferJoinPersonInfo.setFirstUser(userId);
			    		uiOfferJoinPersonInfo.setFirstPgm(LOGIC_ID.substring(0, 7));
			    		uiOfferJoinPersonInfo.setFirstTmsp(currentTimestamp);
			    		uiOfferJoinPersonInfo.setLastUser(userId);
			    		uiOfferJoinPersonInfo.setLastPgm(LOGIC_ID.substring(0, 7));
			    		uiOfferJoinPersonInfo.setLastTmsp(currentTimestamp);
			    		try {
			    			getUIOfferJoinDao().insertJoin(uiOfferJoinPersonInfo);
			        	} catch(SQLRuntimeException se) {
			                SQLException e2 = (SQLException) se.getCause();
			                if( e2.getSQLState().equals("23505") ) {//重複レコードによりインサート不可(今回分のエントリー状況が登録済み)
					    		uiOfferJoinPersonInfo.setLastUser(userId);
					    		uiOfferJoinPersonInfo.setLastPgm(LOGIC_ID.substring(0, 7));
					    		getUIOfferJoinDao().updateJoin(uiOfferJoinPersonInfo);
			                }
			            }
			    	} else if(editKbn.equals(ProjectPlanOfferDto.PROC_KBN_UPDATE)) {
			    		if(i == 0) {
			    			//一回ＤＢから削除
			    			getUIOfferJoinDao().deleteJoin((String)uniqKey.get(0),(String)uniqKey.get(1),
			    				(String)uniqKey.get(2),(String)uniqKey.get(3),(String)uniqKey.get(4));
			    		}
			    		uiOfferJoinPersonInfo.setFirstUser(userId);
			    		uiOfferJoinPersonInfo.setFirstPgm(LOGIC_ID.substring(0, 7));
			    		uiOfferJoinPersonInfo.setFirstTmsp(currentTimestamp);
			    		uiOfferJoinPersonInfo.setLastUser(userId);
			    		uiOfferJoinPersonInfo.setLastPgm(LOGIC_ID.substring(0, 7));
			    		uiOfferJoinPersonInfo.setLastTmsp(currentTimestamp);
	                	//新しいデータを更新する場合
	                	getUIOfferJoinDao().insertJoin(uiOfferJoinPersonInfo);
			    	}
	    		}
	    	}
	    	//事業方針説明会委任状
	    	//if(uiOfferEntrustInfo != null) {
		    	if(ininKbn.equals(ProjectPlanOfferDto.PROC_KBN_INSERT)) {
		    		uiOfferEntrustInfo.setFirstUser(userId);
		    		uiOfferEntrustInfo.setFirstPgm(LOGIC_ID.substring(0, 7));
		    		uiOfferEntrustInfo.setFirstTmsp(currentTimestamp);
		    		uiOfferEntrustInfo.setLastUser(userId);
		    		uiOfferEntrustInfo.setLastPgm(LOGIC_ID.substring(0, 7));
		    		uiOfferEntrustInfo.setLastTmsp(currentTimestamp);
		    		try {
		    			getUIOfferIninDao().insertInin(uiOfferEntrustInfo);
		        	} catch(SQLRuntimeException se) {
		                SQLException e2 = (SQLException) se.getCause();
		                if( e2.getSQLState().equals("23505") ) {//重複レコードによりインサート不可(今回分のエントリー状況が登録済み)
				    		uiOfferEntrustInfo.setLastUser(userId);
				    		uiOfferEntrustInfo.setLastPgm(LOGIC_ID.substring(0, 7));
				    		getUIOfferIninDao().updateInin(uiOfferEntrustInfo);
		                }
		            }
		    	} else if(ininKbn.equals(ProjectPlanOfferDto.PROC_KBN_UPDATE)) {
		    		uiOfferEntrustInfo.setLastUser(userId);
		    		uiOfferEntrustInfo.setLastPgm(LOGIC_ID.substring(0, 7));
		    		
		    		try{
		    			getUIOfferIninDao().updateInin(uiOfferEntrustInfo);
		            }
		            catch(NotSingleRowUpdatedRuntimeException se) {
			    		uiOfferEntrustInfo.setFirstUser(userId);
			    		uiOfferEntrustInfo.setFirstPgm(LOGIC_ID.substring(0, 7));
			    		uiOfferEntrustInfo.setFirstTmsp(currentTimestamp);
			    		uiOfferEntrustInfo.setLastUser(userId);
			    		uiOfferEntrustInfo.setLastPgm(LOGIC_ID.substring(0, 7));
			    		uiOfferEntrustInfo.setLastTmsp(currentTimestamp);             	
	                	//新しいデータを更新する場合
			    		getUIOfferIninDao().insertInin(uiOfferEntrustInfo);
		            }
		    		
		    	} else if(ininKbn.equals(ProjectPlanOfferDto.PROC_KBN_DELETE)) {
		    		getUIOfferIninDao().deleteInin((String)uniqKey.get(0),(String)uniqKey.get(1),
		    				(String)uniqKey.get(2),(String)uniqKey.get(3),(String)uniqKey.get(4));
		    	}
	    	//}
    	}
		return null;
    }
}
