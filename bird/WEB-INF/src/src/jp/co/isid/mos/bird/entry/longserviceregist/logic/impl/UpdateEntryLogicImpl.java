/*
 * 作成日: 2006/12/19
 */
package jp.co.isid.mos.bird.entry.longserviceregist.logic.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;
import jp.co.isid.mos.bird.entry.longserviceregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.longserviceregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.longserviceregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.longserviceregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.longserviceregist.logic.UpdateEntryLogic;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistConstants;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * エントリーマスタ管理の更新ロジック
 * @author narita
 */
public class UpdateEntryLogicImpl implements UpdateEntryLogic {

    /** ロジックID 定義 */
	public static final String LOGIC_ID = "BEN017L04";
    
    /**
     * エントリー日付管理（UIEntryDateDao）
     */
    private UIEntryDateDao uiEntryDateDao;

    /**
     * エントリーマスタ管理（UIEntryMstDao）
     */
    private UIEntryMstDao uiEntryMstDao;

    /**
     * エントリーマスタ管理・エントリー日付管理の更新を行う
     * @param LongserviceRegistDto     
     */
    public List execute(LongserviceRegistDto dto) {

        Map entryDate = new HashMap();
        int result = 9;
        
        CodeFormatter formatter = new CodeFormatter(3);
        formatter.setFormatPattern("000");
        
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // BIRDユーザー情報取得
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        // タイムスタンプ取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        // エントリーマスタ管理
        UIEntryMst uiEntryMst = dto.getUiEntryMst();
        // エントリーマスタ日付情報
        List uiEntryDateList = dto.getDateInfoList();
        UIEntryDate uiEntryDate = new UIEntryDate();
        
        // エントリー年に本部申込FROMの年度(YYYY)をセット
        String newEntryYear = DateManager.getCurrentYear(dto.getUiEntryMst().getHonbuEntryFrom());
        // エントリー回のMaxを取得
        int maxEntryKai = getUiEntryMstDao().getMaxEntryKai(
        		LongserviceRegistConstants.ENTRY_CD_LONGSERVICE, newEntryYear);
        String newEntryKai = formatter.format(String.valueOf(maxEntryKai + 1), true);
        
        if (dto.getEditMode() == LongserviceRegistConstants.EDIT_MODE_INSERT) {
            // [新規]ボタン押下時の処理

            // 新規処理の共通パラメータセット
            entryDate.put(LongserviceRegistConstants.ENTRY_YEAR,newEntryYear);
            entryDate.put(LongserviceRegistConstants.ENTRY_KAI,newEntryKai);
            entryDate.put(LongserviceRegistConstants.FIRST_USER,birdUserInfo.getUserID());
            entryDate.put(LongserviceRegistConstants.FIRST_PGM,LongserviceRegistConstants.SCREEN_ID);
            entryDate.put(LongserviceRegistConstants.FIRST_TMSP,currentTimestamp);
            entryDate.put(LongserviceRegistConstants.LAST_USER,birdUserInfo.getUserID());
            entryDate.put(LongserviceRegistConstants.LAST_PGM,LongserviceRegistConstants.SCREEN_ID);
            entryDate.put(LongserviceRegistConstants.LAST_TMSP,currentTimestamp);
            
            // エントリーマスタ管理
            entryDate.put(LongserviceRegistConstants.ENTRY_TITLE,uiEntryMst.getEntryTitle());
            entryDate.put(LongserviceRegistConstants.SAKUJO_FLG,LongserviceRegistConstants.SAKUJO_FLG_OFF);
            UIEntryMst uiEntryMstData = setUIEntryMst(entryDate);
            
            // エントリー日付管理 本部表示
            entryDate.put(LongserviceRegistConstants.USERTYPE_CD,LongserviceRegistConstants.HONBU);
            entryDate.put(LongserviceRegistConstants.DAY_KBN,LongserviceRegistConstants.DAY_KBN_HYOJI);
            entryDate.put(LongserviceRegistConstants.FROM_DT,uiEntryMst.getHonbuDispFrom());
            entryDate.put(LongserviceRegistConstants.TO_DT,uiEntryMst.getHonbuDispTo());
            UIEntryDate uiEntryDateHonbuHyoji = setUIEntryDate(entryDate);
            // エントリー日付管理 本部登録
            entryDate.put(LongserviceRegistConstants.USERTYPE_CD,LongserviceRegistConstants.HONBU);
            entryDate.put(LongserviceRegistConstants.DAY_KBN,LongserviceRegistConstants.DAY_KBN_TOROKU);
            entryDate.put(LongserviceRegistConstants.FROM_DT,uiEntryMst.getHonbuEntryFrom());
            entryDate.put(LongserviceRegistConstants.TO_DT,uiEntryMst.getHonbuEntryTo());
            UIEntryDate uiEntryDateHonbuToroku = setUIEntryDate(entryDate);  
            
            // エントリー日付管理 オーナー表示
            entryDate.put(LongserviceRegistConstants.USERTYPE_CD,LongserviceRegistConstants.ONER);
            entryDate.put(LongserviceRegistConstants.DAY_KBN,LongserviceRegistConstants.DAY_KBN_HYOJI);
            entryDate.put(LongserviceRegistConstants.FROM_DT,uiEntryMst.getOnerDispFrom());
            entryDate.put(LongserviceRegistConstants.TO_DT,uiEntryMst.getOnerDispTo());
            UIEntryDate uiEntryDateOnerHyoji = setUIEntryDate(entryDate);
            // エントリー日付管理 オーナー登録
            entryDate.put(LongserviceRegistConstants.USERTYPE_CD,LongserviceRegistConstants.ONER);
            entryDate.put(LongserviceRegistConstants.DAY_KBN,LongserviceRegistConstants.DAY_KBN_TOROKU);
            entryDate.put(LongserviceRegistConstants.FROM_DT,uiEntryMst.getOnerEntryFrom());
            entryDate.put(LongserviceRegistConstants.TO_DT,uiEntryMst.getOnerEntryTo());
            UIEntryDate uiEntryDateOnerToroku = setUIEntryDate(entryDate);

            // エントリー管理の新規登録
            result = getUiEntryMstDao().insertEntryMst(uiEntryMstData);
            if(result > 0) result = getUiEntryDateDao().insertEntryDate(uiEntryDateHonbuHyoji);
            if(result > 0) result = getUiEntryDateDao().insertEntryDate(uiEntryDateOnerHyoji);
            if(result > 0) result = getUiEntryDateDao().insertEntryDate(uiEntryDateHonbuToroku);
            if(result > 0) result = getUiEntryDateDao().insertEntryDate(uiEntryDateOnerToroku);

        } else if (dto.getEditMode() == LongserviceRegistConstants.EDIT_MODE_UPDATE) {
            // [編集]ボタン押下時の処理

            // 編集処理の共通パラメータセット
            entryDate.put(LongserviceRegistConstants.ENTRY_YEAR,uiEntryMst.getEntryYear());
            entryDate.put(LongserviceRegistConstants.ENTRY_KAI,uiEntryMst.getEntryKai());
            entryDate.put(LongserviceRegistConstants.LAST_USER,birdUserInfo.getUserID());
            entryDate.put(LongserviceRegistConstants.LAST_PGM,LongserviceRegistConstants.SCREEN_ID);
            // エントリーマスタ管理
        	entryDate.put(LongserviceRegistConstants.ENTRY_TITLE,uiEntryMst.getEntryTitle());
        	entryDate.put(LongserviceRegistConstants.SAKUJO_FLG,LongserviceRegistConstants.SAKUJO_FLG_OFF);
            entryDate.put(LongserviceRegistConstants.LAST_TMSP,uiEntryMst.getLastTmsp());
        	UIEntryMst uiEntryMstData = setUIEntryMst(entryDate);
             // エントリー日付管理 オーナー登録
            entryDate.put(LongserviceRegistConstants.USERTYPE_CD,LongserviceRegistConstants.ONER);
            entryDate.put(LongserviceRegistConstants.DAY_KBN,LongserviceRegistConstants.DAY_KBN_TOROKU);
            entryDate.put(LongserviceRegistConstants.FROM_DT,uiEntryMst.getOnerEntryFrom());
            entryDate.put(LongserviceRegistConstants.TO_DT,uiEntryMst.getOnerEntryTo());
            entryDate.put(LongserviceRegistConstants.LAST_TMSP,uiEntryMst.getLastTmsp());
            uiEntryDate = (UIEntryDate) uiEntryDateList.get(0);
            entryDate.put(LongserviceRegistConstants.LAST_TMSP,uiEntryDate.getLastTmsp());
            UIEntryDate uiEntryDateOnerToroku = setUIEntryDate(entryDate);
            // エントリー日付管理 オーナー表示
            entryDate.put(LongserviceRegistConstants.USERTYPE_CD,LongserviceRegistConstants.ONER);
            entryDate.put(LongserviceRegistConstants.DAY_KBN,LongserviceRegistConstants.DAY_KBN_HYOJI);
            entryDate.put(LongserviceRegistConstants.FROM_DT,uiEntryMst.getOnerDispFrom());
            entryDate.put(LongserviceRegistConstants.TO_DT,uiEntryMst.getOnerDispTo());
            uiEntryDate = (UIEntryDate) uiEntryDateList.get(1);
            entryDate.put(LongserviceRegistConstants.LAST_TMSP,uiEntryDate.getLastTmsp());
            UIEntryDate uiEntryDateOnerHyoji = setUIEntryDate(entryDate);
            // エントリー日付管理 本部登録
            entryDate.put(LongserviceRegistConstants.USERTYPE_CD,LongserviceRegistConstants.HONBU);
            entryDate.put(LongserviceRegistConstants.DAY_KBN,LongserviceRegistConstants.DAY_KBN_TOROKU);
            entryDate.put(LongserviceRegistConstants.FROM_DT,uiEntryMst.getHonbuEntryFrom());
            entryDate.put(LongserviceRegistConstants.TO_DT,uiEntryMst.getHonbuEntryTo());
            uiEntryDate = (UIEntryDate) uiEntryDateList.get(2);
            entryDate.put(LongserviceRegistConstants.LAST_TMSP,uiEntryDate.getLastTmsp());
            UIEntryDate uiEntryDateHonbuToroku = setUIEntryDate(entryDate);
            // エントリー日付管理 本部表示
            entryDate.put(LongserviceRegistConstants.USERTYPE_CD,LongserviceRegistConstants.HONBU);
            entryDate.put(LongserviceRegistConstants.DAY_KBN,LongserviceRegistConstants.DAY_KBN_HYOJI);
            entryDate.put(LongserviceRegistConstants.FROM_DT,uiEntryMst.getHonbuDispFrom());
            entryDate.put(LongserviceRegistConstants.TO_DT,uiEntryMst.getHonbuDispTo());
            uiEntryDate = (UIEntryDate) uiEntryDateList.get(3);
            entryDate.put(LongserviceRegistConstants.LAST_TMSP,uiEntryDate.getLastTmsp());
            UIEntryDate uiEntryDateHonbuHyoji = setUIEntryDate(entryDate);
            
            // エントリー年が変化したか判定
            if (newEntryYear.compareTo(uiEntryMst.getEntryYear()) == 0) {
                // エントリー年に変化が無い為、エントリー管理の更新を行う
            	if(result > 0) result = getUiEntryMstDao().update(uiEntryMstData);
            	if(result > 0) result = getUiEntryDateDao().update(uiEntryDateHonbuHyoji);
            	if(result > 0) result = getUiEntryDateDao().update(uiEntryDateOnerHyoji);
            	if(result > 0) result = getUiEntryDateDao().update(uiEntryDateHonbuToroku);
            	if(result > 0) result = getUiEntryDateDao().update(uiEntryDateOnerToroku);

            } else {
                // エントリー管理の更新（キーを更新する場合）
            	if(result > 0) result = getUiEntryMstDao().updateEntryMstKey(uiEntryMstData,newEntryYear,newEntryKai,currentTimestamp);
            	if(result > 0) result = getUiEntryDateDao().updateEntryDateKey(uiEntryDateOnerToroku,newEntryYear,newEntryKai,currentTimestamp);
            	if(result > 0) result = getUiEntryDateDao().updateEntryDateKey(uiEntryDateOnerHyoji,newEntryYear,newEntryKai,currentTimestamp);
            	if(result > 0) result = getUiEntryDateDao().updateEntryDateKey(uiEntryDateHonbuToroku,newEntryYear,newEntryKai,currentTimestamp);
            	if(result > 0) result = getUiEntryDateDao().updateEntryDateKey(uiEntryDateHonbuHyoji,newEntryYear,newEntryKai,currentTimestamp);
            }
        } else if (dto.getEditMode() == LongserviceRegistConstants.EDIT_MODE_DELETE) {
            // [削除]ボタン押下時の処理
            entryDate.put(LongserviceRegistConstants.ENTRY_YEAR,uiEntryMst.getEntryYear());
            entryDate.put(LongserviceRegistConstants.ENTRY_KAI,uiEntryMst.getEntryKai());
            entryDate.put(LongserviceRegistConstants.SAKUJO_FLG,LongserviceRegistConstants.SAKUJO_FLG_ON);
            entryDate.put(LongserviceRegistConstants.LAST_USER,birdUserInfo.getUserID());
            entryDate.put(LongserviceRegistConstants.LAST_PGM,LongserviceRegistConstants.SCREEN_ID);
            entryDate.put(LongserviceRegistConstants.LAST_TMSP,currentTimestamp);
            UIEntryMst uiEntryMstData = setUIEntryMst(entryDate);
            
            // エントリー管理の削除フラグ更新 
            result = getUiEntryMstDao().updateSakujoFlg(uiEntryMstData);
        }

        // 排他チェック
        if (result == 0) {
            throw new CannotExecuteWithReasonException(
            		LongserviceRegistConstants.MSG_EXCLUSION_CHK,
            		LongserviceRegistConstants.MSG_UPDATE);
        }
        
        return null;
    }
    
    /**
	 * エントリー管理エンティティにＭａｐデータをセットします
	 * @param Map
	 * @return UIEntryMst を戻します。
	 */
    private UIEntryMst setUIEntryMst(Map entryDate){
    	
    	UIEntryMst entity = new UIEntryMst();
    	
    	entity.setEntryCd(LongserviceRegistConstants.ENTRY_CD_LONGSERVICE);
    	entity.setEntryYear( (String) entryDate.get(LongserviceRegistConstants.ENTRY_YEAR));
        entity.setEntryKai( (String) entryDate.get(LongserviceRegistConstants.ENTRY_KAI));
    	entity.setEntryTitle( (String)  entryDate.get(LongserviceRegistConstants.ENTRY_TITLE));
    	entity.setEntryPlace( LongserviceRegistConstants.EMPTY);   	
        entity.setNumberLimit( BigDecimal.valueOf(0));
        entity.setPlaceLimit(  BigDecimal.valueOf(0));
        entity.setNote( LongserviceRegistConstants.EMPTY );
        entity.setSpareFlg1( LongserviceRegistConstants.EMPTY );
        entity.setSpareFlg2( LongserviceRegistConstants.EMPTY );
        entity.setSakujoFlg( (String) entryDate.get(LongserviceRegistConstants.SAKUJO_FLG));
        entity.setFirstUser( (String) entryDate.get(LongserviceRegistConstants.FIRST_USER));
        entity.setFirstPgm( (String) entryDate.get(LongserviceRegistConstants.FIRST_PGM));
        entity.setFirstTmsp( (Timestamp) entryDate.get(LongserviceRegistConstants.FIRST_TMSP));
        entity.setLastUser( (String) entryDate.get(LongserviceRegistConstants.LAST_USER));
        entity.setLastPgm( (String) entryDate.get(LongserviceRegistConstants.LAST_PGM));
        entity.setLastTmsp( (Timestamp) entryDate.get(LongserviceRegistConstants.LAST_TMSP));       
        
    	return entity;
    }
    
    /**
	 * エントリー日付管理エンティティにＭａｐデータをセットします
	 * @param Map
	 * @return UIEntryDate を戻します。
	 */
    private UIEntryDate setUIEntryDate(Map entryDate){
    	
    	UIEntryDate entity = new UIEntryDate();
    	
    	entity.setEntryCd(LongserviceRegistConstants.ENTRY_CD_LONGSERVICE);
    	entity.setEntryYear( (String) entryDate.get(LongserviceRegistConstants.ENTRY_YEAR));
        entity.setEntryKai( (String) entryDate.get(LongserviceRegistConstants.ENTRY_KAI));
        entity.setUsertypeCd( (String) entryDate.get(LongserviceRegistConstants.USERTYPE_CD));
        entity.setDayKbn( (String) entryDate.get(LongserviceRegistConstants.DAY_KBN));
        entity.setFromDt( (String) entryDate.get(LongserviceRegistConstants.FROM_DT));
        entity.setToDt( (String) entryDate.get(LongserviceRegistConstants.TO_DT));
        entity.setFirstUser( (String) entryDate.get(LongserviceRegistConstants.FIRST_USER));
        entity.setFirstPgm( (String) entryDate.get(LongserviceRegistConstants.FIRST_PGM));
        entity.setFirstTmsp( (Timestamp) entryDate.get(LongserviceRegistConstants.FIRST_TMSP));
        entity.setLastUser( (String) entryDate.get(LongserviceRegistConstants.LAST_USER));
        entity.setLastPgm( (String) entryDate.get(LongserviceRegistConstants.LAST_PGM));
        entity.setLastTmsp( (Timestamp) entryDate.get(LongserviceRegistConstants.LAST_TMSP));       
    	
    	return entity;
    }
    
    /**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @return uiEntryDateDao を戻します。
	 */
	public UIEntryDateDao getUiEntryDateDao() {
		return uiEntryDateDao;
	}
	/**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @param uiEntryDateDao uiEntryDateDao を設定。
	 */
	public void setUiEntryDateDao(UIEntryDateDao uiEntryDateDao) {
		this.uiEntryDateDao = uiEntryDateDao;
	}

	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @return uiEntryMstDao を戻します。
	 */
	public UIEntryMstDao getUiEntryMstDao() {
		return uiEntryMstDao;
	}
	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @param uiEntryMstDao uiEntryMstDao を設定。
	 */
	public void setUiEntryMstDao(UIEntryMstDao uiEntryMstDao) {
		this.uiEntryMstDao = uiEntryMstDao;
	}
}
