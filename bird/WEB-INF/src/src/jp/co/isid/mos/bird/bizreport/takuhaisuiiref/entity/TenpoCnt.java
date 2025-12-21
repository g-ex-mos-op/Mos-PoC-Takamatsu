package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity;

public class TenpoCnt {
    
    public static final String TABLE = "BT63SNIP";
    
    public static final String eigyoYm_COLUMN  = "EIGYO_YM";
    
    public static final String tenpoCnt_COLUMN = "TENPO_CNT";

    /**
     * ‰c‹Æ”NŒ
     */
    private String eigyoYm;
    
    /**
     * ‰c‹Æ”NŒ‚ğæ“¾‚µ‚Ü‚·B
     * @return ‰c‹Æ”NŒ
     */
    public String getEigyoYm() {
        return eigyoYm;
    }
    /**
     * ‰c‹Æ”NŒ‚ğİ’è‚µ‚Ü‚·B
     * @param ‰c‹Æ”NŒ
     */
    public void setEigyoYm(String ym) {
        this.eigyoYm = ym;
    }


    /**
     * “X•Ü”
     */
    private String tenpoCnt;

    /**
     * “X•Ü”‚ğæ“¾‚µ‚Ü‚·B
     * @return “X•Ü”
     */
    public String getTenpoCnt() {
        return tenpoCnt;
    }
    /**
     * “X•Ü”‚ğİ’è‚µ‚Ü‚·B
     * @param “X•Ü”
     */
    public void setTenpoCnt(String ym) {
        this.tenpoCnt = ym;
    }

}
