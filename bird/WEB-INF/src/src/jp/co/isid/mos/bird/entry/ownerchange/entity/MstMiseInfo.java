package jp.co.isid.mos.bird.entry.ownerchange.entity;

public class MstMiseInfo {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
            
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * クローズ日
     */
    private String closeDt;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
        
    public String getMiseInfo() {
        return miseCd + " " + miseNameKj;
    }

    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
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
     * クローズ日を取得します。
     * @return クローズ日
     */
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * クローズ日を設定します。
     * @param closeDt クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
}