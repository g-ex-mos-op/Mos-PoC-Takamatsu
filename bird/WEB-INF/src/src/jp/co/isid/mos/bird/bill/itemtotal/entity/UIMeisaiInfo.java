package jp.co.isid.mos.bird.bill.itemtotal.entity;

import java.math.BigDecimal;

public class UIMeisaiInfo {
    
    public static final String TABLE = "BT37URTR";
    
    public static final String nohinDtJ_COLUMN = "NOHIN_DT_J";
    
    public static final String denpyoNo_COLUMN = "DENPYO_NO";
    
    public static final String shoAmount_COLUMN = "SHO_AMOUNT";
    
    public static final String nisuName_COLUMN = "NISU_NAME";
    
    public static final String nohinTanka_COLUMN = "NOHIN_TANKA";
    
    public static final String uriKin_COLUMN = "URI_KIN";
    
    public static final String tekiyou_COLUMN = "TEKIYOU";
    
    public static final String uchiTax_COLUMN = "UCHI_TAX";
    
    public static final String sotoTax_COLUMN = "SOTO_TAX";
    
    /**
     * ”[•iÀÑ“ú
     */
    private String nohinDtJ;
    
    /**
     * “`•[NO
     */
    private String denpyoNo;
    
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
     * “E—v
     */
    private String tekiyou;
    
    /**
     * Zo“àÅÁ”ïÅ
     */
    private BigDecimal uchiTax;
    
    /**
     * ŠOÅÁ”ïÅ
     */
    private BigDecimal sotoTax;
    
    /**
     * ”[•iÀÑ“ú‚ğæ“¾‚µ‚Ü‚·B
     * @return ”[•iÀÑ“ú
     */
    public String getNohinDtJ() {
        return nohinDtJ;
    }
    /**
     * ”[•iÀÑ“ú‚ğİ’è‚µ‚Ü‚·B
     * @param nohinDtJ ”[•iÀÑ“ú
     */
    public void setNohinDtJ(String nohinDtJ) {
        this.nohinDtJ = nohinDtJ;
    }
    
    /**
     * “`•[NO‚ğæ“¾‚µ‚Ü‚·B
     * @return “`•[NO
     */
    public String getDenpyoNo() {
        return denpyoNo;
    }
    /**
     * “`•[NO‚ğİ’è‚µ‚Ü‚·B
     * @param denpyoNo “`•[NO
     */
    public void setDenpyoNo(String denpyoNo) {
        this.denpyoNo = denpyoNo;
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
    
    /**
     * “E—v‚ğæ“¾‚µ‚Ü‚·B
     * @return “E—v
     */
    public String getTekiyou() {
        return tekiyou;
    }
    /**
     * “E—v‚ğİ’è‚µ‚Ü‚·B
     * @param tekiyou “E—v
     */
    public void setTekiyou(String tekiyou) {
        this.tekiyou = tekiyou;
    }
    
    /**
     * Zo“àÅÁ”ïÅ‚ğæ“¾‚µ‚Ü‚·B
     * @return Zo“àÅÁ”ïÅ
     */
    public BigDecimal getUchiTax() {
        return uchiTax;
    }
    /**
     * Zo“àÅÁ”ïÅ‚ğİ’è‚µ‚Ü‚·B
     * @param uchiTax Zo“àÅÁ”ïÅ
     */
    public void setUchiTax(BigDecimal uchiTax) {
        this.uchiTax = uchiTax;
    }
    
    /**
     * ŠOÅÁ”ïÅ‚ğæ“¾‚µ‚Ü‚·B
     * @return ŠOÅÁ”ïÅ
     */
    public BigDecimal getSotoTax() {
        return sotoTax;
    }
    /**
     * ŠOÅÁ”ïÅ‚ğİ’è‚µ‚Ü‚·B
     * @param sotoTax ŠOÅÁ”ïÅ
     */
    public void setSotoTax(BigDecimal sotoTax) {
        this.sotoTax = sotoTax;
    }
    
}
