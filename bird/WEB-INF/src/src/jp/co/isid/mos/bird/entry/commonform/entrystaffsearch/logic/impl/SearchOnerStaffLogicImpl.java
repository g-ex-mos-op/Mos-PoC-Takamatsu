package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic.impl;


import java.util.List;

import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.common.EntryStaffSearchConst;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dao.MstStaffDao;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic.SearchOnerStaffLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * スタッフ一覧取得ロジック
 * @author xnkusama
 */
public class SearchOnerStaffLogicImpl implements SearchOnerStaffLogic {

    public static final String LOGIC_ID = EntryStaffSearchConst.VIEW_ID + "L01";

    private MstStaffDao entryStaffSearchMstStaffDao;

	public List execute(String companyCd, String onerCd) {
	    // 事前処理
        validate(companyCd, onerCd);
        
        // 検索実行
        return getEntryStaffSearchMstStaffDao()
                    .getOnerStaffList(companyCd, onerCd);
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
     */
    private void validate(String companyCd, String onerCd) {
        if (companyCd == null || "".equals(companyCd.trim())) {
            throw new NotNullException("会社コード");
        }
        if (onerCd == null || "".equals(onerCd.trim())) {
            throw new NotNullException("オーナーコード");
        }
    }
}