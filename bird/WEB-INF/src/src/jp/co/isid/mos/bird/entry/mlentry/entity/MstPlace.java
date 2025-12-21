package jp.co.isid.mos.bird.entry.mlentry.entity;


public class MstPlace {
    
    public static final String TABLE = "BR20MLPL";
    
    public static final String placeCd_COLUMN = "ENTRY_PLACE_CD";
    
    public static final String placeName_COLUMN = "ENTRY_PLACE_NAME";
    
    
    /**
     * 受験地コード
     */
    private String placeCd;
    
    /**
     * 受験地名称
     */
    private String placeName;

    
    
    public String getPlaceCd() {
        return placeCd;
    }

    public void setPlaceCd(String placeCd) {
        this.placeCd = placeCd;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
