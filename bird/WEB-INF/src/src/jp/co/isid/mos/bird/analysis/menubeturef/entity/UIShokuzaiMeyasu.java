package jp.co.isid.mos.bird.analysis.menubeturef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIMenu;

/**
 * HŞ€”õ–ÚˆÀ•\ƒGƒ“ƒeƒBƒeƒB
 * 
 * ì¬“ú:2008/09/10
 * @author xkinu
 *
 */
public class UIShokuzaiMeyasu extends UIMenu {
    
    public static final String TABLE = "BD10NMSL";
   
    public static final String kosuEat_COLUMN = "KOSU_EAT";
    
    public static final String kingakuEat_COLUMN = "KINGAKU_EAT";
    
    public static final String kosuTake_COLUMN = "KOSU_TAKE";
    
    public static final String kingakuTake_COLUMN = "KINGAKU_TAKE";
    
    public static final String kosuTel_COLUMN = "KOSU_TEL";
    
    public static final String kingakuTel_COLUMN = "KINGAKU_TEL";
    
    public static final String kosuDrive_COLUMN = "KOSU_DRIVE";
    
    public static final String kingakuDrive_COLUMN = "KINGAKU_DRIVE";
    
    public static final String kosuEtc_COLUMN = "KOSU_ETC";
    
    public static final String kingakuEtc_COLUMN = "KINGAKU_ETC";
    
    /**
     * E/IŒÂ”
     */
    private BigDecimal kosuEat = new BigDecimal("0");
    
    /**
     * EAT-IN‹àŠz
     */
    private BigDecimal kingakuEat = new BigDecimal("0");
    
    /**
     * T/OŒÂ”
     */
    private BigDecimal kosuTake = new BigDecimal("0");
    
    /**
     * TAKE-OUT‹àŠz
     */
    private BigDecimal kingakuTake = new BigDecimal("0");
    
    /**
     * TEL-ORDERŒÂ”
     */
    private BigDecimal kosuTel = new BigDecimal("0");
    
    /**
     * TEL-ORDER‹àŠz
     */
    private BigDecimal kingakuTel = new BigDecimal("0");
    
    /**
     * D/TŒÂ”
     */
    private BigDecimal kosuDrive = new BigDecimal("0");
    
    /**
     * DRIVE-THROUGH‹àŠz
     */
    private BigDecimal kingakuDrive = new BigDecimal("0");
    
    /**
     * ‚»‚Ì‘¼Œ”
     */
    private BigDecimal kosuEtc = new BigDecimal("0");
    
    /**
     * ‚»‚Ì‘¼‹àŠz
     */
    private BigDecimal kingakuEtc = new BigDecimal("0");
    
    /**
     * E/IŒÂ”‚ğæ“¾‚µ‚Ü‚·B
     * @return E/IŒÂ”
     */
    public BigDecimal getKosuEat() {
        return kosuEat;
    }
    /**
     * E/IŒÂ”‚ğİ’è‚µ‚Ü‚·B
     * @param kosuEat E/IŒÂ”
     */
    public void setKosuEat(BigDecimal kosuEat) {
        this.kosuEat = kosuEat;
    }
    
    /**
     * EAT-IN‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return EAT-IN‹àŠz
     */
    public BigDecimal getKingakuEat() {
        return kingakuEat;
    }
    /**
     * EAT-IN‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuEat EAT-IN‹àŠz
     */
    public void setKingakuEat(BigDecimal kingakuEat) {
        this.kingakuEat = kingakuEat;
    }
    
    /**
     * T/OŒÂ”‚ğæ“¾‚µ‚Ü‚·B
     * @return T/OŒÂ”
     */
    public BigDecimal getKosuTake() {
        return kosuTake;
    }
    /**
     * T/OŒÂ”‚ğİ’è‚µ‚Ü‚·B
     * @param kosuTake T/OŒÂ”
     */
    public void setKosuTake(BigDecimal kosuTake) {
        this.kosuTake = kosuTake;
    }
    
    /**
     * TAKE-OUT‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return TAKE-OUT‹àŠz
     */
    public BigDecimal getKingakuTake() {
        return kingakuTake;
    }
    /**
     * TAKE-OUT‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuTake TAKE-OUT‹àŠz
     */
    public void setKingakuTake(BigDecimal kingakuTake) {
        this.kingakuTake = kingakuTake;
    }
    
    /**
     * TEL-ORDERŒÂ”‚ğæ“¾‚µ‚Ü‚·B
     * @return TEL-ORDERŒÂ”
     */
    public BigDecimal getKosuTel() {
        return kosuTel;
    }
    /**
     * TEL-ORDERŒÂ”‚ğİ’è‚µ‚Ü‚·B
     * @param kosuTel TEL-ORDERŒÂ”
     */
    public void setKosuTel(BigDecimal kosuTel) {
        this.kosuTel = kosuTel;
    }
    
    /**
     * TEL-ORDER‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return TEL-ORDER‹àŠz
     */
    public BigDecimal getKingakuTel() {
        return kingakuTel;
    }
    /**
     * TEL-ORDER‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuTel TEL-ORDER‹àŠz
     */
    public void setKingakuTel(BigDecimal kingakuTel) {
        this.kingakuTel = kingakuTel;
    }
    
    /**
     * D/TŒÂ”‚ğæ“¾‚µ‚Ü‚·B
     * @return D/TŒÂ”
     */
    public BigDecimal getKosuDrive() {
        return kosuDrive;
    }
    /**
     * D/TŒÂ”‚ğİ’è‚µ‚Ü‚·B
     * @param kosuDrive D/TŒÂ”
     */
    public void setKosuDrive(BigDecimal kosuDrive) {
        this.kosuDrive = kosuDrive;
    }
    
    /**
     * DRIVE-THROUGH‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return DRIVE-THROUGH‹àŠz
     */
    public BigDecimal getKingakuDrive() {
        return kingakuDrive;
    }
    /**
     * DRIVE-THROUGH‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuDrive DRIVE-THROUGH‹àŠz
     */
    public void setKingakuDrive(BigDecimal kingakuDrive) {
        this.kingakuDrive = kingakuDrive;
    }
    
    /**
     * ‚»‚Ì‘¼Œ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚»‚Ì‘¼Œ”
     */
    public BigDecimal getKosuEtc() {
        return kosuEtc;
    }
    /**
     * ‚»‚Ì‘¼Œ”‚ğİ’è‚µ‚Ü‚·B
     * @param kosuEtc ‚»‚Ì‘¼Œ”
     */
    public void setKosuEtc(BigDecimal kosuEtc) {
        this.kosuEtc = kosuEtc;
    }
    
    /**
     * ‚»‚Ì‘¼‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚»‚Ì‘¼‹àŠz
     */
    public BigDecimal getKingakuEtc() {
        return kingakuEtc;
    }
    /**
     * ‚»‚Ì‘¼‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuEtc ‚»‚Ì‘¼‹àŠz
     */
    public void setKingakuEtc(BigDecimal kingakuEtc) {
        this.kingakuEtc = kingakuEtc;
    }
    
}
