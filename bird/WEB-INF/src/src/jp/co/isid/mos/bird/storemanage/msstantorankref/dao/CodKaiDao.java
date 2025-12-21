/*
 * 作成日: 2006/08/03
 * 
 */
package jp.co.isid.mos.bird.storemanage.msstantorankref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.CodKai;


/**
 * 回数リスト検索処理Dao
 * 
 * @author xkinu
 */
public interface CodKaiDao {

    public static final Class BEAN = CodKai.class;

    public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd";
    
    /**
     * 回数リスト情報の取得
     * @param userId　ユーザーID
     * @param userTypeCd　ユーザータイプコード
     * @param limitFlg limitFlg リミットフラグ
     * @param companyCd 企業コード
     * @return List
     */
    public List select(String userId, String userTypeCd, boolean limitFlg, String companyCd);
    
}