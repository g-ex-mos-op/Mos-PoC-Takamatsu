package jp.co.isid.mos.bird.bizsupport.energyamount.dto;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * エネルギー使用量ダウンロード リクエストDTO
 * @
 * @author xnkusama
 *
 */
public class EnergyAmountRequestDto implements CsvOutputDto {

    /**検索条件*/
    //会社コード
    private String companyCd;
    //対象データ
    private String taishoData;
    //対象条件
    private String taishoJoken;
    //対象期間
    private String taishoKikan;
    //対象期間（未入力店舗一覧）
    private String taishoKikanNoinput;
    //対象年月
    private String taishoNengetu;
    //対象年度
    private String taishoNendo;
    //対象年月（未入力店舗一覧）
    private String taishoNengetuNoinput;
    //メータ区分
    private String meterKbn;
    //メータ区分（未入力店舗一覧）
    private String meterKbnNoinput;
    //ユーザータイプコード
    private String userTypeCd;
    //ユーザーID
    private String userId;
    //制限フラグ
    private boolean limitFlg;
    
    //システム日付
    private String sysDate;
    
    
    public String getMeterKbn() {
        return meterKbn;
    }
    public void setMeterKbn(String meterKbn) {
        this.meterKbn = meterKbn;
    }
    public String getTaishoData() {
        return taishoData;
    }
    public void setTaishoData(String taishoData) {
        this.taishoData = taishoData;
    }
    public String getTaishoJoken() {
        return taishoJoken;
    }
    public void setTaishoJoken(String taishoJoken) {
        this.taishoJoken = taishoJoken;
    }
    public String getTaishoKikan() {
        return taishoKikan;
    }
    public void setTaishoKikan(String taishoKikan) {
        this.taishoKikan = taishoKikan;
    }
    public String getTaishoNendo() {
        return taishoNendo;
    }
    public void setTaishoNendo(String taishoNendo) {
        this.taishoNendo = taishoNendo;
    }
    public String getTaishoNengetu() {
        return taishoNengetu;
    }
    public void setTaishoNengetu(String taishoNengetu) {
        this.taishoNengetu = taishoNengetu;
    }
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getTaishoNengetuNoinput() {
        return taishoNengetuNoinput;
    }
    public void setTaishoNengetuNoinput(String taishoNengetuNoinput) {
        this.taishoNengetuNoinput = taishoNengetuNoinput;
    }
    public String getTaishoKikanNoinput() {
        return taishoKikanNoinput;
    }
    public void setTaishoKikanNoinput(String taishoKikanNoinput) {
        this.taishoKikanNoinput = taishoKikanNoinput;
    }
    public String getMeterKbnNoinput() {
        return meterKbnNoinput;
    }
    public void setMeterKbnNoinput(String meterKbnNoinput) {
        this.meterKbnNoinput = meterKbnNoinput;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserTypeCd() {
        return userTypeCd;
    }
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    public String getSysDate() {
        return sysDate;
    }
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
    public boolean isLimitFlg() {
        return limitFlg;
    }
    public void setLimitFlg(boolean limitFlg) {
        this.limitFlg = limitFlg;
    }
    
}
