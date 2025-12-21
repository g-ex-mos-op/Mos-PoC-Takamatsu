/*
 * 作成日: 2006/4/12
 */
package jp.co.isid.mos.bird.bizsupport.similarshop.logic;

import jp.co.isid.mos.bird.bizsupport.similarshop.entity.UIOnerTenpoInfo;

/**
 * 対象店舗情報の取得ロジックインターフェース
 * @author Nakajima
 */
public interface GetTenpoInfoLogic {

    public UIOnerTenpoInfo execute(String sysdate, String miseCd, String nenGetu, String userId, String userTypeCd, boolean limitFlg);
}
