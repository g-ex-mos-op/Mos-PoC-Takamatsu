package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

import java.sql.Timestamp;

public class TrnAbilityCheckResult {
    
    public static final String TABLE = "BT28MLNC";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String assesser_COLUMN = "ASSESSER";
    
    public static final String sub1Chk1Byear_COLUMN = "SUB1_CHK1_BYEAR";
    
    public static final String sub1Chk1Lyear_COLUMN = "SUB1_CHK1_LYEAR";
    
    public static final String sub1Chk1Lkai_COLUMN = "SUB1_CHK1_LKAI";
    
    public static final String sub1Chk1LastRslt_COLUMN = "SUB1_CHK1_LAST_RSLT";
    
    public static final String sub1Chk1Result_COLUMN = "SUB1_CHK1_RESULT";
    
    public static final String sub1Chk2Byear_COLUMN = "SUB1_CHK2_BYEAR";
    
    public static final String sub1Chk2Lyear_COLUMN = "SUB1_CHK2_LYEAR";
    
    public static final String sub1Chk2Lkai_COLUMN = "SUB1_CHK2_LKAI";
    
    public static final String sub1Chk2LastRslt_COLUMN = "SUB1_CHK2_LAST_RSLT";
    
    public static final String sub1Chk2Result_COLUMN = "SUB1_CHK2_RESULT";
    
    public static final String sub1Chk3Byear_COLUMN = "SUB1_CHK3_BYEAR";
    
    public static final String sub1Chk3Lyear_COLUMN = "SUB1_CHK3_LYEAR";
    
    public static final String sub1Chk3Lkai_COLUMN = "SUB1_CHK3_LKAI";
    
    public static final String sub1Chk3LastRslt_COLUMN = "SUB1_CHK3_LAST_RSLT";
    
    public static final String sub1Chk3Result_COLUMN = "SUB1_CHK3_RESULT";
    
    public static final String sub1Chk4Byear_COLUMN = "SUB1_CHK4_BYEAR";
    
    public static final String sub1Chk4Lyear_COLUMN = "SUB1_CHK4_LYEAR";
    
    public static final String sub1Chk4Lkai_COLUMN = "SUB1_CHK4_LKAI";
    
    public static final String sub1Chk4LastRslt_COLUMN = "SUB1_CHK4_LAST_RSLT";
    
    public static final String sub1Chk4Result_COLUMN = "SUB1_CHK4_RESULT";
    
    public static final String sub1Chk5Byear_COLUMN = "SUB1_CHK5_BYEAR";
    
    public static final String sub1Chk5Lyear_COLUMN = "SUB1_CHK5_LYEAR";
    
    public static final String sub1Chk5Lkai_COLUMN = "SUB1_CHK5_LKAI";
    
    public static final String sub1Chk5LastRslt_COLUMN = "SUB1_CHK5_LAST_RSLT";
    
    public static final String sub1Chk5Result_COLUMN = "SUB1_CHK5_RESULT";
    
    public static final String sub1Chk6Byear_COLUMN = "SUB1_CHK6_BYEAR";
    
    public static final String sub1Chk6Lyear_COLUMN = "SUB1_CHK6_LYEAR";
    
    public static final String sub1Chk6Lkai_COLUMN = "SUB1_CHK6_LKAI";
    
    public static final String sub1Chk6LastRslt_COLUMN = "SUB1_CHK6_LAST_RSLT";
    
    public static final String sub1Chk6Result_COLUMN = "SUB1_CHK6_RESULT";
    
    public static final String sub1Chk7Byear_COLUMN = "SUB1_CHK7_BYEAR";
    
    public static final String sub1Chk7Lyear_COLUMN = "SUB1_CHK7_LYEAR";
    
    public static final String sub1Chk7Lkai_COLUMN = "SUB1_CHK7_LKAI";
    
    public static final String sub1Chk7LastRslt_COLUMN = "SUB1_CHK7_LAST_RSLT";
    
    public static final String sub1Chk7Result_COLUMN = "SUB1_CHK7_RESULT";
    
    public static final String sub1Chk8Byear_COLUMN = "SUB1_CHK8_BYEAR";
    
    public static final String sub1Chk8Lyear_COLUMN = "SUB1_CHK8_LYEAR";
    
    public static final String sub1Chk8Lkai_COLUMN = "SUB1_CHK8_LKAI";
    
    public static final String sub1Chk8LastRslt_COLUMN = "SUB1_CHK8_LAST_RSLT";
    
    public static final String sub1Chk8Result_COLUMN = "SUB1_CHK8_RESULT";
    
    public static final String sub1Chk9Byear_COLUMN = "SUB1_CHK9_BYEAR";
    
    public static final String sub1Chk9Lyear_COLUMN = "SUB1_CHK9_LYEAR";
    
    public static final String sub1Chk9Lkai_COLUMN = "SUB1_CHK9_LKAI";
    
    public static final String sub1Chk9LastRslt_COLUMN = "SUB1_CHK9_LAST_RSLT";
    
    public static final String sub1Chk9Result_COLUMN = "SUB1_CHK9_RESULT";
    
    public static final String sub1Chk10Byear_COLUMN = "SUB1_CHK10_BYEAR";
    
    public static final String sub1Chk10Lyear_COLUMN = "SUB1_CHK10_LYEAR";
    
    public static final String sub1Chk10Lkai_COLUMN = "SUB1_CHK10_LKAI";
    
    public static final String sub1Chk10LastRslt_COLUMN = "SUB1_CHK10_LAST_RSLT";
    
    public static final String sub1Chk10Result_COLUMN = "SUB1_CHK10_RESULT";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    public static final String insertFlg_COLUMN = "INSERT_FLG";

    public static final String reentryFlg_COLUMN = "REENTRY_FLG";

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
     * アセッサー
     */
    private String assesser;
    
    /**
     * 能力チェックカテゴリ１再エントリ基準年度
     */
    private String sub1Chk1Byear;
    
    /**
     * 能力チェックカテゴリ１最終受験年度
     */
    private String sub1Chk1Lyear;
    
    /**
     * 能力チェックカテゴリ１最終受験回
     */
    private String sub1Chk1Lkai;
    
    /**
     * 能力チェックカテゴリ１前回結果
     */
    private String sub1Chk1LastRslt;
    
    /**
     * 能力チェックカテゴリ１結果
     */
    private String sub1Chk1Result;
    
    /**
     * 能力チェックカテゴリ2再エントリ基準年度
     */
    private String sub1Chk2Byear;
    
    /**
     * 能力チェックカテゴリ2最終受験年度
     */
    private String sub1Chk2Lyear;
    
    /**
     * 能力チェックカテゴリ2最終受験回
     */
    private String sub1Chk2Lkai;
    
    /**
     * 能力チェックカテゴリ2前回結果
     */
    private String sub1Chk2LastRslt;
    
    /**
     * 能力チェックカテゴリ2結果
     */
    private String sub1Chk2Result;
    
    /**
     * 能力チェックカテゴリ3再エントリ基準年度
     */
    private String sub1Chk3Byear;
    
    /**
     * 能力チェックカテゴリ3最終受験年度
     */
    private String sub1Chk3Lyear;
    
    /**
     * 能力チェックカテゴリ3最終受験回
     */
    private String sub1Chk3Lkai;
    
    /**
     * 能力チェックカテゴリ3前回結果
     */
    private String sub1Chk3LastRslt;
    
    /**
     * 能力チェックカテゴリ3結果
     */
    private String sub1Chk3Result;
    
    /**
     * 能力チェックカテゴリ4再エントリ基準年度
     */
    private String sub1Chk4Byear;
    
    /**
     * 能力チェックカテゴリ4最終受験年度
     */
    private String sub1Chk4Lyear;
    
    /**
     * 能力チェックカテゴリ4最終受験回
     */
    private String sub1Chk4Lkai;
    
    /**
     * 能力チェックカテゴリ4前回結果
     */
    private String sub1Chk4LastRslt;
    
    /**
     * 能力チェックカテゴリ4結果
     */
    private String sub1Chk4Result;
    
    /**
     * 能力チェックカテゴリ5再エントリ基準年度
     */
    private String sub1Chk5Byear;
    
    /**
     * 能力チェックカテゴリ5最終受験年度
     */
    private String sub1Chk5Lyear;
    
    /**
     * 能力チェックカテゴリ5最終受験回
     */
    private String sub1Chk5Lkai;
    
    /**
     * 能力チェックカテゴリ5前回結果
     */
    private String sub1Chk5LastRslt;
    
    /**
     * 能力チェックカテゴリ5結果
     */
    private String sub1Chk5Result;
    
    /**
     * 能力チェックカテゴリ6再エントリ基準年度
     */
    private String sub1Chk6Byear;
    
    /**
     * 能力チェックカテゴリ6最終受験年度
     */
    private String sub1Chk6Lyear;
    
    /**
     * 能力チェックカテゴリ6最終受験回
     */
    private String sub1Chk6Lkai;
    
    /**
     * 能力チェックカテゴリ6前回結果
     */
    private String sub1Chk6LastRslt;
    
    /**
     * 能力チェックカテゴリ6結果
     */
    private String sub1Chk6Result;
    
    /**
     * 能力チェックカテゴリ7再エントリ基準年度
     */
    private String sub1Chk7Byear;
    
    /**
     * 能力チェックカテゴリ7最終受験年度
     */
    private String sub1Chk7Lyear;
    
    /**
     * 能力チェックカテゴリ7最終受験回
     */
    private String sub1Chk7Lkai;
    
    /**
     * 能力チェックカテゴリ7前回結果
     */
    private String sub1Chk7LastRslt;
    
    /**
     * 能力チェックカテゴリ7結果
     */
    private String sub1Chk7Result;
    
    /**
     * 能力チェックカテゴリ8再エントリ基準年度
     */
    private String sub1Chk8Byear;
    
    /**
     * 能力チェックカテゴリ8最終受験年度
     */
    private String sub1Chk8Lyear;
    
    /**
     * 能力チェックカテゴリ8最終受験回
     */
    private String sub1Chk8Lkai;
    
    /**
     * 能力チェックカテゴリ8前回結果
     */
    private String sub1Chk8LastRslt;
    
    /**
     * 能力チェックカテゴリ8結果
     */
    private String sub1Chk8Result;
    
    /**
     * 能力チェックカテゴリ9再エントリ基準年度
     */
    private String sub1Chk9Byear;
    
    /**
     * 能力チェックカテゴリ9最終受験年度
     */
    private String sub1Chk9Lyear;
    
    /**
     * 能力チェックカテゴリ9最終受験回
     */
    private String sub1Chk9Lkai;
    
    /**
     * 能力チェックカテゴリ9前回結果
     */
    private String sub1Chk9LastRslt;
    
    /**
     * 能力チェックカテゴリ9結果
     */
    private String sub1Chk9Result;
    
    /**
     * 能力チェックカテゴリ１0再エントリ基準年度
     */
    private String sub1Chk10Byear;
    
    /**
     * 能力チェックカテゴリ１0最終受験年度
     */
    private String sub1Chk10Lyear;
    
    /**
     * 能力チェックカテゴリ１0最終受験回
     */
    private String sub1Chk10Lkai;
    
    /**
     * 能力チェックカテゴリ１0前回結果
     */
    private String sub1Chk10LastRslt;
    
    /**
     * 能力チェックカテゴリ１0結果
     */
    private String sub1Chk10Result;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;

    /**
     * 新規フラグ
     */
    private boolean insertFlg;

    /**
     * 再エントリーフラグ
     */
    private String reentryFlg;

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
     * アセッサーを取得します。
     * @return アセッサー
     */
    public String getAssesser() {
        return assesser;
    }
    /**
     * アセッサーを設定します。
     * @param asseser アセッサー
     */
    public void setAssesser(String assesser) {
        this.assesser = assesser;
    }
    
    /**
     * 能力チェックカテゴリ１再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ１再エントリ基準年度
     */
    public String getSub1Chk1Byear() {
        return sub1Chk1Byear;
    }
    /**
     * 能力チェックカテゴリ１再エントリ基準年度を設定します。
     * @param sub1Chk1Byear 能力チェックカテゴリ１再エントリ基準年度
     */
    public void setSub1Chk1Byear(String sub1Chk1Byear) {
        this.sub1Chk1Byear = sub1Chk1Byear;
    }
    
    /**
     * 能力チェックカテゴリ１最終受験年度を取得します。
     * @return 能力チェックカテゴリ１最終受験年度
     */
    public String getSub1Chk1Lyear() {
        return sub1Chk1Lyear;
    }
    /**
     * 能力チェックカテゴリ１最終受験年度を設定します。
     * @param sub1Chk1Lyear 能力チェックカテゴリ１最終受験年度
     */
    public void setSub1Chk1Lyear(String sub1Chk1Lyear) {
        this.sub1Chk1Lyear = sub1Chk1Lyear;
    }
    
    /**
     * 能力チェックカテゴリ１最終受験回を取得します。
     * @return 能力チェックカテゴリ１最終受験回
     */
    public String getSub1Chk1Lkai() {
        return sub1Chk1Lkai;
    }
    /**
     * 能力チェックカテゴリ１最終受験回を設定します。
     * @param sub1Chk1Lkai 能力チェックカテゴリ１最終受験回
     */
    public void setSub1Chk1Lkai(String sub1Chk1Lkai) {
        this.sub1Chk1Lkai = sub1Chk1Lkai;
    }
    
    /**
     * 能力チェックカテゴリ１前回結果を取得します。
     * @return 能力チェックカテゴリ１前回結果
     */
    public String getSub1Chk1LastRslt() {
        return sub1Chk1LastRslt;
    }
    /**
     * 能力チェックカテゴリ１前回結果を設定します。
     * @param sub1Chk1LastRslt 能力チェックカテゴリ１前回結果
     */
    public void setSub1Chk1LastRslt(String sub1Chk1LastRslt) {
        this.sub1Chk1LastRslt = sub1Chk1LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ１結果を取得します。
     * @return 能力チェックカテゴリ１結果
     */
    public String getSub1Chk1Result() {
        return sub1Chk1Result;
    }
    /**
     * 能力チェックカテゴリ１結果を設定します。
     * @param sub1Chk1Result 能力チェックカテゴリ１結果
     */
    public void setSub1Chk1Result(String sub1Chk1Result) {
        this.sub1Chk1Result = sub1Chk1Result;
    }
    
    /**
     * 能力チェックカテゴリ2再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ2再エントリ基準年度
     */
    public String getSub1Chk2Byear() {
        return sub1Chk2Byear;
    }
    /**
     * 能力チェックカテゴリ2再エントリ基準年度を設定します。
     * @param sub1Chk2Byear 能力チェックカテゴリ2再エントリ基準年度
     */
    public void setSub1Chk2Byear(String sub1Chk2Byear) {
        this.sub1Chk2Byear = sub1Chk2Byear;
    }
    
    /**
     * 能力チェックカテゴリ2最終受験年度を取得します。
     * @return 能力チェックカテゴリ2最終受験年度
     */
    public String getSub1Chk2Lyear() {
        return sub1Chk2Lyear;
    }
    /**
     * 能力チェックカテゴリ2最終受験年度を設定します。
     * @param sub1Chk2Lyear 能力チェックカテゴリ2最終受験年度
     */
    public void setSub1Chk2Lyear(String sub1Chk2Lyear) {
        this.sub1Chk2Lyear = sub1Chk2Lyear;
    }
    
    /**
     * 能力チェックカテゴリ2最終受験回を取得します。
     * @return 能力チェックカテゴリ2最終受験回
     */
    public String getSub1Chk2Lkai() {
        return sub1Chk2Lkai;
    }
    /**
     * 能力チェックカテゴリ2最終受験回を設定します。
     * @param sub1Chk2Lkai 能力チェックカテゴリ2最終受験回
     */
    public void setSub1Chk2Lkai(String sub1Chk2Lkai) {
        this.sub1Chk2Lkai = sub1Chk2Lkai;
    }
    
    /**
     * 能力チェックカテゴリ2前回結果を取得します。
     * @return 能力チェックカテゴリ2前回結果
     */
    public String getSub1Chk2LastRslt() {
        return sub1Chk2LastRslt;
    }
    /**
     * 能力チェックカテゴリ2前回結果を設定します。
     * @param sub1Chk2LastRslt 能力チェックカテゴリ2前回結果
     */
    public void setSub1Chk2LastRslt(String sub1Chk2LastRslt) {
        this.sub1Chk2LastRslt = sub1Chk2LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ2結果を取得します。
     * @return 能力チェックカテゴリ2結果
     */
    public String getSub1Chk2Result() {
        return sub1Chk2Result;
    }
    /**
     * 能力チェックカテゴリ2結果を設定します。
     * @param sub1Chk2Result 能力チェックカテゴリ2結果
     */
    public void setSub1Chk2Result(String sub1Chk2Result) {
        this.sub1Chk2Result = sub1Chk2Result;
    }
    
    /**
     * 能力チェックカテゴリ3再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ3再エントリ基準年度
     */
    public String getSub1Chk3Byear() {
        return sub1Chk3Byear;
    }
    /**
     * 能力チェックカテゴリ3再エントリ基準年度を設定します。
     * @param sub1Chk3Byear 能力チェックカテゴリ3再エントリ基準年度
     */
    public void setSub1Chk3Byear(String sub1Chk3Byear) {
        this.sub1Chk3Byear = sub1Chk3Byear;
    }
    
    /**
     * 能力チェックカテゴリ3最終受験年度を取得します。
     * @return 能力チェックカテゴリ3最終受験年度
     */
    public String getSub1Chk3Lyear() {
        return sub1Chk3Lyear;
    }
    /**
     * 能力チェックカテゴリ3最終受験年度を設定します。
     * @param sub1Chk3Lyear 能力チェックカテゴリ3最終受験年度
     */
    public void setSub1Chk3Lyear(String sub1Chk3Lyear) {
        this.sub1Chk3Lyear = sub1Chk3Lyear;
    }
    
    /**
     * 能力チェックカテゴリ3最終受験回を取得します。
     * @return 能力チェックカテゴリ3最終受験回
     */
    public String getSub1Chk3Lkai() {
        return sub1Chk3Lkai;
    }
    /**
     * 能力チェックカテゴリ3最終受験回を設定します。
     * @param sub1Chk3Lkai 能力チェックカテゴリ3最終受験回
     */
    public void setSub1Chk3Lkai(String sub1Chk3Lkai) {
        this.sub1Chk3Lkai = sub1Chk3Lkai;
    }
    
    /**
     * 能力チェックカテゴリ3前回結果を取得します。
     * @return 能力チェックカテゴリ3前回結果
     */
    public String getSub1Chk3LastRslt() {
        return sub1Chk3LastRslt;
    }
    /**
     * 能力チェックカテゴリ3前回結果を設定します。
     * @param sub1Chk3LastRslt 能力チェックカテゴリ3前回結果
     */
    public void setSub1Chk3LastRslt(String sub1Chk3LastRslt) {
        this.sub1Chk3LastRslt = sub1Chk3LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ3結果を取得します。
     * @return 能力チェックカテゴリ3結果
     */
    public String getSub1Chk3Result() {
        return sub1Chk3Result;
    }
    /**
     * 能力チェックカテゴリ3結果を設定します。
     * @param sub1Chk3Result 能力チェックカテゴリ3結果
     */
    public void setSub1Chk3Result(String sub1Chk3Result) {
        this.sub1Chk3Result = sub1Chk3Result;
    }
    
    /**
     * 能力チェックカテゴリ4再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ4再エントリ基準年度
     */
    public String getSub1Chk4Byear() {
        return sub1Chk4Byear;
    }
    /**
     * 能力チェックカテゴリ4再エントリ基準年度を設定します。
     * @param sub1Chk4Byear 能力チェックカテゴリ4再エントリ基準年度
     */
    public void setSub1Chk4Byear(String sub1Chk4Byear) {
        this.sub1Chk4Byear = sub1Chk4Byear;
    }
    
    /**
     * 能力チェックカテゴリ4最終受験年度を取得します。
     * @return 能力チェックカテゴリ4最終受験年度
     */
    public String getSub1Chk4Lyear() {
        return sub1Chk4Lyear;
    }
    /**
     * 能力チェックカテゴリ4最終受験年度を設定します。
     * @param sub1Chk4Lyear 能力チェックカテゴリ4最終受験年度
     */
    public void setSub1Chk4Lyear(String sub1Chk4Lyear) {
        this.sub1Chk4Lyear = sub1Chk4Lyear;
    }
    
    /**
     * 能力チェックカテゴリ4最終受験回を取得します。
     * @return 能力チェックカテゴリ4最終受験回
     */
    public String getSub1Chk4Lkai() {
        return sub1Chk4Lkai;
    }
    /**
     * 能力チェックカテゴリ4最終受験回を設定します。
     * @param sub1Chk4Lkai 能力チェックカテゴリ4最終受験回
     */
    public void setSub1Chk4Lkai(String sub1Chk4Lkai) {
        this.sub1Chk4Lkai = sub1Chk4Lkai;
    }
    
    /**
     * 能力チェックカテゴリ4前回結果を取得します。
     * @return 能力チェックカテゴリ4前回結果
     */
    public String getSub1Chk4LastRslt() {
        return sub1Chk4LastRslt;
    }
    /**
     * 能力チェックカテゴリ4前回結果を設定します。
     * @param sub1Chk4LastRslt 能力チェックカテゴリ4前回結果
     */
    public void setSub1Chk4LastRslt(String sub1Chk4LastRslt) {
        this.sub1Chk4LastRslt = sub1Chk4LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ4結果を取得します。
     * @return 能力チェックカテゴリ4結果
     */
    public String getSub1Chk4Result() {
        return sub1Chk4Result;
    }
    /**
     * 能力チェックカテゴリ4結果を設定します。
     * @param sub1Chk4Result 能力チェックカテゴリ4結果
     */
    public void setSub1Chk4Result(String sub1Chk4Result) {
        this.sub1Chk4Result = sub1Chk4Result;
    }
    
    /**
     * 能力チェックカテゴリ5再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ5再エントリ基準年度
     */
    public String getSub1Chk5Byear() {
        return sub1Chk5Byear;
    }
    /**
     * 能力チェックカテゴリ5再エントリ基準年度を設定します。
     * @param sub1Chk5Byear 能力チェックカテゴリ5再エントリ基準年度
     */
    public void setSub1Chk5Byear(String sub1Chk5Byear) {
        this.sub1Chk5Byear = sub1Chk5Byear;
    }
    
    /**
     * 能力チェックカテゴリ5最終受験年度を取得します。
     * @return 能力チェックカテゴリ5最終受験年度
     */
    public String getSub1Chk5Lyear() {
        return sub1Chk5Lyear;
    }
    /**
     * 能力チェックカテゴリ5最終受験年度を設定します。
     * @param sub1Chk5Lyear 能力チェックカテゴリ5最終受験年度
     */
    public void setSub1Chk5Lyear(String sub1Chk5Lyear) {
        this.sub1Chk5Lyear = sub1Chk5Lyear;
    }
    
    /**
     * 能力チェックカテゴリ5最終受験回を取得します。
     * @return 能力チェックカテゴリ5最終受験回
     */
    public String getSub1Chk5Lkai() {
        return sub1Chk5Lkai;
    }
    /**
     * 能力チェックカテゴリ5最終受験回を設定します。
     * @param sub1Chk5Lkai 能力チェックカテゴリ5最終受験回
     */
    public void setSub1Chk5Lkai(String sub1Chk5Lkai) {
        this.sub1Chk5Lkai = sub1Chk5Lkai;
    }
    
    /**
     * 能力チェックカテゴリ5前回結果を取得します。
     * @return 能力チェックカテゴリ5前回結果
     */
    public String getSub1Chk5LastRslt() {
        return sub1Chk5LastRslt;
    }
    /**
     * 能力チェックカテゴリ5前回結果を設定します。
     * @param sub1Chk5LastRslt 能力チェックカテゴリ5前回結果
     */
    public void setSub1Chk5LastRslt(String sub1Chk5LastRslt) {
        this.sub1Chk5LastRslt = sub1Chk5LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ5結果を取得します。
     * @return 能力チェックカテゴリ5結果
     */
    public String getSub1Chk5Result() {
        return sub1Chk5Result;
    }
    /**
     * 能力チェックカテゴリ5結果を設定します。
     * @param sub1Chk5Result 能力チェックカテゴリ5結果
     */
    public void setSub1Chk5Result(String sub1Chk5Result) {
        this.sub1Chk5Result = sub1Chk5Result;
    }
    
    /**
     * 能力チェックカテゴリ6再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ6再エントリ基準年度
     */
    public String getSub1Chk6Byear() {
        return sub1Chk6Byear;
    }
    /**
     * 能力チェックカテゴリ6再エントリ基準年度を設定します。
     * @param sub1Chk6Byear 能力チェックカテゴリ6再エントリ基準年度
     */
    public void setSub1Chk6Byear(String sub1Chk6Byear) {
        this.sub1Chk6Byear = sub1Chk6Byear;
    }
    
    /**
     * 能力チェックカテゴリ6最終受験年度を取得します。
     * @return 能力チェックカテゴリ6最終受験年度
     */
    public String getSub1Chk6Lyear() {
        return sub1Chk6Lyear;
    }
    /**
     * 能力チェックカテゴリ6最終受験年度を設定します。
     * @param sub1Chk6Lyear 能力チェックカテゴリ6最終受験年度
     */
    public void setSub1Chk6Lyear(String sub1Chk6Lyear) {
        this.sub1Chk6Lyear = sub1Chk6Lyear;
    }
    
    /**
     * 能力チェックカテゴリ6最終受験回を取得します。
     * @return 能力チェックカテゴリ6最終受験回
     */
    public String getSub1Chk6Lkai() {
        return sub1Chk6Lkai;
    }
    /**
     * 能力チェックカテゴリ6最終受験回を設定します。
     * @param sub1Chk6Lkai 能力チェックカテゴリ6最終受験回
     */
    public void setSub1Chk6Lkai(String sub1Chk6Lkai) {
        this.sub1Chk6Lkai = sub1Chk6Lkai;
    }
    
    /**
     * 能力チェックカテゴリ6前回結果を取得します。
     * @return 能力チェックカテゴリ6前回結果
     */
    public String getSub1Chk6LastRslt() {
        return sub1Chk6LastRslt;
    }
    /**
     * 能力チェックカテゴリ6前回結果を設定します。
     * @param sub1Chk6LastRslt 能力チェックカテゴリ6前回結果
     */
    public void setSub1Chk6LastRslt(String sub1Chk6LastRslt) {
        this.sub1Chk6LastRslt = sub1Chk6LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ6結果を取得します。
     * @return 能力チェックカテゴリ6結果
     */
    public String getSub1Chk6Result() {
        return sub1Chk6Result;
    }
    /**
     * 能力チェックカテゴリ6結果を設定します。
     * @param sub1Chk6Result 能力チェックカテゴリ6結果
     */
    public void setSub1Chk6Result(String sub1Chk6Result) {
        this.sub1Chk6Result = sub1Chk6Result;
    }
    
    /**
     * 能力チェックカテゴリ7再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ7再エントリ基準年度
     */
    public String getSub1Chk7Byear() {
        return sub1Chk7Byear;
    }
    /**
     * 能力チェックカテゴリ7再エントリ基準年度を設定します。
     * @param sub1Chk7Byear 能力チェックカテゴリ7再エントリ基準年度
     */
    public void setSub1Chk7Byear(String sub1Chk7Byear) {
        this.sub1Chk7Byear = sub1Chk7Byear;
    }
    
    /**
     * 能力チェックカテゴリ7最終受験年度を取得します。
     * @return 能力チェックカテゴリ7最終受験年度
     */
    public String getSub1Chk7Lyear() {
        return sub1Chk7Lyear;
    }
    /**
     * 能力チェックカテゴリ7最終受験年度を設定します。
     * @param sub1Chk7Lyear 能力チェックカテゴリ7最終受験年度
     */
    public void setSub1Chk7Lyear(String sub1Chk7Lyear) {
        this.sub1Chk7Lyear = sub1Chk7Lyear;
    }
    
    /**
     * 能力チェックカテゴリ7最終受験回を取得します。
     * @return 能力チェックカテゴリ7最終受験回
     */
    public String getSub1Chk7Lkai() {
        return sub1Chk7Lkai;
    }
    /**
     * 能力チェックカテゴリ7最終受験回を設定します。
     * @param sub1Chk7Lkai 能力チェックカテゴリ7最終受験回
     */
    public void setSub1Chk7Lkai(String sub1Chk7Lkai) {
        this.sub1Chk7Lkai = sub1Chk7Lkai;
    }
    
    /**
     * 能力チェックカテゴリ7前回結果を取得します。
     * @return 能力チェックカテゴリ7前回結果
     */
    public String getSub1Chk7LastRslt() {
        return sub1Chk7LastRslt;
    }
    /**
     * 能力チェックカテゴリ7前回結果を設定します。
     * @param sub1Chk7LastRslt 能力チェックカテゴリ7前回結果
     */
    public void setSub1Chk7LastRslt(String sub1Chk7LastRslt) {
        this.sub1Chk7LastRslt = sub1Chk7LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ7結果を取得します。
     * @return 能力チェックカテゴリ7結果
     */
    public String getSub1Chk7Result() {
        return sub1Chk7Result;
    }
    /**
     * 能力チェックカテゴリ7結果を設定します。
     * @param sub1Chk7Result 能力チェックカテゴリ7結果
     */
    public void setSub1Chk7Result(String sub1Chk7Result) {
        this.sub1Chk7Result = sub1Chk7Result;
    }
    
    /**
     * 能力チェックカテゴリ8再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ8再エントリ基準年度
     */
    public String getSub1Chk8Byear() {
        return sub1Chk8Byear;
    }
    /**
     * 能力チェックカテゴリ8再エントリ基準年度を設定します。
     * @param sub1Chk8Byear 能力チェックカテゴリ8再エントリ基準年度
     */
    public void setSub1Chk8Byear(String sub1Chk8Byear) {
        this.sub1Chk8Byear = sub1Chk8Byear;
    }
    
    /**
     * 能力チェックカテゴリ8最終受験年度を取得します。
     * @return 能力チェックカテゴリ8最終受験年度
     */
    public String getSub1Chk8Lyear() {
        return sub1Chk8Lyear;
    }
    /**
     * 能力チェックカテゴリ8最終受験年度を設定します。
     * @param sub1Chk8Lyear 能力チェックカテゴリ8最終受験年度
     */
    public void setSub1Chk8Lyear(String sub1Chk8Lyear) {
        this.sub1Chk8Lyear = sub1Chk8Lyear;
    }
    
    /**
     * 能力チェックカテゴリ8最終受験回を取得します。
     * @return 能力チェックカテゴリ8最終受験回
     */
    public String getSub1Chk8Lkai() {
        return sub1Chk8Lkai;
    }
    /**
     * 能力チェックカテゴリ8最終受験回を設定します。
     * @param sub1Chk8Lkai 能力チェックカテゴリ8最終受験回
     */
    public void setSub1Chk8Lkai(String sub1Chk8Lkai) {
        this.sub1Chk8Lkai = sub1Chk8Lkai;
    }
    
    /**
     * 能力チェックカテゴリ8前回結果を取得します。
     * @return 能力チェックカテゴリ8前回結果
     */
    public String getSub1Chk8LastRslt() {
        return sub1Chk8LastRslt;
    }
    /**
     * 能力チェックカテゴリ8前回結果を設定します。
     * @param sub1Chk8LastRslt 能力チェックカテゴリ8前回結果
     */
    public void setSub1Chk8LastRslt(String sub1Chk8LastRslt) {
        this.sub1Chk8LastRslt = sub1Chk8LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ8結果を取得します。
     * @return 能力チェックカテゴリ8結果
     */
    public String getSub1Chk8Result() {
        return sub1Chk8Result;
    }
    /**
     * 能力チェックカテゴリ8結果を設定します。
     * @param sub1Chk8Result 能力チェックカテゴリ8結果
     */
    public void setSub1Chk8Result(String sub1Chk8Result) {
        this.sub1Chk8Result = sub1Chk8Result;
    }
    
    /**
     * 能力チェックカテゴリ9再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ9再エントリ基準年度
     */
    public String getSub1Chk9Byear() {
        return sub1Chk9Byear;
    }
    /**
     * 能力チェックカテゴリ9再エントリ基準年度を設定します。
     * @param sub1Chk9Byear 能力チェックカテゴリ9再エントリ基準年度
     */
    public void setSub1Chk9Byear(String sub1Chk9Byear) {
        this.sub1Chk9Byear = sub1Chk9Byear;
    }
    
    /**
     * 能力チェックカテゴリ9最終受験年度を取得します。
     * @return 能力チェックカテゴリ9最終受験年度
     */
    public String getSub1Chk9Lyear() {
        return sub1Chk9Lyear;
    }
    /**
     * 能力チェックカテゴリ9最終受験年度を設定します。
     * @param sub1Chk9Lyear 能力チェックカテゴリ9最終受験年度
     */
    public void setSub1Chk9Lyear(String sub1Chk9Lyear) {
        this.sub1Chk9Lyear = sub1Chk9Lyear;
    }
    
    /**
     * 能力チェックカテゴリ9最終受験回を取得します。
     * @return 能力チェックカテゴリ9最終受験回
     */
    public String getSub1Chk9Lkai() {
        return sub1Chk9Lkai;
    }
    /**
     * 能力チェックカテゴリ9最終受験回を設定します。
     * @param sub1Chk9Lkai 能力チェックカテゴリ9最終受験回
     */
    public void setSub1Chk9Lkai(String sub1Chk9Lkai) {
        this.sub1Chk9Lkai = sub1Chk9Lkai;
    }
    
    /**
     * 能力チェックカテゴリ9前回結果を取得します。
     * @return 能力チェックカテゴリ9前回結果
     */
    public String getSub1Chk9LastRslt() {
        return sub1Chk9LastRslt;
    }
    /**
     * 能力チェックカテゴリ9前回結果を設定します。
     * @param sub1Chk9LastRslt 能力チェックカテゴリ9前回結果
     */
    public void setSub1Chk9LastRslt(String sub1Chk9LastRslt) {
        this.sub1Chk9LastRslt = sub1Chk9LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ9結果を取得します。
     * @return 能力チェックカテゴリ9結果
     */
    public String getSub1Chk9Result() {
        return sub1Chk9Result;
    }
    /**
     * 能力チェックカテゴリ9結果を設定します。
     * @param sub1Chk9Result 能力チェックカテゴリ9結果
     */
    public void setSub1Chk9Result(String sub1Chk9Result) {
        this.sub1Chk9Result = sub1Chk9Result;
    }
    
    /**
     * 能力チェックカテゴリ１0再エントリ基準年度を取得します。
     * @return 能力チェックカテゴリ１0再エントリ基準年度
     */
    public String getSub1Chk10Byear() {
        return sub1Chk10Byear;
    }
    /**
     * 能力チェックカテゴリ１0再エントリ基準年度を設定します。
     * @param sub1Chk10Byear 能力チェックカテゴリ１0再エントリ基準年度
     */
    public void setSub1Chk10Byear(String sub1Chk10Byear) {
        this.sub1Chk10Byear = sub1Chk10Byear;
    }
    
    /**
     * 能力チェックカテゴリ１0最終受験年度を取得します。
     * @return 能力チェックカテゴリ１0最終受験年度
     */
    public String getSub1Chk10Lyear() {
        return sub1Chk10Lyear;
    }
    /**
     * 能力チェックカテゴリ１0最終受験年度を設定します。
     * @param sub1Chk10Lyear 能力チェックカテゴリ１0最終受験年度
     */
    public void setSub1Chk10Lyear(String sub1Chk10Lyear) {
        this.sub1Chk10Lyear = sub1Chk10Lyear;
    }
    
    /**
     * 能力チェックカテゴリ１0最終受験回を取得します。
     * @return 能力チェックカテゴリ１0最終受験回
     */
    public String getSub1Chk10Lkai() {
        return sub1Chk10Lkai;
    }
    /**
     * 能力チェックカテゴリ１0最終受験回を設定します。
     * @param sub1Chk10Lkai 能力チェックカテゴリ１0最終受験回
     */
    public void setSub1Chk10Lkai(String sub1Chk10Lkai) {
        this.sub1Chk10Lkai = sub1Chk10Lkai;
    }
    
    /**
     * 能力チェックカテゴリ１0前回結果を取得します。
     * @return 能力チェックカテゴリ１0前回結果
     */
    public String getSub1Chk10LastRslt() {
        return sub1Chk10LastRslt;
    }
    /**
     * 能力チェックカテゴリ１0前回結果を設定します。
     * @param sub1Chk10LastRslt 能力チェックカテゴリ１0前回結果
     */
    public void setSub1Chk10LastRslt(String sub1Chk10LastRslt) {
        this.sub1Chk10LastRslt = sub1Chk10LastRslt;
    }
    
    /**
     * 能力チェックカテゴリ１0結果を取得します。
     * @return 能力チェックカテゴリ１0結果
     */
    public String getSub1Chk10Result() {
        return sub1Chk10Result;
    }
    /**
     * 能力チェックカテゴリ１0結果を設定します。
     * @param sub1Chk10Result 能力チェックカテゴリ１0結果
     */
    public void setSub1Chk10Result(String sub1Chk10Result) {
        this.sub1Chk10Result = sub1Chk10Result;
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
     * 登録ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 更新ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 更新ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }

    /**
     * 新規フラグを取得します。
     * @return 新規フラグ
     */
    public boolean isInsertFlg() {
        return insertFlg;
    }
    /**
     * 新規フラグを設定します。
     * @param insertFlg 新規フラグ
     */
    public void setInsertFlg(boolean insertFlg) {
        this.insertFlg = insertFlg;
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

    public void setSub1ChkResult(String categoryCd, String result) {
        if (categoryCd.equals("01")) {
            setSub1Chk1Result(result);
        } else if (categoryCd.equals("02")) {
            setSub1Chk2Result(result);
        } else if (categoryCd.equals("03")) {
            setSub1Chk3Result(result);
        } else if (categoryCd.equals("04")) {
            setSub1Chk4Result(result);
        } else if (categoryCd.equals("05")) {
            setSub1Chk5Result(result);
        } else if (categoryCd.equals("06")) {
            setSub1Chk6Result(result);
        } else if (categoryCd.equals("07")) {
            setSub1Chk7Result(result);
        } else if (categoryCd.equals("08")) {
            setSub1Chk8Result(result);
        } else if (categoryCd.equals("09")) {
            setSub1Chk9Result(result);
        } else if (categoryCd.equals("10")) {
            setSub1Chk10Result(result);
        }
    }

    public String getSub1ChkResult(String categoryCd) {
        String result = null;
        if (categoryCd.equals("01")) {
            result = getSub1Chk1Result();
        } else if (categoryCd.equals("02")) {
            result = getSub1Chk2Result();
        } else if (categoryCd.equals("03")) {
            result = getSub1Chk3Result();
        } else if (categoryCd.equals("04")) {
            result = getSub1Chk4Result();
        } else if (categoryCd.equals("05")) {
            result = getSub1Chk5Result();
        } else if (categoryCd.equals("06")) {
            result = getSub1Chk6Result();
        } else if (categoryCd.equals("07")) {
            result = getSub1Chk7Result();
        } else if (categoryCd.equals("08")) {
            result = getSub1Chk8Result();
        } else if (categoryCd.equals("09")) {
            result = getSub1Chk9Result();
        } else if (categoryCd.equals("10")) {
            result = getSub1Chk10Result();
        }
        return result;
    }
}
