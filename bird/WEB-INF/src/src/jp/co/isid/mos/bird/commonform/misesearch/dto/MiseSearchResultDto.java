/*
 * çÏê¨ì˙: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.misesearch.dto;

import java.util.List;

/**
 * @author xyuchida
 *
 */
public class MiseSearchResultDto {

    private List miseSearchList;
    private List companyList;
    private List sibuList;

	public List getMiseSearchList() {
		return miseSearchList;
	}

	public void setMiseSearchList(List miseSearchList) {
		this.miseSearchList = miseSearchList;
	}

	public int getMiseSearchListSize() {
		return (getMiseSearchList() == null) ? 0 : getMiseSearchList().size();
	}

	public List getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }

	public List getSibuList() {
		return sibuList;
	}

	public void setSibuList(List sibuList) {
		this.sibuList = sibuList;
	}

	public void clear() {
		setMiseSearchList(null);
		setCompanyList(null);
		setSibuList(null);
	}
}
