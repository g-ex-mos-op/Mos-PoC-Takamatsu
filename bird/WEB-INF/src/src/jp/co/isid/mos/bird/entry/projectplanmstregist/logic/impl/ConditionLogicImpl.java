package jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanmstregist.dao.UICourseInfoDao;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 事業方針説明会マスタ登録
 * 【条件項目情報の取得】ロジック
 * 
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic{
    /** ロジックID */
    public static final String LOGIC_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"L01";
    /**
     * パラメーターKey： エントリーコード
     */
    public static final String PK_ENTRY_CD = "entryCd";
    /**
     * パラメーターKey： システム日付
     */
    public static final String PK_SYSDATE = "sysDate";
    
    /* DAO【対象事業方針説明会情報】 */
    private UICourseInfoDao uiCourseInfoDao;

    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        
        //エントリーコード
        String entryCd = (String)params.get(PK_ENTRY_CD);
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        //システム日付の年度を取得
        String thisNendo = DateManager.getCurrentYear(sysDate);
        //前年度前の年度
        String lastNendo = "9999";
        try{
            lastNendo = DateManager.getPrevYear(thisNendo, 1);
        }catch(Exception e){
            new FtlSystemException("事業方針説明会マスタ登録 【条件項目情報の取得】ロジック","",e);
        }
        
        //２．Dao【対象事業方針説明会情報】．検索 を実行し、
        //    実行結果[[対象事業方針説明会情報]]を取得する。
        List list = getProjectPlanMstRegUICourseInfoDao().selectList(sysDate, entryCd, lastNendo);
        
        //３．[対象事業方針説明会情報]]をリターンする。
        return list;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //エントリーコード
        String entryCd = (String)params.get(PK_ENTRY_CD);
        if (ProjectPlanMstRegistUtil.isNull(entryCd)) {
            throw new MissingDataException("エントリーコード");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (ProjectPlanMstRegistUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
    }
    /**
     * @return projectPlanMstRegUICourseInfoDao を戻します。
     */
    public UICourseInfoDao getProjectPlanMstRegUICourseInfoDao() {
        return uiCourseInfoDao;
    }
    /**
     * @param projectPlanMstRegUICourseInfoDao 設定する projectPlanMstRegUICourseInfoDao。
     */
    public void setProjectPlanMstRegMstUICourseInfoDao(UICourseInfoDao dao) {
        this.uiCourseInfoDao = dao;
    }
}
