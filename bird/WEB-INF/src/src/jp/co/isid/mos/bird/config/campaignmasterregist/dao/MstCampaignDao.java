/*
 * 作成日: 2008/03/18
 *
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampaign;

/**
 * @author xnkusama
 *
 */
public interface MstCampaignDao {

    public static final Class BEAN = MstCampaign.class;

    public static final String getEditCampaign_ARGS = "campId, companyCd";
    public static final String getEditableCampaignList_ARGS = "kizyunDt";
    public static final String getCampaignHistoryList_ARGS = "kizyunDt";
    public static final String getMaxCampaignId_ARGS = "year, companyCd";
    public static final String getCountViewCamp_ARGS = "targetDt, campId";
    public static final String updateCampaign_PERSISTENT_PROPS = "companyCd, campTitle, campFrom, campTo, dispFrom, dispTo, yobiFrom, yobiTo, targetKbn, lastUser, lastPgm";

    /**
     * 編集キャンペーン情報の取得
     * 
     * @param campId キャンペーン識別番号
     * @param companyCd 会社コード
     * @return 
     */
    public List getEditCampaign(String campId, String companyCd);
    
    /**
     * キャンペーンの新規登録
     * @param entity
     * @return
     */
    public int insertCampaign(MstCampaign entity);
    
    /**
     * キャンペーンの更新
     * @param entity
     * @return
     */
    public int updateCampaign(MstCampaign entity);
    
    /**
     * 編集可能キャンペーン一覧の取得
     * @param kizyunDt
     * @return
     */
    public List getEditableCampaignList(String kizyunDt);
    
    /**
     * 過去キャンペーン一覧の取得
     * @param kizyunDt
     * @return
     */
    public List getCampaignHistoryList(String kizyunDt);
    
    /**
     * 最大識別番号の取得
     * @param year Like文で使用するため、「%」付の文字列を指定する
     * @param companyCd
     * @return
     */
    public String getMaxCampaignId(String year, String companyCd);
    
    /**
     * 表示期間中キャンペーン数取得
     * @param targetDt
     * @param campId
     * @return
     */
    public int getCountViewCamp(String targetDt, String campId);
}