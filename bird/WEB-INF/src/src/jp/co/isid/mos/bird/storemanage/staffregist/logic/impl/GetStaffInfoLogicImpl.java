/*
 * 作成日: 2006/06/07
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import jp.co.isid.mos.bird.storemanage.staffregist.dao.MstStaffDao;
import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaff;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.GetStaffInfoLogic;

/**
 * @author xyuchida
 */
public class GetStaffInfoLogicImpl implements GetStaffInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM004L05";

    private static final String MOVE_DATE_DUMMY = "19000101";

    private MstStaffDao mstStaffDao;

    public MstStaffDao getMstStaffDao() {
        return mstStaffDao;
    }
    public void setMstStaffDao(MstStaffDao mstStaffDao) {
        this.mstStaffDao = mstStaffDao;
    }

    public MstStaff execute(String staffId) {

        // スタッフ情報取得
        MstStaff mstStaff = getMstStaffDao().selectStaff(staffId);

        // 生年月日
        if(mstStaff.getBirthday() != null && mstStaff.getBirthday().trim().length() !=0 ){
        	String strBirthday = mstStaff.getBirthday().trim();
	        mstStaff.setBirthdayYear(strBirthday.substring(0,4));
	        mstStaff.setBirthdayMonth(strBirthday.substring(4,6));
	        mstStaff.setBirthdayDay(strBirthday.substring(6,8));
        }
        // 異動日
        // ダミー値判定
        if (mstStaff.getMoveDt() != null && mstStaff.getMoveDt().equals(MOVE_DATE_DUMMY)) {
            // 未設定として扱う
            mstStaff.setMoveDt(null);
        }
        if(mstStaff.getMoveDt() != null && mstStaff.getMoveDt().trim().length() !=0 ){
        	String strMoveDt = mstStaff.getMoveDt().trim();
	        mstStaff.setMoveDtYear(strMoveDt.substring(0,4));
	        mstStaff.setMoveDtMonth(strMoveDt.substring(4,6));
	        mstStaff.setMoveDtDay(strMoveDt.substring(6,8));
        }
        // 退職日
        if(mstStaff.getRetireDt() != null && mstStaff.getRetireDt().trim().length() !=0 ){
        	String strRetireDt = mstStaff.getRetireDt().trim();
            mstStaff.setRetireDtYear(strRetireDt.substring(0,4));
            mstStaff.setRetireDtMonth(strRetireDt.substring(4,6));
            mstStaff.setRetireDtDay(strRetireDt.substring(6,8));
        }
        // 休職日
        if(mstStaff.getLeaveDt() != null && mstStaff.getLeaveDt().trim().length() != 0){
        	String strLeaveDt = mstStaff.getLeaveDt().trim();
            mstStaff.setLeaveDtYear(strLeaveDt.substring(0,4));
            mstStaff.setLeaveDtMonth(strLeaveDt.substring(4,6));
            mstStaff.setLeaveDtDay(strLeaveDt.substring(6,8));
        }
        // 復職日
        if(mstStaff.getReturnDt() != null && mstStaff.getReturnDt().trim().length() != 0){
        	String strReturnDt = mstStaff.getReturnDt().trim();
	        mstStaff.setReturnDtYear(strReturnDt.substring(0,4));
	        mstStaff.setReturnDtMonth(strReturnDt.substring(4,6));
	        mstStaff.setReturnDtDay(strReturnDt.substring(6,8));
        }

        return mstStaff;
    }

}
