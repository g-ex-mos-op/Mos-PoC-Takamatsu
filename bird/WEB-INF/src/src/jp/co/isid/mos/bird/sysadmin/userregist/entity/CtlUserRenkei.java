package jp.co.isid.mos.bird.sysadmin.userregist.entity;

import java.sql.Timestamp;

public class CtlUserRenkei {
    
    public static final String TABLE = "BR08UCIF";
    
    public static final String eUserId_COLUMN = "E_USER_ID";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String shokuiCd_COLUMN = "SHOKUI_CD";
    
    public static final String gryoupName_COLUMN = "GROUP_NAME";
    
    public static final String alphName_COLUMN = "ALPH_NAME";
    
    public static final String userKbn1_COLUMN = "USER_KBN1";
    
    public static final String userLevel_COLUMN = "USER_LEVEL";
    
    public static final String bingoNo_COLUMN = "BINGO_NO";
    
    public static final String gyoumuCd_COLUMN = "GYOMU_CD";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * e-mossles ユーザID
     */
    private String eUserId;
    
    /**
     * BIRD ユーザID
     */
    private String userId;
    
    /**
     * 職位コード
     */
    private String shokuiCd;
    
    /**
     * グループ名称
     */
    private String gryoupName;
    
    /**
     * アルファベット名称
     */
    private String alphName;
    
    /**
     * 使用者区分１
     */
    private String userKbn1;
    
    /**
     * ユーザーレベル
     */
    private String userLevel;
    
    /**
     * ビンゴNO
     */
    private String bingoNo;
    
    /**
     * 対応業務コード
     */
    private String gyoumuCd;
    
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
     * e-mossles ユーザIDを取得します。
     * @return e-mossles ユーザID
     */
    public String getEUserId() {
        return (eUserId == null) ? "" : eUserId;
    }
    /**
     * e-mossles ユーザIDを設定します。
     * @param eUserId e-mossles ユーザID
     */
    public void setEUserId(String eUserId) {
        this.eUserId = eUserId;
    }
    
    /**
     * BIRD ユーザIDを取得します。
     * @return BIRD ユーザID
     */
    public String getUserId() {
        return (userId == null) ? "" : userId;
    }
    /**
     * BIRD ユーザIDを設定します。
     * @param userId BIRD ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 職位コードを取得します。
     * @return 職位コード
     */
    public String getShokuiCd() {
        return (shokuiCd == null) ? "" : shokuiCd;
    }
    /**
     * 職位コードを設定します。
     * @param shokuiCd 職位コード
     */
    public void setShokuiCd(String shokuiCd) {
        this.shokuiCd = shokuiCd;
    }
    
    /**
     * グループ名称を取得します。
     * @return グループ名称
     */
    public String getGryoupName() {
        return (gryoupName == null) ? "" : gryoupName;
    }
    /**
     * グループ名称を設定します。
     * @param gryoupName グループ名称
     */
    public void setGryoupName(String gryoupName) {
        this.gryoupName = gryoupName;
    }
    
    /**
     * アルファベット名称を取得します。
     * @return アルファベット名称
     */
    public String getAlphName() {
        return (alphName == null) ? "" : alphName;
    }
    /**
     * アルファベット名称を設定します。
     * @param alphName アルファベット名称
     */
    public void setAlphName(String alphName) {
        this.alphName = alphName;
    }
    
    /**
     * 使用者区分１を取得します。
     * @return 使用者区分１
     */
    public String getUserKbn1() {
        return (userKbn1 == null) ? "" : userKbn1;
    }
    /**
     * 使用者区分１を設定します。
     * @param userKbn1 使用者区分１
     */
    public void setUserKbn1(String userKbn1) {
        this.userKbn1 = userKbn1;
    }
    
    /**
     * ユーザーレベルを取得します。
     * @return ユーザーレベル
     */
    public String getUserLevel() {
        return (userLevel == null) ? "" : userLevel;
    }
    /**
     * ユーザーレベルを設定します。
     * @param userLevel ユーザーレベル
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
    
    /**
     * ビンゴNOを取得します。
     * @return ビンゴNO
     */
    public String getBingoNo() {
        return (bingoNo == null) ? "" : bingoNo;
    }
    /**
     * ビンゴNOを設定します。
     * @param bingoNo ビンゴNO
     */
    public void setBingoNo(String bingoNo) {
        this.bingoNo = bingoNo;
    }
    
    /**
     * 対応業務コードを取得します。
     * @return 対応業務コード
     */
    public String getGyoumuCd() {
        return (gyoumuCd == null) ? "" : gyoumuCd;
    }
    /**
     * 対応業務コードを設定します。
     * @param gyoumuCd 対応業務コード
     */
    public void setGyoumuCd(String gyoumuCd) {
        this.gyoumuCd = gyoumuCd;
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
