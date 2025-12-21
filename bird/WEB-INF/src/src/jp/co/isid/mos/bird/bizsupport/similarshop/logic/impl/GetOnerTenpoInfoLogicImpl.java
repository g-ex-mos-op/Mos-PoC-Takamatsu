/*
 * 作成日: 2006/4/11
 *
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.similarshop.dao.UIOnerTenpoDao;
import jp.co.isid.mos.bird.bizsupport.similarshop.logic.GetOnerTenpoInfoLogic;
import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIOnerTenpoInfo;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * オーナー保有店舗取得ロジック
 * @author Nakajima
 */
public class GetOnerTenpoInfoLogicImpl implements GetOnerTenpoInfoLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BBS008L01";

    /**
     * オーナー保有店舗情報（UIOnerTenpoDao）
     */
    private UIOnerTenpoDao uIOnerTenpoDao;
    
	/**
	 * オーナー保有店舗Daoの設定
	 * @return codCompanyJohoDao を戻します。
	 */
	public UIOnerTenpoDao getUIOnerTenpoDao() {
		return uIOnerTenpoDao;
	}
	/**
	 * オーナー保有店舗Daoの設定
	 * @param UIOnerTenpoDao uIOnerTenpoDao を設定。
	 */
	public void setUIOnerTenpoDao(UIOnerTenpoDao uIOnerTenpoDao) {
		this.uIOnerTenpoDao = uIOnerTenpoDao;
	}

	/**
	 * オーナー保有店舗の検索
	 * @return 検索結果
	 */
	public List execute(String sysdate, String onerCd, String nenGetu) {
		
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

        
		//オーナー保有店取得
        List hoyuuTenpoList = uIOnerTenpoDao.getOnerTenpoInfo(onerCd, prevPlYm);
        
    	//CLOSE店表示セット
    	//if (hoyuuTenpoList != null) {
        //    for (Iterator i = hoyuuTenpoList.iterator(); i.hasNext();) {
        //    	UIOnerTenpoInfo entity = (UIOnerTenpoInfo) i.next();
        //    	if(Integer.valueOf(entity.getCloseDt()).intValue() < Integer.valueOf(sysdate).intValue()){
        //    		entity.setMiseNameKj(entity.getMiseNameKj() + "（CLOSE）");
        //    	}
        //    }
    	//}
        
		//CLOSE店編集処理
        //結果の件数を取得し、for分でまわす。CLOSE店にはCLOSEを付与する。Array
        //hoyuuTenpoList.
		return hoyuuTenpoList;
	}
}
