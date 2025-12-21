package jp.co.isid.mos.bird.config.campaignmasterregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;

/**
 * 対象店舗、対象支部情報を取得ロジック Interface
 * @author xnkusama
 * 更新日:2011/07/26 ASPAC オープン中の店舗が存在する支部情報を取得できるよう対応
 *
 */
public interface GetMiseSibuInfoLogic {

    /**
     * 対象店舗、対象支部情報を取得する
     * 
     * @param birdDateInfo e-mossles日付情報(2011/07追加)
     * @param campId
     * @param searchMode
     * @param dto
     * @return List
     * 
     * 更新日:2011/07/26 ASPAC オープン中の店舗が存在する支部情報を取得できるよう対応
     */
    public List execute(
    		BirdDateInfo birdDateInfo
    		, String campId, String searchMode
    		, CampaignMasterRegistDto dto);
}
