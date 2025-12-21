/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIStaffPointHisInfo;

/**
 * 社員付与ポイント履歴Dao
 * @author yushuncheng
 *
 */
public interface UIStaffPointHisDao {

	public static final Class BEAN = UIStaffPointHisInfo.class;

    public static final String checkExist_ARGS = "nendo,userId,pointShu";
    public static final String getKaigaiPointInfo_ARGS = "nendo,userId";
    public static final String getDeleteList_ARGS = "tmsp,pointShu";
    public static final String getDeleteListByBD64_ARGS = "tmsp,pointShu";
    public static final String getSumPointList_ARGS = "maxNendo,minNendo";
    public static final String deletePointHisByNendo_ARGS = "nendo,pointShu";
    public static final String deleteStaffPointHisInfoList_SQL = "delete from BD59HPRI where USER_ID = ? AND POINT_SHU=?";

    /**
     * 社員付与ポイント履歴リスト更新
     * @param staffPointHisList
     */
    public void updateStaffPointHisList(List staffPointHisList);

    /**
     * 社員付与ポイント履歴リスト登録
     * @param staffPointReadHisList
     */
    public void insertStaffPointHisList(List staffPointHisList);

    /**
     * 社員付与ポイント履歴更新
     * @param staffPointHisInfo
     */
    public void updateStaffPointHis(UIStaffPointHisInfo staffPointHisInfo);

    /**
     * 社員付与ポイント履歴登録
     * @param staffPointHisInfo
     */
    public void insertStaffPointHis(UIStaffPointHisInfo staffPointHisInfo);

    /**
     * 社員付与ポイント履歴リスト削除
     * @param staffPointReadHisList
     */
    public void deleteStaffPointHisList(List staffPointHisList);

    /**
     * 社員付与ポイント取込履歴の取得
     * @param String 年度
     * @param String 社員番号
     * @param String 適用日
     * @param String 付与ポイント種別
     * @return UIStaffPointHisInfo
     */
    public UIStaffPointHisInfo checkExist(String nendo, String userId, String pointShu);


    /**
     * 「削除対象フラグ」が「0」の場合、社員付与ポイン取込履歴と付与ポイントリストの取得
     * @param tmsp
     * @return
     */
    public UIStaffPointHisInfo getKaigaiPointInfo(String nendo, String userId);

    /**
     * 社員付与ポイント取込履歴により、社員付与ポイント履歴の削除データの取得
     * @param tmsp
     * @param pointShu
     * @return
     */
    public List<UIStaffPointHisInfo> getDeleteList(java.sql.Timestamp tmsp, String pointShu);

    /**
     *
     * @param tmsp
     * @param pointShu
     * @return
     */
    public List<UIStaffPointHisInfo> getDeleteListByBD64(java.sql.Timestamp tmsp, String pointShu);

    /**
     * 最大年度と最小年度により、社員番号で合計ポイントの取得
     * @param maxNendo 最大年度
     * @param minNendo 最小年度
     * @return
     */
    public List<UIStaffPointHisInfo> getSumPointList(String maxNendo, String minNendo);

    /**
     * 最大年度により、社員付与ポイント履歴の削除
     * @param nendo 年度
     * @param pointShu 付与ポイント種別
     */
    public void deletePointHisByNendo(String nendo, String pointShu);


    public void deleteStaffPointHisInfoList(String userId,String pointShu);

}
