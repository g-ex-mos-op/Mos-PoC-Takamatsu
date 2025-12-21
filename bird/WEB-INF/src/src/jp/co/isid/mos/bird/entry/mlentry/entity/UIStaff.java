package jp.co.isid.mos.bird.entry.mlentry.entity;

import java.sql.Timestamp;

public class UIStaff {
    
    public static final String TABLE = "BM12STAF";
    
//    public static final String TIMESTAMP_PROPERTY = "lastTmsp";    
    
    public static final String staffId_COLUMN = "STAFF_ID";
    public static final String companyCd_COLUMN = "COMPANY_CD";
    public static final String onerCd_COLUMN = "ONER_CD";
    public static final String oldOnerCd_COLUMN = "OLD_ONER_CD";
    public static final String miseCd1_COLUMN = "MISE_CD_1";
    public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
    public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
    public static final String staffLNameKna_COLUMN = "STAFF_L_NAME_KNA";
    public static final String staffFNameKna_COLUMN = "STAFF_F_NAME_KNA";
    public static final String sex_COLUMN = "SEX";
    public static final String birthday_COLUMN = "BIRTHDAY";
    public static final String job_COLUMN = "JOB";
    public static final String situationKbn_COLUMN = "SITUATION_KBN";
    public static final String note_COLUMN = "NOTE";
    public static final String lastUser_COLUMN = "LAST_USER";
    public static final String lastPgm_COLUMN = "LAST_PGM";
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    public static final String staffSelect_COLUMN = "STAFF_SELECT";
    public static final String entryFlg_COLUMN = "ENTRY_FLG";
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    public static final String examNo_COLUMN = "EXAM_NO";
    public static final String abilityChk_COLUMN = "ABILITY_CHK";
    public static final String examChk_COLUMN = "EXAM_CHK";
    public static final String interviewChk_COLUMN = "INTERVIEW_CHK";
    public static final String empExpYear_COLUMN = "EMP_EXP_YEAR";
    public static final String empExpMonth_COLUMN = "EMP_EXP_MONTH";
    public static final String paExpYear_COLUMN = "PA_EXP_YEAR";
    public static final String paExpMonth_COLUMN = "PA_EXP_MONTH";
    
    
    /**
     * エントリー状況フラグ
     * 3:エントリー済＋有効履歴あり
     * 2:エントリー済＋有効履歴なし ※履歴テーブルにレコードは存在するが、空レコードのデータ(今回分のデータ)を含む
     * 1:エントリー未＋有効履歴あり
     * 0:エントリー未＋有効履歴なし
     */
    private String stateFlg;
    
    
    /**
     * 更新フラグ
     * true: 更新対象データ
     */
    private boolean updateFlg;
    
    
    
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
     * 職位
     */
    private String job;
    
    /**
     * 活動状況区分
     */
    private String situationKbn;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 申込済フラグ
     */
    private String entryFlg;
    
    /**
     * スタッフ選択プルダウン表示用
     */
    private String staffSelect;
    
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
     * 受験番号
     */
    private String examNo;
    
    /**
     * 能力チェック
     */
    private String abilityChk;
    
    /**
     * 筆記テストチェック
     */
    private String examChk;
    
    /**
     * 面接チェック
     */
    private String interviewChk;
    
    /**
     * 社員暦　年
     */
    private String empExpYear;
    
    /**
     * 社員暦　月
     */
    private String empExpMonth;
    
    /**
     * PA暦　年
     */
    private String paExpYear;
    
    /**
     * PA暦　月
     */
    private String paExpMonth;
    
    /**
     * 備考
     */
    private String note;
    
    
    private String staffNameKj;
    private String staffNameKna;
    
    public String getStaffNameKj() {
        return staffNameKj;
    }
    public void setStaffNameKj(String staffNameKj) {
        this.staffNameKj = staffNameKj;
    }
    public String getStaffNameKna() {
        return staffNameKna;
    }
    public void setStaffNameKna(String staffNameKna) {
        this.staffNameKna = staffNameKna;
    }
    
    
    
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
     * 店コード1を取得します。
     * @return 店コード1
     */
    public String getMiseCd1() {
        return miseCd1;
    }
    /**
     * 店コード1を設定します。
     * @param miseCd1 店コード1
     */
    public void setMiseCd1(String miseCd1) {
        this.miseCd1 = miseCd1;
    }
    
    /**
     * スタッフ氏　（漢字）を取得します。
     * @return スタッフ氏　（漢字）
     */
    public String getStaffLNameKj() {
        return staffLNameKj;
    }
    /**
     * スタッフ氏　（漢字）を設定します。
     * @param staffLNameKj スタッフ氏　（漢字）
     */
    public void setStaffLNameKj(String staffLNameKj) {
        this.staffLNameKj = staffLNameKj;
    }
    
    /**
     * スタッフ名　（漢字）を取得します。
     * @return スタッフ名　（漢字）
     */
    public String getStaffFNameKj() {
        return staffFNameKj;
    }
    /**
     * スタッフ名　（漢字）を設定します。
     * @param staffFNameKj スタッフ名　（漢字）
     */
    public void setStaffFNameKj(String staffFNameKj) {
        this.staffFNameKj = staffFNameKj;
    }
    
    /**
     * スタッフ氏　（カナ）を取得します。
     * @return スタッフ氏　（カナ）
     */
    public String getStaffLNameKna() {
        return staffLNameKna;
    }
    /**
     * スタッフ氏　（カナ）を設定します。
     * @param staffLNameKna スタッフ氏　（カナ）
     */
    public void setStaffLNameKna(String staffLNameKna) {
        this.staffLNameKna = staffLNameKna;
    }
    
    /**
     * スタッフ名　（カナ）を取得します。
     * @return スタッフ名　（カナ）
     */
    public String getStaffFNameKna() {
        return staffFNameKna;
    }
    /**
     * スタッフ名　（カナ）を設定します。
     * @param staffFNameKna スタッフ名　（カナ）
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
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 申込済フラグを取得します。
     * @return 申込済フラグ
     */
    public String getEntryFlg() {
        return entryFlg;
    }
    /**
     * 申込済フラグを設定します。
     * @param entryFlg 申込済フラグ
     */
    public void setEntryFlg(String entryFlg) {
        this.entryFlg = entryFlg;
    }
    
    
    /**
     * Nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    
    
    public String getStaffSelect() {
        if(isNull(this.staffSelect)) return "";
        return staffSelect;
    }
    public void setStaffSelect(String staffSelect) {
        this.staffSelect = staffSelect;
    }
    public String getLastPgm() {
        if(isNull(this.lastPgm)) return "";
        return lastPgm;
    }
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    public String getLastUser() {
        if(isNull(this.lastUser)) return "";
        return lastUser;
    }
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    public String getJob() {
        if(isNull(this.job)) return "";
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getAbilityChk() {
        if(isNull(this.abilityChk)) return "";
        return abilityChk;
    }
    public void setAbilityChk(String abilityChk) {
        this.abilityChk = abilityChk;
    }
    public String getEmpExpMonth() {
        if(isNull(this.empExpMonth)) return "";
        return empExpMonth;
    }
    public void setEmpExpMonth(String empExpMonth) {
        this.empExpMonth = empExpMonth;
    }
    public String getEmpExpYear() {
        if(isNull(this.empExpYear)) return "";
        return empExpYear;
    }
    public void setEmpExpYear(String empExpYear) {
        this.empExpYear = empExpYear;
    }
    public String getExamChk() {
        if(isNull(this.examChk)) return "";
        return examChk;
    }
    public void setExamChk(String examChk) {
        this.examChk = examChk;
    }
    public String getExamNo() {
        if(isNull(this.examNo)) return "";
        return examNo;
    }
    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }
    public String getInterviewChk() {
        if(isNull(this.interviewChk)) return "";
        return interviewChk;
    }
    public void setInterviewChk(String interviewChk) {
        this.interviewChk = interviewChk;
    }
    public String getNote() {
        if(isNull(this.note)) return "";
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getPaExpMonth() {
        if(isNull(this.paExpMonth)) return "";
        return paExpMonth;
    }
    public void setPaExpMonth(String paExpMonth) {
        this.paExpMonth = paExpMonth;
    }
    public String getPaExpYear() {
        if(isNull(this.paExpYear)) return "";
        return paExpYear;
    }
    public void setPaExpYear(String paExpYear) {
        this.paExpYear = paExpYear;
    }

    
    
    public String getStateFlg() {
        return stateFlg;
    }
    public void setStateFlg(String stateFlg) {
        this.stateFlg = stateFlg;
    }
    
    public boolean isUpdateFlg() {
        return updateFlg;
    }
    public void setUpdateFlg(boolean updateFlg) {
        this.updateFlg = updateFlg;
    }



}
