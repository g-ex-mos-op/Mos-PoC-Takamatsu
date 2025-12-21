package jp.co.isid.mos.bird.bizsupport.budgetregist.entity;

public class MstMiseInfo {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    public static final String sibuCd_COLUMN = "SIBU_CD";
    public static final String sibuName_COLUMN = "SIBU_NAME";
    public static final String areaDai_COLUMN = "AREA_DAI";
    public static final String sibuTorikomiName_COLUMN = "SIBU_TORIKOMI_NAME";
    public static final String fcrc_COLUMN = "FCRC";
    public static final String miseKbn_COLUMN = "MISE_KBN";
    public static final String openDt_COLUMN = "OPEN_DT";
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * FC/RC区分
     */
    private String miseKbn;
    
    /**
     * FC/RC区分表示
     */
    private String fcrc;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 支部取込コード
     */
    private String areaDai;
    
    /**
     * 支部取込名称
     */
    private String sibuTorikomiName;
    
    /**
     * オープン日付
     */
    private String openDt;
    

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
     * 支部取込コードを取得します。
     * @return 支部取込コード
     */
    public String getAreaDai() {
        return areaDai;
    }
    
    /**
     * 支部取込コードを設定します。
     * @param areaDaiCd 支部取込コード
     */
    public void setAreaDai(String areaDai) {
        this.areaDai = areaDai;
    }
    
    /**
     * 支部取込名称を取得します。
     * @return 支部取込名称 
     */
    public String getSibuTorikomiName() {
        return sibuTorikomiName;
    }
    
    /**
     * 支部取込名称を設定します。
     * @param 支部取込名称 
     */
    public void setSibuTorikomiName(String sibuTorikomiName) {
        this.sibuTorikomiName = sibuTorikomiName;
    }
    
    /**
     * 店舗区分を取得します。
     * @return 店舗区分
     */
    public String getMiseKbn() {
        return miseKbn;
    }
    
    /**
     * 店舗区分を設定します。
     * @param 店舗区分 
     */
    public void setMiseKbn(String miseKbn) {
        this.miseKbn = miseKbn;
    }
    
    /**
     * 店舗区分名称を取得します。
     * @return 店舗区分名称
     */
    public String getFcrc() {
        return fcrc;
    }
    
    /**
     * 店舗区分名称を設定します。
     * @param 店舗区分名称 
     */
    public void setFcrc(String fcrc) {
        this.fcrc = fcrc;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    
    /**
     * 支部コードを設定します。
     * @param 支部コード 
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部コード
     */
    public String getSibuName() {
        return sibuName;
    }
    
    /**
     * 支部名称を設定します。
     * @param 支部名称 
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * オープン日付を取得します。
     * @return
     */
    public String getOpenDt() {
        return openDt;
    }
    
    /**
     * オープン日付を設定します。
     * @param openDt
     */
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    
}
