package jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity;

import java.sql.Timestamp;

public class UIRenewalStaff {
    
    public static final String TABLE = "BM12STAF";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
    
    public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
    
    public static final String staffLNameKna_COLUMN = "STAFF_L_NAME_KNA";
    
    public static final String staffFNameKna_COLUMN = "STAFF_F_NAME_KNA";
    
    public static final String courseStatus_COLUMN = "COURSE_STATUS";
    
    public static final String compleCourseDt_COLUMN = "COMPLE_COURSE_DT";
    
    public static final String compleCourseCd_COLUMN = "COMPLE_COURSE_CD";
    
    public static final String compleCourseName_COLUMN = "COMPLE_COURSE_NAME";
    
    public static final String licenseDt_COLUMN = "LICENSE_DT";
    
    public static final String entryFlg_COLUMN = "ENTRY_FLG";
    
    public static final String dataStatusEt30_COLUMN = "DATA_STATUS_ET30";
    
    public static final String dataStatus_COLUMN = "DATA_STATUS";
    
    public static final String regFlg_COLUMN = "REG_FLG";
    
    public static final String shuryoFlg_COLUMN = "SHURYO_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    public static final String licenseUpDt_COLUMN = "LICENSE_UP_DT";

    public static final String licenseValidDt_COLUMN = "LICENSE_VALID_DT";

    public static final String areaDai_COLUMN = "AREA_DAI";

    public static final String sibuName_COLUMN = "SIBU_NAME";

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
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 会社名称
     */
    private String companyName;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * スタッフID
     */
    private String staffId;
    
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
     * 修了（予定）コース状況
     */
    private String courseStatus;
    
    /**
     * 修了（予定）コース修了年月
     */
    private String compleCourseDt;
    
    /**
     * 修了（予定）コースコード
     */
    private String compleCourseCd;
    
    /**
     * 修了（予定）コース名称
     */
    private String compleCourseName;
    
    /**
     * ライセンス取得年月
     */
    private String licenseDt;
    
    /**
     * エントリーフラグ
     */
    private String entryFlg;
    
    /**
     * データステータスET30用
     */
    private String dataStatusEt30;
    
    /**
     * データステータス
     */
    private String dataStatus;
    
    /**
     * 登録対象フラグ
     */
    private String regFlg;
    
    /**
     * 修了フラグ
     */
    private String shuryoFlg;
    
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
     * ライセンス発行期限年月
     */
    private String licenseValidDt;

    /**
     * ライセンス更新年月
     */
    private String licenseUpDt;

    /**
     * エリア大コード(支部取込コード)
     */
    private String areaDai;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    //////////////////////////////以下・セッター、ゲッター//////////////////////////////////////////////
    
	/**
	 * ライセンス発行期限年月の設定
	 * @return licenseValidDt を戻します。
	 */
	public String getLicenseValidDt() {
		return licenseValidDt;
	}
	/**
	 * ライセンス発行期限年月の設定
	 * @param licenseValidDt licenseValidDt を設定。
	 */
	public void setLicenseValidDt(String licenseValidDt) {
		this.licenseValidDt = licenseValidDt;
	}
	
	/**
	 * ライセンス更新年月の設定
	 * @return licenseUpDt を戻します。
	 */
	public String getLicenseUpDt() {
		return licenseUpDt;
	}
	/**
	 * ライセンス更新年月の設定
	 * @param licenseUpDt licenseUpDt を設定。
	 */
	public void setLicenseUpDt(String licenseUpDt) {
		this.licenseUpDt = licenseUpDt;
	}

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
     * 会社名称を取得します。
     * @return 会社名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名称を設定します。
     * @param companyName 会社名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
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
     * 修了（予定）コース状況を取得します。
     * @return 修了（予定）コース状況
     */
    public String getCourseStatus() {
        return courseStatus;
    }
    /**
     * 修了（予定）コース状況を設定します。
     * @param courseStatus 修了（予定）コース状況
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
    
    /**
     * 修了（予定）コース修了年月を取得します。
     * @return 修了（予定）コース修了年月
     */
    public String getCompleCourseDt() {
        return compleCourseDt;
    }
    /**
     * 修了（予定）コース修了年月を設定します。
     * @param compleCourseDt 修了（予定）コース修了年月
     */
    public void setCompleCourseDt(String compleCourseDt) {
        this.compleCourseDt = compleCourseDt;
    }
    
    /**
     * 修了（予定）コースコードを取得します。
     * @return 修了（予定）コースコード
     */
    public String getCompleCourseCd() {
        return compleCourseCd;
    }
    /**
     * 修了（予定）コースコードを設定します。
     * @param compleCourseCd 修了（予定）コースコード
     */
    public void setCompleCourseCd(String compleCourseCd) {
        this.compleCourseCd = compleCourseCd;
    }
    
    /**
     * 修了（予定）コース名称を取得します。
     * @return 修了（予定）コース名称
     */
    public String getCompleCourseName() {
        return compleCourseName;
    }
    /**
     * 修了（予定）コース名称を設定します。
     * @param compleCourseName 修了（予定）コース名称
     */
    public void setCompleCourseName(String compleCourseName) {
        this.compleCourseName = compleCourseName;
    }
    
    /**
     * ライセンス取得年月を取得します。
     * @return ライセンス取得年月
     */
    public String getLicenseDt() {
        return licenseDt;
    }
    /**
     * ライセンス取得年月を設定します。
     * @param licenseDt ライセンス取得年月
     */
    public void setLicenseDt(String licenseDt) {
        this.licenseDt = licenseDt;
    }
    
    /**
     * エントリーフラグを取得します。
     * @return エントリーフラグ
     */
    public String getEntryFlg() {
        return entryFlg;
    }
    /**
     * エントリーフラグを設定します。
     * @param entryFlg エントリーフラグ
     */
    public void setEntryFlg(String entryFlg) {
        this.entryFlg = entryFlg;
    }
    
    /**
     * データステータスET30用を取得します。
     * @return データステータスET30用
     */
    public String getDataStatusEt30() {
        return dataStatusEt30;
    }
    /**
     * データステータスET30用を設定します。
     * @param dataStatusEt30 データステータスET30用
     */
    public void setDataStatusEt30(String dataStatusEt30) {
        this.dataStatusEt30 = dataStatusEt30;
    }
    
    /**
     * データステータスを取得します。
     * @return データステータス
     */
    public String getDataStatus() {
        return dataStatus;
    }
    /**
     * データステータスを設定します。
     * @param dataStatus データステータス
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
    
    /**
     * 登録対象フラグを取得します。
     * @return 登録対象フラグ
     */
    public String getRegFlg() {
        return regFlg;
    }
    /**
     * 登録対象フラグを設定します。
     * @param regFlg 登録対象フラグ
     */
    public void setRegFlg(String regFlg) {
        this.regFlg = regFlg;
    }
        
    /**
     * 登録対象フラグを取得します。
     * @return 登録対象フラグ
     */
    public boolean getChangeRegFlg() {
        return "1".equals(regFlg);
    }
    /**
     * 登録対象フラグを設定します。
     * @param regFlg 登録対象フラグ
     */
    public void setChangeRegFlg(boolean  changeRegFlg) {
        if(changeRegFlg) {
            this.regFlg = "1";
        }
        else {
            this.regFlg = "0";
        }
    }
    
    /**
     * 修了フラグを取得します。
     * @return 修了フラグ
     */
    public String getShuryoFlg() {
        return shuryoFlg;
    }
    /**
     * 修了フラグを設定します。
     * @param shuryoFlg 修了フラグ
     */
    public void setShuryoFlg(String shuryoFlg) {
        this.shuryoFlg = shuryoFlg;
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

    /**
     * @return areaDai を戻します。
     */
    public String getAreaDai() {
        return areaDai;
    }
    /**
     * @param areaDai 設定する areaDai。
     */
    public void setAreaDai(String areaDai) {
        this.areaDai = areaDai;
    }
    /**
     * @return sibuName を戻します。
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * @param sibuName 設定する sibuName。
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }

}
