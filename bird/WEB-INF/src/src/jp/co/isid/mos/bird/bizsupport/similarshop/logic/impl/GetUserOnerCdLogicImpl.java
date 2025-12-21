/*
 * 作成日: 2006/4/12
 *
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.logic.impl;

import jp.co.isid.mos.bird.bizsupport.similarshop.logic.GetUserOnerCdLogic;
import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIUserOnerCdInfo;
import jp.co.isid.mos.bird.bizsupport.similarshop.dao.UIUserOnerCdDao;

/**
 * オーナー情報の取得ロジックアクション
 * @author Nakajima
 */
public class GetUserOnerCdLogicImpl implements GetUserOnerCdLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BBS008L05";

    /**
     * ユーザーのオーナーコード情報（UIUserOnerCdDao）
     */
    private UIUserOnerCdDao uIUserOnerCdDao;
    
	/**
	 * ユーザーのオーナーコードDaoの設定
	 * @return uIUserOnerCdDao を戻します。
	 */
	public UIUserOnerCdDao getUIUserOnerCdDao() {
		return uIUserOnerCdDao;
	}
	/**
	 * ユーザーのオーナーコードDaoの設定
	 * @param UIUserOnerCdDao uIUserOnerCdDao を設定。
	 */
	public void setUIUserOnerCdDao(UIUserOnerCdDao uIUserOnerCdDao) {
		this.uIUserOnerCdDao = uIUserOnerCdDao;
	}
    
    
	/**
	 * ユーザーのオーナーコードの検索を行う
	 * @return 検索結果
	 */
	public UIUserOnerCdInfo execute(String userId, String companyCd) {

		//オーナーコード取得
        return uIUserOnerCdDao.getUserOnerCdInfo(userId, companyCd);

	}
}
