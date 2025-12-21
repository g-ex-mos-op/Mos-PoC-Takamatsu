/*
 * 作成日: 2006/06/16
 */
package jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao.UIHonbuTehaiListDao;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIStatusInfo;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.HonbuTetaiListLogic;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.util.ProjectPlanStatusInfoUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 本部手配宿泊　ロジッククラス
 * @author xamaruyama
 */
public class HonbuTetaiListLogicImpl implements HonbuTetaiListLogic {

    /** ロジックID */
    public static final String LOGIC_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"L03";
    /** 
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = "userInfo";
    /** 
     * パラメーターKey：システム日付 
     */
    public static final String PK_SYSDATE = "sysDate";
    /**
     * パラメーターKey：対象コースエンティティー
     */
    public static final String PK_ENTITY_COURSE= "entityCourse";
    /**
     * パラメーターKey：条件項目『対象条件』
     */
    public static final String PK_TAISHOJOKEN = "taishoJoken";
    /**
     * パラメーターKey：会社コード
     */
    public static final String PK_COMPANY_CD= "companyCd";
    /**
     * パラメーターKey：支部コード
     */
    public static final String PK_SIBU_CD= "sibuCd";
    /**
     * パラメーターKey：SVコード
     */
    public static final String PK_SV_CD= "svCd";
    /**
     * リターンKey：宿泊情報
     */
    public static final String RK_LIST_LODGER = "listLodger";
    
    private UIHonbuTehaiListDao getProjectPlanStatusInfoUIHonbuTehaiListDao;
    
    /**
     * 事前条件処理
     * 
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //ユーザ情報
        if (params.get(PK_USERINFO) == null || !(params.get(PK_USERINFO) instanceof BirdUserInfo)) {
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        } 
        //パラメーター対象コースエンティティー
        UIStatusInfo entity = (UIStatusInfo)params.get(PK_ENTITY_COURSE);
        if (entity == null) {
            throw new MissingDataException("対象コース情報");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (ProjectPlanStatusInfoUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
        String companyCd = (String)params.get(PK_COMPANY_CD);
        if(ProjectPlanStatusInfoUtil.isNull(companyCd)){
            throw new MissingDataException("会社コード");
        }
        //パラメーター対象条件必須チェック
        String taishouJokenChoice = (String)params.get(PK_TAISHOJOKEN);
        if(ProjectPlanStatusInfoUtil.isNull(taishouJokenChoice)){
            throw new NotNullException("対象条件", "taishouJokenChoice", 0);
        }
        if(ProjectPlanStatusInfoUtil.TAISHO_JOKEN_SIBU.equals(taishouJokenChoice)){
            String sibuListChoice = (String)params.get(PK_SIBU_CD);
            if(ProjectPlanStatusInfoUtil.isNull(sibuListChoice)){
                throw new NotNullException("支部コード", "sibuListChoice", 0);
            }
        }
        else if(ProjectPlanStatusInfoUtil.TAISHO_JOKEN_SV.equals(taishouJokenChoice)){
            String svCd = (String)params.get(PK_SV_CD);
            if(ProjectPlanStatusInfoUtil.isNull(svCd)){
                throw new NotNullException("SVコード", "svCd", 0);
            }
        }
    }
    /**
     * 実行処理
     */
    public List execute(final Map params) {        
        //１．事前条件処理を実行する。
        validate(params);
        
        List honbuTehaiList = new ArrayList();
        //ユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        boolean limit = userInfo.isLimit();
        String userId = userInfo.getUserID();
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        //パラメーター対象コースエンティティー
        UIStatusInfo entity = (UIStatusInfo)params.get(PK_ENTITY_COURSE);
        //パラメーター会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        //パラメーター対象条件
        String taishouJokenChoice = (String)params.get(PK_TAISHOJOKEN);
        //パラメーター支部コード
        String sibuListChoice = (String)params.get(PK_SIBU_CD);
        //パラメーターSVコード
        String svCd = (String)params.get(PK_SV_CD);
        
        String entryCd = entity.getEntryCd();
        String entryYear = entity.getEntryYear();
        String entryKai = entity.getEntryKai();
        
        honbuTehaiList = getProjectPlanStatusInfoUIHonbuTehaiListDao().getHonbuTehaiList(limit, userId,
            entryCd, entryYear, entryKai, sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd);
        
        return honbuTehaiList;
    }

    /**
     * @return getProjectPlanStatusInfoUIHonbuTehaiListDao を戻します。
     */
    public UIHonbuTehaiListDao getProjectPlanStatusInfoUIHonbuTehaiListDao() {
        return getProjectPlanStatusInfoUIHonbuTehaiListDao;
    }

    /**
     * @param getProjectPlanStatusInfoUIHonbuTehaiListDao 設定する getProjectPlanStatusInfoUIHonbuTehaiListDao。
     */
    public void setProjectPlanStatusInfoUIHonbuTehaiListDao(
            UIHonbuTehaiListDao getProjectPlanStatusInfoUIHonbuTehaiListDao) {
        this.getProjectPlanStatusInfoUIHonbuTehaiListDao = getProjectPlanStatusInfoUIHonbuTehaiListDao;
    }
}
