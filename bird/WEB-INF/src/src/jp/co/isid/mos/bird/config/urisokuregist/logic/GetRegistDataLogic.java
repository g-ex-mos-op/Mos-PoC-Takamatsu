/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic;

import java.util.List;

/**
 * [機能設定]【売上速報設定】
 * 編集情報取得
 * ロジックインターフェース
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface GetRegistDataLogic {
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
	public List execute(String taishoYm);

}
