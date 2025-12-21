package jp.co.isid.mos.bird.bizsupport.plregist.entity;

import java.math.BigDecimal;

public class TrnPosZenUriage {
    
    public static final String TABLE = "BT61ZNDM";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    /**
     * ”„ã‚
     */
    private BigDecimal uriage;
    
    /**
     * ”„ã‚‚ğæ“¾‚µ‚Ü‚·B
     * @return ”„ã‚
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * ”„ã‚‚ğİ’è‚µ‚Ü‚·B
     * @param uriage ”„ã‚
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
    
}
