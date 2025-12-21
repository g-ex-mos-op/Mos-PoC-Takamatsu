/*
 * ì¬“ú: 2006/08/03
 * 
 */
package jp.co.isid.mos.bird.storemanage.mssonerpointref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.MiseInfo;


/**
 * ‘ÎÛ“X•Üî•ñ
 * 
 * @author xkinu
 */
public interface MiseInfoDao {

    public static final Class BEAN = MiseInfo.class;

    public static final String select_ARGS = "userId, userTypeCd, limitFlg, searchType, companyCd, sibuCd, svCd";
    
    /**
     * ‘ÎÛ“X•Üî•ñ‚Ìæ“¾
     * 
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param searchType
     * @param companyCd
     * @param sibuCd
     * @param svCd
     * @return
     */
    public List select(String userId, String userTypeCd, boolean limitFlg, String searchType, String companyCd, String[] sibuCd, String svCd);
    
}