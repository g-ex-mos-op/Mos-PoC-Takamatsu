package jp.co.isid.mos.bird.storemanage.msschousadataref.entity;

public class MssFileInfo {
    
    public static final String TABLE = "BM99DMMY";
    
    public static final String fileName_COLUMN = "FILE_NAME";
    
    public static final String nendo_COLUMN = "NENDO";
    
    public static final String kai_COLUMN = "KAI";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseName_COLUMN = "MISE_NAME";
    
    public static final String OnerCd_COLUMN = "ONER_CD";
    
    public static final String datsKbn_COLUMN = "DATA_KBN";
    
    public static final String dateName_COLUMN = "DATE_NAME";
    
    public static final String compnayCd_COLUMN = "COMPNAY_CD";
    
    /**
     * ファイル名称
     */
    private String fileName;
    
    /**
     * 年度
     */
    private String nendo;
    
    /**
     * 回数
     */
    private String kai;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseName;
    
    /**
     * オーナーコード
     */
    private String OnerCd;
    
    /**
     * オーナー名称
     */
    private String OnerName;
    
    /**
     * データ区分
     */
    private String dataKbn;
    
    /**
     * オーナー名称
     */
    private String compnayCd;

    /**
     * 企業コード
     */
    private String dataName;

    /**
     * ファイル名称を取得します。
     * @return ファイル名称
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * ファイル名称を設定します。
     * @param fileName ファイル名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * 年度を取得します。
     * @return 年度
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * 年度を設定します。
     * @param nendo 年度
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }
    
    /**
     * 回数を取得します。
     * @return 回数
     */
    public String getKai() {
        return kai;
    }
    /**
     * 回数を設定します。
     * @param kai 回数
     */
    public void setKai(String kai) {
        this.kai = kai;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseName() {
        return miseName;
    }
    /**
     * 店名称を設定します。
     * @param miseName 店名称
     */
    public void setMiseName(String miseName) {
        this.miseName = miseName;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return OnerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param OnerCd オーナーコード
     */
    public void setOnerCd(String OnerCd) {
        this.OnerCd = OnerCd;
    }
    
    /**
     * データ区分を取得します。
     * @return データ区分
     */
    public String getDataKbn() {
        return dataKbn;
    }
    /**
     * データ区分を設定します。
     * @param dateKbn データ区分
     */
    public void setDataKbn(String dataKbn) {
        this.dataKbn = dataKbn;
    }
    
    /**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getDataName() {
        return dataName;
    }
    /**
     * オーナー名称を設定します。
     * @param dateName オーナー名称
     */
    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
    /**
     * 企業コードを設定します。
     * @param compnayCd 企業コード
     */
    public String getCompnayCd() {
        return compnayCd;
    }
    /**
     * 企業コードを設定します。
     * @param compnayCd 企業コード
     */
    public void setCompnayCd(String compnayCd) {
        this.compnayCd = compnayCd;
    }
   /** オーナー名称を取得します。
    * @return オーナー名称
    */
    public String getOnerName() {
        return OnerName;
    }
    /**
     * オーナー名称を設定します。
     * @param dateName オーナー名称
     */
    public void setOnerName(String onerName) {
        OnerName = onerName;
    }
    
}
