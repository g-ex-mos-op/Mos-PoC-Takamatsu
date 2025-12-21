/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.config.categoryregist.dao.MstCategoryInfoDao;
import jp.co.isid.mos.bird.config.categoryregist.logic.SearchCategoryLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * @author xyuchida
 *
 */
public class SearchCategoryLogicImpl implements SearchCategoryLogic {

    public static final String LOGIC_ID = "BCF004L01";

    /**
     * 情報カテゴリDAO
     */
    private MstCategoryInfoDao mstCategoryInfoDao;

    /**
     * 情報カテゴリDAOを取得します。
     * @return 情報カテゴリDAO
     */
    public MstCategoryInfoDao getMstCategoryInfoDao() {
        return mstCategoryInfoDao;
    }

    /**
     * 情報カテゴリDAOを設定します。
     * @param mstCategoryInfoDao 情報カテゴリDAO
     */
    public void setMstCategoryInfoDao(MstCategoryInfoDao mstCategoryInfoDao) {
        this.mstCategoryInfoDao = mstCategoryInfoDao;
    }

    /**
     * 情報カテゴリ検索
     * 
     * @param infoShu 情報種別
     * @return 検索結果
     */
    public List execute(String infoShu) {
        if (infoShu == null || infoShu.length() <= 0) {
            throw new NotNullException("情報種別");
        }
        return getMstCategoryInfoDao().getCategory(infoShu);
    }
}
