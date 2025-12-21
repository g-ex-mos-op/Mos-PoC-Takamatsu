/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.code.RowType;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.TenkoKbn;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * @author xkinu
 *
 */
public class CommonUtil {
    /** 会社コード：モス */
    public static final String COMPANY_CD_MOS = "00";
    /** VIEWID：店舗選択 */
    public static final String VIEW_ID_MISESEARCH   = "BCO008V01";
    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();
    /** タイプ：日報系 */
    public static final int TYPE_NIPO = 0;
    /** タイプ：推移系 */
    public static final int TYPE_SUII = 1;
    /** 一覧項目名称：事業本部ｺｰﾄﾞ */
    public static final String CNM_JIGYOU_CD = "事業本部ｺｰﾄﾞ";		
    /** 一覧項目名称：事業本部名称 */
    public static final String CNM_JIGYOU_NAME = "事業本部名称";		
    /** 一覧項目名称：ｴﾘｱｺｰﾄﾞ */
    public static final String CNM_SLAREA_CD = "ｴﾘｱｺｰﾄﾞ";
    /** 一覧項目名称：ｴﾘｱ名称 */
    public static final String CNM_SLAREA_NAME = "ｴﾘｱ名称";
    /** 一覧項目名称：支部ｺｰﾄﾞ */
    public static final String CNM_SIBU_CD = "支部ｺｰﾄﾞ";
    /** 一覧項目名称：支部名称 */
    public static final String CNM_SIBU_NAME = "支部名称";
    /** 一覧項目名称：ﾌﾞﾛｯｸｺｰﾄﾞ */
    public static final String CNM_BLOCK_CD = "ﾌﾞﾛｯｸｺｰﾄﾞ";
    /** 一覧項目名称：ﾌﾞﾛｯｸ名称 */
    public static final String CNM_BLOCK_NAME = "ﾌﾞﾛｯｸ名称";
    /** 一覧項目名称：店ｺｰﾄﾞ */
    public static final String CNM_MISE_CD = "店ｺｰﾄﾞ";
    /** 一覧項目名称：店名称 */
    public static final String CNM_MISE_NAME = "店名称";
    /** 一覧項目名称：店種 */
    public static final String CNM_TENSHU = "店種";
    /** 一覧項目名称：メニュー名称 */
    public static final String CNM_MENU_NAME = "メニュー名称";
    /** 一覧項目名称：休業 */
    public static final String CNM_CLOSE_MJ = "クローズ";
    /** 一覧項目名称：天候 */
    public static final String CNM_TENKOU = "天候";
    /** 一覧項目名称：販売個数 */
    public static final String CNM_KAZUKEI = "販売個数";
    /** 一覧項目名称：販売金額 */
    public static final String CNM_MENU_URIAGE = "販売金額";
    /** 一覧項目名称：金額構成比 */
    public static final String CNM_KINGAKU_KOSEIHI = "金額構成比";
    /** 一覧項目名称：前年天候 */
    public static final String CNM_TENKOU_ZEN = "前年天候";
    /** 一覧項目名称：前年休業 */
    public static final String CNM_CLOSE_MJ_ZEN = "前年クローズ";
    /** 一覧項目名称：営業日数 */
    public static final String CNM_EIGYO_DAYS = "営業日数";
    /** 一覧項目名称：前年営業日数 */
    public static final String CNM_EIGYO_DAYS_ZEN = "前年営業日数";
    /** 一覧項目名称：店舗数 */
    public static final String CNM_TENPO_COUNT = "店舗数";
    /** 一覧項目名称：当年店数 */
    public static final String CNM_TOUNEN_TENPO_COUNT = "当年店数";
    /** 一覧項目名称：前年店数 */
    public static final String CNM_ZENNEN_TENPO_COUNT = "前年店数";
    /** 一覧項目名称：前年比対象店数 */
    public static final String CNM_HOSEI_TENPO_COUNT = "前年比対象店数";
    /** 一覧項目名称：前年比対象前年店数 */
    public static final String CNM_HOSEI_ZEN_TENPO_COUNT = "前年比対象前年店数";
    /** 一覧項目名称：売上 */
    public static final String CNM_URIAGE = "売上";
    /** 一覧項目名称：予算 */
    public static final String CNM_YOSAN = "予算";
    /** 一覧項目名称：達成率 */
    public static final String CNM_TASSEI = "達成率";
    /** 一覧項目名称：前年実績 */
    public static final String CNM_URIAGE_ZEN = "前年売上";
    /** 一覧項目名称：前年比対象売上 */
    public static final String CNM_HOSEI_URIAGE = "前年比対象売上";
    /** 一覧項目名称：前年比対象前年売上 */
    public static final String CNM_HOSEI_URIAGE_ZEN = "前年比対象前年売上";
    /** 一覧項目名称：前年比(売上) */
    public static final String CNM_URIAGE_HI = "前年比(売上)";
    /** 一覧項目名称：前年差(売上) */
    public static final String CNM_DIFF_URIAGE_ZEN = "前年差(売上)";
    /** 一覧項目名称：客数 */
    public static final String CNM_KYAKUSU = "客数";
    /** 一覧項目名称：前年客数 */
    public static final String CNM_KYAKUSU_ZEN = "前年客数";
    /** 一覧項目名称：前年比対象客数 */
    public static final String CNM_HOSEI_KYAKUSU = "前年比対象客数";
    /** 一覧項目名称：前年比対象前年客数 */
    public static final String CNM_HOSEI_KYAKUSU_ZEN = "前年比対象前年客数";
    /** 一覧項目名称：前年比(客数) */
    public static final String CNM_KYAKUSU_HI = "前年比(客数)";
    /** 一覧項目名称：前年差(客数) */
    public static final String CNM_ZEN_DIFF_KYAKUSU = "前年差(客数)";
    /** 一覧項目名称：客単価 */
    public static final String CNM_KYAKU_TANKA = "客単価";
    /** 一覧項目名称：前年客単価 */
    public static final String CNM_KYAKU_TANKA_ZEN = "前年客単価";
    /** 一覧項目名称：前年比対象客単価 */
    public static final String CNM_HOSEI_KYAKU_TANKA = "前年比対象客単価";
    /** 一覧項目名称：前年比対象前年客単価 */
    public static final String CNM_HOSEI_KYAKU_TANKA_ZEN = "前年比対象前年客単価";
    /** 一覧項目名称：前年比(客単価) */
    public static final String CNM_KYAKU_TANKA_HI = "前年比(客単価)";
    /** 一覧項目名称：支部順位 */
    public static final String CNM_RANK_SIBU = "支部順位";
    /** 一覧項目名称：全国順位 */
    public static final String CNM_RANK_ALL = "全国順位";
    
    /** ソートタイプ：昇順 */
    public static final int SORT_ASC = 1;
    /** ソートタイプ：降順 */
    public static final int SORT_DESC = 0;
	/**
	 * 
	 */
	private CommonUtil() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	/**
	 * yyyyMMddをMM月dd日(E)変換処理します。
	 * @param ymd
	 * @return
	 */
	public static String formattMDKj(String ymd) {
		return formatter.format(ymd, DateFormatter.PATTERN_DAY_KANJI
				, DateFormatter.DATE_TYPE_YMD);
	}
	/**
	 * yyyyMMddをyyyy/MM/dd変換処理します。
	 * @param ymd
	 * @return
	 */
	public static String formattYMDSlash(String ymd) {
		return formatter.format(ymd, DateFormatter.PATTERN_SLASH
				, DateFormatter.DATE_TYPE_YMD);
	}
    /**
     * 年度リスト作成取得
     * 
     * アプリ年度含めて過去「リスト個数」年度分を生成します。
     * 
     * @param String アプリ日付
     * @param int リスト個数
     * @return List 期間指定データ
     */
    public static List creatListNendo(String appDate, int cnt) {
        List list = new ArrayList();
        String app = DateManager.getCurrentYear(appDate);
        for (int index=0; index<cnt; index++) {
            String code = "";
            try {
                code = DateManager.getPrevYear(app, index);
            }catch (Exception ex) {
                throw new FtlSystemException("期間指定生成"
                        , "アプリ日付年度["+app+"]から["+index+"]を引く際のDateManager.getPrevYearメソッド処理で例外が発生しました。"
                        , ex);
            }
            String name = code+"年度";
            list.add(index, createKikanEntity(code, name));
        }
        return list;
    }
    /**
     * 日付リストを取得する
     * 
     * @param fromDt 指定開始日
     * @param toDt   指定終了日
     * @return
     */
    public static List creatListDay(String appDt, String startDt, String endDt) {
        List list = new ArrayList();
        String day = startDt;
        if(appDt.compareTo(startDt) < 0) {
        	startDt = appDt;
        }
        for (int index=0; day.compareTo(endDt)>=0; index++) {
            try {
           		day = DateManager.getPrevDate(startDt, index);
	            String name = formatter.format(day, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
	            list.add(index, createKikanEntity(day, name));
	            if(day.compareTo(endDt)==0) {
	            	break;
	            }
	        }catch (Exception ex) {
	            throw new FtlSystemException("期間指定生成"
	                    , "指定開始日["+startDt+"]から["+index+"]を計算する際のDateManagerメソッド処理で例外が発生しました。"
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
    private static CodKikanSitei createKikanEntity(String code, String name) {
        CodKikanSitei entity = new CodKikanSitei();
        entity.setCode(code);
        entity.setName(name);
        return entity;
    }
    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
	/**
	 * CSV用データ部ヘッダー項目名称作成取得処理
	 * 
	 * @param clmName
	 * @param taishoKikanName
	 * @return
	 */
	public static String createCsvClmName(String clmName, String taishoKikanName) {
		return clmName += createCsvTabName(taishoKikanName);
	}
	/**
	 * CSV用データ部タブ名称作成取得処理
	 * 
	 * @param clmName
	 * @param taishoKikanName
	 * @return
	 */
	public static String createCsvTabName(String taishoKikanName) {
		return "【"+taishoKikanName+"】" ;
	}
	/**
	 * おんなじ？null同士も判断します。
	 * @param moji1
	 * @param moji2
	 * @return
	 */
	public static boolean isNotEquals(String moji1, String moji2) {
		if(moji1 == null) {
			if(moji2== null) {
				return false;
			}
		}
		else if(moji1.equals(moji2)) {
			return false;
		}
		return true;
	}
	/**
	 * null値の場合ブランクを返す。
	 * @param value
	 * @return
	 */
	public static String changeBlank(String value) {
		if(value == null) {
			return "";
		}
		return value;
	}
	/**
	 * 行Cssクラス文字列名称取得処理
	 * 
	 * @param rowType
	 * @return
	 */
	public static String getTrCssClass(String rowType) {
    	if(RowType.CD_TOTAL.equals(rowType)) {
    		return "body_sum5";
    	}
    	else if(RowType.CD_SEGMENT.equals(rowType)) {
    		return "body_sum4";
    	}
    	else if(RowType.CD_HONBU.equals(rowType)) {
    		return "body_sum3";
    	}
    	else if(RowType.CD_JIGYOU.equals(rowType)) {
    		return "body_sum2";
    	}
    	else if(RowType.CD_SLAREA.equals(rowType)) {
    		return "body_sum1";
		}
		else if(RowType.CD_SIBU.equals(rowType)) {
	    		return "body_sum5";
		}
		else if(RowType.CD_BLOCK.equals(rowType)) {
			return "body_sum1";
		}
		else if(RowType.CD_MENU.equals(rowType)) {
    		return "body_sum3";
    	}
   		return "default_bkgColor";
		
	}
	/**
	 * 店舗種別略漢字取得処理
	 * 
	 * @param tenpoShubetu
	 * @return
	 */
	public static String getTenpoShubetuKj(String tenpoShubetu) {
		if(TenpoShubetu.CODE_ZENNEN.equals(tenpoShubetu)){
			return "前";
		}
		else if (TenpoShubetu.CODE_YOSAN.equals(tenpoShubetu)){
			return "予";
		}
		else if (TenpoShubetu.CODE_SIN.equals(tenpoShubetu)){
			return "新";
		}
		return "";
		
	}
	/**
	 * 天候区分漢字名称取得処理
	 * 
	 * @param tenkoKbn
	 * @return
	 */
	public static String getTenkoKj(String tenkoKbn) {
		if(TenkoKbn.CODE_HARE.equals(tenkoKbn)) {
			return "晴";
		}
		else if(TenkoKbn.CODE_KUMORI.equals(tenkoKbn)) {
			return "曇";
		}
		else if(TenkoKbn.CODE_AME.equals(tenkoKbn)) {
			return "雨";
		}
		else if(TenkoKbn.CODE_YUKI.equals(tenkoKbn)) {
			return "雪";
		}
		else if(TenkoKbn.CODE_ARASHI.equals(tenkoKbn)) {
			return "嵐";
		}
		return "";

	}
	/**
	 * クローズ文字列取得処理
	 * 
	 * @param openKbn
	 * @return
	 */
	public static String getCloseKj(BigDecimal openKbn) {
		if(openKbn != null) {
			return (openKbn.intValue() == 1 ? "": "クローズ");
		}
		return "";
		
	}
}
