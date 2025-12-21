/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao.UISaleStateInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetSaleStateInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.util.MosChichenSaleStateUtil;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 販売状況一覧情報取得ロジック
 * 
 * @author xlee
 */
public class GetSaleStateInfoLogicImpl implements GetSaleStateInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS017L06";

    /**
     * 販売状況一覧情報DAOを取得します。
     */
    private UISaleStateInfoDao uiSaleStateInfoDao;

    /**
     * 販売状況一覧情報DAOを取得します。
     * @return 販売状況一覧情報DAO
     */
    public UISaleStateInfoDao getUISaleStateInfoDao() {
        return uiSaleStateInfoDao;
    }

    /**
     * 販売状況一覧の詳細情報DAOを設定します。
     * @param uiOnerCdDao 販売状況一覧情報DAO
     */
    public void setUISaleStateInfoDao(UISaleStateInfoDao uiSaleStateInfoDao) {
        this.uiSaleStateInfoDao = uiSaleStateInfoDao;
    }

    /**
     * 販売状況一覧情報を取得
     * @param　sysDate　システム日付
     * @param　ckanriNo　管理番号
     * @param　miseCd　店舗コード
     * @param　menuGroup　メニューグループ
     * @param　taishoDtTo　対象期間の開始日
     * @param　taishoDtFrom　対象期間の終了日
     * 
     * @return  販売状況一覧情報
     */
    public List execute(String sysDate, String ckanriNo, String miseCd, String menuGroupCd, String taishoDtTo, String taishoDtFrom) {
    	//エラー処理：
    	if(MosChichenSaleStateUtil.isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 システム日付
        }
    	if(MosChichenSaleStateUtil.isNull(ckanriNo)){
            throw new NotNullException("管理番号"); //E0506 管理番号
        }
    	if(MosChichenSaleStateUtil.isNull(miseCd)){
            throw new NotNullException("店舗コード"); //E0506 店舗コード
        }
    	if(MosChichenSaleStateUtil.isNull(menuGroupCd)){
            throw new NotNullException("メニューグループ"); //E0506 メニューグループ
        }    	
    	if(MosChichenSaleStateUtil.isNull(taishoDtTo)){
            throw new NotNullException("対象期間の開始日"); //E0506 対象期間の開始日
        }
    	if(MosChichenSaleStateUtil.isNull(taishoDtFrom)){
            throw new NotNullException("対象期間の終了日"); //E0506 対象期間の終了日
        }
    	
    	List tmpResult = getUISaleStateInfoDao().getSaleStateInfo(sysDate, ckanriNo, miseCd, menuGroupCd, taishoDtTo, taishoDtFrom);
        
        return tmpResult;
    }	
}
