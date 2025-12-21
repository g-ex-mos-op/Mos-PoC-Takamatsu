/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.svsearch.dto;

import java.util.List;

/**
 * SV検索処理Dto
 * 
 * @author kinugawa(ASPAC)
 */
public class SvSearchConditionDto {

    /* 会社コード */
    private String kaisyaCd;
    /* 支部コード */
    private String sibuCd;
    /* オーナ名 */
    private String onerNameKj;
    /* オーナコード */
    private String onerCd;
    /* 店名 */
    private String miseNameKj;
    /* 店コード */
    private String miseCd;
    /* 検索結果SVコード */
    private String resultSvCd;
    /* 検索結果SVリスト */
    private List searchSvList;
    
    private List companyList;
    /* sibuList */
    private List sibuList;
    /* ソートタイプ */
    private String sortSeq;

    /**
     * 会社コード設定処理
     * @return kaisyaCd
     */
    public String getKaisyaCd() {
		return kaisyaCd;
	}
    /**
     * 会社コード設定処理
     * @param kaisyaCd
     */
	public void setKaisyaCd(String kaisyaCd) {
		this.kaisyaCd = kaisyaCd;
	}
    /**
     * 支部コード設定処理
     * @return sibuCd
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コード設定処理
     * @param sibuCd
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    /**
     * 店コード設定処理
     * @return miseCd
     */
	public String getMiseCd() {
		return miseCd;
	}
    /**
     * 店コード設定処理
     * @param miseCd
     */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
    /**
     * 店名設定処理
     * @return miseNameKj
     */
	public String getMiseNameKj() {
		return miseNameKj;
	}
    /**
     * 店名設定処理
     * @param miseNameKj
     */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}
    /**
     * オーナコード設定処理
     * @return onerCd
     */
	public String getOnerCd() {
		return onerCd;
	}
    /**
     * オーナコード設定処理
     * @param onerCd
     */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
    /**
     * オーナ名設定処理
     * @return onerNameKj
     */
	public String getOnerNameKj() {
		return onerNameKj;
	}
    /**
     * オーナ名設定処理
     * @param onerNameKj
     */
	public void setOnerNameKj(String onerNameKj) {
		this.onerNameKj = onerNameKj;
	}
    /**
     * 結果会社コード設定処理
     * @return resultSvCd
     */
	public String getResultSvCd() {
		return resultSvCd;
	}
    /**
     * 結果会社コード設定処理
     * @param resultSvCd
     */
	public void setResultSvCd(String resultSvCd) {
		this.resultSvCd = resultSvCd;
	}
    /**
     * 検索結果リスト設定処理
     * @return searchSvList
     */
    public List getSearchSvList() {
        return searchSvList;
    }
    /**
     * 検索結果リスト設定処理
     * @param searchSvList
     */
    public void setSearchSvList(List searchSvList) {
        this.searchSvList = searchSvList;
    }
    /**
     * 検索結果リストサイズ設定処理
     * @return searchSvListSize
     */
    public int getSearchSvListSize() {
        return (searchSvList == null) ? 0 : searchSvList.size();
    }
    /**
     * 会社リスト設定
     * @return companyList
     */
    public List getCompanyList() {
        return companyList;
    }
    /**
     * 会社リスト設定
     * @param companyList
     */
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }
    /**
     * 支部リスト設定
     * @return sibuList
     */
    public List getSibuList() {
        return sibuList;
    }
    /**
     * 支部リスト設定
     * @param sibuList
     */
    public void setSibuList(List sibuList) {
        this.sibuList = sibuList;
    }
  
    /**
     * ソート順取得
     * @return
     */
    public String getSortSeq() {
        return this.sortSeq;
    }
    
    /**
     * ソート順設定
     * @param sortSeq
     */
    public void setSortSeq( String sortSeq ) {
        this.sortSeq = sortSeq;
    }
    
    /**
     * 検索情報クリア処理<br>
     * （パラメータfalse設定時、会社コード、会社リスト、支部リストはクリアしない）
     * @param flag
     */
    public void clear(boolean flag) {
        if (flag) {
            setKaisyaCd(null);
            setCompanyList(null);
            setSibuList(null);
        }
        setSibuCd(null);
        setMiseCd(null);
        setMiseNameKj(null);
        setOnerCd(null);
        setOnerNameKj(null);
        setResultSvCd(null);
        setSearchSvList(null);
        setSortSeq( null );
    }
    
}
