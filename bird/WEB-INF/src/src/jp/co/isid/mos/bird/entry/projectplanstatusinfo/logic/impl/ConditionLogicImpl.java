package jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao.UISibuListDao;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao.UIStatusInfoDao;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.ConditionLogic;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.util.ProjectPlanStatusInfoUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;


/**
 * 事業方針説明会申込状況確認
 * 【条件項目情報の取得】ロジック
 * 
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic{
    /** ロジックID */
    public static final String LOGIC_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"L01";
    /** 
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = "userInfo";
    /** 
     * パラメーターKey：システム日付 
     */
    public static final String PK_SYSDATE = "sysDate";
    /**
     * パラメーターKey： 会社コード
     */
    public static final String PK_COMPANY_CD = "companyCd";
    /**
     * パラメーターKey： エントリーコード
     */
    public static final String PK_ENTRY_CD = "entryCd";
    /**
     * パラメーターKey：エントリー年度
     */
    public static final String PK_ENTRY_YEAR= "entryYear";
    /**
     * パラメーターKey：エントリー回
     */
    public static final String PK_ENTRY_KAI= "entryKai";
    /**
     * リターンKey：対象事業方針説明会状況情報
     */
    public static final String RK_LIST_STATUS_INFO= "listStatusInfo";
    /**
     * リターンKey：支部情報取得
     */
    public static final String RK_LIST_SIBU= "listSibu";
    
    /* DAO【対象事業方針説明会状況情報】 */
    private UIStatusInfoDao projectPlanStatusInfoUIStatusInfoDao;
    /* DAO【支部情報取得】*/
    private UISibuListDao projectPlanStatusInfoUISibuListDao;

    public Map execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．リターン値Mapをインスタンス化する。
        Map remap = new HashMap();
        //ユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        boolean limitKbn = userInfo.isLimit();
        String userId = userInfo.getUserID();
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        //エントリーコード
        String entryCd = (String)params.get(PK_ENTRY_CD);
        //エントリー年
        String entryYear = (String)params.get(PK_ENTRY_YEAR);
        //エントリー回
        String entryKai = (String)params.get(PK_ENTRY_KAI);
        
        //３．Dao【支部情報取得】．検索 を実行し、
        //    実行結果[[支部情報取得]]を取得する。
        List listSibu = getProjectPlanStatusInfoUISibuListDao().getSibuList(limitKbn, companyCd, userId);
        remap.put(RK_LIST_SIBU, listSibu);
        //４．Dao【対象事業方針説明会状況情報】.検索を実行する。
        List listStatusInfo = getProjectPlanStatusInfoUIStatusInfoDao().getCode(entryCd, entryYear, entryKai, companyCd, sysDate);
        remap.put(RK_LIST_STATUS_INFO, listStatusInfo);
        //５．リターン値Mapをリターンする。
        return remap;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //ユーザ情報
        if (params.get(PK_USERINFO) == null || !(params.get(PK_USERINFO) instanceof BirdUserInfo)) {
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (ProjectPlanMstRegistUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        if (ProjectPlanStatusInfoUtil.isNull(companyCd)) {
            throw new MissingDataException("会社コード");
        }
        //エントリーコード
        String entryCd = (String)params.get(PK_ENTRY_CD);
        if (ProjectPlanStatusInfoUtil.isNull(entryCd)) {
            throw new MissingDataException("エントリーコード");
        }
        //エントリー年
        String entryYear = (String)params.get(PK_ENTRY_YEAR);
        if (ProjectPlanStatusInfoUtil.isNull(entryYear)) {
            throw new MissingDataException("エントリー年");
        }
        //エントリー回
        String entryKai = (String)params.get(PK_ENTRY_KAI);
        if (ProjectPlanStatusInfoUtil.isNull(entryKai)) {
            throw new MissingDataException("エントリー回");
        }
    }
    /**
     * @return projectPlanStatusInfoUISibuListDao を戻します。
     */
    public UISibuListDao getProjectPlanStatusInfoUISibuListDao() {
        return projectPlanStatusInfoUISibuListDao;
    }
    /**
     * @param projectPlanStatusInfoUISibuListDao 設定する projectPlanStatusInfoUISibuListDao。
     */
    public void setProjectPlanStatusInfoUISibuListDao(
            UISibuListDao projectPlanStatusInfoUISibuListDao) {
        this.projectPlanStatusInfoUISibuListDao = projectPlanStatusInfoUISibuListDao;
    }
    /**
     * @return projectPlanStatusInfoUIStatusInfoDao を戻します。
     */
    public UIStatusInfoDao getProjectPlanStatusInfoUIStatusInfoDao() {
        return projectPlanStatusInfoUIStatusInfoDao;
    }
    /**
     * @param projectPlanStatusInfoUIStatusInfoDao 設定する projectPlanStatusInfoUIStatusInfoDao。
     */
    public void setProjectPlanStatusInfoUIStatusInfoDao(
            UIStatusInfoDao projectPlanStatusInfoUIStatusInfoDao) {
        this.projectPlanStatusInfoUIStatusInfoDao = projectPlanStatusInfoUIStatusInfoDao;
    }
}
