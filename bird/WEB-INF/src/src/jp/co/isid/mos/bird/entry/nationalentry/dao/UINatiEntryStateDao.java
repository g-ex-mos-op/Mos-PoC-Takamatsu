/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.dao;

import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryState;

/**
 * オーナー別エントリー状況、登録・削除処理
 * 
 * @author xlee
 *
 */
public interface UINatiEntryStateDao {
    
    public static final Class BEAN = UINatiEntryState.class;
    
    public static final String getNatiEntryState_ARGS = "entryCd, entryYear, entryKai, companyCd, onerCd";
    
    public static final String updateEntryState_PERSISTENT_PROPS 
	= " entryCd, entryYear, entryKai, companyCd, onerCd, entryFlg , lastUser, lastPgm ";
    
    /**
     * オーナー別エントリ状況情報取得
     * @param entryCd　エントリーコード
     * @param entryYear　エントリー年
     * @param entryKai　エントリー回
     * @param companyCd　エントリー会社コード
     * @param onerCd　エントリーオーナーコード
     * @return　オーナー別エントリ状況情報
     */
    public UINatiEntryState getNatiEntryState(String entryCd, String entryYear, String entryKai, 
    		String companyCd, String onerCd);
    
    /**
     * オーナー別エントリ状況の新規登録(insertEntry)
     * @param uiNatiEntryState
     */
    public int insertEntryState(UINatiEntryState uiNatiEntryState);
    
    /**
     * オーナー別エントリ状況の更新(updateEntry)
     * @param uiNatiEntryState
     */
    public int updateEntryState(UINatiEntryState uiNatiEntryState);
}
