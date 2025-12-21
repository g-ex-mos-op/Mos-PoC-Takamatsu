/*
 * 作成日: 2006/01/11
 *
 */
package jp.co.isid.mos.bird.portal.login.dao;

import java.util.List;

import jp.co.isid.mos.bird.portal.login.entity.UIUserMatrixInfo;


/**
 * メニューを取得します
 * @author xnkusama
 */
public interface UIUserMatrixInfoDao {

    public Class BEAN = UIUserMatrixInfo.class;

    public static final String getUserMatrixInfo_ARGS = "userId";
    
    /**
     * 指定ユーザーのマトリクス認証情報取得
     * @param user_id ユーザＩＤ
     * @return 
     */   
    public List getUserMatrixInfo(String userId);

    /**
     * マトリクス情報登録
     * @param entity
     */
    public void insertUserMatrixInfo(UIUserMatrixInfo entity);

    /**
     * マトリクス情報更新
     * @param entity
     */
    public int updateUserMatrixInfo(UIUserMatrixInfo entity);
}
