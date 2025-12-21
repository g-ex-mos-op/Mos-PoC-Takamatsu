/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportregist.logic;

import jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 登録ロジックインターフェース
 * 
 * Ｐ６対応仕様
 * テーブルBR82RTSM(店舗別リアルタイム集信期間マスタ)へ登録処理を行います。
 * 作成日:2010/11/19
 * @author xkinu
 *
 */
public interface RegistLogic {
	/**
	 * 実行処理
	 * 
	 * @param birdUserInfo BIRDユーザー情報
	 * @param registDto    DTO【POS速報設定情報保持】
	 */
	public void execute(
			  BirdUserInfo birdUserInfo
			, PosReportRegistDto registDto);

}
