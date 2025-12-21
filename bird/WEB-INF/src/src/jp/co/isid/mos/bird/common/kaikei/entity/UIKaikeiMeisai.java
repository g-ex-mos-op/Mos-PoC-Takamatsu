package jp.co.isid.mos.bird.common.kaikei.entity;

import java.math.BigDecimal;

/**
 * ‰ïŒv‹æ•ª–¾×î•ñƒGƒ“ƒeƒBƒeƒB
 * 
 * ‚c‚aƒf[ƒ^æ“¾Œã‚Ì‰æ–Ê•\¦—p‚Ég—p‚·‚éEntityƒNƒ‰ƒX‚Ì‚½‚ßADao‚Í‘¶İ‚µ‚Ü‚¹‚ñB
 * 
 * ì¬“ú:2012/07/27
 * @author xkinu
 *
 */
public class UIKaikeiMeisai {
    
    public static final String TABLE = "BD30KKAD";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String taishoName_COLUMN = "TAISHO_NAME";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String existsMeisai1_COLUMN = "EXISTS_MEISAI_1";
    
    public static final String meisaiKen1_COLUMN = "MEISAI_KEN_1";
    
    public static final String meisaiKin1_COLUMN = "MEISAI_KIN_1";
    
    public static final String existsMeisai2_COLUMN = "EXISTS_MEISAI_2";
    
    public static final String meisaiKen2_COLUMN = "MEISAI_KEN_2";
    
    public static final String meisaiKin2_COLUMN = "MEISAI_KIN_2";
    
    public static final String existsMeisai3_COLUMN = "EXISTS_MEISAI_3";
    
    public static final String meisaiKen3_COLUMN = "MEISAI_KEN_3";
    
    public static final String meisaiKin3_COLUMN = "MEISAI_KIN_3";
    
    public static final String existsMeisai4_COLUMN = "EXISTS_MEISAI_4";
    
    public static final String meisaiKen4_COLUMN = "MEISAI_KEN_4";
    
    public static final String meisaiKin4_COLUMN = "MEISAI_KIN_4";
    
    public static final String meisaiKenTotal_COLUMN = "MEISAI_KEN_TOTAL";
    
    public static final String meisaiKinTotal_COLUMN = "MEISAI_KIN_TOTAL";
    private BigDecimal _dec0 = new BigDecimal("0");
    /**
     * Šé‹ÆƒR[ƒh
     */
    private String companyCd;
    
    /**
     * ‘ÎÛ–¼Ì
     */
    private String taishoName;
    
    /**
     * ‰c‹Æ“ú
     */
    private String eigyoDt;
    
    /**
     * –¾×‚P‘¶İƒtƒ‰ƒO
     */
    private boolean existsMeisai1;
    
    /**
     * –¾×Œ”‚P
     */
    private BigDecimal meisaiKen1;
    
    /**
     * –¾×‹àŠz‚P
     */
    private BigDecimal meisaiKin1;
    
    /**
     * –¾×‚Q‘¶İƒtƒ‰ƒO
     */
    private boolean existsMeisai2;
    
    /**
     * –¾×Œ”‚Q
     */
    private BigDecimal meisaiKen2;
    
    /**
     * –¾×‹àŠz‚Q
     */
    private BigDecimal meisaiKin2;
    
    /**
     * –¾×‚R‘¶İƒtƒ‰ƒO
     */
    private boolean existsMeisai3;
    
    /**
     * –¾×Œ”‚R
     */
    private BigDecimal meisaiKen3;
    
    /**
     * –¾×‹àŠz‚R
     */
    private BigDecimal meisaiKin3;
    
    /**
     * –¾×‚S‘¶İƒtƒ‰ƒO
     */
    private boolean existsMeisai4;
    
    /**
     * –¾×Œ”‚S
     */
    private BigDecimal meisaiKen4;
    
    /**
     * –¾×‹àŠz‚S
     */
    private BigDecimal meisaiKin4;
    
    /**
     * –¾×Œ”‡Œv
     */
    private BigDecimal meisaiKenTotal;
    
    /**
     * –¾×‹àŠz‡Œv
     */
    private BigDecimal meisaiKinTotal;
    
    /**
     * Šé‹ÆƒR[ƒh‚ğæ“¾‚µ‚Ü‚·B
     * @return Šé‹ÆƒR[ƒh
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * Šé‹ÆƒR[ƒh‚ğİ’è‚µ‚Ü‚·B
     * @param companyCd Šé‹ÆƒR[ƒh
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * ‘ÎÛ–¼Ì‚ğæ“¾‚µ‚Ü‚·B
     * @return ‘ÎÛ–¼Ì
     */
    public String getTaishoName() {
        return taishoName;
    }
    /**
     * ‘ÎÛ–¼Ì‚ğİ’è‚µ‚Ü‚·B
     * @param taishoName ‘ÎÛ–¼Ì
     */
    public void setTaishoName(String taishoName) {
        this.taishoName = taishoName;
    }
    
    /**
     * ‰c‹Æ“ú‚ğæ“¾‚µ‚Ü‚·B
     * @return ‰c‹Æ“ú
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * ‰c‹Æ“ú‚ğİ’è‚µ‚Ü‚·B
     * @param eigyoDt ‰c‹Æ“ú
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * –¾×‚P‘¶İƒtƒ‰ƒO‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×‚P‘¶İƒtƒ‰ƒO
     */
    public boolean isExistsMeisai1() {
        return existsMeisai1;
    }
    /**
     * –¾×‚P‘¶İƒtƒ‰ƒO‚ğİ’è‚µ‚Ü‚·B
     * @param existsMeisai1 –¾×‚P‘¶İƒtƒ‰ƒO
     */
    public void setExistsMeisai1(boolean existsMeisai1) {
        this.existsMeisai1 = existsMeisai1;
    }
    
    /**
     * –¾×Œ”‚P‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×Œ”‚P
     */
    public BigDecimal getMeisaiKen1() {
    	if(meisaiKen1!= null) {
    		return meisaiKen1;
    	}
    	return _dec0;
    }
    /**
     * –¾×Œ”‚P‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKen1 –¾×Œ”‚P
     */
    public void setMeisaiKen1(BigDecimal meisaiKen1) {
        this.meisaiKen1 = meisaiKen1;
    }
    
    /**
     * –¾×‹àŠz‚P‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×‹àŠz‚P
     */
    public BigDecimal getMeisaiKin1() {
    	if(meisaiKin1!= null) {
    		return meisaiKin1;
    	}
    	return _dec0;
    }
    /**
     * –¾×‹àŠz‚P‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKin1 –¾×‹àŠz‚P
     */
    public void setMeisaiKin1(BigDecimal meisaiKin1) {
        this.meisaiKin1 = meisaiKin1;
    }
    
    /**
     * –¾×‚Q‘¶İƒtƒ‰ƒO‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×‚Q‘¶İƒtƒ‰ƒO
     */
    public boolean isExistsMeisai2() {
        return existsMeisai2;
    }
    /**
     * –¾×‚Q‘¶İƒtƒ‰ƒO‚ğİ’è‚µ‚Ü‚·B
     * @param existsMeisai2 –¾×‚Q‘¶İƒtƒ‰ƒO
     */
    public void setExistsMeisai2(boolean existsMeisai2) {
        this.existsMeisai2 = existsMeisai2;
    }
    
    /**
     * –¾×Œ”‚Q‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×Œ”‚Q
     */
    public BigDecimal getMeisaiKen2() {
    	if(meisaiKen2!= null) {
    		return meisaiKen2;
    	}
    	return _dec0;
    }
    /**
     * –¾×Œ”‚Q‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKen2 –¾×Œ”‚Q
     */
    public void setMeisaiKen2(BigDecimal meisaiKen2) {
        this.meisaiKen2 = meisaiKen2;
    }
    
    /**
     * –¾×‹àŠz‚Q‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×‹àŠz‚Q
     */
    public BigDecimal getMeisaiKin2() {
    	if(meisaiKin2!= null) {
    		return meisaiKin2;
    	}
    	return _dec0;
    }
    /**
     * –¾×‹àŠz‚Q‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKin2 –¾×‹àŠz‚Q
     */
    public void setMeisaiKin2(BigDecimal meisaiKin2) {
        this.meisaiKin2 = meisaiKin2;
    }
    
    /**
     * –¾×‚R‘¶İƒtƒ‰ƒO‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×‚R‘¶İƒtƒ‰ƒO
     */
    public boolean isExistsMeisai3() {
        return existsMeisai3;
    }
    /**
     * –¾×‚R‘¶İƒtƒ‰ƒO‚ğİ’è‚µ‚Ü‚·B
     * @param existsMeisai3 –¾×‚R‘¶İƒtƒ‰ƒO
     */
    public void setExistsMeisai3(boolean existsMeisai3) {
        this.existsMeisai3 = existsMeisai3;
    }
    
    /**
     * –¾×Œ”‚R‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×Œ”‚R
     */
    public BigDecimal getMeisaiKen3() {
    	if(meisaiKen3!= null) {
    		return meisaiKen3;
    	}
    	return _dec0;
    }
    /**
     * –¾×Œ”‚R‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKen3 –¾×Œ”‚R
     */
    public void setMeisaiKen3(BigDecimal meisaiKen3) {
        this.meisaiKen3 = meisaiKen3;
    }
    
    /**
     * –¾×‹àŠz‚R‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×‹àŠz‚R
     */
    public BigDecimal getMeisaiKin3() {
    	if(meisaiKin3!= null) {
    		return meisaiKin3;
    	}
    	return _dec0;
    }
    /**
     * –¾×‹àŠz‚R‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKin3 –¾×‹àŠz‚R
     */
    public void setMeisaiKin3(BigDecimal meisaiKin3) {
        this.meisaiKin3 = meisaiKin3;
    }
    
    /**
     * –¾×‚S‘¶İƒtƒ‰ƒO‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×‚S‘¶İƒtƒ‰ƒO
     */
    public boolean isExistsMeisai4() {
        return existsMeisai4;
    }
    /**
     * –¾×‚S‘¶İƒtƒ‰ƒO‚ğİ’è‚µ‚Ü‚·B
     * @param existsMeisai4 –¾×‚S‘¶İƒtƒ‰ƒO
     */
    public void setExistsMeisai4(boolean existsMeisai4) {
        this.existsMeisai4 = existsMeisai4;
    }
    
    /**
     * –¾×Œ”‚S‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×Œ”‚S
     */
    public BigDecimal getMeisaiKen4() {
    	if(meisaiKen4!= null) {
    		return meisaiKen4;
    	}
    	return _dec0;
    }
    /**
     * –¾×Œ”‚S‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKen4 –¾×Œ”‚S
     */
    public void setMeisaiKen4(BigDecimal meisaiKen4) {
        this.meisaiKen4 = meisaiKen4;
    }
    
    /**
     * –¾×‹àŠz‚S‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×‹àŠz‚S
     */
    public BigDecimal getMeisaiKin4() {
    	if(meisaiKin4!= null) {
    		return meisaiKin4;
    	}
    	return _dec0;
    }
    /**
     * –¾×‹àŠz‚S‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKin4 –¾×‹àŠz‚S
     */
    public void setMeisaiKin4(BigDecimal meisaiKin4) {
        this.meisaiKin4 = meisaiKin4;
    }
    
    /**
     * –¾×Œ”‡Œv‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×Œ”‡Œv
     */
    public BigDecimal getMeisaiKenTotal() {
    	if(meisaiKenTotal!= null) {
    		return meisaiKenTotal;
    	}
    	return _dec0;
    }
    /**
     * –¾×Œ”‡Œv‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKenTotal –¾×Œ”‡Œv
     */
    public void setMeisaiKenTotal(BigDecimal meisaiKenTotal) {
        this.meisaiKenTotal = meisaiKenTotal;
    }
    
    /**
     * –¾×‹àŠz‡Œv‚ğæ“¾‚µ‚Ü‚·B
     * @return –¾×‹àŠz‡Œv
     */
    public BigDecimal getMeisaiKinTotal() {
    	if(meisaiKinTotal!= null) {
    		return meisaiKinTotal;
    	}
    	return _dec0;
    }
    /**
     * –¾×‹àŠz‡Œv‚ğİ’è‚µ‚Ü‚·B
     * @param meisaiKinTotal –¾×‹àŠz‡Œv
     */
    public void setMeisaiKinTotal(BigDecimal meisaiKinTotal) {
        this.meisaiKinTotal = meisaiKinTotal;
    }
    
}
