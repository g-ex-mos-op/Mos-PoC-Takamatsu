/**
 * 作成日: 2017/04/01
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity;

public class CodKbCompanyJoho {

    public static final String TABLE = "VBM78COMR";

    public static final String kbCompanyCd_COLUMN = "KB_COMPANY_CD";

    public static final String kbCompanyName_COLUMN = "KB_COMPANY_NAME";

    /**
     * 会社コード
     */
    private String kbCompanyCd;

    /**
     * 会社名称
     */
    private String kbCompanyName;

    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getKbCompanyCd() {
        return kbCompanyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setKbCompanyCd(String kbCompanyCd) {
        this.kbCompanyCd = kbCompanyCd;
    }

    /**
     * 会社名を取得します。
     * @return kbCompanyName 会社名
     */
    public String getKbCompanyName() {
        return kbCompanyName;
    }
    /**
     * 会社名を設定します。
     * @param kbCompanyName 会社
     */
    public void setKbCompanyName(String kbCompanyName) {
        this.kbCompanyName = kbCompanyName;
    }


}
