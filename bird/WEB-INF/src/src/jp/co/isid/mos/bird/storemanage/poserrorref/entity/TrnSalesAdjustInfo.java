package jp.co.isid.mos.bird.storemanage.poserrorref.entity;

import java.util.Arrays;
import java.util.List;

public class TrnSalesAdjustInfo {
    
    public static final String TABLE = "BT79SKNR";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String p4OpenDt_COLUMN = "P4_OPEN_DT";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String seisanYm_COLUMN = "SEISAN_YM";
    
    public static final String seisanKbn1_COLUMN = "SEISAN_KBN1";
    
    public static final String seisanKbn2_COLUMN = "SEISAN_KBN2";
    
    public static final String seisanKbn3_COLUMN = "SEISAN_KBN3";
    
    public static final String seisanKbn4_COLUMN = "SEISAN_KBN4";
    
    public static final String seisanKbn5_COLUMN = "SEISAN_KBN5";
    
    public static final String seisanKbn6_COLUMN = "SEISAN_KBN6";
    
    public static final String seisanKbn7_COLUMN = "SEISAN_KBN7";
    
    public static final String seisanKbn8_COLUMN = "SEISAN_KBN8";
    
    public static final String seisanKbn9_COLUMN = "SEISAN_KBN9";
    
    public static final String seisanKbn10_COLUMN = "SEISAN_KBN10";
    
    public static final String seisanKbn11_COLUMN = "SEISAN_KBN11";
    
    public static final String seisanKbn12_COLUMN = "SEISAN_KBN12";
    
    public static final String seisanKbn13_COLUMN = "SEISAN_KBN13";
    
    public static final String seisanKbn14_COLUMN = "SEISAN_KBN14";
    
    public static final String seisanKbn15_COLUMN = "SEISAN_KBN15";
    
    public static final String seisanKbn16_COLUMN = "SEISAN_KBN16";
    
    public static final String seisanKbn17_COLUMN = "SEISAN_KBN17";
    
    public static final String seisanKbn18_COLUMN = "SEISAN_KBN18";
    
    public static final String seisanKbn19_COLUMN = "SEISAN_KBN19";
    
    public static final String seisanKbn20_COLUMN = "SEISAN_KBN20";
    
    public static final String seisanKbn21_COLUMN = "SEISAN_KBN21";
    
    public static final String seisanKbn22_COLUMN = "SEISAN_KBN22";
    
    public static final String seisanKbn23_COLUMN = "SEISAN_KBN23";
    
    public static final String seisanKbn24_COLUMN = "SEISAN_KBN24";
    
    public static final String seisanKbn25_COLUMN = "SEISAN_KBN25";
    
    public static final String seisanKbn26_COLUMN = "SEISAN_KBN26";
    
    public static final String seisanKbn27_COLUMN = "SEISAN_KBN27";
    
    public static final String seisanKbn28_COLUMN = "SEISAN_KBN28";
    
    public static final String seisanKbn29_COLUMN = "SEISAN_KBN29";
    
    public static final String seisanKbn30_COLUMN = "SEISAN_KBN30";
    
    public static final String seisanKbn31_COLUMN = "SEISAN_KBN31";
    
    /**
     * ‰ïĞƒR[ƒh
     */
    private String companyCd;
    
    /**
     * ‰ïĞ–¼
     */
    private String companyName;
    
    /**
     * “XƒR[ƒh
     */
    private String miseCd;
    
    /**
     * “X–¼Ì
     */
    private String miseNameKj;
    
    /**
     * ƒI[ƒvƒ““ú
     */
    private String openDt;
    
    /**
     * ƒNƒ[ƒY“ú
     */
    private String closeDt;
    
    /**
     * P4ƒlƒbƒgƒ[ƒNŠJn“ú•t
     */
    private String p4OpenDt;
    
    /**
     * ƒI[ƒi[ƒR[ƒh
     */
    private String onerCd;
    
    /**
     * ¸Z”NŒ
     */
    private String seisanYm;
    
    /**
     * ¸Z‹æ•ªƒŠƒXƒg
     */
    
    private String[] seisanKbnArray = new String[31];
    /**
     * ‰ïĞƒR[ƒh‚ğæ“¾‚µ‚Ü‚·B
     * @return ‰ïĞƒR[ƒh
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * ‰ïĞƒR[ƒh‚ğİ’è‚µ‚Ü‚·B
     * @param companyCd ‰ïĞƒR[ƒh
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * ‰ïĞ–¼‚ğæ“¾‚µ‚Ü‚·B
     * @return ‰ïĞ–¼
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * ‰ïĞ–¼‚ğİ’è‚µ‚Ü‚·B
     * @param companyName ‰ïĞ–¼
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
     * “X–¼Ì‚ğæ“¾‚µ‚Ü‚·B
     * @return “X–¼Ì
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * “X–¼Ì‚ğİ’è‚µ‚Ü‚·B
     * @param miseNameKj “X–¼Ì
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    public String getOpenDt() {
        return openDt;
    }
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    /**
     * ƒNƒ[ƒY“ú‚ğæ“¾‚µ‚Ü‚·B
     * @return ƒNƒ[ƒY“ú
     */
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * ƒNƒ[ƒY“ú‚ğİ’è‚µ‚Ü‚·B
     * @param closeDt ƒNƒ[ƒY“ú
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    public String getP4OpenDt() {
        return p4OpenDt;
    }
    public void setP4OpenDt(String openDt) {
        p4OpenDt = openDt;
    }
    
    /**
     * ƒI[ƒi[ƒR[ƒh‚ğæ“¾‚µ‚Ü‚·B
     * @return ƒI[ƒi[ƒR[ƒh
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * ƒI[ƒi[ƒR[ƒh‚ğİ’è‚µ‚Ü‚·B
     * @param onerCd ƒI[ƒi[ƒR[ƒh
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * ¸Z”NŒ‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z”NŒ
     */
    public String getSeisanYm() {
        return seisanYm;
    }
    /**
     * ¸Z”NŒ‚ğİ’è‚µ‚Ü‚·B
     * @param seisanYm ¸Z”NŒ
     */
    public void setSeisanYm(String seisanYm) {
        this.seisanYm = seisanYm;
    }
    
    /**
     * ¸Z‹æ•ª1‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª1
     */
    public String getSeisanKbn1() {
        return seisanKbnArray[0];
    }
    /**
     * ¸Z‹æ•ª1‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn1 ¸Z‹æ•ª1
     */
    public void setSeisanKbn1(String seisanKbn1) {
        seisanKbnArray[0] = seisanKbn1;
    }
    
    /**
     * ¸Z‹æ•ª2‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª2
     */
    public String getSeisanKbn2() {
        return seisanKbnArray[1];
    }
    /**
     * ¸Z‹æ•ª2‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn2 ¸Z‹æ•ª2
     */
    public void setSeisanKbn2(String seisanKbn2) {
        seisanKbnArray[1] = seisanKbn2;
    }
    
    /**
     * ¸Z‹æ•ª3‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª3
     */
    public String getSeisanKbn3() {
        return seisanKbnArray[2];
    }
    /**
     * ¸Z‹æ•ª3‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn3 ¸Z‹æ•ª3
     */
    public void setSeisanKbn3(String seisanKbn3) {
        seisanKbnArray[2] = seisanKbn3;
    }
    
    /**
     * ¸Z‹æ•ª4‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª4
     */
    public String getSeisanKbn4() {
        return seisanKbnArray[3];
    }
    /**
     * ¸Z‹æ•ª4‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn4 ¸Z‹æ•ª4
     */
    public void setSeisanKbn4(String seisanKbn4) {
        seisanKbnArray[3] = seisanKbn4;
    }
    
    /**
     * ¸Z‹æ•ª5‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª5
     */
    public String getSeisanKbn5() {
        return seisanKbnArray[4];
    }
    /**
     * ¸Z‹æ•ª5‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn5 ¸Z‹æ•ª5
     */
    public void setSeisanKbn5(String seisanKbn5) {
        this.seisanKbnArray[4] = seisanKbn5;
    }
    
    /**
     * ¸Z‹æ•ª6‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª6
     */
    public String getSeisanKbn6() {
        return seisanKbnArray[5];
    }
    /**
     * ¸Z‹æ•ª6‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn6 ¸Z‹æ•ª6
     */
    public void setSeisanKbn6(String seisanKbn6) {
        this.seisanKbnArray[5] = seisanKbn6;
    }
    
    /**
     * ¸Z‹æ•ª7‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª7
     */
    public String getSeisanKbn7() {
        return seisanKbnArray[6];
    }
    /**
     * ¸Z‹æ•ª7‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn7 ¸Z‹æ•ª7
     */
    public void setSeisanKbn7(String seisanKbn7) {
        this.seisanKbnArray[6] = seisanKbn7;
    }
    
    /**
     * ¸Z‹æ•ª8‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª8
     */
    public String getSeisanKbn8() {
        return seisanKbnArray[7];
    }
    /**
     * ¸Z‹æ•ª8‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn8 ¸Z‹æ•ª8
     */
    public void setSeisanKbn8(String seisanKbn8) {
        this.seisanKbnArray[7] = seisanKbn8;
    }
    
    /**
     * ¸Z‹æ•ª9‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª9
     */
    public String getSeisanKbn9() {
        return seisanKbnArray[8];
    }
    /**
     * ¸Z‹æ•ª9‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn9 ¸Z‹æ•ª9
     */
    public void setSeisanKbn9(String seisanKbn9) {
        this.seisanKbnArray[8] = seisanKbn9;
    }
    
    /**
     * ¸Z‹æ•ª10‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª10
     */
    public String getSeisanKbn10() {
        return seisanKbnArray[9];
    }
    /**
     * ¸Z‹æ•ª10‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn10 ¸Z‹æ•ª10
     */
    public void setSeisanKbn10(String seisanKbn10) {
        this.seisanKbnArray[9] = seisanKbn10;
    }
    
    /**
     * ¸Z‹æ•ª11‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª11
     */
    public String getSeisanKbn11() {
        return seisanKbnArray[10];
    }
    /**
     * ¸Z‹æ•ª11‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn11 ¸Z‹æ•ª11
     */
    public void setSeisanKbn11(String seisanKbn11) {
        this.seisanKbnArray[10] = seisanKbn11;
    }
    
    /**
     * ¸Z‹æ•ª12‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª12
     */
    public String getSeisanKbn12() {
        return seisanKbnArray[11];
    }
    /**
     * ¸Z‹æ•ª12‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn12 ¸Z‹æ•ª12
     */
    public void setSeisanKbn12(String seisanKbn12) {
        this.seisanKbnArray[11] = seisanKbn12;
    }
    
    /**
     * ¸Z‹æ•ª13‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª13
     */
    public String getSeisanKbn13() {
        return seisanKbnArray[12];
    }
    /**
     * ¸Z‹æ•ª13‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn13 ¸Z‹æ•ª13
     */
    public void setSeisanKbn13(String seisanKbn13) {
        this.seisanKbnArray[12] = seisanKbn13;
    }
    
    /**
     * ¸Z‹æ•ª14‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª14
     */
    public String getSeisanKbn14() {
        return seisanKbnArray[13];
    }
    /**
     * ¸Z‹æ•ª14‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn14 ¸Z‹æ•ª14
     */
    public void setSeisanKbn14(String seisanKbn14) {
        this.seisanKbnArray[13] = seisanKbn14;
    }
    
    /**
     * ¸Z‹æ•ª15‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª15
     */
    public String getSeisanKbn15() {
        return seisanKbnArray[14];
    }
    /**
     * ¸Z‹æ•ª15‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn15 ¸Z‹æ•ª15
     */
    public void setSeisanKbn15(String seisanKbn15) {
        this.seisanKbnArray[14] = seisanKbn15;
    }
    
    /**
     * ¸Z‹æ•ª16‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª16
     */
    public String getSeisanKbn16() {
        return seisanKbnArray[15];
    }
    /**
     * ¸Z‹æ•ª16‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn16 ¸Z‹æ•ª16
     */
    public void setSeisanKbn16(String seisanKbn16) {
        this.seisanKbnArray[15] = seisanKbn16;
    }
    
    /**
     * ¸Z‹æ•ª17‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª17
     */
    public String getSeisanKbn17() {
        return seisanKbnArray[16];
    }
    /**
     * ¸Z‹æ•ª17‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn17 ¸Z‹æ•ª17
     */
    public void setSeisanKbn17(String seisanKbn17) {
        this.seisanKbnArray[16] = seisanKbn17;
    }
    
    /**
     * ¸Z‹æ•ª18‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª18
     */
    public String getSeisanKbn18() {
        return seisanKbnArray[17];
    }
    /**
     * ¸Z‹æ•ª18‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn18 ¸Z‹æ•ª18
     */
    public void setSeisanKbn18(String seisanKbn18) {
        this.seisanKbnArray[17] = seisanKbn18;
    }
    
    /**
     * ¸Z‹æ•ª19‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª19
     */
    public String getSeisanKbn19() {
        return seisanKbnArray[18];
    }
    /**
     * ¸Z‹æ•ª19‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn19 ¸Z‹æ•ª19
     */
    public void setSeisanKbn19(String seisanKbn19) {
        this.seisanKbnArray[18] = seisanKbn19;
    }
    
    /**
     * ¸Z‹æ•ª20‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª20
     */
    public String getSeisanKbn20() {
        return seisanKbnArray[19];
    }
    /**
     * ¸Z‹æ•ª20‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn20 ¸Z‹æ•ª20
     */
    public void setSeisanKbn20(String seisanKbn20) {
        this.seisanKbnArray[19] = seisanKbn20;
    }
    
    /**
     * ¸Z‹æ•ª21‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª21
     */
    public String getSeisanKbn21() {
        return seisanKbnArray[20];
    }
    /**
     * ¸Z‹æ•ª21‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn21 ¸Z‹æ•ª21
     */
    public void setSeisanKbn21(String seisanKbn21) {
        this.seisanKbnArray[20] = seisanKbn21;
    }
    
    /**
     * ¸Z‹æ•ª22‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª22
     */
    public String getSeisanKbn22() {
        return seisanKbnArray[21];
    }
    /**
     * ¸Z‹æ•ª22‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn22 ¸Z‹æ•ª22
     */
    public void setSeisanKbn22(String seisanKbn22) {
        this.seisanKbnArray[21] = seisanKbn22;
    }
    
    /**
     * ¸Z‹æ•ª23‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª23
     */
    public String getSeisanKbn23() {
        return seisanKbnArray[22];
    }
    /**
     * ¸Z‹æ•ª23‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn23 ¸Z‹æ•ª23
     */
    public void setSeisanKbn23(String seisanKbn23) {
        this.seisanKbnArray[22] = seisanKbn23;
    }
    
    /**
     * ¸Z‹æ•ª24‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª24
     */
    public String getSeisanKbn24() {
        return seisanKbnArray[23];
    }
    /**
     * ¸Z‹æ•ª24‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn24 ¸Z‹æ•ª24
     */
    public void setSeisanKbn24(String seisanKbn24) {
        this.seisanKbnArray[23] = seisanKbn24;
    }
    
    /**
     * ¸Z‹æ•ª25‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª25
     */
    public String getSeisanKbn25() {
        return seisanKbnArray[24];
    }
    /**
     * ¸Z‹æ•ª25‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn25 ¸Z‹æ•ª25
     */
    public void setSeisanKbn25(String seisanKbn25) {
        this.seisanKbnArray[24] = seisanKbn25;
    }
    
    /**
     * ¸Z‹æ•ª26‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª26
     */
    public String getSeisanKbn26() {
        return seisanKbnArray[25];
    }
    /**
     * ¸Z‹æ•ª26‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn26 ¸Z‹æ•ª26
     */
    public void setSeisanKbn26(String seisanKbn26) {
        this.seisanKbnArray[25] = seisanKbn26;
    }
    
    /**
     * ¸Z‹æ•ª27‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª27
     */
    public String getSeisanKbn27() {
        return seisanKbnArray[26];
    }
    /**
     * ¸Z‹æ•ª27‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn27 ¸Z‹æ•ª27
     */
    public void setSeisanKbn27(String seisanKbn27) {
        this.seisanKbnArray[26] = seisanKbn27;
    }
    
    /**
     * ¸Z‹æ•ª28‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª28
     */
    public String getSeisanKbn28() {
        return seisanKbnArray[27];
    }
    /**
     * ¸Z‹æ•ª28‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn28 ¸Z‹æ•ª28
     */
    public void setSeisanKbn28(String seisanKbn28) {
        this.seisanKbnArray[27] = seisanKbn28;
    }
    
    /**
     * ¸Z‹æ•ª29‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª29
     */
    public String getSeisanKbn29() {
        return seisanKbnArray[28];
    }
    /**
     * ¸Z‹æ•ª29‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn29 ¸Z‹æ•ª29
     */
    public void setSeisanKbn29(String seisanKbn29) {
        this.seisanKbnArray[28] = seisanKbn29;
    }
    
    /**
     * ¸Z‹æ•ª30‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª30
     */
    public String getSeisanKbn30() {
        return seisanKbnArray[29];
    }
    /**
     * ¸Z‹æ•ª30‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn30 ¸Z‹æ•ª30
     */
    public void setSeisanKbn30(String seisanKbn30) {
        this.seisanKbnArray[29] = seisanKbn30;
    }
    
    /**
     * ¸Z‹æ•ª31‚ğæ“¾‚µ‚Ü‚·B
     * @return ¸Z‹æ•ª31
     */
    public String getSeisanKbn31() {
        return seisanKbnArray[30];
    }
    /**
     * ¸Z‹æ•ª31‚ğİ’è‚µ‚Ü‚·B
     * @param seisanKbn31 ¸Z‹æ•ª31
     */
    public void setSeisanKbn31(String seisanKbn31) {
        this.seisanKbnArray[30] = seisanKbn31;
    }
    
    public List getSeisanKbnList() {
        return Arrays.asList(seisanKbnArray);
    }
    public void setSeisanKbnList(List seisanKbnList) {
        seisanKbnArray = (String[]) seisanKbnList.toArray();
    }
}