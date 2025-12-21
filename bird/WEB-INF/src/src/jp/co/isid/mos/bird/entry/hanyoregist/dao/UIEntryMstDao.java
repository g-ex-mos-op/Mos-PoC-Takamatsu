/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.hanyoregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryMst;


/**
 * エントリーマスタ管理（UIEntryMstDao）
 * @author itamoto
 */
public interface UIEntryMstDao {

    public static final Class BEAN = UIEntryMst.class;
    public static final String getEntry_ARGS  = "entryCd, joinCharacterList";
    public static final String getMaxEntryKai_ARGS  = "entryCd, entryYear";
    public static final String insertEntry_ARGS = "uiEntryMst";
    public static final String updateEntry_ARGS = "uiEntryMst";
    public static final String deleteEntry_ARGS = "uiEntryMst";
    public static final String updateEntry_PERSISTENT_PROPS 
			= "entryTitle, entryPlace, numberLimit, placeLimit,"
			+ "note, spareFlg1, spareFlg2, sakujoFlg, lastUser, lastPgm";

    /**
     * エントリーマスタ管理の新規登録(insertEntry)
     * @param uiEntryMst
     */
    public int insertEntry(UIEntryMst uiEntryMst);

    /**
     * エントリーマスタ管理の更新(updateEntry)
     * @param uiEntryMst
     */
    public int updateEntry(UIEntryMst uiEntryMst);

    /**
     * エントリーマスタ管理の削除（論理削除）
     */
    public int deleteEntry(UIEntryMst uiEntryMst);
    
    /**
     * エントリーマスタ管理の取得(getEntry)
     * @param entryCd エントリーコード
     * @param joinCharacterList 連結文字(エントリー年 + エントリー回)　←複数
     * @return List 検索結果
     */
    public List getEntry(String entryCd, List joinCharacterList);

    /**
     * エントリー回のMAX値取得(getMaxEntryKai)
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリ回
     * @return int 検索結果
     */
    public int getMaxEntryKai(String entryCd, String entryYear);
}
