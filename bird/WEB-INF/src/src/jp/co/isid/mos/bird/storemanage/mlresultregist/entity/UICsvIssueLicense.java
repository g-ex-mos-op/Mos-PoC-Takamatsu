/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

/**
 * ライセンス発行CSVレコード
 * 
 * @author xyuchida
 */
public class UICsvIssueLicense implements UICsvRecord {

    /**
     * 受験番号
     */
    private String examNo;

    /**
     * ライセンス番号
     */
    private String licenseNo;

    /**
     * CSVレコードエラー情報
     */
    private UICsvErrorInfo uiCsvErrorInfo = new UICsvErrorInfo();

    /**
     * @return examNo を戻します。
     */
    public String getExamNo() {
        return examNo;
    }
    /**
     * @param examNo examNo を設定。
     */
    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }
    /**
     * @return licenseNo を戻します。
     */
    public String getLicenseNo() {
        return licenseNo;
    }
    /**
     * @param licenseNo licenseNo を設定。
     */
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
    /**
     * @return uiCsvErrorInfo を戻します。
     */
    public UICsvErrorInfo getUiCsvErrorInfo() {
        return uiCsvErrorInfo;
    }
    /**
     * @param uiCsvErrorInfo uiCsvErrorInfo を設定。
     */
    private void setUiCsvErrorInfo(UICsvErrorInfo uiCsvErrorInfo) {
        this.uiCsvErrorInfo = uiCsvErrorInfo;
    }
}
