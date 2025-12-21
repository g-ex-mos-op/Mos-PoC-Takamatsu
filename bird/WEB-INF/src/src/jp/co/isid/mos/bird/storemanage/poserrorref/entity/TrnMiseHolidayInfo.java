/*
 * ì¬“ú:2007/03/07
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.entity;

/**
 * “X•Ü‹x“ú—\’èî•ñ
 * @author xkonishi
 *
 */
public class TrnMiseHolidayInfo {
    
    public static final String TABLE = "TN01MHOL";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
        
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseHolDt_COLUMN = "MISE_HOL_DT";
    
    public static final String HolKbn_COLUMN = "HOL_KBN";
    
    /**
     * ‰ïĞƒR[ƒh
     */
    private String companyCd;
    
    /**
     * “XƒR[ƒh
     */
    private String miseCd;

    /**
     * “X‹x“ú—\’è“ú
     */
    private String miseHolDt;
    
    /**
     * ‹x“ú‹æ•ª
     */
    private String HolKbn;

    
    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getHolKbn() {
        return HolKbn;
    }

    public void setHolKbn(String holKbn) {
        HolKbn = holKbn;
    }

    public String getMiseCd() {
        return miseCd;
    }

    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    public String getMiseHolDt() {
        return miseHolDt;
    }

    public void setMiseHolDt(String miseHolDt) {
        this.miseHolDt = miseHolDt;
    }
}