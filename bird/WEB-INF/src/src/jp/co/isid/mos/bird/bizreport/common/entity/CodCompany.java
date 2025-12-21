package jp.co.isid.mos.bird.bizreport.common.entity;

import java.math.BigDecimal;

/**
 * 
 * 更新日:2013/04/03 xkinu 海外売上集信レート表記追加対応 相場ラベルカラム追加
 * 更新日:2013/01/09 xkinu 海外売上集信対応
 * @author xkinu
 *
 */
public class CodCompany {
    
    public static final String TABLE = "BC05KCOM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    /** 海外フラグ 2013/02追加 */
    public static final String foreingFlg_COLUMN = "FOREING_FLG";
    /** 通貨タイプ 2013/02追加 */
    public static final String currencyType_COLUMN = "CURRENCY_TYPE";
    /** 通貨名称 2013/02追加 */
    public static final String currencyName_COLUMN = "CURRENCY_NAME";
    /** 通貨表示形式 2013/02追加 */
    public static final String dispFormat_COLUMN = "DISP_FORMAT";
    /** 円換算相場 2013/02追加 */
    public static final String rate_COLUMN = "RATE";
    /** 相場ラベル 2013/04追加 */
    public static final String rateLabel_COLUMN = "RATE_LABEL";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 会社名
     */
    private String companyName;
    /** 海外フラグ 2013/02追加 */
    private String foreignFlg;
    /** 通貨タイプ 2013/02追加 */
    private String currencyType;
    /** 通貨名称 2013/02追加 */
    private String currencyName;
    /** 通貨表示形式 2013/02追加 */
    private String dispFormat;
    /** 円換算相場 2013/02追加 */
    private BigDecimal rate;
    /** 相場ラベル 2013/04追加 */
    private String rateLabel;
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 会社名を取得します。
     * @return 会社名
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名を設定します。
     * @param companyName 会社名
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
	/**
	 * @return クラス変数currencyName を戻します。
	 */
	public String getCurrencyName() {
		return currencyName;
	}
	/**
	 * @param currencyName を クラス変数currencyNameへ設定します。
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	/**
	 * @return クラス変数currencyType を戻します。
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	/**
	 * @param currencyType を クラス変数currencyTypeへ設定します。
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	/**
	 * @return クラス変数foreingFlg を戻します。
	 */
	public String getForeignFlg() {
		return foreignFlg;
	}
	/**
	 * @param foreignFlg を クラス変数foreingFlgへ設定します。
	 */
	public void setForeignFlg(String foreingFlg) {
		this.foreignFlg = foreingFlg;
	}
	/**
	 * @return クラス変数format を戻します。
	 */
	public String getDispFormat() {
		return dispFormat;
	}
	/**
	 * @param dispFormat を クラス変数formatへ設定します。
	 */
	public void setDispFormat(String format) {
		this.dispFormat = format;
	}
	/**
	 * @return クラス変数rate を戻します。
	 */
	public BigDecimal getRate() {
		return rate;
	}
	/**
	 * @param rate を クラス変数rateへ設定します。
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	/**
	 * 海外判断処理
	 * 
	 * @return　海外の会社の場合はtrueを返します。
	 */
    public boolean isForeign() {
    	return "1".equals(getForeignFlg());
    }
	/**
	 * 相場ラベル
	 * @return クラス変数rateLabel を戻します。
	 */
	public String getRateLabel() {
		return rateLabel;
	}
	/**
	 * 相場ラベル
	 * @param label を クラス変数rateLabelへ設定します。
	 */
	public void setRateLabel(String label) {
		this.rateLabel = label;
	}
}
