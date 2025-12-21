/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.UIYakuinPointRireki;

/**
 * 役員付与ポイント履歴
 * @author Yuichi Tamura(ISID-AO)
 */
public interface UIYakuinPointRirekiDao {

    public static final Class BEAN = UIYakuinPointRireki.class;
    public static final String selectYakuinHuyoPoint_ARGS = "kbCompanyCd, userID, nendoFrom, nendoTo, taishokuFlg, sysDate";
    public static final String selectCountYakuinHuyoPoint_ARGS = "kbCompanyCd, userID, nendoFrom, nendoTo, taishokuFlg, sysDate";

    /**
     * 役員付与ポイントの検索
     * @param String kbCompanyCd 会社コード
     * @param String userID      社員番号
     * @param String nendoFrom   年度（From）
     * @param String nendoTo     年度（To）
     * @param String taishokuFlg 退職フラグ
     * @param String sysDate     システム日付
     * @return List
     */
	public List selectYakuinHuyoPoint(String kbCompanyCd, String userID, String nendoFrom, String nendoTo,
			String taishokuFlg, String sysDate);

    /**
     * 役員付与ポイントの件数取得
     * @param String kbCompanyCd 会社コード
     * @param String userID      社員番号
     * @param String nendoFrom   年度（From）
     * @param String nendoTo     年度（To）
     * @param String taishokuFlg 退職フラグ
     * @param String sysDate     システム日付
     * @return int 件数
     */
	public int selectCountYakuinHuyoPoint(String kbCompanyCd, String userID, String nendoFrom, String nendoTo,
			String taishokuFlg, String sysDate);

}