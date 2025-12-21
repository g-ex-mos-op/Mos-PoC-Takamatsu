/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointmstregist.logic;

import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.PointDataDto;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.SeidoDataDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 付与ポイント検索ロジックインターフェース
 * @author yushuncheng
 *
 */
public interface GetPointInfoLogic {

	/**
	 * 付与ポイントリストを取得します。
	 * @param seidoDataDto 株式報酬制度Dto
	 * @param pointDataDto 付与ポイントDto
	 */
	public void execute(SeidoDataDto seidoDataDto, PointDataDto pointDataDto);

	/**
	 * 年度付与ポイントを追加します。
	 * @param pointDataDto 付与ポイントDto
	 */
	public void addNendo(PointDataDto pointDataDto);

	/**
	 * 新規登録 を実行する。
	 * @param pointDataDto 付与ポイントDto
	 * @param userInfo ユーザ情報Dto
	 */
	public void insertDate(PointDataDto pointDataDto, BirdUserInfo userInfo);

    /**
	 * 編集登録 を実行する。
	 * @param pointDataDto 付与ポイントDto
	 * @param userInfo ユーザ情報Dto
     */
	public void updateDate(PointDataDto pointDataDto, BirdUserInfo userInfo);

	/**
	 *  計画削除登録 を実行する。
	 * @param pointDataDto 付与ポイントDto
	 * @param userInfo ユーザ情報Dto
	 */
	public void deleteDate(String tourokuNo, BirdUserInfo userInfo);
}
