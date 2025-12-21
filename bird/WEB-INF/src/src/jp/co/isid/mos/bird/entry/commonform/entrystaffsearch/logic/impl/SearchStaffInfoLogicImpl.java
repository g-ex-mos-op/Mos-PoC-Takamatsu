package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.common.EntryStaffSearchConst;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dao.MstStaffDao;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity.MstStaff;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.logic.SearchStaffInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 指定スタッフ情報取得ロジック
 * @author xnkusama
 */
public class SearchStaffInfoLogicImpl implements SearchStaffInfoLogic {

    public static final String LOGIC_ID = EntryStaffSearchConst.VIEW_ID + "L02";

    private MstStaffDao entryStaffSearchMstStaffDao;

	public MstStaff execute(String staffId) {
        // 事前処理
        validate(staffId);

        // 検索実行
        List listMstStaff = getEntryStaffSearchMstStaffDao().getStaffInfo(staffId);
        
        if (listMstStaff == null || listMstStaff.isEmpty()) {
            throw new NotExistException("指定スタッフ情報");
        }
        
        return (MstStaff) listMstStaff.get(0);
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
     * @param staffId
     */
    private void validate(String staffId) {
        if (staffId == null || "".equals(staffId.trim())) {
            throw new NotNullException("スタッフID");
        }
    }
}