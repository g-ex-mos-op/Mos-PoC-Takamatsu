package jp.co.isid.mos.bird.bizsupport.spot.logic.impl;


import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.spot.dao.CampOrderManageDao;
import jp.co.isid.mos.bird.bizsupport.spot.logic.CampListLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 対象キャンペーンのプルダウンLIST取得ロジック
 *
 * @author xsong
 */
public class CampListLogicImpl implements CampListLogic {

	/**  ロジックID  */
    public static final String LOGIC_ID = "BBS028L01";

    /** 対象キャンペーン受注管理情報Daoクラス */
    private CampOrderManageDao campOrderMngDao;
    
    
    /**
     * 対象キャンペーンのプルダウンリストを取得します。
     * @param sysDate システム日付
     * @return resultList 対象キャンペーンのプルダウンリスト 
     */
	public List getList(String sysDate) {
	
		//入力値チェック
		validate(sysDate);
		
		String sysDateOne = null;
		String sysDateThr = null;
				
		try{
        	sysDateOne = DateManager.getPrevDate(sysDate, 1);
			sysDateThr = DateManager.getPrevDate(sysDate, 3);
        
		}catch(Exception e){
            throw new NotNullException("システム日付");
        }
		
		List resultList = new ArrayList();
		
		if((sysDateOne != null && sysDateThr != null)) {
		
			return getCampOrderMngDao().getCampExisDatatList(sysDateOne,sysDateThr);
		
		}
		
       return resultList;
	}


    /**
     * 対象キャンペーン受注管理情報Daoを取得します。
     * @return campOrderMngDao 対象キャンペーン受注管理情報Dao
     */
	public CampOrderManageDao getCampOrderMngDao() {
		return campOrderMngDao;
	}
    
	/**
	 * 対象キャンペーン受注管理情報Daoを設定します。
	 * @param campOrderMngDao 対象キャンペーン受注管理情報Dao
	 */
	public void setCampOrderMngDao(CampOrderManageDao campOrderMngDao) {
		this.campOrderMngDao = campOrderMngDao;
	}

	/**
     * 入力値チェック
     *@param sysDate システム日付
     */
    private void validate(String sysDate) {

        if (sysDate == null || sysDate.trim().length() == 0) {
            throw new NotNullException("システム日付");
        }
        
     }
}