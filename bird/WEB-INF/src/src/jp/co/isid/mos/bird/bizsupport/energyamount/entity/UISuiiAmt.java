/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.entity;

import java.math.BigDecimal;

/**
 * エネルギー使用量置き換え後データ
 * 
 * 作成日:2010/05/06
 * @author xkinu
 *
 */
public class UISuiiAmt extends UISuiiUnit {
    public static final String TABLE = "BD27EUPD";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    /** 対象(店舗・支部・営業エリア)コード */
    public static final String taishoCd_COLUMN = "TAISHO_CD";
    /** 対象(店舗・支部・営業エリア)名称 */
    public static final String taishoName_COLUMN = "TAISHO_NAME";
    /** メーター年月 */
    public static final String meterYm_COLUMN = "METER_YM";
    
    /** 電力(電灯＋動力)使用量 */
    public static final String epAmt_COLUMN = "EP_AMT";
    /** ガス使用量 */
    public static final String gasAmt_COLUMN = "GAS_AMT";
    /** 水道使用量 */
    public static final String waterAmt_COLUMN = "WATER_AMT";
    
    /** 前年 電力(電灯＋動力)使用量 */
    public static final String lastEpAmt_COLUMN = "LAST_EP_AMT";
    /** 前年 ガス使用量 */
    public static final String lastGasAmt_COLUMN = "LAST_GAS_AMT";
    /** 前年 水道使用量 */
    public static final String lastWaterAmt_COLUMN = "LAST_WATER_AMT";

    private String companyCd = "";
    /** 対象(店舗・支部・営業エリア)コード */
    private String taishoCd = "";
    /** 対象(店舗・支部・営業エリア)名称 */
    private String taishoName = "";
    /** メーター年月 */
    private String meterYm = "";
    /** 電力(電灯＋動力)使用量 */
    private BigDecimal epAmt;
    /** ガス使用量 */
    private BigDecimal gasAmt;
    /** 水道使用量 */
    private BigDecimal waterAmt;

    /** 前年 電力(電灯＋動力)使用量 */
    private BigDecimal lastEpAmt;
    /** 前年 ガス使用量 */
    private BigDecimal lastGasAmt;
    /** 前年 水道使用量 */
    private BigDecimal lastWaterAmt;

    /**
	 * @return クラス変数companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd を クラス変数companyCdへ設定します。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return クラス変数epAmt を戻します。
	 */
	public BigDecimal getEpAmt() {
		return epAmt;
	}
	/**
	 * @param epAmt を クラス変数epAmtへ設定します。
	 */
	public void setEpAmt(BigDecimal epAmt) {
		this.epAmt = epAmt;
	}
	/**
	 * @return クラス変数gasAmt を戻します。
	 */
	public BigDecimal getGasAmt() {
		return gasAmt;
	}
	/**
	 * @param gasAmt を クラス変数gasAmtへ設定します。
	 */
	public void setGasAmt(BigDecimal gasAmt) {
		this.gasAmt = gasAmt;
	}
	/**
	 * @return クラス変数meterYm を戻します。
	 */
	public String getMeterYm() {
		return meterYm;
	}
	/**
	 * @param meterYm を クラス変数meterYmへ設定します。
	 */
	public void setMeterYm(String meterYm) {
		this.meterYm = meterYm;
	}
	/**
	 * @return クラス変数taishoCd を戻します。
	 */
	public String getTaishoCd() {
		return taishoCd;
	}
	/**
	 * @param taishoCd を クラス変数taishoCdへ設定します。
	 */
	public void setTaishoCd(String taishoCd) {
		this.taishoCd = taishoCd;
	}
	/**
	 * @return クラス変数taishoName を戻します。
	 */
	public String getTaishoName() {
		return taishoName;
	}
	/**
	 * @param taishoName を クラス変数taishoNameへ設定します。
	 */
	public void setTaishoName(String taishoName) {
		this.taishoName = taishoName;
	}
	/**
	 * @return クラス変数waterAmt を戻します。
	 */
	public BigDecimal getWaterAmt() {
		return waterAmt;
	}
	/**
	 * @param waterAmt を クラス変数waterAmtへ設定します。
	 */
	public void setWaterAmt(BigDecimal waterAmt) {
		this.waterAmt = waterAmt;
	}
	/**
	 * @return クラス変数lastEpAmt を戻します。
	 */
	public BigDecimal getLastEpAmt() {
		return lastEpAmt;
	}
	/**
	 * @param lastEpAmt を クラス変数lastEpAmtへ設定します。
	 */
	public void setLastEpAmt(BigDecimal lastEpAmt) {
		this.lastEpAmt = lastEpAmt;
	}
	/**
	 * @return クラス変数lastGasAmt を戻します。
	 */
	public BigDecimal getLastGasAmt() {
		return lastGasAmt;
	}
	/**
	 * @param lastGasAmt を クラス変数lastGasAmtへ設定します。
	 */
	public void setLastGasAmt(BigDecimal lastGasAmt) {
		this.lastGasAmt = lastGasAmt;
	}
	/**
	 * @return クラス変数lastWaterAmt を戻します。
	 */
	public BigDecimal getLastWaterAmt() {
		return lastWaterAmt;
	}
	/**
	 * @param lastWaterAmt を クラス変数lastWaterAmtへ設定します。
	 */
	public void setLastWaterAmt(BigDecimal lastWaterAmt) {
		this.lastWaterAmt = lastWaterAmt;
	}
}
