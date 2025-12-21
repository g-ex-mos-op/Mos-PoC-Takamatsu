package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstSubCategoryInfo;



/**
 *カテゴリ情報DAO
 * @author xnkusama
 */
public interface MstSubCategoryInfoDao {

    public Class BEAN = MstSubCategoryInfo.class;
    
    public static final String getSubCategory_ARGS = "infoShu,cateId";

    /**
     * サブカテゴリ一覧の取得
     * @param String infoShu
     * @param String cateId
     * @return
     */
    public List getSubCategory(String infoShu, String cateId);
    
}
