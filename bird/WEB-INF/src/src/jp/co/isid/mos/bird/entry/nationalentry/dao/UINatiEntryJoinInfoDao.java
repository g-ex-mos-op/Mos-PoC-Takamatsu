/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryJoinInfo;

/**
 * @author xlee
 *
 */
public interface UINatiEntryJoinInfoDao {
    
    public static final Class BEAN = UINatiEntryJoinInfo.class;
    
    public static final String getNatiEntryJoinInfo_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";

    public static final String deleteJoin_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
	/**
	 * 申込参加者の情報取得
	 * @param entryCd　エントリーコード
	 * @param entryYear　エントリー年
	 * @param entryKai　エントリー回
	 * @param companyCd　会社コード
	 * @param onerCd　オーナーコード
	 * @return　申込参加者の情報 List
	 */
    public List getNatiEntryJoinInfo(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);
    
    /**
     * 申込参加者１～30情報の新規登録(insertEntry)
     * @param uiNatiEntryJoinInfo
     */
    public int insertJoin(UINatiEntryJoinInfo uiNatiEntryJoinInfo);

    /**
     *  申込参加者１～30情報の削除
     */
    public int deleteJoin(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);

}
