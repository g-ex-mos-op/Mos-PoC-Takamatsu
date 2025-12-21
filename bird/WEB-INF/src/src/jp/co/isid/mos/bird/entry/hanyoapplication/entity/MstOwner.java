package jp.co.isid.mos.bird.entry.hanyoapplication.entity;

public class MstOwner {
    
    public static final String TABLE = "BM11ONRM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String onerNameKna_COLUMN = "ONER_NAME_KNA";
    
    public static final String onerSubKj_COLUMN = "ONER_SUB_KJ";
    
    public static final String seikyuPostNo_COLUMN = "SEIKYU_POST_NO";
    
    public static final String seikyuAdrs1_COLUMN = "SEIKYU_ADRS1";
    
    public static final String seikyuAdrs2_COLUMN = "SEIKYU_ADRS2";
    
    public static final String seikyuAdrs3_COLUMN = "SEIKYU_ADRS3";
    
    public static final String seikyuName_COLUMN = "SEIKYU_NAME";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * オーナー名称（カナ）
     */
    private String onerNameKna;
    
    /**
     * オーナー個人名称
     */
    private String onerSubKj;
    
    /**
     * 請求郵便番号
     */
    private String seikyuPostNo;
    
    /**
     * 請求住所１
     */
    private String seikyuAdrs1;
    
    /**
     * 請求住所２
     */
    private String seikyuAdrs2;
    
    /**
     * 請求住所３
     */
    private String seikyuAdrs3;
    
    /**
     * 送付先名称
     */
    private String seikyuName;
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * オーナー名称（カナ）を取得します。
     * @return オーナー名称（カナ）
     */
    public String getOnerNameKna() {
        return onerNameKna;
    }
    /**
     * オーナー名称（カナ）を設定します。
     * @param onerNameKna オーナー名称（カナ）
     */
    public void setOnerNameKna(String onerNameKna) {
        this.onerNameKna = onerNameKna;
    }
    
    /**
     * 請求郵便番号を取得します。
     * @return 請求郵便番号
     */
    public String getSeikyuPostNo() {
        return seikyuPostNo;
    }
    /**
     * 請求郵便番号を設定します。
     * @param seikyuPostNo 請求郵便番号
     */
    public void setSeikyuPostNo(String seikyuPostNo) {
        this.seikyuPostNo = seikyuPostNo;
    }
    
    /**
     * 請求住所１を取得します。
     * @return 請求住所１
     */
    public String getSeikyuAdrs1() {
        return seikyuAdrs1;
    }
    /**
     * 請求住所１を設定します。
     * @param seikyuAdrs1 請求住所１
     */
    public void setSeikyuAdrs1(String seikyuAdrs1) {
        this.seikyuAdrs1 = seikyuAdrs1;
    }
    
    /**
     * 請求住所２を取得します。
     * @return 請求住所２
     */
    public String getSeikyuAdrs2() {
        return seikyuAdrs2;
    }
    /**
     * 請求住所２を設定します。
     * @param seikyuAdrs2 請求住所２
     */
    public void setSeikyuAdrs2(String seikyuAdrs2) {
        this.seikyuAdrs2 = seikyuAdrs2;
    }
    
    /**
     * 請求住所３を取得します。
     * @return 請求住所３
     */
    public String getSeikyuAdrs3() {
        return seikyuAdrs3;
    }
    /**
     * 請求住所３を設定します。
     * @param seikyuAdrs3 請求住所３
     */
    public void setSeikyuAdrs3(String seikyuAdrs3) {
        this.seikyuAdrs3 = seikyuAdrs3;
    }
    
    /**
     * 送付先名称を取得します。
     * @return 送付先名称
     */
    public String getSeikyuName() {
        return seikyuName;
    }
    /**
     * 送付先名称を設定します。
     * @param seikyuName 送付先名称
     */
    public void setSeikyuName(String seikyuName) {
        this.seikyuName = seikyuName;
    }
    public String getOnerSubKj() {
        return onerSubKj;
    }
    public void setOnerSubKj(String onerSubKj) {
        this.onerSubKj = onerSubKj;
    }
    
}
