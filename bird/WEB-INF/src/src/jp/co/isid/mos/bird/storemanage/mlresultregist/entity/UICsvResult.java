/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

/**
 * 受験結果CSVレコード
 * 
 * @author xyuchida
 */
public class UICsvResult implements UICsvRecord {

    /**
     * 受験番号
     */
    private String examNo;

    /**
     * 総合結果
     */
    private String totalResult;

    /**
     * 筆記テスト結果
     */
    private String sub2Result;

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
     * @return totalResult を戻します。
     */
    public String getTotalResult() {
        return totalResult;
    }
    /**
     * @param totalResult totalResult を設定。
     */
    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }
    /**
     * @return sub2Result を戻します。
     */
    public String getSub2Result() {
        return sub2Result;
    }
    /**
     * @param sub2Result sub2Result を設定。
     */
    public void setSub2Result(String sub2Result) {
        this.sub2Result = sub2Result;
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
