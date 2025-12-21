package jp.co.isid.mos.bird.config.campaignmasterregist.logic;

import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;

/**
 * 入力内容チェック
 * @author xnkusama
 *
 */
public interface CheckCampaignInfoLogic {

    /**
     * 入力内容チェック
     * @param dto
     * @return
     */
    public void execute(CampaignMasterRegistDto dto);
}
