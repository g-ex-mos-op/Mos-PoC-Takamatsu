package jp.co.isid.mos.bird.entry.projectplanoffer.entity;

public class UIOfferOnerInfo {
    
    public static final String TABLE = "BM11ONER";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String onerTel_COLUMN = "ONER_TEL";
    
    public static final String seikyuPostNo_COLUMN = "SEIKYU_POST_NO";
    
    public static final String seikyuAdrs1_COLUMN = "SEIKYU_ADRS1";
    
    public static final String seikyuAdrs2_COLUMN = "SEIKYU_ADRS2";
    
    public static final String seikyuAdrs3_COLUMN = "SEIKYU_ADRS3";
    
    /**
     * オーナー名称（漢字）
     */
    private String onerNameKj;
    
    /**
     * オーナー電話番号
     */
    private String onerTel;
    
    /**
     * オーナー郵便番号
     */
    private String seikyuPostNo;
    
    /**
     * 請求住所1
     */
    private String seikyuAdrs1;
    
    /**
     * 請求住所2
     */
    private String seikyuAdrs2;
    
    /**
     * 請求住所3
     */
    private String seikyuAdrs3;
    
    /**
     * オーナー名称（漢字）を取得します。
     * @return オーナー名称（漢字）
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称（漢字）を設定します。
     * @param onerNameKj オーナー名称（漢字）
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * オーナー電話番号を取得します。
     * @return オーナー電話番号
     */
    public String getOnerTel() {
        return onerTel;
    }
    /**
     * オーナー電話番号を設定します。
     * @param onerTel オーナー電話番号
     */
    public void setOnerTel(String onerTel) {
        this.onerTel = onerTel;
    }
    
    /**
     * オーナー郵便番号を取得します。
     * @return オーナー郵便番号
     */
    public String getSeikyuPostNo() {
        return seikyuPostNo;
    }
    /**
     * オーナー郵便番号を設定します。
     * @param seikyuPostNo オーナー郵便番号
     */
    public void setSeikyuPostNo(String seikyuPostNo) {
        this.seikyuPostNo = seikyuPostNo;
    }
    
    /**
     * 請求住所1を取得します。
     * @return 請求住所1
     */
    public String getSeikyuAdrs1() {
        return seikyuAdrs1;
    }
    /**
     * 請求住所1を設定します。
     * @param seikyuAdrs1 請求住所1
     */
    public void setSeikyuAdrs1(String seikyuAdrs1) {
        this.seikyuAdrs1 = seikyuAdrs1;
    }
    
    /**
     * 請求住所2を取得します。
     * @return 請求住所2
     */
    public String getSeikyuAdrs2() {
        return seikyuAdrs2;
    }
    /**
     * 請求住所2を設定します。
     * @param seikyuAdrs2 請求住所2
     */
    public void setSeikyuAdrs2(String seikyuAdrs2) {
        this.seikyuAdrs2 = seikyuAdrs2;
    }
    
    /**
     * 請求住所3を取得します。
     * @return 請求住所3
     */
    public String getSeikyuAdrs3() {
        return seikyuAdrs3;
    }
    /**
     * 請求住所3を設定します。
     * @param seikyuAdrs3 請求住所3
     */
    public void setSeikyuAdrs3(String seikyuAdrs3) {
        this.seikyuAdrs3 = seikyuAdrs3;
    }
    
}
