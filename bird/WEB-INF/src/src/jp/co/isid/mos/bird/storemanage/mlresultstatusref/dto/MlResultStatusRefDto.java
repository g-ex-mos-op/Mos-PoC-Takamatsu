/*
 * 作成日: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;


/**
 * @author Noh
 */
public class MlResultStatusRefDto implements CsvOutputDto {
	/**ユーザタイプコード*/
    private String userTypeCd;
    /* 条件画面表示モード 非表示:0、表示:1 */
    private int selectViewMode;

    /* 会社コード */
    private String companyCd;
    
    /* 本部コード */
    private String sitenCd;
    
    /* オーナーコード */
    private String onerCd;
    
    /* 店舗コード */
    private String miseCd;
    
    /* 選択Flage 0:本部　1:オーナー　2:店舗　3:全て  */
    private int selectFlg; 
    
    /* 現在の年度 */
    private String bandleYear1;
    
    /* 現在の年度 */
    private String bandleYear2;
    
    /* 現在の年度 */
    private String bandleYear3;
    
    /* エントリー年 */
    private String entryYear;
       
    /* エントリー選択　0:最新回　1:年度指定 */
    private int entryFlg;
    
    /* スタッフ情報の検索取得 */
    private List resultList = new ArrayList(0);
    
    /* エントリー年リスト */
    private List entryYearList = new ArrayList(0);
    
    /* エントリリスト */
    private List entryList = new ArrayList(0);
    
    /* マスタライセンス結果人数情報リスト */
    private List resultNumList = new ArrayList(0);
    
    /* 会社プルダウン用リスト */
    private List listCompany = new ArrayList(0);
    
    /* 支部プルダウン用リスト */
    private List sibuList = new ArrayList(0);
    
    /* 実施時期リスト */
    private List kikanList = new ArrayList(0);
	
    /* 最新回 */
    private String entryYearKai;

    /* 最新エントリー年 */
    private String latestEntryYear;
    /* 最新エントリー回 */
    private String latestEntryKai;

    /**
	 * 条件画面表示モードの設定
	 * @return selectViewMode を戻します。
	 */
	public int getSelectViewMode() {
		return selectViewMode;
	}
	/**
	 * 条件画面表示モードの設定
	 * @param selectViewMode selectViewMode を設定。
	 */
	public void setSelectViewMode(int selectViewMode) {
		this.selectViewMode = selectViewMode;
	}
	
    /**
	 * 最新回の設定
	 * @return entryYearKai を戻します。
	 */
	public String getEntryYearKai() {
		return entryYearKai;
	}
	/**
	 * 最新回の設定
	 * @param entryYearKai entryYearKai を設定。
	 */
	public void setEntryYearKai(String entryYearKai) {
		this.entryYearKai = entryYearKai;
	}
	
	/**
	 * 最新エントリー年の設定
	 * @return latestEntryYear を戻します。
	 */
	public String getLatestEntryYear() {
		return latestEntryYear;
	}
	/**
	 * 最新エントリー年の設定
	 * @param latestEntryYear latestEntryYear を設定。
	 */
	public void setLatestEntryYear(String latestEntryYear) {
		this.latestEntryYear = latestEntryYear;
	}

	
	/**
	 * @return kikanList を戻します。
	 */
	public List getKikanList() {
		return kikanList;
	}
	/**
	 * @param kikanList kikanList を設定。
	 */
	public void setKikanList(List kikanList) {
		this.kikanList = kikanList;
	}
	/**
	 * @param kikanList kikanList を設定。
	 */
	public int getKikanListSize() {
		return getKikanList().size();
	}
	/**
	 * @return sibuList を戻します。
	 */
	public List getSibuList() {
		return sibuList;
	}
	/**
	 * @param sibuList sibuList を設定。
	 */
	public void setSibuList(List sibuList) {
		this.sibuList = sibuList;
	}
	/**
	 * @return bandleYear1 を戻します。
	 */
	public String getBandleYear1() {
		return bandleYear1;
	}
	/**
	 * @param bandleYear1 bandleYear1 を設定。
	 */
	public void setBandleYear1(String bandleYear1) {
		this.bandleYear1 = bandleYear1;
	}
	/**
	 * @return bandleYear2 を戻します。
	 */
	public String getBandleYear2() {
		return bandleYear2;
	}
	/**
	 * @param bandleYear2 bandleYear2 を設定。
	 */
	public void setBandleYear2(String bandleYear2) {
		this.bandleYear2 = bandleYear2;
	}
	/**
	 * @return bandleYear3 を戻します。
	 */
	public String getBandleYear3() {
		return bandleYear3;
	}
	/**
	 * @param bandleYear3 bandleYear3 を設定。
	 */
	public void setBandleYear3(String bandleYear3) {
		this.bandleYear3 = bandleYear3;
	}
	/**
	 * @return resultNumList を戻します。
	 */
	public List getResultNumList() {
		return resultNumList;
	}
	/**
	 * @param resultNumList resultNumList を設定。
	 */
	public void setResultNumList(List resultNumList) {
		this.resultNumList = resultNumList;
	}
	/**
	 * @return entryList を戻します。
	 */
	public List getEntryList() {
		return entryList;
	}
	/**
	 * @param entryList entryList を設定。
	 */
	public void setEntryList(List entryList) {
		this.entryList = entryList;
	}
	
	/**
	 * @return resultList を戻します。
	 */
	public List getResultList() {
		return resultList;
	}
	/**
	 * @param resultList resultList を設定。
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	/**
	 * @return sitenCd を戻します。
	 */
	public String getSitenCd() {
		return sitenCd;
	}
	/**
	 * @param sitenCd sitenCd を設定。
	 */
	public void setSitenCd(String sitenCd) {
		this.sitenCd = sitenCd;
	}
	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * @param miseCd miseCd を設定。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
	 * @return onerCd を戻します。
	 */
	public String getOnerCd() {
		return onerCd;
	}
	/**
	 * @param onerCd onerCd を設定。
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
	/**
	 * @return selectFlg を戻します。
	 */
	public int getSelectFlg() {
		return selectFlg;
	}
	/**
	 * @param selectFlg selectFlg を設定。
	 */
	public void setSelectFlg(int selectFlg) {
		this.selectFlg = selectFlg;
	}
	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return entryFlg を戻します。
	 */
	public int getEntryFlg() {
		return entryFlg;
	}
	/**
	 * @param entryFlg entryFlg を設定。
	 */
	public void setEntryFlg(int entryFlg) {
		this.entryFlg = entryFlg;
	}
	/**
	 * @return entryKai を戻します。
	 */
	public String getLatestEntryKai() {
		return latestEntryKai;
	}
	/**
	 * @param entryKai entryKai を設定。
	 */
	public void setLatestEntryKai(String entryKai) {
		this.latestEntryKai = entryKai;
	}
	/**
	 * @return entryYear を戻します。
	 */
	public String getEntryYear() {
		return entryYear;
	}
	/**
	 * @param entryYear entryYear を設定。
	 */
	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}
	/**
	 * @return entryYearList を戻します。
	 */
	public List getEntryYearList() {
		return entryYearList;
	}
	/**
	 * @param entryYearList entryYearList を設定。
	 */
	public void setEntryYearList(List entryYearList) {
		this.entryYearList = entryYearList;
	}
    
    public void clear(){
		setCompanyCd(null);
		setEntryList(null);
		setEntryYear(null);
		setEntryYearList(null);
		setLatestEntryYear(null);
		setLatestEntryKai(null);
		setEntryYearList(null);
		setMiseCd(null);
		setOnerCd(null);
		setResultList(null);
		setResultNumList(null);

		setSitenCd(null);
	}
	public String getUserTypeCd() {
		return userTypeCd;
	}
	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
	}
    public void clearSearchData(){
		setResultList(null);
		setResultNumList(null);

	}
	public List getListCompany() {
		return listCompany;
	}
	public void setListCompany(List listCompany) {
		this.listCompany = listCompany;
	}
	/**
	 * 会社名称取得処理
	 * @return
	 */
	public String getCompanyName() {
		if(getListCompany() != null && getCompanyCd() != null) {
			for(int i=0; i<getListCompany().size(); i++) {
				CodCompany entity = (CodCompany)getListCompany().get(i);
				if(getCompanyCd().equals(entity.getCompanyCd())){
					return entity.getCompanyName();
				}
			}
		}
		return null;
	}
}
