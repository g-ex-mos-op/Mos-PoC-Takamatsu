package jp.co.isid.mos.bird.storemanage.mssonerpointref.entity;

public class UIOnerMiseCnt {
    
    public static final String TABLE = "BM10GSIB";
    
    public static final String sibuCd_COLUMN = "SUBU_CD";

    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String zenCnt_COLUMN = "ZEN_CNT";
    
    public static final String sibuCnt_COLUMN = "SIBU_CNT";
    
    public static final String svCnt_COLUMN = "SV_CNT";
    
    public static final String miseCnt_COLUMN = "MISE_CNT";
    
    /**
     * 支部コード
     */
    private String sibuCd;
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 全店舗数
     */
    private String zenCnt;
    
    /**
     * 支部店舗数
     */
    private String sibuCnt;
    
    /**
     * SV店舗数
     */
    private String svCnt;
    
    /**
     * 店舗数
     */
    private String miseCnt;
    
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
     * 全店舗数を取得します。
     * @return 全店舗数
     */
    public String getZenCnt() {
        return zenCnt;
    }
    /**
     * 全店舗数を設定します。
     * @param zenCnt 全店舗数
     */
    public void setZenCnt(String zenCnt) {
        this.zenCnt = zenCnt;
    }
    
    /**
     * 支部店舗数を取得します。
     * @return 支部店舗数
     */
    public String getSibuCnt() {
        return sibuCnt;
    }
    /**
     * 支部店舗数を設定します。
     * @param sibuCnt 支部店舗数
     */
    public void setSibuCnt(String sibuCnt) {
        this.sibuCnt = sibuCnt;
    }
    
    /**
     * SV店舗数を取得します。
     * @return SV店舗数
     */
    public String getSvCnt() {
        return svCnt;
    }
    /**
     * SV店舗数を設定します。
     * @param svCnt SV店舗数
     */
    public void setSvCnt(String svCnt) {
        this.svCnt = svCnt;
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public String getMiseCnt() {
        return miseCnt;
    }
    /**
     * 店舗数を設定します。
     * @param miseCnt 店舗数
     */
    public void setMiseCnt(String miseCnt) {
        this.miseCnt = miseCnt;
    }
    
}
