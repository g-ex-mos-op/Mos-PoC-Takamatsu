package jp.co.isid.mos.bird.storeinfo.onerref.entity;

public class TrnOnerDai {
    
    public static final String TABLE = "BM14DAIH";
    
    public static final String onerSubKj_COLUMN = "ONER_SUB_KJ";
    
    public static final String onerSubKna_COLUMN = "ONER_SUB_KNA";
    
    public static final String daihyoChgReason_COLUMN = "DAIHYO_CHG_REASON";
    
    /**
     * 個人名称（漢字）
     */
    private String onerSubKj;
    
    /**
     * 個人名称（カナ）
     */
    private String onerSubKna;
    
    /**
     * 代表者変更理由
     */
    private String daihyoChgReason;
    
    /**
     * 個人名称（漢字）を取得します。
     * @return 個人名称（漢字）
     */
    public String getOnerSubKj() {
        return onerSubKj;
    }
    /**
     * 個人名称（漢字）を設定します。
     * @param onerSubKj 個人名称（漢字）
     */
    public void setOnerSubKj(String onerSubKj) {
        this.onerSubKj = onerSubKj;
    }
    
    /**
     * 個人名称（カナ）を取得します。
     * @return 個人名称（カナ）
     */
    public String getOnerSubKna() {
        return onerSubKna;
    }
    /**
     * 個人名称（カナ）を設定します。
     * @param onerSubKna 個人名称（カナ）
     */
    public void setOnerSubKna(String onerSubKna) {
        this.onerSubKna = onerSubKna;
    }
    
    /**
     * 代表者変更理由を取得します。
     * @return 代表者変更理由
     */
    public String getDaihyoChgReason() {
        return daihyoChgReason;
    }
    /**
     * 代表者変更理由を設定します。
     * @param daihyoChgReason 代表者変更理由
     */
    public void setDaihyoChgReason(String daihyoChgReason) {
        this.daihyoChgReason = daihyoChgReason;
    }
    
}
