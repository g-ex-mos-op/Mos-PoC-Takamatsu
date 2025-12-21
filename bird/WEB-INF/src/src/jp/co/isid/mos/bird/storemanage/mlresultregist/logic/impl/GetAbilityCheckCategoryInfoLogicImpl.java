/*
 * 作成日: 2006/07/27
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.MstAbilityCheckCategoryDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckCategoryInfoLogic;

/**
 * マスターライセンス能力チェックカテゴリー情報取得ロジック 
 * 
 * @author xyuchida
 */
public class GetAbilityCheckCategoryInfoLogicImpl implements
        GetAbilityCheckCategoryInfoLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BSM008L10";

    private MstAbilityCheckCategoryDao mstAbilityCheckCategoryDao;

    public MstAbilityCheckCategoryDao getMstAbilityCheckCategoryDao() {
        return mstAbilityCheckCategoryDao;
    }
    public void setMstAbilityCheckCategoryDao(
            MstAbilityCheckCategoryDao mstAbilityCheckCategoryDao) {
        this.mstAbilityCheckCategoryDao = mstAbilityCheckCategoryDao;
    }

    /**
     * カテゴリー情報取得
     * 
     * @return カテゴリー情報リスト
     */
    public List execute() {
        return getMstAbilityCheckCategoryDao().selectCategory();
    }
}
