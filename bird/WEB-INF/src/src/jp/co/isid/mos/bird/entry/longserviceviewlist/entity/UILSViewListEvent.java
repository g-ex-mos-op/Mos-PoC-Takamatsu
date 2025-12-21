/*
 * 作成日: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.entity;

public class UILSViewListEvent {
    
    public static final String TABLE = "BR17ENTL";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String honbuFromDt_COLUMN = "HONBU_FROM_DT";
    
    public static final String honbuToDt_COLUMN = "HONBU_TO_DT";
    
    public static final String onerFromDt_COLUMN = "ONER_FROM_DT";
    
    public static final String onerToDt_COLUMN = "ONER_TO_DT";
    
    public static final String applyOnerCnt_COLUMN = "APPLY_ONER_CNT";
    
    public static final String noApplyOner_COLUMN = "NO_APPLY_ONER";
    
    public static final String applyCnt_COLUMN = "APPLY_CNT";
    
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
     * エントリータイトル（説明会名称）
     */
    private String entryTitle;
    
    /**
     * 開始日（本部申込開始日）
     */
    private String honbuFromDt;
    
    /**
     * 終了日(本部申込終了日)
     */
    private String honbuToDt;
    
    /**
     * 開始日（オーナー申込開始日）
     */
    private String onerFromDt;
    
    /**
     * 終了日(オーナー申込終了日)
     */
    private String onerToDt;
    
    /**
     * 申請オーナー
     */
    private java.math.BigDecimal applyOnerCnt;
    
    /**
     * 未申請オーナー
     */
    private java.math.BigDecimal noApplyOner;
    
    /**
     * 申請人数
     */
    private java.math.BigDecimal applyCnt;
    
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
     * エントリータイトル（説明会名称）を取得します。
     * @return エントリータイトル（説明会名称）
     */
    public String getEntryTitle() {
        return entryTitle;
    }
    /**
     * エントリータイトル（説明会名称）を設定します。
     * @param entryTitle エントリータイトル（説明会名称）
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }
    
    /**
     * 開始日（本部申込開始日）を取得します。
     * @return 開始日（本部申込開始日）
     */
    public String getHonbuFromDt() {
        return honbuFromDt;
    }
    /**
     * 開始日（本部申込開始日）を設定します。
     * @param honbuFromDt 開始日（本部申込開始日）
     */
    public void setHonbuFromDt(String honbuFromDt) {
        this.honbuFromDt = honbuFromDt;
    }
    
    /**
     * 終了日(本部申込終了日)を取得します。
     * @return 終了日(本部申込終了日)
     */
    public String getHonbuToDt() {
        return honbuToDt;
    }
    /**
     * 終了日(本部申込終了日)を設定します。
     * @param honbuToDt 終了日(本部申込終了日)
     */
    public void setHonbuToDt(String honbuToDt) {
        this.honbuToDt = honbuToDt;
    }
    
    /**
     * 開始日（オーナー申込開始日）を取得します。
     * @return 開始日（オーナー申込開始日）
     */
    public String getOnerFromDt() {
        return onerFromDt;
    }
    /**
     * 開始日（オーナー申込開始日）を設定します。
     * @param onerFromDt 開始日（オーナー申込開始日）
     */
    public void setOnerFromDt(String onerFromDt) {
        this.onerFromDt = onerFromDt;
    }
    
    /**
     * 終了日(オーナー申込終了日)を取得します。
     * @return 終了日(オーナー申込終了日)
     */
    public String getOnerToDt() {
        return onerToDt;
    }
    /**
     * 終了日(オーナー申込終了日)を設定します。
     * @param onerToDt 終了日(オーナー申込終了日)
     */
    public void setOnerToDt(String onerToDt) {
        this.onerToDt = onerToDt;
    }
    
    /**
     * 申請オーナーを取得します。
     * @return 申請オーナー
     */
    public java.math.BigDecimal getApplyOnerCnt() {
        return applyOnerCnt;
    }
    /**
     * 申請オーナーを設定します。
     * @param applyOnerCnt 申請オーナー
     */
    public void setApplyOnerCnt(java.math.BigDecimal applyOnerCnt) {
        this.applyOnerCnt = applyOnerCnt;
    }
    
    /**
     * 未申請オーナーを取得します。
     * @return 未申請オーナー
     */
    public java.math.BigDecimal getNoApplyOner() {
        return noApplyOner;
    }
    /**
     * 未申請オーナーを設定します。
     * @param noApplyOner 未申請オーナー
     */
    public void setNoApplyOner(java.math.BigDecimal noApplyOner) {
        this.noApplyOner = noApplyOner;
    }
    
    /**
     * 申請人数を取得します。
     * @return 申請人数
     */
    public java.math.BigDecimal getApplyCnt() {
        return applyCnt;
    }
    /**
     * 申請人数を設定します。
     * @param applyCnt 申請人数
     */
    public void setApplyCnt(java.math.BigDecimal applyCnt) {
        this.applyCnt = applyCnt;
    }
    
}
