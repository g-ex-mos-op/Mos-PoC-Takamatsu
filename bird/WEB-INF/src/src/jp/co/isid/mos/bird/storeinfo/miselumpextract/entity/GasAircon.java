/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.miselumpextract.entity;

/**
 * 店舗ガス種別
 * 
 * 作成日:2011/07/11
 * @author xkinu
 *
 */
public class GasAircon extends MstMiseYobi {

    public static final String gasShuName_COLUMN = "GAS_SHU_NAME";

    public static final String airConShuName_COLUMN = "AIR_CON_SHU_NAME";

    /** ガス種別名称(BC32GASS.GAS_SHU_NAME) */
    private String gasShuName;
    /** エアコン種別名称(BC33AIRS.AIR_CON_SHU_NAME) */
    private String airConShuName;
	/**
	 * @return クラス変数airConShuName を戻します。
	 */
	public String getAirConShuName() {
		return airConShuName;
	}
	/**
	 * @param airConShuName を クラス変数airConShuNameへ設定します。
	 */
	public void setAirConShuName(String airConShuName) {
		this.airConShuName = airConShuName;
	}
	/**
	 * @return クラス変数gasShuName を戻します。
	 */
	public String getGasShuName() {
		return gasShuName;
	}
	/**
	 * @param gasShuName を クラス変数gasShuNameへ設定します。
	 */
	public void setGasShuName(String gasShuName) {
		this.gasShuName = gasShuName;
	}

}
