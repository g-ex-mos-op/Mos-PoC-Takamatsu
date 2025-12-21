/*
 * 作成日: 2006/04/10
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.TrnStaffIDDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.TrnStaffID;
import jp.co.isid.mos.bird.storemanage.staffregist.dao.MstStaffDao;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistDto;
import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaff;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.StaffRegistLogic;

/**
 * @author xylee
 *
 */
public class StaffRegistLogicImpl implements StaffRegistLogic {

	/* ロジックID */
    public static final String LOGIC_ID = "BSM004L02";

    private static final String SCREENID_STAFF_REGIST = "BSM004";

    private static final String MOVE_DATE_DUMMY = "19000101";

    private TrnStaffIDDao trnStaffIDDao;
	
	private MstStaffDao mstStaffDao;
	
	/**
	 * @return mstStaffDao を戻します。
	 */
	public MstStaffDao getMstStaffDao() {
		return mstStaffDao;
	}
	/**
	 * @param mstStaffDao mstStaffDao を設定。
	 */
	public void setMstStaffDao(MstStaffDao mstStaffDao) {
		this.mstStaffDao = mstStaffDao;
	}
	
	/**
	 * @return trnStaffIDDao を戻します。
	 */
	public TrnStaffIDDao getTrnStaffIDDao() {
		return trnStaffIDDao;
	}
	/**
	 * @param trnStaffIDDao trnStaffIDDao を設定。
	 */
	public void setTrnStaffIDDao(TrnStaffIDDao trnStaffIDDao) {
		this.trnStaffIDDao = trnStaffIDDao;
	}

    public void execute(StaffRegistDto staffRegistDto, String userId) {

        MstStaff mstStaff = staffRegistDto.getMstStaff();

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        // 更新者情報設定
        mstStaff.setLastUser(userId);
        mstStaff.setLastPgm(SCREENID_STAFF_REGIST);
        mstStaff.setLastTmsp(currentTimestamp);

        // 異動日ダミー値設定
        if (mstStaff.getMoveDt() == null || mstStaff.getMoveDt().trim().length() == 0) {
            mstStaff.setMoveDt(MOVE_DATE_DUMMY);
        }
        // null置換
        mstStaff.setNote((mstStaff.getNote() == null) ? "" : mstStaff.getNote());

        // 新規フラグ判定
        if (mstStaff.isInsertFlag()) {
            // スタッフID採番
            String staffId = null;
            TrnStaffID trnStaffID = null;
            List staffIdList = getTrnStaffIDDao().selectStaffID();
            if (staffIdList != null && !staffIdList.isEmpty()) {
                trnStaffID = (TrnStaffID) staffIdList.get(0);
                try {
                    // 最大スタッフIDを取得
                    int counter = Integer.parseInt(trnStaffID.getNoCounter());
                    // スタッフIDをインクリメント
                    staffId = String.valueOf(counter + 1);
                } catch (Exception e) {
                    // 無処理
                }
            }
            mstStaff.setStaffId(staffId);

            // 登録者情報設定
            mstStaff.setFirstUser(userId);
            mstStaff.setFirstPgm(SCREENID_STAFF_REGIST);
            mstStaff.setFirstTmsp(currentTimestamp);

            // insert
            getMstStaffDao().insertStaff(mstStaff);

            // スタッフIDカウンタ更新
            trnStaffID.setNoCounter(staffId);
            updateStaffIdCounter(trnStaffID, userId);

        } else {
            // update
            if (staffRegistDto.isSpecificItemEdit()) {
                getMstStaffDao().updateStaff(mstStaff);
            }
            else {
                getMstStaffDao().updateStaffLimited(mstStaff);
            }
        }
	}

    private String createStaffId() {
        String staffId = null;
        // スタッフID採番テーブルを検索
        List staffIdList = getTrnStaffIDDao().selectStaffID();
        if (staffIdList != null && !staffIdList.isEmpty()) {
            TrnStaffID trnStaffID = (TrnStaffID) staffIdList.get(0);
            try {
                // 最大スタッフIDを取得
                int counter = Integer.parseInt(trnStaffID.getNoCounter());
                // スタッフIDをインクリメント
                staffId = String.valueOf(counter + 1);
            } catch (Exception e) {
                // 無処理
            }
        }
        return staffId;
    }

    private void updateStaffIdCounter(TrnStaffID trnStaffID, String userId) {

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        // 更新者情報設定
        trnStaffID.setLastUser(userId);
        trnStaffID.setLastPgm(SCREENID_STAFF_REGIST);
        trnStaffID.setLastTmsp(currentTimestamp);

        // update
        getTrnStaffIDDao().updateStaffID(trnStaffID);
    }
}
