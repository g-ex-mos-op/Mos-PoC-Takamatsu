/*
 * 作成日: 2006/12/01
 *
 */
package jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao.UICourseShukketuListInfoDao;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao.UIShukketuDao;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIStatusInfo;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.CourseAttendanceListLogic;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.util.ProjectPlanStatusInfoUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 出欠席情報取得 ロジック
 * 
 * @author xamaruyama
 */
public class CourseAttendanceListLogicImpl implements CourseAttendanceListLogic {

    /** ロジックID */
    public static final String LOGIC_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"L02";
    /**
     * 
     */
    private UIShukketuDao projectPlanStatusInfoUIShukketuDao;
    /**
     * 
     */
    private UICourseShukketuListInfoDao projectPlanStatusInfoUICourseShukketuListInfoDao;
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
     * パラメーターKey：条件項目『対象』
     */
    public static final String PK_TAISHO= "taisho";
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
     * リターンKey：出欠席情報
     */
    public static final String RK_LIST_SIBUSV_ATTENDANCE = "listAttendance";
    /**
     * リターンKey：オーナー別出欠席情報
     */
    public static final String RK_LIST_ONER_ATTENDANCE= "listOnerAttendance";

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
        //パラメーター対象必須チェック
        String taishouListChoice = (String)params.get(PK_TAISHO);
        if(ProjectPlanStatusInfoUtil.isNull(taishouListChoice)){
            throw new NotNullException("対象", "taishouListChoice", 0);
        }
    }
    /**
     * 実行処理
     */
    public Map execute(final Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．リターン値Mapをインスタンス化する。
        Map rparam = new HashMap();
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
        //パラメーター対象
        String taishouListChoice = (String)params.get(PK_TAISHO);
        //パラメーター支部コード
        String sibuListChoice = (String)params.get(PK_SIBU_CD);
        //パラメーターSVコード
        String svCd = (String)params.get(PK_SV_CD);
        
        String entryCd = entity.getEntryCd();
        String entryYear = entity.getEntryYear();
        String entryKai = entity.getEntryKai();
        
        rparam.put(RK_LIST_SIBUSV_ATTENDANCE
                , executeTotalAttendance(entryCd, entryYear, entryKai, sysDate
                        , companyCd, taishouJokenChoice, taishouListChoice, sibuListChoice, svCd));
        rparam.put(RK_LIST_ONER_ATTENDANCE
                , executeOnerAttendance(limit, userId, entryCd, entryYear, entryKai, sysDate
                        , companyCd, taishouJokenChoice, taishouListChoice, sibuListChoice, svCd));
        return rparam;
    }
    /**
     * 全体の出欠席情報取得処理
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param onerEntryFrom
     * @param onerEntryTo
     * @param companyCd
     * @param taishouJokenChoice
     * @param taishouListChoice
     * @param sibuListChoice
     * @param svCd
     * @return
     */
    private List executeTotalAttendance(String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String taishouListChoice, String sibuListChoice, String svCd) {
        List total = null;
        //事業方針説明会の場合
        if(ProjectPlanStatusInfoUtil.TAISHO_PROJECTPLAN.equals(taishouListChoice)){
            total = getProjectPlanStatusInfoUIShukketuDao().selectJigyo(
                entryCd, entryYear, entryKai, sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd);
        }
        //懇親会の場合
        if(ProjectPlanStatusInfoUtil.TAISHO_KONSIN.equals(taishouListChoice)){
            total = getProjectPlanStatusInfoUIShukketuDao().selectKonshin(
                entryCd, entryYear, entryKai, sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd);
        }
        //共栄会の場合
        if(ProjectPlanStatusInfoUtil.TAISHO_KYOEIKAI.equals(taishouListChoice)){
            total = getProjectPlanStatusInfoUIShukketuDao().selectKyoei(
                entryCd, entryYear, entryKai, sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd);
        }
        return total;
    }
    /**
     * オーナー別出欠席情報取得処理
     * 
     * @param projectPlanStatusInfoDto
     * @return
     * @throws ApplicationException
     */
    private List executeOnerAttendance(boolean limit, String userId, String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String taishouListChoice, String sibuListChoice, String svCd) {

        List attendanceList = new ArrayList();
        //事業方針説明会の場合
        if(ProjectPlanStatusInfoUtil.TAISHO_PROJECTPLAN.equals(taishouListChoice)){
            attendanceList = getProjectPlanStatusInfoUICourseShukketuListInfoDao().selectJigyo(limit, userId,
                entryCd, entryYear, entryKai, sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd);
        }
        //懇親会の場合
        if(ProjectPlanStatusInfoUtil.TAISHO_KONSIN.equals(taishouListChoice)){
            attendanceList = getProjectPlanStatusInfoUICourseShukketuListInfoDao().selectKonshin(limit, userId,
                entryCd, entryYear, entryKai, sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd);
        }
        //共栄会の場合
        if(ProjectPlanStatusInfoUtil.TAISHO_KYOEIKAI.equals(taishouListChoice)){
            attendanceList = getProjectPlanStatusInfoUICourseShukketuListInfoDao().selectKyoei(limit, userId,
                entryCd, entryYear, entryKai, sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd);
        }
        return attendanceList;
    }
    
    
    /**
     * @return projectPlanStatusInfoUICourseShukketuListInfoDao を戻します。
     */
    public UICourseShukketuListInfoDao getProjectPlanStatusInfoUICourseShukketuListInfoDao() {
        return projectPlanStatusInfoUICourseShukketuListInfoDao;
    }

    /**
     * @param projectPlanStatusInfoUICourseShukketuListInfoDao 設定する projectPlanStatusInfoUICourseShukketuListInfoDao。
     */
    public void setProjectPlanStatusInfoUICourseShukketuListInfoDao(
            UICourseShukketuListInfoDao projectPlanStatusInfoUICourseShukketuListInfoDao) {
        this.projectPlanStatusInfoUICourseShukketuListInfoDao = projectPlanStatusInfoUICourseShukketuListInfoDao;
    }

    /**
     * @return projectPlanStatusInfoUIShukketuDao を戻します。
     */
    public UIShukketuDao getProjectPlanStatusInfoUIShukketuDao() {
        return projectPlanStatusInfoUIShukketuDao;
    }

    /**
     * @param projectPlanStatusInfoUIShukketuDao 設定する projectPlanStatusInfoUIShukketuDao。
     */
    public void setProjectPlanStatusInfoUIShukketuDao(
            UIShukketuDao projectPlanStatusInfoUIShukketuDao) {
        this.projectPlanStatusInfoUIShukketuDao = projectPlanStatusInfoUIShukketuDao;
    }
    
    
}
