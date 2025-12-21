/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.codbalancelist.common.CodBalanceListUtil;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.CodBalanceListDataLogic;

/**
 * COD残高一覧
 * 初期情報の取得ロジック
 * 
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CodBalanceListUtil.LOGIC_ID_CONDITION;
        
	/** LOGIC【COD残高一覧取得】*/
	private CodBalanceListDataLogic codBalanceListDataLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.ConditionLogic#execute(java.util.Map)
	 */
	public Map execute(Map params) {
        //１．ロジック【COD残高一覧取得】を実行し、戻り値Mapを取得。
        Map rparam = getCodBalanceListDataLogic().execute(params);
            	
        //２．処理１の戻り値Map.[[COD残高一覧]]を変数[[CSVデータ]]へ代入する。
        List listDatas = (List)rparam.get(ConditionLogic.RK_LIST_GETDATA);
        
        //３．変数[[CSVデータ]]件数を判断し下記の処理を行う。
        if(listDatas.size() == 0) {
            //３－A．該当データがない場合。
        	//戻り値Mapへ新規でダウンロード許可フラグにfalseを設定する。
        	rparam.put(ConditionLogic.RK_DOWNLOAD_FLG, new Boolean(false));
        	//戻り値Mapへ新規でメッセージに「現在ＣＯＤオーナーは存在しません。」を設定する。
        	rparam.put(ConditionLogic.RK_MSG, "現在ＣＯＤオーナーは存在しません。");
        }
        else {
        	//３－B．該当データがある場合
            //戻り値Map.ダウンロード許可フラグをtrueを設定する。
        	rparam.put(ConditionLogic.RK_DOWNLOAD_FLG, new Boolean(true));
        }
		//４．処理１の戻り値Mapをリターンする。
		return rparam;
	}

	/**
	 * LOGIC【COD残高一覧取得】取得処理
	 * 
	 * @return codBalanceListDataLogic を戻します。
	 */
	public CodBalanceListDataLogic getCodBalanceListDataLogic() {
		return codBalanceListDataLogic;
	}

	/**
	 * LOGIC【COD残高一覧取得】設定処理
	 * 
	 * @param codBalanceListDataLogic 設定する codBalanceListDataLogic。
	 */
	public void setCodBalanceListDataLogic(
			CodBalanceListDataLogic codBalanceListDataLogic) {
		this.codBalanceListDataLogic = codBalanceListDataLogic;
	}

}
