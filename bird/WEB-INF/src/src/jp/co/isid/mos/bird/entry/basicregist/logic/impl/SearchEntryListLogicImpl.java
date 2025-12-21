/*
 * 作成日: 2006/5/31
 */
package jp.co.isid.mos.bird.entry.basicregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicregist.common.BasicRegistConstants;
import jp.co.isid.mos.bird.entry.basicregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.basicregist.dto.BasicRegistDto;
import jp.co.isid.mos.bird.entry.basicregist.logic.SearchEntryListLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * エントリーマスタ管理の検索ロジック
 * @author xyuchida
 */
public class SearchEntryListLogicImpl implements SearchEntryListLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN001L01";

    /**
     * エントリーマスタ管理（UIEntryMstDao）
     */
    private UIEntryMstDao uiEntryMstDao;

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

	/**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param basicRegistDto ベーシック研修マスタ登録DTO
     */
    public void execute(BasicRegistDto basicRegistDto) {

        String entryCd = BasicRegistConstants.ENTRYCODE_BASIC;

        // 抽出日付範囲算出
        String fromYear = getPreviousYear(basicRegistDto.getSysDate());
        
        //---20070111---削除(マスタライセンスPh4対応)---
        //String toYear = getCurrentYear(basicRegistDto.getSysDate());

        //-------------
        // 全件数取得
        //-------------
//---20070111---変更(マスタライセンスPh4対応)---start---
        //basicRegistDto.setCount(getUiEntryMstDao().getEntryCount(entryCd, fromYear, toYear));
        basicRegistDto.setCount(getUiEntryMstDao().getEntryCount(entryCd, fromYear));
//---20070111---変更(マスタライセンスPh4対応)--- end ---

        //-------------
        // select
        //-------------
//---20070111---変更(マスタライセンスPh4対応)---start---
        //List basicEntryList = getUiEntryMstDao().getEntry(entryCd, fromYear, toYear);
        List basicEntryList = getUiEntryMstDao().getEntry(entryCd, fromYear);
//---20070111---変更(マスタライセンスPh4対応)--- end ---

        // ページ範囲インデックス設定
        int fromIndex = basicRegistDto.getPageFirstRecordNumber();
        int toIndex = fromIndex + basicRegistDto.getMaxPageCount();
        fromIndex = (fromIndex > basicEntryList.size()) ? basicEntryList.size() : fromIndex;
        toIndex = (toIndex > basicEntryList.size()) ? basicEntryList.size() : toIndex;

        // エントリーリスト設定
        basicRegistDto.setBasicEntryList(basicEntryList.subList(fromIndex, toIndex));
    }

    public String getCurrentYear(String date) {
        String result = null;
        if (date != null && date.length() >= 4) {
            result = date.substring(0, 4);
        }
        return result;
    }

    public String getPreviousYear(String date) {
        String result = null;
        try {
            result = DateManager.getPrevYear(getCurrentYear(date), 1);
        } catch (Exception e) {
            // 計算不可能な場合はnullを返却する為無処理
        }
        return result;
    }
}
