/*
 * 作成日:2007/01/16
 */
package jp.co.isid.mos.bird.entry.ownerchange.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.ownerchange.entity.MstStaffInfo;
/**
 * スタッフ情報Dao
 * @author xkonishi
 *
 */
public interface MstStaffInfoDao {

    public static final Class BEAN = MstStaffInfo.class;
    
    public static final String selectByMiseCd_ARGS = "companyCd, miseCd";
    
    public static final String selectByOnerCd_ARGS = "companyCd, onerCd";

    public static final String update_PERSISTENT_PROPS = "onerCd, oldOnerCd, miseCd1, moveDt, lastUser, lastPgm";
   
    /**
     * 店舗スタッフ情報検索
     * @param 会社コード
     * @param 店コード
     * @return スタッフ情報リスト
     */
    public List selectByMiseCd(String companyCd, String miseCd);
    
    /**
     * オーナー保有店舗スタッフ情報検索
     * @param 会社コード
     * @param オーナーコード
     * @return スタッフ情報リスト
     */
    public List selectByOnerCd(String companyCd, String onerCd);   

    /**
     * 加盟店スタッフマスタ更新
     * @param スタッフ情報
     */
    public void update(MstStaffInfo mstStaffInfo);

}
