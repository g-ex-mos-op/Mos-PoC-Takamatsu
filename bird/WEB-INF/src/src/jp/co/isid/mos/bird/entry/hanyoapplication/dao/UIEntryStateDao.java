package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryState;

/**
 * 研修エントリー状況を取得する
 * @author kusama
 * 修正： 2007/05/23 DELETE処理のパラメータを変更
 * 　　　　　　　　　　--> 削除の時には、オーナーコード単位で全スタッフを削除 
 */
public interface UIEntryStateDao {

    public static final Class BEAN = UIEntryState.class;
    public static final String getEntry_ARGS  = "entryCd, entryYear, entryKai, companyCd, onerCd";
    public static final String insertEntry_ARGS = "uiEntryState";
    public static final String updateEntry_ARGS = "uiEntryState";
    public static final String deleteEntry_ARGS = "entryCd, entryYear, entryKai, onerCd";

    /**
     * 研修エントリー状況の新規登録
     * @param UIEntryState
     */
    public int insertEntry(UIEntryState uiEntryState);

    /**
     * 研修エントリー状況の更新
     * @param UIEntryState
     */
    public int updateEntry(UIEntryState uiEntryState);
    
    /**
     * 研修エントリー状況の削除
     * @param UIEntryState
     */
    public void deleteEntry(String entryCd, String entryYear, String entryKai, String onerCd);

    /**
     * 研修エントリー状況の取得(getEntry)
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param companyCd 会社コード
     * @param staffId オーナーコード
     * @return List 検索結果
     */
    public List getEntry(String entryCd, String entryYear, String entryKai, String companyCd, String onerCd);

}