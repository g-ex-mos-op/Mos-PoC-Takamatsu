/**
 *
 */
package jp.co.isid.mos.bird.minisite.login.exception;

import jp.co.isid.mos.bird.framework.exception.GenericErrorException;

/**
 * minisite不正アクセス例外クラス
 *
 * 作成日:2017/08/05
 * @author zcj
 *
 */
public class MinisiteIllegalAccessException extends GenericErrorException {

    static final long serialVersionUID = 1L;

    private String memo = "";

    public MinisiteIllegalAccessException(String memo) {
    	super("ログイン", "不正なアクセスです。");
    	setMemo(memo);
    }
	/**
	 * @return クラス変数memo を戻します。
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo を クラス変数memoへ設定します。
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
