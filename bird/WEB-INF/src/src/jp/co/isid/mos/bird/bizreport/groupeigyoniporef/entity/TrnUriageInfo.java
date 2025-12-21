package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity;

import java.math.BigDecimal;

public class TrnUriageInfo {
    
    public static final String TABLE = "BT60ZNIP";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String openKbn_COLUMN = "OPEN_KBN";
    
    public static final String openKbnZen_COLUMN = "OPEN_KBN_ZEN";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";
    
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";
    
    public static final String onerYosan_COLUMN = "ONER_YOSAN";
    
    public static final String uriYosan_COLUMN = "URI_YOSAN";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 売上
     */
    private BigDecimal uriage = new BigDecimal("0");
    
    /**
     * 客数
     */
    private BigDecimal kyakusu = new BigDecimal("0");
    
    /**
     * 当年OPEN区分
     */
    private BigDecimal openKbn = new BigDecimal("0");
    
    /**
     * 前年OPEN区分
     */
    private BigDecimal openKbnZen = new BigDecimal("0");
    
    /**
     * 前年売上
     */
    private BigDecimal uriageZen = new BigDecimal("0");
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen = new BigDecimal("0");
    
    /**
     * オーナー予算
     */
    private BigDecimal onerYosan = new BigDecimal("0");
    
    /**
     * 事業計画予算
     */
    private BigDecimal uriYosan = new BigDecimal("0");
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
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
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 売上を取得します。
     * @return 売上
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上を設定します。
     * @param uriage 売上
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public BigDecimal getKyakusu() {
        return kyakusu;
    }
    /**
     * 客数を設定します。
     * @param kyakusu 客数
     */
    public void setKyakusu(BigDecimal kyakusu) {
        this.kyakusu = kyakusu;
    }
    
    /**
     * 当年OPEN区分を取得します。
     * @return 当年OPEN区分
     */
    public BigDecimal getOpenKbn() {
        return openKbn;
    }
    /**
     * 当年OPEN区分を設定します。
     * @param openKbn 当年OPEN区分
     */
    public void setOpenKbn(BigDecimal openKbn) {
        this.openKbn = openKbn;
    }
    
    /**
     * 前年OPEN区分を取得します。
     * @return 前年OPEN区分
     */
    public BigDecimal getOpenKbnZen() {
        return openKbnZen;
    }
    /**
     * 前年OPEN区分を設定します。
     * @param openKbnZen 前年OPEN区分
     */
    public void setOpenKbnZen(BigDecimal openKbnZen) {
        this.openKbnZen = openKbnZen;
    }
    
    /**
     * 前年売上を取得します。
     * @return 前年売上
     */
    public BigDecimal getUriageZen() {
        return uriageZen;
    }
    /**
     * 前年売上を設定します。
     * @param uriageZen 前年売上
     */
    public void setUriageZen(BigDecimal uriageZen) {
        this.uriageZen = uriageZen;
    }
    
    /**
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getKyakusuZen() {
        return kyakusuZen;
    }
    /**
     * 前年客数を設定します。
     * @param kyakusuZen 前年客数
     */
    public void setKyakusuZen(BigDecimal kyakusuZen) {
        this.kyakusuZen = kyakusuZen;
    }
    
    /**
     * オーナー予算を取得します。
     * @return オーナー予算
     */
    public BigDecimal getOnerYosan() {
        return onerYosan;
    }
    /**
     * オーナー予算を設定します。
     * @param onerYosan オーナー予算
     */
    public void setOnerYosan(BigDecimal onerYosan) {
        this.onerYosan = onerYosan;
    }
    
    /**
     * 事業計画予算を取得します。
     * @return 事業計画予算
     */
    public BigDecimal getUriYosan() {
        return uriYosan;
    }
    /**
     * 事業計画予算を設定します。
     * @param uriYosan 事業計画予算
     */
    public void setUriYosan(BigDecimal uriYosan) {
        this.uriYosan = uriYosan;
    }
    
}
