/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 * 作成日:2010/05/06
 * @author xkinu
 *
 */
public class UISuiiDetaile extends UISuiiAmt {
	
    public static final String TABLE = "BD27EUPD";
    
    /** 電力(電灯＋動力)使用量置き換えフラグ */
    public static final String epMtFlg_COLUMN = "EP_MT_FLG";
    /** ガス使用量置き換えフラグ */
    public static final String gasMtFlg_COLUMN = "GAS_MT_FLG";
    /** 水道使用量置き換えフラグ */
    public static final String waterMtFlg_COLUMN = "WATER_MT_FLG";
    /** 売上フラグ */
    public static final String uriageFlg_COLUMN = "URIAGE_FLG";
    /** データメンテ日 */
    public static final String dataMtDt_COLUMN = "DATA_MT_DT";

    /** 前年 電力(電灯＋動力)使用量置き換えフラグ */
    public static final String lastEpMtFlg_COLUMN = "LAST_EP_MT_FLG";
    /** 前年 ガス使用量置き換えフラグ */
    public static final String lastGasMtFlg_COLUMN = "LAST_GAS_MT_FLG";
    /** 前年 水道使用量置き換えフラグ */
    public static final String lastWaterMtFlg_COLUMN = "LAST_WATER_MT_FLG";
    /** 前年 売上フラグ */
    public static final String lastUriageFlg_COLUMN = "LAST_URIAGE_FLG";
    /** 前年 データメンテ日 */
    public static final String lastDataMtDt_COLUMN = "LAST_DATA_MT_DT";

    /** 売上 */
    public static final String uriage_COLUMN = "URIAGE";
    /** 前年売上 */
    public static final String lastUriage_COLUMN = "LAST_URIAGE";

    /** 電力(電灯＋動力)使用量置き換えフラグ */
    private String epMtFlg = "";
    /** ガス使用量置き換えフラグ */
    private String gasMtFlg = "";
   /** 水道使用量置き換えフラグ */
    private String waterMtFlg = "";
    /** 売上フラグ */
    private String uriageFlg = "";
    /** データメンテ日 */
    private String dataMtDt = "";

    /** 前年 電力(電灯＋動力)使用量置き換えフラグ */
    private String lastEpMtFlg = "";
    /** 前年 ガス使用量置き換えフラグ */
    private String lastGasMtFlg = "";
    /** 前年 水道使用量置き換えフラグ */
    private String lastWaterMtFlg = "";
    /** 前年 売上フラグ */
    private String lastUriageFlg = "";
    /** 前年 データメンテ日 */
    private String lastDataMtDt = "";
    /** 売上 */
    private BigDecimal uriage;
    /** 前年 売上 */
    private BigDecimal lastUriage;

    /**
	 * @return クラス変数dataMtDt を戻します。
	 */
	public String getDataMtDt() {
		return dataMtDt;
	}
	/**
	 * @param dataMtDt を クラス変数dataMtDtへ設定します。
	 */
	public void setDataMtDt(String dataMtDt) {
		this.dataMtDt = dataMtDt;
	}
	/**
	 * @return クラス変数epMtFlg を戻します。
	 */
	public String getEpMtFlg() {
		return epMtFlg;
	}
	/**
	 * @param epMtFlg を クラス変数epMtFlgへ設定します。
	 */
	public void setEpMtFlg(String epMtFlg) {
		this.epMtFlg = epMtFlg;
	}
	/**
	 * @return クラス変数gasMtFlg を戻します。
	 */
	public String getGasMtFlg() {
		return gasMtFlg;
	}
	/**
	 * @param gasMtFlg を クラス変数gasMtFlgへ設定します。
	 */
	public void setGasMtFlg(String gasMtFlg) {
		this.gasMtFlg = gasMtFlg;
	}
	/**
	 * @return クラス変数uriageFlg を戻します。
	 */
	public String getUriageFlg() {
		return uriageFlg;
	}
	/**
	 * @param uriageFlg を クラス変数uriageFlgへ設定します。
	 */
	public void setUriageFlg(String uriageFlg) {
		this.uriageFlg = uriageFlg;
	}
	/**
	 * @return クラス変数waterMtFlg を戻します。
	 */
	public String getWaterMtFlg() {
		return waterMtFlg;
	}
	/**
	 * @param waterMtFlg を クラス変数waterMtFlgへ設定します。
	 */
	public void setWaterMtFlg(String waterMtFlg) {
		this.waterMtFlg = waterMtFlg;
	}
	/**
	 * @return クラス変数lastEpMtFlg を戻します。
	 */
	public String getLastEpMtFlg() {
		return lastEpMtFlg;
	}
	/**
	 * @param lastEpMtFlg を クラス変数lastEpMtFlgへ設定します。
	 */
	public void setLastEpMtFlg(String lastEpMtFlg) {
		this.lastEpMtFlg = lastEpMtFlg;
	}
	/**
	 * @return クラス変数lastGasMtFlg を戻します。
	 */
	public String getLastGasMtFlg() {
		return lastGasMtFlg;
	}
	/**
	 * @param lastGasMtFlg を クラス変数lastGasMtFlgへ設定します。
	 */
	public void setLastGasMtFlg(String lastGasMtFlg) {
		this.lastGasMtFlg = lastGasMtFlg;
	}
	/**
	 * @return クラス変数lastUriageFlg を戻します。
	 */
	public String getLastUriageFlg() {
		return lastUriageFlg;
	}
	/**
	 * @param lastUriageFlg を クラス変数lastUriageFlgへ設定します。
	 */
	public void setLastUriageFlg(String lastUriageFlg) {
		this.lastUriageFlg = lastUriageFlg;
	}
	/**
	 * @return クラス変数lastWaterMtFlg を戻します。
	 */
	public String getLastWaterMtFlg() {
		return lastWaterMtFlg;
	}
	/**
	 * @param lastWaterMtFlg を クラス変数lastWaterMtFlgへ設定します。
	 */
	public void setLastWaterMtFlg(String lastWaterMtFlg) {
		this.lastWaterMtFlg = lastWaterMtFlg;
	}
	/**
	 * @return クラス変数lastDataMtDt を戻します。
	 */
	public String getLastDataMtDt() {
		return lastDataMtDt;
	}
	/**
	 * @param lastDataMtDt を クラス変数lastDataMtDtへ設定します。
	 */
	public void setLastDataMtDt(String lastDataMtDt) {
		this.lastDataMtDt = lastDataMtDt;
	}
	/**
	 * @return クラス変数lastUriage を戻します。
	 */
	public BigDecimal getLastUriage() {
		return lastUriage;
	}
	/**
	 * @param lastUriage を クラス変数lastUriageへ設定します。
	 */
	public void setLastUriage(BigDecimal lastUriage) {
		this.lastUriage = lastUriage;
	}
	/**
	 * @return クラス変数uriage を戻します。
	 */
	public BigDecimal getUriage() {
		return uriage;
	}
	/**
	 * @param uriage を クラス変数uriageへ設定します。
	 */
	public void setUriage(BigDecimal uriage) {
		this.uriage = uriage;
	}
	/**
	 * @return クラス変数epZennenHi を戻します。
	 */
	public BigDecimal getEpZennenHi() {
		return Calculator.percentage(getEpGnt(), getLastEpGnt(), 2);
	}
	/**
	 * @return クラス変数gasZennenHi を戻します。
	 */
	public BigDecimal getGasZennenHi() {
		return Calculator.percentage(getGasGnt(), getLastGasGnt(), 2);
	}
	/**
	 * @return クラス変数waterZennenHi を戻します。
	 */
	public BigDecimal getWaterZennenHi() {
		return Calculator.percentage(getWaterGnt(), getLastWaterGnt(), 2);
	}
}
