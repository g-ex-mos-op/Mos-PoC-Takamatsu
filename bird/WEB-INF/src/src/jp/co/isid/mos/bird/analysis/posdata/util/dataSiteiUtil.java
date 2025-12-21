package jp.co.isid.mos.bird.analysis.posdata.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.posdata.common.PosDataConstants;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 期間指定クラス
 *
 * @author xjung
 */
public class dataSiteiUtil {
 
    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();

    /**
     * 日付リストを取得する
     * @param  appDate  アプリ日付
     * @return List     日付リスト
     */
    public static List getDateDayList(String appDate) {
    	// 日付リスト
        List dayList = new ArrayList();
 
        String appMonth = formatter.format
        	(appDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
 
        // 対象となる月を算出
        String fromMonth = PosDataConstants.EMPTY;
        try{
            fromMonth = DateManager.getPrevMonth(appMonth, PosDataConstants.DISPLAY_DAY_MONTH);
        }catch(Exception ex) {
            throw new FtlSystemException(PosDataConstants.MSG_CONDITION_ERR);
        }

        String day = PosDataConstants.EMPTY;
        int i = 0;      
        while( true ) {
            try {
            	// 日付取得(YYYYMMDD)
                day = DateManager.getPrevDate(appDate,i);
            }catch ( Exception ex ) {
                throw new FtlSystemException(PosDataConstants.MSG_CONDITION_ERR);
            }

            if ( day.substring(0,6).equals( fromMonth ) ) {
                break;
            }

            SelectItem item = item = new SelectItem(day, formatter.format
            	(day, DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD));
            dayList.add( item );
 
            day = PosDataConstants.EMPTY;
            i++;
        }
        return dayList;
    }
}