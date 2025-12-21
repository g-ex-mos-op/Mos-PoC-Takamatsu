package jp.co.isid.mos.bird.bizadmin.accountedit.entity;

import java.sql.Timestamp;

public class UIUserMatrixInfo {
    
    public static final String TABLE = "BR16MTRX";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String matrixKey1_COLUMN = "MATRIX_KEY_1";
    
    public static final String matrixKey2_COLUMN = "MATRIX_KEY_2";
    
    public static final String matrixKey3_COLUMN = "MATRIX_KEY_3";
    
    public static final String matrixKey4_COLUMN = "MATRIX_KEY_4";
    
    public static final String matrixKey5_COLUMN = "MATRIX_KEY_5";
    
    public static final String lastUpdtDt_COLUMN = "LAST_UPDT_DT";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * 認証キー１
     */
    private String matrixKey1;
    
    /**
     * 認証キー２
     */
    private String matrixKey2;
    
    /**
     * 認証キー３
     */
    private String matrixKey3;
    
    /**
     * 認証キー４
     */
    private String matrixKey4;
    
    /**
     * 認証キー５
     */
    private String matrixKey5;
    
    /**
     * 最終更新日
     */
    private String lastUpdtDt;
    
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
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザIDを設定します。
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 認証キー１を取得します。
     * @return 認証キー１
     */
    public String getMatrixKey1() {
        return matrixKey1;
    }
    /**
     * 認証キー１を設定します。
     * @param matrixKey1 認証キー１
     */
    public void setMatrixKey1(String matrixKey1) {
        this.matrixKey1 = matrixKey1;
    }
    
    /**
     * 認証キー２を取得します。
     * @return 認証キー２
     */
    public String getMatrixKey2() {
        return matrixKey2;
    }
    /**
     * 認証キー２を設定します。
     * @param matrixKey2 認証キー２
     */
    public void setMatrixKey2(String matrixKey2) {
        this.matrixKey2 = matrixKey2;
    }
    
    /**
     * 認証キー３を取得します。
     * @return 認証キー３
     */
    public String getMatrixKey3() {
        return matrixKey3;
    }
    /**
     * 認証キー３を設定します。
     * @param matrixKey3 認証キー３
     */
    public void setMatrixKey3(String matrixKey3) {
        this.matrixKey3 = matrixKey3;
    }
    
    /**
     * 認証キー４を取得します。
     * @return 認証キー４
     */
    public String getMatrixKey4() {
        return matrixKey4;
    }
    /**
     * 認証キー４を設定します。
     * @param matrixKey4 認証キー４
     */
    public void setMatrixKey4(String matrixKey4) {
        this.matrixKey4 = matrixKey4;
    }
    
    /**
     * 認証キー５を取得します。
     * @return 認証キー５
     */
    public String getMatrixKey5() {
        return matrixKey5;
    }
    /**
     * 認証キー５を設定します。
     * @param matrixKey5 認証キー５
     */
    public void setMatrixKey5(String matrixKey5) {
        this.matrixKey5 = matrixKey5;
    }
    
    /**
     * 最終更新日を取得します。
     * @return 最終更新日
     */
    public String getLastUpdtDt() {
        return lastUpdtDt;
    }
    /**
     * 最終更新日を設定します。
     * @param lastUpdtDt 最終更新日
     */
    public void setLastUpdtDt(String lastUpdtDt) {
        this.lastUpdtDt = lastUpdtDt;
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
