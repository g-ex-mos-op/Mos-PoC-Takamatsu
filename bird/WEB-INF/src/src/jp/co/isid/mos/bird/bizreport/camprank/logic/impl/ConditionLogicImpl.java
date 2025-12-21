package jp.co.isid.mos.bird.bizreport.camprank.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.camprank.common.CampRankConst;
import jp.co.isid.mos.bird.bizreport.camprank.dto.CampRankCondDto;
import jp.co.isid.mos.bird.bizreport.camprank.dto.CampRankDto;
import jp.co.isid.mos.bird.bizreport.camprank.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.dao.MstCampDateDao;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 条件項目の取得設定
 * @author xnkusama
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */    
    public static final String LOGIC_ID = "BBR013L01";

    /* DAO */
    private MstCampDateDao camprankMstCampDateDao;
    
    /**
     * 条件項目の取得設定
     * @param dto
     * @return
     */
    public void execute(CampRankDto dto, CampRankCondDto requestDto) {
        // キャンペーン一覧情報取得
        List listCamp = getCamprankMstCampDateDao()
                            .selectOpen(dto.getBirdDateInfo().getSysDate(),
                                        dto.getBirdUserInfo().getUserID(),
                                        dto.getBirdUserInfo().getMstUser().getUserTypeCd(),
                                        false,
                                        dto.getCondCompanyCd(requestDto.getWindowId()),
                                        CampRankConst.CAMP_LIST_SORT_CAMP_FROM);
        dto.setListCamp(listCamp);
        // キャンペーンプルダウン用のList作成
        dto.setListCampPulldown(makeCampPulldown(listCamp));
        // 対象日プルダウン用のList作成
        dto.setListTargetDtPulldown(makeTargetDtPulldown(listCamp, dto.getBirdDateInfo().getAppDate()));
    }

    /**
     * キャンペーン選択用プルダウン作成
     * @param listCamp
     * @return
     */
    private List makeCampPulldown(List listCamp) {
        List listCampPulldown = new ArrayList();
        
        for (Iterator ite = listCamp.iterator(); ite.hasNext();) {
            MstCampDate entity = (MstCampDate) ite.next();
            SelectItem selectItem = new SelectItem(entity.getCampId(), entity.getCampTitle());
            listCampPulldown.add(selectItem);
        }
        return listCampPulldown;
    }
    
    /**
     * 対象日選択用プルダウン作成
     * @param listCamp
     * @param appDate
     * @return
     */
    private List makeTargetDtPulldown(List listCamp, String appDate) {
        List listTargetDtPulldown = new ArrayList();
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
        for (Iterator ite = listCamp.iterator(); ite.hasNext();) {
            MstCampDate entity = (MstCampDate) ite.next();
            List listPulldown = new ArrayList();
            String dateSta = entity.getCampFrom();
            String dateEnd = entity.getCampTo();
            if (appDate.compareTo(dateEnd) < 0) {
                dateEnd = appDate;
            }
            int loopCnt = 0;
            try {
                while (dateSta.compareTo(DateManager.getPrevDate(dateEnd, loopCnt)) <= 0) {
                    String nextDate = DateManager.getPrevDate(dateEnd, loopCnt);
                    SelectItem selectItem 
                        = new SelectItem(nextDate, dateFormatter.format(nextDate, true));
                    listPulldown.add(selectItem);
                    loopCnt++;
                }
            }
            catch (Exception ex) {
                throw new FtlSystemException("日付", null, ex);
            }
            listTargetDtPulldown.add(listPulldown);
        }
        return listTargetDtPulldown;
    }
    public MstCampDateDao getCamprankMstCampDateDao() {
        return camprankMstCampDateDao;
    }

    public void setCamprankMstCampDateDao(MstCampDateDao camprankMstCampDateDao) {
        this.camprankMstCampDateDao = camprankMstCampDateDao;
    }

}
