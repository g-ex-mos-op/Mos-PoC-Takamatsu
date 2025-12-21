/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.logic;

import jp.co.isid.mos.bird.commonform.menusearch.dto.RequestDto;
import jp.co.isid.mos.bird.commonform.menusearch.dto.SessionDto;

/**
 * [呼び出し専用共通]メニュー選択画面
 * 
 * 条件項目情報の取得と設定
 * ロジックインターフェース
 * 
 * 作成日:2008/08/20
 * @author xkinu
 *
 */
public interface ConditionLogic {
	/**
	 * 実行処理
	 * 
	 * @param sessionDto
	 * @param requestDto
	 */
	public void execute(SessionDto sessionDto, RequestDto requestDto);

}