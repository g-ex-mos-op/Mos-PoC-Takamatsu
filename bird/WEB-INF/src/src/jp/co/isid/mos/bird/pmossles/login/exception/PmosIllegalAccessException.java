/**
 * 
 */
package jp.co.isid.mos.bird.pmossles.login.exception;

import jp.co.isid.mos.bird.framework.exception.GenericErrorException;

/**
 * p-mossles不正アクセス例外クラス
 * 
 * 作成日:2010/12/20
 * @author xkinu
 *
 */
public class PmosIllegalAccessException extends GenericErrorException {

    static final long serialVersionUID = 1L;
    
    private String memo = "";

    public PmosIllegalAccessException(String memo) {
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
