package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity;


/**
 * 全店ダウンロード用Entity
 * 
 * @author xnkusama
 *
 */
public class UICsvAutoAmountData {
    
    public static final String TABLE = "TM28SAUT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String sokoCd_COLUMN = "SOKO_CD";
    
    public static final String sokoNameKj_COLUMN = "SOKO_NAME_KJ";
    
    public static final String shoCdDai_COLUMN = "SHO_CD_DAI";
    
    public static final String shoNameKj_COLUMN = "SHO_NAME_KJ";
    
    public static final String shoNisugata_COLUMN = "SHO_NISUGATA";
    
    public static final String nisuName_COLUMN = "NISU_NAME";
    
    public static final String unitJ_COLUMN = "UNIT_J";
    
    public static final String amtWeek_COLUMN = "AMT_WEEK";
    
    public static final String amtDatd_COLUMN = "AMT_SATD";
    
    public static final String amtHold_COLUMN = "AMT_HOLD";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 管理会社企業名称
     */
    private String companyName;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称（漢字）
     */
    private String onerNameKj;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店舗名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 倉庫コード
     */
    private String sokoCd;
    
    /**
     * 倉庫名称（漢字）
     */
    private String sokoNameKj;
    
    /**
     * 代表商品コード
     */
    private String shoCdDai;
    
    /**
     * 代表商品名称
     */
    private String shoNameKj;
    
    /**
     * 商品荷姿コード
     */
    private String shoNisugata;
    
    /**
     * 商品荷姿名称
     */
    private String nisuName;
    
    /**
     * 受注単位区分
     */
    private String unitJ;
    
    /**
     * 平日納品
     */
    private java.math.BigDecimal amtWeek;
    
    /**
     * 土曜納品
     */
    private java.math.BigDecimal amtSatd;
    
    /**
     * 日祝納品
     */
    private java.math.BigDecimal amtHold;
    /**
     * オープン日
     */
    private String openDt;
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
     * 管理会社企業名称を取得します。
     * @return 管理会社企業名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 管理会社企業名称を設定します。
     * @param companyName 管理会社企業名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
     * オーナー名称（漢字）を取得します。
     * @return オーナー名称（漢字）
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称（漢字）を設定します。
     * @param onerNameKj オーナー名称（漢字）
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
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
     * 店舗名称（漢字）を取得します。
     * @return 店舗名称（漢字）
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称（漢字）を設定します。
     * @param miseNameKj 店舗名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 倉庫コードを取得します。
     * @return 倉庫コード
     */
    public String getSokoCd() {
        return sokoCd;
    }
    /**
     * 倉庫コードを設定します。
     * @param sokoCd 倉庫コード
     */
    public void setSokoCd(String sokoCd) {
        this.sokoCd = sokoCd;
    }
    
    /**
     * 倉庫名称（漢字）を取得します。
     * @return 倉庫名称（漢字）
     */
    public String getSokoNameKj() {
        return sokoNameKj;
    }
    /**
     * 倉庫名称（漢字）を設定します。
     * @param sokoNameKj 倉庫名称（漢字）
     */
    public void setSokoNameKj(String sokoNameKj) {
        this.sokoNameKj = sokoNameKj;
    }
    
    /**
     * 代表商品コードを取得します。
     * @return 代表商品コード
     */
    public String getShoCdDai() {
        return shoCdDai;
    }
    /**
     * 代表商品コードを設定します。
     * @param shoCdDai 代表商品コード
     */
    public void setShoCdDai(String shoCdDai) {
        this.shoCdDai = shoCdDai;
    }
    
    /**
     * 代表商品名称を取得します。
     * @return 代表商品名称
     */
    public String getShoNameKj() {
        return shoNameKj;
    }
    /**
     * 代表商品名称を設定します。
     * @param shoNameKj 代表商品名称
     */
    public void setShoNameKj(String shoNameKj) {
        this.shoNameKj = shoNameKj;
    }
    
    /**
     * 商品荷姿コードを取得します。
     * @return 商品荷姿コード
     */
    public String getShoNisugata() {
        return shoNisugata;
    }
    /**
     * 商品荷姿コードを設定します。
     * @param shoNisugata 商品荷姿コード
     */
    public void setShoNisugata(String shoNisugata) {
        this.shoNisugata = shoNisugata;
    }
    
    /**
     * 商品荷姿名称を取得します。
     * @return 商品荷姿名称
     */
    public String getNisuName() {
        return nisuName;
    }
    /**
     * 商品荷姿名称を設定します。
     * @param nisuName 商品荷姿名称
     */
    public void setNisuName(String nisuName) {
        this.nisuName = nisuName;
    }
    
    /**
     * 平日納品を取得します。
     * @return 平日納品
     */
    public java.math.BigDecimal getAmtWeek() {
        return amtWeek;
    }
    /**
     * 平日納品を設定します。
     * @param amtWeek 平日納品
     */
    public void setAmtWeek(java.math.BigDecimal amtWeek) {
        this.amtWeek = amtWeek;
    }
    
    /**
     * 土曜納品を取得します。
     * @return 土曜納品
     */
    public java.math.BigDecimal getAmtSatd() {
        return amtSatd;
    }
    /**
     * 土曜納品を設定します。
     * @param amtSatd 土曜納品
     */
    public void setAmtSatd(java.math.BigDecimal amtSatd) {
        this.amtSatd = amtSatd;
    }
    
    /**
     * 日祝納品を取得します。
     * @return 日祝納品
     */
    public java.math.BigDecimal getAmtHold() {
        return amtHold;
    }
    /**
     * 日祝納品を設定します。
     * @param amtHold 日祝納品
     */
    public void setAmtHold(java.math.BigDecimal amtHold) {
        this.amtHold = amtHold;
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
     * @return unitJ を戻します。
     */
    public String getUnitJ() {
        return unitJ;
    }
    /**
     * @param unitJ 設定する unitJ。
     */
    public void setUnitJ(String unitJ) {
        this.unitJ = unitJ;
    }
}