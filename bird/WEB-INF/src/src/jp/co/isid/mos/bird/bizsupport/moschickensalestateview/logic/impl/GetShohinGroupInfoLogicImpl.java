/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao.UIShohinGroupInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetShohinGroupInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.util.MosChichenSaleStateUtil;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 商品グループ情報取得ロジック
 * 
 * @author xlee
 */
public class GetShohinGroupInfoLogicImpl implements GetShohinGroupInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS017L03";

    /**
     * 商品グループ情報DAOを取得します。
     */
    private UIShohinGroupInfoDao uiShohinGroupInfoDao;

    /**
     * 商品グループ情報DAOを取得します。
     * @return 商品グループ情報DAO
     */
    public UIShohinGroupInfoDao getUIShohinGroupInfoDao() {
        return uiShohinGroupInfoDao;
    }

    /**
     * 商品グループ情報DAOを設定します。
     * @param uiShohinGroupInfoDao 商品グループ情報DAO
     */
    public void setUIShohinGroupInfoDao(UIShohinGroupInfoDao uiShohinGroupInfoDao) {
        this.uiShohinGroupInfoDao = uiShohinGroupInfoDao;
    }

    /**
     * 商品グループ情報を取得
     * @param　titleKanriNo タイトル管理番号
     * @return 　商品グループリスト
     */
    public List execute(String titleKanriNo) {

    	//エラー処理：
    	if(MosChichenSaleStateUtil.isNull(titleKanriNo)){
            throw new NotNullException("キャンペーンタイトルの管理番号"); //E0506 ユーザーID
        }
        
    	List tmpResult = getUIShohinGroupInfoDao().getShohinGroupInfo(titleKanriNo);
    	
        return tmpResult;
    }
}
