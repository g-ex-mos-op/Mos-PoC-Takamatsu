/*
 * çÏê¨ì˙: 2006/02/23
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.dao;

import jp.co.isid.mos.bird.config.categoryregist.entity.TrnSubCateId;

/**
 * @author xyuchida
 *
 */
public interface TrnSubCateIdDao {

    public static final Class BEAN = TrnSubCateId.class;

    public static final String getId_ARGS = "infoShu, cateId";
    public static final String getId_SQL
            = "select BR41.ID from BR41SCCT as BR41 where BR41.INFO_SHU = /*infoShu*/'03' and BR41.CATE_ID = /*cateId*/'001'";

    public static final String deleteId_ARGS = "infoShu, cateId";
    public static final String deleteId_SQL
            = "delete from BR41SCCT as BR41 where BR41.INFO_SHU = /*infoShu*/'03' and BR41.CATE_ID = /*cateId*/'001'";

    public String getId(String infoShu, String cateId);

    public int insertId(TrnSubCateId trnSubCateId);

    public int deleteId(String infoShu, String cateId);
}
