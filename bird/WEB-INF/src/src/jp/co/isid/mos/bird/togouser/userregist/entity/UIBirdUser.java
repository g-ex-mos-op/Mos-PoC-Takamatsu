package jp.co.isid.mos.bird.togouser.userregist.entity;

import java.sql.Timestamp;

public class UIBirdUser {

    public static final String TABLE = "BR01USER";

    public static final String userId_COLUMN = "USER_ID";

    public static final String userNameKj_COLUMN = "USER_NAME_KJ";

    public static final String userNameKana_COLUMN = "USER_NAME_KANA";

    public static final String userNameCdKj_COLUMN = "USER_CD_NAME_KJ";

    public static final String usertypeCd_COLUMN = "USERTYPE_CD";

    public static final String bumonCd_COLUMN = "BUMON_CD";

    public static final String bumonName_COLUMN = "BUMON_NAME";

    public static final String bumonCdName_COLUMN = "BUMON_CD_NAME";

    public static final String userPswd_COLUMN = "USER_PSWD";

    public static final String userPswdConfirm_COLUMN = "USER_PSWD_CONFIRM";

    public static final String pswdupdtDt_COLUMN = "PSWD_UPDT_DT";

    public static final String lastPswd_COLUMN = "LAST_PSWD";

    public static final String sekyuFlg_COLUMN = "SEKYU_FLG";

    public static final String mailAdd_COLUMN = "MAIL_ADD";

    public static final String appliedDt_COLUMN = "APPLIED_DT";

    public static final String sekyuDt_COLUMN = "SEKYU_DT";

    public static final String sekyuUpdtDt_COLUMN = "SEKYU_UPDT_DT";

    public static final String limitKbn_COLUMN = "LIMIT_KBN";

    public static final String stopFlg_COLUMN = "STOP_FLG";

    public static final String firstUser_COLUMN = "FIRST_USER";

    public static final String firstPgm_COLUMN = "FIRST_PGM";

    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    public static final String lastUser_COLUMN = "LAST_USER";

    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    public static final String taishokuDt_COLUMN = "TAISHOKU_DT";

    public static final String TIMESTAMP_PROPERTY = "lastTmsp";


    /**
     * ユーザID
     */
    private String userId;

    /**
     * ユーザ名称
     */
    private String userNameKj;

    /**
     * ユーザ名称(カナ)
     */
    private String userNameKana;

    /**
     * ユーザID+名称
     */
    private String userCdNameKj;

    /**
     * ユーザタイプコード
     */
    private String usertypeCd;

    /**
     * 部門コード
     */
    private String bumonCd;

    /**
     * 部門名称
     */
    private String bumonName;

    /**
     * 部門コード＋名称
     */
    private String bumonCdName;

    /**
     * パスワード
     */
    private byte[] userPswd;

    /**
     * 確認用パスワード
     */
    private byte[] userPswdConfirm;

    /**
     * パスワード更新日
     */
    private String pswdupdtDt;

    /**
     * 前回パスワード
     */
    private byte[] lastPswd;

    /**
     * 請求フラグ
     */
    private String sekyuFlg;

    /**
     * メールアドレス
     */
    private String mailAdd = "";

    /**
     * 申込日
     */
    private String appliedDt;

    /**
     * 請求開始日
     */
    private String sekyuDt;

    /**
     * 請求変更予定日
     */
    private String sekyuUpdtDt;

    /**
     * 制限区分
     */
    private String limitKbn;

    /**
     * 使用停止フラグ
     */
    private String stopFlg;

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
     * 退職日
     */
    private String taishokuDt;



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
     * ユーザ名称を取得します。
     * @return ユーザ名称
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * ユーザ名称を設定します。
     * @param userNameKj ユーザ名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }

    /**
     * ユーザ名称(カナ)を取得します。
     * @return ユーザ名称(カナ)
     */
    public String getUserNameKana() {
        if(userNameKana == null){
            return "";
        }
        return userNameKana.trim();
    }
    /**
     * ユーザ名称(カナ)を設定します。
     * @param userNameKana ユーザ名称(カナ)
     */
    public void setUserNameKana(String userNameKana) {
        this.userNameKana = userNameKana;
    }

    /**
     * ユーザタイプコードを取得します。
     * @return ユーザタイプコード
     */
    public String getUsertypeCd() {
        return usertypeCd;
    }
    /**
     * ユーザタイプコードを設定します。
     * @param usertypeCd ユーザタイプコード
     */
    public void setUsertypeCd(String usertypeCd) {
        this.usertypeCd = usertypeCd;
    }

    /**
     * 部門コードを取得します。
     * @return 部門コード
     */
    public String getBumonCd() {
        return bumonCd;
    }
    /**
     * 部門コードを設定します。
     * @param bumonCd 部門コード
     */
    public void setBumonCd(String bumonCd) {
        this.bumonCd = bumonCd;
    }

    /**
     * パスワードを取得します。
     * @return パスワード
     */
    public byte[] getUserPswd() {
        return userPswd;
    }
    /**
     * パスワードを設定します。
     * @param userPswd パスワード
     */
    public void setUserPswd(byte[] userPswd) {
        this.userPswd = userPswd;
    }

    /**
     * パスワード更新日を取得します。
     * @return パスワード更新日
     */
    public String getPswdupdtDt() {
        return pswdupdtDt;
    }
    /**
     * パスワード更新日を設定します。
     * @param pswdupdtDt パスワード更新日
     */
    public void setPswdupdtDt(String pswdupdtDt) {
        this.pswdupdtDt = pswdupdtDt;
    }

    /**
     * 前回パスワードを取得します。
     * @return 前回パスワード
     */
    public byte[] getLastPswd() {
        return lastPswd;
    }
    /**
     * 前回パスワードを設定します。
     * @param lastPswd 前回パスワード
     */
    public void setLastPswd(byte[] lastPswd) {
        this.lastPswd = lastPswd;
    }

    /**
     * 請求フラグを取得します。
     * @return 請求フラグ
     */
    public String getSekyuFlg() {
        return sekyuFlg;
    }
    /**
     * 請求フラグを設定します。
     * @param sekyuFlg 請求フラグ
     */
    public void setSekyuFlg(String sekyuFlg) {
        this.sekyuFlg = sekyuFlg;
    }

    /**
     * メールアドレスを取得します。
     * @return メールアドレス
     */
    public String getMailAdd() {
        return mailAdd;
    }
    /**
     * メールアドレスを設定します。
     * @param mailAdd メールアドレス
     */
    public void setMailAdd(String mailAdd) {
        this.mailAdd = mailAdd;
    }

    /**
     * 申込日を取得します。
     * @return 申込日
     */
    public String getAppliedDt() {
        return appliedDt;
    }
    /**
     * 申込日を設定します。
     * @param appliedDt 申込日
     */
    public void setAppliedDt(String appliedDt) {
        this.appliedDt = appliedDt;
    }

    /**
     * 請求開始日を取得します。
     * @return 請求開始日
     */
    public String getSekyuDt() {
        return sekyuDt;
    }
    /**
     * 請求開始日を設定します。
     * @param sekyuDt 請求開始日
     */
    public void setSekyuDt(String sekyuDt) {
        this.sekyuDt = sekyuDt;
    }

    /**
     * 請求変更予定日を取得します。
     * @return 請求変更予定日
     */
    public String getSekyuUpdtDt() {
        return sekyuUpdtDt;
    }
    /**
     * 請求変更予定日を設定します。
     * @param sekyuUpdtDt 請求変更予定日
     */
    public void setSekyuUpdtDt(String sekyuUpdtDt) {
        this.sekyuUpdtDt = sekyuUpdtDt;
    }

    /**
     * 制限区分を取得します。
     * @return 制限区分
     */
    public String getLimitKbn() {
        return limitKbn;
    }
    /**
     * 制限区分を設定します。
     * @param limitKbn 制限区分
     */
    public void setLimitKbn(String limitKbn) {
        this.limitKbn = limitKbn;
    }

    /**
     * 使用停止フラグを取得します。
     * @return 使用停止フラグ
     */
    public String getStopFlg() {
        return stopFlg;
    }
    /**
     * 使用停止フラグを設定します。
     * @param stopFlg 使用停止フラグ
     */
    public void setStopFlg(String stopFlg) {
        this.stopFlg = stopFlg;
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
    public String getTaishokuDt() {
        return taishokuDt;
    }
    public void setTaishokuDt(String taishokuDt) {
        this.taishokuDt = taishokuDt;
    }
    public String getUserCdNameKj() {
        return userCdNameKj;
    }
    public void setUserCdNameKj(String userCdNameKj) {
        this.userCdNameKj = userCdNameKj;
    }
    public byte[] getUserPswdConfirm() {
        return userPswdConfirm;
    }
    public void setUserPswdConfirm(byte[] userPswdConfirm) {
        this.userPswdConfirm = userPswdConfirm;
    }
    public String getBumonName() {
    	return (bumonName == null) ? "" : bumonName.replaceAll("[　*| *]*$", "");
    }
    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
    }
    public String getBumonCdName() {
        return bumonCdName;
    }
    public void setBumonCdName(String bumonCdName) {
        this.bumonCdName = bumonCdName;
    }

}
