/*
 * 作成日: 2009/03/12
 *
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountedit.entity.CtlMatrixRireki;


/**
 * マトリクス認証キー履歴Dao
 * @author xnkusama
 */
public interface CtlMatrixRirekiDao {

    public Class BEAN = CtlMatrixRireki.class;

    public static final String getUserMatrixInfo_ARGS = "userId";
    
    /**
     * 指定ユーザーのマトリクス認証情報取得
     * @param user_id ユーザＩＤ
     * @parma updateDt
     * @return 
     */   
    public List getUserMatrixInfo(String userId);

    /**
     * マトリクス認証キー履歴削除
     * @param entity
     */
    public void deleteUserMatrixInfo(CtlMatrixRireki entity);

}
