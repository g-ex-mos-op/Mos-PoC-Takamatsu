/*
 * 作成日: 2006/4/12
 *
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.logic.impl;

import jp.co.isid.mos.bird.bizsupport.similarshop.logic.GetTenpoInfoLogic;
import jp.co.isid.mos.bird.bizsupport.similarshop.dao.UIOnerTenpoDao;
import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIOnerTenpoInfo;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 対象店舗情報の取得ロジックアクション
 * @author Nakajima
 */
public class GetTenpoInfoLogicImpl implements GetTenpoInfoLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BBS008L04";

    /**
     * 対象店舗情報（UIOnerTenpoDao）
     */
    private UIOnerTenpoDao uIOnerTenpoDao;
    
	/**
	 * 店舗情報Daoの設定
	 * @return uIOnerTenpoDao を戻します。
	 */
	public UIOnerTenpoDao getUIOnerTenpoDao() {
		return uIOnerTenpoDao;
	}
	/**
	 * 店舗情報Daoの設定
	 * @param UIOnerTenpoDao uIOnerTenpoDao を設定。
	 */
	public void setUIOnerTenpoDao(UIOnerTenpoDao uIOnerTenpoDao) {
		this.uIOnerTenpoDao = uIOnerTenpoDao;
	}
    
    
	/**
	 * 対象店舗情報の検索を行う
	 * @return 検索結果
	 */
	public UIOnerTenpoInfo execute(String sysdate, String miseCd, String nenGetu, String userId, String userTypeCd, boolean limitFlg) {

		//対象月(YYYYMM形式)取得
        String prevPlYm = null;
        try {
        	//15日までは前々月、16日以降は前月データを用いる
        	if(Integer.valueOf(sysdate.substring(6,8)).intValue() < 16){
        		//前々月
        		prevPlYm = DateManager.getPrevMonth(nenGetu, 2);
        	}else{
        		//前月
        		prevPlYm = DateManager.getPrevMonth(nenGetu, 1);
        	}
        } catch (Exception e) {
            // 無処理
        }
		
       
		//対象店舗情報取得
        return uIOnerTenpoDao.getTenpoInfo(miseCd, prevPlYm, userId, userTypeCd, limitFlg);

	}
}
