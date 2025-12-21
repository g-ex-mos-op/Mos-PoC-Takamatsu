package jp.co.isid.mos.bird.entry.hanyoviewlist.entity;

public class UIHanyoEntryInfo {

    public static final String TABLE = "BT22ENKN";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";

    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String staffNameKj_COLUMN = "STAFF_NAME_KJ";
    
    public static final String keiyakuFlg_COLUMN = "KEIYAKU_FLG";
    
    
    /**
     * エントリーコード
     */
    private String entryCd;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 研修生ID
     */
    private String staffId;
    
    /**
     * 研修生名称
     */
    private String staffNameKj;
    
    /**
     * オーナー契約フラグ
     */
    private String keiyakuFlg;
    

    
    
    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
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
     * 研修生IDを取得します。
     * @return 研修生ID
     */
    public String getStaffId() {
        return staffId;
    }
    /**
     * 研修生IDを設定します。
     * @param staffId 研修生ID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    /**
     * 研修生名称を取得します。
     * @return 研修生名称
     */
    public String getStaffNameKj() {
        return staffNameKj;
    }
    /**
     * 研修生名称を設定します。
     * @param staffNameKj 研修生名称
     */
    public void setStaffNameKj(String staffNameKj) {
        this.staffNameKj = staffNameKj;
    }

    /**
     * オーナー契約フラグを取得します。
     * @return オーナー契約フラグ
     */
    public String getKeiyakuFlg() {
        return keiyakuFlg;
    }
    /**
     * オーナー契約フラグを設定します。
     * @param keiyakuFlg オーナー契約フラグ
     */
    public void setKeiyakuFlg(String keiyakuFlg) {
        this.keiyakuFlg = keiyakuFlg;
    }

    
}
