package jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity;

import java.sql.Timestamp;

public class TrnTempStoreInfo {
    
    public static final String TABLE = "BT43KTKT";
    
    public static final String nendo_COLUMN = "NENDO";
    
    public static final String kariCd_COLUMN = "KARI_CD";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String kakuteiDt_COLUMN = "KAKUTEI_DT";
    
    public static final String setFlg_COLUMN = "SET_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 年度
     */
    private String nendo;
    
    /**
     * 仮店番
     */
    private String kariCd;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 実店番
     */
    private String miseCd;
    
    /**
     * 確定日
     */
    private String kakuteiDt;
    
    /**
     * 取込フラグ
     */
    private String setFlg;
    
    /**
     * 登録ユーザ
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 更新ユーザ
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 年度を取得します。
     * @return 年度
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * 年度を設定します。
     * @param nendo 年度
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
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
     * 実店番を取得します。
     * @return 実店番
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 実店番を設定します。
     * @param miseCd 実店番
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
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
     * 登録ユーザを取得します。
     * @return 登録ユーザ
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザを設定します。
     * @param firstUser 登録ユーザ
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
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 更新ユーザを取得します。
     * @return 更新ユーザ
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザを設定します。
     * @param lastUser 更新ユーザ
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
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
