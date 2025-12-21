/*
 * 作成日: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity;

/**
 * @author Noh
 */

public class UITaishoKenshuInfo {
	
	public static final String TABLE = "BR17ENTL";
	
	public static final String entryYear_COLUMN = "ENTRY_YEAR";
	
	public static final String entryKai_COLUMN = "ENTRY_KAI";
	
	public static final String fromDt_COLUMN = "FROM_DT";
	
	public static final String toDt_COLUMN = "TO_DT";
	
	/**
	 * エントリー年
	 */
	private String entryYear;
	
	/**
	 * エントリー回
	 */
	private String entryKai;
	
	/**
	 * 開始日
	 */
	private String fromDt;
	
	/**
	 * 終了日
	 */
	private String toDt;
	
	
	
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
	/**
	 * @return fromDt を戻します。
	 */
	public String getFromDt() {
		return fromDt;
	}
	/**
	 * @param fromDt fromDt を設定。
	 */
	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}
	/**
	 * @return toDt を戻します。
	 */
	public String getToDt() {
		return toDt;
	}
	/**
	 * @param toDt toDt を設定。
	 */
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
}
