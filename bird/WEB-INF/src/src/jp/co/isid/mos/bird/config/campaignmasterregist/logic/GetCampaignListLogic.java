package jp.co.isid.mos.bird.config.campaignmasterregist.logic;

import java.util.List;

/**
 * キャンペーン情報の取得
 * @author xnkusama
 *
 */
public interface GetCampaignListLogic {

    /**
     * キャンペーン情報を取得する
     * @param kizyunDt
     * @param mode
     * @param campId
     * @param companyCd
     * @return
     */
    public List execute(String kizyunDt, int mode, String campId, String companyCd);
}
