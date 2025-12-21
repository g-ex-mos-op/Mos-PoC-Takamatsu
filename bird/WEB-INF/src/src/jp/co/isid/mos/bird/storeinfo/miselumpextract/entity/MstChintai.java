package jp.co.isid.mos.bird.storeinfo.miselumpextract.entity;

public class MstChintai {
    
    public static final String TABLE = "BM21CHIT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String miseLeaseShu_COLUMN = "MISE_LEASE_SHU";
    
    public static final String miseLeaseName_COLUMN = "MISE_LEASE_NAME";
    
    public static final String miseLeaseStart_COLUMN = "MISE_LEASE_START";
    
    public static final String miseLeaseEnd_COLUMN = "MISE_LEASE_END";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    /**
     * 管理会社コード企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 店クローズ日
     */
    private String closeDt;
    
    /**
     * 賃貸店舗種別
     */
    private String miseLeaseShu;
    
    /**
     * 賃貸店舗名称
     */
    private String miseLeaseName;
    
    /**
     * 賃貸店舗契約日
     */
    private String miseLeaseStart;
    
    /**
     * 契約終了予定日
     */
    private String miseLeaseEnd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名称
     */
    private String blockName;
    
    /**
     * 管理会社コード企業コードを取得します。
     * @return 管理会社コード企業コード
     */
    public String getCompanyCd() {
        return convString(companyCd);
    }
    /**
     * 管理会社コード企業コードを設定します。
     * @param companyCd 管理会社コード企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return convString(miseCd);
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
        return convString(miseNameKj);
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 店クローズ日を取得します。
     * @return 店クローズ日
     */
    public String getCloseDt() {
        return convString(closeDt);
    }
    /**
     * 店クローズ日を設定します。
     * @param closeDt 店クローズ日
     */
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    
    /**
     * 賃貸店舗種別を取得します。
     * @return 賃貸店舗種別
     */
    public String getMiseLeaseShu() {
        return convString(miseLeaseShu);
    }
    /**
     * 賃貸店舗種別を設定します。
     * @param miseLeaseShu 賃貸店舗種別
     */
    public void setMiseLeaseShu(String miseLeaseShu) {
        this.miseLeaseShu = miseLeaseShu;
    }
    
    /**
     * 賃貸店舗名称を取得します。
     * @return 賃貸店舗名称
     */
    public String getMiseLeaseName() {
        return convString(miseLeaseName);
    }
    /**
     * 賃貸店舗名称を設定します。
     * @param miseLeaseName 賃貸店舗名称
     */
    public void setMiseLeaseName(String miseLeaseName) {
        this.miseLeaseName = miseLeaseName;
    }
    
    /**
     * 賃貸店舗契約日を取得します。
     * @return 賃貸店舗契約日
     */
    public String getMiseLeaseStart() {
        return convString(miseLeaseStart);
    }
    /**
     * 賃貸店舗契約日を設定します。
     * @param miseLeaseStart 賃貸店舗契約日
     */
    public void setMiseLeaseStart(String miseLeaseStart) {
        this.miseLeaseStart = miseLeaseStart;
    }
    
    /**
     * 契約終了予定日を取得します。
     * @return 契約終了予定日
     */
    public String getMiseLeaseEnd() {
        return convString(miseLeaseEnd);
    }
    /**
     * 契約終了予定日を設定します。
     * @param miseLeaseEnd 契約終了予定日
     */
    public void setMiseLeaseEnd(String miseLeaseEnd) {
        this.miseLeaseEnd = miseLeaseEnd;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return convString(sibuCd);
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return convString(sibuName);
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * ブロックコードを取得します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return convString(blockCd);
    }
    /**
     * ブロックコードを設定します。
     * @param blockCd ブロックコード
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }
    
    /**
     * ブロック名称を取得します。
     * @return ブロック名称
     */
    public String getBlockName() {
        return convString(blockName);
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    
    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
    
}
