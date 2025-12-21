/*
 * 作成日: 2006/02/14
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.UIUserMise;


/**
 * ユーザー対応店舗
 * @author xnkusama,lee
 */
public interface UIUserMiseDao {

    public Class BEAN = UIUserMise.class;

    public static final String getUserMiseList_ARGS = "userId, companyCd, miseCd";
    public static final String getUserMiseInfo_ARGS = "userId";
    public static final String getUserMiseFromOwner_ARGS = "userId, companyCd, ownerCd";
    
    
    /**
     * 同一オーナーコードが設定されているBIRDユーザの取得
     * @param user_id ユーザＩＤ
     * @param companyCd 管理会社企業コード
     * @param miseCd 店コード
     * @return 
     */   
    public List getUserMiseList(String userId, String companyCd, String miseCd);
    
    
    /**
     * 指定ユーザーの対応店舗一覧取得
     * @param user_id ユーザＩＤ
     * @return 
     */   
    public List getUserMiseInfo(String userId);
    
    /**
     * 
     * @param userId ユーザＩＤ
     * @param companyCd 会社コード
     * @param ownerCd オーナーコード
     * @return
     */
    public List getUserMiseFromOwner(String userId, String companyCd, String ownerCd);
    

}
