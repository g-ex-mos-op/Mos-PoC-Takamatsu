/**
 * 
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.logic;

import jp.co.isid.mos.bird.common.entity.CtlPasswordPolicy;

/**
 * パスワードポリシー取得ロジックインターフェース
 * 
 * 作成日:2009/12/21
 * @author xkinu
 *
 */
public interface GetCtlPasswordPolicyLogic {
	
	/**
	 * 実行処理
	 * @param userId
	 * @return
	 */
	public CtlPasswordPolicy execute(String userId);
}
