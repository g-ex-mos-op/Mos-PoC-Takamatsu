/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.logic;

import jp.co.isid.mos.bird.config.zenurikakuteiregist.dto.SessionDto;

/**
 * [機能設定]【売上速報前月売上確定登録】
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
	 * @param sessionDto DTO【Session】
	 */
	public void execute(SessionDto sessionDto);

}
