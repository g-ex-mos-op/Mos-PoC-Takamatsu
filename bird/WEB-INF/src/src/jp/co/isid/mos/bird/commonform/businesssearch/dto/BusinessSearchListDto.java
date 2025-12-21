/*
 * 作成日: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.businesssearch.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xyuchida
 *
 */
public class BusinessSearchListDto {

    /**
     * 選択中管理会社企業コード
     */
    private String selectedCompanyCd;

    /**
     * 管理会社リスト
     */
    private List companyList;

    /**
     * 業態リスト
     */
    private List gyotaiList;

    /**
     * 結果業態区分セット
     */
    private Set resultGyotaiKbnSet = new HashSet();

    /**
     * 選択中管理会社企業コードを取得します。
     *
     * @return 選択中管理会社企業コード
     */
    public String getSelectedCompanyCd() {
        return selectedCompanyCd;
    }
    /**
     * 選択中管理会社企業コードを設定します。
     *
     * @param selectedCompanyCd 選択中管理会社企業コード
     */
    public void setSelectedCompanyCd(String selectedCompanyCd) {
        this.selectedCompanyCd = selectedCompanyCd;
    }

    /**
     * 管理会社リストを取得します。
     *
     * @return 管理会社リスト
     */
    public List getCompanyList() {
        return companyList;
    }
    /**
     * 管理会社リストを設定します。
     *
     * @param companyList 管理会社リスト
     */
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }

    /**
     * 業態リストを取得します。
     *
     * @return 業態リスト
     */
    public List getGyotaiList() {
        return gyotaiList;
    }
    /**
     * 業態リストを設定します。
     *
     * @param gyotaiList 業態リスト
     */
    public void setGyotaiList(List gyotaiList) {
        this.gyotaiList = gyotaiList;
    }

    /**
     * 選択中管理会社企業コードを取得します。
     *
     * @return 選択中管理会社企業コード
     */
    public Set getResultGyotaiKbnSet() {
        return resultGyotaiKbnSet;
    }
    /**
     * 選択中管理会社企業コードを設定します。
     *
     * @param selectedCompanyCd 選択中管理会社企業コード
     */
    public void setResultGyotaiKbnSet(Set resultGyotaiKbnSet) {
        this.resultGyotaiKbnSet = resultGyotaiKbnSet;
    }
}
