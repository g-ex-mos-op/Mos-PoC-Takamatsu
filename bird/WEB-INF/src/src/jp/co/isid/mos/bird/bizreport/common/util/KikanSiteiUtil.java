package jp.co.isid.mos.bird.bizreport.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.code.Kibetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 期間指定クラス
 *
 * @author xjung
 */
public class KikanSiteiUtil {
 
    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();

    /**
     * 年度リストを取得する
     * @param  appDate アプリ日付
     * @return List 年度リスト
     */
    public static List getDateYearList(String appDate) {
    	// 年度リスト
    	List yearList = new ArrayList();

        // フォーマットパターン：YYYYMM、日付タイプ＝１
        String appMonth = formatter.format
        	(appDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);

        // 指定した年月の年度を取得
        String appNendo = DateManager.getCurrentYear(appMonth);

        for (int i = 0; i < BizReportConstants.DISPLAY_YEAR; i++) {
            String year = BizReportConstants.EMPTY;
            try {
            	// 現在から指定年前の年を取得
                year = DateManager.getPrevYear(appNendo, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException(BizReportConstants.MSG_CONDITION_ERR);
            }
            SelectItem item = new SelectItem(year, year);
            yearList.add(item);
        }
        return yearList;       
    }

    /**
     * 年月リストを取得する。
     * @param  appDate  アプリ日付
     * @return List     年月リスト
     */
    public static List getDateMonthList(String appDate) {
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
//        int i = 0;
        int i = 1;
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
    
    
    /**
     * 期間指定の種類を取得
     * 
     * 任意指定の年月含めて過去「リスト個数」月分を生成します。
     * 
     * 2008/10/06 追加
     * 
     * @param      String 任意指定の年月
     * @param      int    リスト個数
     * @return     List   期間指定データ
     * @exception ApplicationException
     */
    public static List getOptionalMonthList(String yyyyMM, int cnt)  {
        List list = new ArrayList();
        DateFormatter dformat = new DateFormatter();
        for (int index=0; index<cnt; index++) {
            String code = "";
            try {
                code = DateManager.getPrevMonth(yyyyMM, index);
            }catch (Exception ex) {
                throw new FtlSystemException("期間指定生成"
                        , "年月["+yyyyMM+"]から["+index+"]を引く際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            String name = dformat.format(code, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM);
            list.add(index, createEntity(code, name));
        }
        if (list == null || list.size() == 0) {
            throw new FtlSystemException(BizReportConstants.MSG_CONDITION_ERR);
        }
        return list;
    }

    /**
     * エンティティ生成処理
     * 
     * 2008/10/06 追加
     * 
     * @param code
     * @param name
     * @return
     */
    private static CodKikanSitei createEntity(String code, String name) {
        CodKikanSitei entity = new CodKikanSitei();
        entity.setCode(code);
        entity.setName(name);
        return entity;
    }


 
    /**
     * 当月年月を取得する
     * @param  appDate  アプリ日付
     * @return String   当月年月(YYYYMM)
     */
    public static String getCurrentMonth(String appDate) {
    	// 当月年月(YYYYMM)
        String currentMonth = formatter.format
        	(appDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD );
 
        return currentMonth;
    }

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
     * 期間指定FROM、期間指定TOの整合性チェックをする
     * @param fromDt	期間指定From
     * @param toDt		期間指定To
     * @return boolean true：正常(FromDt ≦toDt)、flase:エラー
     */
    public static boolean validateDateFromTo(String fromDt, String toDt) {        
        if (Integer.parseInt(fromDt) - Integer.parseInt(toDt) > 0 ) {
        	return false;
        }
        return true;
    }

    /**
     * 期間指定FROMと期間指定TOが９２日以内であることをチェックする
     * @param fromDt	期間指定From
     * @param toDt		期間指定To
     * @return boolean true：正常、flase:エラー
     */
    public static boolean validateDateLimit(String fromDt, String toDt) {
    	boolean resultFlg = false;

    	for ( int i = 0; i < BizReportConstants.LIMIT_DAY; i++ ) {
    		String day = BizReportConstants.EMPTY;
            try{            	
                day = DateManager.getPrevDate(toDt, i);
            }catch(Exception ex) {
                throw new FtlSystemException(BizReportConstants.MSG_CONDITION_ERR);
            }
            if (day.equals(fromDt)) {
            	resultFlg = true;
            	break;
            }
        }
        return resultFlg;
    }

    /**
     * 年、期から対象期間を算出する
     * @param nendo 年度
     * @param ki 期
     * @return monthList 月リスト
     */
    public static List henkanKiMonth( String nendo, String ki ) {
        List monthList = new ArrayList();
        String nextNen = String.valueOf(Integer.parseInt( nendo ) + 1);

        if ( ki.equals( Kibetu.ICHI_SIHANKI ) ) {
            monthList.add( nendo + BizReportConstants.MONTH_04);
            monthList.add( nendo + BizReportConstants.MONTH_06);
        } else if ( ki.equals( Kibetu.NI_SIHANKI ) ){
            monthList.add( nendo + BizReportConstants.MONTH_07);
            monthList.add( nendo + BizReportConstants.MONTH_09);
        } else if ( ki.equals( Kibetu.KAMIKI ) ) {
            monthList.add( nendo + BizReportConstants.MONTH_04);
            monthList.add( nendo + BizReportConstants.MONTH_09);
        } else if ( ki.equals( Kibetu.SAN_SIHANKI ) ) {
            monthList.add( nendo + BizReportConstants.MONTH_10);
            monthList.add( nendo + BizReportConstants.MONTH_12);
        } else if ( ki.equals( Kibetu.YON_SIHANKI ) ) {
            monthList.add( nextNen + BizReportConstants.MONTH_01);
            monthList.add( nextNen + BizReportConstants.MONTH_03);
        } else if ( ki.equals( Kibetu.SIMOKI ) ) {
            monthList.add( nendo + BizReportConstants.MONTH_10);
            monthList.add( nextNen + BizReportConstants.MONTH_03);
        } else if ( ki.equals( Kibetu.NENDO ) ) {
            monthList.add( nendo + BizReportConstants.MONTH_04);
            monthList.add( nextNen + BizReportConstants.MONTH_03);
        }
        return monthList;
    }
}