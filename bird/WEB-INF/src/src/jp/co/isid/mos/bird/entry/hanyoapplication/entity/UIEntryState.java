package jp.co.isid.mos.bird.entry.hanyoapplication.entity;

import java.sql.Timestamp;

public class UIEntryState {
    
    public static final String TABLE = "BT22ENKN";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String courseCd_COLUMN = "COURSE_CD";
    
    public static final String empExpYear_COLUMN = "EMP_EXP_YEAR";
    
    public static final String empExpMonth_COLUMN = "EMP_EXP_MONTH";
    
    public static final String paExpYear_COLUMN = "PA_EXP_YEAR";
    
    public static final String paExpMonth_COLUMN = "PA_EXP_MONTH";
    
    public static final String job_COLUMN = "JOB";
    
    public static final String guideKbn_COLUMN = "GUIDE_KBN";
    
    public static final String guideName_COLUMN = "GUIDE_NAME";
    
    public static final String guideZip_COLUMN = "GUIDE_ZIP";
    
    public static final String guideAdrs1_COLUMN = "GUIDE_ADRS1";
    
    public static final String guideAdrs2_COLUMN = "GUIDE_ADRS2";
    
    public static final String guideAdrs3_COLUMN = "GUIDE_ADRS3";
    
    public static final String rintenFlg_COLUMN = "RINTEN_FLG";
    
    public static final String otherFlg1_COLUMN = "OTHER_FLG1";
    
    public static final String otherFlg2_COLUMN = "OTHER_FLG2";
    
    public static final String otherFlg3_COLUMN = "OTHER_FLG3";
    
    public static final String otherFlg4_COLUMN = "OTHER_FLG4";
    
    public static final String otherFlg5_COLUMN = "OTHER_FLG5";
    
    public static final String bossName_COLUMN = "BOSS_NAME";
    
    public static final String bossGroup_COLUMN = "BOSS_GROUP";
    
    public static final String bossJobType_COLUMN = "BOSS_JOB_TYPE";
    
    public static final String bossComment_COLUMN = "BOSS_COMMENT";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String staffSelectCOLUMN = "STAFF_SELECT";
    
    public static final String nameKnaInput_COLUMN = "NAME_KNA_INPUT";
    
    public static final String sex_COLUMN = "SEX";
    
    /**
     * エントリーコード
     */
    private String entryCd;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * スタッフID
     */
    private String staffId;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * コースコード
     */
    private String courseCd;
    
    /**
     * 社員暦　年
     */
    private String empExpYear;
    
    /**
     * 社員暦　月
     */
    private String empExpMonth;
    
    /**
     * ＰＡ暦　年
     */
    private String paExpYear;
    
    /**
     * ＰＡ暦　月
     */
    private String paExpMonth;
    
    /**
     * 職位
     */
    private String job;
    
    /**
     * 受講案内送付先区分
     */
    private String guideKbn;
    
    /**
     * 受講案内送付先名
     */
    private String guideName;
    
    /**
     * 受講案内送付先郵便番号
     */
    private String guideZip;
    
    /**
     * 受講案内送付先住所１
     */
    private String guideAdrs1;
    
    /**
     * 受講案内送付先住所２
     */
    private String guideAdrs2;
    
    /**
     * 受講案内送付先住所３
     */
    private String guideAdrs3;
    
    /**
     * 臨店実習コースフラグ
     */
    private String rintenFlg;
    
    /**
     * その他フラグ1
     */
    private String otherFlg1;
    
    /**
     * その他フラグ2
     */
    private String otherFlg2;
    
    /**
     * その他フラグ3
     */
    private String otherFlg3;
    
    /**
     * その他フラグ4
     */
    private String otherFlg4;
    
    /**
     * その他フラグ5
     */
    private String otherFlg5;
    
    /**
     * 上司名
     */
    private String bossName;
    
    /**
     * 上司所属
     */
    private String bossGroup;
    
    /**
     * 上司職位
     */
    private String bossJobType;
    
    /**
     * 上司コメント
     */
    private String bossComment;
    
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
     * スタッフ選択プルダウン用
     */
    private String staffSelect;
    
    /**
     * スタッフ名 カタカナ入力欄
     */
    private String nameKnaInput;
    
    /**
     * その他送付先（入力欄）
     */
    private String guideNameInput;
    
    /**
     * 検索時のスタッフID
     */
    private String selectedStaffId;
    /**
     * 性別
     */
    private String sex;
    
    /**
     * 受講案内送付先区分名称
     */
    private String guideKbnName;
    
    /**
     * スタッフ名称（確認画面表示用)
     */
    private String staffName;
    
    /**
     * 新規行フラグ true:新規
     */
    private boolean insertFlg = false;
    
//  add start xkhata 20060822 その他readonly設定
    /**
     * その他時のテキストの状態
     */
    private boolean readOnlyFlg = true;;

//  add end

    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
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
     * コースコードを取得します。
     * @return コースコード
     */
    public String getCourseCd() {
        return changeNullToBlank(courseCd);
    }
    /**
     * コースコードを設定します。
     * @param courseCd コースコード
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }
    
    /**
     * 社員暦　年を取得します。
     * @return 社員暦　年
     */
    public String getEmpExpYear() {
        return empExpYear;
    }
    /**
     * 社員暦　年を設定します。
     * @param empExpYear 社員暦　年
     */
    public void setEmpExpYear(String empExpYear) {
        this.empExpYear = empExpYear;
    }
    
    /**
     * 社員暦　月を取得します。
     * @return 社員暦　月
     */
    public String getEmpExpMonth() {
        return empExpMonth;
    }
    /**
     * 社員暦　月を設定します。
     * @param empExpMonth 社員暦　月
     */
    public void setEmpExpMonth(String empExpMonth) {
        this.empExpMonth = empExpMonth;
    }
    
    /**
     * ＰＡ暦　年を取得します。
     * @return ＰＡ暦　年
     */
    public String getPaExpYear() {
        return paExpYear;
    }
    /**
     * ＰＡ暦　年を設定します。
     * @param paExpYear ＰＡ暦　年
     */
    public void setPaExpYear(String paExpYear) {
        this.paExpYear = paExpYear;
    }
    
    /**
     * ＰＡ暦　月を取得します。
     * @return ＰＡ暦　月
     */
    public String getPaExpMonth() {
        return paExpMonth;
    }
    /**
     * ＰＡ暦　月を設定します。
     * @param paExpMonth ＰＡ暦　月
     */
    public void setPaExpMonth(String paExpMonth) {
        this.paExpMonth = paExpMonth;
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
     * 受講案内送付先区分を取得します。
     * @return 受講案内送付先区分
     */
    public String getGuideKbn() {
        return guideKbn;
    }
    /**
     * 受講案内送付先区分を設定します。
     * @param guideKbn 受講案内送付先区分
     */
    public void setGuideKbn(String guideKbn) {
        this.guideKbn = guideKbn;
    }
    
    /**
     * 受講案内送付先名を取得します。
     * @return 受講案内送付先名
     */
    public String getGuideName() {
        if (guideName == null) {
            guideName = "";
        }
        return guideName;
    }
    /**
     * 受講案内送付先名を設定します。
     * @param guideName 受講案内送付先名
     */
    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }
    
    /**
     * 受講案内送付先郵便番号を取得します。
     * @return 受講案内送付先郵便番号
     */
    public String getGuideZip() {
        if (guideZip == null) {
            guideZip = "";
        }
        return guideZip;
    }
    /**
     * 受講案内送付先郵便番号を設定します。
     * @param guideZip 受講案内送付先郵便番号
     */
    public void setGuideZip(String guideZip) {
        this.guideZip = guideZip;
    }
    
    /**
     * 受講案内送付先住所を取得します。
     * @return 受講案内送付先住所
     */
    public String getGuideAdrs1() {
        if (guideAdrs1 == null) {
            guideAdrs1 = "";
        }
        return guideAdrs1;
    }
    /**
     * 受講案内送付先住所を設定します。
     * @param guideAdrs 受講案内送付先住所
     */
    public void setGuideAdrs1(String guideAdrs) {
        this.guideAdrs1 = guideAdrs;
    }
    
    /**
     * 臨店実習コースフラグを取得します。
     * @return 臨店実習コースフラグ
     */
    public String getRintenFlg() {
        return rintenFlg;
    }
    /**
     * 臨店実習コースフラグを設定します。
     * @param rintenFlg 臨店実習コースフラグ
     */
    public void setRintenFlg(String rintenFlg) {
        this.rintenFlg = rintenFlg;
    }
    
    /**
     * その他フラグ1を取得します。
     * @return その他フラグ1
     */
    public String getOtherFlg1() {
        return changeNullToBlank(otherFlg1);
    }
    /**
     * その他フラグ1を設定します。
     * @param otherFlg1 その他フラグ1
     */
    public void setOtherFlg1(String otherFlg1) {
        this.otherFlg1 = otherFlg1;
    }
    
    /**
     * その他フラグ2を取得します。
     * @return その他フラグ2
     */
    public String getOtherFlg2() {
        return changeNullToBlank(otherFlg2);
    }
    /**
     * その他フラグ2を設定します。
     * @param otherFlg2 その他フラグ2
     */
    public void setOtherFlg2(String otherFlg2) {
        this.otherFlg2 = otherFlg2;
    }
    
    /**
     * その他フラグ3を取得します。
     * @return その他フラグ3
     */
    public String getOtherFlg3() {
        return changeNullToBlank(otherFlg3);
    }
    /**
     * その他フラグ3を設定します。
     * @param otherFlg3 その他フラグ3
     */
    public void setOtherFlg3(String otherFlg3) {
        this.otherFlg3 = otherFlg3;
    }
    
    /**
     * その他フラグ4を取得します。
     * @return その他フラグ4
     */
    public String getOtherFlg4() {
        return changeNullToBlank(otherFlg4);
    }
    /**
     * その他フラグ4を設定します。
     * @param otherFlg4 その他フラグ4
     */
    public void setOtherFlg4(String otherFlg4) {
        this.otherFlg4 = otherFlg4;
    }
    
    /**
     * その他フラグ5を取得します。
     * @return その他フラグ5
     */
    public String getOtherFlg5() {
        return changeNullToBlank(otherFlg5);
    }
    /**
     * その他フラグ5を設定します。
     * @param otherFlg5 その他フラグ5
     */
    public void setOtherFlg5(String otherFlg5) {
        this.otherFlg5 = otherFlg5;
    }
    
    /**
     * 上司名を取得します。
     * @return 上司名
     */
    public String getBossName() {
        return changeNullToBlank(bossName);
    }
    /**
     * 上司名を設定します。
     * @param bossName 上司名
     */
    public void setBossName(String bossName) {
        this.bossName = bossName;
    }
    
    /**
     * 上司所属を取得します。
     * @return 上司所属
     */
    public String getBossGroup() {
        return changeNullToBlank(bossGroup);
    }
    /**
     * 上司所属を設定します。
     * @param bossGroup 上司所属
     */
    public void setBossGroup(String bossGroup) {
        this.bossGroup = bossGroup;
    }
    
    /**
     * 上司職位を取得します。
     * @return 上司職位
     */
    public String getBossJobType() {
        return changeNullToBlank(bossJobType);
    }
    /**
     * 上司職位を設定します。
     * @param bossJobType 上司職位
     */
    public void setBossJobType(String bossJobType) {
        this.bossJobType = bossJobType;
    }
    
    /**
     * 上司コメントを取得します。
     * @return 上司コメント
     */
    public String getBossComment() {
        return changeNullToBlank(bossComment);
    }
    /**
     * 上司コメントを設定します。
     * @param bossComment 上司コメント
     */
    public void setBossComment(String bossComment) {
        this.bossComment = bossComment;
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
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
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
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    public String getNameKnaInput() {
        return nameKnaInput;
    }
    public void setNameKnaInput(String nameKnaInput) {
        this.nameKnaInput = nameKnaInput;
    }
    public String getSex() {
        if (sex == null || "".equals(sex.trim())) {
            sex = "0";
        }
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getStaffSelect() {
        return staffSelect;
    }
    public void setStaffSelect(String staffSelect) {
        this.staffSelect = staffSelect;
    }
    public String getGuideKbnName() {
        return guideKbnName;
    }
    public void setGuideKbnName(String guideKbnName) {
        this.guideKbnName = guideKbnName;
    }
    public String getGuideNameInput() {
        return guideNameInput;
    }
    public void setGuideNameInput(String guideNameInput) {
        this.guideNameInput = guideNameInput;
    }
    public String getGuideAdrs2() {
        if (guideAdrs2 == null) {
            guideAdrs2 = "";
        }
        return guideAdrs2;
    }
    public void setGuideAdrs2(String guideAdrs2) {
        this.guideAdrs2 = guideAdrs2;
    }
    public String getGuideAdrs3() {
        if (guideAdrs3 == null) {
            guideAdrs3 = "";
        }
        return guideAdrs3;
    }
    public void setGuideAdrs3(String guideAdrs3) {
        this.guideAdrs3 = guideAdrs3;
    }
    public String getSelectedStaffId() {
        return selectedStaffId;
    }
    public void setSelectedStaffId(String selectedStaffId) {
        this.selectedStaffId = selectedStaffId;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public boolean isInsertFlg() {
        return insertFlg;
    }
    public void setInsertFlg(boolean insertFlg) {
        this.insertFlg = insertFlg;
    }

    public boolean getReadOnlyFlg() {
        return readOnlyFlg;
    }
    public void setReadOnlyFlg( boolean readOnlyFlg ) {
        this.readOnlyFlg = readOnlyFlg;
    }
    
    private String changeNullToBlank(String value) {
        return value == null ? "" : value;
    }
}
