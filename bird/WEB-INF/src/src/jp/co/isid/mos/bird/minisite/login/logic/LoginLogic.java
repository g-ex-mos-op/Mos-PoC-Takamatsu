/**
 *
 */
package jp.co.isid.mos.bird.minisite.login.logic;

import javax.servlet.http.HttpServletRequest;

/**
 * minisiteログイン ロジックインターフェース
 *
 * 作成日:2018/08/05
 * @author zcj
 *
 */
public interface LoginLogic {
	/**
	 *  minisiteログイン処理実行
	 *
	 * @param request
	 * @return 遷移先VIEWID
	 */
	public String execute(HttpServletRequest request);
}
