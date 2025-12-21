/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIOfficialPointReadHisInfo;

/**
 * 役員付与ポイント取込履歴Dao
 * @author yushuncheng
 *
 */
public interface UIOfficialPointReadHisDao {

	public static final Class BEAN = UIOfficialPointReadHisInfo.class;

    public static final String getToriSakujoList_ARGS = "tmsp";
    public static final String getUserList_ARGS = "tmsp";
    public static final String getCompanyList_ARGS = "tmsp";
    public static final String getListByFlg_ARGS = "tmsp";

    /**
     * 役員付与ポイント取込履歴登録
     * @param officialPointReadHisList
     */
    public void insertOfficialPointReadHisList(List officialPointReadHisList);


    /**
     * 役員付与ポイント取込履歴と役員付与ポイント履歴リストの取得
     */
    public List<UIOfficialPointReadHisInfo> getToriSakujoList(java.sql.Timestamp tmsp);


    /**
     * 役員付与ポイント取込履歴と統合ユーザー（非履歴）リストの取得
     * @param tmsp
     * @return
     */
    public List<UIOfficialPointReadHisInfo> getUserList(java.sql.Timestamp tmsp);


    /**
     * 役員付与ポイント取込履歴と会社ビューリストの取得
     * @param tmsp
     * @return
     */
    public List<UIOfficialPointReadHisInfo> getCompanyList(java.sql.Timestamp tmsp);


    /**
     * 「削除対象フラグ」が「0」の場合、役員付与ポイント取込履歴リストの取得
     * @param tmsp
     * @return
     */
    public List<UIOfficialPointReadHisInfo> getListByFlg(java.sql.Timestamp tmsp);

}
