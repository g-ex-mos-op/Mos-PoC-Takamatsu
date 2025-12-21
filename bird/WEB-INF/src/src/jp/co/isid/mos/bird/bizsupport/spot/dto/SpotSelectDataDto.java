package jp.co.isid.mos.bird.bizsupport.spot.dto;

import java.util.List;


/**
 * 条件部DTOクラス
 *
 * @author xsong
 */
public class SpotSelectDataDto {
	
    /** ボタン表示用フラグ  */	
	private boolean dispFlg;
	
	/** 結果表示用フラグ */
	private boolean dataDispFlag;
	
	/** 対象キャンペンプルダウンデータList */
	private List cmpDataList;
	
	/** 支部プルダウンデータList */
	private List sibuDataList;
	
	/** 選択されたキャンペーンNo */
	private String cmpDataSelectVal;
	
	/** 選択された支部コード */
	private String sibuDataSelectVal;
		
  
    /**
     * 対象キャンペンプルダウンデータListを取得します。
     * @return cmpDataList　対象キャンペンプルダウンデータList
     */
	public List getCmpDataList() {
		return cmpDataList;
	}
    
	/**
	 * 対象キャンペンプルダウンデータListを設定します。
	 * @param cmpDataList　対象キャンペンプルダウンデータList
	 */
	public void setCmpDataList(List cmpDataList) {
		this.cmpDataList = cmpDataList;
	}

	/**
	 * 支部プルダウンデータListを取得します。
	 * @return　sibuDataList 支部プルダウンデータList
	 */
	public List getSibuDataList() {
		return sibuDataList;
	}

	/**
	 * 支部プルダウンデータListを設定します。
	 * @param sibuDataList　支部プルダウンデータList
	 */
	public void setSibuDataList(List sibuDataList) {
		this.sibuDataList = sibuDataList;
	}

	/**
	 * 対象キャンペンプルダウンデータListのサイズを取得します。
	 * @return　対象キャンペンプルダウンデータListのサイズ
	 */
	public int getCmpDataListSize() {
		
		return (cmpDataList == null || cmpDataList.isEmpty()) ? 0 : cmpDataList.size();  
	}

	/**
	 * 対象キャンペンプルダウンデータListのサイズを設定します。
	 * @param cmpDataListSize　対象キャンペンプルダウンデータListのサイズ
	 */
	public void setCmpDataListSize(int cmpDataListSize) {
	}

	/**
	 * ボタン表示用フラグを取得します。
	 * @return false: 実行 / true: 再建策 
	 */
	public boolean isDispFlg() {
		return dispFlg;
	}

	/**
	 * ボタン表示用フラグを設定します。
	 * @param dispFlg　ボタン表示用フラグ
	 */
	public void setDispFlg(boolean dispFlg) {
		this.dispFlg = dispFlg;
	}

	/**
	 * 選択されたキャンペーンNoを取得します。
	 * @return cmpDataSelectVal 選択されたキャンペーンNo
	 */
	public String getCmpDataSelectVal() {
		return cmpDataSelectVal;
	}

	/**
	 * 選択されたキャンペーンNoを設定します。
	 * @param cmpDataSelectVal　選択されたキャンペーンNo
	 */
	public void setCmpDataSelectVal(String cmpDataSelectVal) {
		this.cmpDataSelectVal = cmpDataSelectVal;
	}

	/**
	 * 選択された支部コードを取得します。
	 * @return  選択された支部コード
	 */
	public String getSibuDataSelectVal() {
		return sibuDataSelectVal;
	}

	/**
	 * 選択された支部コードを設定します。
	 * @param sibuDataSelectVal　選択された支部コード
	 */
	public void setSibuDataSelectVal(String sibuDataSelectVal) {
		this.sibuDataSelectVal = sibuDataSelectVal;
	}

	/**
	 * 結果表示用フラグを取得します。
	 * @return true:表示する/false:表示しない
	 */
	public boolean isDataDispFlag() {
		return dataDispFlag;
	}

	/**
	 * 結果表示用フラグを設定します。
	 * @param dataDispFlag　結果表示用フラグ　
	 */
	public void setDataDispFlag(boolean dataDispFlag) {
		this.dataDispFlag = dataDispFlag;
	}
	

}