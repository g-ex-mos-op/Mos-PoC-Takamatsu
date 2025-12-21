/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic;

import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;

/**
 * [機能設定]【売上速報設定】
 * 編集情報登録
 * ロジックインターフェース
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface RegistLogic {
	/**
	 * 実行処理
	 * 
	 * @param taishoYm 対象年月
	 * @return List 編集情報
	 * 
	 * ＜List[[編集情報]]＞
	 * index0:前月末店舗数
	 * index1:テレビCM放映日
	 * index2:商品メニュー
	 * index3:キャンペーン
	 */
	public void execute(SessionDto sessionDto);

}
