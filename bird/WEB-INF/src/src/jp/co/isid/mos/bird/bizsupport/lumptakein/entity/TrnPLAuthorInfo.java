package jp.co.isid.mos.bird.bizsupport.lumptakein.entity;

import java.sql.Timestamp;

/**
 * P/Lデータ作成者情報エンティティ
 * 
 * @author xyuchida
 */
public class TrnPLAuthorInfo {
    
    public static final String TABLE = "BT18PLAU";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";

    public static final String authorName_COLUMN = "AUTHOR_NAME";
    
    public static final String authDt_COLUMN = "AUTH_DT";
    
    public static final String authPhoneNum_COLUMN = "AUTH_PHONE_NUM";
    
    public static final String authOther_COLUMN = "AUTH_OTHER";
    
    public static final String kessanDt_COLUMN = "KESSAN_DT";

    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;

    /**
     * 作成者
     */
    private String authorName;
    
    /**
     * 作成年月日
     */
    private String authDt;
    
    /**
     * 作成者電話番号
     */
    private String authPhoneNum;
    
    /**
     * 作成者会計事務所等
     */
    private String authOther;
    
    /**
     * 決算月
     */
    private String kessanDt;

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
     * 作成者を取得します。
     * @return 作成者
     */
    public String getAuthorName() {
        return authorName;
    }
    /**
     * 作成者を設定します。
     * @param authorName 作成者
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    /**
     * 作成年月日を取得します。
     * @return 作成年月日
     */
    public String getAuthDt() {
        return authDt;
    }
    /**
     * 作成年月日を設定します。
     * @param authDt 作成年月日
     */
    public void setAuthDt(String authDt) {
        this.authDt = authDt;
    }
    
    /**
     * 作成者電話番号を取得します。
     * @return 作成者電話番号
     */
    public String getAuthPhoneNum() {
        return authPhoneNum;
    }
    /**
     * 作成者電話番号を設定します。
     * @param authPhoneNum 作成者電話番号
     */
    public void setAuthPhoneNum(String authPhoneNum) {
        this.authPhoneNum = authPhoneNum;
    }
    
    /**
     * 作成者会計事務所等を取得します。
     * @return 作成者会計事務所等
     */
    public String getAuthOther() {
        return authOther;
    }
    /**
     * 作成者会計事務所等を設定します。
     * @param authOther 作成者会計事務所等
     */
    public void setAuthOther(String authOther) {
        this.authOther = authOther;
    }
    
    /**
     * 決算月を取得します。
     * @return 決算月
     */
    public String getKessanDt() {
        return kessanDt;
    }
    /**
     * 決算月を設定します。
     * @param kessanDt 決算月
     */
    public void setKessanDt(String kessanDt) {
        this.kessanDt = kessanDt;
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
