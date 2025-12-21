package jp.co.isid.mos.bird.config.scheduleregist.logic.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.scheduleregist.code.ScheduleRegistConst;
import jp.co.isid.mos.bird.config.scheduleregist.dao.CtlScheduleDao;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;
import jp.co.isid.mos.bird.config.scheduleregist.entity.CtlSchedule;
import jp.co.isid.mos.bird.config.scheduleregist.logic.SearchScheduleLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 編集画面情報の取得ロジック 
 * @author xnkusama
 */
public class SearchScheduleLogicImpl implements SearchScheduleLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BCF010L02";
    
    private CtlScheduleDao scheduleregistCtlScheduleDao;
    
    /**
     * 実行
     * @param dto
     */
    public void execute(ScheduleRegistSessionDto dto) {
        // １．事前条件処理
        validate(dto);
        // ２．検索パラメータ作成
        String taishoCd;
        String companyCd;
        String kikanFrom;
        String kikanTo;
        //対象コード、会社コード
        if (CommonUtil.isNull(dto.getCondCompany())) {
            taishoCd = ScheduleRegistConst.TAISHO_CD_ALL;
            companyCd = ScheduleRegistConst.TAISHO_CD_ALL_COMPANY;
        }
        else {
            taishoCd = ScheduleRegistConst.TAISHO_CD_NOT_ALL;
            companyCd = dto.getCondCompany();
        }
        //期間From
        kikanFrom = getWeekFirstDay(dto.getBirdDateInfo().getSysDate(), dto.getCondTargetWeek());
        kikanTo = getWeekLastDay(dto.getBirdDateInfo().getSysDate(), dto.getCondTargetWeek());
        
        // ３．【DAO】スケジュール管理Dao．スケジュール取得を実行
        List listSchedule = getScheduleregistCtlScheduleDao().selectScheduleList(taishoCd, companyCd, kikanFrom, kikanTo);
        
        // ４．処理２で求めた期間Fromから期間Toまでの各日付を持つリストを作成。
        List listDateTab = new ArrayList();
        for (int i = 0; i < 7; i++) {
            listDateTab.add(getNextDate(kikanFrom, i));
        }
        
        // ５．処理２で取得したスケジュール情報が、各日付10件になるように足りない行を追加する
        List listAllSchedule = doPaddingList(dto, listSchedule, kikanFrom);
        
        // ６．初期選択タブの日付をセット
        dto.setActiveTabDate(kikanFrom);
        
        // ７．下記の値をパラメータDTOへセット
        dto.setListSchedule(listAllSchedule);
        dto.setListDateTable(listDateTab);
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

    /**
     * 指定日付から指定日数後を取得
     * @param day
     * @param appned
     * @return
     */
    private String getNextDate(String day, int appned) {
        try {
            return DateManager.getNextDate(day, appned);
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
        
    }
    
    /**
     * 検索スケジュールデータの各日の件数が10件になるようにする
     * @param dto
     * @param listData
     * @param kikanFrom
     * @return
     */
    private List doPaddingList(ScheduleRegistSessionDto dto, List listData, String kikanFrom) {
        List listAllSchedule = new ArrayList();
        List listDay = new ArrayList();
        for (int i = 0; i < 7; i++) {
            List listDayUnit = new ArrayList();
            String kizyunDt = getNextDate(kikanFrom, i);
            for (Iterator ite = listData.iterator(); ite.hasNext();) {
                CtlSchedule entity = (CtlSchedule) ite.next();
                if (kizyunDt.equals(entity.getScdlDate())) {
                    listDayUnit.add(entity);
                    ite.remove();
                }
            }
            listDay.add(listDayUnit);
        }
        
        for (int i = 0; i < listDay.size(); i++) {
            List listDayUnit = (List) listDay.get(i);
            String kizyunDt = getNextDate(kikanFrom, i);
            while(listDayUnit.size() < ScheduleRegistConst.SCHEDULE_MAX_COUNT) {
                CtlSchedule entity = new CtlSchedule();
                entity.setScdlDate(kizyunDt);
                entity.setStatus(ScheduleRegistConst.STATUS_INSERT);
                entity.setSeq(String.valueOf(listDayUnit.size() + 1));
                entity.setSakujoFlg("");
                //対象コード
                if (!CommonUtil.isNull(dto.getCondCompany())) {
                    entity.setTaishoCd(ScheduleRegistConst.TAISHO_CD_NOT_ALL);
                    entity.setCompanyCd(dto.getCondCompany());
                }
                else {
                    entity.setTaishoCd(ScheduleRegistConst.TAISHO_CD_ALL);
                    entity.setCompanyCd(ScheduleRegistConst.TAISHO_CD_ALL_COMPANY);
                }
                listDayUnit.add(entity);
            }
            listAllSchedule.addAll(listDayUnit);
        }
        
        return listAllSchedule;
    }
    
    /**
     * 指定週後の月曜日を求める
     * @param sysDt
     * @param week 対象週（0:今週　1:来週　2:再来週　3:再々来週）
     * @return
     */
    private String getWeekFirstDay(String sysDt, String week) {
        String calcDay = "";
        try {
            String kizyunDt = DateManager.getNextDate(sysDt, Integer.valueOf(week).intValue() * 7);
            for (int i = 0; i < 7; i++) {
                calcDay = DateManager.getPrevDate(kizyunDt, i);
                if (Calendar.MONDAY == DateManager.getWeek(calcDay)) {
                    break;
                }
            }
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
        
        return calcDay;
    }
    
    /**
     * 指定週後の日曜日を求める
     * @param sysDt
     * @param week 対象週（0:今週　1:来週　2:再来週　3:再々来週）
     * @return
     */
    private String getWeekLastDay(String sysDt, String week) {
        String calcDay = "";
        try {
            String kizyunDt = DateManager.getNextDate(sysDt, Integer.valueOf(week).intValue() * 7);
            for (int i = 0; i < 7; i++) {
                calcDay = DateManager.getNextDate(kizyunDt, i);
                if (Calendar.SUNDAY == DateManager.getWeek(calcDay)) {
                    break;
                }
            }
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
        
        return calcDay;
    }
    
    public CtlScheduleDao getScheduleregistCtlScheduleDao() {
        return scheduleregistCtlScheduleDao;
    }

    public void setScheduleregistCtlScheduleDao(
            CtlScheduleDao scheduleregistCtlScheduleDao) {
        this.scheduleregistCtlScheduleDao = scheduleregistCtlScheduleDao;
    }
}
