/*
 * 作成日: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;


/**
 * @author Noh
 */
public class RequestDto implements CsvOutputDto {
    
    /* 条件画面表示モード 非表示:0、表示:1 */
    private int selectViewMode;

    /* 会社コード */
    private String companyCd;
    
    /* 支部(取込)コード */
    private String areaDai;
    
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
    
    /* エントリー回 */
    private String entryKai;
    
    /* エントリー選択　0:最新回　1:年度指定 */
    private int entryFlg;

    /* 検索結果：スタッフ情報の検索取得 */
    private List listStaff = new ArrayList(0);
    
    /* 検索結果：マスタライセンス結果人数情報リスト */
    private List listStaffCnt = new ArrayList(0);

    /**
	 * 条件画面表示モードの設定
	 * 非表示:0、表示:1
	 * @return selectViewMode を戻します。
	 */
	public int getSelectViewMode() {
		return selectViewMode;
	}
	/**
	 * 条件画面表示モードの設定
	 * 非表示:0、表示:1
	 * @param selectViewMode selectViewMode を設定。
	 */
	public void setSelectViewMode(int selectViewMode) {
		this.selectViewMode = selectViewMode;
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
	 * @return areaDai を戻します。
	 */
	public String getAreaDai() {
		return areaDai;
	}
	/**
	 * @param areaDai areaDai を設定。
	 */
	public void setAreaDai(String sitenCd) {
		this.areaDai = sitenCd;
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
	public String getEntryKai() {
		return entryKai;
	}
	/**
	 * @param entryKai entryKai を設定。
	 */
	public void setEntryKai(String entryKai) {
		this.entryKai = entryKai;
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

	public List getListStaff() {
		return listStaff;
	}
	public void setListStaff(List listStaff) {
		this.listStaff = listStaff;
	}
	public List getListStaffCnt() {
		return listStaffCnt;
	}
	public void setListStaffCnt(List listStaffCnt) {
		this.listStaffCnt = listStaffCnt;
	}
	public void clear(){
		setCompanyCd(null);
		setEntryKai(null);
		setEntryYear(null);
		setMiseCd(null);
		setOnerCd(null);
		setListStaff(null);
		setListStaffCnt(null);

		setAreaDai(null);
	}
	public void settingSearchData(MlResultStatusRefDto sessonDto) {
		sessonDto.setCompanyCd(getCompanyCd());
		sessonDto.setSitenCd(getAreaDai());
		sessonDto.setOnerCd(getOnerCd());
		sessonDto.setMiseCd(getMiseCd());
		sessonDto.setSelectFlg(getSelectFlg());
		sessonDto.setEntryFlg(getEntryFlg());
		sessonDto.setEntryYear(getEntryYear());
		sessonDto.setResultList(getListStaff());
		sessonDto.setResultNumList(getListStaffCnt());

	}
}
