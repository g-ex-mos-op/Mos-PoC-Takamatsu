package jp.co.isid.mos.bird.entry.ownerchange.entity;

import java.sql.Timestamp;

public class MstStaffInfo {
    
    public static final String TABLE = "BM12STAF";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String oldOnerCd_COLUMN = "OLD_ONER_CD";
    
    public static final String miseCd1_COLUMN = "MISE_CD_1";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
    
    public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
    
    public static final String staffLNameKn_COLUMN = "STAFF_L_NAME_KN";
    
    public static final String staffFNameKn_COLUMN = "STAFF_F_NAME_KN";
    
    public static final String situationKbn_COLUMN = "SITUATION_KBN";

    public static final String moveDt_COLUMN = "MOVE_DT";
    
    public static final String licenseNo_COLUMN = "LICENSE_NO";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * スタッフＩＤ
     */
    private String staffId;
    
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
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * クローズ日
     */
    private String closeDt;
    /**
     * スタッフ氏（漢字）
     */
    private String staffLNameKj;
    
    /**
     * スタッフ名（漢字）
     */
    private String staffFNameKj;
    
    /**
     * スタッフ氏（カナ）
     */
    private String staffLNameKn;
    
    /**
     * スタッフ名（カナ）
     */
    private String staffFNameKn;
    
    /**
     * 活動状況区分
     */
    private String situationKbn;
    
    private String moveDt;
    
    /**
     * ライセンス認定番号
     */
    private String licenseNo;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * スタッフＩＤを取得します。
     * @return スタッフＩＤ
     */
    public String getStaffId() {
        return staffId;
    }
    /**
     * スタッフＩＤを設定します。
     * @param staffId スタッフＩＤ
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
     * スタッフ氏（漢字）を取得します。
     * @return スタッフ氏（漢字）
     */
    public String getStaffLNameKj() {
        return staffLNameKj;
    }
    /**
     * スタッフ氏（漢字）を設定します。
     * @param staffLNameKj スタッフ氏（漢字）
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
    public String getStaffLNameKn() {
        return staffLNameKn;
    }
    /**
     * スタッフ氏（カナ）を設定します。
     * @param staffLNameKn スタッフ氏（カナ）
     */
    public void setStaffLNameKn(String staffLNameKn) {
        this.staffLNameKn = staffLNameKn;
    }
    
    /**
     * スタッフ名（カナ）を取得します。
     * @return スタッフ名（カナ）
     */
    public String getStaffFNameKn() {
        return staffFNameKn;
    }
    /**
     * スタッフ名（カナ）を設定します。
     * @param staffFNameKn スタッフ名（カナ）
     */
    public void setStaffFNameKn(String staffFNameKn) {
        this.staffFNameKn = staffFNameKn;
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
     * ライセンス認定番号を取得します。
     * @return ライセンス認定番号
     */
    public String getLicenseNo() {
        return licenseNo;
    }
    /**
     * ライセンス認定番号を設定します。
     * @param licenseNo ライセンス認定番号
     */
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
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
     * 修正時タイムスタンプを取得します。
     * @return 修正時タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時タイムスタンプを設定します。
     * @param lastTmsp 修正時タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * クローズ日を取得します。
     * @return
     */
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * クローズ日を設定します。
     * @param closeDt クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    public String getMoveDt() {
        return moveDt;
    }
    public void setMoveDt(String moveDt) {
        this.moveDt = moveDt;
    }
}
