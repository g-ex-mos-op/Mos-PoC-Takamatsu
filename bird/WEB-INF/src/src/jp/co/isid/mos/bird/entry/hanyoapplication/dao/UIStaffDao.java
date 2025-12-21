package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIStaff;

/**
 * 加盟店スタッフマスタ
 * @author kusama
 */
public interface UIStaffDao {

    public static final Class BEAN = UIStaff.class;
    public static final String getStaff_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    public static final String getStaffInfo_ARGS = "staffId";
    public static final String updateStaff_ARGS = "uiStaff";
    public static final String updateStaff_PERSISTENT_PROPS = "job, lastUser, lastPgm";

    /**
     * 加盟店スタッフマスタの取得
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param companyCd 会社コード
     * @param onerCd オーナーコード
     * @return List 検索結果
     */
    public List getStaff(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);
    
    /**
     * スタッフ情報取得（キー指定）
     * @param staffId
     * @return
     */
    public UIStaff getStaffInfo(String staffId);
    
    public void updateStaff(UIStaff uiStaff);
}