/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryMst;


/**
 * エントリーマスタ管理（UIEntryMstDao）
 * @author itamoto
 */
public interface UIEntryMstDao {

    public static final Class BEAN = UIEntryMst.class;
    public static final String getEntry_ARGS  = "entryCd, joinCharacterList, onerCd";
    public static final String getEntryInfo_ARGS = "entryCd, entryYear, entryKai";

    /**
     * エントリーマスタ管理の取得(getEntry)
     * @param entryCd エントリーコード
     * @param joinCharacterList 連結文字(エントリー年 + エントリー回)　←複数
     * @return List 検索結果
     */
    public List getEntry(String entryCd, List joinCharacterList, String onerCd);

    /**
     * 指定条件のエントリーマスタ取得
     */
    public UIEntryMst getEntryInfo(String entryCd, String entryYear, String entryKai);
}