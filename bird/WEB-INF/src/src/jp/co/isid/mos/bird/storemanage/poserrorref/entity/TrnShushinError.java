package jp.co.isid.mos.bird.storemanage.poserrorref.entity;

public class TrnShushinError {

    public static final String TABLE = "BT75PERT";

    public static final String shuDt_COLUMN = "SHU_DT";

    public static final String shuKbn_COLUMN = "SHU_KBN";

    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String companyName_COLUMN = "COMPANY_NAME";

    public static final String onerCd_COLUMN = "ONER_CD";

    public static final String miseCd_COLUMN = "MISE_CD";

    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";

    public static final String closeDt_COLUMN = "CLOSE_DT";

    public static final String tekiyou_COLUMN = "TEKIYOU";

    public static final String holKbn_COLUMN = "HOL_KBN";

    /**
     * 集信日
     */
    private String shuDt;

    /**
     * 集信区分
     */
    private String shuKbn;

    /**
     * 会社コード
     */
    private String companyCd;

    /**
     * 会社名称
     */
    private String companyName;

    /**
     * オーナーコード
     */
    private String onerCd;

    /**
     * 店舗コード
     */
    private String miseCd;

    /**
     * 店舗名称
     */
    private String miseNameKj;

    /**
     * クローズ日
     */
    private String closeDt;

    /**
     * 摘要
     */
    private String tekiyou;

    /**
     * 休業区分
     */
    private String holKbn;

    /**
     * 集信日を取得します。
     * @return 集信日
     */
    public String getShuDt() {
        return shuDt;
    }
    /**
     * 集信日を設定します。
     * @param shuDt 集信日
     */
    public void setShuDt(String shuDt) {
        this.shuDt = shuDt;
    }

    /**
     * 集信区分を取得します。
     * @return 集信区分
     */
    public String getShuKbn() {
        return shuKbn;
    }
    /**
     * 集信区分を設定します。
     * @param shuKbn 集信区分
     */
    public void setShuKbn(String shuKbn) {
        this.shuKbn = shuKbn;
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
     * 会社名称を取得します。
     * @return 会社名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名称を設定します。
     * @param companyName 会社名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseNameKj() {
    	if(miseNameKj == null)
    		return null;
        return miseNameKj.replaceAll("[　*| *]*$", "");
    }
    /**
     * 店舗名称を設定します。
     * @param miseNameKj 店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
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
     * 摘要を取得します。
     * @return 摘要
     */
    public String getTekiyou() {
    	if(tekiyou == null)
    		return null;
        return tekiyou.replaceAll("[　*| *]*$", "");
    }
    /**
     * 摘要を設定します。
     * @param tekiyou 摘要
     */
    public void setTekiyou(String tekiyou) {
        this.tekiyou = tekiyou;
    }

    /**
     * 休業区分を取得します。
     * @return 休業区分
     */
    public String getHolKbn() {
        return holKbn;
    }
    /**
     * 休業区分を設定します。
     * @param holKbn 休業区分
     */
    public void setHolKbn(String holKbn) {
        this.holKbn = holKbn;
    }
}