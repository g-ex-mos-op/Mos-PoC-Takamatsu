package jp.co.isid.mos.bird.entry.mlentry.entity;

public class MstMise {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String misePostNo_COLUMN = "MISE_POST_NO";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String miseNameKna_COLUMN = "MISE_NAME_KNA";
    
    public static final String miseAdrs1_COLUMN = "MISE_ADRS1";
    
    public static final String miseAdrs2_COLUMN = "MISE_ADRS2";
    
    public static final String miseAdrs3_COLUMN = "MISE_ADRS3";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String rSakujoFlg_COLUMN = "R_SAKUJO_FLG";
    
    public static final String miseAdrs_COLUMN = "MISE_ADRS";
    
    public static final String onerSubKj_COLUMN = "ONER_SUB_KJ";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店郵便番号
     */
    private String misePostNo;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 店名称（カナ）
     */
    private String miseNameKna;
    
    /**
     * 店住所１
     */
    private String miseAdrs1;
    
    /**
     * 店住所２
     */
    private String miseAdrs2;
    
    /**
     * 店住所３
     */
    private String miseAdrs3;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称漢字
     */
    private String onerNameKj;

    /**
     * 論理削除フラグ
     */
    private String rSakujoFlg;
    
    /**
     * 店住所
     */
    private String miseAdrs;
    
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
     * 店郵便番号を取得します。
     * @return 店郵便番号
     */
    public String getMisePostNo() {
        return misePostNo;
    }
    /**
     * 店郵便番号を設定します。
     * @param misePostNo 店郵便番号
     */
    public void setMisePostNo(String misePostNo) {
        this.misePostNo = misePostNo;
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
     * 店名称（カナ）を取得します。
     * @return 店名称（カナ）
     */
    public String getMiseNameKna() {
        return miseNameKna;
    }
    /**
     * 店名称（カナ）を設定します。
     * @param miseNameKna 店名称（カナ）
     */
    public void setMiseNameKna(String miseNameKna) {
        this.miseNameKna = miseNameKna;
    }
    
    /**
     * 店住所１を取得します。
     * @return 店住所１
     */
    public String getMiseAdrs1() {
        return miseAdrs1;
    }
    /**
     * 店住所１を設定します。
     * @param miseAdrs1 店住所１
     */
    public void setMiseAdrs1(String miseAdrs1) {
        this.miseAdrs1 = miseAdrs1;
    }
    
    /**
     * 店住所２を取得します。
     * @return 店住所２
     */
    public String getMiseAdrs2() {
        return miseAdrs2;
    }
    /**
     * 店住所２を設定します。
     * @param miseAdrs2 店住所２
     */
    public void setMiseAdrs2(String miseAdrs2) {
        this.miseAdrs2 = miseAdrs2;
    }
    
    /**
     * 店住所３を取得します。
     * @return 店住所３
     */
    public String getMiseAdrs3() {
        return miseAdrs3;
    }
    /**
     * 店住所３を設定します。
     * @param miseAdrs3 店住所３
     */
    public void setMiseAdrs3(String miseAdrs3) {
        this.miseAdrs3 = miseAdrs3;
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
     * 論理削除フラグを取得します。
     * @return 論理削除フラグ
     */
    public String getRSakujoFlg() {
        return rSakujoFlg;
    }
    /**
     * 論理削除フラグを設定します。
     * @param rSakujoFlg 論理削除フラグ
     */
    public void setRSakujoFlg(String rSakujoFlg) {
        this.rSakujoFlg = rSakujoFlg;
    }
    public String getOnerNameKj() {
        return onerNameKj;
    }
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    public String getMiseAdrs() {
        return miseAdrs;
    }
    public void setMiseAdrs(String miseAdrs) {
        this.miseAdrs = miseAdrs;
    }
}
