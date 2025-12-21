/*
 * 作成日: 2006/4/12
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.logic;

import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIUserOnerCdInfo;

/**
 * ユーザーのオーナーコード取得ロジックインターフェース
 * @author Nakajima
 */
public interface GetUserOnerCdLogic {

    public UIUserOnerCdInfo execute(String userId, String companyCd);
}
