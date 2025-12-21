/*
 * 作成日: 2006/08/03
 * 
 */
package jp.co.isid.mos.bird.storemanage.mssonerpointref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.SibuInfo;


/**
 * 対象支部情報
 * 
 * @author xkinu
 */
public interface SibuInfoDao {

    public static final Class BEAN = SibuInfo.class;

    public static final String select_ARGS = "userId, userTypeCd, limitFlg, searchType, nendo, kai, companyCd, sibuCd, onerCd, miseCd";
    
    /**
     * 支部情報の取得
     * @param userId　ユーザーID
     * @param userTypeCd　ユーザータイプコード
     * @param limitFlg limitFlg リミットフラグ
     * @param companyCd 企業コード
     * @return List
     */
    public List select(String userId, String userTypeCd, boolean limitFlg, String searchType
            , String nendo, String kai, String companyCd, String sibuCd, String onerCd, String miseCd);
    
}