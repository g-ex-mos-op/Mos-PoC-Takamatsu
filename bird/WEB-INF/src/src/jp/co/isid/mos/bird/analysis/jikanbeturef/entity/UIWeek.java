package jp.co.isid.mos.bird.analysis.jikanbeturef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 * 曜日別エンティティクラス
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public class UIWeek extends UAWeekTime {
    /**
     * 前年売上
     */
    private BigDecimal kingakuZen = new BigDecimal("0");
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen = new BigDecimal("0");
    
    /**
     * 前年売上を取得します。
     * @return 前年売上
     */
    public BigDecimal getKingakuZen() {
        return kingakuZen;
    }
    /**
     * 前年売上を設定します。
     * @param kingakuZen 前年売上
     */
    public void setKingakuZen(BigDecimal kingakuZen) {
        this.kingakuZen = kingakuZen;
    }
	/**
	 * 前年比(売上金額)
	 * @return kyakusuZennenHi を戻します。
	 */
	public BigDecimal getKingakuZennenHi() {
		return Calculator.percentage(getKingaku(), getKingakuZen(), 2);
	}
    /**
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getKyakusuZen() {
        return kyakusuZen;
    }
    /**
     * 前年客数を設定します。
     * @param kyakusuZen 前年客数
     */
    public void setKyakusuZen(BigDecimal kyakusuZen) {
        this.kyakusuZen = kyakusuZen;
    }
	/**
	 * 前年比(客数)
	 * @return kyakusuZennenHi を戻します。
	 */
	public BigDecimal getKyakusuZennenHi() {
		return Calculator.percentage(getKyakusu(), getKyakusuZen(), 2);
	}
    
    /**
     * 客単価を取得します。
     * @return 客単価
     */
    public BigDecimal getKyakutanka() {
    	return Calculator.divide(getKingaku(), getKyakusu(), 0);
    }
    
    /**
     * 前年客単価を取得します。
     * @return 前年客単価
     */
    public BigDecimal getKyakutankaZen() {
        return Calculator.divide(getKingakuZen(), getKyakusuZen(), 0);
    }
	/**
	 * 前年比(客単価)
	 * @return kyakutankaZennenHi を戻します。
	 */
	public BigDecimal getKyakutankaZennenHi() {
		return Calculator.percentage(getKyakutanka(), getKyakutankaZen(), 2);
	}
}
