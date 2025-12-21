/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIOfficialPointHisInfo;

/**
 * 役員付与ポイント履歴Dao
 * @author yushuncheng
 *
 */
public interface UIOfficialPointHisDao {

	public static final Class BEAN = UIOfficialPointHisInfo.class;

    public static final String checkExist_ARGS = "nendo,userId,pointShuName";
    public static final String getDeleteList_ARGS = "tmsp";
    public static final String getSumPointList_ARGS = "maxNendo,minNendo";
    public static final String deletePfficialPointHisByNendo_SQL = "delete from BD60YPRI where NENDO=? and POINT_SHU=?";

    /**
     * 退役員付与ポイント履歴更新
     * @param officialPointHisList
     */
    public void updateOfficialPointHisList(List officialPointHisList);

    /**
     * 役員付与ポイント履歴登録
     * @param officialPointHisList
     */
    public void insertOfficialPointHisList(List officialPointHisList);

    /**
     * 役員付与ポイント履歴削除
     * @param officialPointHisList
     */
    public void deleteOfficialPointHisList(List officialPointHisList);

    /**
     * 役員付与ポイント履歴の取得
     * @param String 年度
     * @param String 社員番号
     * @param String 適用日
     * @param String 付与ポイント種別
     * @return UIOfficialPointHisInfo
     */
    public UIOfficialPointHisInfo checkExist(String nendo, String userId,String pointShuName);

    /**
     * 役員付与ポイント取込履歴により、役員付与ポイント履歴の削除データの取得
     * @param tmsp
     * @return List
     */
    public List<UIOfficialPointHisInfo> getDeleteList(java.sql.Timestamp tmsp);

    /**
     *
     * @param maxNendo
     * @param minNendo
     * @return
     */
    public List<UIOfficialPointHisInfo> getSumPointList(String maxNendo,String minNendo);

    public void deletePfficialPointHisByNendo(String maxNendo,String pointShu);

}
