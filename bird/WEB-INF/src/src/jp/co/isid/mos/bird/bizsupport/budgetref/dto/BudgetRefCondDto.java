/*
 * 作成日: 2006/11/29
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * 事業計画予算確認DTO
 * 
 * @author inazawa
 */
public class BudgetRefCondDto implements CsvOutputDto {
    /**
     * ユーザーID
     */
    private String userId;
    /**
     * ユーザータイプ
     */
    private String userTypeCd;
    /**
     * システム日付
     */
    private String sysDate;
    /**
     * List対象年度
     */
    private List listTaishoNendo; 
    /**
     * List対象会社
     */
    private List listCompany; 
    /**
     * 年度
     */
    private String nendo;
    /**
     * 会社コード
     */
    private String companyCd = "";
    /**
     * 会社名
     */
    private String companyNm;
    /**
     * 再検索判断
     */
    private String reExec;
    /**
     * クリア処理
     *
     */
    public void clear(){
        setListTaishoNendo(null);
        setListCompany(null);
        setReExec(null);
        setCompanyCd(null);
        setCompanyNm(null);
        setNendo(null);
    }
    /**
     * userIdの取得
     * @return userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * userIdの設定
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * userTypeCdの取得
     * @return userTypeCd
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }
    /**
     * userTypeCdの設定
     * @param userTypeCd
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    /**
     * sysDateの取得
     * @return sysDate
     */
    public String getSysDate() {
        return sysDate;
    }
    /**
     * sysDateの設定
     * @param sysDate
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
    /**
     * taishoNendoを取得
     * @return taishoNendo
     */
    public List getListTaishoNendo() {
        return (List) listTaishoNendo;
    }
    /**
     * taishoNendoを設定
     * @param taishoNendo
     */
    public void setListTaishoNendo(List listTaishoNendo) {
        this.listTaishoNendo =  listTaishoNendo;
    }
    /**
     * listCompanyを取得
     * @return listCompany
     */
    public List getListCompany() {
        return (List) listCompany;
    }
    /**
     * listCompanyを設定
     * @param listCompany
     */
    public void setListCompany(List listCompany) {
        this.listCompany =  listCompany;
    }
    /**
     * nendoを取得
     * @return nendo
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * nendoを設定
     * @param nendo
     */
    public void setNendo(String nendo) {
        this.nendo =  nendo;
    }
    /**
     * companyCdを取得
     * @return companyCd
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * companyCdを設定
     * @param companyCd
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * companyNmを取得
     * @return companyNm
     */
    public String getCompanyNm() {
        return companyNm;
    }
    /**
     * companyNmを設定
     * @param companyNm
     */
    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }
    /**
     * reExecを取得
     * @return reExec
     */
    public String getReExec() {
        return reExec;
    }
    /**
     * reExecを設定
     * @param reExec
     */
    public void setReExec(String reExec) {
        this.reExec = reExec;
    }

}
