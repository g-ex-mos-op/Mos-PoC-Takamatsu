package jp.co.isid.mos.bird.config.campaignmasterregist.logic;

import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterListDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;

/**
 * 個店情報チェック
 * @author xnkusama
 *
 */
public interface CheckMiseUploadLogic {

    /**
     * 個店情報チェック
     * @param dto
     * @return
     */
    public void execute(CampaignMasterRegistDto dto, CampaignMasterListDto dtoReq);
}
