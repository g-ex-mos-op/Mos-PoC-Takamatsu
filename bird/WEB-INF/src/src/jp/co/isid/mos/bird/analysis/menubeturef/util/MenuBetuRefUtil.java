package jp.co.isid.mos.bird.analysis.menubeturef.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * メニュー別売上共通定数処理クラス
 * 
 * @author xkinu
 *
 */
public class MenuBetuRefUtil {
	/** 画面ID */
	public static final String SCREEN_ID = "BDT002";
    /** VIEWID：照会画面 */
    public static final String VIEW_ID= SCREEN_ID+"V01";
    /** ACTIONID：照会画面 */
    public static final String ACTION_ID= SCREEN_ID+"A";
    
    /** LOGICID：指定会社条件項目設定ロジックID */
    public static final String LOGIC_ID_SET_COMPANY= SCREEN_ID+"L01";
    /** LOGICID：条件項目設定ロジックID */
    public static final String LOGIC_ID_CONDITION= SCREEN_ID+"L02";
    /** LOGICID：ABC分析表(メニュー別売上情報)検索ロジックID */
    public static final String LOGIC_ID_SEARCH_ABC= SCREEN_ID+"L03";
    /** LOGICID：メニュー別種別個数情報検索ロジックID */
    public static final String LOGIC_ID_SEARCH_SHUBETU= SCREEN_ID+"L04";
    /** LOGICID：食材準備目安表情報検索ロジックID */
    public static final String LOGIC_ID_SEARCH_MEYASU= SCREEN_ID+"L05";
    /** LOGICID：ABC分析表(メニュー別売上情報)CSVダウンロードロジックID */
    public static final String LOGIC_ID_CSV_ABC= SCREEN_ID+"L06";
    /** LOGICID：メニュー別種別個数情報CSVダウンロードロジックID */
    public static final String LOGIC_ID_CSV_SHUBETU= SCREEN_ID+"L07";
    /** LOGICID：食材準備目安表情報CSVダウンロードロジックID */
    public static final String LOGIC_ID_CSV_MEYASU= SCREEN_ID+"L08";
    
   /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();
    
	private MenuBetuRefUtil() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
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
    	if(RowType.CD_TOTAL_ALL.equals(rowType)) {
    		return "row_total_all";
    	}
    	if(RowType.CD_TOTAL.equals(rowType)) {
    		return "row_total";
    	}
    	else if(RowType.CD_MENU_BUNRUI.equals(rowType)) {
    		return "row_total_mbunrui";
    	}
		else if(RowType.CD_MENU.equals(rowType)) {
    		return "row_total_menu";
    	}
   		return "";
		
	}
    /**
     * 年月リストを取得する
     * 
     * @param appDate アプリ日付
     * @param cnts   当月含めて過去？ヶ月
     * @return
     */
    public static List creatListNengetu(String appDate, int cnts) {
        List list = new ArrayList();
        String startNengetu = appDate.substring(0,6);
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
     * @param appDate アプリ日付
     * @param cnts
     * @return
     */
    public static List creatListNengappi(String appDate, int cnts) {
        String endDate = "";
        try {
        	endDate = DateManager.getPrevMonth(appDate.substring(0,6), cnts)+"01";
	    }catch (Exception ex) {
	        throw new FtlSystemException("年月日リストを取得"
	                , "対象日["+appDate.substring(0,6)+"]から"+cnts+"ヶ月を引き算する際のDateManager.getPrevMonth"
	                , ex);
	    }
        return createNengappi(appDate, endDate);
    }
    /**
     * 年月日リスト作成処理
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    private static List createNengappi(String startDate, String endDate) {
        List list = new ArrayList();
        String name = formatter.format(startDate, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
        list.add(createSelectItem(startDate, name));
        while (startDate.compareTo(endDate) > 0) {
            try {
           		startDate = DateManager.getPrevDate(startDate, 1);
	            name = formatter.format(startDate, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
	            list.add(createSelectItem(startDate, name));
	        }catch (Exception ex) {
	            throw new FtlSystemException("年月日リストを取得"
	                    , "対象日["+startDate+"]から[1]を引き算する際のDateManager.getPrevDate"
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
}
