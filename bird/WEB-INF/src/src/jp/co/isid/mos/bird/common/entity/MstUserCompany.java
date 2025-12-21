/**
 * 
 */
package jp.co.isid.mos.bird.common.entity;

/**
 * @author xyuchida
 *
 */
public class MstUserCompany {

    public static final String TABLE = "BM03USCP";

    public static final String userId_COLUMN = "USER_ID";

    public static final String zokuseiKbn_COLUMN = "ZOKUSEI_KBN";

    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String companyName_COLUMN = "COMPANY_NAME";

    public static final String sortSeq_COLUMN = "SORT_SEQ";

    /**
     * ユーザID
     */
    private String userId;
    /**
     * 属性区分
     */
    private String zokuseiKbn;
    /**
     * 会社コード
     */
    private String companyCd;
    /**
     * 会社名称
     */
    private String companyName;
    /**
     * ソート順
     */
    private String sortSeq;

    /**
     * @return userId を戻します。
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId 設定する userId。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return zokuseiKbn を戻します。
     */
    public String getZokuseiKbn() {
        return zokuseiKbn;
    }
    /**
     * @param zokuseiKbn 設定する zokuseiKbn。
     */
    public void setZokuseiKbn(String zokuseiKbn) {
        this.zokuseiKbn = zokuseiKbn;
    }
    /**
     * @return companyCd を戻します。
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * @param companyCd 設定する companyCd。
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * @return companyName を戻します。
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * @param companyName 設定する companyName。
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**
     * @return sortSeq を戻します。
     */
    public String getSortSeq() {
        return sortSeq;
    }
    /**
     * @param sortSeq 設定する sortSeq。
     */
    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
    }

    /**
     * 会社コード + 会社名称取得
     * @return 会社コード + 会社名称
     */
    public String getCompanyCdAndName() {
        String companyCd = (getCompanyCd() == null) ? "" : getCompanyCd();
        String companyName = (getCompanyName() == null) ? "" : getCompanyName();
        return companyCd + " " + companyName;
    }
}
