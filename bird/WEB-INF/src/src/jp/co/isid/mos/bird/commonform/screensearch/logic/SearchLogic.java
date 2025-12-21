/**
 * 
 */
package jp.co.isid.mos.bird.commonform.screensearch.logic;

import java.util.List;

import jp.co.isid.mos.bird.commonform.screensearch.dto.RequestDto;


/**
 * [呼び出し専用共通]メニュー選択画面
 * 
 * 検索処理と検索情報の取得
 * ロジックインターフェース
 * 
 * 作成日:2008/11/20
 * @author xkinu
 *
 */
public interface SearchLogic {
	/**
	 * 実行処理
	 * @param requestDto
	 * @return
	 */
	public List execute(RequestDto requestDto);
}
