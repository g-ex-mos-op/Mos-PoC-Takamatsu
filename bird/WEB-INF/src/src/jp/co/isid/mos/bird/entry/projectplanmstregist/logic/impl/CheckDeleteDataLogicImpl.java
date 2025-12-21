package jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.UICourseInfo;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.CheckDeleteDataLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 事業方針説明会マスタ登録
 * 
 * 削除処理不可判断ロジック
 * 
 * @author xkinu
 *
 */
public class CheckDeleteDataLogicImpl implements CheckDeleteDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"L04";
    /**
     * パラメーターKey： 管理マスタリスト
     */
    public static final String PK_ENTITY_COURSE = "entityCourse";
    /**
     * パラメーターKey： システム日付
     */
    public static final String PK_SYSDATE = "sysDate";
    
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //対象事業方針説明会情報
        UICourseInfo entityCourse = (UICourseInfo)params.get(PK_ENTITY_COURSE);
        if (entityCourse == null) {
            throw new MissingDataException("対象事業方針説明会情報");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (ProjectPlanMstRegistUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
    }

    /**
     * 実行処理
     * 
     * @param params
     * @return null
     */
    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //対象事業方針説明会情報
        UICourseInfo entityCourse = (UICourseInfo)params.get(PK_ENTITY_COURSE);
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        
        //２．本部またはオーナーの申込が開始されている説明会か判断を行う。
        String entryDt = entityCourse.getEntryFrom();
        String onerEntryDt = entityCourse.getOnerEntryFrom();
        
        if(entryDt.compareTo(sysDate) <= 0 || onerEntryDt.compareTo(sysDate) <= 0){
           // Exception:  MSG【E0404】(”申込が開始されている為、削除できません。”）
           throw new CannotExecuteWithReasonException("申込が開始されている", "削除");
        }
        //３．nullをリターンする。
        return null;
    }

}
