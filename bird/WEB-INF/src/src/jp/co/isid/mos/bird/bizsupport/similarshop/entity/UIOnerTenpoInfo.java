package jp.co.isid.mos.bird.bizsupport.similarshop.entity;

public class UIOnerTenpoInfo {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String areaDai_COLUMN = "AREA_DAI";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    public static final String locateKbn_COLUMN = "LOCATE_KBN";
    
    public static final String miseKeitai_COLUMN = "MISE_KEITAI";
    
    public static final String uriagedaka_COLUMN = "URIAGEDAKA";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * エリア大コード(支部取込コード)
     */
    private String areaDai;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * 店オープン日
     */
    private String openDt;
    
    /**
     * ロケーション区分
     */
    private String locateKbn;
    
    /**
     * 店舗形態区分
     */
    private String miseKeitai;
    
    /**
     * 売上高
     */
    private String uriagedaka;
    
    /**
     * 店クローズ日
     */
    private String closeDt;
    
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
     * エリア大コード(支部取込コード)を取得します。
     * @return エリア大コード(支部取込コード)
     */
    public String getAreaDai() {
        return areaDai;
    }
    /**
     * エリア大コード(支部取込コード)を設定します。
     * @param areaDai エリア大コード(支部取込コード)
     */
    public void setAreaDai(String areaDai) {
        this.areaDai = areaDai;
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
     * ロケーション区分を取得します。
     * @return ロケーション区分
     */
    public String getLocateKbn() {
        return locateKbn;
    }
    /**
     * ロケーション区分を設定します。
     * @param locateKbn ロケーション区分
     */
    public void setLocateKbn(String locateKbn) {
        this.locateKbn = locateKbn;
    }
    
    /**
     * 店舗形態区分を取得します。
     * @return 店舗形態区分
     */
    public String getMiseKeitai() {
        return miseKeitai;
    }
    /**
     * 店舗形態区分を設定します。
     * @param miseKeitai 店舗形態区分
     */
    public void setMiseKeitai(String miseKeitai) {
        this.miseKeitai = miseKeitai;
    }
    
    /**
     * 売上高を取得します。
     * @return 売上高
     */
    public String getUriagedaka() {
        return uriagedaka;
    }
    /**
     * 売上高を設定します。
     * @param uriagedaka 売上高
     */
    public void setUriagedaka(String uriagedaka) {
        this.uriagedaka = uriagedaka;
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
