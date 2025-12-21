package jp.co.isid.mos.bird.storeinfo.miselumpextract.entity;

public class MstStaff {
    
    public static final String TABLE = "BT32MLKR";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String miseCd1_COLUMN = "MISE_CD_1";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
    
    public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
    
    public static final String sex_COLUMN = "SEX";
    
    public static final String situationKbn_COLUMN = "SITUATION_KBN";
    
    public static final String licenseKbn_COLUMN = "LICENSE_KBN";
    
    public static final String examNo_COLUMN = "EXAM_NO";
    
    public static final String reentryFlg_COLUMN = "REENTRY_FLG";
    
    public static final String entryCount_COLUMN = "ENTRY_COUNT";
    
    public static final String reentryBaseYear_COLUMN = "REENTRY_BASE_YEAR";
    
    public static final String totalLastYear_COLUMN = "TOTAL_LAST_YEAR";
    
    public static final String totalLastKai_COLUMN = "TOTAL_LAST_KAI";
    
    public static final String totalResult_COLUMN = "TOTAL_RESULT";
    
    public static final String sub1BaseYear_COLUMN = "SUB1_BASE_YEAR";
    
    public static final String sub1LastYear_COLUMN = "SUB1_LAST_YEAR";
    
    public static final String sub1LastKai_COLUMN = "SUB1_LAST_KAI";
    
    public static final String sub1LastResult_COLUMN = "SUB1_LAST_RESULT";
    
    public static final String sub1Result_COLUMN = "SUB1_RESULT";
    
    public static final String sub2BaseYear_COLUMN = "SUB2_BASE_YEAR";
    
    public static final String sub2LastYear_COLUMN = "SUB2_LAST_YEAR";
    
    public static final String sub2LastKai_COLUMN = "SUB2_LAST_KAI";
    
    public static final String sub2LastResult_COLUMN = "SUB2_LAST_RESULT";
    
    public static final String sub2Result_COLUMN = "SUB2_RESULT";
    
    public static final String sub3BaseYear_COLUMN = "SUB3_BASE_YEAR";
    
    public static final String sub3LastYear_COLUMN = "SUB3_LAST_YEAR";
    
    public static final String sub3LastKai_COLUMN = "SUB3_LAST_KAI";
    
    public static final String sub3LastResult_COLUMN = "SUB3_LAST_RESULT";
    
    public static final String sub3Result_COLUMN = "SUB3_RESULT";
    
    public static final String courseStatus_COLUMN = "COURSE_STATUS";
    
    public static final String compleCourseDt_COLUMN = "COMPLE_COURSE_DT";
    
    public static final String compleCourseCd_COLUMN = "COMPLE_COURSE_CD";
    
    public static final String compleCourseName_COLUMN = "COMPLE_COURSE_NAME";
    
    public static final String complePoint_COLUMN = "COMPLE_POINT";

    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String licenseNo_COLUMN = "LICENSE_NO";
    
    public static final String licenseDt_COLUMN = "LICENSE_DT";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
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
     * 店コード
     */
    private String miseCd1;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 店クローズ日
     */
    private String closeDt;
    
    /**
     * スタッフ氏　（漢字）
     */
    private String staffLNameKj;
    
    /**
     * スタッフ名　（漢字）
     */
    private String staffFNameKj;
    
    /**
     * 性別
     */
    private String sex;
    
    /**
     * 活動状況区分
     */
    private String situationKbn;
    
    /**
     * ライセンス種別
     */
    private String licenseKbn;
    
    /**
     * 受験番号
     */
    private String examNo;
    
    /**
     * 再エントリーフラグ
     */
    private String reentryFlg;
    
    /**
     * 総受験回数
     */
    private String entryCount;
    
    /**
     * 再エントリー基準年度
     */
    private String reentryBaseYear;
    
    /**
     * 受験年度
     */
    private String totalLastYear;
    
    /**
     * 受験回
     */
    private String totalLastKai;
    
    /**
     * 総合結果
     */
    private String totalResult;
    
    /**
     * 能力チェック  再エントリー基準年度
     */
    private String sub1BaseYear;
    
    /**
     * 能力チェック　最終受験年度
     */
    private String sub1LastYear;
    
    /**
     * 能力チェック　最終受験回
     */
    private String sub1LastKai;
    
    /**
     * 能力チェック  前回結果
     */
    private String sub1LastResult;
    
    /**
     * 能力チェック  結果
     */
    private String sub1Result;
    
    /**
     * 筆記テスト  再エントリー基準年度
     */
    private String sub2BaseYear;
    
    /**
     * 筆記テスト　最終受験年度
     */
    private String sub2LastYear;
    
    /**
     * 筆記テスト　最終受験回
     */
    private String sub2LastKai;
    
    /**
     * 筆記テスト  前回結果
     */
    private String sub2LastResult;
    
    /**
     * 筆記テスト  結果
     */
    private String sub2Result;
    
    /**
     * 面接  再エントリー基準年度
     */
    private String sub3BaseYear;
    
    /**
     * 面接　最終受験年度
     */
    private String sub3LastYear;
    
    /**
     * 面接　最終受験回
     */
    private String sub3LastKai;
    
    /**
     * 面接  前回結果
     */
    private String sub3LastResult;
    
    /**
     * 面接  結果
     */
    private String sub3Result;
    
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
     * 出張研修用点数
     */
    private String complePoint;
    
    /**
     * オーナー名称（漢字）
     */
    private String onerNameKj;
    
    /**
     * ライセンス認定番号
     */
    private String licenseNo;
    
    /**
     * ライセンス取得年月
     */
    private String licenseDt;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * スタッフIDを取得します。
     * @return スタッフID
     */
    public String getStaffId() {
        return convString(staffId);
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
        return convString(companyCd);
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
        return convString(onerCd);
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd1() {
        return convString(miseCd1);
    }
    /**
     * 店コードを設定します。
     * @param miseCd1 店コード
     */
    public void setMiseCd1(String miseCd1) {
        this.miseCd1 = miseCd1;
    }
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return convString(miseNameKj);
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 店クローズ日を取得します。
     * @return 店クローズ日
     */
    public String getCloseDt() {
        return convString(closeDt);
    }
    /**
     * 店クローズ日を設定します。
     * @param closeDt 店クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * スタッフ氏　（漢字）を取得します。
     * @return スタッフ氏　（漢字）
     */
    public String getStaffLNameKj() {
        return convString(staffLNameKj);
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
        return convString(staffFNameKj);
    }
    /**
     * スタッフ名　（漢字）を設定します。
     * @param staffFNameKj スタッフ名　（漢字）
     */
    public void setStaffFNameKj(String staffFNameKj) {
        this.staffFNameKj = staffFNameKj;
    }
    
    /**
     * 性別を取得します。
     * @return 性別
     */
    public String getSex() {
        return convString(sex);
    }
    /**
     * 性別を設定します。
     * @param sex 性別
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    /**
     * 活動状況区分を取得します。
     * @return 活動状況区分
     */
    public String getSituationKbn() {
        return convString(situationKbn);
    }
    /**
     * 活動状況区分を設定します。
     * @param situationKbn 活動状況区分
     */
    public void setSituationKbn(String situationKbn) {
        this.situationKbn = situationKbn;
    }
    
    /**
     * ライセンス種別を取得します。
     * @return ライセンス種別
     */
    public String getLicenseKbn() {
        return convString(licenseKbn);
    }
    /**
     * ライセンス種別を設定します。
     * @param licenseKbn ライセンス種別
     */
    public void setLicenseKbn(String licenseKbn) {
        this.licenseKbn = licenseKbn;
    }
    
    /**
     * 受験番号を取得します。
     * @return 受験番号
     */
    public String getExamNo() {
        return convString(examNo);
    }
    /**
     * 受験番号を設定します。
     * @param examNo 受験番号
     */
    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }
    
    /**
     * 再エントリーフラグを取得します。
     * @return 再エントリーフラグ
     */
    public String getReentryFlg() {
        return convString(reentryFlg);
    }
    /**
     * 再エントリーフラグを設定します。
     * @param reentryFlg 再エントリーフラグ
     */
    public void setReentryFlg(String reentryFlg) {
        this.reentryFlg = reentryFlg;
    }
    
    /**
     * 総受験回数を取得します。
     * @return 総受験回数
     */
    public String getEntryCount() {
        return convString(entryCount);
    }
    /**
     * 総受験回数を設定します。
     * @param entryCount 総受験回数
     */
    public void setEntryCount(String entryCount) {
        this.entryCount = entryCount;
    }
    
    /**
     * 再エントリー基準年度を取得します。
     * @return 再エントリー基準年度
     */
    public String getReentryBaseYear() {
        return convString(reentryBaseYear);
    }
    /**
     * 再エントリー基準年度を設定します。
     * @param reentryBaseYear 再エントリー基準年度
     */
    public void setReentryBaseYear(String reentryBaseYear) {
        this.reentryBaseYear = reentryBaseYear;
    }
    
    /**
     * 受験年度を取得します。
     * @return 受験年度
     */
    public String getTotalLastYear() {
        return convString(totalLastYear);
    }
    /**
     * 受験年度を設定します。
     * @param totalLastYear 受験年度
     */
    public void setTotalLastYear(String totalLastYear) {
        this.totalLastYear = totalLastYear;
    }
    
    /**
     * 受験回を取得します。
     * @return 受験回
     */
    public String getTotalLastKai() {
        return convString(totalLastKai);
    }
    /**
     * 受験回を設定します。
     * @param totalLastKai 受験回
     */
    public void setTotalLastKai(String totalLastKai) {
        this.totalLastKai = totalLastKai;
    }
    
    /**
     * 総合結果を取得します。
     * @return 総合結果
     */
    public String getTotalResult() {
        return convString(totalResult);
    }
    /**
     * 総合結果を設定します。
     * @param totalResult 総合結果
     */
    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }
    
    /**
     * 能力チェック  再エントリー基準年度を取得します。
     * @return 能力チェック  再エントリー基準年度
     */
    public String getSub1BaseYear() {
        return convString(sub1BaseYear);
    }
    /**
     * 能力チェック  再エントリー基準年度を設定します。
     * @param sub1BaseYear 能力チェック  再エントリー基準年度
     */
    public void setSub1BaseYear(String sub1BaseYear) {
        this.sub1BaseYear = sub1BaseYear;
    }
    
    /**
     * 能力チェック　最終受験年度を取得します。
     * @return 能力チェック　最終受験年度
     */
    public String getSub1LastYear() {
        return convString(sub1LastYear);
    }
    /**
     * 能力チェック　最終受験年度を設定します。
     * @param sub1LastYear 能力チェック　最終受験年度
     */
    public void setSub1LastYear(String sub1LastYear) {
        this.sub1LastYear = sub1LastYear;
    }
    
    /**
     * 能力チェック　最終受験回を取得します。
     * @return 能力チェック　最終受験回
     */
    public String getSub1LastKai() {
        return convString(sub1LastKai);
    }
    /**
     * 能力チェック　最終受験回を設定します。
     * @param sub1LastKai 能力チェック　最終受験回
     */
    public void setSub1LastKai(String sub1LastKai) {
        this.sub1LastKai = sub1LastKai;
    }
    
    /**
     * 能力チェック  前回結果を取得します。
     * @return 能力チェック  前回結果
     */
    public String getSub1LastResult() {
        return convString(sub1LastResult);
    }
    /**
     * 能力チェック  前回結果を設定します。
     * @param sub1LastResult 能力チェック  前回結果
     */
    public void setSub1LastResult(String sub1LastResult) {
        this.sub1LastResult = sub1LastResult;
    }
    
    /**
     * 能力チェック  結果を取得します。
     * @return 能力チェック  結果
     */
    public String getSub1Result() {
        return convString(sub1Result);
    }
    /**
     * 能力チェック  結果を設定します。
     * @param sub1Result 能力チェック  結果
     */
    public void setSub1Result(String sub1Result) {
        this.sub1Result = sub1Result;
    }
    
    /**
     * 筆記テスト  再エントリー基準年度を取得します。
     * @return 筆記テスト  再エントリー基準年度
     */
    public String getSub2BaseYear() {
        return convString(sub2BaseYear);
    }
    /**
     * 筆記テスト  再エントリー基準年度を設定します。
     * @param sub2BaseYear 筆記テスト  再エントリー基準年度
     */
    public void setSub2BaseYear(String sub2BaseYear) {
        this.sub2BaseYear = sub2BaseYear;
    }
    
    /**
     * 筆記テスト　最終受験年度を取得します。
     * @return 筆記テスト　最終受験年度
     */
    public String getSub2LastYear() {
        return convString(sub2LastYear);
    }
    /**
     * 筆記テスト　最終受験年度を設定します。
     * @param sub2LastYear 筆記テスト　最終受験年度
     */
    public void setSub2LastYear(String sub2LastYear) {
        this.sub2LastYear = sub2LastYear;
    }
    
    /**
     * 筆記テスト　最終受験回を取得します。
     * @return 筆記テスト　最終受験回
     */
    public String getSub2LastKai() {
        return convString(sub2LastKai);
    }
    /**
     * 筆記テスト　最終受験回を設定します。
     * @param sub2LastKai 筆記テスト　最終受験回
     */
    public void setSub2LastKai(String sub2LastKai) {
        this.sub2LastKai = sub2LastKai;
    }
    
    /**
     * 筆記テスト  前回結果を取得します。
     * @return 筆記テスト  前回結果
     */
    public String getSub2LastResult() {
        return convString(sub2LastResult);
    }
    /**
     * 筆記テスト  前回結果を設定します。
     * @param sub2LastResult 筆記テスト  前回結果
     */
    public void setSub2LastResult(String sub2LastResult) {
        this.sub2LastResult = sub2LastResult;
    }
    
    /**
     * 筆記テスト  結果を取得します。
     * @return 筆記テスト  結果
     */
    public String getSub2Result() {
        return convString(sub2Result);
    }
    /**
     * 筆記テスト  結果を設定します。
     * @param sub2Result 筆記テスト  結果
     */
    public void setSub2Result(String sub2Result) {
        this.sub2Result = sub2Result;
    }
    
    /**
     * 面接  再エントリー基準年度を取得します。
     * @return 面接  再エントリー基準年度
     */
    public String getSub3BaseYear() {
        return convString(sub3BaseYear);
    }
    /**
     * 面接  再エントリー基準年度を設定します。
     * @param sub3BaseYear 面接  再エントリー基準年度
     */
    public void setSub3BaseYear(String sub3BaseYear) {
        this.sub3BaseYear = sub3BaseYear;
    }
    
    /**
     * 面接　最終受験年度を取得します。
     * @return 面接　最終受験年度
     */
    public String getSub3LastYear() {
        return convString(sub3LastYear);
    }
    /**
     * 面接　最終受験年度を設定します。
     * @param sub3LastYear 面接　最終受験年度
     */
    public void setSub3LastYear(String sub3LastYear) {
        this.sub3LastYear = sub3LastYear;
    }
    
    /**
     * 面接　最終受験回を取得します。
     * @return 面接　最終受験回
     */
    public String getSub3LastKai() {
        return convString(sub3LastKai);
    }
    /**
     * 面接　最終受験回を設定します。
     * @param sub3LastKai 面接　最終受験回
     */
    public void setSub3LastKai(String sub3LastKai) {
        this.sub3LastKai = sub3LastKai;
    }
    
    /**
     * 面接  前回結果を取得します。
     * @return 面接  前回結果
     */
    public String getSub3LastResult() {
        return convString(sub3LastResult);
    }
    /**
     * 面接  前回結果を設定します。
     * @param sub3LastResult 面接  前回結果
     */
    public void setSub3LastResult(String sub3LastResult) {
        this.sub3LastResult = sub3LastResult;
    }
    
    /**
     * 面接  結果を取得します。
     * @return 面接  結果
     */
    public String getSub3Result() {
        return convString(sub3Result);
    }
    /**
     * 面接  結果を設定します。
     * @param sub3Result 面接  結果
     */
    public void setSub3Result(String sub3Result) {
        this.sub3Result = sub3Result;
    }
    
    /**
     * 修了（予定）コース状況を取得します。
     * @return 修了（予定）コース状況
     */
    public String getCourseStatus() {
        return convString(courseStatus);
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
        return convString(compleCourseDt);
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
        return convString(compleCourseCd);
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
        return convString(compleCourseName);
    }
    /**
     * 修了（予定）コース名称を設定します。
     * @param compleCourseName 修了（予定）コース名称
     */
    public void setCompleCourseName(String compleCourseName) {
        this.compleCourseName = compleCourseName;
    }
    
    /**
     * 出張研修用点数を取得します。
     * @return 出張研修用点数
     */
    public String getComplePoint() {
        return convString(complePoint);
    }
    /**
     * 出張研修用点数を設定します。
     * @param complePoint 出張研修用点数
     */
    public void setComplePoint(String complePoint) {
        this.complePoint = complePoint;
    }
    
    /**
     * オーナー名称（漢字）を取得します。
     * @return オーナー名称（漢字）
     */
    public String getOnerNameKj() {
        return convString(onerNameKj);
    }
    /**
     * オーナー名称（漢字）を設定します。
     * @param onerNameKj オーナー名称（漢字）
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * ライセンス認定番号を取得します。
     * @return ライセンス認定番号
     */
    public String getLicenseNo() {
        return convString(licenseNo);
    }
    /**
     * ライセンス認定番号を設定します。
     * @param licenseNo ライセンス認定番号
     */
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
    
    /**
     * ライセンス取得年月を取得します。
     * @return ライセンス取得年月
     */
    public String getLicenseDt() {
        return convString(licenseDt);
    }
    /**
     * ライセンス取得年月を設定します。
     * @param licenseDt ライセンス取得年月
     */
    public void setLicenseDt(String licenseDt) {
        this.licenseDt = licenseDt;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return convString(sibuCd);
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return convString(sibuName);
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * スタッフ名　(苗字 + 名前）を取得します。
     * @return スタッフ名　(苗字 + 名前）
     */
    public String getCsvStaffName() {
        
        String csvStaffName = getStaffLNameKj() + getStaffFNameKj();
        
        return csvStaffName;
    }
    
    /**
     * 総合結果（CSV表示用）を取得します。
     * @return 総合結果（CSV表示用）
     */
    public String getCsvTotalResult() {
        String name = "";
        
        String code = getTotalResult();
        if(code.equals("0")){
            name = "不合格";
            
        } else if(code.equals("1")){
            name = "合格";
            
        } else if(code.equals("2")){
            name = "ライセンス発行済";
            
        } else if(code.equals("9")){
            name = "未受験";
        }
        
        return name;
    }
    
    /**
     * 能力チェック  結果（CSV表示用）を取得します。
     * @return 能力チェック  結果（CSV表示用）
     */
    public String getCsvSub1Result() {
        
        return convResult(getSub1Result());
    }
    
    /**
     * 筆記テスト  結果（CSV表示用）を取得します。
     * @return 筆記テスト  結果（CSV表示用）
     */
    public String getCsvSub2Result() {
        return convResult(getSub2Result());
    }
    
    /**
     * 面接  結果（CSV表示用）を取得します。
     * @return 面接  結果（CSV表示用）
     */
    public String getCsvSub3Result() {
        return convResult(getSub3Result());
    }
    
    /**
     * 修了（予定）コース状況（CSV表示用）を取得します。
     * @return 修了（予定）コース状況（CSV表示用）
     */
    public String getCsvCourseStatus() {
        String name = "";       
        String code = getCourseStatus();
        
        if(code.equals("0")){
            name = "未修了";
            
        } else if(code.equals("1")){
            name = "修了";
            
        } else if(code.equals("2")){
            name = "予定";
            
        } 
        
        return name;
    }
    
    private String convResult(String code) {
        String name = "";
        
        if(code.equals("0")){
            name = "不合格";
            
        } else if(code.equals("1")){
            name = "合格";
            
        } else if(code.equals("2")){
            name = "免除";
            
        } else if(code.equals("9")){
            name = "未受験";
        }
        
        return name;
    }
    
    /**
     * 活動状況区分（CSV表示用）を取得します。
     * @return 活動状況区分（CSV表示用）
     */
    public String getCsvSituationKbn() {
        
        String status = "";
        
        if ("0".equals(getSituationKbn())) {
            status = "活動中";
        }
        else if ("1".equals(getSituationKbn())) {
            status = "休職中";
            
        }else if ("2".equals(getSituationKbn())) {
            status = "退職";
        }
        return status;
    }
 
    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
    
    
}
