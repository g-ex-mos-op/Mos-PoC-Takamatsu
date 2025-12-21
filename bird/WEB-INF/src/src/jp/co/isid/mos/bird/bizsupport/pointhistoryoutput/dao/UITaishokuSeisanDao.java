/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.UITaishokuSeisan;

/**
 * 退職時精算
 * @author Yuichi Tamura(ISID-AO)
 */
public interface UITaishokuSeisanDao {

    public static final Class BEAN = UITaishokuSeisan.class;
    public static final String selectTaishokuSeisanPoint_ARGS = "kbCompanyCd, userID, nendoFrom, nendoTo";
    public static final String selectCountTaishokuSeisanPoint_ARGS = "kbCompanyCd, userID, nendoFrom, nendoTo";


    /**
     * 退職時精算ポイントの検索
     * @param String kbCompanyCd 会社コード
     * @param String userID      社員番号
     * @param String nendoFrom   年度（From）
     * @param String nendoTo     年度（To）
     * @return List
     */
    public List selectTaishokuSeisanPoint(String kbCompanyCd, String userID, String nendoFrom, String nendoTo);


    /**
     * 退職時精算ポイントの件数取得
     * @param String kbCompanyCd 会社コード
     * @param String userID      社員番号
     * @param String nendoFrom   年度（From）
     * @param String nendoTo     年度（To）
     * @return int 件数
     */
    public int selectCountTaishokuSeisanPoint(String kbCompanyCd, String userID, String nendoFrom, String nendoTo);

}