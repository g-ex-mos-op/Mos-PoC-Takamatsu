/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao.UITitleInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetTitleInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.util.MosChichenSaleStateUtil;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * タイトル報取得ロジック
 * 
 * @author xlee
 */
public class GetTitleInfoLogicImpl implements GetTitleInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS017L01";

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
     * @param condFromDt 対象期間開始日
     * @param condToDt 対象期間終了日
     * @return タイトルリスト
     */
	public List execute(String condFromDt, String condToDt) {
    	//エラー処理：
    	if(MosChichenSaleStateUtil.isNull(condFromDt)){
            throw new NotNullException("対象期間開始日"); //E0506 対象期間開始日
        }
    	
    	if(MosChichenSaleStateUtil.isNull(condToDt)){
            throw new NotNullException("対象期間終了日"); //E0506 対象期間終了日
        }
        
    	List tmpResult = getUITitleInfoDao().getTitleInfo(condFromDt, condToDt);
        
    	if (tmpResult == null || tmpResult.size() == 0) {
            throw new NoResultException(); //E0103 キャンペーンタイトル情報
        }
        return tmpResult;
	}
}
