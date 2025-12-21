package jp.co.isid.mos.bird.bizsupport.spot.dto;

import java.util.List;



/**
 * 結果部DTOクラス
 *
 * @author xsong
 */
public class SpotResultDataDto {
	
	
	/** キャンペーン開始日 */
	private String cmpStartDate;
	
	/** キャンペーン終了日 */
	private String cpmEndDate;
	
	/** POS発注開始日 */
	private String posStartDate;
	
	/** POS発注終了日 */
	private String posEndDate;
	
	/** 作成日 */
	private String sakuseiDate;
	
	/** 店舗データList */
	private List resultTable;
		
	/** 集信エラーフラグ1 */
    private String flg1;
	
    /** 集信エラーフラグ2 */
	private String flg2;
	
	/** 集信エラーフラグ3 */
	private String flg3;
	
	/** 店舗名称1 */
	private String tenName1;
	
	/** 店舗名称2 */
	private String tenName2;
	
	/** 店舗名称3 */
	private String tenName3;

	/**
	 * キャンペーン開始日を取得します。
	 * @return　POS発注開始日
	 */
	public String getCmpStartDate() {
		return cmpStartDate;
	}

	/**
	 * キャンペーン開始日を設定します。
	 * @param cmpStartDate　キャンペーン開始日
	 */
	public void setCmpStartDate(String cmpStartDate) {
		this.cmpStartDate = cmpStartDate;
	}

	/**
	 * キャンペーン開始日を取得します。
	 * @return　cpmEndDate　キャンペーン開始日
	 */
	public String getCpmEndDate() {
		return cpmEndDate;
	}

	/**
	 * キャンペーン終了日を設定します。 
	 * @param cpmEndDate　キャンペーン終了日 
	 */
	public void setCpmEndDate(String cpmEndDate) {
		this.cpmEndDate = cpmEndDate;
	}

	/**
	 * POS発注終了日を設定します。
	 * @return posEndDate　POS発注終了日
	 */
	public String getPosEndDate() {
		return posEndDate;
	}

	/**
	 * POS発注終了日を設定します。
	 * @param posEndDate POS発注終了日
	 */
	public void setPosEndDate(String posEndDate) {
		this.posEndDate = posEndDate;
	}

	/**
	 * POS発注開始日を取得します。
	 * @return posStartDate　POS発注開始日
	 */
	public String getPosStartDate() {
		return posStartDate;
	}

	/**
	 * POS発注開始日を設定します。
	 * @param posStartDate POS発注開始日
	 */
	public void setPosStartDate(String posStartDate) {
		this.posStartDate = posStartDate;
	}

	/**
	 * 作成日を取得します。
	 * @return sakuseiDate　作成日
	 */
	public String getSakuseiDate() {
		return sakuseiDate;
	}

	/**
	 * 作成日を設定します。
	 * @param sakuseiDate　作成日
	 */
	public void setSakuseiDate(String sakuseiDate) {
		this.sakuseiDate = sakuseiDate;
	}

	/**
	 * 店舗データListを取得します。
	 * @return　resultTable 店舗データList
	 */
	public List getResultTable() {
		return resultTable;
	}

	/**
	 * 店舗データListを設定します。
	 * @param resultTable　店舗データList
	 */
	public void setResultTable(List resultTable) {
		this.resultTable = resultTable;
	}
	
	/**
	 * 集信エラーフラグ1を返します
	 * @return flg1　集信エラーフラグ1
	 */
	public String getFlg1() {
		return flg1;
	}

	/**
	 * 集信エラーフラグ1を設定します。
	 * @param flg1 集信エラーフラグ1
	 */
	public void setFlg1(String flg1) {
		this.flg1 = flg1;
	}

	/**
	 * 集信エラーフラグ２を取得します。
	 * @return flg2　集信エラーフラグ２
	 */
	public String getFlg2() {
		return flg2;
	}

	/**
	 * 集信エラーフラグ２を設定します。
	 * @param flg2 集信エラーフラグ２
	 */
	public void setFlg2(String flg2) {
		this.flg2 = flg2;
	}

	/**
	 * 集信エラーフラグ３を取得します。
	 * @return flg3　集信エラーフラグ３
	 */
	public String getFlg3() {
		return flg3;
	}

	/**
	 * 集信エラーフラグ３を設定します。
	 * @param flg3 集信エラーフラグ３
	 */
	public void setFlg3(String flg3) {
		this.flg3 = flg3;
	}

	/**
	 * 集信エラーフラグ1を取得します。
	 * @return tenName1　集信エラーフラグ1
	 */
	public String getTenName1() {
		
		return tenName1;
	}

	/**
	 * 集信エラーフラグ1を設定します。
	 * @param tenName1 集信エラーフラグ1
	 */
	public void setTenName1(String tenName1) {
		this.tenName1 = tenName1;
	}

	/**
	 * 集信エラーフラグ２を取得します。
	 * @return tenName2　集信エラーフラグ２
	 */
	public String getTenName2() {
		return tenName2;
	}

	/**
	 * 集信エラーフラグ２を設定します。
	 * @param tenName2 集信エラーフラグ２
	 */
	public void setTenName2(String tenName2) {
		this.tenName2 = tenName2;
	}

	/**
	 * 集信エラーフラグ３を取得します。
	 * @return tenName3　集信エラーフラグ３
	 */
	public String getTenName3() {
		return tenName3;
	}

	/**
	 * 集信エラーフラグ３を設定します。
	 * @param tenName3 集信エラーフラグ３
	 */
	public void setTenName3(String tenName3) {
		this.tenName3 = tenName3;
	}
	
		
}