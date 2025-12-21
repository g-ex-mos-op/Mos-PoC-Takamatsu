package jp.co.isid.mos.bird.bizreport.urimaintenance.action;

/**
 * ”„ãC³iŒyŒ¸Å—¦•ÒW‰æ–Êj
 */
public interface GetKeigenTaxEditAction {

    /**
     * ‰Šú•\¦
     * @return
     */
    public String initialize();


    /**
     * –ß‚é
     * @return
     */
    public String returnEdit();


    /**
     * Œˆ’è
     * @return
     */
    public String decide();


    /**
     * ÄŒvZ
     * @return
     */
    public String calculate();

    /**
     * ƒ^ƒuØ‘Ö
     * @return
     */
    public String changeTab();


    /**
     * C³
     * @return
     */
    public String getKeigenTax();
}
