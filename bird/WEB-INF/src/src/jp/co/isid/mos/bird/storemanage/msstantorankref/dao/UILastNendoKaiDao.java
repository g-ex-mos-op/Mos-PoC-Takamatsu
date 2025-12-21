/*
 * 作成日: 2006/08/11
 * 
 */
package jp.co.isid.mos.bird.storemanage.msstantorankref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.UILastNendoKai;


/**
 * 前回実施の年度・回数リスト検索Dao
 * 
 * @author xkinu
 */
public interface UILastNendoKaiDao {

    public static final Class BEAN = UILastNendoKai.class;

    public static final String select_ARGS = "userId, userTypeCd, limitFlg, nendo, kai, companyCd";
    
    /**
     * 前回実施の年度・回数の取得
     * 
     * @param userId　ユーザーID
     * @param userTypeCd　ユーザータイプコード
     * @param limitFlg limitFlg リミットフラグ
     * @param companyCd 企業コード
     * @return List
     */
    public List select(String userId, String userTypeCd, boolean limitFlg, String nendo, String kai, String companyCd);
    
}