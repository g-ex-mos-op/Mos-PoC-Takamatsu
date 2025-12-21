package jp.co.isid.mos.bird.bizreport.common.entity;

public class TenpoInfo {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String companyCd_COLUMN   = "COMPANY_CD";
    public static final String miseCd_COLUMN      = "MISE_CD";
    public static final String miseKbn_COLUMN     = "MISE_KBN";
    public static final String miseNameKj_COLUMN  = "MISE_NAME_KJ";
    
    /** 会社コード */
    private String companyCd;
    /** 店コード */
    private String miseCd;
    /** 店区分 */
    private String miseKbn;
    /** 店名称(漢字) */
    private String miseNameKj;

    //////////////////////////セッター・ゲッター///////////////////////////

    /**
     * 会社コードを取得します。
     * @return companyCd
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを取得します。
     * @param companyCd
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
     * 店区分を取得します。
     * @return miseKbn
     */
    public String getMiseKbn() {
        return miseKbn;
    }
    /**
     * 店区分を設定します。
     * @param miseKbn
     */
    public void setMiseKbn(String miseKbn) {
        this.miseKbn = miseKbn;
    }

    /**
     * 店名称(漢字)を取得します。
     * @return 店名称(漢字)
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称(漢字)を設定します。
     * @param miseNameKj 店名称(漢字)
     */
    public void setMiseNameKj(String name) {
        this.miseNameKj = name;
    }

}
