/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.longserviceregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistCommon;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistConstants;
import jp.co.isid.mos.bird.entry.longserviceregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;
import jp.co.isid.mos.bird.entry.longserviceregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.longserviceregist.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * エントリー日付マスタ管理の検索ロジック
 * @author narita
 */
public class SearchEntryLogicImpl implements SearchEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN017L03";

    /** エントリー日付マスタ管理Dao */
    UIEntryDateDao uiEntryDateDao;

    /**
     * 永年勤続日付マスタ情報リストを取得する
     * @param  sysDate システム日付
     * @return 永年勤続マスタ情報リスト
     */
    public List execute(LongserviceRegistDto dto) throws ApplicationException {
        //入力必須チェックを行う
        validate(dto);

        // 永年勤続マスタ情報リスト取得
        List result = getUiEntryDateDao().getEntryDate(
        		LongserviceRegistConstants.ENTRY_CD_LONGSERVICE,dto.getUiEntryMst().getEntryYear(),dto.getUiEntryMst().getEntryKai());

        return result;
    }

    /**
     * 必須、妥当性チェック
     * @param longserviceRegistDto
     */
    private void validate(LongserviceRegistDto dto){

        // エントリーコード
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getEntryCd())) {
            throw new NotNullException("エントリーコード");
        }
        // エントリー年
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getEntryYear())) {
            throw new NotNullException("エントリー年度");
        }
        // エントリー回数
        if (LongserviceRegistCommon.isNull(dto.getUiEntryMst().getEntryKai())) {
            throw new NotNullException("エントリー回数");
        }
    }
    
    /**
     * デフォルトのラジオボタン番号の取得
     * @param mstInfoList 永年勤続マスタ情報
     * @param sysDate システムデータ
     * @return i チェックを付けるラジオボタン番号
     */
    public int getDefSelectIndex(List mstInfoList,String sysNendo){
    	
    	if(LongserviceRegistCommon.isNull(sysNendo)){
    		return 0;
    	}
    	    	
    	for(int i = 0;i < mstInfoList.size(); i++){
    		
    		UIEntryMst uiEntryMst = (UIEntryMst) mstInfoList.get(i);
    		
    		if(LongserviceRegistCommon.isNull(uiEntryMst.getEntryYear())){
    			return 0;
    		}else if(Integer.parseInt(uiEntryMst.getEntryYear()) >= Integer.parseInt(sysNendo)){  
    			return i;
    		}
    	}
    	return 0;
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
}
