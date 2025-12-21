/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.dao.UIRirekiInfoDao;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetRirekiInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * オーナーコード報取得ロジック
 * 
 * @author xlee
 */
public class GetRirekiInfoLogicImpl implements GetRirekiInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS011L07";

    /**
     * 商品別履歴情報DAOを取得します。
     */
    private UIRirekiInfoDao uIRirekiInfoDao;

    /**
     * 商品別履歴情報DAOを取得します。
     * @return 商品別履歴情報DAO
     */
    public UIRirekiInfoDao getUIMeisaiInfoDao() {
        return uIRirekiInfoDao;
    }

    /**
     * 商品別履歴情報DAOを設定します。
     * @param uiMeisaiInfoDao 商品別履歴細情報DAO
     */
    public void setUIRirekiInfoDao(UIRirekiInfoDao uIRirekiInfoDao) {
        this.uIRirekiInfoDao = uIRirekiInfoDao;
    }

    /**
     * 商品別履歴情報を取得
     * @param　miseCd　　店コード
     * @param　shoJituCd　実商品コード
     * @param　condYMStr　売掛年月（START）
     * @param　condYMEnd　売掛年月（END）
     * 
     * @return  前年同月対象当年明細（同月売上）
     */
    public List execute(String miseCd, String shoJituCd, String condYMStr, String condYMEnd) {

    	//エラー処理：
    	if(isNull(miseCd)){
            throw new NotNullException("店コード"); //E0506 店コード
        }

    	//エラー処理：
    	if(isNull(shoJituCd)){
            throw new NotNullException("実商品コード"); //E0506 実商品コード
        }
    	
    	//エラー処理：
    	if(isNull(condYMStr)){
            throw new NotNullException("対象年月"); //E0506 対象年月
        }
    	
    	//エラー処理：
    	if(isNull(condYMEnd)){
            throw new NotNullException("対象年月"); //E0506 対象年月
        }
    	
    	List tmpResult = (List) getUIMeisaiInfoDao().getRirekiInfo(miseCd, shoJituCd, condYMStr, condYMEnd);
        
        return tmpResult;
    }
    
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }    
}
