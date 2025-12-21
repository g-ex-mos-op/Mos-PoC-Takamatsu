package jp.co.isid.mos.bird.bizreport.common.logic;


/**
 * 業務管理　グループ業績画面共通ロジック インターフェース
 * @author xkinu
 *
 */
public interface BizReportCommonLogic {
    /** モス会社コード */
    public static final String COMPANY_CD_MOS = "00";
    /** パラメーターキー：会社コード */
    public static final String KEY_COMPANY_CD = "companyCd";
    /** パラメーターキー：店舗種別 */
    public static final String KEY_TENPOSHUBETU = "tenpoShubetu";
    /** パラメーターキー：対象期間 */
    public static final String KEY_TAISHOKIKAN = "taishoKikan";
    /** パラメーターキー：期間指定1番目 */
    public static final String KEY_KIKANSITEI1 = "kikanSitei1";
    /** パラメーターキー：期間指定2番目 */
    public static final String KEY_KIKANSITEI2 = "kikanSitei2";
    /** パラメーターキー：前年データ種別 */
    public static final String KEY_ZENDATASHUBETU= "zennenDataShubetu";
    /** パラメーターキー：対象条件 */
    public static final String KEY_TAISHOJOKEN = "taishoJoken";
    /** パラメーターキー：表示対象 */
    public static final String KEY_HYOJITAISHO = "hyojiTaisho";
    /** パラメーターキー：ブロックコード */
    public static final String KEY_BLOCK_CD = "blockCd";

}
