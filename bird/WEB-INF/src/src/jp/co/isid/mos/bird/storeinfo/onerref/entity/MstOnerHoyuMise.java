package jp.co.isid.mos.bird.storeinfo.onerref.entity;

public class MstOnerHoyuMise {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 店オープン日
     */
    private String openDt;
    
    /**
     * 店クローズ日
     */
    private String closeDt;
    
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
    
    /**
     * 店オープン日を取得します。
     * @return 店オープン日
     */
    public String getOpenDt() {
        return openDt;
    }
    /**
     * 店オープン日を設定します。
     * @param openDt 店オープン日
     */
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    
    /**
     * 店クローズ日を取得します。
     * @return 店クローズ日
     */
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * 店クローズ日を設定します。
     * @param closeDt 店クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
}
