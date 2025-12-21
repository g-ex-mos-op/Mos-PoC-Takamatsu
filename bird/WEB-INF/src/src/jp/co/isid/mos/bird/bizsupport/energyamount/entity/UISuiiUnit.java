/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.entity;

import java.math.BigDecimal;

/**
 * エネルギー原単位
 * 
 * 作成日:2010/05/06
 * @author xkinu
 *
 */
public class UISuiiUnit {
    public static final String TABLE = "BD27EUPD";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    /** 対象(店舗・支部・営業エリア)コード */
    public static final String taishoCd_COLUMN = "TAISHO_CD";
    /** 対象(店舗・支部・営業エリア)名称 */
    public static final String taishoName_COLUMN = "TAISHO_NAME";
    /** メーター年月 */
    public static final String meterYm_COLUMN = "METER_YM";
    
    /** 対象店舗数 */
    public static final String tenpoCnt_COLUMN = "TENPO_CNT";
    /** 電力(電灯＋動力)原単位 */
    public static final String epGnt_COLUMN = "EP_GNT";
    /** ガス原単位 */
    public static final String gasGnt_COLUMN = "GAS_GNT";
    /** 水道原単位 */
    public static final String waterGnt_COLUMN = "WATER_GNT";
    
    /** 前年 対象店舗数 */
    public static final String lastTenpoCnt_COLUMN = "LAST_TENPO_CNT";
    /** 前年 電力(電灯＋動力)原単位 */
    public static final String lastEpGnt_COLUMN = "LAST_EP_GNT";
    /** 前年 ガス原単位 */
    public static final String lastGasGnt_COLUMN = "LAST_GAS_GNT";
    /** 前年 水道原単位 */
    public static final String lastWaterGnt_COLUMN = "LAST_WATER_GNT";

    /** 対象店舗数 */
    private int tenpoCnt=0;
    
    private String companyCd = "";
    /** 対象(店舗・支部・営業エリア)コード */
    private String taishoCd = "";
    /** 対象(店舗・支部・営業エリア)名称 */
    private String taishoName = "";
    /** メーター年月 */
    private String meterYm = "";
    /** 電力(電灯＋動力)原単位 */
    private BigDecimal epGnt;
    /** ガス原単位 */
    private BigDecimal gasGnt;
    /** 水道原単位 */
    private BigDecimal waterGnt;

    /** 前年 対象店舗数 */
    private int lastTenpoCnt=0;
    /** 前年 電力(電灯＋動力)原単位 */
    private BigDecimal lastEpGnt;
    /** 前年 ガス原単位 */
    private BigDecimal lastGasGnt;
    /** 前年 水道原単位 */
    private BigDecimal lastWaterGnt;

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
	 * @return クラス変数epGnt を戻します。
	 */
	public BigDecimal getEpGnt() {
		return epGnt;
	}
	/**
	 * @param epGnt を クラス変数epGntへ設定します。
	 */
	public void setEpGnt(BigDecimal epGnt) {
		this.epGnt = epGnt;
	}
	/**
	 * @return クラス変数gasGnt を戻します。
	 */
	public BigDecimal getGasGnt() {
		return gasGnt;
	}
	/**
	 * @param gasGnt を クラス変数gasGntへ設定します。
	 */
	public void setGasGnt(BigDecimal gasGnt) {
		this.gasGnt = gasGnt;
	}
	/**
	 * @return クラス変数waterGnt を戻します。
	 */
	public BigDecimal getWaterGnt() {
		return waterGnt;
	}
	/**
	 * @param waterGnt を クラス変数waterGntへ設定します。
	 */
	public void setWaterGnt(BigDecimal waterGnt) {
		this.waterGnt = waterGnt;
	}
	/**
	 * @return クラス変数lastEpGnt を戻します。
	 */
	public BigDecimal getLastEpGnt() {
		return lastEpGnt;
	}
	/**
	 * @param lastEpGnt を クラス変数lastEpGntへ設定します。
	 */
	public void setLastEpGnt(BigDecimal lastEpGnt) {
		this.lastEpGnt = lastEpGnt;
	}
	/**
	 * @return クラス変数lastGasGnt を戻します。
	 */
	public BigDecimal getLastGasGnt() {
		return lastGasGnt;
	}
	/**
	 * @param lastGasGnt を クラス変数lastGasGntへ設定します。
	 */
	public void setLastGasGnt(BigDecimal lastGasGnt) {
		this.lastGasGnt = lastGasGnt;
	}
	/**
	 * @return クラス変数lastWaterGnt を戻します。
	 */
	public BigDecimal getLastWaterGnt() {
		return lastWaterGnt;
	}
	/**
	 * @param lastWaterGnt を クラス変数lastWaterGntへ設定します。
	 */
	public void setLastWaterGnt(BigDecimal lastWaterGnt) {
		this.lastWaterGnt = lastWaterGnt;
	}
	/**
	 * @return クラス変数lastTenpoCnt を戻します。
	 */
	public int getLastTenpoCnt() {
		return lastTenpoCnt;
	}
	/**
	 * @param lastTenpoCnt を クラス変数lastTenpoCntへ設定します。
	 */
	public void setLastTenpoCnt(int lastTenpoCnt) {
		this.lastTenpoCnt = lastTenpoCnt;
	}
	/**
	 * @return クラス変数tenpoCnt を戻します。
	 */
	public int getTenpoCnt() {
		return tenpoCnt;
	}
	/**
	 * @param tenpoCnt を クラス変数tenpoCntへ設定します。
	 */
	public void setTenpoCnt(int tenpoCnt) {
		this.tenpoCnt = tenpoCnt;
	}
}
