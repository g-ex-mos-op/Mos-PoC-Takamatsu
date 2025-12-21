package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.MstSubCategoryInfoDao;
import jp.co.isid.mos.bird.common.logic.GetSubCategoryLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * @author xnkusama
 */
public class GetSubCategoryLogicImpl implements GetSubCategoryLogic {

    private MstSubCategoryInfoDao mstSubCategoryInfoDao;
    
    /**
     * サブカテゴリの取得
     * @param String infoShu
     * @param String cateId
     * @return List
     * @exception ApplicationException 
     */
    public List getSubCategory(String infoShu, String cateId) throws ApplicationException {
        // 事前条件チェック
        validate(infoShu, cateId);
        // カテゴリ取得
        List listSubCategory = getMstSubCategoryInfoDao().getSubCategory(infoShu, cateId);

        if (listSubCategory == null || listSubCategory.size() == 0) {
            throw new NotExistException("サブカテゴリ情報");
        }
        
        return listSubCategory;
    }

    /**
     * @param mstSubCategoryInfoDao mstSubCategoryInfoDao を設定。
     */
    public void setMstSubCategoryInfoDao(MstSubCategoryInfoDao mstSubCategoryInfoDao) {
        this.mstSubCategoryInfoDao = mstSubCategoryInfoDao;
    }

    /**
     * @return mstSubCategoryInfoDao を戻します。
     */
    public MstSubCategoryInfoDao getMstSubCategoryInfoDao() {
        return mstSubCategoryInfoDao;
    }
    
    /**
     * 事前条件チェック
     * @param infoShu
     * @param cateId
     * @throws ApplicationException
     */
    private void validate(String infoShu, String cateId) throws ApplicationException {
        if (isNull(infoShu)) {
            throw new NotNullException("情報種別");
        }
        
        if (isNull(cateId)) {
            throw new NotNullException("カテゴリID");
        }
    }
    
    /*
     * null、空文字判定
     */
    private boolean isNull(String str) {
        boolean flg = false;
        if (str == null || "".equals(str.trim())) {
            flg = true;
        }
        
        return flg;
    }
}