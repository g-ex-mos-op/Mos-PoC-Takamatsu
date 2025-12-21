/*
 * 作成日: 2006/2/22
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserShozoku;

/**
 * ユーザ公開対象所属区分管理(MstUserShozokuDao）
 * @author itamoto
 */
public interface MstUserShozokuDao {

    public static final Class BEAN = MstUserShozoku.class;

    public static final String select_ARGS  = "miseCd, companyCd";
    public static final String deleteByUserId_SQL = "DELETE FROM BM13SHKM WHERE USER_ID =?";

    /**
     * ユーザ所属(insertBirdUser)
     * @param  USER_ID
     * @return 結果
     */
    public String select(String mise_cd,String conpany_cd);
    
    /**
     * ユーザ公開対象所属区分管理の新規登録(insertBirdUser)
     * @param mstUserShozoku  エンティティ
     * @return int なし
     */
    public int insert(MstUserShozoku mstUserShozoku);
    
    /**
     * ユーザ公開対象所属区分管理の新規登録(update)
     * @param UIUserShozoku  エンティティ
     * @return int なし
     */
    public int update(MstUserShozoku mstUserShozoku);
    

    /**
     * ユーザ公開対象所属区分管理の削除(deleteByUserId)
     * @param userId ユーザＩＤ
     */
    
    public int deleteByUserId(String userId);
    

}
