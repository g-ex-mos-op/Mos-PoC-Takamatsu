/*
 * 作成日: 2009/03/12
 *
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountedit.entity.UIUserMatrixInfo;



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
     * マトリクス認証情報削除
     */
    public void deleteUserMatrixInfo(UIUserMatrixInfo entity);
}
