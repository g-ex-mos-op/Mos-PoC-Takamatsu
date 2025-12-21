package jp.co.isid.mos.bird.storemanage.msssurveydataref.entity;

public class UIMssBatch {
    
    public static final String TABLE = "BR48BSTA";
    
    public static final String pgmId_COLUMN = "PGM_ID";
    
    public static final String pgmKbn_COLUMN = "PGM_KBN";
    
    public static final String StatKbn_COLUMN = "STAT_KBN";
    
    public static final String Kbn1_COLUMN = "KBN_1";
    
    public static final String Kbn2_COLUMN = "KBN_2";
    
    public static final String ErrCd_COLUMN = "ERR_CD";
    
    public static final String EndTmsp_COLUMN = "END_TMSP";
    
    /**
     * プログラムID
     */
    private String pgmId;
    
    /**
     * プログラム区分
     */
    private String pgmKbn;
    
    /**
     * 状態区分
     */
    private String StatKbn;
    
    /**
     * 区分1
     */
    private String Kbn1;
    
    /**
     * 区分2
     */
    private String Kbn2;
    
    /**
     * エラーコード
     */
    private String ErrCd;
    
    /**
     * 終了タイムスタンプ
     */
    private String EndTmsp;
    
    /**
     * プログラムIDを取得します。
     * @return プログラムID
     */
    public String getPgmId() {
        return pgmId;
    }
    /**
     * プログラムIDを設定します。
     * @param pgmId プログラムID
     */
    public void setPgmId(String pgmId) {
        this.pgmId = pgmId;
    }
    
    /**
     * プログラム区分を取得します。
     * @return プログラム区分
     */
    public String getPgmKbn() {
        return pgmKbn;
    }
    /**
     * プログラム区分を設定します。
     * @param pgmKbn プログラム区分
     */
    public void setPgmKbn(String pgmKbn) {
        this.pgmKbn = pgmKbn;
    }
    
    /**
     * 状態区分を取得します。
     * @return 状態区分
     */
    public String getStatKbn() {
        return StatKbn;
    }
    /**
     * 状態区分を設定します。
     * @param StatKbn 状態区分
     */
    public void setStatKbn(String StatKbn) {
        this.StatKbn = StatKbn;
    }
    
    /**
     * 区分1を取得します。
     * @return 区分1
     */
    public String getKbn1() {
        return Kbn1;
    }
    /**
     * 区分1を設定します。
     * @param Kbn1 区分1
     */
    public void setKbn1(String Kbn1) {
        this.Kbn1 = Kbn1;
    }
    
    /**
     * 区分2を取得します。
     * @return 区分2
     */
    public String getKbn2() {
        return Kbn2;
    }
    /**
     * 区分2を設定します。
     * @param Kbn2 区分2
     */
    public void setKbn2(String Kbn2) {
        this.Kbn2 = Kbn2;
    }
    
    /**
     * エラーコードを取得します。
     * @return エラーコード
     */
    public String getErrCd() {
        return ErrCd;
    }
    /**
     * エラーコードを設定します。
     * @param ErrCd エラーコード
     */
    public void setErrCd(String ErrCd) {
        this.ErrCd = ErrCd;
    }
    
    /**
     * 終了タイムスタンプを取得します。
     * @return 終了タイムスタンプ
     */
    public String getEndTmsp() {
        return EndTmsp;
    }
    /**
     * 終了タイムスタンプを設定します。
     * @param EndTmsp 終了タイムスタンプ
     */
    public void setEndTmsp(String EndTmsp) {
        this.EndTmsp = EndTmsp;
    }
    
}
