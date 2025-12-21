package jp.co.isid.mos.bird.analysis.sibuaverage.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.Calculator;

public class UISuiiData {
    
    public static final String TABLE = "BT60ZNIP";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";
    
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";
    
    public static final String uriageZenhi_COLUMN = "URIAGE_ZENHI";
    
    public static final String kyakusuZenhi_COLUMN = "KYAKUSU_ZENHI";
    
    public static final String kyakutankaZenHi_COLUMN = "KYAKUTANKA_ZEN_HI";
    
    public static final String sibuUriage_COLUMN = "SIBU_URIAGE";
    
    public static final String sibuKyakusu_COLUMN = "SIBU_KYAKUSU";
    
    public static final String sibuUriageZen_COLUMN = "SIBU_URIAGE_ZEN";
    
    public static final String sibuKyakusuZen_COLUMN = "SIBU_KYAKUSU_ZEN";
    
    public static final String sibuUriageZenhi_COLUMN = "SIBU_URIAGE_ZENHI";
    
    public static final String sibuKyakusuZenhi_COLUMN = "SIBU_KYAKUSU_ZENHI";
    
    public static final String sibuKyakutankaZenHi_COLUMN = "SIBU_KYAKUTANKA_ZEN_HI";
    
    BigDecimal dec0 = new BigDecimal("0");
    BigDecimal decHiritu0 = new BigDecimal("0.00");
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
    
    /**
     * 前年売上
     */
    private BigDecimal uriageZen;
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen;
    
    /**
     * 売上（支部）
     */
    private BigDecimal sibuUriage;
    
    /**
     * 客数（支部）
     */
    private BigDecimal sibuKyakusu;
    
    /**
     * 前年売上（支部）
     */
    private BigDecimal sibuUriageZen;
    
    /**
     * 前年客数（支部）
     */
    private BigDecimal sibuKyakusuZen;
    
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
     * 前年比売上を取得します。
     * @return 前年比売上
     */
    public BigDecimal getUriageZenhi() {
        return Calculator.percentage(this.uriage, this.uriageZen, 2);
    }
    
    /**
     * 前年比客数を取得します。
     * @return 前年比客数
     */
    public BigDecimal getKyakusuZenhi() {
        return Calculator.percentage(this.kyakusu, this.kyakusuZen, 2);
    }
    /**
     * 客単価取得処理
     * @return
     */
    public BigDecimal getKyakutanka() {
    	return Calculator.divide(this.uriage, this.kyakusu);
    }
    /**
     * 客単価(前年値)取得処理
     * @return
     */
    public BigDecimal getKyakutankaZen() {
    	return Calculator.divide(this.uriageZen, this.kyakusuZen);
    }
    /**
     * 前年比客単価を取得します。
     * @return 前年比客単価
     */
    public BigDecimal getKyakutankaZenHi() {
        return Calculator.percentage(getKyakutanka(), getKyakutankaZen(), 2);
    }
    
    /**
     * 売上（支部）を取得します。
     * @return 売上（支部）
     */
    public BigDecimal getSibuUriage() {
    	if(sibuUriage==null) {
    		return dec0;
    	}
        return sibuUriage;
    }
    /**
     * 売上（支部）を設定します。
     * @param sibuUriage 売上（支部）
     */
    public void setSibuUriage(BigDecimal sibuUriage) {
        this.sibuUriage = sibuUriage;
    }
    
    /**
     * 客数（支部）を取得します。
     * @return 客数（支部）
     */
    public BigDecimal getSibuKyakusu() {
    	if(sibuKyakusu==null) {
    		return dec0;
    	}
        return sibuKyakusu;
    }
    /**
     * 客数（支部）を設定します。
     * @param sibuKyakusu 客数（支部）
     */
    public void setSibuKyakusu(BigDecimal sibuKyakusu) {
        this.sibuKyakusu = sibuKyakusu;
    }
    
    /**
     * 前年売上（支部）を取得します。
     * @return 前年売上（支部）
     */
    public BigDecimal getSibuUriageZen() {
    	if(sibuUriageZen==null) {
    		return dec0;
    	}
        return sibuUriageZen;
    }
    /**
     * 前年売上（支部）を設定します。
     * @param sibuUriageZen 前年売上（支部）
     */
    public void setSibuUriageZen(BigDecimal sibuUriageZen) {
        this.sibuUriageZen = sibuUriageZen;
    }
    
    /**
     * 前年客数（支部）を取得します。
     * @return 前年客数（支部）
     */
    public BigDecimal getSibuKyakusuZen() {
    	if(sibuKyakusuZen==null) {
    		return dec0;
    	}
        return sibuKyakusuZen;
    }
    /**
     * 前年客数（支部）を設定します。
     * @param sibuKyakusuZen 前年客数（支部）
     */
    public void setSibuKyakusuZen(BigDecimal sibuKyakusuZen) {
        this.sibuKyakusuZen = sibuKyakusuZen;
    }
    
    /**
     * 前年比売上（支部）を取得します。
     * @return 前年比売上（支部）
     */
    public BigDecimal getSibuUriageZenhi() {
        return Calculator.percentage(this.sibuUriage, this.sibuUriageZen, 2);
    }
    
    /**
     * 前年比客数（支部）を取得します。
     * @return 前年比客数（支部）
     */
    public BigDecimal getSibuKyakusuZenhi() {
        return Calculator.percentage(this.sibuKyakusu, this.sibuKyakusuZen, 2);
    }
    /**
     * 支部客単価取得処理
     * @return
     */
    public BigDecimal getSibuKyakutanka() {
    	return Calculator.divide(this.sibuUriage, this.sibuKyakusu);
    }
    /**
     * 支部客単価(前年値)取得処理
     * @return
     */
    public BigDecimal getSibuKyakutankaZen() {
    	return Calculator.divide(this.sibuUriageZen, this.sibuKyakusuZen);
    }
    
    /**
     * 前年比客単価（支部）を取得します。
     * @return 前年比客単価（支部）
     */
    public BigDecimal getSibuKyakutankaZenHi() {
    	return Calculator.percentage(getSibuKyakutanka(), getSibuKyakutankaZen(), 2);
        
    }

    public String getUriageZenhiCls() {
        return getHirituClass(getUriageZenhi());
        
    }
    public String getKyakusuZenhiCls() {
        return getHirituClass(getKyakusuZenhi());
        
    }
    public String getKyakutankaZenhiCls() {
        return getHirituClass(getKyakutankaZenHi());
        
    }
    public String getSibuUriageZenhiCls() {
        return getHirituClass(getSibuUriageZenhi());
        
    }
    public String getSibuKyakusuZenhiCls() {
        return getHirituClass(getSibuKyakusuZenhi());
        
    }
    public String getSibuKyakutankaZenhiCls() {
        return getHirituClass(getSibuKyakutankaZenHi());
        
    }
    
    
    private String getHirituClass(BigDecimal val) {
        if (getEigyoDt().equals("月合計")) {
            return val.compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu" : "body_num";
        }
        return val.compareTo(new BigDecimal("100.00")) < 0 ? "body_hiritu_m" : "body_num_n";
    }
}
