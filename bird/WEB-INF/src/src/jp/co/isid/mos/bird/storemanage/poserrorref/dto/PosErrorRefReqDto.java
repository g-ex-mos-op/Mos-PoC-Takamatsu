/*
 * 作成日:2007/02/16
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

public class PosErrorRefReqDto implements CsvOutputDto {
    
    /**
     * ウインドウID
     */
    private int windowId;
    
    /**
     * 会社コード
     */
    private String selectedCompanyCd;
    
    /**
     * 会社名称
     */
    private String companyName;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    /**
     * 集信日
     */
    private String shuDt;
    
    /**
     * 表示用集信日
     */
    private String dispShuDt;

    /**
     * システム日付
     */
    private String sysDate;
    
    /**
     * アプリ日付
     */
    private String appDate;
    
    /**
     * 集信エラー店舗情報
     */
    private List shushinErrorInfo;
    
    /**
     * ボタン名称フラグ
     */
    private boolean btnNameFlg;
    
    /**
     * ユーザータイプコード
     */
    private String userTypeCd;
    
    /**
     * 検索フラグ
     */
    private int referenceFlag;
    

    public String getUserTypeCd() {
        return userTypeCd;
    }

    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    public boolean isBtnNameFlg() {
        return btnNameFlg;
    }

    public void setBtnNameFlg(boolean btnNameFlg) {
        this.btnNameFlg = btnNameFlg;
    }

    public String getSelectedCompanyCd() {
        return selectedCompanyCd;
    }

    public void setSelectedCompanyCd(String selectedCompanyCd) {
        this.selectedCompanyCd = selectedCompanyCd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDispShuDt() {
        return dispShuDt;
    }

    public void setDispShuDt(String dispShuDt) {
        this.dispShuDt = dispShuDt;
    }

    public String getMiseCd() {
        return miseCd;
    }

    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    public String getOnerCd() {
        return onerCd;
    }

    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    public String getShuDt() {
        return shuDt;
    }

    public void setShuDt(String shuDt) {
        this.shuDt = shuDt;
    }

    public List getShushinErrorInfo() {
        return shushinErrorInfo;
    }

    public void setShushinErrorInfo(List shushinErrorInfo) {
        this.shushinErrorInfo = shushinErrorInfo;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public int getWindowId() {
        return windowId;
    }

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public int getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(int referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

}