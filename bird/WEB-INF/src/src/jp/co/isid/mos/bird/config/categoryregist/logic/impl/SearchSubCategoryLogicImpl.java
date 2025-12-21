/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.config.categoryregist.dao.MstSubCategoryInfoDao;
import jp.co.isid.mos.bird.config.categoryregist.logic.SearchSubCategoryLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * @author xyuchida
 *
 */
public class SearchSubCategoryLogicImpl implements SearchSubCategoryLogic {

    public static final String LOGIC_ID = "BCF004L02";

    /**
     * 情報サブカテゴリDAO
     */
    private MstSubCategoryInfoDao mstSubCategoryInfoDao;

    /**
     * 情報サブカテゴリDAOを取得します。
     * @return 情報サブカテゴリDAO
     */
    public MstSubCategoryInfoDao getMstSubCategoryInfoDao() {
        return mstSubCategoryInfoDao;
    }

    /**
     * 情報サブカテゴリDAOを設定します。
     * @param mstSubCategoryInfoDao 情報サブカテゴリDAO
     */
    public void setMstSubCategoryInfoDao(
            MstSubCategoryInfoDao mstSubCategoryInfoDao) {
        this.mstSubCategoryInfoDao = mstSubCategoryInfoDao;
    }

    /**
     * 情報サブカテゴリ検索
     * 
     * @param infoShu 情報種別
     * @param cateId カテゴリID
     * @return 検索結果
     */
    public List execute(String infoShu, String cateId) {
        if (infoShu == null || infoShu.length() <= 0) {
            throw new NotNullException("情報種別");
        }
        if (cateId == null || cateId.length() <= 0) {
            throw new NotNullException("カテゴリ");
        }
        return getMstSubCategoryInfoDao().getSubCategory(infoShu, cateId);
    }
}
