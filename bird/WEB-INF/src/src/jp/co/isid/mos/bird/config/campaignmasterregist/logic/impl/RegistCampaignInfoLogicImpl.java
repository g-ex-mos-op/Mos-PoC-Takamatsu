package jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.common.CampaignMasterRegistConst;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampMenuDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampMiseDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampSibuDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampSumMenuDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstCampaignDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampMenu;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampMise;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampSibu;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampaign;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.UICampMenu;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.UISibuList;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.RegistCampaignInfoLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * キャンペーン情報取得
 * @author xnkusama
 *
 */
public class RegistCampaignInfoLogicImpl implements RegistCampaignInfoLogic {
    
    /** ロジックID */    
    public static final String LOGIC_ID = "BCF006L04";
    
    /** DAO */
    private MstCampaignDao campmasterregistMstCampaignDao;
    private MstCampMenuDao campmasterregistMstCampMenuDao;
    private MstCampSumMenuDao campmasterregistMstCampSumMenuDao;
    private MstCampSibuDao campmasterregistMstCampSibuDao;
    private MstCampMiseDao campmasterregistMstCampMiseDao;
    
    /**
     * キャンペーン情報の登録
     * @param dto
     */
    public void execute(CampaignMasterRegistDto dto) {
        //キャンペーン対象期間マスタ
        MstCampaign mstCamp = dto.getEntityMstCampaign();
        //新規登録の場合、キャンペーン識別番号採番
        if (CampaignMasterRegistConst.REGIST_MODE_INSERT == dto.getRegistMode()) {
            // 最大キャンペーン識別番号を取得
            String campIdMax = getCampmasterregistMstCampaignDao()
                .getMaxCampaignId(mstCamp.getCampFrom().substring(0, 4) + "%", dto.getCompanyCd());
            int campIdRenban = 0;
            if (campIdMax != null) {
                // 下二桁が99の場合はエラー
                //if ("99".equals(campIdMax.substring(4, 6))) { modify xou.zoubun 2019/12/09 キャンペーンIDを16進数で採番する
                if ("ff".equals(campIdMax.substring(4, 6))) {
                    throw new CannotExecuteException("登録可能数を超えています。登録");
                }
                // 連番部分をインクリメント
                //campIdRenban = (new Integer(campIdMax.substring(4, 6))).intValue() + 1; modify xou.zoubun 2019/12/09 キャンペーンIDを16進数で採番する
                campIdRenban = Integer.parseInt(campIdMax.substring(4, 6), 16) + 1;
            }
            else {
                campIdRenban = 1;
            }
            CodeFormatter codeFormatter = new CodeFormatter(2);
            codeFormatter.setFormatPattern("00");
            //dto.setCampId(mstCamp.getCampFrom().substring(0, 4) + codeFormatter.format(String.valueOf(campIdRenban), true)); modify xou.zoubun 2019/12/09 キャンペーンIDを16進数で採番する
            dto.setCampId(mstCamp.getCampFrom().substring(0, 4) + codeFormatter.format(Integer.toHexString(campIdRenban), true));
        }
        
        // キャンペーン対象期間マスタの登録
        doRegistMstCamp(dto);
        
        // メニュー情報の登録
        doRegistMenuIfno(dto);
        
        // 支部情報の登録
        doRegistSibuInfo(dto);
        
        // 店舗情報の登録
        doRegistMiseInfo(dto);
    }

    /**
     * 店舗情報の登録
     * @param dto
     */
    private void doRegistMiseInfo(CampaignMasterRegistDto dto) {
        // 対象店舗が店舗以外の場合は、処理なし
        if (!CampaignMasterRegistConst.TARGET_KBN_MISE.equals(dto.getCondTaishoTenpo())) {
            return;
        }
        
        // 登録済み店舗情報を全件削除
        getCampmasterregistMstCampMiseDao().deleteCampaignMise(dto.getCampId());
        // 登録済み支部情報を全件削除
        getCampmasterregistMstCampSibuDao().deleteCampaignSibu(dto.getCampId());

        for (Iterator ite = dto.getUploadFileData().iterator(); ite.hasNext();) {
            MstCampMise entity = (MstCampMise) ite.next();
            entity.setCampId(dto.getCampId());
            entity.setCompanyCd(dto.getCompanyCd());
            Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
            entity.setFirstUser(dto.getBirdUserInfo().getUserID());
            entity.setFirstPgm(CampaignMasterRegistConst.VIEW_ID);
            entity.setFirstTmsp(currentTimestamp);
            entity.setLastUser(dto.getBirdUserInfo().getUserID());
            entity.setLastPgm(CampaignMasterRegistConst.VIEW_ID);
            entity.setLastTmsp(currentTimestamp);
            
            getCampmasterregistMstCampMiseDao().insertCampaignMise(entity);
        }
    }
    
    /**
     * 支部情報の登録
     * @param dto
     */
    private void doRegistSibuInfo(CampaignMasterRegistDto dto) {
        // 対象店舗が支部以外の場合は、処理なし
        if (!CampaignMasterRegistConst.TARGET_KBN_SIBU.equals(dto.getCondTaishoTenpo())) {
            return;
        }
        
        // 登録済み店舗情報を全件削除
        getCampmasterregistMstCampMiseDao().deleteCampaignMise(dto.getCampId());
        // 登録済み支部情報を全件削除
        getCampmasterregistMstCampSibuDao().deleteCampaignSibu(dto.getCampId());
        
        // 支部一覧、直営・販社含む一覧 Listの選択
        Iterator ite;
        if (dto.isFlgAreaDai()) {
            ite = dto.getListAreaDai().iterator();
        }
        else {
            ite = dto.getListSibu().iterator();
        }
        // チェックされている支部を登録
        for (;ite.hasNext();) {
            UISibuList entity = (UISibuList) ite.next();
            if (entity.getChkSentaku()) {
                MstCampSibu entitySibu = new MstCampSibu();
                entitySibu.setCampId(dto.getCampId());
                entitySibu.setCompanyCd(dto.getCompanyCd());
                entitySibu.setSibuCd(entity.getSibuCd());
                if (dto.isFlgAreaDai()) {
                    entitySibu.setAreaDaiFlg(CampaignMasterRegistConst.AREA_DAI_FLG_ON);
                }
                else {
                    entitySibu.setAreaDaiFlg(CampaignMasterRegistConst.AREA_DAI_FLG_OFF);
                }
                Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
                entitySibu.setFirstUser(dto.getBirdUserInfo().getUserID());
                entitySibu.setFirstPgm(CampaignMasterRegistConst.VIEW_ID);
                entitySibu.setFirstTmsp(currentTimestamp);
                entitySibu.setLastUser(dto.getBirdUserInfo().getUserID());
                entitySibu.setLastPgm(CampaignMasterRegistConst.VIEW_ID);
                entitySibu.setLastTmsp(currentTimestamp);
                
                getCampmasterregistMstCampSibuDao().insertCampaignSibu(entitySibu);
            }
        }

    }
    
    /**
     * キャンペーン対象期間マスタの登録
     */
    private void doRegistMstCamp(CampaignMasterRegistDto dto) {
        MstCampaign mstCamp = dto.getEntityMstCampaign();
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        if (CampaignMasterRegistConst.REGIST_MODE_INSERT == dto.getRegistMode()) {
            mstCamp.setCampId(dto.getCampId());
            mstCamp.setCompanyCd(dto.getCompanyCd());
            mstCamp.setTargetKbn(dto.getCondTaishoTenpo());
            mstCamp.setYobiFrom("");
            mstCamp.setYobiTo("");
            mstCamp.setFirstUser(dto.getBirdUserInfo().getUserID());
            mstCamp.setFirstPgm(CampaignMasterRegistConst.VIEW_ID);
            mstCamp.setFirstTmsp(currentTimestamp);
            mstCamp.setLastUser(dto.getBirdUserInfo().getUserID());
            mstCamp.setLastPgm(CampaignMasterRegistConst.VIEW_ID);
            
            //新規登録
            getCampmasterregistMstCampaignDao().insertCampaign(mstCamp);
        }
        else if (CampaignMasterRegistConst.REGIST_MODE_UPDATE == dto.getRegistMode()) {
            mstCamp.setTargetKbn(dto.getCondTaishoTenpo());
            mstCamp.setLastUser(dto.getBirdUserInfo().getUserID());
            mstCamp.setLastPgm(CampaignMasterRegistConst.VIEW_ID);
            
            //更新
            getCampmasterregistMstCampaignDao().updateCampaign(mstCamp);
        }
    }

    /**
     * メニュー情報の登録
     */
    private void doRegistMenuIfno(CampaignMasterRegistDto dto) {
        List listMenu = dto.getEntityMstMenuList();
        // 更新モードの場合、登録済みのメニュー情報を削除
        if (CampaignMasterRegistConst.REGIST_MODE_UPDATE == dto.getRegistMode()) {
            getCampmasterregistMstCampMenuDao().deleteCampaignMenu(dto.getCampId());
        }
        
        // メニューリスト全件に対して処理を行う
        for (Iterator ite = listMenu.iterator(); ite.hasNext();) {
            Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
            for (Iterator ite2 = ((List) ite.next()).iterator(); ite2.hasNext();) {
                UICampMenu uiCampMenu = (UICampMenu) ite2.next();
                // メニューコードが空の行は処理なし
                if (isNull(uiCampMenu.getMenuCd())) {
                    continue;
                }
                // キャンペーン対象メニューマスタの登録
                MstCampMenu mstCampMenu = new MstCampMenu();
                mstCampMenu.setCampId(dto.getCampId());
                mstCampMenu.setMenuCd(uiCampMenu.getMenuCd());
                mstCampMenu.setFirstUser(dto.getBirdUserInfo().getUserID());
                mstCampMenu.setFirstPgm(CampaignMasterRegistConst.VIEW_ID);
                mstCampMenu.setFirstTmsp(currentTimestamp);
                mstCampMenu.setLastUser(dto.getBirdUserInfo().getUserID());
                mstCampMenu.setLastPgm(CampaignMasterRegistConst.VIEW_ID);
                mstCampMenu.setLastTmsp(currentTimestamp);
                
                getCampmasterregistMstCampMenuDao().insertCampaignMenu(mstCampMenu);
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

    public MstCampMenuDao getCampmasterregistMstCampMenuDao() {
        return campmasterregistMstCampMenuDao;
    }

    public void setCampmasterregistMstCampMenuDao(
            MstCampMenuDao campmasterregistMstCampMenuDao) {
        this.campmasterregistMstCampMenuDao = campmasterregistMstCampMenuDao;
    }

    public MstCampSumMenuDao getCampmasterregistMstCampSumMenuDao() {
        return campmasterregistMstCampSumMenuDao;
    }

    public void setCampmasterregistMstCampSumMenuDao(
            MstCampSumMenuDao campmasterregistMstCampSumMenuDao) {
        this.campmasterregistMstCampSumMenuDao = campmasterregistMstCampSumMenuDao;
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
}
