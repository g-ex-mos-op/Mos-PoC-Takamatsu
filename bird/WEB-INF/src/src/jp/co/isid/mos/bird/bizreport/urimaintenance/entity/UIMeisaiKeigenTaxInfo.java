package jp.co.isid.mos.bird.bizreport.urimaintenance.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.common.kaikei.entity.UIKaikeiMeisai;

/**
 * 現金在高（日次）売上消費税明細
 *
 * 作成日:2019/07/11
 * @author USI欒
 *
 */
public class UIMeisaiKeigenTaxInfo extends UIKaikeiMeisai {

    public static final String TABLE = "BD66ADUM";

    /**
     * 合計_売上_通常税率対象
     */
    private BigDecimal sumUriage1;

    /**
     * 合計_売上_軽減税率対象
     */
    private BigDecimal sumUriage2;

    /**
     * 合計_売上_予備3
     */
    private BigDecimal sumUriage3;

    /**
     * 合計_売上_予備4
     */
    private BigDecimal sumUriage4;

    /**
     * 合計_売上_予備5
     */
    private BigDecimal sumUriage5;

    /**
     * 合計_消費税_通常税率対象
     */
    private BigDecimal sumTax1;

    /**
     * 合計_消費税_軽減税率対象
     */
    private BigDecimal sumTax2;

    /**
     * 合計_消費税_予備3
     */
    private BigDecimal sumTax3;

    /**
     * 合計_消費税_予備4
     */
    private BigDecimal sumTax4;

    /**
     * 合計_消費税_予備5
     */
    private BigDecimal sumTax5;

    /**
     * 合計_売上
     */
    private BigDecimal totalUriage;

    /**
     * 合計_消費税
     */
    private BigDecimal totalTax;


    /**
     * @return sumUriage1 を戻します。
     */
	 public BigDecimal getSumUriage1() {
			return sumUriage1;
	}
	/**
	 * @param sumUriage1 を設定します。
	 */
	public void setSumUriage1(BigDecimal sumUriage1) {
		this.sumUriage1 = sumUriage1;
	}

	/**
     * @return sumUriage2 を戻します。
     */
	public BigDecimal getSumUriage2() {
		return sumUriage2;
	}

	/**
	 * @param sumUriage2 を設定します。
	 */
	public void setSumUriage2(BigDecimal sumUriage2) {
		this.sumUriage2 = sumUriage2;
	}

	/**
	 * @return sumUriage3 を戻します。
	 */
	public BigDecimal getSumUriage3() {
		return sumUriage3;
	}

	/**
	 * @param sumUriage3 を設定します。
	 */
	public void setSumUriage3(BigDecimal sumUriage3) {
		this.sumUriage3 = sumUriage3;
	}

	/**
	 * @return sumUriage4 を戻します。
	 */
	public BigDecimal getSumUriage4() {
		return sumUriage4;
	}

	/**
	 * @param sumUriage4 を設定します。
	 */
	public void setSumUriage4(BigDecimal sumUriage4) {
		this.sumUriage4 = sumUriage4;
	}

	/**
	 * @return sumUriage5 を戻します。
	 */
	public BigDecimal getSumUriage5() {
		return sumUriage5;
	}

	/**
	 * @param sumUriage5 を設定します。
	 */
	public void setSumUriage5(BigDecimal sumUriage5) {
		this.sumUriage5 = sumUriage5;
	}

	/**
	 * @return sumTax1 を戻します。
	 */
	public BigDecimal getSumTax1() {
		return sumTax1;
	}

	/**
	 * @param sumTax1 を設定します。
	 */
	public void setSumTax1(BigDecimal sumTax1) {
		this.sumTax1 = sumTax1;
	}

	/**
	 * @return sumTax3 を戻します。
	 */
	public BigDecimal getSumTax2() {
		return sumTax2;
	}

	/**
	 * @param sumTax2を設定します。
	 */
	public void setSumTax2(BigDecimal sumTax2) {
		this.sumTax2 = sumTax2;
	}

	/**
	 * @return sumTax3 を戻します。
	 */
	public BigDecimal getSumTax3() {
		return sumTax3;
	}

	/**
	 * @param sumTax3 を設定します。
	 */
	public void setSumTax3(BigDecimal sumTax3) {
		this.sumTax3 = sumTax3;
	}

	/**
	 * @return sumTax4 を戻します。
	 */
	public BigDecimal getSumTax4() {
		return sumTax4;
	}

	/**
	 * @param sumTax4 を設定します。
	 */
	public void setSumTax4(BigDecimal sumTax4) {
		this.sumTax4 = sumTax4;
	}

	/**
	 * @return sumTax5 を戻します。
	 */
	public BigDecimal getSumTax5() {
		return sumTax5;
	}

	/**
	 * @param sumTax5 を設定します。
	 */
	public void setSumTax5(BigDecimal sumTax5) {
		this.sumTax5 = sumTax5;
	}

	/**
	 * @return totalUriage を戻します。
	 */
    public BigDecimal getTotalUriage() {
		return totalUriage;
	}

	/**
	 * @param totalUriage を設定します。
	 */
	public void setTotalUriage(BigDecimal totalUriage) {
		this.totalUriage = totalUriage;
	}

	/**
	 * @return totalTax を戻します。
	 */
	public BigDecimal getTotalTax() {
		return totalTax;
	}

	/**
	 * @param totalUriage を設定します。
	 */
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}
}
