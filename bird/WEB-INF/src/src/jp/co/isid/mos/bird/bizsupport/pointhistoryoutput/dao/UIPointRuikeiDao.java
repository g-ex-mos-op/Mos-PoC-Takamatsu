/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.UIPointRuikei;

/**
 * 社員役員ポイント累計
 * @author Yuichi Tamura(ISID-AO)
 */
public interface UIPointRuikeiDao {

    public static final Class BEAN = UIPointRuikei.class;
	public static final String selectPointRuikei_ARGS = "kbCompanyCd, userID, nendoFrom, nendoTo, taishokuFlg, sysDate";
	public static final String selectCountPointRuikei_ARGS = "kbCompanyCd, userID, nendoFrom, nendoTo, taishokuFlg, sysDate";

    /**
     * 社員役員ポイント累計の検索
     * @param String kbCompanyCd 会社コード
     * @param String userID      社員番号
     * @param String nendoFrom   年度（From）
     * @param String nendoTo     年度（To）
     * @param String taishokuFlg 退職フラグ
     * @param String sysDate     システム日付
     * @return List
     */
	public List selectPointRuikei(String kbCompanyCd, String userID, String nendoFrom, String nendoTo,
			String taishokuFlg, String sysDate);

    /**
     * 社員役員ポイント累計の件数取得
     * @param String kbCompanyCd 会社コード
     * @param String userID      社員番号
     * @param String nendoFrom   年度（From）
     * @param String nendoTo     年度（To）
     * @param String taishokuFlg 退職フラグ
     * @param String sysDate     システム日付
     * @return int 件数
     */
	public int selectCountPointRuikei(String kbCompanyCd, String userID, String nendoFrom, String nendoTo,
			String taishokuFlg, String sysDate);

}