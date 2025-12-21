package jp.co.isid.mos.bird.bizreport.urimaintenance.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TrnNBUPInfo {
    
    public static final String TABLE = "BT96NBUP";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String upFlg_COLUMN = "UP_FLG";
    
    public static final String upDt_COLUMN = "UP_DT";
    
    public static final String batupDt_COLUMN = "BATUP_DT";
    
    public static final String upNo_COLUMN = "UP_NO";
    
    public static final String u01NebikiKen1_COLUMN = "U01_NEBIKI_KEN_1";
    
    public static final String u02NebikiKin1_COLUMN = "U02_NEBIKI_KIN_1";
    
    public static final String u03NebikiTax1_COLUMN = "U03_NEBIKI_TAX_1";
    
    public static final String u04NebikiKen2_COLUMN = "U04_NEBIKI_KEN_2";
    
    public static final String u05NebikiKin2_COLUMN = "U05_NEBIKI_KIN_2";
    
    public static final String u06NebikiTax2_COLUMN = "U06_NEBIKI_TAX_2";
    
    public static final String u07NebikiKen3_COLUMN = "U07_NEBIKI_KEN_3";
    
    public static final String u08NebikiKin3_COLUMN = "U08_NEBIKI_KIN_3";
    
    public static final String u09NebikiTax3_COLUMN = "U09_NEBIKI_TAX_3";
    
    public static final String u10NebikiKen4_COLUMN = "U10_NEBIKI_KEN_4";
    
    public static final String u11NebikiKin4_COLUMN = "U11_NEBIKI_KIN_4";
    
    public static final String u12NebikiTax4_COLUMN = "U12_NEBIKI_TAX_4";
    
    public static final String u13NebikiKen5_COLUMN = "U13_NEBIKI_KEN_5";
    
    public static final String u14NebikiKin5_COLUMN = "U14_NEBIKI_KIN_5";
    
    public static final String u15NebikiTax5_COLUMN = "U15_NEBIKI_TAX_5";
    
    public static final String u16NebikiKen6_COLUMN = "U16_NEBIKI_KEN_6";
    
    public static final String u17NebikiKin6_COLUMN = "U17_NEBIKI_KIN_6";
    
    public static final String u18NebikiTax6_COLUMN = "U18_NEBIKI_TAX_6";
    
    public static final String u19NebikiKen7_COLUMN = "U19_NEBIKI_KEN_7";
    
    public static final String u20NebikiKin7_COLUMN = "U20_NEBIKI_KIN_7";
    
    public static final String u21NebikiTax7_COLUMN = "U21_NEBIKI_TAX_7";
    
    public static final String u22NebikiKen8_COLUMN = "U22_NEBIKI_KEN_8";
    
    public static final String u23NebikiKin8_COLUMN = "U23_NEBIKI_KIN_8";
    
    public static final String u24NebikiTax8_COLUMN = "U24_NEBIKI_TAX_8";
    
    public static final String u25NebikiKen9_COLUMN = "U25_NEBIKI_KEN_9";
    
    public static final String u26NebikiKin9_COLUMN = "U26_NEBIKI_KIN_9";
    
    public static final String u27NebikiTax9_COLUMN = "U27_NEBIKI_TAX_9";
    
    public static final String flg1_COLUMN = "FLG1";
    
    public static final String flg2_COLUMN = "FLG2";
    
    public static final String flg3_COLUMN = "FLG3";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * Šé‹ÆƒR[ƒh
     */
    private String companyCd;
    
    /**
     * “XƒR[ƒh
     */
    private String miseCd;
    
    /**
     * ‰c‹Æ“ú
     */
    private String eigyoDt;
    
    /**
     * ƒoƒbƒ`XVƒtƒ‰ƒO
     */
    private String upFlg;
    
    /**
     * XV“ú•t
     */
    private String upDt;
    
    /**
     * ƒoƒbƒ`XV“ú•t
     */
    private String batupDt;
    
    /**
     * XV€–ÚNO
     */
    private String upNo;
    
    /**
     * ’lˆø‚PŒ”
     */
    private BigDecimal u01NebikiKen1;
    
    /**
     * ’lˆø‚P‹àŠz
     */
    private BigDecimal u02NebikiKin1;
    
    /**
     * ’lˆø‚PÅŠz
     */
    private BigDecimal u03NebikiTax1;
    
    /**
     * ’lˆø‚QŒ”
     */
    private BigDecimal u04NebikiKen2;
    
    /**
     * ’lˆø‚Q‹àŠz
     */
    private BigDecimal u05NebikiKin2;
    
    /**
     * ’lˆø‚QÅŠz
     */
    private BigDecimal u06NebikiTax2;
    
    /**
     * ’lˆø‚RŒ”
     */
    private BigDecimal u07NebikiKen3;
    
    /**
     * ’lˆø‚R‹àŠz
     */
    private BigDecimal u08NebikiKin3;
    
    /**
     * ’lˆø‚RÅŠz
     */
    private BigDecimal u09NebikiTax3;
    
    /**
     * ’lˆø‚SŒ”
     */
    private BigDecimal u10NebikiKen4;
    
    /**
     * ’lˆø‚S‹àŠz
     */
    private BigDecimal u11NebikiKin4;
    
    /**
     * ’lˆø‚SÅŠz
     */
    private BigDecimal u12NebikiTax4;
    
    /**
     * ’lˆø‚TŒ”
     */
    private BigDecimal u13NebikiKen5;
    
    /**
     * ’lˆø‚T‹àŠz
     */
    private BigDecimal u14NebikiKin5;
    
    /**
     * ’lˆø‚TÅŠz
     */
    private BigDecimal u15NebikiTax5;
    
    /**
     * ’lˆø‚UŒ”
     */
    private BigDecimal u16NebikiKen6;
    
    /**
     * ’lˆø‚U‹àŠz
     */
    private BigDecimal u17NebikiKin6;
    
    /**
     * ’lˆø‚UÅŠz
     */
    private BigDecimal u18NebikiTax6;
    
    /**
     * ’lˆø‚VŒ”
     */
    private BigDecimal u19NebikiKen7;
    
    /**
     * ’lˆø‚V‹àŠz
     */
    private BigDecimal u20NebikiKin7;
    
    /**
     * ’lˆø‚VÅŠz
     */
    private BigDecimal u21NebikiTax7;
    
    /**
     * ’lˆø‚WŒ”
     */
    private BigDecimal u22NebikiKen8;
    
    /**
     * ’lˆø‚W‹àŠz
     */
    private BigDecimal u23NebikiKin8;
    
    /**
     * ’lˆø‚WÅŠz
     */
    private BigDecimal u24NebikiTax8;
    
    /**
     * ’lˆø‚XŒ”
     */
    private BigDecimal u25NebikiKen9;
    
    /**
     * ’lˆø‚X‹àŠz
     */
    private BigDecimal u26NebikiKin9;
    
    /**
     * ’lˆø‚XÅŠz
     */
    private BigDecimal u27NebikiTax9;
    
    /**
     * —\”õƒtƒ‰ƒO‚P
     */
    private String flg1;
    
    /**
     * —\”õƒtƒ‰ƒO‚Q
     */
    private String flg2;
    
    /**
     * —\”õƒtƒ‰ƒO‚R
     */
    private String flg3;
    
    /**
     * “o˜^ƒ†[ƒU‚h‚c
     */
    private String firstUser;
    
    /**
     * “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    private String firstPgm;
    
    /**
     * “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    private Timestamp firstTmsp;
    
    /**
     * C³ƒ†[ƒU‚h‚c
     */
    private String  lastUser;
    
    /**
     * C³ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    private String  lastPgm;
    
    /**
     * C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    private Timestamp lastTmsp;
    
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
     * “XƒR[ƒh‚ğæ“¾‚µ‚Ü‚·B
     * @return “XƒR[ƒh
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * “XƒR[ƒh‚ğİ’è‚µ‚Ü‚·B
     * @param miseCd “XƒR[ƒh
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
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
     * ƒoƒbƒ`XVƒtƒ‰ƒO‚ğæ“¾‚µ‚Ü‚·B
     * @return ƒoƒbƒ`XVƒtƒ‰ƒO
     */
    public String getUpFlg() {
        return upFlg;
    }
    /**
     * ƒoƒbƒ`XVƒtƒ‰ƒO‚ğİ’è‚µ‚Ü‚·B
     * @param upFlg ƒoƒbƒ`XVƒtƒ‰ƒO
     */
    public void setUpFlg(String upFlg) {
        this.upFlg = upFlg;
    }
    
    /**
     * XV“ú•t‚ğæ“¾‚µ‚Ü‚·B
     * @return XV“ú•t
     */
    public String getUpDt() {
        return upDt;
    }
    /**
     * XV“ú•t‚ğİ’è‚µ‚Ü‚·B
     * @param upDt XV“ú•t
     */
    public void setUpDt(String upDt) {
        this.upDt = upDt;
    }
    
    /**
     * ƒoƒbƒ`XV“ú•t‚ğæ“¾‚µ‚Ü‚·B
     * @return ƒoƒbƒ`XV“ú•t
     */
    public String getBatupDt() {
        return batupDt;
    }
    /**
     * ƒoƒbƒ`XV“ú•t‚ğİ’è‚µ‚Ü‚·B
     * @param batupDt ƒoƒbƒ`XV“ú•t
     */
    public void setBatupDt(String batupDt) {
        this.batupDt = batupDt;
    }
    
    /**
     * XV€–ÚNO‚ğæ“¾‚µ‚Ü‚·B
     * @return XV€–ÚNO
     */
    public String getUpNo() {
        return upNo;
    }
    /**
     * XV€–ÚNO‚ğİ’è‚µ‚Ü‚·B
     * @param upNo XV€–ÚNO
     */
    public void setUpNo(String upNo) {
        this.upNo = upNo;
    }
    
    /**
     * ’lˆø‚PŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚PŒ”
     */
    public BigDecimal getU01NebikiKen1() {
        return u01NebikiKen1;
    }
    /**
     * ’lˆø‚PŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param u01NebikiKen1 ’lˆø‚PŒ”
     */
    public void setU01NebikiKen1(BigDecimal u01NebikiKen1) {
        this.u01NebikiKen1 = u01NebikiKen1;
    }
    
    /**
     * ’lˆø‚P‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚P‹àŠz
     */
    public BigDecimal getU02NebikiKin1() {
        return u02NebikiKin1;
    }
    /**
     * ’lˆø‚P‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u02NebikiKin1 ’lˆø‚P‹àŠz
     */
    public void setU02NebikiKin1(BigDecimal u02NebikiKin1) {
        this.u02NebikiKin1 = u02NebikiKin1;
    }
    
    /**
     * ’lˆø‚PÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚PÅŠz
     */
    public BigDecimal getU03NebikiTax1() {
        return u03NebikiTax1;
    }
    /**
     * ’lˆø‚PÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u03NebikiTax1 ’lˆø‚PÅŠz
     */
    public void setU03NebikiTax1(BigDecimal u03NebikiTax1) {
        this.u03NebikiTax1 = u03NebikiTax1;
    }
    
    /**
     * ’lˆø‚QŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚QŒ”
     */
    public BigDecimal getU04NebikiKen2() {
        return u04NebikiKen2;
    }
    /**
     * ’lˆø‚QŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param u04NebikiKen2 ’lˆø‚QŒ”
     */
    public void setU04NebikiKen2(BigDecimal u04NebikiKen2) {
        this.u04NebikiKen2 = u04NebikiKen2;
    }
    
    /**
     * ’lˆø‚Q‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚Q‹àŠz
     */
    public BigDecimal getU05NebikiKin2() {
        return u05NebikiKin2;
    }
    /**
     * ’lˆø‚Q‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u05NebikiKin2 ’lˆø‚Q‹àŠz
     */
    public void setU05NebikiKin2(BigDecimal u05NebikiKin2) {
        this.u05NebikiKin2 = u05NebikiKin2;
    }
    
    /**
     * ’lˆø‚QÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚QÅŠz
     */
    public BigDecimal getU06NebikiTax2() {
        return u06NebikiTax2;
    }
    /**
     * ’lˆø‚QÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u06NebikiTax2 ’lˆø‚QÅŠz
     */
    public void setU06NebikiTax2(BigDecimal u06NebikiTax2) {
        this.u06NebikiTax2 = u06NebikiTax2;
    }
    
    /**
     * ’lˆø‚RŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚RŒ”
     */
    public BigDecimal getU07NebikiKen3() {
        return u07NebikiKen3;
    }
    /**
     * ’lˆø‚RŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param u07NebikiKen3 ’lˆø‚RŒ”
     */
    public void setU07NebikiKen3(BigDecimal u07NebikiKen3) {
        this.u07NebikiKen3 = u07NebikiKen3;
    }
    
    /**
     * ’lˆø‚R‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚R‹àŠz
     */
    public BigDecimal getU08NebikiKin3() {
        return u08NebikiKin3;
    }
    /**
     * ’lˆø‚R‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u08NebikiKin3 ’lˆø‚R‹àŠz
     */
    public void setU08NebikiKin3(BigDecimal u08NebikiKin3) {
        this.u08NebikiKin3 = u08NebikiKin3;
    }
    
    /**
     * ’lˆø‚RÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚RÅŠz
     */
    public BigDecimal getU09NebikiTax3() {
        return u09NebikiTax3;
    }
    /**
     * ’lˆø‚RÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u09NebikiTax3 ’lˆø‚RÅŠz
     */
    public void setU09NebikiTax3(BigDecimal u09NebikiTax3) {
        this.u09NebikiTax3 = u09NebikiTax3;
    }
    
    /**
     * ’lˆø‚SŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚SŒ”
     */
    public BigDecimal getU10NebikiKen4() {
        return u10NebikiKen4;
    }
    /**
     * ’lˆø‚SŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param u10NebikiKen4 ’lˆø‚SŒ”
     */
    public void setU10NebikiKen4(BigDecimal u10NebikiKen4) {
        this.u10NebikiKen4 = u10NebikiKen4;
    }
    
    /**
     * ’lˆø‚S‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚S‹àŠz
     */
    public BigDecimal getU11NebikiKin4() {
        return u11NebikiKin4;
    }
    /**
     * ’lˆø‚S‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u11NebikiKin4 ’lˆø‚S‹àŠz
     */
    public void setU11NebikiKin4(BigDecimal u11NebikiKin4) {
        this.u11NebikiKin4 = u11NebikiKin4;
    }
    
    /**
     * ’lˆø‚SÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚SÅŠz
     */
    public BigDecimal getU12NebikiTax4() {
        return u12NebikiTax4;
    }
    /**
     * ’lˆø‚SÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u12NebikiTax4 ’lˆø‚SÅŠz
     */
    public void setU12NebikiTax4(BigDecimal u12NebikiTax4) {
        this.u12NebikiTax4 = u12NebikiTax4;
    }
    
    /**
     * ’lˆø‚TŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚TŒ”
     */
    public BigDecimal getU13NebikiKen5() {
        return u13NebikiKen5;
    }
    /**
     * ’lˆø‚TŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param u13NebikiKen5 ’lˆø‚TŒ”
     */
    public void setU13NebikiKen5(BigDecimal u13NebikiKen5) {
        this.u13NebikiKen5 = u13NebikiKen5;
    }
    
    /**
     * ’lˆø‚T‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚T‹àŠz
     */
    public BigDecimal getU14NebikiKin5() {
        return u14NebikiKin5;
    }
    /**
     * ’lˆø‚T‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u14NebikiKin5 ’lˆø‚T‹àŠz
     */
    public void setU14NebikiKin5(BigDecimal u14NebikiKin5) {
        this.u14NebikiKin5 = u14NebikiKin5;
    }
    
    /**
     * ’lˆø‚TÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚TÅŠz
     */
    public BigDecimal getU15NebikiTax5() {
        return u15NebikiTax5;
    }
    /**
     * ’lˆø‚TÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u15NebikiTax5 ’lˆø‚TÅŠz
     */
    public void setU15NebikiTax5(BigDecimal u15NebikiTax5) {
        this.u15NebikiTax5 = u15NebikiTax5;
    }
    
    /**
     * ’lˆø‚UŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚UŒ”
     */
    public BigDecimal getU16NebikiKen6() {
        return u16NebikiKen6;
    }
    /**
     * ’lˆø‚UŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param u16NebikiKen6 ’lˆø‚UŒ”
     */
    public void setU16NebikiKen6(BigDecimal u16NebikiKen6) {
        this.u16NebikiKen6 = u16NebikiKen6;
    }
    
    /**
     * ’lˆø‚U‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚U‹àŠz
     */
    public BigDecimal getU17NebikiKin6() {
        return u17NebikiKin6;
    }
    /**
     * ’lˆø‚U‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u17NebikiKin6 ’lˆø‚U‹àŠz
     */
    public void setU17NebikiKin6(BigDecimal u17NebikiKin6) {
        this.u17NebikiKin6 = u17NebikiKin6;
    }
    
    /**
     * ’lˆø‚UÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚UÅŠz
     */
    public BigDecimal getU18NebikiTax6() {
        return u18NebikiTax6;
    }
    /**
     * ’lˆø‚UÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u18NebikiTax6 ’lˆø‚UÅŠz
     */
    public void setU18NebikiTax6(BigDecimal u18NebikiTax6) {
        this.u18NebikiTax6 = u18NebikiTax6;
    }
    
    /**
     * ’lˆø‚VŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚VŒ”
     */
    public BigDecimal getU19NebikiKen7() {
        return u19NebikiKen7;
    }
    /**
     * ’lˆø‚VŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param u19NebikiKen7 ’lˆø‚VŒ”
     */
    public void setU19NebikiKen7(BigDecimal u19NebikiKen7) {
        this.u19NebikiKen7 = u19NebikiKen7;
    }
    
    /**
     * ’lˆø‚V‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚V‹àŠz
     */
    public BigDecimal getU20NebikiKin7() {
        return u20NebikiKin7;
    }
    /**
     * ’lˆø‚V‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u20NebikiKin7 ’lˆø‚V‹àŠz
     */
    public void setU20NebikiKin7(BigDecimal u20NebikiKin7) {
        this.u20NebikiKin7 = u20NebikiKin7;
    }
    
    /**
     * ’lˆø‚VÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚VÅŠz
     */
    public BigDecimal getU21NebikiTax7() {
        return u21NebikiTax7;
    }
    /**
     * ’lˆø‚VÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u21NebikiTax7 ’lˆø‚VÅŠz
     */
    public void setU21NebikiTax7(BigDecimal u21NebikiTax7) {
        this.u21NebikiTax7 = u21NebikiTax7;
    }
    
    /**
     * ’lˆø‚WŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚WŒ”
     */
    public BigDecimal getU22NebikiKen8() {
        return u22NebikiKen8;
    }
    /**
     * ’lˆø‚WŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param u22NebikiKen8 ’lˆø‚WŒ”
     */
    public void setU22NebikiKen8(BigDecimal u22NebikiKen8) {
        this.u22NebikiKen8 = u22NebikiKen8;
    }
    
    /**
     * ’lˆø‚W‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚W‹àŠz
     */
    public BigDecimal getU23NebikiKin8() {
        return u23NebikiKin8;
    }
    /**
     * ’lˆø‚W‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u23NebikiKin8 ’lˆø‚W‹àŠz
     */
    public void setU23NebikiKin8(BigDecimal u23NebikiKin8) {
        this.u23NebikiKin8 = u23NebikiKin8;
    }
    
    /**
     * ’lˆø‚WÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚WÅŠz
     */
    public BigDecimal getU24NebikiTax8() {
        return u24NebikiTax8;
    }
    /**
     * ’lˆø‚WÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u24NebikiTax8 ’lˆø‚WÅŠz
     */
    public void setU24NebikiTax8(BigDecimal u24NebikiTax8) {
        this.u24NebikiTax8 = u24NebikiTax8;
    }
    
    /**
     * ’lˆø‚XŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚XŒ”
     */
    public BigDecimal getU25NebikiKen9() {
        return u25NebikiKen9;
    }
    /**
     * ’lˆø‚XŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param u25NebikiKen9 ’lˆø‚XŒ”
     */
    public void setU25NebikiKen9(BigDecimal u25NebikiKen9) {
        this.u25NebikiKen9 = u25NebikiKen9;
    }
    
    /**
     * ’lˆø‚X‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚X‹àŠz
     */
    public BigDecimal getU26NebikiKin9() {
        return u26NebikiKin9;
    }
    /**
     * ’lˆø‚X‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u26NebikiKin9 ’lˆø‚X‹àŠz
     */
    public void setU26NebikiKin9(BigDecimal u26NebikiKin9) {
        this.u26NebikiKin9 = u26NebikiKin9;
    }
    
    /**
     * ’lˆø‚XÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚XÅŠz
     */
    public BigDecimal getU27NebikiTax9() {
        return u27NebikiTax9;
    }
    /**
     * ’lˆø‚XÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param u27NebikiTax9 ’lˆø‚XÅŠz
     */
    public void setU27NebikiTax9(BigDecimal u27NebikiTax9) {
        this.u27NebikiTax9 = u27NebikiTax9;
    }
    
    /**
     * —\”õƒtƒ‰ƒO‚P‚ğæ“¾‚µ‚Ü‚·B
     * @return —\”õƒtƒ‰ƒO‚P
     */
    public String getFlg1() {
        return flg1;
    }
    /**
     * —\”õƒtƒ‰ƒO‚P‚ğİ’è‚µ‚Ü‚·B
     * @param flg1 —\”õƒtƒ‰ƒO‚P
     */
    public void setFlg1(String flg1) {
        this.flg1 = flg1;
    }
    
    /**
     * —\”õƒtƒ‰ƒO‚Q‚ğæ“¾‚µ‚Ü‚·B
     * @return —\”õƒtƒ‰ƒO‚Q
     */
    public String getFlg2() {
        return flg2;
    }
    /**
     * —\”õƒtƒ‰ƒO‚Q‚ğİ’è‚µ‚Ü‚·B
     * @param flg2 —\”õƒtƒ‰ƒO‚Q
     */
    public void setFlg2(String flg2) {
        this.flg2 = flg2;
    }
    
    /**
     * —\”õƒtƒ‰ƒO‚R‚ğæ“¾‚µ‚Ü‚·B
     * @return —\”õƒtƒ‰ƒO‚R
     */
    public String getFlg3() {
        return flg3;
    }
    /**
     * —\”õƒtƒ‰ƒO‚R‚ğİ’è‚µ‚Ü‚·B
     * @param flg3 —\”õƒtƒ‰ƒO‚R
     */
    public void setFlg3(String flg3) {
        this.flg3 = flg3;
    }
    
    /**
     * “o˜^ƒ†[ƒU‚h‚c‚ğæ“¾‚µ‚Ü‚·B
     * @return “o˜^ƒ†[ƒU‚h‚c
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * “o˜^ƒ†[ƒU‚h‚c‚ğİ’è‚µ‚Ü‚·B
     * @param firstUser “o˜^ƒ†[ƒU‚h‚c
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c‚ğæ“¾‚µ‚Ü‚·B
     * @return “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c‚ğİ’è‚µ‚Ü‚·B
     * @param firstPgm “o˜^ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv‚ğæ“¾‚µ‚Ü‚·B
     * @return “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv‚ğİ’è‚µ‚Ü‚·B
     * @param firstTmsp “o˜^ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * C³ƒ†[ƒU‚h‚c‚ğæ“¾‚µ‚Ü‚·B
     * @return C³ƒ†[ƒU‚h‚c
     */
    public String  getLastUser() {
        return lastUser;
    }
    /**
     * C³ƒ†[ƒU‚h‚c‚ğİ’è‚µ‚Ü‚·B
     * @param lastUser C³ƒ†[ƒU‚h‚c
     */
    public void setLastUser(String  lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * C³ƒvƒƒOƒ‰ƒ€‚h‚c‚ğæ“¾‚µ‚Ü‚·B
     * @return C³ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    public String  getLastPgm() {
        return lastPgm;
    }
    /**
     * C³ƒvƒƒOƒ‰ƒ€‚h‚c‚ğİ’è‚µ‚Ü‚·B
     * @param lastPgm C³ƒvƒƒOƒ‰ƒ€‚h‚c
     */
    public void setLastPgm(String  lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv‚ğæ“¾‚µ‚Ü‚·B
     * @return C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv‚ğİ’è‚µ‚Ü‚·B
     * @param lastTmsp C³ƒ^ƒCƒ€ƒXƒ^ƒ“ƒv
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
