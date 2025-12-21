package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstUserShozoku;


public interface MstUserShozokuDao {

    public Class BEAN = MstUserShozoku.class;
    
    public static final String getCtlPassword_ARGS = "userId";
    
    /**
     * ユーザーパスワード情報の検索
     * @param userId ユーザーID
     * @return 統合ユーザーパスワード
     */
    public List getMstUserShozoku(String userId);

}
