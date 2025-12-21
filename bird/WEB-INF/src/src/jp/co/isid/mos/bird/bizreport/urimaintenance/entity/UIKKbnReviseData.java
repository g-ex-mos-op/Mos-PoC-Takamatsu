package jp.co.isid.mos.bird.bizreport.urimaintenance.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIMeisaiReviseInf;

/**
 * 会計区分修正情報
 * 
 * 作成日:2012/07/27
 * @author xkinu
 *
 */
public class UIKKbnReviseData extends UIMeisaiReviseInf {
    /**
     * 登録済件数合計値
     */
    private BigDecimal bd31KenTotal;
    
    /**
     * 登録済金額合計値
     */
    private BigDecimal bd31KinTotal;
    /**
     * 明細件数1修正値
     */
    private String strReviseKen1;
    
    /**
     * 明細金額1修正値
     */
    private String strReviseKin1;
    
    /**
     * 明細件数2修正値
     */
    private String strReviseKen2;
    /**
     * 明細金額2修正値
     */
    private String strReviseKin2;
    /**
     * 明細件数3修正値
     */
    private String strReviseKen3;
    /**
     * 明細金額3修正値
     */
    private String strReviseKin3;
    /**
     * 明細件数4修正値
     */
    private String strReviseKen4;
    
    /**
     * 明細金額4修正値
     */
    private String strReviseKin4;

	/**
	 * 登録済件数1修正値
	 */
	private BigDecimal bd31Ken1;

	/**
	 * 登録済金額1修正値
	 */
	private BigDecimal bd31Kin1;

	/**
	 * 登録済件数2修正値
	 */
	private BigDecimal bd31Ken2;

	/**
	 * 登録済金額2修正値
	 */
	private BigDecimal bd31Kin2;

	/**
	 * 登録済件数3修正値
	 */
	private BigDecimal bd31Ken3;

	/**
	 * 登録済金額3修正値
	 */
	private BigDecimal bd31Kin3;

	/**
	 * 登録済件数4修正値
	 */
	private BigDecimal bd31Ken4;

	/**
	 * 登録済金額4修正値
	 */
	private BigDecimal bd31Kin4;
    

	/**
	 * @return クラス変数bd31KenTotal を戻します。
	 */
	public BigDecimal getBd31KenTotal() {
		return bd31KenTotal;
	}
	/**
	 * @param bd31KenTotal を クラス変数bd31KenTotalへ設定します。
	 */
	public void setBd31KenTotal(BigDecimal bd31KenTotal) {
		this.bd31KenTotal = bd31KenTotal;
	}
	/**
	 * @return クラス変数bd31KinTotal を戻します。
	 */
	public BigDecimal getBd31KinTotal() {
		return bd31KinTotal;
	}
	/**
	 * @param bd31KinTotal を クラス変数bd31KinTotalへ設定します。
	 */
	public void setBd31KinTotal(BigDecimal bd31KinTotal) {
		this.bd31KinTotal = bd31KinTotal;
	}
	/**
	 * @return クラス変数strReviseKen1 を戻します。
	 */
	public String getStrReviseKen1() {
		return strReviseKen1;
	}
	/**
	 * @param strValue を クラス変数strReviseKen1へ設定します。
	 */
	public void setStrReviseKen1(String strValue) {
		this.strReviseKen1 = strValue;
		setReviseKen1(formatDec(strValue));
	}
	/**
	 * @return クラス変数strReviseKen2 を戻します。
	 */
	public String getStrReviseKen2() {
		return strReviseKen2;
	}
	/**
	 * @param strValue を クラス変数strReviseKen2へ設定します。
	 */
	public void setStrReviseKen2(String strValue) {
		this.strReviseKen2 = strValue;
		setReviseKen2(formatDec(strValue));
	}
	/**
	 * @return クラス変数strReviseKen3 を戻します。
	 */
	public String getStrReviseKen3() {
		return strReviseKen3;
	}
	/**
	 * @param strReviseKen3 を クラス変数strReviseKen3へ設定します。
	 */
	public void setStrReviseKen3(String strValue) {
		this.strReviseKen3 = strValue;
		setReviseKen3(formatDec(strValue));
	}
	/**
	 * @return クラス変数strReviseKen4 を戻します。
	 */
	public String getStrReviseKen4() {
		return strReviseKen4;
	}
	/**
	 * @param strReviseKen4 を クラス変数strReviseKen4へ設定します。
	 */
	public void setStrReviseKen4(String strValue) {
		this.strReviseKen4 = strValue;
		setReviseKen4(formatDec(strValue));
	}
	/**
	 * @return クラス変数strReviseKin1 を戻します。
	 */
	public String getStrReviseKin1() {
		return strReviseKin1;
	}
	/**
	 * @param strReviseKin1 を クラス変数strReviseKin1へ設定します。
	 */
	public void setStrReviseKin1(String strValue) {
		this.strReviseKin1 = strValue;
		setReviseKin1(formatDec(strValue));
	}
	/**
	 * @return クラス変数strReviseKin2 を戻します。
	 */
	public String getStrReviseKin2() {
		return strReviseKin2;
	}
	/**
	 * @param strValue を クラス変数strReviseKin2へ設定します。
	 */
	public void setStrReviseKin2(String strValue) {
		this.strReviseKin2 = strValue;
		setReviseKin2(formatDec(strValue));
	}
	/**
	 * @return クラス変数strReviseKin3 を戻します。
	 */
	public String getStrReviseKin3() {
		return strReviseKin3;
	}
	/**
	 * @param strValue を クラス変数strReviseKin3へ設定します。
	 */
	public void setStrReviseKin3(String strValue) {
		this.strReviseKin3 = strValue;
		setReviseKin3(formatDec(strValue));
	}
	/**
	 * @return クラス変数strReviseKin4 を戻します。
	 */
	public String getStrReviseKin4() {
		return strReviseKin4;
	}
	/**
	 * @param strValue を クラス変数strReviseKin4へ設定します。
	 */
	public void setStrReviseKin4(String strValue) {
		this.strReviseKin4 = strValue;
		setReviseKin4(formatDec(strValue));
	}
	/**
	 * @return クラス変数bd31Ken1 を戻します。
	 */
	public BigDecimal getBd31Ken1() {
		return bd31Ken1;
	}
	/**
	 * @param bd31Ken1 を クラス変数bd31Ken1へ設定します。
	 */
	public void setBd31Ken1(BigDecimal bd31Ken1) {
		this.bd31Ken1 = bd31Ken1;
	}
	/**
	 * @return クラス変数bd31Ken2 を戻します。
	 */
	public BigDecimal getBd31Ken2() {
		return bd31Ken2;
	}
	/**
	 * @param bd31Ken2 を クラス変数bd31Ken2へ設定します。
	 */
	public void setBd31Ken2(BigDecimal bd31Ken2) {
		this.bd31Ken2 = bd31Ken2;
	}
	/**
	 * @return クラス変数bd31Ken3 を戻します。
	 */
	public BigDecimal getBd31Ken3() {
		return bd31Ken3;
	}
	/**
	 * @param bd31Ken3 を クラス変数bd31Ken3へ設定します。
	 */
	public void setBd31Ken3(BigDecimal bd31Ken3) {
		this.bd31Ken3 = bd31Ken3;
	}
	/**
	 * @return クラス変数bd31Ken4 を戻します。
	 */
	public BigDecimal getBd31Ken4() {
		return bd31Ken4;
	}
	/**
	 * @param bd31Ken4 を クラス変数bd31Ken4へ設定します。
	 */
	public void setBd31Ken4(BigDecimal bd31Ken4) {
		this.bd31Ken4 = bd31Ken4;
	}
	/**
	 * @param bd31Kin1 を クラス変数bd31Kin1へ設定します。
	 */
	public void setBd31Kin1(BigDecimal bd31Kin1) {
		this.bd31Kin1 = bd31Kin1;
	}
	/**
	 * @return クラス変数bd31Kin1 を戻します。
	 */
	public BigDecimal getBd31Kin1() {
		return bd31Kin1;
	}
	/**
	 * @return クラス変数bd31Kin2 を戻します。
	 */
	public BigDecimal getBd31Kin2() {
		return bd31Kin2;
	}
	/**
	 * @param bd31Kin2 を クラス変数bd31Kin2へ設定します。
	 */
	public void setBd31Kin2(BigDecimal bd31Kin2) {
		this.bd31Kin2 = bd31Kin2;
	}
	/**
	 * @return クラス変数bd31Kin3 を戻します。
	 */
	public BigDecimal getBd31Kin3() {
		return bd31Kin3;
	}
	/**
	 * @param bd31Kin3 を クラス変数bd31Kin3へ設定します。
	 */
	public void setBd31Kin3(BigDecimal bd31Kin3) {
		this.bd31Kin3 = bd31Kin3;
	}
	/**
	 * @return クラス変数bd31Kin4 を戻します。
	 */
	public BigDecimal getBd31Kin4() {
		return bd31Kin4;
	}
	/**
	 * @param bd31Kin4 を クラス変数bd31Kin4へ設定します。
	 */
	public void setBd31Kin4(BigDecimal bd31Kin4) {
		this.bd31Kin4 = bd31Kin4;
	}
	private static BigDecimal formatDec(String value) {
		if(value ==null) {
			return null;
		}
		try {
			return new BigDecimal(value);
		}
		catch(NumberFormatException nfex) {
			return new BigDecimal("0");
		}
	}
}
