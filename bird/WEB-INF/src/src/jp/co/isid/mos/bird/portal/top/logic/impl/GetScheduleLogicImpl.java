/*
 * 作成日: 2006/2/1
 */
package jp.co.isid.mos.bird.portal.top.logic.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.CtlUserCompany;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.portal.top.dao.UIScheduleDao;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.entity.UISchedule;
import jp.co.isid.mos.bird.portal.top.entity.UIScheduleList;
import jp.co.isid.mos.bird.portal.top.logic.GetScheduleLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

import org.seasar.framework.log.Logger;

/**
 * スケジュール情報取得処理ロジック
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public class GetScheduleLogicImpl implements GetScheduleLogic {

    /** ロジックID */
    public static final String LOGIC_ID = PortalTopUtil.SCREEN_ID+"L05";

    private static Logger logger_ = Logger.getLogger(GetScheduleLogicImpl.class);

    /* UIWhasNewDao */
    private UIScheduleDao portalTopUIScheduleDao;

    /**
     * 売上情報取得処理
     * @param userInfo
     */
    public List execute(BirdUserInfo userInfo, BirdDateInfo birdDateInfo, PortalTopDto portalTopDto) {
        
        // [ユーザ所属会社]r_company_cdを変数.配列[表示会社コードリスト]へ設定します。
        List listUserCompany = userInfo.getUserCompany();
        String[] companyList = new String[listUserCompany.size()];
        for (int i=0; i<listUserCompany.size(); i++) {
        	CtlUserCompany entity = (CtlUserCompany)listUserCompany.get(i);
        	companyList[i] = entity.getRCompanyCd();
        }
        logger_.debug("ユーザ所属会社 r_company_cd [" + companyList+ "]");
        String fromDate = "";
        String toDate = "";
        try {
    		int d=0;
    		fromDate = birdDateInfo.getSysDate();
    		while(Calendar.MONDAY != DateManager.getWeek(fromDate)) {
    			d++;
    			fromDate = DateManager.getPrevDate(birdDateInfo.getSysDate(), d);
    		}
    		d=0;
    		toDate = birdDateInfo.getSysDate();
    		while(Calendar.SUNDAY != DateManager.getWeek(toDate)) {
    			d++;
    			toDate = DateManager.getNextDate(birdDateInfo.getSysDate(), d);
    		}
        }
        catch (Exception e) {
        	throw new FtlSystemException("スケジュール情報取得処理ロジック","今週の日付処理", e);
        }
        List listTab = new ArrayList(0);
        
        UILists thisweek = new UILists();
        thisweek.setKey("thisweek");
        thisweek.setKeyName("今週");
        createWeek(thisweek, fromDate, toDate, companyList);
        
        listTab.add(thisweek);
        
        //来週のスケジュールを検索します。
        try {
   			fromDate = DateManager.getNextDate(toDate, 1);
    		int d=0;
    		toDate = fromDate;
    		while(Calendar.SUNDAY != DateManager.getWeek(toDate)) {
    			d++;
    			toDate = DateManager.getNextDate(fromDate, d);
    		}
        }
        catch (Exception e) {
        	throw new FtlSystemException("スケジュール情報取得処理ロジック","来週の日付処理", e);
        }
        UILists nextweek = new UILists();
        nextweek.setKey("nextweek");
        nextweek.setKeyName("来週");
        createWeek(nextweek, fromDate, toDate, companyList);
        listTab.add(nextweek);
        
        //今週と来週のスケジュール情報を保持したリストをリターンします。
        return listTab;
    }
    /**
     * 
     * @param fromDate
     * @param toDate
     * @param companyList
     * @return
     */
    private UILists createWeek(UILists week, String fromDate, String toDate, String[] companyList) {
        List listWeek = new ArrayList(0);
    	try {
			for(int i=0; i<7; i++) {
				UIScheduleList entityNew = new UIScheduleList();
    			entityNew.setScdlDate(DateManager.getNextDate(fromDate, i));
    			entityNew.setTitle(null);
				listWeek.add(entityNew);
			}
	    }
	    catch (Exception e) {
	    	throw new FtlSystemException("スケジュール情報取得処理ロジック",week.getKeyName()+"の日付処理", e);
	    }
        List listSearchData = getPortalTopUIScheduleDao().select(fromDate, toDate, companyList);
    	for(int w=0; w<listWeek.size(); w++) {
    		UIScheduleList entityNew = (UIScheduleList)listWeek.get(w);
    		List listInfo = new ArrayList(0);
    		for(int i=0; i<listSearchData.size(); i++) {
        		UISchedule entity = (UISchedule)listSearchData.get(i);
        		if(entityNew.getScdlDate().equals(entity.getScdlDate())) {
        			listInfo.add(entity.getTitle());
        			entityNew.setTitle(listInfo);
        		}
        		else if(entityNew.getTitle() != null){
        			break;
        		}
    		}
    	}
        week.setListData(listWeek);
        return week;

    }
	/**
	 * @return portalTopUIScheduleDao を戻します。
	 */
	public UIScheduleDao getPortalTopUIScheduleDao() {
		return portalTopUIScheduleDao;
	}

	/**
	 * @param portalTopUIScheduleDao を クラス変数portalTopUIScheduleDaoへ設定します。
	 */
	public void setPortalTopUIScheduleDao(UIScheduleDao portalTopUIScheduleDao) {
		this.portalTopUIScheduleDao = portalTopUIScheduleDao;
	}
}
