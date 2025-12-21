/*
 * 作成日: 2006/08/03
 * 
 */
package jp.co.isid.mos.bird.storemanage.mssonerpointref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.CodSibu;


/**
 * 支部情報
 * 
 * @author xkinu
 */
public interface CodSibuDao {

    public static final Class BEAN = CodSibu.class;

    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd";
    
    /**
     * 支部情報の取得
     * @param userId　ユーザーID
     * @param userTypeCd　ユーザータイプコード
     * @param limitFlg limitFlg リミットフラグ
     * @param companyCd 企業コード
     * @return List
     */
    public List select(String userId, String userTypeCd, boolean limitFlg, String companyCd);
    
}