package jp.co.isid.mos.bird.config.scheduleregist.logic.impl;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.scheduleregist.code.ScheduleRegistConst;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistRequestDto;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;
import jp.co.isid.mos.bird.config.scheduleregist.entity.CtlSchedule;
import jp.co.isid.mos.bird.config.scheduleregist.logic.ScheduleRegistCheckLogic;
import jp.co.isid.mos.bird.framework.exception.InputConstraintException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoTargetException;

/**
 * 登録チェック ロジック
 * @author xnkusama
 */
public class ScheduleRegistCheckLogicImpl implements ScheduleRegistCheckLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BCF010L03";
    
    /**
     * 実行
     * @param dto
     * @param reqDto
     */
    public void execute(ScheduleRegistSessionDto dto, ScheduleRegistRequestDto reqDto) {
        // １．事前条件
        validate(dto);
        
        // ２．登録件数カウンタ ＝ ０
        int registCnt = 0;
        
        // ３．パラメータ．セッションDTO．編集スケジュールListの全件に対して下記の処理を行う
        for (int i = 0; i < dto.getListSchedule().size(); i++ ) {
            CtlSchedule entity = (CtlSchedule) dto.getListSchedule().get(i);
            // a） スケジュールタイトルが60バイトより大きい場合、下記の例外を発生
            if (entity.getTitle() != null && entity.getTitle().getBytes().length > 60) {
                dto.setActiveTabDate(entity.getScdlDate());
                reqDto.setErrFocusTabName(entity.getScdlDate());
                int dateIndex = 0;
                for (int j = 0; j < dto.getListDateTable().size(); j++) {
                    if (entity.getScdlDate().equals(dto.getListDateTable().get(j))) {
                        dateIndex = j;
                        break;
                    }
                }
                reqDto.setErrFocusDateIndex(dateIndex);
                reqDto.setErrFocusIndex(i);
                throw new InputConstraintException("スケジュール", "60バイト以内");
            }
            
            // b） [スケジュール管理]．ステータス ＝ ’9’（新規）かつスケジュールタイトル≠ブランクの場合
            if (ScheduleRegistConst.STATUS_INSERT.equals(entity.getStatus())) {
                if (!CommonUtil.isNull(entity.getTitle())) {
                    entity.setFirstUser(dto.getBirdUserInfo().getUserID());
                    entity.setFirstUserName(dto.getBirdUserInfo().getMstUser().getUser_name());
                    entity.setRegistFlg(true);
                    registCnt++;
                }
                else {
                    entity.setRegistFlg(false);
                }
            }
            
            // d） [スケジュール管理]．ステータス ＝ ’2’（更新）または’3’（削除）の場合
            if (ScheduleRegistConst.STATUS_UPDATE.equals(entity.getStatus())
                    || ScheduleRegistConst.STATUS_DELETE.equals(entity.getStatus())) 
            {
                entity.setRegistFlg(true);
                registCnt++;
            }
                
        }
        
        // ４．登録カウンタ ＝ ０の場合、下記の例外を発生
        if (registCnt == 0) {
            throw new NoTargetException("更新データ");
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
}
