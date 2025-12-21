/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

/**
 * ガス種別(CodGas)
 * 
 * 作成日:2011/07/08
 * @author xkinu
 *
 */
public class CodGas {
    public static final String TABLE = "BC32GASS";
    
    public static final String gasShu_COLUMN = "GAS_SHU";
    public static final String gasShuName_COLUMN = "GAS_SHU_NAME";
    public static final String gasShuDisp_COLUMN = "GAS_SHU_DISP";

    //カラム：ガス種別
    private String gasShu;
    //カラム：ガス種別名称
    private String gasShuName;
    //カラム：ガス種別コード+' ' + 名称
    private String gasShuDisp;

    /**
	 * ガス種別取得処理
	 * 
	 * @return クラス変数gasShu を戻します。
	 */
	public String getGasShu() {
		return gasShu;
	}
	/**
	 * ガス種別設定処理
	 * 
	 * @param gasShu を クラス変数gasShuへ設定します。
	 */
	public void setGasShu(String gasShu) {
		this.gasShu = gasShu;
	}
	/**
	 * ガス種別名称取得処理
	 * 
	 * @return クラス変数gasShuName を戻します。
	 */
	public String getGasShuName() {
		return gasShuName;
	}
	/**
	 * ガス種別名称設定処理
	 * 
	 * @param gasShuName を クラス変数gasShuNameへ設定します。
	 */
	public void setGasShuName(String gasShuName) {
		this.gasShuName = gasShuName;
	}
	/**
	 * ガス種別コード名称連結値取得処理
	 * コード+' ' + 名称
	 * 
	 * @return クラス変数gasShuDisp を戻します。
	 */
	public String getGasShuDisp() {
		return gasShuDisp;
	}
	/**
	 * ガス種別コード名称連結値取得処理
	 * コード+' ' + 名称
	 * 
	 * @param gasShuDisp を クラス変数gasShuDispへ設定します。
	 */
	public void setGasShuDisp(String gasShuDisp) {
		this.gasShuDisp = gasShuDisp;
	}
}
