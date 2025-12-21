package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity;

public class TotalMiseGepoCnt {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String totalTempoCnt_COLUMN = "TOTAL_TEMPO_CNT";
    
    public static final String tenpoCnt_COLUMN = "TENPO_CNT";

    /**
     * (13ƒ–Œ‚Ì)‘“X•Ü”
     */
    private int totalTempoCnt;
    
    /**
     * (13ƒ–Œ‚Ì)‘“X•Ü”‚ğæ“¾‚µ‚Ü‚·B
     * @return (13ƒ–Œ‚Ì)‘“X•Ü”
     */
    public int getTotalTempoCnt() {
        return totalTempoCnt;
    }
    /**
     * (13ƒ–Œ‚Ì)‘“X•Ü”‚ğİ’è‚µ‚Ü‚·B
     * @param totalTempoCnt (13ƒ–Œ‚Ì)‘“X•Ü”
     */
    public void setTotalTempoCnt(int totalTempoCnt) {
        this.totalTempoCnt = totalTempoCnt;
    }

    
    /**
     * (1ƒ–Œ‚Ì)“X•Ü”
     */
    private int tenpoCnt;

    /**
     * (1ƒ–Œ‚Ì)“X•Ü”‚ğæ“¾‚µ‚Ü‚·B
     * @return TenpoCnt
     */
    public int getTenpoCnt() {
        return tenpoCnt;
    }
    /**
     * (1ƒ–Œ‚Ì)“X•Ü”‚ğİ’è‚µ‚Ü‚·B
     * @param TenpoCnt
     */
    public void setTenpoCnt(int num) {
        this.tenpoCnt = num;
    }

}
