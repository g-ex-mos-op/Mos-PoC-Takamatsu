package jp.co.isid.mos.bird.storemanage.staffregist.entity;

import java.sql.Timestamp;

/**
 * @author xylee
 */
public class MstStaff {

	public static final String TABLE = "BM12STAF";

	public static final String staffId_COLUMN = "STAFF_ID";
	public static final String companyCd_COLUMN = "COMPANY_CD";
	public static final String onerCd_COLUMN = "ONER_CD";
	public static final String oldOnerCd_COLUMN = "OLD_ONER_CD";
	public static final String miseCd1_COLUMN = "MISE_CD_1";
	public static final String miseCd2_COLUMN = "MISE_CD_2";
	public static final String miseCd3_COLUMN = "MISE_CD_3";
	public static final String miseCd4_COLUMN = "MISE_CD_4";
	public static final String miseCd5_COLUMN = "MISE_CD_5";
	public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
	public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
	public static final String staffLNameKna_COLUMN = "STAFF_L_NAME_KNA";
	public static final String staffFNameKna_COLUMN = "STAFF_F_NAME_KNA";
	public static final String sex_COLUMN = "SEX";
	public static final String birthday_COLUMN = "BIRTHDAY";
	public static final String situationKbn_COLUMN = "SITUATION_KBN";
	public static final String moveDt_COLUMN = "MOVE_DT";
	public static final String retireDt_COLUMN = "RETIRE_DT";
	public static final String leaveDt_COLUMN = "LEAVE_DT";
	public static final String returnDt_COLUMN = "RETURN_DT";
	public static final String note_COLUMN = "NOTE";
	public static final String spareFlg1_COLUMN = "SPARE_FLG1";
	public static final String spareFlg2_COLUMN = "SPARE_FLG2";
	public static final String spareFlg3_COLUMN = "SPARE_FLG3";
	public static final String firstUser_COLUMN = "FIRST_USER";
	public static final String firstPgm_COLUMN = "FIRST_PGM";
	public static final String firstTmsp_COLUMN = "FIRST_TMSP";
	public static final String lastUser_COLUMN = "LAST_USER";
	public static final String lastPgm_COLUMN = "LAST_PGM";
	public static final String lastTmsp_COLUMN = "LAST_TMSP";
	public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    public static final String closeDt_COLUMN = "CLOSE_DT";

    /**
     * スタッフID
     */
    private String staffId;

	/**
     * 企業コード
     */
    private String companyCd;

    /**
     * オーナーコード
     */
    private String onerCd;

    /**
     * 前回オーナーコード
     */
    private String oldOnerCd;

    /**
     * 店コード1
     */
    private String miseCd1;

    /**
     * 店コード2
     */
    private String miseCd2;

    /**
     * 店コード3
     */
    private String miseCd3;

    /**
     * 店コード4
     */
    private String miseCd4;

    /**
     * 店コード5
     */
    private String miseCd5;

    /**
     * 店コード名
     */
    private String miseNameKj;
    /**
     * スタッフ氏　（漢字）
     */
    private String staffLNameKj;

    /**
     * スタッフ名　（漢字）
     */
    private String staffFNameKj;

    /**
     * スタッフ氏　（カナ）
     */
    private String staffLNameKna;

    /**
     * スタッフ名　（カナ）
     */
    private String staffFNameKna;

    /**
     * 性別
     */
    private String sex;

    /**
     * 生年月日
     */
    private String birthday;

    /**
     * 活動状況区分
     */
    private String situationKbn;

    /**
     * 異動日
     */
    private String moveDt;

    /**
     * 退職日
     */
    private String retireDt;

    /**
     * 休職日
     */
    private String leaveDt;

    /**
     * 復職日
     */
    private String returnDt;

    /**
     * 	備考
     */
    private String note;

    /**
     * 予備フラグ1
     */
    private String spareFlg1;

    /**
     * 予備フラグ2
     */
    private String spareFlg2;

    /**
     * 予備フラグ3
     */
    private String spareFlg3;

    /**
     * 登録ユーザー
     */
    private String firstUser;

    /**
     * 登録プログラム
     */
    private String firstPgm;

    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;

    /**
     * 修正ユーザー
     */
    private String lastUser;

    /**
     * 修正プログラム
     */
    private String lastPgm;

    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;

    /**
     * クローズ日
     */
    private String closeDt;

    private boolean insertFlag;
    private String birthdayYear;
    private String birthdayMonth;
    private String birthdayDay;
    private String moveDtYear;
    private String moveDtMonth;
    private String moveDtDay;
    private String retireDtYear;
    private String retireDtMonth;
    private String retireDtDay;
    private String leaveDtYear;
    private String leaveDtMonth;
    private String leaveDtDay;
    private String returnDtYear;
    private String returnDtMonth;
    private String returnDtDay;

    /**
	 * @return miseNameKj を戻します。
	 */
	public String getMiseNameKj() {
		return miseNameKj;
	}
	/**
	 * @param miseNameKj miseNameKj を設定。
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}
	/**
	 * @return birthday を戻します。
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday birthday を設定。
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return firstPgm を戻します。
	 */
	public String getFirstPgm() {
		return firstPgm;
	}
	/**
	 * @param firstPgm firstPgm を設定。
	 */
	public void setFirstPgm(String firstPgm) {
		this.firstPgm = firstPgm;
	}
	/**
	 * @return firstTmsp を戻します。
	 */
	public Timestamp getFirstTmsp() {
		return firstTmsp;
	}
	/**
	 * @param firstTmsp firstTmsp を設定。
	 */
	public void setFirstTmsp(Timestamp firstTmsp) {
		this.firstTmsp = firstTmsp;
	}
	/**
	 * @return firstUser を戻します。
	 */
	public String getFirstUser() {
		return firstUser;
	}
	/**
	 * @param firstUser firstUser を設定。
	 */
	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}
	/**
	 * @return lastPgm を戻します。
	 */
	public String getLastPgm() {
		return lastPgm;
	}
	/**
	 * @param lastPgm lastPgm を設定。
	 */
	public void setLastPgm(String lastPgm) {
		this.lastPgm = lastPgm;
	}
	/**
	 * @return lastTmsp を戻します。
	 */
	public Timestamp getLastTmsp() {
		return lastTmsp;
	}
	/**
	 * @param lastTmsp lastTmsp を設定。
	 */
	public void setLastTmsp(Timestamp lastTmsp) {
		this.lastTmsp = lastTmsp;
	}
	/**
	 * @return lastUser を戻します。
	 */
	public String getLastUser() {
		return lastUser;
	}
	/**
	 * @param lastUser lastUser を設定。
	 */
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
	/**
	 * @return leaveDt を戻します。
	 */
	public String getLeaveDt() {
		return leaveDt;
	}
	/**
	 * @param leaveDt leaveDt を設定。
	 */
	public void setLeaveDt(String leaveDt) {
		this.leaveDt = leaveDt;
	}
	/**
	 * @return miseCd1 を戻します。
	 */
	public String getMiseCd1() {
		return miseCd1;
	}
	/**
	 * @param miseCd1 miseCd1 を設定。
	 */
	public void setMiseCd1(String miseCd1) {
		this.miseCd1 = miseCd1;
	}
	/**
	 * @return miseCd2 を戻します。
	 */
	public String getMiseCd2() {
		return miseCd2;
	}
	/**
	 * @param miseCd2 miseCd2 を設定。
	 */
	public void setMiseCd2(String miseCd2) {
		this.miseCd2 = miseCd2;
	}
	/**
	 * @return miseCd3 を戻します。
	 */
	public String getMiseCd3() {
		return miseCd3;
	}
	/**
	 * @param miseCd3 miseCd3 を設定。
	 */
	public void setMiseCd3(String miseCd3) {
		this.miseCd3 = miseCd3;
	}
	/**
	 * @return miseCd4 を戻します。
	 */
	public String getMiseCd4() {
		return miseCd4;
	}
	/**
	 * @param miseCd4 miseCd4 を設定。
	 */
	public void setMiseCd4(String miseCd4) {
		this.miseCd4 = miseCd4;
	}
	/**
	 * @return miseCd5 を戻します。
	 */
	public String getMiseCd5() {
		return miseCd5;
	}
	/**
	 * @param miseCd5 miseCd5 を設定。
	 */
	public void setMiseCd5(String miseCd5) {
		this.miseCd5 = miseCd5;
	}
	/**
	 * @return moveDt を戻します。
	 */
	public String getMoveDt() {
		return moveDt;
	}
	/**
	 * @param moveDt moveDt を設定。
	 */
	public void setMoveDt(String moveDt) {
		this.moveDt = moveDt;
	}
	/**
	 * @return note を戻します。
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note note を設定。
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return oldOnerCd を戻します。
	 */
	public String getOldOnerCd() {
		return oldOnerCd;
	}
	/**
	 * @param oldOnerCd oldOnerCd を設定。
	 */
	public void setOldOnerCd(String oldOnerCd) {
		this.oldOnerCd = oldOnerCd;
	}
	/**
	 * @return onerCd を戻します。
	 */
	public String getOnerCd() {
		return onerCd;
	}
	/**
	 * @param onerCd onerCd を設定。
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
	/**
	 * @return retireDt を戻します。
	 */
	public String getRetireDt() {
		return retireDt;
	}
	/**
	 * @param retireDt retireDt を設定。
	 */
	public void setRetireDt(String retireDt) {
		this.retireDt = retireDt;
	}
	/**
	 * @return returnDt を戻します。
	 */
	public String getReturnDt() {
		return returnDt;
	}
	/**
	 * @param returnDt returnDt を設定。
	 */
	public void setReturnDt(String returnDt) {
		this.returnDt = returnDt;
	}
	/**
	 * @return sex を戻します。
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex sex を設定。
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return situationKbn を戻します。
	 */
	public String getSituationKbn() {
		return situationKbn;
	}
	/**
	 * @param situationKbn situationKbn を設定。
	 */
	public void setSituationKbn(String situationKbn) {
		this.situationKbn = situationKbn;
	}
	/**
	 * @return spareFlg1 を戻します。
	 */
	public String getSpareFlg1() {
		return spareFlg1;
	}
	/**
	 * @param spareFlg1 spareFlg1 を設定。
	 */
	public void setSpareFlg1(String spareFlg1) {
		this.spareFlg1 = spareFlg1;
	}
	/**
	 * @return spareFlg2 を戻します。
	 */
	public String getSpareFlg2() {
		return spareFlg2;
	}
	/**
	 * @param spareFlg2 spareFlg2 を設定。
	 */
	public void setSpareFlg2(String spareFlg2) {
		this.spareFlg2 = spareFlg2;
	}
	/**
	 * @return spareFlg3 を戻します。
	 */
	public String getSpareFlg3() {
		return spareFlg3;
	}
	/**
	 * @param spareFlg3 spareFlg3 を設定。
	 */
	public void setSpareFlg3(String spareFlg3) {
		this.spareFlg3 = spareFlg3;
	}
	/**
	 * @return staffFNameKj を戻します。
	 */
	public String getStaffFNameKj() {
		return staffFNameKj;
	}
	/**
	 * @param staffFNameKj staffFNameKj を設定。
	 */
	public void setStaffFNameKj(String staffFNameKj) {
		this.staffFNameKj = staffFNameKj;
	}
	/**
	 * @return staffFNameKna を戻します。
	 */
	public String getStaffFNameKna() {
		return staffFNameKna;
	}
	/**
	 * @param staffFNameKna staffFNameKna を設定。
	 */
	public void setStaffFNameKna(String staffFNameKna) {
		this.staffFNameKna = staffFNameKna;
	}
	/**
	 * @return staffId を戻します。
	 */
	public String getStaffId() {
		return staffId;
	}
	/**
	 * @param staffId staffId を設定。
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	/**
	 * @return staffLNameKj を戻します。
	 */
	public String getStaffLNameKj() {
		return staffLNameKj;
	}
	/**
	 * @param staffLNameKj staffLNameKj を設定。
	 */
	public void setStaffLNameKj(String staffLNameKj) {
		this.staffLNameKj = staffLNameKj;
	}
	/**
	 * @return staffLNameKna を戻します。
	 */
	public String getStaffLNameKna() {
		return staffLNameKna;
	}
	/**
	 * @param staffLNameKna staffLNameKna を設定。
	 */
	public void setStaffLNameKna(String staffLNameKna) {
		this.staffLNameKna = staffLNameKna;
	}
    /**
     * @return closeDt を戻します。
     */
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * @param closeDt closeDt を設定。
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }

    /** 新規フラグ */
    public boolean isInsertFlag() {
        return insertFlag;
    }
    public void setInsertFlag(boolean insertFlag) {
        this.insertFlag = insertFlag;
    }

    public String getBirthdayYear() {
        return birthdayYear;
    }
    public void setBirthdayYear(String birthdayYear) {
        this.birthdayYear = birthdayYear;
    }
    public String getBirthdayMonth() {
        return birthdayMonth;
    }
    public void setBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }
    public String getBirthdayDay() {
        return birthdayDay;
    }
    public void setBirthdayDay(String birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    public String getMoveDtYear() {
        return moveDtYear;
    }
    public void setMoveDtYear(String moveDtYear) {
        this.moveDtYear = moveDtYear;
    }
    public String getMoveDtMonth() {
        return moveDtMonth;
    }
    public void setMoveDtMonth(String moveDtMonth) {
        this.moveDtMonth = moveDtMonth;
    }
    public String getMoveDtDay() {
        return moveDtDay;
    }
    public void setMoveDtDay(String moveDtDay) {
        this.moveDtDay = moveDtDay;
    }

    public String getRetireDtYear() {
        return retireDtYear;
    }
    public void setRetireDtYear(String retireDtYear) {
        this.retireDtYear = retireDtYear;
    }
    public String getRetireDtMonth() {
        return retireDtMonth;
    }
    public void setRetireDtMonth(String retireDtMonth) {
        this.retireDtMonth = retireDtMonth;
    }
    public String getRetireDtDay() {
        return retireDtDay;
    }
    public void setRetireDtDay(String retireDtDay) {
        this.retireDtDay = retireDtDay;
    }

    public String getLeaveDtYear() {
        return leaveDtYear;
    }
    public void setLeaveDtYear(String leaveDtYear) {
        this.leaveDtYear = leaveDtYear;
    }
    public String getLeaveDtMonth() {
        return leaveDtMonth;
    }
    public void setLeaveDtMonth(String leaveDtMonth) {
        this.leaveDtMonth = leaveDtMonth;
    }
    public String getLeaveDtDay() {
        return leaveDtDay;
    }
    public void setLeaveDtDay(String leaveDtDay) {
        this.leaveDtDay = leaveDtDay;
    }

    public String getReturnDtYear() {
        return returnDtYear;
    }
    public void setReturnDtYear(String returnDtYear) {
        this.returnDtYear = returnDtYear;
    }
    public String getReturnDtMonth() {
        return returnDtMonth;
    }
    public void setReturnDtMonth(String returnDtMonth) {
        this.returnDtMonth = returnDtMonth;
    }
    public String getReturnDtDay() {
        return returnDtDay;
    }
    public void setReturnDtDay(String returnDtDay) {
        this.returnDtDay = returnDtDay;
    }

}