package jp.co.isid.mos.bird.bizreport.urimaintenance.entity;

public class MstMatchStateInfo {
    
    public static final String TABLE = "BR48BSTA";
    
    
    public static final String statKbn_COLUMN = "STAT_KBN";
    

    
    /**
     * ステータス
     */
    private String statKbn;



    public String getStatKbn() {
        return statKbn;
    }
    public void setStatKbn(String statKbn) {
        this.statKbn = statKbn;
    }
    
}
