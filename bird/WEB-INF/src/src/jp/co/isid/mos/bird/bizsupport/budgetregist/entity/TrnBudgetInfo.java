package jp.co.isid.mos.bird.bizsupport.budgetregist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TrnBudgetInfo {
    
    public static final String TABLE = "BT42MSYD";
    
    public static final String yosanDt_COLUMN = "YOSAN_DT";
    public static final String companyCd_COLUMN = "COMPANY_CD";
    public static final String miseCd_COLUMN = "MISE_CD";
    public static final String sibuCd_COLUMN = "SIBU_CD";
    public static final String areaDai_COLUMN = "AREA_DAI";
    public static final String tenpoShu_COLUMN = "TENPO_SHU";
    public static final String fcRc_COLUMN = "FC_RC";
    public static final String yosan_COLUMN = "YOSAN";
    public static final String tenpoCount_COLUMN = "TENPO_COUNT";
    public static final String sakujoKbn_COLUMN = "SAKUJO_KBN";
    public static final String lastDate_COLUMN = "LAST_DATE";
    public static final String firstUser_COLUMN = "FIRST_USER";
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    public static final String lastUser_COLUMN = "LAST_USER";
    public static final String lastPgm_COLUMN = "LAST_PGM";
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    

    /**
     * 予算対象年月
     */
    private String yosanDt;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部取込コード
     */
    private String areaDai;
    
    /**
     * 店舗種別
     */
    private String tenpoShu;

    /**
     * FC/RC
     */
    private String fcRc;
    
    /**
     * 予算
     */
    private BigDecimal yosan;
    
    /**
     * 店舗件数(カウントに使用するらしい。１を入力)
     */
    private BigDecimal tenpoCount;
    
    /**
     * 削除フラグ 0: 初期値 1: 削除
     */
    private String sakujoKbn;
    
    /**
     * 最終更新日
     */
    private String lastDate;
    
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
     * 修正ユーザ
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正タイムスタンプ
     */
    private Timestamp lastTmsp;

    
    
    
    
    
    public String getAreaDai() {
        return areaDai;
    }

    public void setAreaDai(String areaDai) {
        this.areaDai = areaDai;
    }

    
    
    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getFcRc() {
        return fcRc;
    }

    public void setFcRc(String fcRc) {
        this.fcRc = fcRc;
    }

    public String getFirstPgm() {
        return firstPgm;
    }

    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }

    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }

    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }

    public String getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastPgm() {
        return lastPgm;
    }

    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }

    public Timestamp getLastTmsp() {
        return lastTmsp;
    }

    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public String getMiseCd() {
        return miseCd;
    }

    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    public String getSakujoKbn() {
        return sakujoKbn;
    }

    public void setSakujoKbn(String sakujoKbn) {
        this.sakujoKbn = sakujoKbn;
    }

    public String getSibuCd() {
        return sibuCd;
    }

    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }

    public BigDecimal getTenpoCount() {
        return tenpoCount;
    }

    public void setTenpoCount(BigDecimal tenpoCount) {
        this.tenpoCount = tenpoCount;
    }

    public String getTenpoShu() {
        return tenpoShu;
    }

    public void setTenpoShu(String tenpoShu) {
        this.tenpoShu = tenpoShu;
    }

    public BigDecimal getYosan() {
        return yosan;
    }

    public void setYosan(BigDecimal yosan) {
        this.yosan = yosan;
    }

    public String getYosanDt() {
        return yosanDt;
    }

    public void setYosanDt(String yosanDt) {
        this.yosanDt = yosanDt;
    }
    

    
}
