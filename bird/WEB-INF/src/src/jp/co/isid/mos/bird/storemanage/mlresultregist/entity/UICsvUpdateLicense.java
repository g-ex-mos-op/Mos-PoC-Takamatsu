/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

/**
 * ライセンス更新CSVレコード
 * 
 * @author xyuchida
 */
public class UICsvUpdateLicense implements UICsvRecord {

    /**
     * ライセンス番号
     */
    private String licenseNo;

    /**
     * ライセンス更新年月
     */
    private String licenseUpDt;

    /**
     * CSVレコードエラー情報
     */
    private UICsvErrorInfo uiCsvErrorInfo = new UICsvErrorInfo();

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
     * @return licenseUpDt を戻します。
     */
    public String getLicenseUpDt() {
        return licenseUpDt;
    }
    /**
     * @param licenseUpDt licenseUpDt を設定。
     */
    public void setLicenseUpDt(String licenseUpDt) {
        this.licenseUpDt = licenseUpDt;
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
