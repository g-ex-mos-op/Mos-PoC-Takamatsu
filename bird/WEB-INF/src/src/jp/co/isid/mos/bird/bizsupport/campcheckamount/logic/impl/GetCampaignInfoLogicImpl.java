package jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.common.CampCheckAmountCommon;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UICampaignInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UICampaignInfo;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetCampaignInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * キャンペーン情報取得ロジック
 *
 * @author xlee
 */
public class GetCampaignInfoLogicImpl implements GetCampaignInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS027L01";

    /**
     * キャンペーン情報DAOを取得します。
     */
    private UICampaignInfoDao uiCampaignInfoDao;

    /**
     * キャンペーン情報DAOを取得します。
     * @return キャンペーン情報DAO
     */
    public UICampaignInfoDao getUICampaignInfoDao() {
        return uiCampaignInfoDao;
    }

    /**
     * キャンペーン情報DAOを設定します。
     * @param uiCampaignInfoDao キャンペーン情報DAO
     */
    public void setUICampaignInfoDao(UICampaignInfoDao uiCampaignInfoDao) {
        this.uiCampaignInfoDao = uiCampaignInfoDao;
    }

    /**
     * キャンペーン情報を設定します。
     * @param sysDate システム日付
     * @return タイトルリスト
     */
	public List execute(String posFromDt, String cmpFrom, String posEndDt) {
    	//エラー処理：
    	if(CampCheckAmountCommon.isNull(posFromDt)){
            throw new NotNullException("POS受注開始日"); //E0506 POS受注開始日
        }
    	//エラー処理：
    	if(CampCheckAmountCommon.isNull(cmpFrom)){
            throw new NotNullException("キャンペーン開始日"); //E0506 キャンペーン開始日
        }
    	//エラー処理：
    	if(CampCheckAmountCommon.isNull(posEndDt)){
            throw new NotNullException("POS受注終了日"); //E0506 POS受注終了日
        }

    	List tmpResult = getUICampaignInfoDao().getCampaignInfo(posFromDt, cmpFrom, posEndDt);
        return tmpResult;
	}

    /**
     * キャンペーン情報を設定します。
     * @param sysDate システム日付
     * @return タイトルリスト
     */
	public String executeKoten(String posFromDt, String cmpFrom, String posEndDt) {
    	//エラー処理：
    	if(CampCheckAmountCommon.isNull(posFromDt)){
            throw new NotNullException("POS受注開始日"); //E0506 POS受注開始日
        }
    	//エラー処理：
    	if(CampCheckAmountCommon.isNull(cmpFrom)){
            throw new NotNullException("キャンペーン開始日"); //E0506 キャンペーン開始日
        }
    	//エラー処理：
    	if(CampCheckAmountCommon.isNull(posEndDt)){
            throw new NotNullException("POS受注終了日"); //E0506 POS受注終了日
        }
    	String campNo = ((UICampaignInfo)getUICampaignInfoDao().getKotenCampaignNo(posFromDt, cmpFrom, posEndDt)).getCmpNo();
        return campNo;
	}
}
