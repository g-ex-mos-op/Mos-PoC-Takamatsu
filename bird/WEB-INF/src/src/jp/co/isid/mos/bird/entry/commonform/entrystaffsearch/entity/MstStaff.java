package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity;

import java.sql.Timestamp;

public class MstStaff {
    
    public static final String TABLE = "BM12STAF";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String oldOnerCd_COLUMN = "OLD_ONER_CD";
    
    public static final String miseCd1_COLUMN = "MISE_CD_1";
    
    public static final String miseCd1Name_COLUMN = "MISE_CD_1_NAME";
    
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
    
    public static final String job_COLUMN = "JOB";
    
    public static final String situationKbn_COLUMN = "SITUATION_KBN";
    
    public static final String situationKbnDisp_COLUMN = "SITUATION_KBN_DISP";
    
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
     * 店コード１
     */
    private String miseCd1;
    
    /**
     * 店コード１名称
     */
    private String miseCd1Name;
    
    /**
     * 店コード２
     */
    private String miseCd2;
    
    /**
     * 店コード３
     */
    private String miseCd3;
    
    /**
     * 店コード４
     */
    private String miseCd4;
    
    /**
     * 店コード５
     */
    private String miseCd5;
    
    /**
     * スタッフ氏(漢字）
     */
    private String staffLNameKj;
    
    /**
     * スタッフ名（漢字）
     */
    private String staffFNameKj;
    
    /**
     * スタッフ氏（カナ）
     */
    private String staffLNameKna;
    
    /**
     * スタッフ名（カナ）
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
     * 職位
     */
    private String job;
    
    /**
     * 活動状況区分
     */
    private String situationKbn;
    
    /**
     * 活動状況区分表示用
     */
    private String situationKbnDisp;
    
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
     * 備考
     */
    private String note;
    
    /**
     * 予備フラグ１
     */
    private String spareFlg1;
    
    /**
     * 予備フラグ２
     */
    private String spareFlg2;
    
    /**
     * 予備フラグ３
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
     * 登録タイムスタンプ
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
     * 修正タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 再エントリー基準年度
     */
    private String reentryBaseYear;
    
    /**
     * スタッフIDを取得します。
     * @return スタッフID
     */
    public String getStaffId() {
        return staffId;
    }
    /**
     * スタッフIDを設定します。
     * @param staffId スタッフID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * 前回オーナーコードを取得します。
     * @return 前回オーナーコード
     */
    public String getOldOnerCd() {
        return oldOnerCd;
    }
    /**
     * 前回オーナーコードを設定します。
     * @param oldOnerCd 前回オーナーコード
     */
    public void setOldOnerCd(String oldOnerCd) {
        this.oldOnerCd = oldOnerCd;
    }
    
    /**
     * 店コード１を取得します。
     * @return 店コード１
     */
    public String getMiseCd1() {
        return miseCd1;
    }
    /**
     * 店コード１を設定します。
     * @param miseCd1 店コード１
     */
    public void setMiseCd1(String miseCd1) {
        this.miseCd1 = miseCd1;
    }
    
    /**
     * 店コード１名称を取得します。
     * @return 店コード１名称
     */
    public String getMiseCd1Name() {
        return miseCd1Name;
    }
    /**
     * 店コード１名称を設定します。
     * @param miseCd1Name 店コード１名称
     */
    public void setMiseCd1Name(String miseCd1Name) {
        this.miseCd1Name = miseCd1Name;
    }
    
    /**
     * 店コード２を取得します。
     * @return 店コード２
     */
    public String getMiseCd2() {
        return miseCd2;
    }
    /**
     * 店コード２を設定します。
     * @param miseCd2 店コード２
     */
    public void setMiseCd2(String miseCd2) {
        this.miseCd2 = miseCd2;
    }
    
    /**
     * 店コード３を取得します。
     * @return 店コード３
     */
    public String getMiseCd3() {
        return miseCd3;
    }
    /**
     * 店コード３を設定します。
     * @param miseCd3 店コード３
     */
    public void setMiseCd3(String miseCd3) {
        this.miseCd3 = miseCd3;
    }
    
    /**
     * 店コード４を取得します。
     * @return 店コード４
     */
    public String getMiseCd4() {
        return miseCd4;
    }
    /**
     * 店コード４を設定します。
     * @param miseCd4 店コード４
     */
    public void setMiseCd4(String miseCd4) {
        this.miseCd4 = miseCd4;
    }
    
    /**
     * 店コード５を取得します。
     * @return 店コード５
     */
    public String getMiseCd5() {
        return miseCd5;
    }
    /**
     * 店コード５を設定します。
     * @param miseCd5 店コード５
     */
    public void setMiseCd5(String miseCd5) {
        this.miseCd5 = miseCd5;
    }
    
    /**
     * スタッフ氏(漢字）を取得します。
     * @return スタッフ氏(漢字）
     */
    public String getStaffLNameKj() {
        return staffLNameKj;
    }
    /**
     * スタッフ氏(漢字）を設定します。
     * @param staffLNameKj スタッフ氏(漢字）
     */
    public void setStaffLNameKj(String staffLNameKj) {
        this.staffLNameKj = staffLNameKj;
    }
    
    /**
     * スタッフ名（漢字）を取得します。
     * @return スタッフ名（漢字）
     */
    public String getStaffFNameKj() {
        return staffFNameKj;
    }
    /**
     * スタッフ名（漢字）を設定します。
     * @param staffFNameKj スタッフ名（漢字）
     */
    public void setStaffFNameKj(String staffFNameKj) {
        this.staffFNameKj = staffFNameKj;
    }
    
    /**
     * スタッフ氏（カナ）を取得します。
     * @return スタッフ氏（カナ）
     */
    public String getStaffLNameKna() {
        return staffLNameKna;
    }
    /**
     * スタッフ氏（カナ）を設定します。
     * @param staffLNameKna スタッフ氏（カナ）
     */
    public void setStaffLNameKna(String staffLNameKna) {
        this.staffLNameKna = staffLNameKna;
    }
    
    /**
     * スタッフ名（カナ）を取得します。
     * @return スタッフ名（カナ）
     */
    public String getStaffFNameKna() {
        return staffFNameKna;
    }
    /**
     * スタッフ名（カナ）を設定します。
     * @param staffFNameKna スタッフ名（カナ）
     */
    public void setStaffFNameKna(String staffFNameKna) {
        this.staffFNameKna = staffFNameKna;
    }
    
    /**
     * 性別を取得します。
     * @return 性別
     */
    public String getSex() {
        return sex;
    }
    /**
     * 性別を設定します。
     * @param sex 性別
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    /**
     * 生年月日を取得します。
     * @return 生年月日
     */
    public String getBirthday() {
        return birthday;
    }
    /**
     * 生年月日を設定します。
     * @param birthday 生年月日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    /**
     * 職位を取得します。
     * @return 職位
     */
    public String getJob() {
        return job;
    }
    /**
     * 職位を設定します。
     * @param job 職位
     */
    public void setJob(String job) {
        this.job = job;
    }
    
    /**
     * 活動状況区分を取得します。
     * @return 活動状況区分
     */
    public String getSituationKbn() {
        return situationKbn;
    }
    /**
     * 活動状況区分を設定します。
     * @param situationKbn 活動状況区分
     */
    public void setSituationKbn(String situationKbn) {
        this.situationKbn = situationKbn;
    }
    
    /**
     * 活動状況区分表示用を取得します。
     * @return 活動状況区分表示用
     */
    public String getSituationKbnDisp() {
        return situationKbnDisp;
    }
    /**
     * 活動状況区分表示用を設定します。
     * @param situationKbnDisp 活動状況区分表示用
     */
    public void setSituationKbnDisp(String situationKbnDisp) {
        this.situationKbnDisp = situationKbnDisp;
    }
    
    /**
     * 異動日を取得します。
     * @return 異動日
     */
    public String getMoveDt() {
        return moveDt;
    }
    /**
     * 異動日を設定します。
     * @param moveDt 異動日
     */
    public void setMoveDt(String moveDt) {
        this.moveDt = moveDt;
    }
    
    /**
     * 退職日を取得します。
     * @return 退職日
     */
    public String getRetireDt() {
        return retireDt;
    }
    /**
     * 退職日を設定します。
     * @param retireDt 退職日
     */
    public void setRetireDt(String retireDt) {
        this.retireDt = retireDt;
    }
    
    /**
     * 休職日を取得します。
     * @return 休職日
     */
    public String getLeaveDt() {
        return leaveDt;
    }
    /**
     * 休職日を設定します。
     * @param leaveDt 休職日
     */
    public void setLeaveDt(String leaveDt) {
        this.leaveDt = leaveDt;
    }
    
    /**
     * 復職日を取得します。
     * @return 復職日
     */
    public String getReturnDt() {
        return returnDt;
    }
    /**
     * 復職日を設定します。
     * @param returnDt 復職日
     */
    public void setReturnDt(String returnDt) {
        this.returnDt = returnDt;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考を設定します。
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 予備フラグ１を取得します。
     * @return 予備フラグ１
     */
    public String getSpareFlg1() {
        return spareFlg1;
    }
    /**
     * 予備フラグ１を設定します。
     * @param spareFlg1 予備フラグ１
     */
    public void setSpareFlg1(String spareFlg1) {
        this.spareFlg1 = spareFlg1;
    }
    
    /**
     * 予備フラグ２を取得します。
     * @return 予備フラグ２
     */
    public String getSpareFlg2() {
        return spareFlg2;
    }
    /**
     * 予備フラグ２を設定します。
     * @param spareFlg2 予備フラグ２
     */
    public void setSpareFlg2(String spareFlg2) {
        this.spareFlg2 = spareFlg2;
    }
    
    /**
     * 予備フラグ３を取得します。
     * @return 予備フラグ３
     */
    public String getSpareFlg3() {
        return spareFlg3;
    }
    /**
     * 予備フラグ３を設定します。
     * @param spareFlg3 予備フラグ３
     */
    public void setSpareFlg3(String spareFlg3) {
        this.spareFlg3 = spareFlg3;
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正タイムスタンプを取得します。
     * @return 修正タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正タイムスタンプを設定します。
     * @param lastTmsp 修正タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * 再エントリー基準年度を取得します。
     * @return reentryBaseYear 再エントリー基準年度
     */
    public String getReentryBaseYear() {
        return reentryBaseYear;
    }
    /**
     * 再エントリー基準年度を設定します。
     * @param reentryBaseYear 再エントリー基準年度
     */
    public void setReentryBaseYear(String reentryBaseYear) {
        this.reentryBaseYear = reentryBaseYear;
    }
    
}