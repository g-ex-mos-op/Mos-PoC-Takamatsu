package jp.co.isid.mos.bird.bizsupport.common.action;

/**
 * P/L“o˜^ó‹µŠm”F
 * 
 * @author Aspac
 */
public interface PlRegistStateConfirmAction {

    /**
     * ‰Šúˆ—
     * 
     * @return 
     */
    public String initialize();

    /**
     * “o˜^ó‹µŠm”Fî•ñ‚ğæ“¾‚·‚é
     * 
     * @return 
     */
    public void executeStateConfirm();

    /**
     * –ß‚é
     * 
     * @return 
     */
    public String doBack();
}
