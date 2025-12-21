/*
 * çÏê¨ì˙: 2005/12/28
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlOnlineUsedTbl;

/**
 * @author xytamura
 */
public interface CtlOnlineUsedTblDao {

    public Class BEAN = CtlOnlineUsedTbl.class;

    public static final String getState_ARGS = "id";
    public static final String getState_SQL =      
        " SELECT "
     +  "         BR13.ONL_ID "
     +  ",        BR13.TBL_ID " 
     +  ",        BR14.STATE "
     +  " FROM "
     +  "         BR13OLTL BR13 " 
     +  ",        BR14TBLS BR14 "
     +  " WHERE "
     +  "         BR13.TBL_ID = BR14.TBL_ID " 
     +  " AND     BR13.ONL_ID=/*id*/ "
     +  " AND   ( BR14.STATE='1' or BR14.STATE='9' ) ";
        
        
    public List getState(final String id);
}
