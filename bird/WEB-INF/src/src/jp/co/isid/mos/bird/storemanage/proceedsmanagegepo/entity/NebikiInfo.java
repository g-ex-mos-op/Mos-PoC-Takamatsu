package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity;

import java.math.BigDecimal;


/**
 * ’lˆøî•ñƒGƒ“ƒeƒBƒeƒBƒNƒ‰ƒX
 * @author xsong
 *
 */
public class NebikiInfo {
	/** ƒe[ƒuƒ‹–¼Ì */  
    public static final String TABLE = "BT95NBKI";
    /** ƒJƒ‰ƒ€–¼ÌF‰c‹Æ“ú */
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøŒ”1 */
    public static final String nebikiKen_1_COLUMN = "NEBIKI_KEN_1";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆø‹àŠz1 */
    public static final String nebikiKin_1_COLUMN = "NEBIKI_KIN_1";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøÅŠz1 */
    public static final String nebikiTax_1_COLUMN = "NEBIKI_TAX_1";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøŒ”2 */
    public static final String nebikiKen_2_COLUMN = "NEBIKI_KEN_2";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆø‹àŠz2 */
    public static final String nebikiKin_2_COLUMN = "NEBIKI_KIN_2";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøÅŠz2 */
    public static final String nebikiTax_2_COLUMN = "NEBIKI_TAX_2";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøŒ”3 */
    public static final String nebikiKen_3_COLUMN = "NEBIKI_KEN_3";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆø‹àŠz3 */
    public static final String nebikiKin_3_COLUMN = "NEBIKI_KIN_3";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøÅŠz3 */
    public static final String nebikiTax_3_COLUMN = "NEBIKI_TAX_3";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøŒ”4 */
    public static final String nebikiKen_4_COLUMN = "NEBIKI_KEN_4";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆø‹àŠz4 */
    public static final String nebikiKin_4_COLUMN = "NEBIKI_KIN_4";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøÅŠz4 */
    public static final String nebikiTax_4_COLUMN = "NEBIKI_TAX_4";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøŒ”5 */
    public static final String nebikiKen_5_COLUMN = "NEBIKI_KEN_5";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆø‹àŠz5 */
    public static final String nebikiKin_5_COLUMN = "NEBIKI_KIN_5";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøÅŠz5 */
    public static final String nebikiTax_5_COLUMN = "NEBIKI_TAX_5";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøŒ”6 */
    public static final String nebikiKen_6_COLUMN = "NEBIKI_KEN_6";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆø‹àŠz6 */
    public static final String nebikiKin_6_COLUMN = "NEBIKI_KIN_6";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøÅŠz6 */
    public static final String nebikiTax_6_COLUMN = "NEBIKI_TAX_6";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøŒ”7 */
    public static final String nebikiKen_7_COLUMN = "NEBIKI_KEN_7";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆø‹àŠz7 */
    public static final String nebikiKin_7_COLUMN = "NEBIKI_KIN_7";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøÅŠz7 */
    public static final String nebikiTax_7_COLUMN = "NEBIKI_TAX_7";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøŒ”8 */
    public static final String nebikiKen_8_COLUMN = "NEBIKI_KEN_8";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆø‹àŠz8 */
    public static final String nebikiKin_8_COLUMN = "NEBIKI_KIN_8";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøÅŠz8 */
    public static final String nebikiTax_8_COLUMN = "NEBIKI_TAX_8";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøŒ”9 */
    public static final String nebikiKen_9_COLUMN = "NEBIKI_KEN_9";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆø‹àŠz9 */
    public static final String nebikiKin_9_COLUMN = "NEBIKI_KIN_9";
    /** ƒJƒ‰ƒ€–¼ÌF’lˆøÅŠz9 */
    public static final String nebikiTax_9_COLUMN = "NEBIKI_TAX_9";
    
    
    /**
     * ‰c‹Æ“ú
     */
    private String eigyoDt;
    
    /**
     * ’lˆø‚PŒ”
     */
    private BigDecimal nebikiKen_1;
    
    /**
     * ’lˆø‚P‹àŠz
     */
    private BigDecimal nebikiKin_1;
    
    /**
     * ’lˆø‚PÅŠz
     */
    private BigDecimal nebikiTax_1;
    
    /**
     * ’lˆø‚QŒ”
     */
    private BigDecimal nebikiKen_2;
    
    /**
     * ’lˆø‚Q‹àŠz
     */
    private BigDecimal nebikiKin_2;
    
    /**
     * ’lˆø‚QÅŠz
     */
    private BigDecimal nebikiTax_2;
    
    /**
     * ’lˆø‚RŒ”
     */
    private BigDecimal nebikiKen_3;
    
    /**
     * ’lˆø‚R‹àŠz
     */
    private BigDecimal nebikiKin_3;
    
    /**
     * ’lˆø‚RÅŠz
     */
    private BigDecimal nebikiTax_3;
    
    /**
     * ’lˆø‚SŒ”
     */
    private BigDecimal nebikiKen_4;
    
    /**
     * ’lˆø‚S‹àŠz
     */
    private BigDecimal nebikiKin_4;
    
    /**
     * ’lˆø‚SÅŠz
     */
    private BigDecimal nebikiTax_4;
    
    /**
     * ’lˆø‚TŒ”
     */
    private BigDecimal nebikiKen_5;
    
    /**
     * ’lˆø‚T‹àŠz
     */
    private BigDecimal nebikiKin_5;
    
    /**
     * ’lˆø‚TÅŠz
     */
    private BigDecimal nebikiTax_5;
    
    /**
     * ’lˆø‚UŒ”
     */
    private BigDecimal nebikiKen_6;
    
    /**
     * ’lˆø‚U‹àŠz
     */
    private BigDecimal nebikiKin_6;
    
    /**
     * ’lˆø‚UÅŠz
     */
    private BigDecimal nebikiTax_6;
    
    /**
     * ’lˆø‚VŒ”
     */
    private BigDecimal nebikiKen_7;
    
    /**
     * ’lˆø‚V‹àŠz
     */
    private BigDecimal nebikiKin_7;
    
    /**
     * ’lˆø‚VÅŠz
     */
    private BigDecimal nebikiTax_7;
    
    /**
     * ’lˆø‚WŒ”
     */
    private BigDecimal nebikiKen_8;
    
    /**
     * ’lˆø‚W‹àŠz
     */
    private BigDecimal nebikiKin_8;
    
    /**
     * ’lˆø‚WÅŠz
     */
    private BigDecimal nebikiTax_8;
    
    /**
     * ’lˆø‚XŒ”
     */
    private BigDecimal nebikiKen_9;
    
    /**
     * ’lˆø‚X‹àŠz
     */
    private BigDecimal nebikiKin_9;
    
    /**
     * ’lˆø‚XÅŠz
     */
    private BigDecimal nebikiTax_9;
    
    /**
     * sCSSƒNƒ‰ƒX–¼
     */
    private String rClass;
    
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
     * ’lˆø‚PŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚PŒ”
     */
    public BigDecimal getNebikiKen_1() {
        return nebikiKen_1;
    }
    /**
     * ’lˆø‚PŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKen_1 ’lˆø‚PŒ”
     */
    public void setNebikiKen_1(BigDecimal nebikiKen_1) {
        this.nebikiKen_1 = nebikiKen_1;
    }
    
    /**
     * ’lˆø‚P‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚P‹àŠz
     */
    public BigDecimal getNebikiKin_1() {
        return nebikiKin_1;
    }
    /**
     * ’lˆø‚P‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKin_1 ’lˆø‚P‹àŠz
     */
    public void setNebikiKin_1(BigDecimal nebikiKin_1) {
        this.nebikiKin_1 = nebikiKin_1;
    }
    
    /**
     * ’lˆø‚PÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚PÅŠz
     */
    public BigDecimal getNebikiTax_1() {
        return nebikiTax_1;
    }
    /**
     * ’lˆø‚PÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiTax_1 ’lˆø‚PÅŠz
     */
    public void setNebikiTax_1(BigDecimal nebikiTax_1) {
        this.nebikiTax_1 = nebikiTax_1;
    }
    
    /**
     * ’lˆø‚QŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚QŒ”
     */
    public BigDecimal getNebikiKen_2() {
        return nebikiKen_2;
    }
    /**
     * ’lˆø‚QŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKen_2 ’lˆø‚QŒ”
     */
    public void setNebikiKen_2(BigDecimal nebikiKen_2) {
        this.nebikiKen_2 = nebikiKen_2;
    }
    
    /**
     * ’lˆø‚Q‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚Q‹àŠz
     */
    public BigDecimal getNebikiKin_2() {
        return nebikiKin_2;
    }
    /**
     * ’lˆø‚Q‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKin_2 ’lˆø‚Q‹àŠz
     */
    public void setNebikiKin_2(BigDecimal nebikiKin_2) {
        this.nebikiKin_2 = nebikiKin_2;
    }
    
    /**
     * ’lˆø‚QÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚QÅŠz
     */
    public BigDecimal getNebikiTax_2() {
        return nebikiTax_2;
    }
    /**
     * ’lˆø‚QÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiTax_2 ’lˆø‚QÅŠz
     */
    public void setNebikiTax_2(BigDecimal nebikiTax_2) {
        this.nebikiTax_2 = nebikiTax_2;
    }
    
    /**
     * ’lˆø‚RŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚RŒ”
     */
    public BigDecimal getNebikiKen_3() {
        return nebikiKen_3;
    }
    /**
     * ’lˆø‚RŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKen_3 ’lˆø‚RŒ”
     */
    public void setNebikiKen_3(BigDecimal nebikiKen_3) {
        this.nebikiKen_3 = nebikiKen_3;
    }
    
    /**
     * ’lˆø‚R‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚R‹àŠz
     */
    public BigDecimal getNebikiKin_3() {
        return nebikiKin_3;
    }
    /**
     * ’lˆø‚R‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKin_3 ’lˆø‚R‹àŠz
     */
    public void setNebikiKin_3(BigDecimal nebikiKin_3) {
        this.nebikiKin_3 = nebikiKin_3;
    }
    
    /**
     * ’lˆø‚RÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚RÅŠz
     */
    public BigDecimal getNebikiTax_3() {
        return nebikiTax_3;
    }
    /**
     * ’lˆø‚RÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiTax_3 ’lˆø‚RÅŠz
     */
    public void setNebikiTax_3(BigDecimal nebikiTax_3) {
        this.nebikiTax_3 = nebikiTax_3;
    }
    
    /**
     * ’lˆø‚SŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚SŒ”
     */
    public BigDecimal getNebikiKen_4() {
        return nebikiKen_4;
    }
    /**
     * ’lˆø‚SŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKen_4 ’lˆø‚SŒ”
     */
    public void setNebikiKen_4(BigDecimal nebikiKen_4) {
        this.nebikiKen_4 = nebikiKen_4;
    }
    
    /**
     * ’lˆø‚S‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚S‹àŠz
     */
    public BigDecimal getNebikiKin_4() {
        return nebikiKin_4;
    }
    /**
     * ’lˆø‚S‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKin_4 ’lˆø‚S‹àŠz
     */
    public void setNebikiKin_4(BigDecimal nebikiKin_4) {
        this.nebikiKin_4 = nebikiKin_4;
    }
    
    /**
     * ’lˆø‚SÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚SÅŠz
     */
    public BigDecimal getNebikiTax_4() {
        return nebikiTax_4;
    }
    /**
     * ’lˆø‚SÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiTax_4 ’lˆø‚SÅŠz
     */
    public void setNebikiTax_4(BigDecimal nebikiTax_4) {
        this.nebikiTax_4 = nebikiTax_4;
    }
    
    /**
     * ’lˆø‚TŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚TŒ”
     */
    public BigDecimal getNebikiKen_5() {
        return nebikiKen_5;
    }
    /**
     * ’lˆø‚TŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKen_5 ’lˆø‚TŒ”
     */
    public void setNebikiKen_5(BigDecimal nebikiKen_5) {
        this.nebikiKen_5 = nebikiKen_5;
    }
    
    /**
     * ’lˆø‚T‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚T‹àŠz
     */
    public BigDecimal getNebikiKin_5() {
        return nebikiKin_5;
    }
    /**
     * ’lˆø‚T‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKin_5 ’lˆø‚T‹àŠz
     */
    public void setNebikiKin_5(BigDecimal nebikiKin_5) {
        this.nebikiKin_5 = nebikiKin_5;
    }
    
    /**
     * ’lˆø‚TÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚TÅŠz
     */
    public BigDecimal getNebikiTax_5() {
        return nebikiTax_5;
    }
    /**
     * ’lˆø‚TÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiTax_5 ’lˆø‚TÅŠz
     */
    public void setNebikiTax_5(BigDecimal nebikiTax_5) {
        this.nebikiTax_5 = nebikiTax_5;
    }
    
    /**
     * ’lˆø‚UŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚UŒ”
     */
    public BigDecimal getNebikiKen_6() {
        return nebikiKen_6;
    }
    /**
     * ’lˆø‚UŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKen_6 ’lˆø‚UŒ”
     */
    public void setNebikiKen_6(BigDecimal nebikiKen_6) {
        this.nebikiKen_6 = nebikiKen_6;
    }
    
    /**
     * ’lˆø‚U‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚U‹àŠz
     */
    public BigDecimal getNebikiKin_6() {
        return nebikiKin_6;
    }
    /**
     * ’lˆø‚U‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKin_6 ’lˆø‚U‹àŠz
     */
    public void setNebikiKin_6(BigDecimal nebikiKin_6) {
        this.nebikiKin_6 = nebikiKin_6;
    }
    
    /**
     * ’lˆø‚UÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚UÅŠz
     */
    public BigDecimal getNebikiTax_6() {
        return nebikiTax_6;
    }
    /**
     * ’lˆø‚UÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiTax_6 ’lˆø‚UÅŠz
     */
    public void setNebikiTax_6(BigDecimal nebikiTax_6) {
        this.nebikiTax_6 = nebikiTax_6;
    }
    
    /**
     * ’lˆø‚VŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚VŒ”
     */
    public BigDecimal getNebikiKen_7() {
        return nebikiKen_7;
    }
    /**
     * ’lˆø‚VŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKen_7 ’lˆø‚VŒ”
     */
    public void setNebikiKen_7(BigDecimal nebikiKen_7) {
        this.nebikiKen_7 = nebikiKen_7;
    }
    
    /**
     * ’lˆø‚V‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚V‹àŠz
     */
    public BigDecimal getNebikiKin_7() {
        return nebikiKin_7;
    }
    /**
     * ’lˆø‚V‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKin_7 ’lˆø‚V‹àŠz
     */
    public void setNebikiKin_7(BigDecimal nebikiKin_7) {
        this.nebikiKin_7 = nebikiKin_7;
    }
    
    /**
     * ’lˆø‚VÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚VÅŠz
     */
    public BigDecimal getNebikiTax_7() {
        return nebikiTax_7;
    }
    /**
     * ’lˆø‚VÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiTax_7 ’lˆø‚VÅŠz
     */
    public void setNebikiTax_7(BigDecimal nebikiTax_7) {
        this.nebikiTax_7 = nebikiTax_7;
    }
    
    /**
     * ’lˆø‚WŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚WŒ”
     */
    public BigDecimal getNebikiKen_8() {
        return nebikiKen_8;
    }
    /**
     * ’lˆø‚WŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKen_8 ’lˆø‚WŒ”
     */
    public void setNebikiKen_8(BigDecimal nebikiKen_8) {
        this.nebikiKen_8 = nebikiKen_8;
    }
    
    /**
     * ’lˆø‚W‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚W‹àŠz
     */
    public BigDecimal getNebikiKin_8() {
        return nebikiKin_8;
    }
    /**
     * ’lˆø‚W‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKin_8 ’lˆø‚W‹àŠz
     */
    public void setNebikiKin_8(BigDecimal nebikiKin_8) {
        this.nebikiKin_8 = nebikiKin_8;
    }
    
    /**
     * ’lˆø‚WÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚WÅŠz
     */
    public BigDecimal getNebikiTax_8() {
        return nebikiTax_8;
    }
    /**
     * ’lˆø‚WÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiTax_8 ’lˆø‚WÅŠz
     */
    public void setNebikiTax_8(BigDecimal nebikiTax_8) {
        this.nebikiTax_8 = nebikiTax_8;
    }
    
    /**
     * ’lˆø‚XŒ”‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚XŒ”
     */
    public BigDecimal getNebikiKen_9() {
        return nebikiKen_9;
    }
    /**
     * ’lˆø‚XŒ”‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKen_9 ’lˆø‚XŒ”
     */
    public void setNebikiKen_9(BigDecimal nebikiKen_9) {
        this.nebikiKen_9 = nebikiKen_9;
    }
    
    /**
     * ’lˆø‚X‹àŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚X‹àŠz
     */
    public BigDecimal getNebikiKin_9() {
        return nebikiKin_9;
    }
    /**
     * ’lˆø‚X‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiKin_9 ’lˆø‚X‹àŠz
     */
    public void setNebikiKin_9(BigDecimal nebikiKin_9) {
        this.nebikiKin_9 = nebikiKin_9;
    }
    
    /**
     * ’lˆø‚XÅŠz‚ğæ“¾‚µ‚Ü‚·B
     * @return ’lˆø‚XÅŠz
     */
    public BigDecimal getNebikiTax_9() {
        return nebikiTax_9;
    }
    /**
     * ’lˆø‚XÅŠz‚ğİ’è‚µ‚Ü‚·B
     * @param nebikiTax_9 ’lˆø‚XÅŠz
     */
    public void setNebikiTax_9(BigDecimal nebikiTax_9) {
        this.nebikiTax_9 = nebikiTax_9;
    }
    
    /**
     * sCSSƒNƒ‰ƒX–¼‚ğæ“¾‚µ‚Ü‚·B
     * @return sCSSƒNƒ‰ƒX–¼
     */
    public String getRClass() {
        return rClass;
    }
    /**
     * sCSSƒNƒ‰ƒX–¼‚ğİ’è‚µ‚Ü‚·B
     * @param rClass sCSSƒNƒ‰ƒX–¼
     */
    public void setRClass(String rClass) {
        this.rClass = rClass;
    }    
}
