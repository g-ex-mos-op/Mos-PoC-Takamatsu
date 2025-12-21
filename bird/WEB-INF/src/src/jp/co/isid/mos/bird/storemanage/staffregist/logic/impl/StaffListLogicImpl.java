/*
 * 作成日: 2006/04/11
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.staffregist.dao.MstStaffDao;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.StaffListLogic;

/**
 * @aeuthor xylee
 *
 */
public class StaffListLogicImpl implements StaffListLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM004L01";

	private MstStaffDao mstStaffDao;

	public List execute(String companyCd, String onerCd) {
        
        // 検索結果ソート順位
        // 1.店舗コード(昇順)
        // 2.活動状況(昇順)
        // 3.マスタライセンスNo(昇順・保持者が上)
        // 4.研修修了状況(研修修了者が上)
        // 5.スタッフID(昇順)
		return mstStaffDao.selectStaffList(companyCd, onerCd);
	}

	public MstStaffDao getMstStaffDao() {
		return mstStaffDao;
	}
	public void setMstStaffDao(MstStaffDao mstStaffDao) {
		this.mstStaffDao = mstStaffDao;
	}
}
