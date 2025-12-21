/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.impl;

import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.codbalancelist.common.CodBalanceListUtil;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.dto.RequestDto;
import jp.co.isid.mos.bird.framework.logic.DownloadFinalLogic;

/**
 * @author xkinu
 *
 */
public class DownloadFinalLogicImpl implements DownloadFinalLogic {
    
    /** ロジックID */
    public static final String LOGIC_ID = CodBalanceListUtil.LOGIC_ID_CSVOUTPUT_FINAL;

	/**
	 * 実行処理
	 * 
	 * @see jp.co.isid.mos.bird.framework.logic.DownloadFinalLogic#execute(java.util.Map)
	 */
	public void execute(Map params) {
		//１．パラメーター例外オブジェクトを取得する。
		Exception ex = (Exception)params.get(DownloadFinalLogic.PK_EXCEPTION);
		if(ex != null) {
			//２．パラメーター例外オブジェクトがnullでない場合は、
			//　　パラメーター.CSV出力用DTO(自画面リクエスト用DTO).例外オブジェクトへ
			//    パラメーター例外オブジェクト設定する。
			RequestDto dto = (RequestDto)params.get(DownloadFinalLogic.PK_CSVOUTPUTDTO);
			dto.setExceptionObj(ex);
		}
	}

}
