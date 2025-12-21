package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity;

import java.math.BigDecimal;

public class UIMCStatusInfo {
    
    public static final String TABLE = "BT69CZIN";
    
    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String title_COLUMN = "TITLE";
    
    public static final String targetFrom_COLUMN = "TARGET_FROM";
    
    public static final String targetTo_COLUMN = "TARGET_TO";
    
    public static final String defaultFrom_COLUMN = "DEFAULT_FROM";
    
    public static final String defaultTo_COLUMN = "DEFAULT_TO";
    
    public static final String yobiFrom_COLUMN = "YOBI_FROM";
    
    public static final String yobiTo_COLUMN = "YOBI_TO";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String shokuCd_COLUMN = "SHOKU_CD";
    
    public static final String shokuNameKna_COLUMN = "SHOKU_NAME_KNA";
    
    public static final String reserveAmt_COLUMN = "RESERVE_AMT";
    
    public static final String salesPerformance_COLUMN = "SALES_PERFORMANCE";
    
    public static final String shoAmtBara_COLUMN = "SHO_AMT_BARA";
    
    public static final String cautionAmt_COLUMN = "CAUTION_AMT";
    
    /**
     * 管理番号
     */
    private String ckanriNo;
    
    /**
     * タイトル
     */
    private String title;
    
    /**
     * 対象期間FROM
     */
    private String targetFrom;
    
    /**
     * 対象期間TO
     */
    private String targetTo;
    
    /**
     * デフォルト表示期間FROM
     */
    private String defaultFrom;
    
    /**
     * デフォルト表示期間TO
     */
    private String defaultTo;
    
    /**
     * 予備期間FROM
     */
    private String yobiFrom;
    
    /**
     * 予備期間TO
     */
    private String yobiTo;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 会社名称
     */
    private String companyName;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * 食包材コード
     */
    private String shokuCd;
    
    /**
     * 食包材名称
     */
    private String shokuNameKna;
    
    /**
     * 予約数
     */
    private BigDecimal reserveAmt;
    
    /**
     * 販売実績
     */
    private BigDecimal salesPerformance;
    
    /**
     * 入荷数量
     */
    private BigDecimal shoAmtBara;
    
    /**
     * 入荷－販売・予約
     */
    private BigDecimal cautionAmt;
    
    /**
     * 管理番号を取得します。
     * @return 管理番号
     */
    public String getCkanriNo() {
        return ckanriNo;
    }
    /**
     * 管理番号を設定します。
     * @param ckanriNo 管理番号
     */
    public void setCkanriNo(String ckanriNo) {
        this.ckanriNo = ckanriNo;
    }
    
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }
    /**
     * タイトルを設定します。
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * 対象期間FROMを取得します。
     * @return 対象期間FROM
     */
    public String getTargetFrom() {
        return targetFrom;
    }
    /**
     * 対象期間FROMを設定します。
     * @param targetFrom 対象期間FROM
     */
    public void setTargetFrom(String targetFrom) {
        this.targetFrom = targetFrom;
    }
    
    /**
     * 対象期間TOを取得します。
     * @return 対象期間TO
     */
    public String getTargetTo() {
        return targetTo;
    }
    /**
     * 対象期間TOを設定します。
     * @param targetTo 対象期間TO
     */
    public void setTargetTo(String targetTo) {
        this.targetTo = targetTo;
    }
    
    /**
     * デフォルト表示期間FROMを取得します。
     * @return デフォルト表示期間FROM
     */
    public String getDefaultFrom() {
        return defaultFrom;
    }
    /**
     * デフォルト表示期間FROMを設定します。
     * @param defaultFrom デフォルト表示期間FROM
     */
    public void setDefaultFrom(String defaultFrom) {
        this.defaultFrom = defaultFrom;
    }
    
    /**
     * デフォルト表示期間TOを取得します。
     * @return デフォルト表示期間TO
     */
    public String getDefaultTo() {
        return defaultTo;
    }
    /**
     * デフォルト表示期間TOを設定します。
     * @param defaultTo デフォルト表示期間TO
     */
    public void setDefaultTo(String defaultTo) {
        this.defaultTo = defaultTo;
    }
    
    /**
     * 予備期間FROMを取得します。
     * @return 予備期間FROM
     */
    public String getYobiFrom() {
        return yobiFrom;
    }
    /**
     * 予備期間FROMを設定します。
     * @param yobiFrom 予備期間FROM
     */
    public void setYobiFrom(String yobiFrom) {
        this.yobiFrom = yobiFrom;
    }
    
    /**
     * 予備期間TOを取得します。
     * @return 予備期間TO
     */
    public String getYobiTo() {
        return yobiTo;
    }
    /**
     * 予備期間TOを設定します。
     * @param yobiTo 予備期間TO
     */
    public void setYobiTo(String yobiTo) {
        this.yobiTo = yobiTo;
    }
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 会社名称を取得します。
     * @return 会社名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名称を設定します。
     * @param companyName 会社名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称を設定します。
     * @param miseNameKj 店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 食包材コードを取得します。
     * @return 食包材コード
     */
    public String getShokuCd() {
        return shokuCd;
    }
    /**
     * 食包材コードを設定します。
     * @param shokuCd 食包材コード
     */
    public void setShokuCd(String shokuCd) {
        this.shokuCd = shokuCd;
    }
    
    /**
     * 食包材名称を取得します。
     * @return 食包材名称
     */
    public String getShokuNameKna() {
        return shokuNameKna;
    }
    /**
     * 食包材名称を設定します。
     * @param shokuNameKna 食包材名称
     */
    public void setShokuNameKna(String shokuNameKna) {
        this.shokuNameKna = shokuNameKna;
    }
    
    /**
     * 予約数を取得します。
     * @return 予約数
     */
    public BigDecimal getReserveAmt() {
        return reserveAmt;
    }
    /**
     * 予約数を設定します。
     * @param reserveAmt 予約数
     */
    public void setReserveAmt(BigDecimal reserveAmt) {
        this.reserveAmt = reserveAmt;
    }
    
    /**
     * 販売実績を取得します。
     * @return 販売実績
     */
    public BigDecimal getSalesPerformance() {
        return salesPerformance;
    }
    /**
     * 販売実績を設定します。
     * @param salesPerformance 販売実績
     */
    public void setSalesPerformance(BigDecimal salesPerformance) {
        this.salesPerformance = salesPerformance;
    }
    
    /**
     * 入荷－販売・予約を取得します。
     * @return 入荷－販売・予約
     */
    public BigDecimal getCautionAmt() {
        return cautionAmt;
    }
    /**
     * 入荷－販売・予約を設定します。
     * @param cautionAmt 入荷－販売・予約
     */
    public void setCautionAmt(BigDecimal cautionAmt) {
        this.cautionAmt = cautionAmt;
    }
    /**
     * @return shoAmtBara を戻します。
     */
    public BigDecimal getShoAmtBara() {
        return shoAmtBara;
    }
    /**
     * @param shoAmtBara 設定する shoAmtBara。
     */
    public void setShoAmtBara(BigDecimal shoAmtBara) {
        this.shoAmtBara = shoAmtBara;
    }
    
}
