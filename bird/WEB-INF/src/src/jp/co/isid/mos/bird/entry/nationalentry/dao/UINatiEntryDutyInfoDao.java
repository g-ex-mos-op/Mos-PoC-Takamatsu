/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.dao;

import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryDutyInfo;

/**
 * 全国大会申込　責任者情報　取得・登録・更新
 * @author xlee
 */
public interface UINatiEntryDutyInfoDao {
    
    public static final Class BEAN = UINatiEntryDutyInfo.class;
    
    public static final String getNatiEntryDutyInfo_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
    public static final String insertDuty_ARGS = "UINatiEntryDutyInfo";
    
    public static final String updateDuty_ARGS = "UINatiEntryDutyInfo";

    /**
	 * 申込責任者情報取得
	 * @param entryCd　エントリーコード
	 * @param entryYear　エントリー年
	 * @param entryKai　エントリー回
	 * @param companyCd　会社コード
	 * @param onerCd　オーナーコード
	 * @return　申込責任者情報
	 */
    public UINatiEntryDutyInfo getNatiEntryDutyInfo(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);
    
    /**
     * 申込責任者情報の新規登録(insertEntry)
     * @param uiNatiEntryDutyInfo
     */
    public int insertDuty(UINatiEntryDutyInfo uiNatiEntryDutyInfo);

    /**
     * 申込責任者情報の更新(updateEntry)
     * @param uiNatiEntryDutyInfo
     */
    public int updateDuty(UINatiEntryDutyInfo uiNatiEntryDutyInfo);
}
