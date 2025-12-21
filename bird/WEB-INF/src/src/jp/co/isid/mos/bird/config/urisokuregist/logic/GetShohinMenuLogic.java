/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic;

import java.util.List;

/**
 * [機能設定]【売上速報設定】
 * 商品メニュー取得
 * ロジックインターフェース
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface GetShohinMenuLogic {
	/**
	 * 実行処理
	 * 
	 * @param taishoYm 当年月
	 * @param frameKbn フレーム区分
	 * @return List 商品メニュー情報
	 * @return
	 */
	public List execute(String taishoYm, String frameKbn);
}
