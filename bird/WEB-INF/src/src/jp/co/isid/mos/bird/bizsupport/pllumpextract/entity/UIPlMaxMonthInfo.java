package jp.co.isid.mos.bird.bizsupport.pllumpextract.entity;

public class UIPlMaxMonthInfo {
    
    public static final String TABLE = "WT54PLRC";
    
    public static final String plYm_COLUMN = "PL_YM";
    
    /**
     * PL年月のMAX年月
     */
    private String plYm;

    
    /**
     * PL年月のMAX年月を取得します。
     * @return PL年月のMAX年月
     */
    public String getPlYm() {
        return plYm;
    }
    /**
     * PL年月のMAX年月を設定します。
     * @param plYm PL年月のMAX年月
     */
    public void setPlYm(String plYm) {
        this.plYm = plYm;
    }
 
}
