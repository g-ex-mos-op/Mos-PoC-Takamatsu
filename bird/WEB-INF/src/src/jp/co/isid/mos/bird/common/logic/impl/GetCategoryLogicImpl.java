package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.MstCategoryInfoDao;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * @author xnkusama
 */
public class GetCategoryLogicImpl implements GetCategoryLogic {

    private MstCategoryInfoDao mstCategoryInfoDao;
    
    /**
     *  カテゴリの取得
     * @param String infoShu
     * @return List
     * @exception ApplicationException 
     */
    public List getCategory(String infoShu) throws ApplicationException {
        // カテゴリ取得
        List listCategory = getMstCategoryInfoDao().getCategory(infoShu);

        if (listCategory == null || listCategory.size() == 0) {
            throw new NotExistException("カテゴリ情報");
        }
        
        return listCategory;
    }

    /**
     * @param mstCategoryInfoDao mstCategoryInfoDao を設定。
     */
    public void setMstCategoryInfoDao(MstCategoryInfoDao mstCategoryInfoDao) {
        this.mstCategoryInfoDao = mstCategoryInfoDao;
    }

    /**
     * @return mstCategoryInfoDao を戻します。
     */
    public MstCategoryInfoDao getMstCategoryInfoDao() {
        return mstCategoryInfoDao;
    }   

    /**
     * 事前条件チェック
     * @param infoShu
     * @throws ApplicationException
     */
    private void validate(String infoShu) throws ApplicationException {
        if (isNull(infoShu)) {
            throw new NotNullException("情報種別");
        }
    }
    
    /*
     * null、空文字判定
     */
    private boolean isNull(String str) {
        boolean flg = false;
        if (str == null) {
            flg = true;
        }
        
        if ("".equals(str.trim())) {
            flg = true;
        }
        
        return flg;
    }
}