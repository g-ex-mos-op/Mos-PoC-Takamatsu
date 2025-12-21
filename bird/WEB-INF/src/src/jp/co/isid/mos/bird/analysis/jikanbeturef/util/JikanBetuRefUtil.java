package jp.co.isid.mos.bird.analysis.jikanbeturef.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;
import jp.co.isid.mos.bird.analysis.jikanbeturef.code.RowType;
import jp.co.isid.mos.bird.analysis.jikanbeturef.code.SevenKbn;
import jp.co.isid.mos.bird.analysis.jikanbeturef.code.ThreeKbn;
import jp.co.isid.mos.bird.analysis.jikanbeturef.code.TimeElement;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 時間帯別売上共通定数処理クラス
 * 
 * @author xkinu
 *
 */
public class JikanBetuRefUtil {
	/** 画面ID */
	public static final String SCREEN_ID = "BDT004";
    /** VIEWID：照会画面 */
    public static final String VIEW_ID= SCREEN_ID+"V01";
    /** ACTIONID：照会画面 */
    public static final String ACTION_ID= SCREEN_ID+"A";
    
    /** LOGICID：指定会社条件項目設定ロジックID */
    public static final String LOGIC_ID_SET_COMPANY= SCREEN_ID+"L01";
    /** LOGICID：条件項目設定ロジックID */
    public static final String LOGIC_ID_CONDITION= SCREEN_ID+"L02";
    /** LOGICID：曜日別検索ロジックID */
    public static final String LOGIC_ID_SEARCH_WEEK= SCREEN_ID+"L03";
    /** LOGICID：日別推移情報検索ロジックID */
    public static final String LOGIC_ID_SEARCH_SUII= SCREEN_ID+"L04";
    /** LOGICID：メニュー別売上種別情報検索ロジックID */
    public static final String LOGIC_ID_SEARCH_SHUBETU= SCREEN_ID+"L05";
    
    /** LOGICID：曜日別CSVダウンロードロジックID */
    public static final String LOGIC_ID_CSV_WEEK= SCREEN_ID+"L06";
    /** LOGICID：日別推移情報CSVダウンロードロジックID */
    public static final String LOGIC_ID_CSV_SUII= SCREEN_ID+"L07";
    /** LOGICID：メニュー別売上種別情報CSVダウンロードロジックID */
    public static final String LOGIC_ID_CSV_SHUBETU= SCREEN_ID+"L08";
    /** LOGICID：メニュー別売上種別情報CSVダウンロードロジックID */
    public static final String LOGIC_ID_CSV_SHUBETU_KIKAN= SCREEN_ID+"L09";
    
    public static final String WEEK_KBN_THREE = "3";
    public static final String WEEK_KBN_SEVEN = "7";
    
	public static String[][] OUTPUT_WEEKS = new String [][]{
    	{JikanBetuRefUtil.WEEK_KBN_THREE, ThreeKbn.CODE_WEEKDAY, "平日", "", ""}
        ,{JikanBetuRefUtil.WEEK_KBN_THREE, ThreeKbn.CODE_SATURDAY, "土曜", "", ""}
        ,{JikanBetuRefUtil.WEEK_KBN_THREE, ThreeKbn.CODE_HOLIDAY, "日祝", "", ""}
    	,{JikanBetuRefUtil.WEEK_KBN_SEVEN, ThreeKbn.CODE_WEEKDAY, "平日", SevenKbn.CODE_MONDAY, "月"}
        ,{JikanBetuRefUtil.WEEK_KBN_SEVEN, ThreeKbn.CODE_WEEKDAY, "平日", SevenKbn.CODE_TUESDAY, "火"}
        ,{JikanBetuRefUtil.WEEK_KBN_SEVEN, ThreeKbn.CODE_WEEKDAY, "平日", SevenKbn.CODE_WEDNESDAY, "水"}
        ,{JikanBetuRefUtil.WEEK_KBN_SEVEN, ThreeKbn.CODE_WEEKDAY, "平日", SevenKbn.CODE_THURSDAY, "木"}
        ,{JikanBetuRefUtil.WEEK_KBN_SEVEN, ThreeKbn.CODE_WEEKDAY, "平日", SevenKbn.CODE_FRIDAY, "金"}
        };
	public static String[][] OUTPUT_TIMES = new String [][]{
    	{ "0000", "00", TimeElement.CODE_LATENIGHT, "０"}
        ,{"0100", "1", TimeElement.CODE_LATENIGHT, "１"}
        ,{"0200", "2", TimeElement.CODE_LATENIGHT, "２"}
        ,{"0300", "3", TimeElement.CODE_LATENIGHT, "３"}
        ,{"0400", "4", TimeElement.CODE_LATENIGHT, "４"}
        ,{"0500", "5", TimeElement.CODE_MORNING, "５"}
        ,{"0600", "6", TimeElement.CODE_MORNING, "６"}
        ,{"0700", "7", TimeElement.CODE_MORNING, "７"}
        ,{"0800", "8", TimeElement.CODE_MORNING, "８"}
        ,{"0900", "9", TimeElement.CODE_MORNING, "９"}
        ,{"1000", "10", TimeElement.CODE_MORNING, "１０"}
        ,{"1100", "11", TimeElement.CODE_LUNCH, "１１"}
        ,{"1200", "12", TimeElement.CODE_LUNCH, "１２"}
        ,{"1300", "13", TimeElement.CODE_LUNCH, "１３"}
        ,{"1400", "14", TimeElement.CODE_AFTERNOON, "１４"}
        ,{"1500", "15", TimeElement.CODE_AFTERNOON, "１５"}
        ,{"1600", "16", TimeElement.CODE_AFTERNOON, "１６"}
        ,{"1700", "17", TimeElement.CODE_AFTERNOON, "１７"}
        ,{"1800", "18", TimeElement.CODE_DINNER, "１８"}
        ,{"1900", "19", TimeElement.CODE_DINNER, "１９"}
        ,{"2000", "20", TimeElement.CODE_NIGHT, "２０"}
        ,{"2100", "21", TimeElement.CODE_NIGHT, "２１"}
        ,{"2200", "22", TimeElement.CODE_NIGHT, "２２"}
        ,{"2300", "23", TimeElement.CODE_NIGHT, "２３"}
        ,{"9900", "99", TimeElement.CODE_ETC, ""}
        };
    
   /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();
    
//	private JikanBetuRefUtil() {
//		super();
//		// TODO 自動生成されたコンストラクター・スタブ
//	}
	/**
	 * 名称行名称取得処理
	 * 
	 * @param rowType
	 * @param name
	 */
	public static String getRowTitleName(String rowType, String name) {
		if(rowType != null) {
			//総合計の場合
			if(RowType.CD_TOTAL.equals(rowType)) {
				return "合計";
			}
			if(rowType.length() > 1 && !CommonUtil.isNull(name)) {
				//合計行の場合
				return name+"計";
			}
		}
		return name;
	}
	/**
	 * 行Cssクラス文字列名称取得処理
	 * 
	 * @param rowType
	 * @return
	 */
	public static String getTrCssClass(String rowType) {
    	if(RowType.CD_KOUSEIHI.equals(rowType)) {
    		return "body_sum5";
    	}
    	else {
    		return MenubetuUtil.getTrCssClass(rowType);
    	}
		
	}
    /**
     * 年月リストを取得する
     * 
     * @param sysDate システム日付
     * @param cnts   当月含めて過去？ヶ月
     * @return
     */
    public static List creatListNengetu(String sysDate, int cnts) {
        List list = new ArrayList();
        String startNengetu = sysDate.substring(0,6);
        for (int index=0; index<cnts; index++) {
            try {
           		String nengetu = DateManager.getPrevMonth(startNengetu, index);
	            String name = formatter.format(nengetu, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM);
	            list.add(index, createSelectItem(nengetu, name));
	        }catch (Exception ex) {
	            throw new FtlSystemException("年月リストを取得"
	                    , "指定開始日["+startNengetu+"]から["+index+"]を計算する際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
	                    , ex);
	        }
        }
        return list;
    }
    /**
     * 年月日リストを取得する
     * 
     * @param sysDate システム日付
     * @param cnts   当日含めて過去62日間
     * @return
     */
    public static List creatListNengappi(String appDate) {
        List list = new ArrayList();
        String nengappi = appDate;
        int dayPast = 61;
        
        String name = formatter.format(nengappi, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
        list.add(createSelectItem(nengappi, name));
        //62日分作ります。
        for (int i = 1; i <= dayPast ;i++) {
            try {
                nengappi = DateManager.getPrevDate(nengappi, 1);
                name = formatter.format(nengappi, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
                list.add(createSelectItem(nengappi, name));
            }catch (Exception ex) {
                throw new FtlSystemException("年月日リストを取得"
                        , "対象日["+nengappi+"]から[1]を引き算する際のDateManager.getPrevDate"
                        , ex);
            }
        }
        return list;
    }
    /**
     * エンティティ生成処理
     * @param code
     * @param name
     * @return
     */
    private static SelectItem createSelectItem(String code, String name) {
        SelectItem entity = new SelectItem();
        entity.setValue(code);
        entity.setLabel(name);
        return entity;
    }
    /**
     * 前年の翌月を取得します。
     * @param thisYM
     * @return
     */
    public static String getLastYearMonth(String thisYM, int prevCnt) {
		String lastYearMonth = null;
		try {
			lastYearMonth = DateManager.getPrevMonth(thisYM, prevCnt);
		} catch (ApplicationException appEx) {
			throw appEx;
		} catch (Exception ex) {
			throw new FtlSystemException("",
					"期間指定の値から前月の年月を取得する処理でエラーが発生しました。", ex);
		}
		return lastYearMonth;
    }
    /**
     * 
     * @param tmKbn
     * @return
     */
    public static String getTmElemName(String tmKbn) {
    	if(tmKbn == null) {
    		return "";
    	}
    	CodeFormatter cdf = new CodeFormatter(2, "00");
        cdf.setFormatPattern("00");
        tmKbn = cdf.format(tmKbn, true);
        for(int i=0; i<OUTPUT_TIMES.length; i++) {
    		if(OUTPUT_TIMES[i][0].substring(0,2).equals(tmKbn)) {
    			return OUTPUT_TIMES[i][3];
    		}
    	}
    	return "";
    }
}
