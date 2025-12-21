package jp.co.isid.mos.bird.storemanage.staffregist.entity;

/**
 * スタッフライセンス情報
 * 
 * 作成日:2009/08/27
 * @author xkinu
 *
 */
public class UIStaffLicensInfo {
    
    public static final String TABLE = "BM12STAF";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String areaDai_COLUMN = "AREA_DAI";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String miseCd1_COLUMN = "MISE_CD_1";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String staffName_COLUMN = "STAFF_NAME";
    
    public static final String sex_COLUMN = "SEX";
    
    public static final String job_COLUMN = "JOB";
    
    public static final String situation_COLUMN = "SITUATION";
    
    public static final String licenseNo_COLUMN = "LICENSE_NO";
    
    public static final String licenseDt_COLUMN = "LICENSE_DT";
    
    public static final String kCourseStatus_COLUMN = "K_COURSE_STATUS";
    
    public static final String firstLicenseYear_COLUMN = "FIRST_LICENSE_YEAR";
    
    public static final String firstLicenseKai_COLUMN = "FIRST_LICENSE_KAI";
    
    public static final String totalResult_COLUMN = "TOTAL_RESULT";
    
    public static final String zCourseStatus_COLUMN = "Z_COURSE_STATUS";
    
    public static final String note_COLUMN = "NOTE";
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 支部取り込みコード
     */
    private String areaDai;
    
    /**
     * 支部取り込み名称
     */
    private String sibuName;
    
    /**
     * 店コード1
     */
    private String miseCd1;
    
    /**
     * 店コード1名称
     */
    private String miseNameKj;
    
    /**
     * スタッフID
     */
    private String staffId;
    
    /**
     * スタッフ名称
     */
    private String staffName;
    
    /**
     * 性別
     */
    private String sex;
    
    /**
     * 職位
     */
    private String job;
    
    /**
     * 活動状況
     */
    private String situation;
    
    /**
     * ライセンス番号
     */
    private String licenseNo;
    
    /**
     * 取得年月
     */
    private String licenseDt;
    
    /**
     * 当年度更新研修
     */
    private String kCourseStatus;
    
    /**
     * 初回受験年度
     */
    private String firstLicenseYear;
    
    /**
     * 初回受験回
     */
    private String firstLicenseKai;
    
    /**
     * 総合結果
     */
    private String totalResult;
    
    /**
     * 前提研修
     */
    private String zCourseStatus;
    /**
     * 備考
     */
    private String note;
    /**
	 * @return クラス変数note を戻します。
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note を クラス変数noteへ設定します。
	 */
	public void setNote(String note) {
		this.note = note;
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
     * 支部取り込みコードを取得します。
     * @return 支部取り込みコード
     */
    public String getAreaDai() {
        return areaDai;
    }
    /**
     * 支部取り込みコードを設定します。
     * @param areaDai 支部取り込みコード
     */
    public void setAreaDai(String areaDai) {
        this.areaDai = areaDai;
    }
    
    /**
     * 支部取り込み名称を取得します。
     * @return 支部取り込み名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部取り込み名称を設定します。
     * @param sibuName 支部取り込み名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
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
     * 店コード1名称を取得します。
     * @return 店コード1名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店コード1名称を設定します。
     * @param miseNameKj 店コード1名称
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
     * スタッフ名称を取得します。
     * @return スタッフ名称
     */
    public String getStaffName() {
        return staffName;
    }
    /**
     * スタッフ名称を設定します。
     * @param staffName スタッフ名称
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
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
     * 活動状況を取得します。
     * @return 活動状況
     */
    public String getSituation() {
        return situation;
    }
    /**
     * 活動状況を設定します。
     * @param situationKbn 活動状況
     */
    public void setSituation(String situation) {
        this.situation = situation;
    }
    
    /**
     * ライセンス番号を取得します。
     * @return ライセンス番号
     */
    public String getLicenseNo() {
        return licenseNo;
    }
    /**
     * ライセンス番号を設定します。
     * @param licenseNo ライセンス番号
     */
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
    
    /**
     * 取得年月を取得します。
     * @return 取得年月
     */
    public String getLicenseDt() {
        return licenseDt;
    }
    /**
     * 取得年月を設定します。
     * @param licenseDt 取得年月
     */
    public void setLicenseDt(String licenseDt) {
        this.licenseDt = licenseDt;
    }
    
    /**
     * 当年度更新研修を取得します。
     * @return 当年度更新研修
     */
    public String getKCourseStatus() {
        return kCourseStatus;
    }
    /**
     * 当年度更新研修を設定します。
     * @param status 当年度更新研修
     */
    public void setKCourseStatus(String status) {
        this.kCourseStatus = status;
    }
    
    /**
     * 初回受験年度を取得します。
     * @return 初回受験年度
     */
    public String getFirstLicenseYear() {
        return firstLicenseYear;
    }
    /**
     * 初回受験年度を設定します。
     * @param firstLicenseYear 初回受験年度
     */
    public void setFirstLicenseYear(String firstLicenseYear) {
        this.firstLicenseYear = firstLicenseYear;
    }
    
    /**
     * 初回受験回を取得します。
     * @return 初回受験回
     */
    public String getFirstLicenseKai() {
        return firstLicenseKai;
    }
    /**
     * 初回受験回を設定します。
     * @param firstLicenseKai 初回受験回
     */
    public void setFirstLicenseKai(String firstLicenseKai) {
        this.firstLicenseKai = firstLicenseKai;
    }
    
    /**
     * 総合結果を取得します。
     * @return 総合結果
     */
    public String getTotalResult() {
        return totalResult;
    }
    /**
     * 総合結果を設定します。
     * @param totalResult 総合結果
     */
    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }
    
    /**
     * 前提研修を取得します。
     * @return 前提研修
     */
    public String getZCourseStatus() {
        return zCourseStatus;
    }
    /**
     * 前提研修を設定します。
     * @param status 前提研修
     */
    public void setZCourseStatus(String status) {
        this.zCourseStatus = status;
    }
    
}
