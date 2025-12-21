/*
 * 作成日: 2006/06/09
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlregist.dao.UIEntryMasterDao;
import jp.co.isid.mos.bird.entry.mlregist.dto.MlRegistDto;
import jp.co.isid.mos.bird.entry.mlregist.logic.SearchEntryMasterListLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * エントリーマスタ管理検索ロジック
 * 
 * @author xyuchida
 */
public class SearchEntryMasterListLogicImpl implements SearchEntryMasterListLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN007L01";

    /**
     * エントリーマスタ管理DAO
     */
    private UIEntryMasterDao uiEntryMasterDao;

    public UIEntryMasterDao getUiEntryMasterDao() {
        return uiEntryMasterDao;
    }
    public void setUiEntryMasterDao(UIEntryMasterDao uiEntryMasterDao) {
        this.uiEntryMasterDao = uiEntryMasterDao;
    }

    /**
     * エントリーマスタ管理検索
     * @param mlRegistDto マスタライセンスマスタ登録DTO
     */
    public void execute(MlRegistDto mlRegistDto) {

        // 抽出日付範囲算出
        String fromYear = getPreviousYear(mlRegistDto.getSysDate());
        String toYear = getCurrentYear(mlRegistDto.getSysDate());

        // 全件数取得
        mlRegistDto.setCount(getUiEntryMasterDao().getEntryMasterCount(mlRegistDto.getEntryCd(), fromYear, toYear));

        // select
        List mlEntryList = getUiEntryMasterDao().getEntryMasterList(mlRegistDto.getEntryCd(), fromYear, toYear);

        // ページ範囲インデックス設定
        int fromIndex = mlRegistDto.getPageFirstRecordNumber();
        int toIndex = fromIndex + mlRegistDto.getMaxPageCount();
        fromIndex = (fromIndex > mlEntryList.size()) ? mlEntryList.size() : fromIndex;
        toIndex = (toIndex > mlEntryList.size()) ? mlEntryList.size() : toIndex;

        // エントリーリスト設定
        mlRegistDto.setMlEntryList(mlEntryList.subList(fromIndex, toIndex));
    }

    private String getCurrentYear(String date) {
        String result = null;
        if (date != null && date.length() >= 4) {
            result = date.substring(0, 4);
        }
        return result;
    }

    private String getPreviousYear(String date) {
        String result = null;
        try {
            result = DateManager.getPrevYear(getCurrentYear(date), 1);
        } catch (Exception e) {
            // 計算不可能な場合はnullを返却する為無処理
        }
        return result;
    }
}
