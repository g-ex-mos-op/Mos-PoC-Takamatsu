/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferEntrustInfoDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferNoticeInfoDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferDutyPersonInfoDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferJoinPersonInfoDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.dao.UIOfferOnerInfoDao;
import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferEntrustInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferJoinPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferNoticeInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferDutyPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferOnerInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.CheckOfferInputParamLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 店舗情報取得ロジック
 * 
 * @author xlee
 */
public class GetOfferInfoLogicImpl implements GetOfferInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN011L02";
    
    /** ロジックID 定義 */
    public static final int sankaBase = 3;
    
    /**
     * オーナー情報DAOを取得します。
     */
    private UIOfferOnerInfoDao uiOfferOnerInfoDao;
    
    /**
     * 申込責任者情報DAOを取得します。
     */
    private UIOfferDutyPersonInfoDao uiOfferDutyPersonInfoDao;
    
    /**
     * 申込参加者情報DAOを取得します。
     */
    private UIOfferJoinPersonInfoDao uiOfferJoinPersonInfoDao;
    
    /**
     * 注意事項DAOを取得します。
     */
    private UIOfferNoticeInfoDao uiOfferNoticeInfoDao;
    
    /**
     * 委任状情報DAOを取得します。
     */
    private UIOfferEntrustInfoDao uiOfferEntrustInfoDao;
    
    /**
     * チェック処理を取得します。
     */
    private CheckOfferInputParamLogic checkOfferInputParamLogic;

    /**
     * オーナー情報DAOを取得します。
     * @return オーナー情報DAO
     */
    public UIOfferOnerInfoDao getUIOfferOnerInfoDao() {
        return uiOfferOnerInfoDao;
    }

    /**
     * オーナー情報DAOを設定します。
     * @param uiOnerInfoDao オーナー情報DAO
     */
    public void setUIOfferOnerInfoDao(UIOfferOnerInfoDao uiOfferOnerInfoDao) {
        this.uiOfferOnerInfoDao = uiOfferOnerInfoDao;
    }

    /**
     * 申込責任者情報DAOを取得します。
     * @return 申込責任者情報DAO
     */
    public UIOfferDutyPersonInfoDao getUIOfferDutyPersonInfoDao() {
        return uiOfferDutyPersonInfoDao;
    }
    
    /**
     * 申込責任者情報DAOを設定します。
     * @param uiOfferDutyPersonInfoDao 申込責任者DAO
     */
    public void setUIOfferDutyPersonInfoDao(UIOfferDutyPersonInfoDao uiOfferDutyPersonInfoDao) {
        this.uiOfferDutyPersonInfoDao = uiOfferDutyPersonInfoDao;
    }
    
    /**
     * 申込参加者情報DAOを取得します。
     * @return 申込参加者情報DAO
     */
    public UIOfferJoinPersonInfoDao getUIOfferJoinPersonInfoDao() {
        return uiOfferJoinPersonInfoDao;
    }
    
    /**
     * 申込参加者情報DAOを設定します。
     * @param uiOfferJoinPersonInfoDao 申込参加者情報DAO
     */
    public void setUIOfferJoinPersonInfoDao(UIOfferJoinPersonInfoDao uiOfferJoinPersonInfoDao) {
        this.uiOfferJoinPersonInfoDao = uiOfferJoinPersonInfoDao;
    }
    
    /**
     * 注意事項情報DAOを取得します。
     * @return 注意事項情報DAO
     */
    public UIOfferNoticeInfoDao getUIOfferNoticeInfoDao() {
        return uiOfferNoticeInfoDao;
    }

    /**
     * 注意事項情報DAOを設定します。
     * @param uiNoticeInfoDao 注意事項情報DAO
     */
    public void setUIOfferNoticeInfoDao(UIOfferNoticeInfoDao uiOfferNoticeInfoDao) {
        this.uiOfferNoticeInfoDao = uiOfferNoticeInfoDao;
    }
    
    /**
     * 委任状情報DAOを取得します。
     * @return 委任状情報DAO
     */
    public UIOfferEntrustInfoDao getUIOfferEntrustInfoDao() {
        return uiOfferEntrustInfoDao;
    }
    
    /**
     * 委任状情報DAOを設定します。
     * @param uiEntrustInfoDao 委任状委任状情報DAO
     */
    public void setUIOfferEntrustInfoDao(UIOfferEntrustInfoDao uiOfferEntrustInfoDao) {
        this.uiOfferEntrustInfoDao = uiOfferEntrustInfoDao;
    }
    
    /**
     * 入力データチェックロジックを取得します。
     * @return 入力データチェックロジック
     */
    public CheckOfferInputParamLogic getCheckOfferInputParamLogic() {
        return checkOfferInputParamLogic;
    }

    /**
     * 入力データチェックロジックを設定します。
     * @param checkInputParam 入力データチェックロジック
     */
    public void setCheckOfferInputParamLogic(CheckOfferInputParamLogic checkOfferInputParamLogic) {
        this.checkOfferInputParamLogic = checkOfferInputParamLogic;
    }
    
	/**
	 * 申込情報を取得します。
     * @param entryCd エントリーコード
     * @param　entryYear エントリー年
     * @param entryKai エントリー回
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @return  申込情報リスト
	 */
    public ProjectPlanOfferDto execute(ProjectPlanOfferDto projectPlanOfferDto) {
    	
    	String entryCd = projectPlanOfferDto.getCondEntryCd();
    	String entryYear = projectPlanOfferDto.getCondEntryYear();
    	String entryKai = projectPlanOfferDto.getCondEntryKai();
    	String companyCd = projectPlanOfferDto.getCondCompanyCd();
    	String onerCd = projectPlanOfferDto.getCondOnerCd();
    	
    	//エラー処理：
    	if(getCheckOfferInputParamLogic().isNull(entryCd)){
            throw new NotNullException("エントリーコード"); //E0506 エントリーコード
        }
    	//エラー処理：
    	if(getCheckOfferInputParamLogic().isNull(entryYear)){
            throw new NotNullException("エントリー年"); //E0506 エントリー年
        }
    	//エラー処理：
    	if(getCheckOfferInputParamLogic().isNull(entryKai)){
            throw new NotNullException("エントリー回"); //E0506 E0506 エントリー会
        }
    	if(getCheckOfferInputParamLogic().isNull(companyCd)){
            throw new NotNullException("会社コード"); //E0506 会社コード
        }
    	if(getCheckOfferInputParamLogic().isNull(onerCd)){
            throw new NotNullException("オーナーコード"); //E0506 オーナーコード
        }
    	//リタンーするリストの生成
    	List tmpResult = new ArrayList();
    	
    	//[[注意事項],[申込責任者],[[申込参加者１，２，３のリスト]],[委任状情報]]
    	//１．注意事項情報取得
    	UIOfferNoticeInfo uiOfferNoticeInfo = getUIOfferNoticeInfoDao().getOfferNoticeInfo(entryCd, entryYear, entryKai);
    	if(uiOfferNoticeInfo == null) {
    		uiOfferNoticeInfo = new UIOfferNoticeInfo();
    		uiOfferNoticeInfo.setNotice1("");
    		uiOfferNoticeInfo.setNote("");
    	}
    	//２．申込責任者
    	UIOfferDutyPersonInfo uiOfferDutyPersonInfo = 
    		getUIOfferDutyPersonInfoDao().getOfferDutyPersonInfo(entryCd, entryYear, entryKai, companyCd, onerCd);
    	
    	if(uiOfferDutyPersonInfo == null) {
    		projectPlanOfferDto.setPrmEditKbn(ProjectPlanOfferDto.EDIT_KBN_INSERT);
    		//申込責任者の情報がNULLであれば、生成
    		uiOfferDutyPersonInfo = new UIOfferDutyPersonInfo();
    		UIOfferOnerInfo uiOfferOnerInfo = getUIOfferOnerInfoDao().getOfferOnerInfo(companyCd, onerCd);
    		uiOfferDutyPersonInfo.setOnerNameKj(uiOfferOnerInfo.getOnerNameKj());
    		uiOfferDutyPersonInfo.setTel(uiOfferOnerInfo.getOnerTel());
    		uiOfferDutyPersonInfo.setSoufuName(uiOfferOnerInfo.getOnerNameKj());
    		uiOfferDutyPersonInfo.setAddress1(uiOfferOnerInfo.getSeikyuAdrs1());
    		uiOfferDutyPersonInfo.setAddress2(uiOfferOnerInfo.getSeikyuAdrs2());
    		uiOfferDutyPersonInfo.setAddress3(uiOfferOnerInfo.getSeikyuAdrs3());
    		uiOfferDutyPersonInfo.setZip(uiOfferOnerInfo.getSeikyuPostNo());
    	} else {
    		projectPlanOfferDto.setPrmEditKbn(ProjectPlanOfferDto.EDIT_KBN_UPDATE);
    	}
    	//３．申込参加者
    	List offerJoinPersonInfoList = 
    		getUIOfferJoinPersonInfoDao().getOfferJoinPersonInfo(entryCd, entryYear, entryKai, companyCd, onerCd);
    	//最初登録の場合、空白値をエンティティへ設定
    	if(offerJoinPersonInfoList == null || 
    			offerJoinPersonInfoList.size() == 0 || offerJoinPersonInfoList.size() < 3) {
    		int sankaCnt = 0;
    		int strPot = 0;
    		if(offerJoinPersonInfoList == null || offerJoinPersonInfoList.size() == 0) {
    			sankaCnt = sankaBase;
    		} else if(offerJoinPersonInfoList.size() < 3){
    			sankaCnt = sankaBase - offerJoinPersonInfoList.size() + 1;
    			strPot = offerJoinPersonInfoList.size();
    		}
    		for(int i = strPot; i < sankaCnt; i++) {
    			UIOfferJoinPersonInfo uiOfferJoinPersonInfo = new UIOfferJoinPersonInfo();
    			uiOfferJoinPersonInfo.setTabNo(String.valueOf(i+1));
    			uiOfferJoinPersonInfo.setMiseCd("");
    			uiOfferJoinPersonInfo.setLNameKj("");
    			uiOfferJoinPersonInfo.setLNameKna("");
    			uiOfferJoinPersonInfo.setFNameKna("");
    			uiOfferJoinPersonInfo.setSex("");
    			uiOfferJoinPersonInfo.setAge("");
    			uiOfferJoinPersonInfo.setJigyoFlg("");
    			uiOfferJoinPersonInfo.setKonshinFlg("");
    			uiOfferJoinPersonInfo.setKyoeiFlg("");
    			uiOfferJoinPersonInfo.setShukuhakuFlg("");
    			uiOfferJoinPersonInfo.setNote("");
    			uiOfferJoinPersonInfo.setAbsenceReason("");
    			uiOfferJoinPersonInfo.setFirstUser("");
    			uiOfferJoinPersonInfo.setFirstPgm("");
    			uiOfferJoinPersonInfo.setFirstTmsp(null);
    			uiOfferJoinPersonInfo.setLastUser("");
    			uiOfferJoinPersonInfo.setLastPgm("");
    			uiOfferJoinPersonInfo.setLastTmsp(null);
    			offerJoinPersonInfoList.add(uiOfferJoinPersonInfo);
    		}
    	}
    	//４．委任状
    	UIOfferEntrustInfo uiOfferEntrustInfo = 
    		getUIOfferEntrustInfoDao().getOfferEntrustInfo(entryCd, entryYear, entryKai, companyCd, onerCd);
    	
    	if(uiOfferEntrustInfo == null) {
    		projectPlanOfferDto.setPrmIninKbn(ProjectPlanOfferDto.EDIT_KBN_INSERT);
    		uiOfferEntrustInfo = new UIOfferEntrustInfo();
    		uiOfferEntrustInfo.setIninMiseCd("");
    		uiOfferEntrustInfo.setIninFName("");
    		uiOfferEntrustInfo.setIninLName("");
    		uiOfferEntrustInfo.setIninMiseTotal("");
    		uiOfferEntrustInfo.setFirstUser("");
    		uiOfferEntrustInfo.setFirstPgm("");
    		uiOfferEntrustInfo.setFirstTmsp(null);
    		uiOfferEntrustInfo.setLastUser("");
    		uiOfferEntrustInfo.setLastPgm("");
    		uiOfferEntrustInfo.setLastTmsp(null);
    	} else {
    		projectPlanOfferDto.setPrmIninKbn(ProjectPlanOfferDto.EDIT_KBN_UPDATE);
    		projectPlanOfferDto.setValChkMode(ProjectPlanOfferDto.VALCHK_MODE_ININ);
    	}
    	//５．リストへ格納する
    	tmpResult.add(uiOfferNoticeInfo);
    	tmpResult.add(uiOfferDutyPersonInfo);
    	tmpResult.add(offerJoinPersonInfoList);
    	tmpResult.add(uiOfferEntrustInfo);
    	
    	projectPlanOfferDto.setInitInfoList(tmpResult);
    	
        return projectPlanOfferDto;
    }
}
