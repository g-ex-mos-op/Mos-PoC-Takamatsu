package jp.co.isid.mos.bird.config.campaignmasterregist.entity;

public class UISibuList {
    
    public static final String TABLE = "BM10GSIB";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 選択チェックボックス
     */
    private boolean chkSentaku = false;
    
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
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * 選択チェックボックスを取得します。
     * @return 選択チェックボックス
     */
    public boolean getChkSentaku() {
        return chkSentaku;
    }
    /**
     * 選択チェックボックスを設定します。
     * @param chkGyotai 選択チェックボックス
     */
    public void setChkSentaku(boolean chkSentaku) {
        this.chkSentaku = chkSentaku;
    }
    
}
