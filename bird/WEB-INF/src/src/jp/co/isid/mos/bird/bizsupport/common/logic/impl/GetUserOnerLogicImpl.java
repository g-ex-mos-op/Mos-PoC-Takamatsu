/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.common.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dao.MSTUserMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.common.dao.MSTUserOnerInfoDao;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.MSTUserMiseInfo;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetUserOnerLogic;

/**
 * ユーザー対応オーナーの検索logic
 * @author itamoto
 */
public class GetUserOnerLogicImpl implements GetUserOnerLogic {

    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS001L01";
    
    /* MSTUserOnerInfoDao */
    private MSTUserOnerInfoDao msTUserOnerInfoDao;
    /* MSTUserMiseInfoDao */
    private MSTUserMiseInfoDao msTUserMiseInfoDao;

    /* 初期日付 */
    private final String DEFAULT_DATE = "99991231"; 
    
    /**
	 * ユーザー対応オーナー情報Daoの設定
	 * @return msTUserOnerInfoDao を戻します。
	 */
	public MSTUserOnerInfoDao getMsTUserOnerInfoDao() {
		return msTUserOnerInfoDao;
	}
	/**
	 * ユーザー対応オーナー情報Daoの設定
	 * @param msTUserOnerInfoDao msTUserOnerInfoDao を設定。
	 */
	public void setMsTUserOnerInfoDao(MSTUserOnerInfoDao msTUserOnerInfoDao) {
		this.msTUserOnerInfoDao = msTUserOnerInfoDao;
	}
	
	/**
	 * ユーザー対応店舗情報Daoの取得
	 * @return msTUserMiseInfoDao を戻します。
	 */
	public MSTUserMiseInfoDao getMsTUserMiseInfoDao() {
		return msTUserMiseInfoDao;
	}
	/**
	 * ユーザー対応店舗情報Daoの取得
	 * @param msTUserMiseInfoDao msTUserMiseInfoDao を設定。
	 */
	public void setMsTUserMiseInfoDao(MSTUserMiseInfoDao msTUserMiseInfoDao) {
		this.msTUserMiseInfoDao = msTUserMiseInfoDao;
	}

    /**
     * ユーザ対応店舗クローズ日の取得
     * @param PlRegistDto
     */
    public void getMiseCloseDt(PlRegistDto plRegistDto, String miseCd) {
        
        if (plRegistDto.getMsTUserMiseInfoList() != null) {
            for (Iterator i = plRegistDto.getMsTUserMiseInfoList().iterator(); i.hasNext();) {
                MSTUserMiseInfo entity = (MSTUserMiseInfo) i.next();
                if(miseCd.equals(entity.getMiseCd())) {
                    if(DEFAULT_DATE.equals(entity.getCloseDt().trim())){
                        plRegistDto.setMiseCloseDate("");
                    }
                    else {
                        plRegistDto.setMiseCloseDate(entity.getCloseDt());
                    }
                    break;
                }
            }
        }
    }
    
    
	/**
     * ユーザ対応オーナー店舗情報の検索・設定
     * @param userId
     * @param PlRegistDto
     */
    public List execute(String userId, PlRegistDto plRegistDto) {
        
    	// Dao【ユーザー対応オーナー情報.オーナーコードの取得】を実行する。
    	plRegistDto.setMsTUserOnerInfo(msTUserOnerInfoDao.getOnerCd(userId));
        plRegistDto.setOnerCd(plRegistDto.getMsTUserOnerInfo().getOnerCd());
    	
        execute(plRegistDto);
        
    	return null;
    }
    
	/**
     * ユーザー対応店舗情報の取得
     * @param PlRegistDto
     */
    public List execute(PlRegistDto plRegistDto) {
     
        // Dao【ユーザー対応店舗情報.ユーザー対応店舗情報の取得】を実行する。
        plRegistDto.setMsTUserMiseInfoList(msTUserMiseInfoDao.getMiseCdByClose (
                plRegistDto.isCloseMiseFlg(),
                plRegistDto.getOnerCd(),
                plRegistDto.getTargetYM()
                ));
        
    	return null;
    }
    
}
