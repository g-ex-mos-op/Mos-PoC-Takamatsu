package jp.co.isid.mos.bird.analysis.rankref.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 売上ランク共通定数処理クラス
 * 
 * @author xkinu
 *
 */
public class RankRefUtil {
	/** 画面ID */
	public static final String SCREEN_ID = "BDT007";
    /** VIEWID：照会画面 */
    public static final String VIEW_ID= SCREEN_ID+"V01";
    /** ACTIONID：照会画面 */
    public static final String ACTION_ID= SCREEN_ID+"A";
    
    /** LOGICID：指定会社条件項目設定ロジックID */
    public static final String LOGIC_ID_SET_COMPANY= SCREEN_ID+"L01";
    /** LOGICID：条件項目設定ロジックID */
    public static final String LOGIC_ID_CONDITION= SCREEN_ID+"L02";
    /** LOGICID：検索ロジックID */
    public static final String LOGIC_ID_SEARCH= SCREEN_ID+"L03";
    /** LOGICID：CSVダウンロードロジックID */
    public static final String LOGIC_ID_CSV= SCREEN_ID+"L04";
    
    
   /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();
    
	private RankRefUtil() {
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
     * 年月リストを取得する
     * 
     * @param kijunDate 基準日付
     * @param cnts   当月含めて過去？ヶ月
     * @return
     */
    public static List creatListNengetu(String kijunDate, int cnts) {
        List list = new ArrayList();
        String startNengetu = kijunDate.substring(0,6);
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
     * 年度リストを取得する
     * 
     * @param kijunDate 基準日付
     * @param cnts   当年度めて過去？日間
     * @return
     */
    public static List creatListNendo(String kijunDate, int cnts) {
        List list = new ArrayList();
        String kijunNendo = DateManager.getCurrentYear(kijunDate);
        for (int index=0; index<cnts; index++) {
            try {
           		String year = DateManager.getPrevYear(kijunNendo, index);
	            list.add(index, createSelectItem(year, year));
	        }catch (Exception ex) {
	            throw new FtlSystemException("年月日リストを取得"
	                    , "基準["+kijunNendo+"]から["+index+"]を計算する際のDateManager.getPrevYearメソッド処理で例外が発生しました。"
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
	 * yyyyMMをyyyy月MM月変換処理します。
	 * @param ym
	 * @return
	 */
	public static String formattYMKj(String ym) {
		return formatter.format(ym, DateFormatter.PATTERN_MONTH_KANJI_YM
				, DateFormatter.DATE_TYPE_YM);
	}
}
