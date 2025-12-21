package jp.co.isid.mos.bird.bizadmin.svtantousibu.entity;

public class UIAllSibu {
    
    public static final String TABLE = "BM10GSIBU";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String selCheck_COLUMN = "SEL_CHECK";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 選択チェック
     */
    private boolean selCheck;
    
    /**
     * レコード存在フラグ
     */
    private boolean existRecord;
    
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
     * 選択チェックを取得します。
     * @return 選択チェック
     */
    public boolean isSelCheck() {
        return selCheck;
    }
    /**
     * 選択チェックを設定します。
     * @param selCheck 選択チェック
     */
    public void setSelCheck(boolean selCheck) {
        this.selCheck = selCheck;
    }
    
    /**
     * レコード存在フラグを取得します。
     * @return レコード存在フラグ
     */
    public boolean isExistRecord() {
        return existRecord;
    }
    /**
     * レコード存在フラグを設定します。
     * @param selCheck レコード存在フラグ
     */
    public void setExistRecord(boolean existRecord) {
        this.existRecord = existRecord;
    }
    
}
