/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.dao;

import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIUserOnerCdInfo;


/**
 * ユーザーオーナーコード情報(UIUserOnerCdDao)
 * @author Nakajima
 */
public interface UIUserOnerCdDao {

    public static final Class  BEAN = UIUserOnerCdInfo.class;
    public static final String getUserOnerCdInfo_ARGS     = "userId,companyCd";

    /**
     * ユーザーオーナーコード情報取得(getUserOnerCdInfo)
     * @param userId,companyCd
     * @return UIUserOnerCdInfo 検索結果
     */
    public UIUserOnerCdInfo getUserOnerCdInfo(String userId, String companyCd);
    
}

