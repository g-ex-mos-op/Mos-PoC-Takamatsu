/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.codbalancelist.common.CodBalanceListUtil;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.dao.UICodBalanceListDao;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.CodBalanceListDataLogic;

/**
 * COD残高一覧
 * COD残高一覧取得ロジック
 * 
 * @author xkinu
 *
 */
public class CodBalanceListDataLogicImpl implements
		CodBalanceListDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CodBalanceListUtil.LOGIC_ID_GETCODDATA;
    /** DAO【COD残高一覧】*/
    private UICodBalanceListDao uiCodBalanceListDao;
        


	/** (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.GetCodBalanceListDataLogic#execute(java.util.Map)
	 */
	public Map execute(Map params) {
		Map rparam = new HashMap();
        List listDatas  = new ArrayList();
        if ( params != null && params.get(COMPANY_CD) != null && (String)params.get(ONER_CD) != null ) {
            listDatas = getUiCodBalanceListDao().selectCod((String)params.get(COMPANY_CD),(String)params.get(ONER_CD));    
        } else {
            listDatas = getUiCodBalanceListDao().select();
        }
		rparam.put(RK_LIST_GETDATA, listDatas);
		return rparam;
	}
	/**
	 * DAO【COD残高一覧】取得処理
	 * 
	 * @return uiCodBalanceListDao を戻します。
	 */
	public UICodBalanceListDao getUiCodBalanceListDao() {
		return uiCodBalanceListDao;
	}


	/**
	 * DAO【COD残高一覧】設定処理
	 * 
	 * @param uiCodBalanceListDao 設定する uiCodBalanceListDao。
	 */
	public void setUiCodBalanceListDao(UICodBalanceListDao uiCodBalanceListDao) {
		this.uiCodBalanceListDao = uiCodBalanceListDao;
	}


}
