/*
 * 作成日: 2006/01/11
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.UIUserOner;


/**
 * メニューを取得します
 * @author xnkusama,lee
 */
public interface UIUserOnerDao {

    public Class BEAN = UIUserOner.class;

    public static final String getUserOner_ARGS = "userId, companyCd, onerCd";
    public static final String getUserOnerList_ARGS = "userId";
    
    /**
     * 同一オーナーコードが設定されているBIRDユーザの取得
     * @param user_id ユーザＩＤ
     * @param companyCd 管理会社企業コード
     * @param onerCd オーナーコード
     * @return 
     */   
    public List getUserOner(String userId, String companyCd, String onerCd);
    
    
    /**
     * 指定ユーザーのオーナー一覧取得
     * @param user_id ユーザＩＤ
     * @return 
     */   
    public List getUserOnerList(String userId);

}
