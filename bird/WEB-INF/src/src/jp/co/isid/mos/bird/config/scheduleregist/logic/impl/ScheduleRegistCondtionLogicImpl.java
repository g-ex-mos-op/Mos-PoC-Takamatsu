package jp.co.isid.mos.bird.config.scheduleregist.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.config.scheduleregist.code.ScheduleRegistConst;
import jp.co.isid.mos.bird.config.scheduleregist.dao.UICompanyPulldownDao;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;
import jp.co.isid.mos.bird.config.scheduleregist.entity.UICompanyPulldown;
import jp.co.isid.mos.bird.config.scheduleregist.logic.ScheduleRegistCondtionLogic;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * スケジュール登録 条件項目取得ロジック 
 * @author xnkusama
 */
public class ScheduleRegistCondtionLogicImpl implements ScheduleRegistCondtionLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BCF010L01";
    
    private UICompanyPulldownDao scheduleregistUICompanyPulldownDao;
    
    /**
     * 実行
     * @param dto
     */
    public void execute(ScheduleRegistSessionDto dto) {
        // １．事前条件処理
        validate(dto);
        
        // ２．【DAO】会社プルダウンDao．会社一覧取得を実行
        List listCompany = getScheduleregistUICompanyPulldownDao().getCompanyList(dto.getBirdUserInfo().getUserID());
        
        // ３．【Entity】 UICompanyPulldownを新規作成し、下記の値をセット
        UICompanyPulldown uiCompanyPulldown = new UICompanyPulldown();
        uiCompanyPulldown.setCompanyCd("");
        uiCompanyPulldown.setCompanyName("全会社共通");
        
        // ４．処理２で取得したListに処理３で作成したEntityを先頭に追加
        if (listCompany == null) {
            listCompany = new ArrayList();
        }
        listCompany.add(0, uiCompanyPulldown);
        
        // ５．対象週プルダウン用Listを作成。下記の値を持つSelectItemを作成し、Listに追加する。
        List listTargetWeek = new ArrayList();
        SelectItem selectItemThisWeek = new SelectItem(ScheduleRegistConst.TARGET_WEEK_THIS_WEEK, ScheduleRegistConst.TARGET_WEEK_THIS_WEEK_LABEL);
        SelectItem selectItemNextWeek = new SelectItem(ScheduleRegistConst.TARGET_WEEK_NEXT_WEEK, ScheduleRegistConst.TARGET_WEEK_NEXT_WEEK_LABEL);
        SelectItem selectItemNext2Week = new SelectItem(ScheduleRegistConst.TARGET_WEEK_NEXT_2WEEK, ScheduleRegistConst.TARGET_WEEK_NEXT_2WEEK_LABEL);
        SelectItem selectItemNext3Week = new SelectItem(ScheduleRegistConst.TARGET_WEEK_NEXT_3WEEK, ScheduleRegistConst.TARGET_WEEK_NEXT_3WEEK_LABEL);
        listTargetWeek.add(selectItemThisWeek);
        listTargetWeek.add(selectItemNextWeek);
        listTargetWeek.add(selectItemNext2Week);
        listTargetWeek.add(selectItemNext3Week);
        
        // ６．編集画面 ステータスプルダウン用Liｓｔを作成。下記の値を持つSelectItemを作成し、Listに追加する。
        List listStatus = new ArrayList();
        SelectItem selectItemStatusNoEdit = new SelectItem(ScheduleRegistConst.STATUS_NO_EDIT, ScheduleRegistConst.STATUS_NO_EDIT_LABEL);
        SelectItem selectItemStatusUpdate = new SelectItem(ScheduleRegistConst.STATUS_UPDATE, ScheduleRegistConst.STATUS_UPDATE_LABEL);
        SelectItem selectItemStatusDelete = new SelectItem(ScheduleRegistConst.STATUS_DELETE, ScheduleRegistConst.STATUS_DELETE_LABEL);
        listStatus.add(selectItemStatusNoEdit);
        listStatus.add(selectItemStatusUpdate);
        listStatus.add(selectItemStatusDelete);
        
        // ７．下記の値をパラメータDTOにセット
        dto.setListCondCompany(listCompany);
        dto.setListCondTargetWeek(listTargetWeek);
        dto.setListStatus(listStatus);
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

    public UICompanyPulldownDao getScheduleregistUICompanyPulldownDao() {
        return scheduleregistUICompanyPulldownDao;
    }

    public void setScheduleregistUICompanyPulldownDao(
            UICompanyPulldownDao scheduleregistUICompanyPulldownDao) {
        this.scheduleregistUICompanyPulldownDao = scheduleregistUICompanyPulldownDao;
    }
}
