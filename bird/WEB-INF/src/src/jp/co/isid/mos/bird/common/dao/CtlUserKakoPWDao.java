package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.CtlUserKakoPW;


public interface CtlUserKakoPWDao {

    public Class BEAN = CtlUserKakoPW.class;
    
    public static final String getCtlPassword_ARGS = "userId";
    public static final String getMaxSeq_ARGS = "userId, updateDt";
    
    /**
     * ユーザーパスワード情報の検索
     * @param userId ユーザーID
     * @return 統合ユーザーパスワード
     */
    public List getCtlPassword(String userId);

    /**
     * 最大SEQ取得
     * @param userId
     * @param updateDt
     * @return
     */
    public int getMaxSeq(String userId, String updateDt);
    
    /**
     * ユーザーパスワード履歴の追加
     * @param ctlUserKakoPW
     */
    public void insertCtlPassword(CtlUserKakoPW ctlUserKakoPW);
}
