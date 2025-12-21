package jp.co.isid.mos.bird.bizsupport.plregist.entity;

import java.math.BigDecimal;

public class TrnZenPLInfo {
    
    public static final String TABLE = "BT17PLDT";
    
    public static final String genzairyoZaiko_COLUMN = "GENZAIRYO_ZAIKO";
    
    public static final String yasaiZaiko_COLUMN = "YASAI_ZAIKO";
    
    public static final String houzaiZaiko_COLUMN = "HOUZAI_ZAIKO";
    
    public static final String buppanZaiko_COLUMN = "BUPPAN_ZAIKO";
    
    public static final String kashiireZandaka_COLUMN = "KASHIIRE_ZANDAKA";
    
    public static final String kappuZandaka_COLUMN = "KAPPU_ZANDAKA";
    
    public static final String leaseZandaka_COLUMN = "LEASE_ZANDAKA";

    public static final String touZaikoKei_COLUMN = "TOU_ZAIKO_KEI";

    public static final String touZandakaKei_COLUMN = "TOU_ZANDAKA_KEI";

    /**
     * 原材料(当月在庫)
     */
    private BigDecimal genzairyoZaiko;
    
    /**
     * 野菜(当月在庫)
     */
    private BigDecimal yasaiZaiko;
    
    /**
     * 包材(当月在庫)
     */
    private BigDecimal houzaiZaiko;
    
    /**
     * 物販(当月在庫)
     */
    private BigDecimal buppanZaiko;
    
    /**
     * 借入金(当月残高)
     */
    private BigDecimal kashiireZandaka;
    
    /**
     * 割賦未払金(当月残高)
     */
    private BigDecimal kappuZandaka;
    
    /**
     * リース未払金(当月残高)
     */
    private BigDecimal leaseZandaka;
    
    /**
     * 当月在庫(計)
     */
    private BigDecimal touZaikoKei;

    /**
     * 当月残高(計)
     */
    private BigDecimal touZandakaKei;

    /**
     * 原材料(当月在庫)を取得します。
     * @return 原材料(当月在庫)
     */
    public BigDecimal getGenzairyoZaiko() {
        return genzairyoZaiko;
    }
    /**
     * 原材料(当月在庫)を設定します。
     * @param genzairyoZaiko 原材料(当月在庫)
     */
    public void setGenzairyoZaiko(BigDecimal genzairyoZaiko) {
        this.genzairyoZaiko = genzairyoZaiko;
    }
    
    /**
     * 野菜(当月在庫)を取得します。
     * @return 野菜(当月在庫)
     */
    public BigDecimal getYasaiZaiko() {
        return yasaiZaiko;
    }
    /**
     * 野菜(当月在庫)を設定します。
     * @param yasaiZaiko 野菜(当月在庫)
     */
    public void setYasaiZaiko(BigDecimal yasaiZaiko) {
        this.yasaiZaiko = yasaiZaiko;
    }
    
    /**
     * 包材(当月在庫)を取得します。
     * @return 包材(当月在庫)
     */
    public BigDecimal getHouzaiZaiko() {
        return houzaiZaiko;
    }
    /**
     * 包材(当月在庫)を設定します。
     * @param houzaiZaiko 包材(当月在庫)
     */
    public void setHouzaiZaiko(BigDecimal houzaiZaiko) {
        this.houzaiZaiko = houzaiZaiko;
    }
    
    /**
     * 物販(当月在庫)を取得します。
     * @return 物販(当月在庫)
     */
    public BigDecimal getBuppanZaiko() {
        return buppanZaiko;
    }
    /**
     * 物販(当月在庫)を設定します。
     * @param buppanZaiko 物販(当月在庫)
     */
    public void setBuppanZaiko(BigDecimal buppanZaiko) {
        this.buppanZaiko = buppanZaiko;
    }
    
    /**
     * 借入金(当月残高)を取得します。
     * @return 借入金(当月残高)
     */
    public BigDecimal getKashiireZandaka() {
        return kashiireZandaka;
    }
    /**
     * 借入金(当月残高)を設定します。
     * @param kashiireZandaka 借入金(当月残高)
     */
    public void setKashiireZandaka(BigDecimal kashiireZandaka) {
        this.kashiireZandaka = kashiireZandaka;
    }
    
    /**
     * 割賦未払金(当月残高)を取得します。
     * @return 割賦未払金(当月残高)
     */
    public BigDecimal getKappuZandaka() {
        return kappuZandaka;
    }
    /**
     * 割賦未払金(当月残高)を設定します。
     * @param kappuZandaka 割賦未払金(当月残高)
     */
    public void setKappuZandaka(BigDecimal kappuZandaka) {
        this.kappuZandaka = kappuZandaka;
    }
    
    /**
     * リース未払金(当月残高)を取得します。
     * @return リース未払金(当月残高)
     */
    public BigDecimal getLeaseZandaka() {
        return leaseZandaka;
    }
    /**
     * リース未払金(当月残高)を設定します。
     * @param leaseZandaka リース未払金(当月残高)
     */
    public void setLeaseZandaka(BigDecimal leaseZandaka) {
        this.leaseZandaka = leaseZandaka;
    }
    
    /**
     * 当月在庫(計)を取得します。
     * @return 当月在庫(計)
     */
    public BigDecimal getTouZaikoKei() {
        return touZaikoKei;
    }
    /**
     * 当月在庫(計)を設定します。
     * @param touZaikoKei 当月在庫(計)
     */
    public void setTouZaikoKei(BigDecimal touZaikoKei) {
        this.touZaikoKei = touZaikoKei;
    }

    /**
     * 当月残高(計)を取得します。
     * @return 当月残高(計)
     */
    public BigDecimal getTouZandakaKei() {
        return touZandakaKei;
    }
    /**
     * 当月残高(計)を設定します。
     * @param touZandakaKei 当月残高(計)
     */
    public void setTouZandakaKei(BigDecimal touZandakaKei) {
        this.touZandakaKei = touZandakaKei;
    }
}
