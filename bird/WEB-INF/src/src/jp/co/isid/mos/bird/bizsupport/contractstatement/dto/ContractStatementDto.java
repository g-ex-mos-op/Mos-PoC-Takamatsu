/*
 * 作成日2007/07/05
 */
package jp.co.isid.mos.bird.bizsupport.contractstatement.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * 特殊契約店計算書DTO
 * @author xnkusama
 *
 */
public class ContractStatementDto implements DownloadDto {

    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */ 
    private String onerCd;
    
    /**
     * 計算書リスト
     */
    private List listData;

    /**
     * 計算書全データリスト
     */
    private List listAllData;
    
    /**
     * ダウンロードIndex
     */
    private int selectedIndex;
    /**
     * カテゴリIndex
     */
    private int categoryIndex;
    

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public List getListData() {
        return listData;
    }

    public void setListData(List listData) {
        this.listData = listData;
    }

    public String getOnerCd() {
        return onerCd;
    }

    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    public int getCntCategory() {
        return getListData() == null ? 0 : getListData().size();
    }

    public boolean isExistListData() {
        return listData == null || listData.isEmpty() ? false : true;
    }

    public List getListAllData() {
        return listAllData;
    }

    public void setListAllData(List listAllData) {
        this.listAllData = listAllData;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
    }
}