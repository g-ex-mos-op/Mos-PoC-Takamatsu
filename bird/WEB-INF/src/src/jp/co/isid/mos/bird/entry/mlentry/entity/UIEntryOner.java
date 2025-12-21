package jp.co.isid.mos.bird.entry.mlentry.entity;

import java.sql.Timestamp;

public class UIEntryOner {
    
    public static final String TABLE = "BT21ENOJ";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String atesakiKbn_COLUMN = "ATESAKI_KBN";
    
    public static final String name_COLUMN = "NAME";
    
    public static final String tel_COLUMN = "TEL";
    
    public static final String zip_COLUMN = "ZIP";
    
    public static final String address1_COLUMN = "ADDRESS1";
    
    public static final String address2_COLUMN = "ADDRESS2";
    
    public static final String address3_COLUMN = "ADDRESS3";
    
    public static final String soufuName_COLUMN = "SOUFU_NAME";
    
    public static final String jobType_COLUMN = "JOB_TYPE";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
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
     * 企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * 宛先区分
     */
    private String atesakiKbn;
    
    /**
     * 氏名
     */
    private String name = "";
    
    /**
     * 電話番号
     */
    private String tel;
    
    /**
     * 郵便番号
     */
    private String zip = "";
    
    /**
     * 住所１
     */
    private String address1 = "";
    
    /**
     * 住所２
     */
    private String address2 = "";
    
    /**
     * 住所３
     */
    private String address3 = "";
    
    /**
     * 送付先名
     */
    private String soufuName;
    
    /**
     * 職位
     */
    private String jobType;
    
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
     * オーナー名称
     */
    private String onerNameKj;

    /**
     * 新規行フラグ true:新規
     */
    private boolean insertFlg = false;
    
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
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
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
     * 宛先区分を取得します。
     * @return 宛先区分
     */
    public String getAtesakiKbn() {
        return atesakiKbn;
    }
    /**
     * 宛先区分を設定します。
     * @param atesakiKbn 宛先区分
     */
    public void setAtesakiKbn(String atesakiKbn) {
        this.atesakiKbn = atesakiKbn;
    }
    
    /**
     * 氏名を取得します。
     * @return 氏名
     */
    public String getName() {
        if(isNull(name)) return "";
        return name;
    }
    /**
     * 氏名を設定します。
     * @param name 氏名
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 電話番号を取得します。
     * @return 電話番号
     */
    public String getTel() {
        if(isNull(tel)) return "";
        return tel;
    }
    /**
     * 電話番号を設定します。
     * @param tel 電話番号
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    /**
     * 郵便番号を取得します。
     * @return 郵便番号
     */
    public String getZip() {
        if(isNull(zip)) return "";
        return zip;
    }
    /**
     * 郵便番号を設定します。
     * @param zip 郵便番号
     */
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    /**
     * 住所を取得します。
     * @return 住所
     */
    public String getAddress1() {
        if(isNull(address1)) return "";
        return address1;
    }
    /**
     * 住所を設定します。
     * @param address 住所
     */
    public void setAddress1(String address) {
        this.address1 = address;
    }
    
    /**
     * 送付先名を取得します。
     * @return 送付先名
     */
    public String getSoufuName() {
        if(isNull(soufuName)) return "";
        return soufuName;
    }
    /**
     * 送付先名を設定します。
     * @param soufuName 送付先名
     */
    public void setSoufuName(String soufuName) {
        this.soufuName = soufuName;
    }
    
    /**
     * 職位を取得します。
     * @return 職位
     */
    public String getJobType() {
        if(isNull(jobType)) return "";
        return jobType;
    }
    /**
     * 職位を設定します。
     * @param jobType 職位
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
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
    public String getAddress2() {
        if(isNull(address2)) return "";
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getAddress3() {
        if(isNull(address3)) return "";
        return address3;
    }
    public void setAddress3(String address3) {
        this.address3 = address3;
    }
    public boolean isInsertFlg() {
        return insertFlg;
    }
    public void setInsertFlg(boolean insertFlg) {
        this.insertFlg = insertFlg;
    }
    
    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
}
