/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic;

import java.util.Map;

import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;

/**
 * 入力データチェックロジックインターフェース
 * 
 * 作成日:2008/08/19
 * @author xkinu
 *
 */
public interface CheckInputDataLogic {
    /** チェック対象データ区分:全てチェック */
    public static final String DATA_KBN_ALL = "ALL";
    /** エラーリターン項目:メインタブキー */
    public static final String RP_ERR_TAB_MAIN = "tabMain";
    /** エラーリターン項目:サブタブキー */
    public static final String RP_ERR_TAB_SUB = "tabSub";
    /** エラーリターン項目:例外 */
    public static final String RP_ERR_EXCEPTION = "exption";
    
	/**
	 * 実行処理
	 * 
	 * @param sessionDto DTO【Session】
	 * @param dataKbn チェック対象データ区分（VIEW_IDが指定されます。)
	 * @return TODO
	 */
	public Map execute(SessionDto sessionDto, String dataKbn);

}
