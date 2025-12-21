package jp.co.isid.mos.bird.commonform.publictargetsearch.entity;

public class CtlCompany {
    
    public static final String TABLE = "BC02COMP";
    
//    public static final String userId_COLUMN = "USER_ID";
    
    public static final String companyCd_COLUMN = "R_COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    
//    /**
//     * ユーザID
//     */
//    private String userId;
    
    /**
     * 企業コード
     */
    private String rCompanyCd;
    
    /**
     * 企業名
     */
    private String companyName;
    
    /**
     * 選択フラグ
     */
    private boolean sentakuFlg;
    

    
//    /**
//     * ユーザIDを取得します。
//     * @return ユーザID
//     */
//    public String getUserId() {
//        return userId;
//    }
//    /**
//     * ユーザIDを設定します。
//     * @param userId ユーザID
//     */
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getRCompanyCd() {
        return rCompanyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setRCompanyCd(String rCompanyCd) {
        this.rCompanyCd = rCompanyCd;
    }
    
    /**
     * 企業名を取得します。
     * @return 企業名
     */
    public String getCompanyName() {
        return companyName;
    }
    
    /**
     * 企業名を設定します。
     * @param companyName 企業名
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean getSentakuFlg() {
        return sentakuFlg;
    }
    /**
     * 選択フラグを設定します。
     * @param sentakuFlg 選択フラグ
     */
    public void setSentakuFlg(boolean sentakuFlg) {
        this.sentakuFlg = sentakuFlg;
    }
    
    
}
