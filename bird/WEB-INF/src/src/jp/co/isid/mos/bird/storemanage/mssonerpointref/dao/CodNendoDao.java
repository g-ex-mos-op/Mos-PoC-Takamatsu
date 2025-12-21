/*
 * 作成日: 2006/08/03
 * 
 */
package jp.co.isid.mos.bird.storemanage.mssonerpointref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.CodNendo;


/**
 * 年度リスト検索Dao
 * 
 * @author xkinu
 */
public interface CodNendoDao {

    public static final Class BEAN = CodNendo.class;

    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd";
    
    /**
     * 年度情報の取得
     * @param userId　ユーザーID
     * @param userTypeCd　ユーザータイプコード
     * @param limitFlg limitFlg リミットフラグ
     * @param companyCd 企業コード
     * @return List
     */
    public List select(String userId, String userTypeCd, boolean limitFlg, String companyCd);
    
}