package jp.co.isid.mos.bird.bizreport.netorderreport.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.netorderreport.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.netorderreport.logic.ConditionLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

public class ConditionLogicImpl implements ConditionLogic{

    /* ロジックID */
    public static final String LOGIC_ID = "BBR019L01";

    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();

    public Map execute(String userType, String userId, String appDate) {

		Map map = new HashMap();
		// パラメータの入力チェック
		if (userType == null || userType.trim().length() == 0) {
			throw new NotNullException(BizReportConstants.MSG_USER_TYPE);
		}
		if (userId == null || userId.trim().length() == 0) {
			throw new NotNullException(BizReportConstants.MSG_USER_ID);
		}
		if (appDate == null || appDate.trim().length() == 0) {
			throw new NotNullException(BizReportConstants.MSG_APP_DATE);
		}

    	// 対象期間リスト取得
        List taishoKikanList = TaishoKikan.getPullDownList(userType);

        // 期間指定年月日(yyyy/MM/dd)リスト取得
        List kikanYmdList = getDateDayList(appDate);

        // 期間指定年月(yyyy/MM)リスト取得
        List kikanYmList = getDateMonthList(appDate);
        // 前14個の年月
        List kikanYmList1 =kikanYmList.subList(0, 14);
		// 取得したリストをMapへ格納

        map.put(BizReportConstants.TAISHO_KIKAN_LIST, taishoKikanList);
        map.put(BizReportConstants.KIKAN_YMD_LIST, kikanYmdList);
        map.put(BizReportConstants.KIKAN_YM_LIST, kikanYmList1);

        return map;
    }

    /**
     * 日付リストを取得する
     * @param  appDate  アプリ日付
     * @return List     日付リスト
     */
    private static List getDateDayList(String appDate) {
    	// 日付リスト
        List dayList = new ArrayList();

        String appMonth = formatter.format
        	(appDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);

        // 対象となる月を算出
        String fromMonth = BizReportConstants.EMPTY;
        try{
            fromMonth = DateManager.getPrevMonth(appMonth, BizReportConstants.DISPLAY_DAY_MONTH);
        }catch(Exception ex) {
            throw new FtlSystemException(BizReportConstants.MSG_CONDITION_ERR);
        }

        String day = BizReportConstants.EMPTY;
        int i = 0;
        while( true ) {
            try {
            	// 日付取得(YYYYMMDD)
                day = DateManager.getPrevDate(appDate,i);
            }catch ( Exception ex ) {
                throw new FtlSystemException(BizReportConstants.MSG_CONDITION_ERR);
            }

            if ( day.substring(0,6).equals( fromMonth ) ) {
                break;
            }

            SelectItem item = item = new SelectItem(day, formatter.format
            	(day, DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD));
            dayList.add( item );

            day = BizReportConstants.EMPTY;
            i++;
        }
        return dayList;
    }

    /**
     * 年月リストを取得する。
     * @param  appDate  アプリ日付
     * @return List     年月リスト
     */
    private static List getDateMonthList(String appDate) {
    	// 年月リスト
    	List yyyyMMList = new ArrayList();

    	// フォーマットパターン：YYYYMM、日付タイプ＝１
        String appMonth = formatter.format
        	(appDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);

        // 指定した年月の年度を取得する
        String currentYear = DateManager.getCurrentYear(appMonth);

        // From年度
        String fromYear = BizReportConstants.EMPTY;
        try {
        	// 現在から指定年前の年を取得
            fromYear = DateManager.getPrevYear(currentYear, BizReportConstants.DISPLAY_MONTH_YEAR);
        } catch(Exception ex ) {
            throw new FtlSystemException(BizReportConstants.MSG_CONDITION_ERR);
        }

        // 年月リスト
        String yyyyMM = BizReportConstants.EMPTY;
        int i = 0;
        while(true) {
            try {
            	// 現在から指定月前の日付を取得(yyyyMM)
            	yyyyMM = DateManager.getPrevMonth(appMonth, i);
            }catch (Exception ex) {
                throw new FtlSystemException(BizReportConstants.MSG_CONDITION_ERR);
            }

            // 指定した年月の年度を取得し、From年度と一致した場合
            if (DateManager.getCurrentYear(yyyyMM).equals(fromYear)) {
                break;
            }

            SelectItem item = new SelectItem (yyyyMM, formatter.format(yyyyMM,
            		DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM));
            yyyyMMList.add(item);

        	yyyyMM = BizReportConstants.EMPTY;
            i++;
        }
        return yyyyMMList;
    }

}
