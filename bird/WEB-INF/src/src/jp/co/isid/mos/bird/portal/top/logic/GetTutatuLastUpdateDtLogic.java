/*
 * 作成日: 2006/1/27
 */
package jp.co.isid.mos.bird.portal.top.logic;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;

/**
 * お知らせ情報取得ロジックインターフェース
 * @author itamoto
 */
public interface GetTutatuLastUpdateDtLogic {

    /**
     * お知らせ情報取得処理
     * @param userInfo
     */
    public String execute(BirdUserInfo userInfo, BirdDateInfo birdDateInfo,
			PortalTopDto portalTopDto);
}
