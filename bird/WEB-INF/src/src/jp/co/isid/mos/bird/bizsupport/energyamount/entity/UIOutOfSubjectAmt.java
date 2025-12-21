/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.entity;

/**
 * メータ入力店舗別管理項目設定情報
 * 
 * 作成日:2010/05/06
 * @author xkinu
 *
 */
public class UIOutOfSubjectAmt {
	/** テーブル：メータ入力店舗別管理項目設定 */
    public static final String TABLE = "BM68MSET";
    /** 会社コード */
    public static final String companyCd_COLUMN = "COMPANY_CD";
    /** 店舗コード */
    public static final String miseCd_COLUMN = "MISE_CD";
    /** メーター区分 */
    public static final String meterKbn_COLUMN = "METER_KBN";
    /** 電力(電灯＋動力)使用量フラグ */
    public static final String epFlg_COLUMN = "EP_FLG";
    /** ガス使用量フラグ */
    public static final String gasFlg_COLUMN = "GAS_FLG";
    /** 水道使用量フラグ */
    public static final String waterFlg_COLUMN = "WATER_FLG";

    /** 会社コード */
    private String companyCd = "";
    /** 店舗コード */
    private String miseCd = "";
    /** メーター区分 */
    private String meterKbn = "";
    /** 電力(電灯＋動力)使用量フラグ */
    private String epFlg = "";
    /** ガス使用量フラグ */
    private String gasFlg = "";
    /** 水道使用量フラグ */
    private String waterFlg = "";
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
	 * 電力(電灯＋動力)使用量フラグ取得処理
	 * @return クラス変数electricpowerFlg を戻します。
	 */
	public String getEpFlg() {
		return epFlg;
	}
	/**
	 * 電力(電灯＋動力)使用量フラグ設定処理
	 * @param epFlg を クラス変数electricpowerFlgへ設定します。
	 */
	public void setEpFlg(String electricpowerFlg) {
		this.epFlg = electricpowerFlg;
	}
	/**
	 * ガス使用量フラグ取得処理
	 *  
	 * @return クラス変数gasFlg を戻します。
	 */
	public String getGasFlg() {
		return gasFlg;
	}
	/**
	 * ガス使用量フラグ設定処理
	 * 
	 * @param gasFlg を クラス変数gasFlgへ設定します。
	 */
	public void setGasFlg(String gasFlg) {
		this.gasFlg = gasFlg;
	}
	/**
	 * @return クラス変数meterKbn を戻します。
	 */
	public String getMeterKbn() {
		return meterKbn;
	}
	/**
	 * @param meterKbn を クラス変数meterKbnへ設定します。
	 */
	public void setMeterKbn(String meterKbn) {
		this.meterKbn = meterKbn;
	}
	/**
	 * @return クラス変数miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * @param miseCd を クラス変数miseCdへ設定します。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
	 * 水道使用量取得処理
	 * 
	 * @return クラス変数waterFlg を戻します。
	 */
	public String getWaterFlg() {
		return waterFlg;
	}
	/**
	 * 水道使用量設定処理
	 *
	 * @param waterFlg を クラス変数waterFlgへ設定します。
	 */
	public void setWaterFlg(String waterFlg) {
		this.waterFlg = waterFlg;
	}
}
