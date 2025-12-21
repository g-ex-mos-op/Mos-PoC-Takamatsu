package jp.co.isid.mos.bird.commonform.publictargetsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodShozoku;

/**
 * @author xytamura
 */
public interface CodShozokuDao {

    public static final Class BEAN = CodShozoku.class;

    public static final String getShozoku_SQL
    =  "    select  "
    +  "        BC07.SHOZOKU_KBN "
    +  "    ,   BC07.SHOZOKU_NAME "
    +  "    from " 
    +  "        BC07SHZK BC07 "
    +  "    order by  SORT_SEQ";
    
    public List getShozoku();
}
