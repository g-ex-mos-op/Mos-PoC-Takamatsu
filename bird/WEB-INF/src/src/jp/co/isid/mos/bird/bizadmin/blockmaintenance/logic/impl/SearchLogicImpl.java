/**
 * 2007/04/18
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.dao.UIMiseBlockInfoDao;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * ブロックメンテナンス
 * 検索対象情報取得ロジック
 * 
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BlockMaintenanceUtil.LOGIC_ID_SEARCH;
    /** DAO【店舗ブロック設定情報】*/
    private UIMiseBlockInfoDao blockMainteUIMiseBlockInfoDao;
    
    /**
     * 事前条件処理
     * @param map
     * @throws MissingDataException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        String sysDate = (String)params.get(PK_SYSDATE);
        if(BlockMaintenanceUtil.isNull(sysDate)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("システム日付");
        }
        String companyCd=(String)params.get(PK_COMPANY_CD);
        if(BlockMaintenanceUtil.isNull(companyCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("会社コード");
        }
        String sibuCd = (String)params.get(PK_SIBU_CD);
        if(BlockMaintenanceUtil.isNull(sibuCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("支部コード");
        }
    }
	/**
	 * 実行処理
     * 
     * @param params
     * @return
	 */
	public List execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        
        //２．リターン値Listをインスタンス化する。
        List list = new ArrayList();
        String companyCd=(String)params.get(PK_COMPANY_CD);
        String sibuCd = (String)params.get(PK_SIBU_CD);
        String sysDate = (String)params.get(PK_SYSDATE);
        //３．Dao【店舗ブロック設定情報】の検索を実行する。
        list = getBlockMainteUIMiseBlockInfoDao().select(companyCd, sibuCd, sysDate);
        
        //４．リターン値Listをリターンする。
        return list;
	}
	/**
	 * @return blockMainteUIMiseBlockInfoDao を戻します。
	 */
	public UIMiseBlockInfoDao getBlockMainteUIMiseBlockInfoDao() {
		return blockMainteUIMiseBlockInfoDao;
	}
	/**
	 * @param blockMainteUIMiseBlockInfoDao 設定する blockMainteUIMiseBlockInfoDao。
	 */
	public void setBlockMainteUIMiseBlockInfoDao(
			UIMiseBlockInfoDao blockMainteUIMiseBlockInfoDao) {
		this.blockMainteUIMiseBlockInfoDao = blockMainteUIMiseBlockInfoDao;
	}

}
