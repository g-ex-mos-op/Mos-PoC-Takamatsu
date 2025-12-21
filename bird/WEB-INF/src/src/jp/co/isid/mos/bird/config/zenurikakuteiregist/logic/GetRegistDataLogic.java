/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.logic;

import java.util.List;

/**
 * [機能設定]【売上速報前月売上確定登録】
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
	 * @param sysDate システム日付
	 * @return List 編集情報
	 * 
	 */
	public List execute(String sysDate);

}
