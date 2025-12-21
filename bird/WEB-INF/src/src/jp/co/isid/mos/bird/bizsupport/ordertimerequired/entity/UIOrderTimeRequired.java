package jp.co.isid.mos.bird.bizsupport.ordertimerequired.entity;

import java.math.BigDecimal;

/**
 * ƒGƒ“ƒeƒBƒeƒB
 * @auth 
 */
public class UIOrderTimeRequired {
    
    public static final String TABLE = "BD26ODTM";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String tmKbn_COLUMN = "TM_KBN";
    
    public static final String teikyoTmKbn_COLUMN = "TEIKYO_TM_KBN";
    
    public static final String eatKyakusu_COLUMN = "EAT_KYAKUSU";
    
    public static final String eatUriSu_COLUMN = "EAT_URI_SU";
    
    public static final String takeKyakusu_COLUMN = "TAKE_KYAKUSU";
    
    public static final String takeUriSu_COLUMN = "TAKE_URI_SU";
    
    public static final String telKyakusu_COLUMN = "TEL_KYAKUSU";
    
    public static final String telUriSu_COLUMN = "TEL_URI_SU";
    
    public static final String driveKyakusu_COLUMN = "DRIVE_KYAKUSU";
    
    public static final String driveUriSu_COLUMN = "DRIVE_URI_SU";
    
    public static final String otherKyakusu_COLUMN = "OTHER_KYAKUSU";
    
    public static final String otherUriSu_COLUMN = "OTHER_URI_SU";
    
    /**
     * “X•ÜƒR[ƒh
     */
    private String miseCd;
    
    /**
     * “X•Ü–¼Ì
     */
    private String miseNameKj;
    
    /**
     * ‰c‹Æ“ú
     */
    private String eigyoDt;
    
    /**
     * ŠÔ‘Ñ‹æ•ª
     */
    private String tmKbn;
    
    /**
     * ’ñ‹ŸŠÔ‹æ•ª
     */
    private String teikyoTmKbn;
    
    /**
     * ”„ãí•Ê1‹q”
     */
    private BigDecimal eatKyakusu;
    
    /**
     * ”„ãí•Ê1”„ãŒÂ”
     */
    private BigDecimal eatUriSu;
    
    /**
     * ”„ãí•Ê2‹q”
     */
    private BigDecimal takeKyakusu;
    
    /**
     * ”„ãí•Ê2”„ãŒÂ”
     */
    private BigDecimal takeUriSu;
    
    /**
     * ”„ãí•Ê3‹q”
     */
    private BigDecimal telKyakusu;
    
    /**
     * ”„ãí•Ê3”„ãŒÂ”
     */
    private BigDecimal telUriSu;
    
    /**
     * ”„ãí•Ê4‹q”
     */
    private BigDecimal driveKyakusu;
    
    /**
     * ”„ãí•Ê4”„ãŒÂ”
     */
    private BigDecimal driveUriSu;
    
    /**
     * ”„ãí•Ê5‚©‚ç15‹q”
     */
    private BigDecimal otherKyakusu;
    
    /**
     * ”„ãí•Ê5`15”„ãŒÂ”
     */
    private BigDecimal otherUriSu;
    
    /**
     * “X•ÜƒR[ƒhæ“¾ˆ—
     * @return “X•ÜƒR[ƒh
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * “X•ÜƒR[ƒhİ’èˆ—
     * @param miseCd “X•ÜƒR[ƒh
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * “X•Ü–¼Ìæ“¾ˆ—
     * @return “X•Ü–¼Ì
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * “X•Ü–¼Ìİ’èˆ—
     * @param miseNameKj “X•Ü–¼Ì
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * ‰c‹Æ“úæ“¾ˆ—
     * @return ‰c‹Æ“ú
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * ‰c‹Æ“úİ’èˆ—
     * @param eigyoDt ‰c‹Æ“ú
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * ŠÔ‘Ñ‹æ•ªæ“¾ˆ—
     * @return ŠÔ‘Ñ‹æ•ª
     */
    public String getTmKbn() {
        return tmKbn;
    }
    /**
     * ŠÔ‘Ñ‹æ•ªİ’èˆ—
     * @param tmKbn ŠÔ‘Ñ‹æ•ª
     */
    public void setTmKbn(String tmKbn) {
        this.tmKbn = tmKbn;
    }
    
    /**
     * ’ñ‹ŸŠÔ‹æ•ªæ“¾ˆ—
     * @return ’ñ‹ŸŠÔ‹æ•ª
     */
    public String getTeikyoTmKbn() {
        return teikyoTmKbn;
    }
    /**
     * ’ñ‹ŸŠÔ‹æ•ªİ’èˆ—
     * @param teikyoTmKbn ’ñ‹ŸŠÔ‹æ•ª
     */
    public void setTeikyoTmKbn(String teikyoTmKbn) {
        this.teikyoTmKbn = teikyoTmKbn;
    }
    
    /**
     * ”„ãí•Ê1‹q”æ“¾ˆ—
     * @return ”„ãí•Ê1‹q”
     */
    public BigDecimal getEatKyakusu() {
        return eatKyakusu;
    }
    /**
     * ”„ãí•Ê1‹q”İ’èˆ—
     * @param eatKyakusu ”„ãí•Ê1‹q”
     */
    public void setEatKyakusu(BigDecimal eatKyakusu) {
        this.eatKyakusu = eatKyakusu;
    }
    
    /**
     * ”„ãí•Ê1”„ãŒÂ”æ“¾ˆ—
     * @return ”„ãí•Ê1”„ãŒÂ”
     */
    public BigDecimal getEatUriSu() {
        return eatUriSu;
    }
    /**
     * ”„ãí•Ê1”„ãŒÂ”İ’èˆ—
     * @param eatUriSu ”„ãí•Ê1”„ãŒÂ”
     */
    public void setEatUriSu(BigDecimal eatUriSu) {
        this.eatUriSu = eatUriSu;
    }
    
    /**
     * ”„ãí•Ê2‹q”æ“¾ˆ—
     * @return ”„ãí•Ê2‹q”
     */
    public BigDecimal getTakeKyakusu() {
        return takeKyakusu;
    }
    /**
     * ”„ãí•Ê2‹q”İ’èˆ—
     * @param takeKyakusu ”„ãí•Ê2‹q”
     */
    public void setTakeKyakusu(BigDecimal takeKyakusu) {
        this.takeKyakusu = takeKyakusu;
    }
    
    /**
     * ”„ãí•Ê2”„ãŒÂ”æ“¾ˆ—
     * @return ”„ãí•Ê2”„ãŒÂ”
     */
    public BigDecimal getTakeUriSu() {
        return takeUriSu;
    }
    /**
     * ”„ãí•Ê2”„ãŒÂ”İ’èˆ—
     * @param takeUriSu ”„ãí•Ê2”„ãŒÂ”
     */
    public void setTakeUriSu(BigDecimal takeUriSu) {
        this.takeUriSu = takeUriSu;
    }
    
    /**
     * ”„ãí•Ê3‹q”æ“¾ˆ—
     * @return ”„ãí•Ê3‹q”
     */
    public BigDecimal getTelKyakusu() {
        return telKyakusu;
    }
    /**
     * ”„ãí•Ê3‹q”İ’èˆ—
     * @param telKyakusu ”„ãí•Ê3‹q”
     */
    public void setTelKyakusu(BigDecimal telKyakusu) {
        this.telKyakusu = telKyakusu;
    }
    
    /**
     * ”„ãí•Ê3”„ãŒÂ”æ“¾ˆ—
     * @return ”„ãí•Ê3”„ãŒÂ”
     */
    public BigDecimal getTelUriSu() {
        return telUriSu;
    }
    /**
     * ”„ãí•Ê3”„ãŒÂ”İ’èˆ—
     * @param telUriSu ”„ãí•Ê3”„ãŒÂ”
     */
    public void setTelUriSu(BigDecimal telUriSu) {
        this.telUriSu = telUriSu;
    }
    
    /**
     * ”„ãí•Ê4‹q”æ“¾ˆ—
     * @return ”„ãí•Ê4‹q”
     */
    public BigDecimal getDriveKyakusu() {
        return driveKyakusu;
    }
    /**
     * ”„ãí•Ê4‹q”İ’èˆ—
     * @param driveKyakusu ”„ãí•Ê4‹q”
     */
    public void setDriveKyakusu(BigDecimal driveKyakusu) {
        this.driveKyakusu = driveKyakusu;
    }
    
    /**
     * ”„ãí•Ê4”„ãŒÂ”æ“¾ˆ—
     * @return ”„ãí•Ê4”„ãŒÂ”
     */
    public BigDecimal getDriveUriSu() {
        return driveUriSu;
    }
    /**
     * ”„ãí•Ê4”„ãŒÂ”İ’èˆ—
     * @param driveUriSu ”„ãí•Ê4”„ãŒÂ”
     */
    public void setDriveUriSu(BigDecimal driveUriSu) {
        this.driveUriSu = driveUriSu;
    }
    
    /**
     * ”„ãí•Ê5‚©‚ç15‹q”æ“¾ˆ—
     * @return ”„ãí•Ê5‚©‚ç15‹q”
     */
    public BigDecimal getOtherKyakusu() {
        return otherKyakusu;
    }
    /**
     * ”„ãí•Ê5‚©‚ç15‹q”İ’èˆ—
     * @param otherKyakusu ”„ãí•Ê5‚©‚ç15‹q”
     */
    public void setOtherKyakusu(BigDecimal otherKyakusu) {
        this.otherKyakusu = otherKyakusu;
    }
    
    /**
     * ”„ãí•Ê5`15”„ãŒÂ”æ“¾ˆ—
     * @return ”„ãí•Ê5`15”„ãŒÂ”
     */
    public BigDecimal getOtherUriSu() {
        return otherUriSu;
    }
    /**
     * ”„ãí•Ê5`15”„ãŒÂ”İ’èˆ—
     * @param otherUriSu ”„ãí•Ê5`15”„ãŒÂ”
     */
    public void setOtherUriSu(BigDecimal otherUriSu) {
        this.otherUriSu = otherUriSu;
    }
    
}
