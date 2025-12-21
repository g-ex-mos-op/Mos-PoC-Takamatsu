package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

import java.sql.Timestamp;

public class TrnEntryStatus {
    
    public static final String TABLE = "BT23MLEJ";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String staffLNameKj_COLUMN = "STAFF_L_NAME_KJ";
    
    public static final String staffFNameKj_COLUMN = "STAFF_F_NAME_KJ";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String examNo_COLUMN = "EXAM_NO";
    
    public static final String entryPlaceCd_COLUMN = "ENTRY_PLACE_CD";
    
    public static final String note_COLUMN = "NOTE";
    
    public static final String sub1Result_COLUMN = "SUB1_RESULT";
    
    public static final String sub2Result_COLUMN = "SUB2_RESULT";
    
    public static final String sub3Result_COLUMN = "SUB3_RESULT";
    
    public static final String otherChk1_COLUMN = "OTHER_CHK1";
    
    public static final String otherChk2_COLUMN = "OTHER_CHK2";
    
    public static final String miseCd1_COLUMN = "MISE_CD_1";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
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
     * スタッフ氏（漢字）
     */
    private String staffLNameKj;
    
    /**
     * スタッフ名（漢字）
     */
    private String staffFNameKj;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称漢字
     */
    private String onerNameKj;
    
    /**
     * 受験番号
     */
    private String examNo;
    
    /**
     * 受験希望地域コード
     */
    private String entryPlaceCd;
    
    /**
     * 備考
     */
    private String note;
    
    /**
     * 能力チェック
     */
    private String sub1Result;
    
    /**
     * 筆記テスト
     */
    private String sub2Result;
    
    /**
     * 面接
     */
    private String sub3Result;
    
    /**
     * その他１
     */
    private String otherChk1;
    
    /**
     * その他２
     */
    private String otherChk2;
    
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
     * 能力チェック結果表示
     */
    private String sub1ResultDisp;
    
    /**
     * 筆記テスト結果表示
     */
    private String sub2ResultDisp;
    
    /**
     * 面接結果表示
     */
    private String sub3ResultDisp;
    
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
     * 受験希望地域コードを取得します。
     * @return 受験希望地域コード
     */
    public String getEntryPlaceCd() {
        return entryPlaceCd;
    }
    /**
     * 受験希望地域コードを設定します。
     * @param entryPlaceCd 受験希望地域コード
     */
    public void setEntryPlaceCd(String entryPlaceCd) {
        this.entryPlaceCd = entryPlaceCd;
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
     * 能力チェックを取得します。
     * @return 能力チェック
     */
    public String getSub1Result() {
        return sub1Result;
    }
    /**
     * 能力チェックを設定します。
     * @param abilityChk 能力チェック
     */
    public void setSub1Result(String sub1Result) {
        this.sub1Result = sub1Result;
    }
    
    /**
     * 筆記テストを取得します。
     * @return 筆記テスト
     */
    public String getSub2Result() {
        return sub2Result;
    }
    /**
     * 筆記テストを設定します。
     * @param examChk 筆記テスト
     */
    public void setSub2Result(String sub2Result) {
        this.sub2Result = sub2Result;
    }
    
    /**
     * 面接を取得します。
     * @return 面接
     */
    public String getSub3Result() {
        return sub3Result;
    }
    /**
     * 面接を設定します。
     * @param interviewChk 面接
     */
    public void setSub3Result(String sub3Result) {
        this.sub3Result = sub3Result;
    }
    
    /**
     * その他１を取得します。
     * @return その他１
     */
    public String getOtherChk1() {
        return otherChk1;
    }
    /**
     * その他１を設定します。
     * @param otherChk1 その他１
     */
    public void setOtherChk1(String otherChk1) {
        this.otherChk1 = otherChk1;
    }
    
    /**
     * その他２を取得します。
     * @return その他２
     */
    public String getOtherChk2() {
        return otherChk2;
    }
    /**
     * その他２を設定します。
     * @param otherChk2 その他２
     */
    public void setOtherChk2(String otherChk2) {
        this.otherChk2 = otherChk2;
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
    public String getSub1ResultDisp() {
        return sub1ResultDisp;
    }
    public void setSub1ResultDisp(String sub1ResultDisp) {
        this.sub1ResultDisp = sub1ResultDisp;
    }
    public String getSub2ResultDisp() {
        return sub2ResultDisp;
    }
    public void setSub2ResultDisp(String sub2ResultDisp) {
        this.sub2ResultDisp = sub2ResultDisp;
    }
    public String getSub3ResultDisp() {
        return sub3ResultDisp;
    }
    public void setSub3ResultDisp(String sub3ResultDisp) {
        this.sub3ResultDisp = sub3ResultDisp;
    }
    
}
