package jp.co.isid.mos.bird.bill.itemtotal.entity;

import java.math.BigDecimal;

public class UIRirekiInfo {
    
    public static final String TABLE = "BS03USSR";
    
    public static final String urikakeYm_COLUMN = "URIKAKE_YM";
    
    public static final String shoAmount_COLUMN = "SHO_AMOUNT";
    
    public static final String nisuName_COLUMN = "NISU_NAME";
    
    public static final String nohinTanka_COLUMN = "NOHIN_TANKA";
    
    public static final String uriKin_COLUMN = "URI_KIN";
    
    /**
     * ”„Š|c‚”NŒ
     */
    private String urikakeYm;
    
    /**
     * ”[•i”—Ê
     */
    private BigDecimal shoAmount;
    
    /**
     * ‰×p–¼Ì
     */
    private String nisuName;
    
    /**
     * ”[•i’P‰¿
     */
    private BigDecimal nohinTanka;
    
    /**
     * ”„ã‹àŠz
     */
    private BigDecimal uriKin;
    
    /**
     * ”„Š|c‚”NŒ‚ğæ“¾‚µ‚Ü‚·B
     * @return ”„Š|c‚”NŒ
     */
    public String getUrikakeYm() {
        return urikakeYm;
    }
    /**
     * ”„Š|c‚”NŒ‚ğİ’è‚µ‚Ü‚·B
     * @param urikakeYm ”„Š|c‚”NŒ
     */
    public void setUrikakeYm(String urikakeYm) {
        this.urikakeYm = urikakeYm;
    }
    
    /**
     * ”[•i”—Ê‚ğæ“¾‚µ‚Ü‚·B
     * @return ”[•i”—Ê
     */
    public BigDecimal getShoAmount() {
        return shoAmount;
    }
    /**
     * ”[•i”—Ê‚ğİ’è‚µ‚Ü‚·B
     * @param shoAmount ”[•i”—Ê
     */
    public void setShoAmount(BigDecimal shoAmount) {
        this.shoAmount = shoAmount;
    }
    
    /**
     * ‰×p–¼Ì‚ğæ“¾‚µ‚Ü‚·B
     * @return ‰×p–¼Ì
     */
    public String getNisuName() {
        return nisuName;
    }
    /**
     * ‰×p–¼Ì‚ğİ’è‚µ‚Ü‚·B
     * @param nisuName ‰×p–¼Ì
     */
    public void setNisuName(String nisuName) {
        this.nisuName = nisuName;
    }
    
    /**
     * ”[•i’P‰¿‚ğæ“¾‚µ‚Ü‚·B
     * @return ”[•i’P‰¿
     */
    public BigDecimal getNohinTanka() {
        return nohinTanka;
    }
    /**
     * ”[•i’P‰¿‚ğİ’è‚µ‚Ü‚·B
     * @param nohinTanka ”[•i’P‰¿
     */
    public void setNohinTanka(BigDecimal nohinTanka) {
        this.nohinTanka = nohinTanka;
    }
    
    /**
     * ”„ã‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ”„ã‹àŠz
     */
    public BigDecimal getUriKin() {
        return uriKin;
    }
    /**
     * ”„ã‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param uriKin ”„ã‹àŠz
     */
    public void setUriKin(BigDecimal uriKin) {
        this.uriKin = uriKin;
    }
    
}
