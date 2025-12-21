/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportregist.logic;

import jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 初期表示情報取得設定ロジックインターフェース
 * 
 * DTOの初期化と、DTOへDBから取得した登録情報などを設定を行います。
 * 作成日:2010/11/10
 * @author xkinu
 *
 */
public interface SetUpInitDataLogic {
	/**
	 * 実行処理
	 * 
	 * @param birdDateInfo BIRD日付情報
	 * @param birdUserInfo BIRDユーザー情報
	 * @param registDto    DTO【POS速報設定情報保持】
	 */
	public void execute(
			  BirdDateInfo birdDateInfo
			, BirdUserInfo birdUserInfo
			, PosReportRegistDto registDto);

}
