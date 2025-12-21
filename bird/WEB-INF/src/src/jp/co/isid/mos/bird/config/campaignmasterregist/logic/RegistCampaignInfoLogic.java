package jp.co.isid.mos.bird.config.campaignmasterregist.logic;

import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;

/**
 * キャンペーン情報の登録ロジック Interface
 * @author xnkusama
 *
 */
public interface RegistCampaignInfoLogic {

    /**
     * キャンペーン情報の登録
     * @param dto
     */
    public void execute(CampaignMasterRegistDto dto);
}
