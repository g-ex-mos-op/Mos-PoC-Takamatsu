/*
 * 作成日: 2006/12/19
 */
package jp.co.isid.mos.bird.entry.longserviceregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;
import jp.co.isid.mos.bird.entry.longserviceregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.longserviceregist.logic.SearchEntryListLogic;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistConstants;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistCommon;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * エントリーマスタ管理の検索ロジック
 * @author narita
 */
public class SearchEntryListLogicImpl implements SearchEntryListLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN017L01";

    /** エントリーマスタ管理Dao */
    UIEntryMstDao uiEntryMstDao;

    /**
     * 永年勤続マスタ情報リストを取得する
     * @param  sysDate システム日付
     * @return 永年勤続マスタ情報リスト
     */
    public List execute(String sysDate) {
        //入力必須チェックを行う
        validate(sysDate);

        // 永年勤続マスタ情報リスト取得
        List result = getUiEntryMstDao().getEntryMst(
        		LongserviceRegistConstants.ENTRY_CD_LONGSERVICE,getPreviousYear(sysDate));

        return result;
    }
    
    public boolean getButtonFlg(LongserviceRegistDto dto){
    	
    	// 編集・削除可能データの存在チェック
    	List mstList = dto.getMstInfoList();
    	
    	for(int i = 0;i < mstList.size(); i++){
    		
    		UIEntryMst uIEntryMst = (UIEntryMst)mstList.get(i);
    		
        	if(Integer.parseInt(uIEntryMst.getEntryYear()) >= Integer.parseInt(dto.getSysNendo()) ){
        		return true;
        	}
    	}
    	return false;
    }

    /**
     * 入力必須チェックする
     * @param sysDate システム日付
     */
    private void validate(String sysDate) {
        if (LongserviceRegistCommon.isNull(sysDate)) {
            throw new NotNullException(LongserviceRegistConstants.MSG_SYS_DATE);
        }
    }
    
    /**
     * 渡された日付の前年度の年を取得する
     * @param date 日付
     * @return 渡された日付の前年度の年
     */
    private String getPreviousYear(String date) {
        try {
            return DateManager.getPrevYear(DateManager.getCurrentYear(date), 1);
        } catch (Exception e) {
            throw new FtlSystemException("年度算出ロジックでエラーが発生しました。");
        }
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