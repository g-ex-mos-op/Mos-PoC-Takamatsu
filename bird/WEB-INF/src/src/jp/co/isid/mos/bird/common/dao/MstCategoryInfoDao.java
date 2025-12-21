package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;



/**
 *カテゴリ情報DAO
 * @author xnkusama
 */
public interface MstCategoryInfoDao {

    public Class BEAN = MstCategoryInfo.class;
    
    public static final String getCategory_ARGS = "infoShu";

    /**
     * カテゴリ一覧の取得
     * @param String infoShu
     * @return
     */
    public List getCategory(String infoShu);
    
}
