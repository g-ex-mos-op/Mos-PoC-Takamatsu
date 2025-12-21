package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

import java.sql.Timestamp;

public class TrnInterviewResult {
    
    public static final String TABLE = "BT29MLMN";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
    
    public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd1_COLUMN = "MISE_CD_1";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String assesser_COLUMN = "ASSESSER";
    
    public static final String sub2Chk1Byear_COLUMN = "SUB2_CHK1_BYEAR";
    
    public static final String sub2Chk1Lyear_COLUMN = "SUB2_CHK1_LYEAR";
    
    public static final String sub2Chk1Lkai_COLUMN = "SUB2_CHK1_LKAI";
    
    public static final String sub2Chk1LastRslt_COLUMN = "SUB2_CHK1_LAST_RSLT";
    
    public static final String sub2Chk1Result_COLUMN = "SUB2_CHK1_RESULT";
    
    public static final String sub2Chk2Byear_COLUMN = "SUB2_CHK2_BYEAR";
    
    public static final String sub2Chk2Lyear_COLUMN = "SUB2_CHK2_LYEAR";
    
    public static final String sub2Chk2Lkai_COLUMN = "SUB2_CHK2_LKAI";
    
    public static final String sub2Chk2LastRslt_COLUMN = "SUB2_CHK2_LAST_RSLT";
    
    public static final String sub2Chk2Result_COLUMN = "SUB2_CHK2_RESULT";
    
    public static final String sub2Chk3Byear_COLUMN = "SUB2_CHK3_BYEAR";
    
    public static final String sub2Chk3Lyear_COLUMN = "SUB2_CHK3_LYEAR";
    
    public static final String sub2Chk3Lkai_COLUMN = "SUB2_CHK3_LKAI";
    
    public static final String sub2Chk3LastRslt_COLUMN = "SUB2_CHK3_LAST_RSLT";
    
    public static final String sub2Chk3Result_COLUMN = "SUB2_CHK3_RESULT";
    
    public static final String sub2Chk4Byear_COLUMN = "SUB2_CHK4_BYEAR";
    
    public static final String sub2Chk4Lyear_COLUMN = "SUB2_CHK4_LYEAR";
    
    public static final String sub2Chk4Lkai_COLUMN = "SUB2_CHK4_LKAI";
    
    public static final String sub2Chk4LastRslt_COLUMN = "SUB2_CHK4_LAST_RSLT";
    
    public static final String sub2Chk4Result_COLUMN = "SUB2_CHK4_RESULT";
    
    public static final String sub2Chk5Byear_COLUMN = "SUB2_CHK5_BYEAR";
    
    public static final String sub2Chk5Lyear_COLUMN = "SUB2_CHK5_LYEAR";
    
    public static final String sub2Chk5Lkai_COLUMN = "SUB2_CHK5_LKAI";
    
    public static final String sub2Chk5LastRslt_COLUMN = "SUB2_CHK5_LAST_RSLT";
    
    public static final String sub2Chk5Result_COLUMN = "SUB2_CHK5_RESULT";
    
    public static final String sub2Chk6Byear_COLUMN = "SUB2_CHK6_BYEAR";
    
    public static final String sub2Chk6Lyear_COLUMN = "SUB2_CHK6_LYEAR";
    
    public static final String sub2Chk6Lkai_COLUMN = "SUB2_CHK6_LKAI";
    
    public static final String sub2Chk6LastRslt_COLUMN = "SUB2_CHK6_LAST_RSLT";
    
    public static final String sub2Chk6Result_COLUMN = "SUB2_CHK6_RESULT";
    
    public static final String sub2Chk7Byear_COLUMN = "SUB2_CHK7_BYEAR";
    
    public static final String sub2Chk7Lyear_COLUMN = "SUB2_CHK7_LYEAR";
    
    public static final String sub2Chk7Lkai_COLUMN = "SUB2_CHK7_LKAI";
    
    public static final String sub2Chk7LastRslt_COLUMN = "SUB2_CHK7_LAST_RSLT";
    
    public static final String sub2Chk7Result_COLUMN = "SUB2_CHK7_RESULT";
    
    public static final String sub2Chk8Byear_COLUMN = "SUB2_CHK8_BYEAR";
    
    public static final String sub2Chk8Lyear_COLUMN = "SUB2_CHK8_LYEAR";
    
    public static final String sub2Chk8Lkai_COLUMN = "SUB2_CHK8_LKAI";
    
    public static final String sub2Chk8LastRslt_COLUMN = "SUB2_CHK8_LAST_RSLT";
    
    public static final String sub2Chk8Result_COLUMN = "SUB2_CHK8_RESULT";
    
    public static final String sub2Chk9Byear_COLUMN = "SUB2_CHK9_BYEAR";
    
    public static final String sub2Chk9Lyear_COLUMN = "SUB2_CHK9_LYEAR";
    
    public static final String sub2Chk9Lkai_COLUMN = "SUB2_CHK9_LKAI";
    
    public static final String sub2Chk9LastRslt_COLUMN = "SUB2_CHK9_LAST_RSLT";
    
    public static final String sub2Chk9Result_COLUMN = "SUB2_CHK9_RESULT";
    
    public static final String sub2Chk10Byear_COLUMN = "SUB2_CHK10_BYEAR";
    
    public static final String sub2Chk10Lyear_COLUMN = "SUB2_CHK10_LYEAR";
    
    public static final String sub2Chk10Lkai_COLUMN = "SUB2_CHK10_LKAI";
    
    public static final String sub2Chk10LastRslt_COLUMN = "SUB2_CHK10_LAST_RSLT";
    
    public static final String sub2Chk10Result_COLUMN = "SUB2_CHK10_RESULT";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String examNo_COLUMN = "EXAM_NO";
    
    public static final String sub3Result_COLUMN = "SUB3_RESULT";
    
    public static final String interviewDt_COLUMN = "INTERVIEW_DT";
    
    public static final String insertFlg_COLUMN = "INSERT_FLG";
    
    public static final String interviewChk_COLUMN = "INTERVIEW_CHK";
    
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
     * スタッフ氏（漢字）
     */
    private String staffLNameKj;
    
    /**
     * スタッフ名（漢字）
     */
    private String staffFNameKj;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称漢字
     */
    private String onerNameKj;
    
    /**
     * 店コード１
     */
    private String miseCd1;
    
    /**
     * 店名称漢字
     */
    private String miseNameKj;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 面接官
     */
    private String assesser;
    
    /**
     * 面接カテゴリ１再エントリ基準年度
     */
    private String sub2Chk1Byear;
    
    /**
     * 面接カテゴリ１最終受験年度
     */
    private String sub2Chk1Lyear;
    
    /**
     * 面接カテゴリ１最終受験回
     */
    private String sub2Chk1Lkai;
    
    /**
     * 面接カテゴリ１前回結果
     */
    private String sub2Chk1LastRslt;
    
    /**
     * 面接カテゴリ１結果
     */
    private String sub2Chk1Result;
    
    /**
     * 面接カテゴリ2再エントリ基準年度
     */
    private String sub2Chk2Byear;
    
    /**
     * 面接カテゴリ2最終受験年度
     */
    private String sub2Chk2Lyear;
    
    /**
     * 面接カテゴリ2最終受験回
     */
    private String sub2Chk2Lkai;
    
    /**
     * 面接カテゴリ2前回結果
     */
    private String sub2Chk2LastRslt;
    
    /**
     * 面接カテゴリ2結果
     */
    private String sub2Chk2Result;
    
    /**
     * 面接カテゴリ3再エントリ基準年度
     */
    private String sub2Chk3Byear;
    
    /**
     * 面接カテゴリ3最終受験年度
     */
    private String sub2Chk3Lyear;
    
    /**
     * 面接カテゴリ3最終受験回
     */
    private String sub2Chk3Lkai;
    
    /**
     * 面接カテゴリ3前回結果
     */
    private String sub2Chk3LastRslt;
    
    /**
     * 面接カテゴリ3結果
     */
    private String sub2Chk3Result;
    
    /**
     * 面接カテゴリ4再エントリ基準年度
     */
    private String sub2Chk4Byear;
    
    /**
     * 面接カテゴリ4最終受験年度
     */
    private String sub2Chk4Lyear;
    
    /**
     * 面接カテゴリ4最終受験回
     */
    private String sub2Chk4Lkai;
    
    /**
     * 面接カテゴリ4前回結果
     */
    private String sub2Chk4LastRslt;
    
    /**
     * 面接カテゴリ4結果
     */
    private String sub2Chk4Result;
    
    /**
     * 面接カテゴリ5再エントリ基準年度
     */
    private String sub2Chk5Byear;
    
    /**
     * 面接カテゴリ5最終受験年度
     */
    private String sub2Chk5Lyear;
    
    /**
     * 面接カテゴリ5最終受験回
     */
    private String sub2Chk5Lkai;
    
    /**
     * 面接カテゴリ5前回結果
     */
    private String sub2Chk5LastRslt;
    
    /**
     * 面接カテゴリ5結果
     */
    private String sub2Chk5Result;
    
    /**
     * 面接カテゴリ6再エントリ基準年度
     */
    private String sub2Chk6Byear;
    
    /**
     * 面接カテゴリ6最終受験年度
     */
    private String sub2Chk6Lyear;
    
    /**
     * 面接カテゴリ6最終受験回
     */
    private String sub2Chk6Lkai;
    
    /**
     * 面接カテゴリ6前回結果
     */
    private String sub2Chk6LastRslt;
    
    /**
     * 面接カテゴリ6結果
     */
    private String sub2Chk6Result;
    
    /**
     * 面接カテゴリ7再エントリ基準年度
     */
    private String sub2Chk7Byear;
    
    /**
     * 面接カテゴリ7最終受験年度
     */
    private String sub2Chk7Lyear;
    
    /**
     * 面接カテゴリ7最終受験回
     */
    private String sub2Chk7Lkai;
    
    /**
     * 面接カテゴリ7前回結果
     */
    private String sub2Chk7LastRslt;
    
    /**
     * 面接カテゴリ7結果
     */
    private String sub2Chk7Result;
    
    /**
     * 面接カテゴリ8再エントリ基準年度
     */
    private String sub2Chk8Byear;
    
    /**
     * 面接カテゴリ8最終受験年度
     */
    private String sub2Chk8Lyear;
    
    /**
     * 面接カテゴリ8最終受験回
     */
    private String sub2Chk8Lkai;
    
    /**
     * 面接カテゴリ8前回結果
     */
    private String sub2Chk8LastRslt;
    
    /**
     * 面接カテゴリ8結果
     */
    private String sub2Chk8Result;
    
    /**
     * 面接カテゴリ9再エントリ基準年度
     */
    private String sub2Chk9Byear;
    
    /**
     * 面接カテゴリ9最終受験年度
     */
    private String sub2Chk9Lyear;
    
    /**
     * 面接カテゴリ9最終受験回
     */
    private String sub2Chk9Lkai;
    
    /**
     * 面接カテゴリ9前回結果
     */
    private String sub2Chk9LastRslt;
    
    /**
     * 面接カテゴリ9結果
     */
    private String sub2Chk9Result;
    
    /**
     * 面接カテゴリ１0再エントリ基準年度
     */
    private String sub2Chk10Byear;
    
    /**
     * 面接カテゴリ１0最終受験年度
     */
    private String sub2Chk10Lyear;
    
    /**
     * 面接カテゴリ１0最終受験回
     */
    private String sub2Chk10Lkai;
    
    /**
     * 面接カテゴリ１0前回結果
     */
    private String sub2Chk10LastRslt;
    
    /**
     * 面接カテゴリ１0結果
     */
    private String sub2Chk10Result;
    
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
     * 受験番号
     */
    private String examNo;

    /**
     * 面接結果
     */
    private String sub3Result;

    /**
     * チェックボックス用
     */
    private boolean sub2Chk1ResultForChkbox;
    private boolean sub2Chk2ResultForChkbox;
    private boolean sub2Chk3ResultForChkbox;
    private boolean sub2Chk4ResultForChkbox;
    private boolean sub2Chk5ResultForChkbox;
    private boolean sub2Chk6ResultForChkbox;
    private boolean sub2Chk7ResultForChkbox;
    private boolean sub2Chk8ResultForChkbox;
    private boolean sub2Chk9ResultForChkbox;
    private boolean sub2Chk10ResultForChkbox;
    
    /**
     * 面接日
     */
    private String interviewDt;
    
    /**
     * 面接試験の合否判定
     * JudgeInterviewLogicにより全カテゴリの結果を考慮し判定される
     */
    private String hantei;
    
    /**
     * 新規フラグ
     */
    private boolean insertFlg;
    
    /**
     * 新規フラグ
     */
    private String interviewChk;
    
    
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
     * オーナー名称漢字を取得します。
     * @return オーナー名称漢字
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称漢字を設定します。
     * @param onerNameKj オーナー名称漢字
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
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
     * 店名称漢字を取得します。
     * @return 店名称漢字
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称漢字を設定します。
     * @param miseNameKj 店名称漢字
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
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
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * 面接官を取得します。
     * @return 面接官
     */
    public String getAssesser() {
        return assesser;
    }
    /**
     * 面接官を設定します。
     * @param assesser 面接官
     */
    public void setAssesser(String assesser) {
        this.assesser = assesser;
    }
    
    /**
     * 面接カテゴリ１再エントリ基準年度を取得します。
     * @return 面接カテゴリ１再エントリ基準年度
     */
    public String getSub2Chk1Byear() {
        return sub2Chk1Byear;
    }
    /**
     * 面接カテゴリ１再エントリ基準年度を設定します。
     * @param sub2Chk1Byear 面接カテゴリ１再エントリ基準年度
     */
    public void setSub2Chk1Byear(String sub2Chk1Byear) {
        this.sub2Chk1Byear = sub2Chk1Byear;
    }
    
    /**
     * 面接カテゴリ１最終受験年度を取得します。
     * @return 面接カテゴリ１最終受験年度
     */
    public String getSub2Chk1Lyear() {
        return sub2Chk1Lyear;
    }
    /**
     * 面接カテゴリ１最終受験年度を設定します。
     * @param sub2Chk1Lyear 面接カテゴリ１最終受験年度
     */
    public void setSub2Chk1Lyear(String sub2Chk1Lyear) {
        this.sub2Chk1Lyear = sub2Chk1Lyear;
    }
    
    /**
     * 面接カテゴリ１最終受験回を取得します。
     * @return 面接カテゴリ１最終受験回
     */
    public String getSub2Chk1Lkai() {
        return sub2Chk1Lkai;
    }
    /**
     * 面接カテゴリ１最終受験回を設定します。
     * @param sub2Chk1Lkai 面接カテゴリ１最終受験回
     */
    public void setSub2Chk1Lkai(String sub2Chk1Lkai) {
        this.sub2Chk1Lkai = sub2Chk1Lkai;
    }
    
    /**
     * 面接カテゴリ１前回結果を取得します。
     * @return 面接カテゴリ１前回結果
     */
    public String getSub2Chk1LastRslt() {
        return sub2Chk1LastRslt;
    }
    /**
     * 面接カテゴリ１前回結果を設定します。
     * @param sub2Chk1LastRslt 面接カテゴリ１前回結果
     */
    public void setSub2Chk1LastRslt(String sub2Chk1LastRslt) {
        this.sub2Chk1LastRslt = sub2Chk1LastRslt;
    }
    
    /**
     * 面接カテゴリ１結果を取得します。
     * @return 面接カテゴリ１結果
     */
    public String getSub2Chk1Result() {
        return sub2Chk1Result;
    }
    /**
     * 面接カテゴリ１結果を設定します。
     * @param sub2Chk1Result 面接カテゴリ１結果
     */
    public void setSub2Chk1Result(String sub2Chk1Result) {
        this.sub2Chk1Result = sub2Chk1Result;
    }
    
    /**
     * 面接カテゴリ2再エントリ基準年度を取得します。
     * @return 面接カテゴリ2再エントリ基準年度
     */
    public String getSub2Chk2Byear() {
        return sub2Chk2Byear;
    }
    /**
     * 面接カテゴリ2再エントリ基準年度を設定します。
     * @param sub2Chk2Byear 面接カテゴリ2再エントリ基準年度
     */
    public void setSub2Chk2Byear(String sub2Chk2Byear) {
        this.sub2Chk2Byear = sub2Chk2Byear;
    }
    
    /**
     * 面接カテゴリ2最終受験年度を取得します。
     * @return 面接カテゴリ2最終受験年度
     */
    public String getSub2Chk2Lyear() {
        return sub2Chk2Lyear;
    }
    /**
     * 面接カテゴリ2最終受験年度を設定します。
     * @param sub2Chk2Lyear 面接カテゴリ2最終受験年度
     */
    public void setSub2Chk2Lyear(String sub2Chk2Lyear) {
        this.sub2Chk2Lyear = sub2Chk2Lyear;
    }
    
    /**
     * 面接カテゴリ2最終受験回を取得します。
     * @return 面接カテゴリ2最終受験回
     */
    public String getSub2Chk2Lkai() {
        return sub2Chk2Lkai;
    }
    /**
     * 面接カテゴリ2最終受験回を設定します。
     * @param sub2Chk2Lkai 面接カテゴリ2最終受験回
     */
    public void setSub2Chk2Lkai(String sub2Chk2Lkai) {
        this.sub2Chk2Lkai = sub2Chk2Lkai;
    }
    
    /**
     * 面接カテゴリ2前回結果を取得します。
     * @return 面接カテゴリ2前回結果
     */
    public String getSub2Chk2LastRslt() {
        return sub2Chk2LastRslt;
    }
    /**
     * 面接カテゴリ2前回結果を設定します。
     * @param sub2Chk2LastRslt 面接カテゴリ2前回結果
     */
    public void setSub2Chk2LastRslt(String sub2Chk2LastRslt) {
        this.sub2Chk2LastRslt = sub2Chk2LastRslt;
    }
    
    /**
     * 面接カテゴリ2結果を取得します。
     * @return 面接カテゴリ2結果
     */
    public String getSub2Chk2Result() {
        return sub2Chk2Result;
    }
    /**
     * 面接カテゴリ2結果を設定します。
     * @param sub2Chk2Result 面接カテゴリ2結果
     */
    public void setSub2Chk2Result(String sub2Chk2Result) {
        this.sub2Chk2Result = sub2Chk2Result;
    }
    
    /**
     * 面接カテゴリ3再エントリ基準年度を取得します。
     * @return 面接カテゴリ3再エントリ基準年度
     */
    public String getSub2Chk3Byear() {
        return sub2Chk3Byear;
    }
    /**
     * 面接カテゴリ3再エントリ基準年度を設定します。
     * @param sub2Chk3Byear 面接カテゴリ3再エントリ基準年度
     */
    public void setSub2Chk3Byear(String sub2Chk3Byear) {
        this.sub2Chk3Byear = sub2Chk3Byear;
    }
    
    /**
     * 面接カテゴリ3最終受験年度を取得します。
     * @return 面接カテゴリ3最終受験年度
     */
    public String getSub2Chk3Lyear() {
        return sub2Chk3Lyear;
    }
    /**
     * 面接カテゴリ3最終受験年度を設定します。
     * @param sub2Chk3Lyear 面接カテゴリ3最終受験年度
     */
    public void setSub2Chk3Lyear(String sub2Chk3Lyear) {
        this.sub2Chk3Lyear = sub2Chk3Lyear;
    }
    
    /**
     * 面接カテゴリ3最終受験回を取得します。
     * @return 面接カテゴリ3最終受験回
     */
    public String getSub2Chk3Lkai() {
        return sub2Chk3Lkai;
    }
    /**
     * 面接カテゴリ3最終受験回を設定します。
     * @param sub2Chk3Lkai 面接カテゴリ3最終受験回
     */
    public void setSub2Chk3Lkai(String sub2Chk3Lkai) {
        this.sub2Chk3Lkai = sub2Chk3Lkai;
    }
    
    /**
     * 面接カテゴリ3前回結果を取得します。
     * @return 面接カテゴリ3前回結果
     */
    public String getSub2Chk3LastRslt() {
        return sub2Chk3LastRslt;
    }
    /**
     * 面接カテゴリ3前回結果を設定します。
     * @param sub2Chk3LastRslt 面接カテゴリ3前回結果
     */
    public void setSub2Chk3LastRslt(String sub2Chk3LastRslt) {
        this.sub2Chk3LastRslt = sub2Chk3LastRslt;
    }
    
    /**
     * 面接カテゴリ3結果を取得します。
     * @return 面接カテゴリ3結果
     */
    public String getSub2Chk3Result() {
        return sub2Chk3Result;
    }
    /**
     * 面接カテゴリ3結果を設定します。
     * @param sub2Chk3Result 面接カテゴリ3結果
     */
    public void setSub2Chk3Result(String sub2Chk3Result) {
        this.sub2Chk3Result = sub2Chk3Result;
    }
    
    /**
     * 面接カテゴリ4再エントリ基準年度を取得します。
     * @return 面接カテゴリ4再エントリ基準年度
     */
    public String getSub2Chk4Byear() {
        return sub2Chk4Byear;
    }
    /**
     * 面接カテゴリ4再エントリ基準年度を設定します。
     * @param sub2Chk4Byear 面接カテゴリ4再エントリ基準年度
     */
    public void setSub2Chk4Byear(String sub2Chk4Byear) {
        this.sub2Chk4Byear = sub2Chk4Byear;
    }
    
    /**
     * 面接カテゴリ4最終受験年度を取得します。
     * @return 面接カテゴリ4最終受験年度
     */
    public String getSub2Chk4Lyear() {
        return sub2Chk4Lyear;
    }
    /**
     * 面接カテゴリ4最終受験年度を設定します。
     * @param sub2Chk4Lyear 面接カテゴリ4最終受験年度
     */
    public void setSub2Chk4Lyear(String sub2Chk4Lyear) {
        this.sub2Chk4Lyear = sub2Chk4Lyear;
    }
    
    /**
     * 面接カテゴリ4最終受験回を取得します。
     * @return 面接カテゴリ4最終受験回
     */
    public String getSub2Chk4Lkai() {
        return sub2Chk4Lkai;
    }
    /**
     * 面接カテゴリ4最終受験回を設定します。
     * @param sub2Chk4Lkai 面接カテゴリ4最終受験回
     */
    public void setSub2Chk4Lkai(String sub2Chk4Lkai) {
        this.sub2Chk4Lkai = sub2Chk4Lkai;
    }
    
    /**
     * 面接カテゴリ4前回結果を取得します。
     * @return 面接カテゴリ4前回結果
     */
    public String getSub2Chk4LastRslt() {
        return sub2Chk4LastRslt;
    }
    /**
     * 面接カテゴリ4前回結果を設定します。
     * @param sub2Chk4LastRslt 面接カテゴリ4前回結果
     */
    public void setSub2Chk4LastRslt(String sub2Chk4LastRslt) {
        this.sub2Chk4LastRslt = sub2Chk4LastRslt;
    }
    
    /**
     * 面接カテゴリ4結果を取得します。
     * @return 面接カテゴリ4結果
     */
    public String getSub2Chk4Result() {
        return sub2Chk4Result;
    }
    /**
     * 面接カテゴリ4結果を設定します。
     * @param sub2Chk4Result 面接カテゴリ4結果
     */
    public void setSub2Chk4Result(String sub2Chk4Result) {
        this.sub2Chk4Result = sub2Chk4Result;
    }
    
    /**
     * 面接カテゴリ5再エントリ基準年度を取得します。
     * @return 面接カテゴリ5再エントリ基準年度
     */
    public String getSub2Chk5Byear() {
        return sub2Chk5Byear;
    }
    /**
     * 面接カテゴリ5再エントリ基準年度を設定します。
     * @param sub2Chk5Byear 面接カテゴリ5再エントリ基準年度
     */
    public void setSub2Chk5Byear(String sub2Chk5Byear) {
        this.sub2Chk5Byear = sub2Chk5Byear;
    }
    
    /**
     * 面接カテゴリ5最終受験年度を取得します。
     * @return 面接カテゴリ5最終受験年度
     */
    public String getSub2Chk5Lyear() {
        return sub2Chk5Lyear;
    }
    /**
     * 面接カテゴリ5最終受験年度を設定します。
     * @param sub2Chk5Lyear 面接カテゴリ5最終受験年度
     */
    public void setSub2Chk5Lyear(String sub2Chk5Lyear) {
        this.sub2Chk5Lyear = sub2Chk5Lyear;
    }
    
    /**
     * 面接カテゴリ5最終受験回を取得します。
     * @return 面接カテゴリ5最終受験回
     */
    public String getSub2Chk5Lkai() {
        return sub2Chk5Lkai;
    }
    /**
     * 面接カテゴリ5最終受験回を設定します。
     * @param sub2Chk5Lkai 面接カテゴリ5最終受験回
     */
    public void setSub2Chk5Lkai(String sub2Chk5Lkai) {
        this.sub2Chk5Lkai = sub2Chk5Lkai;
    }
    
    /**
     * 面接カテゴリ5前回結果を取得します。
     * @return 面接カテゴリ5前回結果
     */
    public String getSub2Chk5LastRslt() {
        return sub2Chk5LastRslt;
    }
    /**
     * 面接カテゴリ5前回結果を設定します。
     * @param sub2Chk5LastRslt 面接カテゴリ5前回結果
     */
    public void setSub2Chk5LastRslt(String sub2Chk5LastRslt) {
        this.sub2Chk5LastRslt = sub2Chk5LastRslt;
    }
    
    /**
     * 面接カテゴリ5結果を取得します。
     * @return 面接カテゴリ5結果
     */
    public String getSub2Chk5Result() {
        return sub2Chk5Result;
    }
    /**
     * 面接カテゴリ5結果を設定します。
     * @param sub2Chk5Result 面接カテゴリ5結果
     */
    public void setSub2Chk5Result(String sub2Chk5Result) {
        this.sub2Chk5Result = sub2Chk5Result;
    }
    
    /**
     * 面接カテゴリ6再エントリ基準年度を取得します。
     * @return 面接カテゴリ6再エントリ基準年度
     */
    public String getSub2Chk6Byear() {
        return sub2Chk6Byear;
    }
    /**
     * 面接カテゴリ6再エントリ基準年度を設定します。
     * @param sub2Chk6Byear 面接カテゴリ6再エントリ基準年度
     */
    public void setSub2Chk6Byear(String sub2Chk6Byear) {
        this.sub2Chk6Byear = sub2Chk6Byear;
    }
    
    /**
     * 面接カテゴリ6最終受験年度を取得します。
     * @return 面接カテゴリ6最終受験年度
     */
    public String getSub2Chk6Lyear() {
        return sub2Chk6Lyear;
    }
    /**
     * 面接カテゴリ6最終受験年度を設定します。
     * @param sub2Chk6Lyear 面接カテゴリ6最終受験年度
     */
    public void setSub2Chk6Lyear(String sub2Chk6Lyear) {
        this.sub2Chk6Lyear = sub2Chk6Lyear;
    }
    
    /**
     * 面接カテゴリ6最終受験回を取得します。
     * @return 面接カテゴリ6最終受験回
     */
    public String getSub2Chk6Lkai() {
        return sub2Chk6Lkai;
    }
    /**
     * 面接カテゴリ6最終受験回を設定します。
     * @param sub2Chk6Lkai 面接カテゴリ6最終受験回
     */
    public void setSub2Chk6Lkai(String sub2Chk6Lkai) {
        this.sub2Chk6Lkai = sub2Chk6Lkai;
    }
    
    /**
     * 面接カテゴリ6前回結果を取得します。
     * @return 面接カテゴリ6前回結果
     */
    public String getSub2Chk6LastRslt() {
        return sub2Chk6LastRslt;
    }
    /**
     * 面接カテゴリ6前回結果を設定します。
     * @param sub2Chk6LastRslt 面接カテゴリ6前回結果
     */
    public void setSub2Chk6LastRslt(String sub2Chk6LastRslt) {
        this.sub2Chk6LastRslt = sub2Chk6LastRslt;
    }
    
    /**
     * 面接カテゴリ6結果を取得します。
     * @return 面接カテゴリ6結果
     */
    public String getSub2Chk6Result() {
        return sub2Chk6Result;
    }
    /**
     * 面接カテゴリ6結果を設定します。
     * @param sub2Chk6Result 面接カテゴリ6結果
     */
    public void setSub2Chk6Result(String sub2Chk6Result) {
        this.sub2Chk6Result = sub2Chk6Result;
    }
    
    /**
     * 面接カテゴリ7再エントリ基準年度を取得します。
     * @return 面接カテゴリ7再エントリ基準年度
     */
    public String getSub2Chk7Byear() {
        return sub2Chk7Byear;
    }
    /**
     * 面接カテゴリ7再エントリ基準年度を設定します。
     * @param sub2Chk7Byear 面接カテゴリ7再エントリ基準年度
     */
    public void setSub2Chk7Byear(String sub2Chk7Byear) {
        this.sub2Chk7Byear = sub2Chk7Byear;
    }
    
    /**
     * 面接カテゴリ7最終受験年度を取得します。
     * @return 面接カテゴリ7最終受験年度
     */
    public String getSub2Chk7Lyear() {
        return sub2Chk7Lyear;
    }
    /**
     * 面接カテゴリ7最終受験年度を設定します。
     * @param sub2Chk7Lyear 面接カテゴリ7最終受験年度
     */
    public void setSub2Chk7Lyear(String sub2Chk7Lyear) {
        this.sub2Chk7Lyear = sub2Chk7Lyear;
    }
    
    /**
     * 面接カテゴリ7最終受験回を取得します。
     * @return 面接カテゴリ7最終受験回
     */
    public String getSub2Chk7Lkai() {
        return sub2Chk7Lkai;
    }
    /**
     * 面接カテゴリ7最終受験回を設定します。
     * @param sub2Chk7Lkai 面接カテゴリ7最終受験回
     */
    public void setSub2Chk7Lkai(String sub2Chk7Lkai) {
        this.sub2Chk7Lkai = sub2Chk7Lkai;
    }
    
    /**
     * 面接カテゴリ7前回結果を取得します。
     * @return 面接カテゴリ7前回結果
     */
    public String getSub2Chk7LastRslt() {
        return sub2Chk7LastRslt;
    }
    /**
     * 面接カテゴリ7前回結果を設定します。
     * @param sub2Chk7LastRslt 面接カテゴリ7前回結果
     */
    public void setSub2Chk7LastRslt(String sub2Chk7LastRslt) {
        this.sub2Chk7LastRslt = sub2Chk7LastRslt;
    }
    
    /**
     * 面接カテゴリ7結果を取得します。
     * @return 面接カテゴリ7結果
     */
    public String getSub2Chk7Result() {
        return sub2Chk7Result;
    }
    /**
     * 面接カテゴリ7結果を設定します。
     * @param sub2Chk7Result 面接カテゴリ7結果
     */
    public void setSub2Chk7Result(String sub2Chk7Result) {
        this.sub2Chk7Result = sub2Chk7Result;
    }
    
    /**
     * 面接カテゴリ8再エントリ基準年度を取得します。
     * @return 面接カテゴリ8再エントリ基準年度
     */
    public String getSub2Chk8Byear() {
        return sub2Chk8Byear;
    }
    /**
     * 面接カテゴリ8再エントリ基準年度を設定します。
     * @param sub2Chk8Byear 面接カテゴリ8再エントリ基準年度
     */
    public void setSub2Chk8Byear(String sub2Chk8Byear) {
        this.sub2Chk8Byear = sub2Chk8Byear;
    }
    
    /**
     * 面接カテゴリ8最終受験年度を取得します。
     * @return 面接カテゴリ8最終受験年度
     */
    public String getSub2Chk8Lyear() {
        return sub2Chk8Lyear;
    }
    /**
     * 面接カテゴリ8最終受験年度を設定します。
     * @param sub2Chk8Lyear 面接カテゴリ8最終受験年度
     */
    public void setSub2Chk8Lyear(String sub2Chk8Lyear) {
        this.sub2Chk8Lyear = sub2Chk8Lyear;
    }
    
    /**
     * 面接カテゴリ8最終受験回を取得します。
     * @return 面接カテゴリ8最終受験回
     */
    public String getSub2Chk8Lkai() {
        return sub2Chk8Lkai;
    }
    /**
     * 面接カテゴリ8最終受験回を設定します。
     * @param sub2Chk8Lkai 面接カテゴリ8最終受験回
     */
    public void setSub2Chk8Lkai(String sub2Chk8Lkai) {
        this.sub2Chk8Lkai = sub2Chk8Lkai;
    }
    
    /**
     * 面接カテゴリ8前回結果を取得します。
     * @return 面接カテゴリ8前回結果
     */
    public String getSub2Chk8LastRslt() {
        return sub2Chk8LastRslt;
    }
    /**
     * 面接カテゴリ8前回結果を設定します。
     * @param sub2Chk8LastRslt 面接カテゴリ8前回結果
     */
    public void setSub2Chk8LastRslt(String sub2Chk8LastRslt) {
        this.sub2Chk8LastRslt = sub2Chk8LastRslt;
    }
    
    /**
     * 面接カテゴリ8結果を取得します。
     * @return 面接カテゴリ8結果
     */
    public String getSub2Chk8Result() {
        return sub2Chk8Result;
    }
    /**
     * 面接カテゴリ8結果を設定します。
     * @param sub2Chk8Result 面接カテゴリ8結果
     */
    public void setSub2Chk8Result(String sub2Chk8Result) {
        this.sub2Chk8Result = sub2Chk8Result;
    }
    
    /**
     * 面接カテゴリ9再エントリ基準年度を取得します。
     * @return 面接カテゴリ9再エントリ基準年度
     */
    public String getSub2Chk9Byear() {
        return sub2Chk9Byear;
    }
    /**
     * 面接カテゴリ9再エントリ基準年度を設定します。
     * @param sub2Chk9Byear 面接カテゴリ9再エントリ基準年度
     */
    public void setSub2Chk9Byear(String sub2Chk9Byear) {
        this.sub2Chk9Byear = sub2Chk9Byear;
    }
    
    /**
     * 面接カテゴリ9最終受験年度を取得します。
     * @return 面接カテゴリ9最終受験年度
     */
    public String getSub2Chk9Lyear() {
        return sub2Chk9Lyear;
    }
    /**
     * 面接カテゴリ9最終受験年度を設定します。
     * @param sub2Chk9Lyear 面接カテゴリ9最終受験年度
     */
    public void setSub2Chk9Lyear(String sub2Chk9Lyear) {
        this.sub2Chk9Lyear = sub2Chk9Lyear;
    }
    
    /**
     * 面接カテゴリ9最終受験回を取得します。
     * @return 面接カテゴリ9最終受験回
     */
    public String getSub2Chk9Lkai() {
        return sub2Chk9Lkai;
    }
    /**
     * 面接カテゴリ9最終受験回を設定します。
     * @param sub2Chk9Lkai 面接カテゴリ9最終受験回
     */
    public void setSub2Chk9Lkai(String sub2Chk9Lkai) {
        this.sub2Chk9Lkai = sub2Chk9Lkai;
    }
    
    /**
     * 面接カテゴリ9前回結果を取得します。
     * @return 面接カテゴリ9前回結果
     */
    public String getSub2Chk9LastRslt() {
        return sub2Chk9LastRslt;
    }
    /**
     * 面接カテゴリ9前回結果を設定します。
     * @param sub2Chk9LastRslt 面接カテゴリ9前回結果
     */
    public void setSub2Chk9LastRslt(String sub2Chk9LastRslt) {
        this.sub2Chk9LastRslt = sub2Chk9LastRslt;
    }
    
    /**
     * 面接カテゴリ9結果を取得します。
     * @return 面接カテゴリ9結果
     */
    public String getSub2Chk9Result() {
        return sub2Chk9Result;
    }
    /**
     * 面接カテゴリ9結果を設定します。
     * @param sub2Chk9Result 面接カテゴリ9結果
     */
    public void setSub2Chk9Result(String sub2Chk9Result) {
        this.sub2Chk9Result = sub2Chk9Result;
    }
    
    /**
     * 面接カテゴリ１0再エントリ基準年度を取得します。
     * @return 面接カテゴリ１0再エントリ基準年度
     */
    public String getSub2Chk10Byear() {
        return sub2Chk10Byear;
    }
    /**
     * 面接カテゴリ１0再エントリ基準年度を設定します。
     * @param sub2Chk10Byear 面接カテゴリ１0再エントリ基準年度
     */
    public void setSub2Chk10Byear(String sub2Chk10Byear) {
        this.sub2Chk10Byear = sub2Chk10Byear;
    }
    
    /**
     * 面接カテゴリ１0最終受験年度を取得します。
     * @return 面接カテゴリ１0最終受験年度
     */
    public String getSub2Chk10Lyear() {
        return sub2Chk10Lyear;
    }
    /**
     * 面接カテゴリ１0最終受験年度を設定します。
     * @param sub2Chk10Lyear 面接カテゴリ１0最終受験年度
     */
    public void setSub2Chk10Lyear(String sub2Chk10Lyear) {
        this.sub2Chk10Lyear = sub2Chk10Lyear;
    }
    
    /**
     * 面接カテゴリ１0最終受験回を取得します。
     * @return 面接カテゴリ１0最終受験回
     */
    public String getSub2Chk10Lkai() {
        return sub2Chk10Lkai;
    }
    /**
     * 面接カテゴリ１0最終受験回を設定します。
     * @param sub2Chk10Lkai 面接カテゴリ１0最終受験回
     */
    public void setSub2Chk10Lkai(String sub2Chk10Lkai) {
        this.sub2Chk10Lkai = sub2Chk10Lkai;
    }
    
    /**
     * 面接カテゴリ１0前回結果を取得します。
     * @return 面接カテゴリ１0前回結果
     */
    public String getSub2Chk10LastRslt() {
        return sub2Chk10LastRslt;
    }
    /**
     * 面接カテゴリ１0前回結果を設定します。
     * @param sub2Chk10LastRslt 面接カテゴリ１0前回結果
     */
    public void setSub2Chk10LastRslt(String sub2Chk10LastRslt) {
        this.sub2Chk10LastRslt = sub2Chk10LastRslt;
    }
    
    /**
     * 面接カテゴリ１0結果を取得します。
     * @return 面接カテゴリ１0結果
     */
    public String getSub2Chk10Result() {
        return sub2Chk10Result;
    }
    /**
     * 面接カテゴリ１0結果を設定します。
     * @param sub2Chk10Result 面接カテゴリ１0結果
     */
    public void setSub2Chk10Result(String sub2Chk10Result) {
        this.sub2Chk10Result = sub2Chk10Result;
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
     * 面接結果を取得します。
     * @return 面接結果
     */
    public String getSub3Result() {
        return sub3Result;
    }
    /**
     * 面接結果を設定します。
     * @param sub3Result 面接結果
     */
    public void setSub3Result(String sub3Result) {
        this.sub3Result = sub3Result;
    }

    /**
     * 面接カテゴリ１結果 チェックボック用
     */
    public boolean isSub2Chk1ResultForChkbox() {
        return sub2Chk1ResultForChkbox;
    }
    public void setSub2Chk1ResultForChkbox(boolean flg) {
        this.sub2Chk1ResultForChkbox = flg;
    }
    /**
     * 面接カテゴリ2結果 チェックボック用
     */
    public boolean isSub2Chk2ResultForChkbox() {
        return sub2Chk2ResultForChkbox;
    }
    public void setSub2Chk2ResultForChkbox(boolean flg) {
        this.sub2Chk2ResultForChkbox = flg;
    }
    /**
     * 面接カテゴリ3結果 チェックボック用
     */
    public boolean isSub2Chk3ResultForChkbox() {
        return sub2Chk3ResultForChkbox;
    }
    public void setSub2Chk3ResultForChkbox(boolean flg) {
        this.sub2Chk3ResultForChkbox = flg;
    }
    /**
     * 面接カテゴリ4結果 チェックボック用
     */
    public boolean isSub2Chk4ResultForChkbox() {
        return sub2Chk4ResultForChkbox;
    }
    public void setSub2Chk4ResultForChkbox(boolean flg) {
        this.sub2Chk4ResultForChkbox = flg;
    }
    /**
     * 面接カテゴリ5結果 チェックボック用
     */
    public boolean isSub2Chk5ResultForChkbox() {
        return sub2Chk5ResultForChkbox;
    }
    public void setSub2Chk5ResultForChkbox(boolean flg) {
        this.sub2Chk5ResultForChkbox = flg;
    }
    /**
     * 面接カテゴリ１結果 チェックボック用
     */
    public boolean isSub2Chk6ResultForChkbox() {
        return sub2Chk6ResultForChkbox;
    }
    public void setSub2Chk6ResultForChkbox(boolean flg) {
        this.sub2Chk6ResultForChkbox = flg;
    }
    /**
     * 面接カテゴリ7結果 チェックボック用
     */
    public boolean isSub2Chk7ResultForChkbox() {
        return sub2Chk7ResultForChkbox;
    }
    public void setSub2Chk7ResultForChkbox(boolean flg) {
        this.sub2Chk7ResultForChkbox = flg;
    }
    /**
     * 面接カテゴリ8結果 チェックボック用
     */
    public boolean isSub2Chk8ResultForChkbox() {
        return sub2Chk8ResultForChkbox;
    }
    public void setSub2Chk8ResultForChkbox(boolean flg) {
        this.sub2Chk8ResultForChkbox = flg;
    }
    /**
     * 面接カテゴリ9結果 チェックボック用
     */
    public boolean isSub2Chk9ResultForChkbox() {
        return sub2Chk9ResultForChkbox;
    }
    public void setSub2Chk9ResultForChkbox(boolean flg) {
        this.sub2Chk9ResultForChkbox = flg;
    }
    /**
     * 面接カテゴリ10結果 チェックボック用
     */
    public boolean isSub2Chk10ResultForChkbox() {
        return sub2Chk10ResultForChkbox;
    }
    public void setSub2Chk10ResultForChkbox(boolean flg) {
        this.sub2Chk10ResultForChkbox = flg;
    }
    
    /**
     * 判定
     */
    public String getHantei() {
        return hantei;
    }
    public void setHantei(String hantei) {
        this.hantei = hantei;
    }
    /**
     * 面接日取得処理
     * @return String
     */
    public String getInterviewDt() {
        return interviewDt;
    }
    /**
     * 面接日設定処理
     * @param interviewDt
     */
    public void setInterviewDt(String interviewDt) {
        this.interviewDt = interviewDt;
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
     * 面接エントリー状況を取得します。
     * @return 
     */
    public String getInterviewChk() {
        return interviewChk;
    }
    /**
     * 面接エントリー状況を設定します。
     * @param interviewChk 
     */
    public void setInterviewChk(String interviewChk) {
        this.interviewChk = interviewChk;
    }
    
}
