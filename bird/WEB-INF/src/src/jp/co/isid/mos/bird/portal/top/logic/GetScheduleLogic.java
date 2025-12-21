/*
 * 作成日: 2006/2/1
 */
package jp.co.isid.mos.bird.portal.top.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;

/**
 * 売上情報取得ロジックインターフェース
 * @author itamoto
 */
public interface GetScheduleLogic {

    /**
     * 売上情報取得処理
     * @param userInfo
     */
    public List execute(BirdUserInfo userInfo, BirdDateInfo birdDateInfo, PortalTopDto portalTopDto);
}
