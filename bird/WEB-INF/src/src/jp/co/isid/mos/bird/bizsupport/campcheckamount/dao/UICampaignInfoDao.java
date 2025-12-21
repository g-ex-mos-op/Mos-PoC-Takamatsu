package jp.co.isid.mos.bird.bizsupport.campcheckamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UICampaignInfo;

/**
 * 対象キャンペーン情報
 *　@author xlee
 */
public interface UICampaignInfoDao {

    public static final Class BEAN = UICampaignInfo.class;

    public static final String getCampaignInfo_ARGS = "posStrDt, cmpStrDt, posEndDt";

    public static final String getKotenCampaignNo_ARGS = "posStrDt, cmpStrDt, posEndDt";

    /**
     * 対象キャンペーン情報の取得
     * @param posStrDt POS受注開始日
     * @param cmpStrDt キャンペーン開始日
     * @param posEndDt POS受注終了日
     * @return　対象キャンペーン情報
     */
    public List getCampaignInfo(String posStrDt, String cmpStrDt, String posEndDt);

    /**
     * 対象キャンペーン情報の取得
     * @param posStrDt POS受注開始日
     * @param cmpStrDt キャンペーン開始日
     * @param posEndDt POS受注終了日
     * @return　対象キャンペーン情報
     */
    public UICampaignInfo getKotenCampaignNo(String posStrDt, String cmpStrDt, String posEndDt);

}
