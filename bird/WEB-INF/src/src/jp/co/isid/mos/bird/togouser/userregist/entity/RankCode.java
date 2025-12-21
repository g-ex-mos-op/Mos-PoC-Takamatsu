package jp.co.isid.mos.bird.togouser.userregist.entity;

public class RankCode {
    
    public static final String TABLE = "IM02SOSI";
    
    public static final String soshikiRank_COLUMN = "SOSHIKI_RANK";
    
    public static final String soshikiCd_COLUMN = "SOSHIKI_CD";
    
    /**
     * ランク
     */
    private String soshikiRank;
    
    /**
     * コード
     */
    private String soshikiCd;

    public String getSoshikiRank() {
        return soshikiRank;
    }

    public void setSoshikiRank(String soshikiRank) {
        this.soshikiRank = soshikiRank;
    }

    public String getSoshikiCd() {
        return soshikiCd;
    }

    public void setSoshikiCd(String soshikiCd) {
        this.soshikiCd = soshikiCd;
    }

        
}
