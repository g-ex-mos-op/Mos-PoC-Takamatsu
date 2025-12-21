package jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampaignDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.GetCampaignListLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * キャンペーン情報取得
 * @author xnkusama
 *
 */
public class GetCampaignListLogicImpl implements GetCampaignListLogic {
    
    /** ロジックID */    
    public static final String LOGIC_ID = "BCF006L01";
    
    /** 検索モード */
    //１：編集可能リスト
    public static final int SEARCH_MODE_EDITABLE = 1;
    //２：過去リスト
    public static final int SEARCH_MODE_KAKO = 2;
    //３：指定キャンペーン
    public static final int SEARCH_MODE_CAMP = 3;

    /** DAO */
    private MstCampaignDao campmasterregistMstCampaignDao;
    
    /**
     * キャンペーン情報を取得する
     * @param kizyunDt
     * @param mode
     * @param campId
     * @param companyCd
     * @return
     */
    public List execute(String kizyunDt, int mode, String campId, String companyCd) {
        /* 戻り値 */
        List listReturn = null;
        
        /* 入力チェックを行う */
        validate(kizyunDt, mode, campId, companyCd);
        
        /* 検索モード＝1 の場合 */
        if (mode == SEARCH_MODE_EDITABLE) {
            listReturn = getCampmasterregistMstCampaignDao().getEditableCampaignList(kizyunDt);
        }
        /* 検索モード＝2 の場合 */
        else if (mode == SEARCH_MODE_KAKO) {
            listReturn = getCampmasterregistMstCampaignDao().getCampaignHistoryList(kizyunDt);
        }
        /* 検索モード＝3 の場合 */
        else if (mode == SEARCH_MODE_CAMP) {
            listReturn = getCampmasterregistMstCampaignDao().getEditCampaign(campId, companyCd);
        }
        
        /* 戻り値がnullの場合は、処理が正常に終了していない */
        if (listReturn == null) {
            throw new FtlSystemException("キャンペーン情報取得");
        }
        
        return listReturn;
    }

    /**
     * パラメータチェック
     * @param kizyunDt
     * @param mode
     * @param campId
     * @param companyCd
     */
    private void validate(String kizyunDt, int mode, String campId, String companyCd) {
        if (mode != SEARCH_MODE_CAMP) {
            if (isNull(kizyunDt)) {
                throw new NotNullException("基準日");
            }
        }
        if (!(mode == SEARCH_MODE_EDITABLE || mode == SEARCH_MODE_KAKO || mode == SEARCH_MODE_CAMP)) {
            throw new InvalidInputException("検索モード");
        }
        if (mode == SEARCH_MODE_CAMP) {
            if (isNull(campId)) {
                throw new NotNullException("キャンペーン識別番号");
            }
            if (isNull(companyCd)) {
                throw new NotNullException("会社コード");
            }
        }
    }
    
    private boolean isNull(String value) {
        return value == null || value.trim().equals("") ? true : false;
    }

    public MstCampaignDao getCampmasterregistMstCampaignDao() {
        return campmasterregistMstCampaignDao;
    }

    public void setCampmasterregistMstCampaignDao(
            MstCampaignDao campmasterregistMstCampaignDao) {
        this.campmasterregistMstCampaignDao = campmasterregistMstCampaignDao;
    }
}
