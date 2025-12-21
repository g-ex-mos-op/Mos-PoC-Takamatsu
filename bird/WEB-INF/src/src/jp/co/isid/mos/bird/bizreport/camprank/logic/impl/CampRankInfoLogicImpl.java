package jp.co.isid.mos.bird.bizreport.camprank.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.camprank.common.CampRankConst;
import jp.co.isid.mos.bird.bizreport.camprank.dao.CampRankTenpoCountDao;
import jp.co.isid.mos.bird.bizreport.camprank.dao.UICampRankDataDao;
import jp.co.isid.mos.bird.bizreport.camprank.entity.CampRankTenpoCount;
import jp.co.isid.mos.bird.bizreport.camprank.logic.CampRankInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 順位情報検索
 * @author xnkusama
 *
 */
public class CampRankInfoLogicImpl implements CampRankInfoLogic {
    /** ロジックID */    
    public static final String LOGIC_ID = "BBR013L02";

    /** DAO */
    private UICampRankDataDao camprankUICampRankDataDao;
    private CampRankTenpoCountDao camprankCampRankTenpoCountDao;
    
    /**
     * 指定されたキャンペーンのベスト１００情報を取得
     * @param campId
     * @param fromDt
     * @param toDt
     * @param rankMode
     */
    public Map execute(String companyCd, String campId, String fromDt, String toDt, String rankMode, String condMode, int kikanIndex) {
        //パラメータチェック
        validate(companyCd, campId, fromDt, toDt, rankMode, condMode, kikanIndex);
        
        // 対象店舗数取得
        CampRankTenpoCount tenpoCount = getCamprankCampRankTenpoCountDao().getTaishoTenpoCount(campId, fromDt, toDt);
        // ベスト１００情報取得
        List listCampInfo = new ArrayList();
        if (CampRankConst.SEARCH_MODE_TAISHOBI.equals(condMode)) {
            listCampInfo = getCamprankUICampRankDataDao().getCampaignData(companyCd, campId, fromDt, toDt, rankMode);
        }
        else {
            if (kikanIndex == 0) {
                listCampInfo = getCamprankUICampRankDataDao().getCampaignDataFromTotal(companyCd, campId, fromDt, toDt, rankMode);
            }
            else {
                String kikanFromZen = "";
                try {
                    kikanFromZen = DateManager.getPrevDate(fromDt, 1);
                }
                catch (Exception ex) {
                    throw new FtlSystemException("日付", null, ex);
                }
                listCampInfo = getCamprankUICampRankDataDao().getCampaignDataKikan(companyCd, campId, fromDt, toDt, kikanFromZen, rankMode);
            }
        }
        
        Map retMap = new HashMap();
        retMap.put(CampRankConst.SEARCH_LOGIC_RETURN_TENPO_COUNT, tenpoCount.getTenpoCount());
        retMap.put(CampRankConst.SEARCH_LOGIC_RETURN_RANK_DATA, listCampInfo);
        
        return retMap;

    }
    
    private void validate(String companyCd, String campId, String fromDt, String toDt, String rankMode, String condMode, int kikanIndex) {
        if (CommonUtil.isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
        if (CommonUtil.isNull(campId)) {
            throw new NotNullException("キャンペーン識別番号");
        }
        if (CommonUtil.isNull(fromDt)) {
            throw new NotNullException("対象日");
        }
        if (CommonUtil.isNull(toDt)) {
            throw new NotNullException("対象日");
        }
        if (CommonUtil.isNull(rankMode)) {
            throw new NotNullException("順位");
        }
        if (CommonUtil.isNull(condMode)) {
            throw new NotNullException("タブ情報");
        }
    }
    
    public UICampRankDataDao getCamprankUICampRankDataDao() {
        return camprankUICampRankDataDao;
    }
    public void setCamprankUICampRankDataDao(
            UICampRankDataDao camprankUICampRankDataDao) {
        this.camprankUICampRankDataDao = camprankUICampRankDataDao;
    }
    public CampRankTenpoCountDao getCamprankCampRankTenpoCountDao() {
        return camprankCampRankTenpoCountDao;
    }
    public void setCamprankCampRankTenpoCountDao(
            CampRankTenpoCountDao camprankCampRankTenpoCountDao) {
        this.camprankCampRankTenpoCountDao = camprankCampRankTenpoCountDao;
    }

}
