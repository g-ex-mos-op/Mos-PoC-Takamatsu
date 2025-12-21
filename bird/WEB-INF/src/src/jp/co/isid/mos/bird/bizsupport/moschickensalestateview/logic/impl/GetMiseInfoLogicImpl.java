/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao.UIMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.util.MosChichenSaleStateUtil;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 店舗情報取得ロジック
 *
 * @author xlee
 */
public class GetMiseInfoLogicImpl implements GetMiseInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS017L02";

    /**
     * 店舗情報DAOを取得します。
     */
    private UIMiseInfoDao uiMiseInfoDao;

    /**
     * 店舗情報DAOを取得します。
     * @return 店舗情報DAO
     */
    public UIMiseInfoDao getUIMiseInfoDao() {
        return uiMiseInfoDao;
    }

    /**
     * 店舗情報DAOを設定します。
     * @param uiMiseInfoDao 店舗情報DAO
     */
    public void setUIMiseInfoDao(UIMiseInfoDao uiMiseInfoDao) {
        this.uiMiseInfoDao = uiMiseInfoDao;
    }

    /**
     * 店舗情報を取得
     * @param　onerCdList オーナーコード
     * @param userType ユーザータイプ
     * @param companyCd 会社コード
     * @return  店舗リスト
     */

    public List execute(String onerCd, String userID, String userType, String companyCd) {

    	List tmpResult = new ArrayList();

        if(userType.equals(MosChichenSaleStateUtil.USER_TYPE_HONBU)) {
        	tmpResult = getUIMiseInfoDao().getHonbuUserMiseInfo(companyCd, onerCd);
        } if(userType.equals(MosChichenSaleStateUtil.USER_TYPE_ONER)) {
            tmpResult = getUIMiseInfoDao().getOnerUserMiseInfo(companyCd, userID);
        } else if(userType.equals(MosChichenSaleStateUtil.USER_TYPE_TENPO)) {
        	tmpResult = getUIMiseInfoDao().getTenpoUserMiseInfo(companyCd, userID);
        }

    	if (tmpResult == null || tmpResult.size() == 0) {
    		if(userType.equals(MosChichenSaleStateUtil.USER_TYPE_HONBU) ||
    				userType.equals(MosChichenSaleStateUtil.USER_TYPE_ONER)) {
    			throw new NotExistException("オーナーコード"); //E0103 店舗情報
    		} else if(userType.equals(MosChichenSaleStateUtil.USER_TYPE_TENPO)) {
    			throw new NotExistException("対応店舗情報"); //E0103 店舗情報
    		}
        }
        return tmpResult;
    }
}
