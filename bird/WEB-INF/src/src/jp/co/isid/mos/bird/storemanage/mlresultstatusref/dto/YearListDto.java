/*
 * 作成日: 2006/04/13
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto;

/**
 * @author Noh
 */
public class YearListDto {
	
	private String year;
	
	
	/**
	 * @return year を戻します。
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year year を設定。
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @param year年度 を設定。
	 */
	public String getYearText(){
		return year + "  年度";
	}
}
