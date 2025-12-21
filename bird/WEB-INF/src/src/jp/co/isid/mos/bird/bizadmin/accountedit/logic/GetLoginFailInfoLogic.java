/**
 * 
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.logic;

import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;

/**
 * ロック情報取得ロジックインターフェース
 * 作成日:2009/12/14
 * @author xkinu
 *
 */
public interface GetLoginFailInfoLogic {
	/**
	 * ロック情報取得　実行処理
	 * 
	 * @param userId
	 * @return
	 */
	public CtlLoginFailKaisu execute(String userId);

}
