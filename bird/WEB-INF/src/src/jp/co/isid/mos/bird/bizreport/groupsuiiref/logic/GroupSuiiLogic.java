/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.groupsuiiref.logic;

import jp.co.isid.mos.bird.bizreport.common.logic.BizReportCommonLogic;

/**
 * 予算データ存在チェック
 * ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface GroupSuiiLogic extends BizReportCommonLogic {
    /** 条件の種類：条件パラメーター */
    public static final String V_JOKEN = "JOKEN";
    /** 条件の種類：一覧条件パラメーター */
    public static final String V_ICHIRAN = "ICHIRAN";
	/** パラメーターKEY:システム日付 */
	public static final String PK_SYS_DATE = "sysDate";
	/** パラメーターKEY:アプリ日付 */
	public static final String PK_APP_DATE = "appDate";
	/** パラメーターKEY:条件の種類(条件パラメーターor一覧条件パラメーター) */
	public static final String PK_JOKEN_KIND = "paramType";
	/** パラメーターKEY:推移タイプ */
	public static final String PK_SUII_TYPE = "suiiType";
	/** パラメーターKEY:ユーザーＩＤ */
	public static final String PK_USER_ID = "userId";
	/** パラメーターKEY:ユーザータイプコード */
	public static final String PK_USER_TYPE_CD = "userTypeCd";
	/** パラメーターKEY:ユーザーサイト区分 */
	public static final String PK_USER_SITE_KBN = "userSiteKbn";
	/** パラメーターKEY:支部制限フラグ */
	public static final String PK_LIMIT_FLG = "limitFlg";
    /** パラメーター名：会社コード */
    public static final String PK_COMPANY_CD = KEY_COMPANY_CD;
    /** パラメーターキー：店舗種別 */
    public static final String PK_TENPOSHUBETU = KEY_TENPOSHUBETU;
    /** パラメーターキー：対象期間 */
    public static final String PK_TAISHOKIKAN = KEY_TAISHOKIKAN;
    /** パラメーターキー：期間指定1番目 */
    public static final String PK_KIKANSITEI1 = KEY_KIKANSITEI1;
    /** パラメーターキー：期間指定2番目 */
    public static final String PK_KIKANSITEI2 = KEY_KIKANSITEI2;
    /** パラメーターキー：前年データ種別 */
    public static final String PK_ZENDATASHUBETU= KEY_ZENDATASHUBETU;
    /** パラメーターキー：対象条件 */
    public static final String PK_TAISHOJOKEN = KEY_TAISHOJOKEN;
    /** パラメーターキー：表示対象 */
    public static final String PK_HYOJITAISHO = KEY_HYOJITAISHO;
    /** パラメーターキー：ブロックコード */
    public static final String PK_BLOCK_CD = KEY_BLOCK_CD;
}
