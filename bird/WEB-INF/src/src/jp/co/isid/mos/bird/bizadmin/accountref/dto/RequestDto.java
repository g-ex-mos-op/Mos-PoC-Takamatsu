/*
 * 作成日: 2010/03/17
 */
package jp.co.isid.mos.bird.bizadmin.accountref.dto;


/**
 * リクエストパラメータ保持DTO
 * 
 * 作成日:2010/03/17
 * @author xkinu
 *
 */
public class RequestDto {
	
	/*ユーザID*/
	private String userId;
	/*設定区分 */
	private String seteiKbn;
	
	public void clear(){
		setSeteiKbn(null);
		setUserId(null);
	}

	/**
	 * @return クラス変数seteiKbn を戻します。
	 */
	public String getSeteiKbn() {
		return seteiKbn;
	}

	/**
	 * @param seteiKbn を クラス変数seteiKbnへ設定します。
	 */
	public void setSeteiKbn(String seteiKbn) {
		this.seteiKbn = seteiKbn;
	}

	/**
	 * @return クラス変数userId を戻します。
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId を クラス変数userIdへ設定します。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
