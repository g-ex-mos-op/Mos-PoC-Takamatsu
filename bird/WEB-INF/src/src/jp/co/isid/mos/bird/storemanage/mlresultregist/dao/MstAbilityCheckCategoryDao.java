/*
 * 作成日: 2006/07/27
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.MstAbilityCheckCategory;

/**
 * マスターライセンス能力チェックカテゴリーDAO
 * 
 * @author xyuchida
 */
public interface MstAbilityCheckCategoryDao {

    public static final Class BEAN = MstAbilityCheckCategory.class;

    public static final String selectCategory_QUERY = "order by SORT_SEQ";

    public List selectCategory();
}
