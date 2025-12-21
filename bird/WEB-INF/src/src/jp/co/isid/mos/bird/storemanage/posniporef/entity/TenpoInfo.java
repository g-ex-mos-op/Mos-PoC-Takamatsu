package jp.co.isid.mos.bird.storemanage.posniporef.entity;


public class TenpoInfo {
    
    public static final String TABLE = "BM01TENM";
    public static final String companyCd_COLUMN      = "COMPANY_CD";
    public static final String miseCd_COLUMN         = "MISE_CD";
    public static final String miseNameKj_COLUMN     = "MISE_NAME_KJ";
    public static final String closeFlg_COLUMN       = "CLOSE_FLG";
    

    /** 会社コード */
    private String companyCd;
    /** 店コード */
    private String miseCd;
    /** 店舗名称 */
    private String miseNameKj;
    /** 閉店フラグ */
    private int closeFlg;
    /** 店舗名称コード付 */
    private String miseNameAddCd;


    /////////////セッター・ゲッター///////////////////////////////////////

    /**
     * @return closeFlg を戻します。
     */
    public int getCloseFlg() {
        return closeFlg;
    }
    /**
     * @param closeFlg 設定する closeFlg。
     */
    public void setCloseFlg(int closeFlg) {
        this.closeFlg = closeFlg;
    }
    /**
     * @return companyCd を戻します。
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * @param companyCd 設定する companyCd。
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * @return miseCd を戻します。
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * @param miseCd 設定する miseCd。
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    /**
     * @return miseNameAddCd を戻します。
     */
    public String getMiseNameAddCd() {
        return miseNameAddCd;
    }
    /**
     * @param miseNameAddCd 設定する miseNameAddCd。
     */
    public void setMiseNameAddCd(String miseNameAddCd) {
        this.miseNameAddCd = miseNameAddCd;
    }
    /**
     * @return miseNameKj を戻します。
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * @param miseNameKj 設定する miseNameKj。
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
}
