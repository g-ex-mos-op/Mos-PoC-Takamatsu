/*
 * çÏê¨ì˙: 2006/02/23
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.dao;

import jp.co.isid.mos.bird.config.categoryregist.entity.TrnCateId;

/**
 * @author xyuchida
 *
 */
public interface TrnCateIdDao {

    public static final Class BEAN = TrnCateId.class;

    public static final String getId_SQL
            = "select BR40.ID from BR40CTCT as BR40 where BR40.INFO_SHU = /*infoShu*/'01'";

    public static final String deleteId_SQL
            = "delete from BR40CTCT as BR40 where BR40.INFO_SHU = /*infoShu*/'01'";

    public String getId(String infoShu);

    public int insertId(TrnCateId trnCateId);

    public int deleteId(String infoShu);
}
