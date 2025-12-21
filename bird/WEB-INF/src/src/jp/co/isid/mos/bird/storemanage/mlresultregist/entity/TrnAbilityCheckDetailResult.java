package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

import java.sql.Timestamp;

public class TrnAbilityCheckDetailResult {
    
    public static final String TABLE = "BR46ABDR";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String koumokuCd_COLUMN = "KOUMOKU_CD";
    
    public static final String koumokuResult_COLUMN = "KOUMOKU_RESULT";
    
    public static final String categoryCd_COLUMN = "CATEGORY_CD";
    
    public static final String koumokuName_COLUMN = "KOUMOKU_NAME";
    
    public static final String koumokuContents_COLUMN = "KOUMOKU_CONTENTS";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String insertFlg_COLUMN = "INSERT_FLG";
    
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
     * 能力チェック項目コード
     */
    private String koumokuCd;
    
    /**
     * 能力チェック項目 結果
     */
    private String koumokuResult;
    
    /**
     * カテゴリーコード
     */
    private String categoryCd;
    
    /**
     * 能力チェック項目 名称
     */
    private String koumokuName;
    
    /**
     * 能力チェック項目 内容
     */
    private String koumokuContents;
    
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
     * 新規フラグ
     */
    private boolean insertFlg;
    
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
     * 能力チェック項目コードを取得します。
     * @return 能力チェック項目コード
     */
    public String getKoumokuCd() {
        return koumokuCd;
    }
    /**
     * 能力チェック項目コードを設定します。
     * @param koumokuCd 能力チェック項目コード
     */
    public void setKoumokuCd(String koumokuCd) {
        this.koumokuCd = koumokuCd;
    }
    
    /**
     * 能力チェック項目 結果を取得します。
     * @return 能力チェック項目 結果
     */
    public String getKoumokuResult() {
        return koumokuResult;
    }
    /**
     * 能力チェック項目 結果を設定します。
     * @param koumokuResult 能力チェック項目 結果
     */
    public void setKoumokuResult(String koumokuResult) {
        this.koumokuResult = koumokuResult;
    }
    
    /**
     * カテゴリーコードを取得します。
     * @return カテゴリーコード
     */
    public String getCategoryCd() {
        return categoryCd;
    }
    /**
     * カテゴリーコードを設定します。
     * @param categoryCd カテゴリーコード
     */
    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }
    
    /**
     * 能力チェック項目 名称を取得します。
     * @return 能力チェック項目 名称
     */
    public String getKoumokuName() {
        return koumokuName;
    }
    /**
     * 能力チェック項目 名称を設定します。
     * @param koumokuName 能力チェック項目 名称
     */
    public void setKoumokuName(String koumokuName) {
        this.koumokuName = koumokuName;
    }
    
    /**
     * 能力チェック項目 内容を取得します。
     * @return 能力チェック項目 内容
     */
    public String getKoumokuContents() {
        return koumokuContents;
    }
    /**
     * 能力チェック項目 内容を設定します。
     * @param koumokuContents 能力チェック項目 内容
     */
    public void setKoumokuContents(String koumokuContents) {
        this.koumokuContents = koumokuContents;
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

    public void setKoumokuResultFlag(boolean koumokuResultFlag) {
        setKoumokuResult((koumokuResultFlag) ? "1" : "0");
    }
    public boolean getKoumokuResultFlag() {
        return getKoumokuResult() != null && getKoumokuResult().trim().length() > 0 && !getKoumokuResult().equals("0");
    }
}
