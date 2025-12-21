/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIStaffPointReadHisInfo;

/**
 * 社員付与ポイント取込履歴Dao
 * @author yushuncheng
 *
 */
public interface UIStaffPointReadHisDao {

	public static final Class BEAN = UIStaffPointReadHisInfo.class;

    public static final String getToriSakujoList_ARGS = "tmsp,pointShu";
    public static final String getPointList_ARGS = "tmsp";
    public static final String getUserList_ARGS = "tmsp";
    public static final String getPointListByFlg_ARGS = "tmsp";
	public static final String updateStaffPointInfo_PERSISTENT_PROPS = "tmsp, nendo, userId, pointShu,point,kbCompanyCd,rankCd";

    /**
     * 社員付与ポイント取込履歴登録
     * @param staffPointReadHisList
     */
    public void insertStaffPointReadHisList(List staffPointReadHisList);


    /**
     * 社員付与ポイン取込履歴と社員付与ポイン履歴リストの取得
     * @param tmsp
     * @param pointShu
     * @return
     */
    public List<UIStaffPointReadHisInfo> getToriSakujoList(java.sql.Timestamp tmsp, String pointShu);


    /**
     * 社員付与ポイン取込履歴と付与ポイントリストの取得
     * @param tmsp
     * @return
     */
    public List<UIStaffPointReadHisInfo> getPointList(Timestamp tmsp);


    /**
     * 社員付与ポイン取込履歴と統合ユーザー（非履歴）リストの取得
     * @param tmsp
     * @return
     */
    public List<UIStaffPointReadHisInfo> getUserList(Timestamp tmsp);


    /**
     * 「削除対象フラグ」が「0」の場合、社員付与ポイン取込履歴と付与ポイントリストの取得
     * @param tmsp
     * @return
     */
    public List<UIStaffPointReadHisInfo> getPointListByFlg(Timestamp tmsp);

    /**
     * 社員付与ポイント取込履歴編集
     * @param staffPointReadHisList
     */
	public void updateStaffPoint(List<UIStaffPointReadHisInfo> staffPointReadHisList);

	public void updateStaffPointInfo(UIStaffPointReadHisInfo staffPointReadHis);

}
