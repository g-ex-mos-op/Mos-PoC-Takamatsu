package jp.co.isid.mos.bird.commonform.publictargetsearch.dao;


import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CtlUserShozoku;

public interface CtlUserShozokuDao {

    public static final Class BEAN = CtlUserShozoku.class;
    public static final String getUserShozoku_ARGS = "userId";
    public static final String getUserShozoku_SQL
    = "    select  "
    +  "        BM13.USER_ID "
    +  "    ,   BM13.SHOZOKU_KBN "
    +  "    from " 
    +  "        BM13SHKM BM13 "
    +  "     where    "
    +  "        BM13.USER_ID=/*userId*/   ";

    public CtlUserShozoku getUserShozoku(final String userId);
    
  
}
