/**
 * 
 */
package jp.co.isid.mos.bird.pmossles.login.logic;

import javax.servlet.http.HttpServletRequest;

/**
 * p-mosslesログイン ロジックインターフェース
 * 
 * 作成日:2010/12/08
 * @author xkinu
 *
 */
public interface LoginLogic {
	/**
	 *  p-mosslesログイン処理実行
	 *  
	 * @param request
	 * @return 遷移先VIEWID
	 */
	public String execute(HttpServletRequest request);
}
