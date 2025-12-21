package jp.co.isid.mos.bird.common.entity;

/**
 * 対象店舗情報
 * 
 * @author xkinu
 *
 */
public class UIMiseList {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String closeMj_COLUMN = "CLOSE_MJ";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * (CLOSE)文字
     */
    private String closeMj = "";
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * オープン日
     */
    private String openDt;
    
    /**
     * クローズ日
     */
    private String closeDt;
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
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
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    /**
     * 店名称+(CLOSE)の型で名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKjInCloseMj() {
        return getMiseNameKj()+getCloseMj();
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
     * @return closeDt を戻します。
     */
    public String getCloseDt() {
        return closeDt;
    }
    /**
     * @param closeDt 設定する closeDt。
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    /**
     * @return openDt を戻します。
     */
    public String getOpenDt() {
        return openDt;
    }
    /**
     * @param openDt 設定する openDt。
     */
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    /**
     * @return sibuCd を戻します。
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * @param sibuCd 設定する sibuCd。
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    /**
     * @return closeMj を戻します。
     */
    public String getCloseMj() {
        return closeMj;
    }
    /**
     * @param closeMj 設定する closeMj。
     */
    public void setCloseMj(String closeMj) {
        this.closeMj = closeMj;
    }
    
}
