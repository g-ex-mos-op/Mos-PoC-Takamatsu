/**
 * 
 */
package jp.co.isid.mos.bird.common.kaikei.entity;

import java.lang.reflect.Method;

/**
 * 件数金額Getterメソッド情報
 * 
 * 作成日:2012/07/30
 * @author xkinu
 *
 */
public class GetterMethod {
	private String kkbnCd;
	private Method methodKen;
	private Method methodKin;
	/**
	 * @return クラス変数kkbnCd を戻します。
	 */
	public String getKkbnCd() {
		return kkbnCd;
	}
	/**
	 * @param kkbnCd を クラス変数kkbnCdへ設定します。
	 */
	public void setKkbnCd(String kkbnCd) {
		this.kkbnCd = kkbnCd;
	}
	/**
	 * @return クラス変数methodKen を戻します。
	 */
	public Method getMethodKen() {
		return methodKen;
	}
	/**
	 * @param methodKen を クラス変数methodKenへ設定します。
	 */
	public void setMethodKen(Method methodKen) {
		this.methodKen = methodKen;
	}
	/**
	 * @return クラス変数methodKin を戻します。
	 */
	public Method getMethodKin() {
		return methodKin;
	}
	/**
	 * @param methodKin を クラス変数methodKinへ設定します。
	 */
	public void setMethodKin(Method methodKin) {
		this.methodKin = methodKin;
	}
}
