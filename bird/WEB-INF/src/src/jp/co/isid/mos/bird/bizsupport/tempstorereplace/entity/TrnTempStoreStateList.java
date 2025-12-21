package jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity;

import java.sql.Timestamp;


public class TrnTempStoreStateList {
    
    public static final String TABLE = "";
    
    public static final String setFlg_COLUMN = "SET_FLG";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String fcRc_COLUMN = "FC_RC";
    
    public static final String kariCd_COLUMN = "KARI_CD";
    
    public static final String yosanDt_COLUMN = "YOSAN_DT";
    
    public static final String yosan_COLUMN = "YOSAN";
    
    public static final String kakuteiDt_COLUMN = "KAKUTEI_DT";
    
    public static final String uriageDt_COLUMN = "URIAGE_DT";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    public static final String miseKbn_COLUMN = "MISE_KBN";
    
    public static final String JituSibuCd_COLUMN = "JITU_SIBU_CD";
    
    public static final String JituSibuName_COLUMN = "JITU_SIBU_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    

    /**
     * 登録可能判断フラグ
     * 0：登録不可　1：登録化
     */
    private String ableUpdateFlg = "1";
    
    /**
     * 取込フラグ
     * 0：取込未済　1：取込済(確定)
     */
    private String setFlg;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * FC_RC区分
     */
    private String fcRc;
    
    /**
     * 仮店番
     */
    private String kariCd;
    
    /**
     * 予算対象年月
     */
    private String yosanDt;
    
    /**
     * 予算
     */
    private String yosan;
    
    /**
     * 確定日
     */
    private String kakuteiDt;
    
    /**
     * 売上げ発生日
     */
    private String uriageDt;
    
    /**
     * オープン日
     */
    private String openDt;
    
    /**
     * クローズ日
     */
    private String closeDt;
    
    /**
     * 店舗区分
     */
    private String miseKbn;
    
    /**
     * 実店舗支部コード
     */
    private String JituSibuCd;
    
    /**
     * 実店舗支部名称
     */
    private String JituSibuName;
    
    /**
     * 実店舗コード
     */
    private String miseCd;
    
    /**
     * 実店舗名称
     */
    private String miseNameKj;
    
    /**
     * 登録ユーザID
     */
    private String firstUser;
    
    /**
     * 登録ユーザ名
     */
    private String userNameKj;
    
    /**
     * 登録時タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    
    

    /**
     * 登録可能判断フラグ
     * @return 登録可能判断フラグを取得します。
     */
    public String getAbleUpdateFlg() {
        return ableUpdateFlg;
    }
    /**
     * 登録可能判断フラグ
     * @param 登録可能判断フラグを設定します。
     */
    public void setAbleUpdateFlg(String ableUpdateFlg) {
        this.ableUpdateFlg = ableUpdateFlg;
    }
    
    /**
     * 取込フラグを取得します。
     * @return 取込フラグ
     */
    public String getSetFlg() {
        return setFlg;
    }
    /**
     * 取込フラグを設定します。
     * @param setFlg 取込フラグ
     */
    public void setSetFlg(String setFlg) {
        this.setFlg = setFlg;
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
     * FC_RC区分を取得します。
     * @return FC_RC区分
     */
    public String getFcRc() {
        return fcRc;
    }
    /**
     * FC_RC区分を設定します。
     * @param fcRc FC_RC区分
     */
    public void setFcRc(String fcRc) {
        this.fcRc = fcRc;
    }
    
    /**
     * 仮店番を取得します。
     * @return 仮店番
     */
    public String getKariCd() {
        return kariCd;
    }
    /**
     * 仮店番を設定します。
     * @param kariCd 仮店番
     */
    public void setKariCd(String kariCd) {
        this.kariCd = kariCd;
    }
    
    /**
     * 予算対象年月を取得します。
     * @return 予算対象年月
     */
    public String getYosanDt() {
        return yosanDt;
    }
    /**
     * 予算対象年月を設定します。
     * @param yosanDt 予算対象年月
     */
    public void setYosanDt(String yosanDt) {
        this.yosanDt = yosanDt;
    }
    
    /**
     * 予算を取得します。
     * @return 予算
     */
    public String getYosan() {
        return yosan;
    }
    /**
     * 予算を設定します。
     * @param yosan 予算
     */
    public void setYosan(String yosan) {
        this.yosan = yosan;
    }
    
    /**
     * 確定日を取得します。
     * @return 確定日
     */
    public String getKakuteiDt() {
        return kakuteiDt;
    }
    /**
     * 確定日を設定します。
     * @param kakuteiDt 確定日
     */
    public void setKakuteiDt(String kakuteiDt) {
        this.kakuteiDt = kakuteiDt;
    }
    
    /**
     * 売上げ発生日を取得します。
     * @return 売上げ発生日
     */
    public String getUriageDt() {
        return uriageDt;
    }
    /**
     * 売上げ発生日を設定します。
     * @param uriageDt 売上げ発生日
     */
    public void setUriageDt(String uriageDt) {
        this.uriageDt = uriageDt;
    }
    
    /**
     * オープン日を取得します。
     * @return オープン日
     */
    public String getOpenDt() {
        return openDt;
    }
    /**
     * オープン日を設定します。
     * @param openDt オープン日
     */
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    
    /**
     * クローズ日を取得します。
     * @return クローズ日
     */    
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * クローズ日を設定します。
     * @param closeDt クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * 店舗区分を取得します。
     * @return 店舗区分
     */
    public String getMiseKbn() {
        return miseKbn;
    }
    /**
     * 店舗種別を設定します。
     * @param miseKbn 店舗区分
     */
    public void setMiseKbn(String miseKbn) {
        this.miseKbn = miseKbn;
    }
    
    /**
     * 実店舗支部コードを取得します。
     * @return 実店舗支部コード
     */
    public String getJituSibuCd() {
        return JituSibuCd;
    }
    /**
     * 実店舗支部コードを設定します。
     * @param JituSibuCd 実店舗支部コード
     */
    public void setJituSibuCd(String JituSibuCd) {
        this.JituSibuCd = JituSibuCd;
    }
    
    /**
     * 実店舗支部名称を取得します。
     * @return 実店舗支部名称
     */
    public String getJituSibuName() {
        return JituSibuName;
    }
    /**
     * 実店舗支部名称を設定します。
     * @param JituSibuName 実店舗支部名称
     */
    public void setJituSibuName(String JituSibuName) {
        this.JituSibuName = JituSibuName;
    }
    
    /**
     * 実店舗コードを取得します。
     * @return 実店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 実店舗コードを設定します。
     * @param miseCd 実店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 実店舗名称を取得します。
     * @return 実店舗名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 実店舗名称を設定します。
     * @param miseNameKj 実店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 登録ユーザIDを取得します。
     * @return 登録ユーザID
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザIDを設定します。
     * @param firstUser 登録ユーザID
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録ユーザ名を取得します。
     * @return 登録ユーザ名
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * 登録ユーザ名を設定します。
     * @param userNameKj 登録ユーザ名
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * 登録時タイムスタンプを取得します。
     * @return 登録時タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時タイムスタンプを設定します。
     * @param firstTmsp 登録時タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    
}
