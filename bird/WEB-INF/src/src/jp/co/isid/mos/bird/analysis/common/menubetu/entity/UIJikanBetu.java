package jp.co.isid.mos.bird.analysis.common.menubetu.entity;

import java.math.BigDecimal;

/**
 * ŠÔ‘Ñ•Êƒƒjƒ…[•Ê”„ãƒGƒ“ƒeƒBƒeƒB
 * 
 * ì¬“ú:2008/09/19
 * @author xkinu
 *
 */
public class UIJikanBetu extends UIAnalysis {
    
    public static final String TABLE = "BD17JMRI";
    
    public static final String menuBunrui_COLUMN = "MENU_BUNRUI";
    
    public static final String mbunruiNameKj_COLUMN = "MBUNRUI_NAME_KJ";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    
    public static final String kosu_COLUMN = "KOSU";
    
    public static final String kingaku_COLUMN = "KINGAKU";
    
    public static final String kingakuKouseiHi_COLUMN = "KINGAKU_KOUSEI_HI";

    public static final String kosuKouseiHi_COLUMN = "KOSU_KOUSEI_HI";
    
    public static final String kin00_COLUMN = "KIN_00";
    
    public static final String kosu00_COLUMN = "KOSU_00";
    
    public static final String kin01_COLUMN = "KIN_01";
    
    public static final String kosu01_COLUMN = "KOSU_01";
    
    public static final String kin02_COLUMN = "KIN_02";
    
    public static final String kosu02_COLUMN = "KOSU_02";
    
    public static final String kin03_COLUMN = "KIN_03";
    
    public static final String kosu03_COLUMN = "KOSU_03";
    
    public static final String kin04_COLUMN = "KIN_04";
    
    public static final String kosu04_COLUMN = "KOSU_04";
    
    public static final String kin05_COLUMN = "KIN_05";
    
    public static final String kosu05_COLUMN = "KOSU_05";
    
    public static final String kin06_COLUMN = "KIN_06";
    
    public static final String kosu06_COLUMN = "KOSU_06";
    
    public static final String kin07_COLUMN = "KIN_07";
    
    public static final String kosu07_COLUMN = "KOSU_07";
    
    public static final String kin08_COLUMN = "KIN_08";
    
    public static final String kosu08_COLUMN = "KOSU_08";
    
    public static final String kin09_COLUMN = "KIN_09";
    
    public static final String kosu09_COLUMN = "KOSU_09";
    
    public static final String kin10_COLUMN = "KIN_10";
    
    public static final String kosu10_COLUMN = "KOSU_10";
    
    public static final String kin11_COLUMN = "KIN_11";
    
    public static final String kosu11_COLUMN = "KOSU_11";
    
    public static final String kin12_COLUMN = "KIN_12";
    
    public static final String kosu12_COLUMN = "KOSU_12";
    
    public static final String kin13_COLUMN = "KIN_13";
    
    public static final String kosu13_COLUMN = "KOSU_13";
    
    public static final String kin14_COLUMN = "KIN_14";
    
    public static final String kosu14_COLUMN = "KOSU_14";
    
    public static final String kin15_COLUMN = "KIN_15";
    
    public static final String kosu15_COLUMN = "KOSU_15";
    
    public static final String kin16_COLUMN = "KIN_16";
    
    public static final String kosu16_COLUMN = "KOSU_16";
    
    public static final String kin17_COLUMN = "KIN_17";
    
    public static final String kosu17_COLUMN = "KOSU_17";
    
    public static final String kin18_COLUMN = "KIN_18";
    
    public static final String kosu18_COLUMN = "KOSU_18";
    
    public static final String kin19_COLUMN = "KIN_19";
    
    public static final String kosu19_COLUMN = "KOSU_19";
    
    public static final String kin20_COLUMN = "KIN_20";
    
    public static final String kosu20_COLUMN = "KOSU_20";
    
    public static final String kin21_COLUMN = "KIN_21";
    
    public static final String kosu21_COLUMN = "KOSU_21";
    
    public static final String kin22_COLUMN = "KIN_22";
    
    public static final String kosu22_COLUMN = "KOSU_22";
    
    public static final String kin23_COLUMN = "KIN_23";
    
    public static final String kosu23_COLUMN = "KOSU_23";
    
    public static final String kinEtc_COLUMN = "KIN_ETC";
    
    public static final String kosuEtc_COLUMN = "KOSU_ETC";
    
    /**
     * ƒƒjƒ…[•ª—ŞƒR[ƒh
     */
    private String menuBunrui;
    
    /**
     * ƒƒjƒ…[•ª—Ş–¼ÌiŠ¿šj
     */
    private String mbunruiNameKj;
    
    /**
     * ƒƒjƒ…[ƒR[ƒh
     */
    private String menuCd;
    
    /**
     * ƒƒjƒ…[–¼Ì
     */
    private String menuNameKj;
    
    /**
     * w’èŠú“ú”Ì”„ŒÂ”
     */
    private BigDecimal kosu = new BigDecimal("0");
    
    /**
     * w’èŠú“ú”Ì”„‹àŠz
     */
    private BigDecimal kingaku = new BigDecimal("0");
    
    /**
     * w’èŠú“ú‹àŠz\¬”ä
     */
    private BigDecimal kingakuKouseiHi = new BigDecimal("0");
    
    
    /**
     * ŒÂ”\¬”ä
     */
    private BigDecimal kosuKouseiHi = new BigDecimal("0");
    
    /**
     * ‹àŠz00
     */
    private BigDecimal kin00 = new BigDecimal("0");
    
    /**
     * ŒÂ”00
     */
    private BigDecimal kosu00 = new BigDecimal("0");
    
    /**
     * ‹àŠz01
     */
    private BigDecimal kin01 = new BigDecimal("0");
    
    /**
     * ŒÂ”01
     */
    private BigDecimal kosu01 = new BigDecimal("0");
    
    /**
     * ‹àŠz02
     */
    private BigDecimal kin02 = new BigDecimal("0");
    
    /**
     * ŒÂ”02
     */
    private BigDecimal kosu02 = new BigDecimal("0");
    
    /**
     * ‹àŠz03
     */
    private BigDecimal kin03 = new BigDecimal("0");
    
    /**
     * ŒÂ”03
     */
    private BigDecimal kosu03 = new BigDecimal("0");
    
    /**
     * ‹àŠz04
     */
    private BigDecimal kin04 = new BigDecimal("0");
    
    /**
     * ŒÂ”04
     */
    private BigDecimal kosu04 = new BigDecimal("0");
    
    /**
     * ‹àŠz05
     */
    private BigDecimal kin05 = new BigDecimal("0");
    
    /**
     * ŒÂ”05
     */
    private BigDecimal kosu05 = new BigDecimal("0");
    
    /**
     * ‹àŠz06
     */
    private BigDecimal kin06 = new BigDecimal("0");
    
    /**
     * ŒÂ”06
     */
    private BigDecimal kosu06 = new BigDecimal("0");
    
    /**
     * ‹àŠz07
     */
    private BigDecimal kin07 = new BigDecimal("0");
    
    /**
     * ŒÂ”07
     */
    private BigDecimal kosu07 = new BigDecimal("0");
    
    /**
     * ‹àŠz08
     */
    private BigDecimal kin08 = new BigDecimal("0");
    
    /**
     * ŒÂ”08
     */
    private BigDecimal kosu08 = new BigDecimal("0");
    
    /**
     * ‹àŠz09
     */
    private BigDecimal kin09 = new BigDecimal("0");
    
    /**
     * ŒÂ”09
     */
    private BigDecimal kosu09 = new BigDecimal("0");
    
    /**
     * ‹àŠz10
     */
    private BigDecimal kin10 = new BigDecimal("0");
    
    /**
     * ŒÂ”10
     */
    private BigDecimal kosu10 = new BigDecimal("0");
    
    /**
     * ‹àŠz11
     */
    private BigDecimal kin11 = new BigDecimal("0");
    
    /**
     * ŒÂ”11
     */
    private BigDecimal kosu11 = new BigDecimal("0");
    
    /**
     * ‹àŠz12
     */
    private BigDecimal kin12 = new BigDecimal("0");
    
    /**
     * ŒÂ”12
     */
    private BigDecimal kosu12 = new BigDecimal("0");
    
    /**
     * ‹àŠz13
     */
    private BigDecimal kin13 = new BigDecimal("0");
    
    /**
     * ŒÂ”13
     */
    private BigDecimal kosu13 = new BigDecimal("0");
    
    /**
     * ‹àŠz14
     */
    private BigDecimal kin14 = new BigDecimal("0");
    
    /**
     * ŒÂ”14
     */
    private BigDecimal kosu14 = new BigDecimal("0");
    
    /**
     * ‹àŠz15
     */
    private BigDecimal kin15 = new BigDecimal("0");
    
    /**
     * ŒÂ”15
     */
    private BigDecimal kosu15 = new BigDecimal("0");
    
    /**
     * ‹àŠz16
     */
    private BigDecimal kin16 = new BigDecimal("0");
    
    /**
     * ŒÂ”16
     */
    private BigDecimal kosu16 = new BigDecimal("0");
    
    /**
     * ‹àŠz17
     */
    private BigDecimal kin17 = new BigDecimal("0");
    
    /**
     * ŒÂ”17
     */
    private BigDecimal kosu17 = new BigDecimal("0");
    
    /**
     * ‹àŠz18
     */
    private BigDecimal kin18 = new BigDecimal("0");
    
    /**
     * ŒÂ”18
     */
    private BigDecimal kosu18 = new BigDecimal("0");
    
    /**
     * ‹àŠz19
     */
    private BigDecimal kin19 = new BigDecimal("0");
    
    /**
     * ŒÂ”19
     */
    private BigDecimal kosu19 = new BigDecimal("0");
    
    /**
     * ‹àŠz20
     */
    private BigDecimal kin20 = new BigDecimal("0");
    
    /**
     * ŒÂ”20
     */
    private BigDecimal kosu20 = new BigDecimal("0");
    
    /**
     * ‹àŠz21
     */
    private BigDecimal kin21 = new BigDecimal("0");
    
    /**
     * ŒÂ”21
     */
    private BigDecimal kosu21 = new BigDecimal("0");
    
    /**
     * ‹àŠz22
     */
    private BigDecimal kin22 = new BigDecimal("0");
    
    /**
     * ŒÂ”22
     */
    private BigDecimal kosu22 = new BigDecimal("0");
    
    /**
     * ‹àŠz23
     */
    private BigDecimal kin23 = new BigDecimal("0");
    
    /**
     * ŒÂ”23
     */
    private BigDecimal kosu23 = new BigDecimal("0");
    
    /**
     * ‹àŠz‚»‚Ì‘¼
     */
    private BigDecimal kinEtc = new BigDecimal("0");
    
    /**
     * ŒÂ”‚»‚Ì‘¼
     */
    private BigDecimal kosuEtc = new BigDecimal("0");
    
    /**
     * ŒÂ”\¬”ä‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”\¬”ä
     */
    public BigDecimal getKosuKouseiHi() {
        return kosuKouseiHi;
    }
    /**
     * ŒÂ”\¬”ä‚ğİ’è‚µ‚Ü‚·B
     * @param kosuKouseiHi ŒÂ”\¬”ä
     */
    public void setKosuKouseiHi(BigDecimal kosuKouseiHi) {
        this.kosuKouseiHi = kosuKouseiHi;
    }
    
    /**
     * ‹àŠz00‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz00
     */
    public BigDecimal getKin00() {
        return kin00;
    }
    /**
     * ‹àŠz00‚ğİ’è‚µ‚Ü‚·B
     * @param kin00 ‹àŠz00
     */
    public void setKin00(BigDecimal kin00) {
        this.kin00 = kin00;
    }
    
    /**
     * ŒÂ”00‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”00
     */
    public BigDecimal getKosu00() {
        return kosu00;
    }
    /**
     * ŒÂ”00‚ğİ’è‚µ‚Ü‚·B
     * @param kosu00 ŒÂ”00
     */
    public void setKosu00(BigDecimal kosu00) {
        this.kosu00 = kosu00;
    }
    
    /**
     * ‹àŠz01‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz01
     */
    public BigDecimal getKin01() {
        return kin01;
    }
    /**
     * ‹àŠz01‚ğİ’è‚µ‚Ü‚·B
     * @param kin01 ‹àŠz01
     */
    public void setKin01(BigDecimal kin01) {
        this.kin01 = kin01;
    }
    
    /**
     * ŒÂ”01‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”01
     */
    public BigDecimal getKosu01() {
        return kosu01;
    }
    /**
     * ŒÂ”01‚ğİ’è‚µ‚Ü‚·B
     * @param kosu01 ŒÂ”01
     */
    public void setKosu01(BigDecimal kosu01) {
        this.kosu01 = kosu01;
    }
    
    /**
     * ‹àŠz02‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz02
     */
    public BigDecimal getKin02() {
        return kin02;
    }
    /**
     * ‹àŠz02‚ğİ’è‚µ‚Ü‚·B
     * @param kin02 ‹àŠz02
     */
    public void setKin02(BigDecimal kin02) {
        this.kin02 = kin02;
    }
    
    /**
     * ŒÂ”02‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”02
     */
    public BigDecimal getKosu02() {
        return kosu02;
    }
    /**
     * ŒÂ”02‚ğİ’è‚µ‚Ü‚·B
     * @param kosu02 ŒÂ”02
     */
    public void setKosu02(BigDecimal kosu02) {
        this.kosu02 = kosu02;
    }
    
    /**
     * ‹àŠz03‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz03
     */
    public BigDecimal getKin03() {
        return kin03;
    }
    /**
     * ‹àŠz03‚ğİ’è‚µ‚Ü‚·B
     * @param kin03 ‹àŠz03
     */
    public void setKin03(BigDecimal kin03) {
        this.kin03 = kin03;
    }
    
    /**
     * ŒÂ”03‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”03
     */
    public BigDecimal getKosu03() {
        return kosu03;
    }
    /**
     * ŒÂ”03‚ğİ’è‚µ‚Ü‚·B
     * @param kosu03 ŒÂ”03
     */
    public void setKosu03(BigDecimal kosu03) {
        this.kosu03 = kosu03;
    }
    
    /**
     * ‹àŠz04‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz04
     */
    public BigDecimal getKin04() {
        return kin04;
    }
    /**
     * ‹àŠz04‚ğİ’è‚µ‚Ü‚·B
     * @param kin04 ‹àŠz04
     */
    public void setKin04(BigDecimal kin04) {
        this.kin04 = kin04;
    }
    
    /**
     * ŒÂ”04‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”04
     */
    public BigDecimal getKosu04() {
        return kosu04;
    }
    /**
     * ŒÂ”04‚ğİ’è‚µ‚Ü‚·B
     * @param kosu04 ŒÂ”04
     */
    public void setKosu04(BigDecimal kosu04) {
        this.kosu04 = kosu04;
    }
    
    /**
     * ‹àŠz05‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz05
     */
    public BigDecimal getKin05() {
        return kin05;
    }
    /**
     * ‹àŠz05‚ğİ’è‚µ‚Ü‚·B
     * @param kin05 ‹àŠz05
     */
    public void setKin05(BigDecimal kin05) {
        this.kin05 = kin05;
    }
    
    /**
     * ŒÂ”05‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”05
     */
    public BigDecimal getKosu05() {
        return kosu05;
    }
    /**
     * ŒÂ”05‚ğİ’è‚µ‚Ü‚·B
     * @param kosu05 ŒÂ”05
     */
    public void setKosu05(BigDecimal kosu05) {
        this.kosu05 = kosu05;
    }
    
    /**
     * ‹àŠz06‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz06
     */
    public BigDecimal getKin06() {
        return kin06;
    }
    /**
     * ‹àŠz06‚ğİ’è‚µ‚Ü‚·B
     * @param kin06 ‹àŠz06
     */
    public void setKin06(BigDecimal kin06) {
        this.kin06 = kin06;
    }
    
    /**
     * ŒÂ”06‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”06
     */
    public BigDecimal getKosu06() {
        return kosu06;
    }
    /**
     * ŒÂ”06‚ğİ’è‚µ‚Ü‚·B
     * @param kosu06 ŒÂ”06
     */
    public void setKosu06(BigDecimal kosu06) {
        this.kosu06 = kosu06;
    }
    
    /**
     * ‹àŠz07‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz07
     */
    public BigDecimal getKin07() {
        return kin07;
    }
    /**
     * ‹àŠz07‚ğİ’è‚µ‚Ü‚·B
     * @param kin07 ‹àŠz07
     */
    public void setKin07(BigDecimal kin07) {
        this.kin07 = kin07;
    }
    
    /**
     * ŒÂ”07‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”07
     */
    public BigDecimal getKosu07() {
        return kosu07;
    }
    /**
     * ŒÂ”07‚ğİ’è‚µ‚Ü‚·B
     * @param kosu07 ŒÂ”07
     */
    public void setKosu07(BigDecimal kosu07) {
        this.kosu07 = kosu07;
    }
    
    /**
     * ‹àŠz08‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz08
     */
    public BigDecimal getKin08() {
        return kin08;
    }
    /**
     * ‹àŠz08‚ğİ’è‚µ‚Ü‚·B
     * @param kin08 ‹àŠz08
     */
    public void setKin08(BigDecimal kin08) {
        this.kin08 = kin08;
    }
    
    /**
     * ŒÂ”08‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”08
     */
    public BigDecimal getKosu08() {
        return kosu08;
    }
    /**
     * ŒÂ”08‚ğİ’è‚µ‚Ü‚·B
     * @param kosu08 ŒÂ”08
     */
    public void setKosu08(BigDecimal kosu08) {
        this.kosu08 = kosu08;
    }
    
    /**
     * ‹àŠz09‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz09
     */
    public BigDecimal getKin09() {
        return kin09;
    }
    /**
     * ‹àŠz09‚ğİ’è‚µ‚Ü‚·B
     * @param kin09 ‹àŠz09
     */
    public void setKin09(BigDecimal kin09) {
        this.kin09 = kin09;
    }
    
    /**
     * ŒÂ”09‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”09
     */
    public BigDecimal getKosu09() {
        return kosu09;
    }
    /**
     * ŒÂ”09‚ğİ’è‚µ‚Ü‚·B
     * @param kosu09 ŒÂ”09
     */
    public void setKosu09(BigDecimal kosu09) {
        this.kosu09 = kosu09;
    }
    
    /**
     * ‹àŠz10‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz10
     */
    public BigDecimal getKin10() {
        return kin10;
    }
    /**
     * ‹àŠz10‚ğİ’è‚µ‚Ü‚·B
     * @param kin10 ‹àŠz10
     */
    public void setKin10(BigDecimal kin10) {
        this.kin10 = kin10;
    }
    
    /**
     * ŒÂ”10‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”10
     */
    public BigDecimal getKosu10() {
        return kosu10;
    }
    /**
     * ŒÂ”10‚ğİ’è‚µ‚Ü‚·B
     * @param kosu10 ŒÂ”10
     */
    public void setKosu10(BigDecimal kosu10) {
        this.kosu10 = kosu10;
    }
    
    /**
     * ‹àŠz11‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz11
     */
    public BigDecimal getKin11() {
        return kin11;
    }
    /**
     * ‹àŠz11‚ğİ’è‚µ‚Ü‚·B
     * @param kin11 ‹àŠz11
     */
    public void setKin11(BigDecimal kin11) {
        this.kin11 = kin11;
    }
    
    /**
     * ŒÂ”11‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”11
     */
    public BigDecimal getKosu11() {
        return kosu11;
    }
    /**
     * ŒÂ”11‚ğİ’è‚µ‚Ü‚·B
     * @param kosu11 ŒÂ”11
     */
    public void setKosu11(BigDecimal kosu11) {
        this.kosu11 = kosu11;
    }
    
    /**
     * ‹àŠz12‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz12
     */
    public BigDecimal getKin12() {
        return kin12;
    }
    /**
     * ‹àŠz12‚ğİ’è‚µ‚Ü‚·B
     * @param kin12 ‹àŠz12
     */
    public void setKin12(BigDecimal kin12) {
        this.kin12 = kin12;
    }
    
    /**
     * ŒÂ”12‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”12
     */
    public BigDecimal getKosu12() {
        return kosu12;
    }
    /**
     * ŒÂ”12‚ğİ’è‚µ‚Ü‚·B
     * @param kosu12 ŒÂ”12
     */
    public void setKosu12(BigDecimal kosu12) {
        this.kosu12 = kosu12;
    }
    
    /**
     * ‹àŠz13‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz13
     */
    public BigDecimal getKin13() {
        return kin13;
    }
    /**
     * ‹àŠz13‚ğİ’è‚µ‚Ü‚·B
     * @param kin13 ‹àŠz13
     */
    public void setKin13(BigDecimal kin13) {
        this.kin13 = kin13;
    }
    
    /**
     * ŒÂ”13‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”13
     */
    public BigDecimal getKosu13() {
        return kosu13;
    }
    /**
     * ŒÂ”13‚ğİ’è‚µ‚Ü‚·B
     * @param kosu13 ŒÂ”13
     */
    public void setKosu13(BigDecimal kosu13) {
        this.kosu13 = kosu13;
    }
    
    /**
     * ‹àŠz14‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz14
     */
    public BigDecimal getKin14() {
        return kin14;
    }
    /**
     * ‹àŠz14‚ğİ’è‚µ‚Ü‚·B
     * @param kin14 ‹àŠz14
     */
    public void setKin14(BigDecimal kin14) {
        this.kin14 = kin14;
    }
    
    /**
     * ŒÂ”14‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”14
     */
    public BigDecimal getKosu14() {
        return kosu14;
    }
    /**
     * ŒÂ”14‚ğİ’è‚µ‚Ü‚·B
     * @param kosu14 ŒÂ”14
     */
    public void setKosu14(BigDecimal kosu14) {
        this.kosu14 = kosu14;
    }
    
    /**
     * ‹àŠz15‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz15
     */
    public BigDecimal getKin15() {
        return kin15;
    }
    /**
     * ‹àŠz15‚ğİ’è‚µ‚Ü‚·B
     * @param kin15 ‹àŠz15
     */
    public void setKin15(BigDecimal kin15) {
        this.kin15 = kin15;
    }
    
    /**
     * ŒÂ”15‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”15
     */
    public BigDecimal getKosu15() {
        return kosu15;
    }
    /**
     * ŒÂ”15‚ğİ’è‚µ‚Ü‚·B
     * @param kosu15 ŒÂ”15
     */
    public void setKosu15(BigDecimal kosu15) {
        this.kosu15 = kosu15;
    }
    
    /**
     * ‹àŠz16‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz16
     */
    public BigDecimal getKin16() {
        return kin16;
    }
    /**
     * ‹àŠz16‚ğİ’è‚µ‚Ü‚·B
     * @param kin16 ‹àŠz16
     */
    public void setKin16(BigDecimal kin16) {
        this.kin16 = kin16;
    }
    
    /**
     * ŒÂ”16‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”16
     */
    public BigDecimal getKosu16() {
        return kosu16;
    }
    /**
     * ŒÂ”16‚ğİ’è‚µ‚Ü‚·B
     * @param kosu16 ŒÂ”16
     */
    public void setKosu16(BigDecimal kosu16) {
        this.kosu16 = kosu16;
    }
    
    /**
     * ‹àŠz17‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz17
     */
    public BigDecimal getKin17() {
        return kin17;
    }
    /**
     * ‹àŠz17‚ğİ’è‚µ‚Ü‚·B
     * @param kin17 ‹àŠz17
     */
    public void setKin17(BigDecimal kin17) {
        this.kin17 = kin17;
    }
    
    /**
     * ŒÂ”17‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”17
     */
    public BigDecimal getKosu17() {
        return kosu17;
    }
    /**
     * ŒÂ”17‚ğİ’è‚µ‚Ü‚·B
     * @param kosu17 ŒÂ”17
     */
    public void setKosu17(BigDecimal kosu17) {
        this.kosu17 = kosu17;
    }
    
    /**
     * ‹àŠz18‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz18
     */
    public BigDecimal getKin18() {
        return kin18;
    }
    /**
     * ‹àŠz18‚ğİ’è‚µ‚Ü‚·B
     * @param kin18 ‹àŠz18
     */
    public void setKin18(BigDecimal kin18) {
        this.kin18 = kin18;
    }
    
    /**
     * ŒÂ”18‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”18
     */
    public BigDecimal getKosu18() {
        return kosu18;
    }
    /**
     * ŒÂ”18‚ğİ’è‚µ‚Ü‚·B
     * @param kosu18 ŒÂ”18
     */
    public void setKosu18(BigDecimal kosu18) {
        this.kosu18 = kosu18;
    }
    
    /**
     * ‹àŠz19‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz19
     */
    public BigDecimal getKin19() {
        return kin19;
    }
    /**
     * ‹àŠz19‚ğİ’è‚µ‚Ü‚·B
     * @param kin19 ‹àŠz19
     */
    public void setKin19(BigDecimal kin19) {
        this.kin19 = kin19;
    }
    
    /**
     * ŒÂ”19‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”19
     */
    public BigDecimal getKosu19() {
        return kosu19;
    }
    /**
     * ŒÂ”19‚ğİ’è‚µ‚Ü‚·B
     * @param kosu19 ŒÂ”19
     */
    public void setKosu19(BigDecimal kosu19) {
        this.kosu19 = kosu19;
    }
    
    /**
     * ‹àŠz20‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz20
     */
    public BigDecimal getKin20() {
        return kin20;
    }
    /**
     * ‹àŠz20‚ğİ’è‚µ‚Ü‚·B
     * @param kin20 ‹àŠz20
     */
    public void setKin20(BigDecimal kin20) {
        this.kin20 = kin20;
    }
    
    /**
     * ŒÂ”20‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”20
     */
    public BigDecimal getKosu20() {
        return kosu20;
    }
    /**
     * ŒÂ”20‚ğİ’è‚µ‚Ü‚·B
     * @param kosu20 ŒÂ”20
     */
    public void setKosu20(BigDecimal kosu20) {
        this.kosu20 = kosu20;
    }
    
    /**
     * ‹àŠz21‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz21
     */
    public BigDecimal getKin21() {
        return kin21;
    }
    /**
     * ‹àŠz21‚ğİ’è‚µ‚Ü‚·B
     * @param kin21 ‹àŠz21
     */
    public void setKin21(BigDecimal kin21) {
        this.kin21 = kin21;
    }
    
    /**
     * ŒÂ”21‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”21
     */
    public BigDecimal getKosu21() {
        return kosu21;
    }
    /**
     * ŒÂ”21‚ğİ’è‚µ‚Ü‚·B
     * @param kosu21 ŒÂ”21
     */
    public void setKosu21(BigDecimal kosu21) {
        this.kosu21 = kosu21;
    }
    
    /**
     * ‹àŠz22‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz22
     */
    public BigDecimal getKin22() {
        return kin22;
    }
    /**
     * ‹àŠz22‚ğİ’è‚µ‚Ü‚·B
     * @param kin22 ‹àŠz22
     */
    public void setKin22(BigDecimal kin22) {
        this.kin22 = kin22;
    }
    
    /**
     * ŒÂ”22‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”22
     */
    public BigDecimal getKosu22() {
        return kosu22;
    }
    /**
     * ŒÂ”22‚ğİ’è‚µ‚Ü‚·B
     * @param kosu22 ŒÂ”22
     */
    public void setKosu22(BigDecimal kosu22) {
        this.kosu22 = kosu22;
    }
    
    /**
     * ‹àŠz23‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz23
     */
    public BigDecimal getKin23() {
        return kin23;
    }
    /**
     * ‹àŠz23‚ğİ’è‚µ‚Ü‚·B
     * @param kin23 ‹àŠz23
     */
    public void setKin23(BigDecimal kin23) {
        this.kin23 = kin23;
    }
    
    /**
     * ŒÂ”23‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”23
     */
    public BigDecimal getKosu23() {
        return kosu23;
    }
    /**
     * ŒÂ”23‚ğİ’è‚µ‚Ü‚·B
     * @param kosu23 ŒÂ”23
     */
    public void setKosu23(BigDecimal kosu23) {
        this.kosu23 = kosu23;
    }
    
    /**
     * ‹àŠz‚»‚Ì‘¼‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹àŠz‚»‚Ì‘¼
     */
    public BigDecimal getKinEtc() {
        return kinEtc;
    }
    /**
     * ‹àŠz‚»‚Ì‘¼‚ğİ’è‚µ‚Ü‚·B
     * @param kinEtc ‹àŠz‚»‚Ì‘¼
     */
    public void setKinEtc(BigDecimal kinEtc) {
        this.kinEtc = kinEtc;
    }
    
    /**
     * ŒÂ”‚»‚Ì‘¼‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ”‚»‚Ì‘¼
     */
    public BigDecimal getKosuEtc() {
        return kosuEtc;
    }
    /**
     * ŒÂ”‚»‚Ì‘¼‚ğİ’è‚µ‚Ü‚·B
     * @param kosuEtc ŒÂ”‚»‚Ì‘¼
     */
    public void setKosuEtc(BigDecimal kosuEtc) {
        this.kosuEtc = kosuEtc;
    }
	/**
	 * @return kingaku ‚ğ–ß‚µ‚Ü‚·B
	 */
	public BigDecimal getKingaku() {
		return kingaku;
	}
	/**
	 * @param kingaku ‚ğ ƒNƒ‰ƒX•Ï”kingaku‚Öİ’è‚µ‚Ü‚·B
	 */
	public void setKingaku(BigDecimal kingaku) {
		this.kingaku = kingaku;
	}
	/**
	 * @return kingakuKouseiHi ‚ğ–ß‚µ‚Ü‚·B
	 */
	public BigDecimal getKingakuKouseiHi() {
		return kingakuKouseiHi;
	}
	/**
	 * @param kingakuKouseiHi ‚ğ ƒNƒ‰ƒX•Ï”kingakuKouseiHi‚Öİ’è‚µ‚Ü‚·B
	 */
	public void setKingakuKouseiHi(BigDecimal kingakuKouseiHi) {
		this.kingakuKouseiHi = kingakuKouseiHi;
	}
	/**
	 * @return kosu ‚ğ–ß‚µ‚Ü‚·B
	 */
	public BigDecimal getKosu() {
		return kosu;
	}
	/**
	 * @param kosu ‚ğ ƒNƒ‰ƒX•Ï”kosu‚Öİ’è‚µ‚Ü‚·B
	 */
	public void setKosu(BigDecimal kosu) {
		this.kosu = kosu;
	}
	/**
	 * @return mbunruiNameKj ‚ğ–ß‚µ‚Ü‚·B
	 */
	public String getMbunruiNameKj() {
		return mbunruiNameKj;
	}
	/**
	 * @param mbunruiNameKj ‚ğ ƒNƒ‰ƒX•Ï”mbunruiNameKj‚Öİ’è‚µ‚Ü‚·B
	 */
	public void setMbunruiNameKj(String mbunruiNameKj) {
		this.mbunruiNameKj = mbunruiNameKj;
	}
	/**
	 * @return menuBunrui ‚ğ–ß‚µ‚Ü‚·B
	 */
	public String getMenuBunrui() {
		return menuBunrui;
	}
	/**
	 * @param menuBunrui ‚ğ ƒNƒ‰ƒX•Ï”menuBunrui‚Öİ’è‚µ‚Ü‚·B
	 */
	public void setMenuBunrui(String menuBunrui) {
		this.menuBunrui = menuBunrui;
	}
	/**
	 * @return menuCd ‚ğ–ß‚µ‚Ü‚·B
	 */
	public String getMenuCd() {
		return menuCd;
	}
	/**
	 * @param menuCd ‚ğ ƒNƒ‰ƒX•Ï”menuCd‚Öİ’è‚µ‚Ü‚·B
	 */
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	/**
	 * @return menuNameKj ‚ğ–ß‚µ‚Ü‚·B
	 */
	public String getMenuNameKj() {
		return menuNameKj;
	}
	/**
	 * @param menuNameKj ‚ğ ƒNƒ‰ƒX•Ï”menuNameKj‚Öİ’è‚µ‚Ü‚·B
	 */
	public void setMenuNameKj(String menuNameKj) {
		this.menuNameKj = menuNameKj;
	}
}
