package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic.impl;


import java.util.List;

import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.common.EntryStaffSearchConst;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dao.MstStaffDao;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic.SearchOnerStaffByMlEntryLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * スタッフ一覧取得ロジック
 * ※モード１(ML受験申込)対応
 * @author Aspac
 */
public class SearchOnerStaffByMlEntryLogicImpl implements SearchOnerStaffByMlEntryLogic {

    public static final String LOGIC_ID = EntryStaffSearchConst.VIEW_ID + "L03";

    private MstStaffDao entryStaffSearchMstStaffDao;

	public List execute(String companyCd, String onerCd, String entryYear, String entryKai) {
	    // 事前処理
        validate(companyCd, onerCd, entryYear, entryKai);
        
        String entryYearKai = entryYear + entryKai;
        
        // 検索実行
        return getEntryStaffSearchMstStaffDao()
                    .getOnerStaffListByMlEntry(companyCd, onerCd, entryYear, entryYearKai);
	}

    public MstStaffDao getEntryStaffSearchMstStaffDao() {
        return entryStaffSearchMstStaffDao;
    }

    public void setEntryStaffSearchMstStaffDao(
            MstStaffDao entryStaffSearchMstStaffDao) {
        this.entryStaffSearchMstStaffDao = entryStaffSearchMstStaffDao;
    }
    
    /**
     * 事前処理
     * @param companyCd
     * @param onerCd
     * @param entryYear
     * @param entryKai
     */
    private void validate(String companyCd, String onerCd, String entryYear, String entryKai) {
        if (companyCd == null || "".equals(companyCd.trim())) {
            throw new NotNullException("会社コード");
        }
        if (onerCd == null || "".equals(onerCd.trim())) {
            throw new NotNullException("オーナーコード");
        }
        if (entryYear == null || "".equals(entryYear.trim())) {
            throw new NotNullException("エントリー年");
        }
        if (entryKai == null || "".equals(entryKai.trim())) {
            throw new NotNullException("エントリー回");
        }
    }
}