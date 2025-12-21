package jp.co.isid.mos.bird.entry.hanyoapplication.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TrnMasterResult {
    
    public static final String TABLE = "BT24MLKJ";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
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
    
    public static final String sub4BaseYear_COLUMN = "SUB4_BASE_YEAR";
    
    public static final String sub4LastYear_COLUMN = "SUB4_LAST_YEAR";
    
    public static final String sub4LastKai_COLUMN = "SUB4_LAST_KAI";
    
    public static final String sub4LastResult_COLUMN = "SUB4_LAST_RESULT";
    
    public static final String sub4Result_COLUMN = "SUB4_RESULT";
    
    public static final String sub5BaseYear_COLUMN = "SUB5_BASE_YEAR";
    
    public static final String sub5LastYear_COLUMN = "SUB5_LAST_YEAR";
    
    public static final String sub5LastKai_COLUMN = "SUB5_LAST_KAI";
    
    public static final String sub5LastResult_COLUMN = "SUB5_LAST_RESULT";
    
    public static final String sub5Result_COLUMN = "SUB5_RESULT";
    
    public static final String courseStatus_COLUMN = "COURSE_STATUS";
    
    public static final String compleCourseDt_COLUMN = "COMPLE_COURSE_DT";
    
    public static final String compleCourseCd_COLUMN = "COMPLE_COURSE_CD";
    
    public static final String compleCourseName_COLUMN = "COMPLE_COURSE_NAME";
    
    public static final String complePoint_COLUMN = "COMPLE_POINT";
    
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
     * ライセンス種別
     */
    private String licenseKbn = "";
    
    /**
     * 受験番号
     */
    private String examNo = "";
    
    /**
     * 再エントリーフラグ
     */
    private String reentryFlg = "";
    
    /**
     * 総受験回数
     */
    private BigDecimal entryCount = new BigDecimal("0");
    
    /**
     * 再エントリー基準年度
     */
    private String reentryBaseYear = "";
    
    /**
     * 受験年度
     */
    private String totalLastYear = "";
    
    /**
     * 受験回
     */
    private String totalLastKai = "";
    
    /**
     * 総合結果
     */
    private String totalResult = "";
    
    /**
     * 能力チェック  再エントリー基準年度
     */
    private String sub1BaseYear = "";
    
    /**
     * 能力チェック　最終受験年度
     */
    private String sub1LastYear = "";
    
    /**
     * 能力チェック　最終受験回
     */
    private String sub1LastKai = "";
    
    /**
     * 能力チェック  前回結果
     */
    private String sub1LastResult = "";
    
    /**
     * 能力チェック  結果
     */
    private String sub1Result = "";
    
    /**
     * 筆記テスト  再エントリー基準年度
     */
    private String sub2BaseYear = "";
    
    /**
     * 筆記テスト　最終受験年度
     */
    private String sub2LastYear = "";
    
    /**
     * 筆記テスト　最終受験回
     */
    private String sub2LastKai = "";
    
    /**
     * 筆記テスト  前回結果
     */
    private String sub2LastResult = "";
    
    /**
     * 筆記テスト  結果
     */
    private String sub2Result = "";
    
    /**
     * 面接  再エントリー基準年度
     */
    private String sub3BaseYear = "";
    
    /**
     * 面接　最終受験年度
     */
    private String sub3LastYear = "";
    
    /**
     * 面接　最終受験回
     */
    private String sub3LastKai = "";
    
    /**
     * 面接  前回結果
     */
    private String sub3LastResult = "";
    
    /**
     * 面接  結果
     */
    private String sub3Result = "";
    
    /**
     * 科目４  再エントリー基準年度
     */
    private String sub4BaseYear = "";
    
    /**
     * 科目４　最終受験年度
     */
    private String sub4LastYear = "";
    
    /**
     * 科目４　最終受験回
     */
    private String sub4LastKai = "";
    
    /**
     * 科目４  前回結果
     */
    private String sub4LastResult = "";
    
    /**
     * 科目４  結果
     */
    private String sub4Result = "";
    
    /**
     * 科目５  再エントリー基準年度
     */
    private String sub5BaseYear = "";
    
    /**
     * 科目５　最終受験年度
     */
    private String sub5LastYear = "";
    
    /**
     * 科目５　最終受験回
     */
    private String sub5LastKai = "";
    
    /**
     * 科目５  前回結果
     */
    private String sub5LastResult = "";
    
    /**
     * 科目５  結果
     */
    private String sub5Result = "";
    
    /**
     * 修了（予定）コース状況
     */
    private String courseStatus = "";
    
    /**
     * 修了（予定）コース修了年月
     */
    private String compleCourseDt = "";
    
    /**
     * 修了（予定）コースコード
     */
    private String compleCourseCd = "";
    
    /**
     * 修了（予定）コース名称
     */
    private String compleCourseName = "";
    
    /**
     * 出張研修用点数
     */
    private BigDecimal complePoint = new BigDecimal("0");
    
    /**
     * 予備フラグ1
     */
    private String spareFlg1 = "";
    
    /**
     * 予備フラグ2
     */
    private String spareFlg2 = "";
    
    /**
     * 予備フラグ3
     */
    private String spareFlg3 = "";
    
    /**
     * 登録ユーザー
     */
    private String firstUser = "";
    
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
     * ライセンス種別を取得します。
     * @return ライセンス種別
     */
    public String getLicenseKbn() {
        return licenseKbn;
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
        return examNo;
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
        return reentryFlg;
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
    public BigDecimal getEntryCount() {
        return entryCount;
    }
    /**
     * 総受験回数を設定します。
     * @param entryCount 総受験回数
     */
    public void setEntryCount(BigDecimal entryCount) {
        this.entryCount = entryCount;
    }
    
    /**
     * 再エントリー基準年度を取得します。
     * @return 再エントリー基準年度
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
    
    /**
     * 受験年度を取得します。
     * @return 受験年度
     */
    public String getTotalLastYear() {
        return totalLastYear;
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
        return totalLastKai;
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
     * 能力チェック  再エントリー基準年度を取得します。
     * @return 能力チェック  再エントリー基準年度
     */
    public String getSub1BaseYear() {
        return sub1BaseYear;
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
        return sub1LastYear;
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
        return sub1LastKai;
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
        return sub1LastResult;
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
        return sub1Result;
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
        return sub2BaseYear;
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
        return sub2LastYear;
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
        return sub2LastKai;
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
        return sub2LastResult;
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
        return sub2Result;
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
        return sub3BaseYear;
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
        return sub3LastYear;
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
        return sub3LastKai;
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
        return sub3LastResult;
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
        return sub3Result;
    }
    /**
     * 面接  結果を設定します。
     * @param sub3Result 面接  結果
     */
    public void setSub3Result(String sub3Result) {
        this.sub3Result = sub3Result;
    }
    
    /**
     * 科目４  再エントリー基準年度を取得します。
     * @return 科目４  再エントリー基準年度
     */
    public String getSub4BaseYear() {
        return sub4BaseYear;
    }
    /**
     * 科目４  再エントリー基準年度を設定します。
     * @param sub4BaseYear 科目４  再エントリー基準年度
     */
    public void setSub4BaseYear(String sub4BaseYear) {
        this.sub4BaseYear = sub4BaseYear;
    }
    
    /**
     * 科目４　最終受験年度を取得します。
     * @return 科目４　最終受験年度
     */
    public String getSub4LastYear() {
        return sub4LastYear;
    }
    /**
     * 科目４　最終受験年度を設定します。
     * @param sub4LastYear 科目４　最終受験年度
     */
    public void setSub4LastYear(String sub4LastYear) {
        this.sub4LastYear = sub4LastYear;
    }
    
    /**
     * 科目４　最終受験回を取得します。
     * @return 科目４　最終受験回
     */
    public String getSub4LastKai() {
        return sub4LastKai;
    }
    /**
     * 科目４　最終受験回を設定します。
     * @param sub4LastKai 科目４　最終受験回
     */
    public void setSub4LastKai(String sub4LastKai) {
        this.sub4LastKai = sub4LastKai;
    }
    
    /**
     * 科目４  前回結果を取得します。
     * @return 科目４  前回結果
     */
    public String getSub4LastResult() {
        return sub4LastResult;
    }
    /**
     * 科目４  前回結果を設定します。
     * @param sub4LastResult 科目４  前回結果
     */
    public void setSub4LastResult(String sub4LastResult) {
        this.sub4LastResult = sub4LastResult;
    }
    
    /**
     * 科目４  結果を取得します。
     * @return 科目４  結果
     */
    public String getSub4Result() {
        return sub4Result;
    }
    /**
     * 科目４  結果を設定します。
     * @param sub4Result 科目４  結果
     */
    public void setSub4Result(String sub4Result) {
        this.sub4Result = sub4Result;
    }
    
    /**
     * 科目５  再エントリー基準年度を取得します。
     * @return 科目５  再エントリー基準年度
     */
    public String getSub5BaseYear() {
        return sub5BaseYear;
    }
    /**
     * 科目５  再エントリー基準年度を設定します。
     * @param sub5BaseYear 科目５  再エントリー基準年度
     */
    public void setSub5BaseYear(String sub5BaseYear) {
        this.sub5BaseYear = sub5BaseYear;
    }
    
    /**
     * 科目５　最終受験年度を取得します。
     * @return 科目５　最終受験年度
     */
    public String getSub5LastYear() {
        return sub5LastYear;
    }
    /**
     * 科目５　最終受験年度を設定します。
     * @param sub5LastYear 科目５　最終受験年度
     */
    public void setSub5LastYear(String sub5LastYear) {
        this.sub5LastYear = sub5LastYear;
    }
    
    /**
     * 科目５　最終受験回を取得します。
     * @return 科目５　最終受験回
     */
    public String getSub5LastKai() {
        return sub5LastKai;
    }
    /**
     * 科目５　最終受験回を設定します。
     * @param sub5LastKai 科目５　最終受験回
     */
    public void setSub5LastKai(String sub5LastKai) {
        this.sub5LastKai = sub5LastKai;
    }
    
    /**
     * 科目５  前回結果を取得します。
     * @return 科目５  前回結果
     */
    public String getSub5LastResult() {
        return sub5LastResult;
    }
    /**
     * 科目５  前回結果を設定します。
     * @param sub5LastResult 科目５  前回結果
     */
    public void setSub5LastResult(String sub5LastResult) {
        this.sub5LastResult = sub5LastResult;
    }
    
    /**
     * 科目５  結果を取得します。
     * @return 科目５  結果
     */
    public String getSub5Result() {
        return sub5Result;
    }
    /**
     * 科目５  結果を設定します。
     * @param sub5Result 科目５  結果
     */
    public void setSub5Result(String sub5Result) {
        this.sub5Result = sub5Result;
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
     * 出張研修用点数を取得します。
     * @return 出張研修用点数
     */
    public BigDecimal getComplePoint() {
        return complePoint;
    }
    /**
     * 出張研修用点数を設定します。
     * @param complePoint 出張研修用点数
     */
    public void setComplePoint(BigDecimal complePoint) {
        this.complePoint = complePoint;
    }
    
    /**
     * 予備フラグ1を取得します。
     * @return 予備フラグ1
     */
    public String getSpareFlg1() {
        return spareFlg1;
    }
    /**
     * 予備フラグ1を設定します。
     * @param spareFlg1 予備フラグ1
     */
    public void setSpareFlg1(String spareFlg1) {
        this.spareFlg1 = spareFlg1;
    }
    
    /**
     * 予備フラグ2を取得します。
     * @return 予備フラグ2
     */
    public String getSpareFlg2() {
        return spareFlg2;
    }
    /**
     * 予備フラグ2を設定します。
     * @param spareFlg2 予備フラグ2
     */
    public void setSpareFlg2(String spareFlg2) {
        this.spareFlg2 = spareFlg2;
    }
    
    /**
     * 予備フラグ3を取得します。
     * @return 予備フラグ3
     */
    public String getSpareFlg3() {
        return spareFlg3;
    }
    /**
     * 予備フラグ3を設定します。
     * @param spareFlg3 予備フラグ3
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
    
}
