/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

/**
 * エアコン種別(CodAirConditioner) 
 * 
 * 作成日:2011/07/08
 * @author xkinu
 *
 */
public class CodAirConditioner {
    public static final String TABLE = "BC33AIRS";
    
    public static final String airConShu_COLUMN = "AIR_CON_SHU";
    public static final String airConShuName_COLUMN = "AIR_CON_SHU_NAME";
    public static final String airConShuDisp_COLUMN = "AIR_CON_SHU_DISP";
    //カラム：エアコン種別
    private String airConShu;
    //カラム：エアコン種別名称
    private String airConShuName;
    //カラム：エアコン種別コード+' ' + 名称
    private String airConShuDisp;
	/**
	 * エアコン種別取得処理
	 * 
	 * @return クラス変数airConShu を戻します。
	 */
	public String getAirConShu() {
		return airConShu;
	}
	/**
	 * エアコン種別設定処理
	 * 
	 * @param airConShu を クラス変数airConShuへ設定します。
	 */
	public void setAirConShu(String airConShu) {
		this.airConShu = airConShu;
	}
	/**
	 * エアコン種別名称取得処理
	 * 
	 * @return クラス変数airConShuName を戻します。
	 */
	public String getAirConShuName() {
		return airConShuName;
	}
	/**
	 * エアコン種別名称設定処理
	 * 
	 * @param airConShuName を クラス変数airConShuNameへ設定します。
	 */
	public void setAirConShuName(String airConShuName) {
		this.airConShuName = airConShuName;
	}
	/**
	 * エアコン種別コード名称連結値取得処理
	 * コード+' ' + 名称
	 * 
	 * @return クラス変数airConShuDisp を戻します。
	 */
	public String getAirConShuDisp() {
		return airConShuDisp;
	}
	/**
	 * エアコン種別コード名称連結値設定処理
	 * コード+' ' + 名称
	 * @param airConShuDisp を クラス変数airConShuDispへ設定します。
	 */
	public void setAirConShuDisp(String airConShuDisp) {
		this.airConShuDisp = airConShuDisp;
	}

}
