package jp.co.isid.mos.bird.bizreport.posreportregist.entity;

public class UIMiseChkInfo {
    
    public static final String TABLE = "PC01TENM";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String P4TelNo_COLUMN = "P4_TEL_NO";
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * クローズ日
     */
    private String closeDt;
    
    /**
     * ネットワーク番号
     */
    private String p4TelNo;
    
    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
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
     * ネットワーク番号を取得します。
     * @return ネットワーク番号
     */
    public String getP4TelNo() {
        return p4TelNo;
    }
    /**
     * ネットワーク番号を設定します。
     * @param p4TelNo ネットワーク番号
     */
    public void setP4TelNo(String p4TelNo) {
        this.p4TelNo = p4TelNo;
    }
    
}
