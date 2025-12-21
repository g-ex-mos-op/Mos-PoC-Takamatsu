/*
 * 作成日: 2008/11/28
 *
 */
package jp.co.isid.mos.bird.portal.login.dao;

import java.util.List;

import jp.co.isid.mos.bird.portal.login.entity.CtlMatrixRireki;


/**
 * マトリクス認証キー履歴Dao
 * @author xnkusama
 */
public interface CtlMatrixRirekiDao {

    public Class BEAN = CtlMatrixRireki.class;

    public static final String getUserMatrixInfoUserAll_ARGS = "userId";
    public static final String getUserMatrixInfo_ARGS = "userId, updateDt";
    
    /**
     * 指定ユーザーのマトリクス認証情報取得
     * @param user_id ユーザＩＤ
     * @return 
     */   
    public List getUserMatrixInfoUserAll(String userId);

    /**
     * 指定ユーザーのマトリクス認証情報取得
     * @param user_id ユーザＩＤ
     * @parma updateDt
     * @return 
     */   
    public List getUserMatrixInfo(String userId, String updateDt);

    /**
     * マトリクス認証キー履歴登録
     * @param entity
     */
    public void insertUserMatrixInfo(CtlMatrixRireki entity);

}
