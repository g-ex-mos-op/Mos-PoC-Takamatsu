package jp.co.isid.mos.bird.bizreport.posreportref.entity;

public class TrnPosReportRef {
    
    public static final String TABLE = "BT76SARL";
    
    public static final String compnayCd_COLUMN = "COMPNAY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String colseDt_COLUMN  = "COLSE_DT";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";

    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String dataNum_COLUMN = "DATA_NUM";
    
    public static final String shuSysDt_COLUMN = "SHU_SYS_DT";
    
    public static final String shuSysTime_COLUMN = "SHU_SYS_TIME";
    
    public static final String eatKen_COLUMN = "EAT_KEN";
    
    public static final String eatKin_COLUMN = "EAT_KIN";
    
    public static final String takeKen_COLUMN = "TAKE_KEN";
    
    public static final String takeKin_COLUMN = "TAKE_KIN";
    
    public static final String telKen_COLUMN = "TEL_KEN";
    
    public static final String telKin_COLUMN = "TEL_KIN";
    
    public static final String driveKen_COLUMN = "DRIVE_KEN";
    
    public static final String driveKin_COLUMN = "DRIVE_KIN";
    
    public static final String otherKen_COLUMN = "OTHER_KEN";
    
    public static final String otherKin_COLUMN = "OTHER_KIN";
    
    public static final String allKen_COLUMN = "ALL_KEN";
    
    public static final String allKin_COLUMN = "ALL_KIN";
    
    public static final String shuleiTime_COLUMN = "SHULEI_TIME";
    
    /**
     * 企業コード
     */
    private String compnayCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * クローズ日（画面上でクローズ店がどうか表示させるため）
     */
    private String colseDt;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 回数
     */
    private String dataNum;
    
    /**
     * 集計日付
     */
    private String shuSysDt;
    
    /**
     * 集計時刻
     */
    private String shuSysTime;
    
    /**
     * EAT_IN個数
     */
    private String eatKen;
    
    /**
     * EAT_IN金額
     */
    private String eatKin;
    
    /**
     * TAKE_OUT個数
     */
    private String takeKen;
    
    /**
     * TAKE_OUT金額
     */
    private String takeKin;
    
    /**
     * TEL_ORDER個数
     */
    private String telKen;
    
    /**
     * TEL_ORDER金額
     */
    private String telKin;
    
    /**
     * DRIVE_TH個数
     */
    private String driveKen;
    
    /**
     * DRIVE_TH金額
     */
    private String driveKin;
    
    /**
     * その他の個数
     */
    private String otherKen;
    
    /**
     * その他の金額
     */
    private String otherKin;
    
    /**
     * 総客数
     */
    private String allKen;
    
    /**
     * 総売り上げ
     */
    private String allKin;
    
    /**
     * 集計時間
     */
    private String shuleiTime;
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompnayCd() {
        return compnayCd;
    }
    /**
     * 企業コードを設定します。
     * @param compnayCd 企業コード
     */
    public void setCompnayCd(String compnayCd) {
        this.compnayCd = compnayCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店コードを設定します。
     * @param miseNameKj 店コード
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 営業日を取得します。
     * @return 営業日
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業日を設定します。
     * @param eigyoDt 営業日
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * 回数を取得します。
     * @return 回数
     */
    public String getDataNum() {
        return dataNum;
    }
    /**
     * 回数を設定します。
     * @param dataNum 回数
     */
    public void setDataNum(String dataNum) {
        this.dataNum = dataNum;
    }
    
    /**
     * 集計日付を取得します。
     * @return 集計日付
     */
    public String getShuSysDt() {
        return shuSysDt;
    }
    /**
     * 集計日付を設定します。
     * @param shuSysDt 集計日付
     */
    public void setShuSysDt(String shuSysDt) {
        this.shuSysDt = shuSysDt;
    }
    
    /**
     * 集計時刻を取得します。
     * @return 集計時刻
     */
    public String getShuSysTime() {
        return shuSysTime;
    }
    /**
     * 集計時刻を設定します。
     * @param shuSysTime 集計時刻
     */
    public void setShuSysTime(String shuSysTime) {
        this.shuSysTime = shuSysTime;
    }
    
    /**
     * EAT_IN個数を取得します。
     * @return EAT_IN個数
     */
    public String getEatKen() {
        return eatKen;
    }
    /**
     * EAT_IN個数を設定します。
     * @param eatKen EAT_IN個数
     */
    public void setEatKen(String eatKen) {
        this.eatKen = eatKen;
    }
    
    /**
     * EAT_IN金額を取得します。
     * @return EAT_IN金額
     */
    public String getEatKin() {
        return eatKin;
    }
    /**
     * EAT_IN金額を設定します。
     * @param eatKin EAT_IN金額
     */
    public void setEatKin(String eatKin) {
        this.eatKin = eatKin;
    }
    
    /**
     * TAKE_OUT個数を取得します。
     * @return TAKE_OUT個数
     */
    public String getTakeKen() {
        return takeKen;
    }
    /**
     * TAKE_OUT個数を設定します。
     * @param takeKen TAKE_OUT個数
     */
    public void setTakeKen(String takeKen) {
        this.takeKen = takeKen;
    }
    
    /**
     * TAKE_OUT金額を取得します。
     * @return TAKE_OUT金額
     */
    public String getTakeKin() {
        return takeKin;
    }
    /**
     * TAKE_OUT金額を設定します。
     * @param takeKin TAKE_OUT金額
     */
    public void setTakeKin(String takeKin) {
        this.takeKin = takeKin;
    }
    
    /**
     * TEL_ORDER個数を取得します。
     * @return TEL_ORDER個数
     */
    public String getTelKen() {
        return telKen;
    }
    /**
     * TEL_ORDER個数を設定します。
     * @param telKen TEL_ORDER個数
     */
    public void setTelKen(String telKen) {
        this.telKen = telKen;
    }
    
    /**
     * TEL_ORDER金額を取得します。
     * @return TEL_ORDER金額
     */
    public String getTelKin() {
        return telKin;
    }
    /**
     * TEL_ORDER金額を設定します。
     * @param telKin TEL_ORDER金額
     */
    public void setTelKin(String telKin) {
        this.telKin = telKin;
    }
    
    /**
     * DRIVE_TH個数を取得します。
     * @return DRIVE_TH個数
     */
    public String getDriveKen() {
        return driveKen;
    }
    /**
     * DRIVE_TH個数を設定します。
     * @param driveKen DRIVE_TH個数
     */
    public void setDriveKen(String driveKen) {
        this.driveKen = driveKen;
    }
    
    /**
     * DRIVE_TH金額を取得します。
     * @return DRIVE_TH金額
     */
    public String getDriveKin() {
        return driveKin;
    }
    /**
     * DRIVE_TH金額を設定します。
     * @param driveKin DRIVE_TH金額
     */
    public void setDriveKin(String driveKin) {
        this.driveKin = driveKin;
    }
    
    /**
     * その他の個数を取得します。
     * @return その他の個数
     */
    public String getOtherKen() {
        return otherKen;
    }
    /**
     * その他の個数を設定します。
     * @param otherKen その他の個数
     */
    public void setOtherKen(String otherKen) {
        this.otherKen = otherKen;
    }
    
    /**
     * その他の金額を取得します。
     * @return その他の金額
     */
    public String getOtherKin() {
        return otherKin;
    }
    /**
     * その他の金額を設定します。
     * @param otherKin その他の金額
     */
    public void setOtherKin(String otherKin) {
        this.otherKin = otherKin;
    }
    
    /**
     * 総客数を取得します。
     * @return 総客数
     */
    public String getAllKen() {
        return allKen;
    }
    /**
     * 総客数を設定します。
     * @param allKen 総客数
     */
    public void setAllKen(String allKen) {
        this.allKen = allKen;
    }
    
    /**
     * 総売り上げを取得します。
     * @return 総売り上げ
     */
    public String getAllKin() {
        return allKin;
    }
    /**
     * 総売り上げを設定します。
     * @param allKin 総売り上げ
     */
    public void setAllKin(String allKin) {
        this.allKin = allKin;
    }
    
    /**
     * 集計時間を取得します。
     * @return 集計時間
     */
    public String getShuleiTime() {
        return shuleiTime;
    }
    /**
     * 集計時間を設定します。
     * @param shuleiTime 集計時間
     */
    public void setShuleiTime(String shuleiTime) {
        this.shuleiTime = shuleiTime;
    }
    /**
     * クローズ日を取得します。
     * @return クローズ日
     */
    public String getColseDt() {
        return colseDt;
    }
    /**
     * クローズ日を設定します。
     * @param colseDt クローズ日
     */
    public void setColseDt(String colseDt) {
        this.colseDt = colseDt;
    }
    
}
