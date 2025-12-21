package jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.config.campaignmasterregist.common.CampaignMasterRegistConst;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampMiseDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampSibuDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.UISibuListDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampSibu;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.UISibuList;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.GetMiseSibuInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * キャンペーン情報取得
 * @author xnkusama
 *
 */
public class GetMiseSibuInfoLogicImpl implements GetMiseSibuInfoLogic {
    
    /** ロジックID */    
    public static final String LOGIC_ID = "BCF006L06";
    
    /** 対象区分 */
    //１：全店
    public static final String SEARCH_MODE_ZENTEN = "1";
    //１：支部
    public static final String SEARCH_MODE_SIBU = "2";
    //１：個店
    public static final String SEARCH_MODE_KOTEN = "3";
    
    /** エリア大フラグ */
    private static final String AREA_DAI_FLG_SIBUTORI = "1";

    /** DAO */
    private UISibuListDao campmasterregistUISibuListDao;
    private MstCampSibuDao campmasterregistMstCampSibuDao;
    private MstCampMiseDao campmasterregistMstCampMiseDao;
    
    /**
     * 対象店舗、対象支部情報を取得する
     * 
     * @param birdDateInfo e-mossles日付情報(2011/07追加)
     * @param campId
     * @param searchMode
     * @param dto
     * @return
     * 
     * 更新日:2011/07/26 ASPAC オープン中の店舗が存在する支部情報を取得できるよう対応
     */
    public List execute(BirdDateInfo birdDateInfo
    		, String campId, String searchMode, CampaignMasterRegistDto dto) {
        /* 戻り値 */
        List listReturn = null;
        
        /* 入力チェックを行う */
        validate(campId, searchMode, dto);
        
        /* 支部一覧の取得 */
        List listSibu = getCampmasterregistUISibuListDao().getSibuList(
        		birdDateInfo.getSysDate(), false);
        
        /* 直営・販社含む 支部一覧の取得 */
        List listSibuTori = getCampmasterregistUISibuListDao().getSibuList(
        		birdDateInfo.getSysDate(), true);
        
        /* 対象区分により必要情報をセット */
        if (CampaignMasterRegistConst.TARGET_KBN_SIBU.equals(searchMode)) {
            // 検索モード＝２の場合、登録済み支部情報を取得
            doSibu(campId, dto, listSibu, listSibuTori);
        }
        else if (CampaignMasterRegistConst.TARGET_KBN_MISE.equals(searchMode)) {
            // 検索モード＝３の場合、登録済み店舗情報を取得
            doMise(campId, dto);
        }
        else {
            // 検索モード＝１の場合、初期情報をセット
            dto.setFlgAreaDai(false);
            dto.setCondTaishoTenpo(CampaignMasterRegistConst.TARGET_KBN_ZENTEN);
            dto.setUploadStatus(CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_NORMAL);
        }
        dto.setListSibu(listSibu);
        dto.setListAreaDai(listSibuTori);
        
        return listReturn;
    }
    
    /**
     * 登録済みの支部情報を取得し一覧Listの
     * チェックボックス用フラグに反映させる
     * @param campId
     * @param dto
     * @param listSibu
     * @param listSibuTori
     */
    private void doSibu(String campId, CampaignMasterRegistDto dto, List listSibu, List listSibuTori) {
        // 登録済み情報の取得
        List listSibuDb = getCampmasterregistMstCampSibuDao().getSibuList(campId);
        
        // 取得した情報を支部一覧に反映させる
        if (listSibuDb == null || listSibuDb.isEmpty()) {
            // 登録済み情報がない場合
            dto.setFlgAreaDai(false);
        }
        else {
            // 登録済み支部をMapへセット
            Map mapSibu = new HashMap();
            for (Iterator ite = listSibuDb.iterator(); ite.hasNext();) {
                MstCampSibu mstCampSibu = (MstCampSibu) ite.next();
                mapSibu.put(mstCampSibu.getSibuCd(), "");
            }
            // １件目のエリア大フラグにより、どちらの一覧にセットするか判別する
            MstCampSibu mstCampSibu = (MstCampSibu) listSibuDb.get(0);
            if (AREA_DAI_FLG_SIBUTORI.equals(mstCampSibu.getAreaDaiFlg())) {
                /*直営・販社含む 支部一覧*/
                // 登録済み支部は支部一覧の選択チェックボックスをONにする
                for (Iterator ite = listSibuTori.iterator(); ite.hasNext();) {
                    UISibuList uiSibuList = (UISibuList) ite.next();
                    if (mapSibu.containsKey(uiSibuList.getSibuCd())) {
                        uiSibuList.setChkSentaku(true);
                    }
                }
                // DTO.支部一覧エリア大フラグを設定
                dto.setFlgAreaDai(true);
            }
            else {
                /*支部一覧 */
                // 登録済み支部は支部一覧の選択チェックボックスをONにする
                for (Iterator ite = listSibu.iterator(); ite.hasNext();) {
                    UISibuList uiSibuList = (UISibuList) ite.next();
                    if (mapSibu.containsKey(uiSibuList.getSibuCd())) {
                        uiSibuList.setChkSentaku(true);
                    }
                }
                // DTO.支部一覧エリア大フラグを設定
                dto.setFlgAreaDai(false);
            }
        }
    }
    
    /**
     * 登録済み店舗情報を取得しDTOへセット
     * @param campId
     * @param dto
     */
    private void doMise(String campId, CampaignMasterRegistDto dto) {
        List listMise = getCampmasterregistMstCampMiseDao().getMiseList(campId);
        dto.setUploadFileData(listMise);
    }

    /**
     * パラメータチェック
     * @param campId
     * @param searchMode
     * @param dto
     */
    private void validate(String campId, String searchMode, CampaignMasterRegistDto dto) {
        if (isNull(searchMode)) {
            throw new NotNullException("検索モード");
        }
        if (!(searchMode.equals(SEARCH_MODE_ZENTEN) || searchMode.equals(SEARCH_MODE_SIBU) || searchMode.equals(SEARCH_MODE_KOTEN))) {
            throw new InvalidInputException("検索モード");
        }
        // 対象区分≠１の時は必須
        if (searchMode != SEARCH_MODE_ZENTEN && isNull(campId)) {
            throw new NotNullException("キャンペーン識別番号");
        }
        if (dto == null) {
            throw new FtlSystemException("対象店舗情報取得");
        }
    }
    
    private boolean isNull(String value) {
        return value == null || value.trim().equals("") ? true : false;
    }

    public MstCampMiseDao getCampmasterregistMstCampMiseDao() {
        return campmasterregistMstCampMiseDao;
    }

    public void setCampmasterregistMstCampMiseDao(
            MstCampMiseDao campmasterregistMstCampMiseDao) {
        this.campmasterregistMstCampMiseDao = campmasterregistMstCampMiseDao;
    }

    public MstCampSibuDao getCampmasterregistMstCampSibuDao() {
        return campmasterregistMstCampSibuDao;
    }

    public void setCampmasterregistMstCampSibuDao(
            MstCampSibuDao campmasterregistMstCampSibuDao) {
        this.campmasterregistMstCampSibuDao = campmasterregistMstCampSibuDao;
    }

    public UISibuListDao getCampmasterregistUISibuListDao() {
        return campmasterregistUISibuListDao;
    }

    public void setCampmasterregistUISibuListDao(
            UISibuListDao campmasterregistUISibuListDao) {
        this.campmasterregistUISibuListDao = campmasterregistUISibuListDao;
    }

}
