package jp.co.isid.mos.bird.config.campaignmasterregist.action.impl;

import jp.co.isid.mos.bird.config.campaignmasterregist.action.CampMasterRegistConfirmAction;
import jp.co.isid.mos.bird.config.campaignmasterregist.common.CampaignMasterRegistConst;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.RegistCampaignInfoLogic;

/**
 * キャンペーンマスタ登録 確認画面Action
 *
 */
public class CampMasterRegistConfirmActionImpl implements
        CampMasterRegistConfirmAction {

    /* Action ID */
    public static final String initialize_ACTION_ID = "BCF006A21";
    public static final String doBack_ACTION_ID = "BCF006A22";
    public static final String doRegist_ACTION_ID = "BCF006A23";
    
    /** LOGIC */
    private RegistCampaignInfoLogic campmasterregistRegistCampaignInfoLogic;
    
    /** DTO */
    private CampaignMasterRegistDto campmasterregistDto;
    
    /**
     * 戻る
     * @return
     */
    public String doBack() {
        if (getCampmasterregistDto().getRegistMode() == CampaignMasterRegistConst.REGIST_MODE_READONLY) {
            return CampaignMasterRegistConst.VIEW_ID_CONDITION;
        }
        
        return CampaignMasterRegistConst.VIEW_ID_EDIT;
    }

    /**
     * 登録・終了
     * @return 
     */
    public String doRegist() {
        getCampmasterregistRegistCampaignInfoLogic().execute(getCampmasterregistDto());
        
        getCampmasterregistDto().setFlgNeedCampList(true);

        return CampaignMasterRegistConst.VIEW_ID_CONDITION;
    }

    public String initialize() {
        return null;
    }

    public RegistCampaignInfoLogic getCampmasterregistRegistCampaignInfoLogic() {
        return campmasterregistRegistCampaignInfoLogic;
    }

    public void setCampmasterregistRegistCampaignInfoLogic(
            RegistCampaignInfoLogic campmasterregistRegistCampaignInfoLogic) {
        this.campmasterregistRegistCampaignInfoLogic = campmasterregistRegistCampaignInfoLogic;
    }

    public CampaignMasterRegistDto getCampmasterregistDto() {
        return campmasterregistDto;
    }

    public void setCampmasterregistDto(CampaignMasterRegistDto campmasterregistDto) {
        this.campmasterregistDto = campmasterregistDto;
    }

}
