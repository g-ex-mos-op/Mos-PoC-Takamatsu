package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

public class CodEventPatternCd {
    
    public static final String TABLE = "BC24EVPT";
    
    public static final String eventCd_COLUMN = "EVENT_CD";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String eventPatternCd_COLUMN = "EVENT_PATTERN_CD";
    
    public static final String eventPatternName_COLUMN = "EVENT_PATTERN_NAME";
    
    /**
     * イベント種別
     */
    private String eventCd;
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    /**
     * イベントパターンコード
     */
    private String eventPatternCd;
    
    /**
     * イベントパターン名称
     */
    private String eventPatternName;
    
    /**
     * イベント種別を取得します。
     * @return イベント種別
     */
    public String getEventCd() {
        return eventCd;
    }
    /**
     * イベント種別を設定します。
     * @param eventCd イベント種別
     */
    public void setEventCd(String eventCd) {
        this.eventCd = eventCd;
    }
    
    /**
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    
    /**
     * イベントパターンコードを取得します。
     * @return イベントパターンコード
     */
    public String getEventPatternCd() {
        return eventPatternCd;
    }
    /**
     * イベントパターンコードを設定します。
     * @param eventPatternCd イベントパターンコード
     */
    public void setEventPatternCd(String eventPatternCd) {
        this.eventPatternCd = eventPatternCd;
    }
    
    /**
     * イベントパターン名称を取得します。
     * @return イベントパターン名称
     */
    public String getEventPatternName() {
        return eventPatternName;
    }
    /**
     * イベントパターン名称を設定します。
     * @param eventPatternName イベントパターン名称
     */
    public void setEventPatternName(String eventPatternName) {
        this.eventPatternName = eventPatternName;
    }
    
}
