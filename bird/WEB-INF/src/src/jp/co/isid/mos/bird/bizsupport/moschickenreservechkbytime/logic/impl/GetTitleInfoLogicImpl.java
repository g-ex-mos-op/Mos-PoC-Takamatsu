/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao.UITitleInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.GetTitleInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.util.MosChickenReserveChkBytimeUtil;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * タイトル報取得ロジック
 * 
 * @author xlee
 */
public class GetTitleInfoLogicImpl implements GetTitleInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS019L01";

    /**
     * タイトルDAOを取得します。
     */
    private UITitleInfoDao uiTitleInfoDao;

    /**
     * タイトルDAOを取得します。
     * @return タイトルDAO
     */
    public UITitleInfoDao getUITitleInfoDao() {
        return uiTitleInfoDao;
    }

    /**
     * タイトルDAOを設定します。
     * @param uiTitleInfoDao タイトルDAO
     */
    public void setUITitleInfoDao(UITitleInfoDao uiTitleInfoDao) {
        this.uiTitleInfoDao = uiTitleInfoDao;
    }
    
    /**
     * タイトル情報を設定します。
     * @param sysDate システム日付
     * @return タイトルリスト
     */
	public List execute(String sysDate) {
    	//エラー処理：
    	if(MosChickenReserveChkBytimeUtil.isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 システム日付
        }
        
    	List tmpResult = getUITitleInfoDao().getTitleInfo(sysDate);
        
        return tmpResult;
	}
}
