/*
 * 作成日: 2006/11/29
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefCondDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefYMDto;

/**
 * 事業計画予算確認DTO
 * 
 * @author inazawa
 */
public class BudgetRefResultDto implements CsvOutputDto {
    /*結果リスト*/
    private List resultList;
    /*結果リスト*/
    private List resultMeisaiList;
    /*合計結果用List*/
    private List resultTotalList;
    /*会社*/
    private String companyNm;
    /*対象年度*/
    private String nendo;
    /*結果件数*/
    private int resultSize; 
    /*条件DTO*/
    private BudgetRefCondDto budgetRefCondDto;
    /*年度DTO*/
    private BudgetRefYMDto budgetRefYMDto;
    public void clear(){
        setCompanyNm(null);
        setNendo(null);
        setResultList(null);
        setResultMeisaiList(null);
        setResultTotalList(null);
        
    }
    /**
     * resultListを取得
     * @return resultList
     */
    public List getResultList() {
        return resultList;
    }
    /**
     * resultListをセット
     * @param resultList
     */
    public void setResultList(List resultList) {
        this.resultList = resultList;
    }
    /**
     * companyNmを取得
     * @return companyNm
     */
    public String getCompanyNm() {
        return companyNm;
    }
    /**
     * companyNmをセット
     * @param companyNm
     */
    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }
    /**
     * nendoを取得
     * @return nendo
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * nendoをセット
     * @param nendo
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }
    /**
     * resultMeisaiListを取得
     * @return resultMeisaiList
     */
    public List getResultMeisaiList() {
        return resultMeisaiList;
    }
    /**
     * resultMeisaiListを設定
     * @param resultMeisaiList
     */
    public void setResultMeisaiList(List resultMeisaiList) {
        this.resultMeisaiList = resultMeisaiList;
    }
    /**
     * resultTotalListを取得
     * @return resultTotalList
     */
    public List getResultTotalList() {
        return resultTotalList;
    }
    /**
     * resultTotalListを設定
     * @param resultTotalList
     */
    public void setResultTotalList(List resultTotalList) {
        this.resultTotalList = resultTotalList;
    }
    /**
     * resultSizeを取得
     * @return resultSize
     */
    public int getResultSize() {
        if(getResultList() == null){
            resultSize = 0;
        }else{
            resultSize = getResultList().size();
        }
        return resultSize;
    }
    public BudgetRefCondDto getBudgetRefCondDto() {
        return budgetRefCondDto;
    }
    public void setBudgetRefCondDto(BudgetRefCondDto budgetRefCondDto) {
        this.budgetRefCondDto = budgetRefCondDto;
    }
    public BudgetRefYMDto getBudgetRefYMDto() {
        return budgetRefYMDto;
    }
    public void setBudgetRefYMDto(BudgetRefYMDto budgetRefYMDto) {
        this.budgetRefYMDto = budgetRefYMDto;
    }
}
