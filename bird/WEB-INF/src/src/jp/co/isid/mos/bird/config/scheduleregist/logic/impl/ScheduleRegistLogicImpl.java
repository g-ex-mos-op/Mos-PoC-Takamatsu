package jp.co.isid.mos.bird.config.scheduleregist.logic.impl;

import java.util.Iterator;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.scheduleregist.code.ScheduleRegistConst;
import jp.co.isid.mos.bird.config.scheduleregist.dao.CtlScheduleDao;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;
import jp.co.isid.mos.bird.config.scheduleregist.entity.CtlSchedule;
import jp.co.isid.mos.bird.config.scheduleregist.logic.ScheduleRegistLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * 登録 ロジック インターフェイス
 * @author xnkusama
 */
public class ScheduleRegistLogicImpl implements ScheduleRegistLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BCF010L04";
    
    private CtlScheduleDao scheduleregistCtlScheduleDao;
    
    /**
     * 実行
     * @param dto
     */
    public void execute(ScheduleRegistSessionDto dto) {
        // １．事前条件
        validate(dto);
        
        CodeFormatter codeFormmater = new CodeFormatter(3);
        codeFormmater.setFormatPattern("000");
        
        // ２．パラメータ．セッションDTO．編集スケジュールListの全件に対して下記の処理を行う
        for (Iterator ite = dto.getListSchedule().iterator(); ite.hasNext();) {
            CtlSchedule entity = (CtlSchedule) ite.next();
            
            // a） [スケジュール管理]．ステータス ＝ ’9’（新規）かつ更新対象フラグ＝trueの場合
            if (ScheduleRegistConst.STATUS_INSERT.equals(entity.getStatus()) && entity.isRegistFlg()) {
                //最大スケジュールID取得
                String maxId = getScheduleregistCtlScheduleDao().getMaxScheduleId(entity.getTaishoCd(), entity.getCompanyCd(), entity.getScdlDate());
                if (CommonUtil.isNull(maxId)) {
                    maxId = "0";
                }
                int scdlId = Integer.valueOf(maxId).intValue() + 1;
                if (scdlId > ScheduleRegistConst.DB_ENABLE_MAX_COUNT) {
                    throw new CannotExecuteException("登録可能件数を超えています。登録");
                }
                
                entity.setScdlId(codeFormmater.format(String.valueOf(scdlId), true));
                entity.setFirstUser(dto.getBirdUserInfo().getUserID());
                entity.setFirstPgm("BCF010");
                entity.setFirstTmsp(DateManager.getCurrentTimestamp());
                entity.setLastUser(dto.getBirdUserInfo().getUserID());
                entity.setLastPgm("BCF010");
                entity.setLastTmsp(DateManager.getCurrentTimestamp());
                // ⅱ． 【DAO】スケジュール管理Dao．新規登録を実行
                getScheduleregistCtlScheduleDao().insertSchedule(entity);
            }
            // b） [スケジュール管理]．ステータス ＝ ’2’（更新）の場合
            else if (ScheduleRegistConst.STATUS_UPDATE.equals(entity.getStatus())) {
                entity.setLastUser(dto.getBirdUserInfo().getUserID());
                entity.setLastPgm("BCF010");
                // ⅱ． 【DAO】スケジュール管理Dao．更新登録を実行
                getScheduleregistCtlScheduleDao().updateScheduleList(entity);
            }
            // c） [スケジュール管理]．ステータス ＝ ’3’（削除）の場合
            else if (ScheduleRegistConst.STATUS_DELETE.equals(entity.getStatus())) {
                // ⅰ． 下記の値をセット
                entity.setTitle(entity.getTitleDb());
                entity.setSakujoFlg(ScheduleRegistConst.SAKUJO_FLG_ON);
                entity.setLastUser(dto.getBirdUserInfo().getUserID());
                entity.setLastPgm("BCF010");
                // ⅱ． 【DAO】スケジュール管理Dao．削除を実行
                getScheduleregistCtlScheduleDao().updateScheduleListSakujoFlg(entity);
            }
        }
    }

    /**
     * 事前条件
     * @param dto
     */
    private void validate(ScheduleRegistSessionDto dto) {
        if (dto == null) {
            throw new MissingDataException("画面情報");
        }
    }
    
    public CtlScheduleDao getScheduleregistCtlScheduleDao() {
        return scheduleregistCtlScheduleDao;
    }

    public void setScheduleregistCtlScheduleDao(
            CtlScheduleDao scheduleregistCtlScheduleDao) {
        this.scheduleregistCtlScheduleDao = scheduleregistCtlScheduleDao;
    }

}
