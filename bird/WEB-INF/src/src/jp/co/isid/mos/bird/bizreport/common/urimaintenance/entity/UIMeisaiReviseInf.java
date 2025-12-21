package jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.common.kaikei.entity.UIKaikeiMeisai;

/**
 * 会計区分修正状況
 * 
 * 作成日:2012/07/27
 * @author xkinu
 *
 */
public class UIMeisaiReviseInf extends UIKaikeiMeisai {
    
    public static final String TABLE = "BD31KKUP";
    
    private BigDecimal _dec0 = new BigDecimal("0");
    /**
     * 明細件数1修正値
     */
    private BigDecimal reviseKen1;
    
    /**
     * 明細金額1修正値
     */
    private BigDecimal reviseKin1;
    
    /**
     * 明細件数2修正値
     */
    private BigDecimal reviseKen2;
    /**
     * 明細金額2修正値
     */
    private BigDecimal reviseKin2;
    /**
     * 明細件数3修正値
     */
    private BigDecimal reviseKen3;
    
    /**
     * 明細金額3修正値
     */
    private BigDecimal reviseKin3;
    
    /**
     * 明細件数4修正値
     */
    private BigDecimal reviseKen4;
    
    /**
     * 明細金額4修正値
     */
    private BigDecimal reviseKin4;
    
    /**
     * 修正値合計件数
     */
    private BigDecimal reviseKenTotal;
    
    /**
     * 修正値合計金額
     */
    private BigDecimal reviseKinTotal;
    
    /**
     * 明細件数1修正フラグ
     */
    private boolean revisedKen1 = false;
    /**
     * 明細金額1修正フラグ
     */
    private boolean revisedKin1 = false;
    /**
     * 明細件数2修正フラグ
     */
    private boolean revisedKen2 = false;
    /**
     * 明細金額2修正フラグ
     */
    private boolean revisedKin2 = false;
    /**
     * 明細件数3修正フラグ
     */
    private boolean revisedKen3 = false;
    /**
     * 明細金額3修正フラグ
     */
    private boolean revisedKin3 = false;
    /**
     * 明細件数4修正フラグ
     */
    private boolean revisedKen4 = false;
    /**
     * 明細金額4修正フラグ
     */
    private boolean revisedKin4 = false;

    /**
	 * @param revisedKen1 を クラス変数revisedKen1へ設定します。
	 */
	public void setRevisedKen1(boolean revisedKen1) {
		this.revisedKen1 = revisedKen1;
	}
	/**
	 * @param revisedKen2 を クラス変数revisedKen2へ設定します。
	 */
	public void setRevisedKen2(boolean revisedKen2) {
		this.revisedKen2 = revisedKen2;
	}
	/**
	 * @param revisedKen3 を クラス変数revisedKen3へ設定します。
	 */
	public void setRevisedKen3(boolean revisedKen3) {
		this.revisedKen3 = revisedKen3;
	}
	/**
	 * @param revisedKen4 を クラス変数revisedKen4へ設定します。
	 */
	public void setRevisedKen4(boolean revisedKen4) {
		this.revisedKen4 = revisedKen4;
	}
	/**
	 * @param revisedKin1 を クラス変数revisedKin1へ設定します。
	 */
	public void setRevisedKin1(boolean revisedKin1) {
		this.revisedKin1 = revisedKin1;
	}
	/**
	 * @param revisedKin2 を クラス変数revisedKin2へ設定します。
	 */
	public void setRevisedKin2(boolean revisedKin2) {
		this.revisedKin2 = revisedKin2;
	}
	/**
	 * @param revisedKin3 を クラス変数revisedKin3へ設定します。
	 */
	public void setRevisedKin3(boolean revisedKin3) {
		this.revisedKin3 = revisedKin3;
	}
	/**
	 * @param revisedKin4 を クラス変数revisedKin4へ設定します。
	 */
	public void setRevisedKin4(boolean revisedKin4) {
		this.revisedKin4 = revisedKin4;
	}
	/**
     * 明細件数1修正値を取得します。
     * @return 明細件数1修正値
     */
    public BigDecimal getReviseKen1() {
    	if(reviseKen1!= null) {
    		return reviseKen1;
    	}
    	return _dec0;
    }
    /**
     * 明細件数1修正値を設定します。
     * @param reviseKen1 明細件数1修正値
     */
    public void setReviseKen1(BigDecimal reviseKen1) {
        this.reviseKen1 = reviseKen1;
    }
    /**
     * 明細金額1修正値を取得します。
     * @return 明細金額1修正値
     */
    public BigDecimal getReviseKin1() {
    	if(reviseKin1!= null) {
    		return reviseKin1;
    	}
    	return _dec0;
    }
    /**
     * 明細金額1修正値を設定します。
     * @param reviseKin1 明細金額1修正値
     */
    public void setReviseKin1(BigDecimal reviseKin1) {
        this.reviseKin1 = reviseKin1;
    }
    
    /**
     * 明細件数2修正値を取得します。
     * @return 明細件数2修正値
     */
    public BigDecimal getReviseKen2() {
    	if(reviseKen2!= null) {
    		return reviseKen2;
    	}
    	return _dec0;
    }
    /**
     * 明細件数2修正値を設定します。
     * @param reviseKen2 明細件数2修正値
     */
    public void setReviseKen2(BigDecimal reviseKen2) {
        this.reviseKen2 = reviseKen2;
    }
    
    /**
     * 明細金額2修正値を取得します。
     * @return 明細金額2修正値
     */
    public BigDecimal getReviseKin2() {
    	if(reviseKin2!= null) {
    		return reviseKin2;
    	}
    	return _dec0;
    }
    /**
     * 明細金額2修正値を設定します。
     * @param reviseKin2 明細金額2修正値
     */
    public void setReviseKin2(BigDecimal reviseKin2) {
        this.reviseKin2 = reviseKin2;
    }
    
    /**
     * 明細件数3修正値を取得します。
     * @return 明細件数3修正値
     */
    public BigDecimal getReviseKen3() {
    	if(reviseKen3!= null) {
    		return reviseKen3;
    	}
    	return _dec0;
    }
    /**
     * 明細件数3修正値を設定します。
     * @param reviseKen3 明細件数3修正値
     */
    public void setReviseKen3(BigDecimal reviseKen3) {
        this.reviseKen3 = reviseKen3;
    }
    
    /**
     * 明細金額3修正値を取得します。
     * @return 明細金額3修正値
     */
    public BigDecimal getReviseKin3() {
    	if(reviseKin3!= null) {
    		return reviseKin3;
    	}
    	return _dec0;
    }
    /**
     * 明細金額3修正値を設定します。
     * @param reviseKin3 明細金額3修正値
     */
    public void setReviseKin3(BigDecimal reviseKin3) {
        this.reviseKin3 = reviseKin3;
    }
    
    /**
     * 明細件数4修正値を取得します。
     * @return 明細件数4修正値
     */
    public BigDecimal getReviseKen4() {
    	if(reviseKen4!= null) {
    		return reviseKen4;
    	}
    	return _dec0;
    }
    /**
     * 明細件数4修正値を設定します。
     * @param reviseKen4 明細件数4修正値
     */
    public void setReviseKen4(BigDecimal reviseKen4) {
        this.reviseKen4 = reviseKen4;
    }
    
    /**
     * 明細金額4修正値を取得します。
     * @return 明細金額4修正値
     */
    public BigDecimal getReviseKin4() {
    	if(reviseKin4!= null) {
    		return reviseKin4;
    	}
    	return _dec0;
    }
    /**
     * 明細金額4修正値を設定します。
     * @param reviseKin4 明細金額4修正値
     */
    public void setReviseKin4(BigDecimal reviseKin4) {
        this.reviseKin4 = reviseKin4;
    }
    
    /**
     * 修正値合計件数を取得します。
     * @return 修正値合計件数
     */
    public BigDecimal getReviseKenTotal() {
    	if(reviseKenTotal!= null) {
    		return reviseKenTotal;
    	}
    	return _dec0;
    }
    /**
     * 修正値合計件数を設定します。
     * @param reviseKenTotal 修正値合計件数
     */
    public void setReviseKenTotal(BigDecimal reviseKenTotal) {
        this.reviseKenTotal = reviseKenTotal;
    }
    
    /**
     * 修正値合計金額を取得します。
     * @return 修正値合計金額
     */
    public BigDecimal getReviseKinTotal() {
    	if(reviseKinTotal!= null) {
    		return reviseKinTotal;
    	}
    	return _dec0;
    }
    /**
     * 修正値合計金額を設定します。
     * @param reviseKinTotal 修正値合計金額
     */
    public void setReviseKinTotal(BigDecimal reviseKinTotal) {
        this.reviseKinTotal = reviseKinTotal;
    }
    public BigDecimal getReviseKenTotal1_4() {
    	BigDecimal decTotal = new BigDecimal("0");
    	decTotal = decTotal.add(getReviseKen1());
    	decTotal = decTotal.add(getReviseKen2());
    	decTotal = decTotal.add(getReviseKen3());
    	decTotal = decTotal.add(getReviseKen4());
    	return decTotal;
    }
    public BigDecimal getReviseKinTotal1_4() {
    	BigDecimal decTotal = new BigDecimal("0");
    	decTotal = decTotal.add(getReviseKin1());
    	decTotal = decTotal.add(getReviseKin2());
    	decTotal = decTotal.add(getReviseKin3());
    	decTotal = decTotal.add(getReviseKin4());
    	return decTotal;
    }
	/**
	 * @return クラス変数revisedKen1 を戻します。
	 */
	public boolean isRevisedKen1() {
		return revisedKen1;
	}
	/**
	 * @return クラス変数revisedKen2 を戻します。
	 */
	public boolean isRevisedKen2() {
		return revisedKen2;
	}
	/**
	 * @return クラス変数revisedKen3 を戻します。
	 */
	public boolean isRevisedKen3() {
		return revisedKen3;
	}
	/**
	 * @return クラス変数revisedKen4 を戻します。
	 */
	public boolean isRevisedKen4() {
		return revisedKen4;
	}
	/**
	 * @return クラス変数revisedKin1 を戻します。
	 */
	public boolean isRevisedKin1() {
		return revisedKin1;
	}
	/**
	 * @return クラス変数revisedKin2 を戻します。
	 */
	public boolean isRevisedKin2() {
		return revisedKin2;
	}
	/**
	 * @return クラス変数revisedKin3 を戻します。
	 */
	public boolean isRevisedKin3() {
		return revisedKin3;
	}
	/**
	 * @return クラス変数revisedKin4 を戻します。
	 */
	public boolean isRevisedKin4() {
		return revisedKin4;
	}
}
