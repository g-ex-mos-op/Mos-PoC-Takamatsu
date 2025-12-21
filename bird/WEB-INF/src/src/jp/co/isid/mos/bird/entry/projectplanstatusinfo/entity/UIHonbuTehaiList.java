package jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity;

public class UIHonbuTehaiList {
    
    public static final String TABLE = "BM11ONER";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String singleCnt_COLUMN = "SINGLE_CNT";
    
    public static final String twinCnt_COLUMN = "TWIN_CNT";
    
    public static final String singleSum_COLUMN = "SINGLE_SUM";
    
    public static final String twinSum_COLUMN = "TWIN_SUM";
    
    public static final String note_COLUMN = "NOTE";
    
    public static final String keiyakuSta_COLUMN = "KEIYAKU_STA";
    
    public static final String keiyakuEnd_COLUMN = "KEIYAKU_END";
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部
     */
    private String sibuName;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * シングルカウント
     */
    private String singleCnt;
    
    /**
     * ツインカウント
     */
    private String twinCnt;
    
    /**
     * シングルの総和
     */
    private String singleSum;
    
    /**
     * ツインの総和
     */
    private String twinSum;
    
    /**
     * 備考
     */
    private String note;
    
    /**
     * 契約開始日
     */
    private String keiyakuSta;
    
    /**
     * 契約終了日
     */
    private String keiyakuEnd;
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部を取得します。
     * @return 支部
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部を設定します。
     * @param sibuName 支部
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
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
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * シングルカウントを取得します。
     * @return シングルカウント
     */
    public String getSingleCnt() {
        return singleCnt;
    }
    /**
     * シングルカウントを設定します。
     * @param singleCnt シングルカウント
     */
    public void setSingleCnt(String singleCnt) {
        this.singleCnt = singleCnt;
    }
    
    /**
     * ツインカウントを取得します。
     * @return ツインカウント
     */
    public String getTwinCnt() {
        return twinCnt;
    }
    /**
     * ツインカウントを設定します。
     * @param twinCnt ツインカウント
     */
    public void setTwinCnt(String twinCnt) {
        this.twinCnt = twinCnt;
    }
    
    /**
     * シングルの総和を取得します。
     * @return シングルの総和
     */
    public String getSingleSum() {
        return singleSum;
    }
    /**
     * シングルの総和を設定します。
     * @param singleSum シングルの総和
     */
    public void setSingleSum(String singleSum) {
        this.singleSum = singleSum;
    }
    
    /**
     * ツインの総和を取得します。
     * @return ツインの総和
     */
    public String getTwinSum() {
        return twinSum;
    }
    /**
     * ツインの総和を設定します。
     * @param twinSum ツインの総和
     */
    public void setTwinSum(String twinSum) {
        this.twinSum = twinSum;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考を設定します。
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 契約開始日を取得します。
     * @return 契約開始日
     */
    public String getKeiyakuSta() {
        return keiyakuSta;
    }
    /**
     * 契約開始日を設定します。
     * @param keiyakuSta 契約開始日
     */
    public void setKeiyakuSta(String keiyakuSta) {
        this.keiyakuSta = keiyakuSta;
    }
    
    /**
     * 契約終了日を取得します。
     * @return 契約終了日
     */
    public String getKeiyakuEnd() {
        return keiyakuEnd;
    }
    /**
     * 契約終了日を設定します。
     * @param keiyakuEnd 契約終了日
     */
    public void setKeiyakuEnd(String keiyakuEnd) {
        this.keiyakuEnd = keiyakuEnd;
    }
    
}
