/*
 * 作成日: 2005/11/21
 *
 */
package jp.co.isid.mos.bird.framework.entity;

/**
 * ユーザ情報
 * @author xytamura
 */
public class MstUser {

    public static final String TABLE = "BR01USER";
    
    public static final String bumonCd_COLUMN       = "BUMON_CD";
    public static final String limitKbnCOLUMN       = "LIMIT_KBN";
    public static final String mailAdd_COLUMN       = "MAIL_ADD";
    public static final String appliedDt_COLUMN     = "APPLIED_DT";
    public static final String pswdUpdtDt_COLUMN    = "PSWD_UPDT_DT";
    public static final String sekyuDt_COLUMN       = "SEKYU_DT";
    public static final String sekyuFlg_COLUMN      = "SEKYU_FLG";
    public static final String stopFlg_COLUMN       = "STOP_FLG";
    public static final String userNameKana_COLUMN  = "USER_NAME_KANA";
    public static final String userTypeCd_COLUMN    = "USERTYPE_CD";
    public static final String rCompanyCd_COLUMN    = "R_COMPANY_CD";
    public static final String companyName_COLUMN   = "COMPANY_NAME";
    public static final String bumonName_COLUMN     = "BUMON_NAME";

    /**
     * ユーザＩＤ
     */
    private String user_id;
        
    /**
     * ユーザ名
     */
    private String user_name;
    
    /**
     * ユーザ名称カナ
     */
    private String userNameKana;
    
    /**
     * ユーザタイプコード
     */
    private String userTypeCd;
    
    /**
     * 部門コード
     */
    private String bumonCd;

    /**
     * パスワード更新日
     */
    private String pswdUpdtDt;

    /**
     * 請求フラグ
     */
    private String sekyuFlg;
    
    /**
     * メールアドレス
     */
    private String mailAdd;
    
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
     * 企業コード
     */
    private String rCompanyCd;

    /**
     * 企業名
     */
    private String companyName;

    /**
     * 部門名
     */
    private String bumonName;

    /**
     * ユーザＩＤを取得します
     * @return ユーザＩＤ
     */
    public String getUser_id() {
        return user_id;
    }
    
    /**
     * ユーザＩＤを設定します
     * @param user_id ユーザＩＤ
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    /**
     * ユーザ名を取得します
     * @return ユーザ名
     */
    public String getUser_name() {
        return user_name;
    }
    
    /**
     * ユーザ名を設定します
     * @param user_name ユーザ名
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * @param bumonCd bumonCd を設定。
     */
    public void setBumonCd(String bumonCd) {
        this.bumonCd = bumonCd;
    }

    /**
     * @return bumonCd を戻します。
     */
    public String getBumonCd() {
        if (bumonCd != null) {
            bumonCd = bumonCd.trim();
        }
        return bumonCd;
    }

    /**
     * @param pswdUpdtDt pswdUpdtDt を設定。
     */
    public void setPswdUpdtDt(String pswdUpdtDt) {
        this.pswdUpdtDt = pswdUpdtDt;
    }

    /**
     * @return pswdUpdtDt を戻します。
     */
    public String getPswdUpdtDt() {
        return pswdUpdtDt;
    }

    /**
     * @param stopFlg stopFlg を設定。
     */
    public void setStopFlg(String stopFlg) {
        this.stopFlg = stopFlg;
    }

    /**
     * @return stopFlg を戻します。
     */
    public String getStopFlg() {
        return stopFlg;
    }

    /**
     * @param userNameKana userNameKana を設定。
     */
    public void setUserNameKana(String userNameKana) {
        this.userNameKana = userNameKana;
    }

    /**
     * @return userNameKana を戻します。
     */
    public String getUserNameKana() {
        return userNameKana;
    }

    /**
     * @param userTypeCd userTypeCd を設定。
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    /**
     * @return userTypeCd を戻します。
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }
    
    /**
     * @return limitKbn を戻します。
     */
    public String getLimitKbn() {
        return limitKbn;
    }
    /**
     * @param limitKbn limitKbn を設定。
     */
    public void setLimitKbn(String limitKbn) {
        this.limitKbn = limitKbn;
    }
    /**
     * @return mailAdd を戻します。
     */
    public String getMailAdd() {
        return mailAdd;
    }
    /**
     * @param mailAdd mailAdd を設定。
     */
    public void setMailAdd(String mailAdd) {
        this.mailAdd = mailAdd;
    }
    /**
     * @return sekyuDt を戻します。
     */
    public String getSekyuDt() {
        return sekyuDt;
    }
    /**
     * @param sekyuDt sekyuDt を設定。
     */
    public void setSekyuDt(String sekyuDt) {
        this.sekyuDt = sekyuDt;
    }
    /**
     * @return sekyuFlg を戻します。
     */
    public String getSekyuFlg() {
        return sekyuFlg;
    }
    /**
     * @param sekyuFlg sekyuFlg を設定。
     */
    public void setSekyuFlg(String sekyuFlg) {
        this.sekyuFlg = sekyuFlg;
    }
    /**
     * @return sekyuUpdtDt を戻します。
     */
    public String getSekyuUpdtDt() {
        return sekyuUpdtDt;
    }
    /**
     * @param sekyuUpdtDt sekyuUpdtDt を設定。
     */
    public void setSekyuUpdtDt(String sekyuUpdtDt) {
        this.sekyuUpdtDt = sekyuUpdtDt;
    }

    /**
     * @param appliedDt appliedDt を設定。
     */
    public void setAppliedDt(String appliedDt) {
        this.appliedDt = appliedDt;
    }

    /**
     * @return appliedDt を戻します。
     */
    public String getAppliedDt() {
        return appliedDt;
    }

    /**
     * @return rCompanyCd を戻します。
     */
    public String getRCompanyCd() {
        return rCompanyCd;
    }

    /**
     * @param companyCd rCompanyCd を設定。
     */
    public void setRCompanyCd(String companyCd) {
        rCompanyCd = companyCd;
    }

    /**
     * @return companyName を戻します。
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName companyName を設定。
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return bumonName を戻します。
     */
    public String getBumonName() {
        return bumonName;
    }

    /**
     * @param bumonName bumonName を設定。
     */
    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
    }
}
