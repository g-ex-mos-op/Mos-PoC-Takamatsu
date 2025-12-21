/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportref.logic;

import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.SessionDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 条件情報取得処理
 * 
 * 条件項目情報を取得生成し、セッション情報保持ＤＴＯへ設定する処理を行います。
 * 作成日:2010/10/25
 * @author xkinu
 *
 */
public interface ConditionLogic {
	/**
	 * 実行処理
	 * 
	 * @param birdUserInfo ユーザー情報
	 * @param sessionDto   DTO【セッション情報保持】
	 * @param requestDto   DTO【リクエスト情報保持】
	 * @return
	 */
	public void execute(
			  BirdUserInfo birdUserInfo
			, SessionDto sessionDto
			, PosReportRefDto requestDto);
}
