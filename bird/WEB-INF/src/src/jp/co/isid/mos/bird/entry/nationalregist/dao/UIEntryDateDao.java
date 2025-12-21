package jp.co.isid.mos.bird.entry.nationalregist.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.entry.nationalregist.entity.UIEntryDate;

/**
 * エントリー日付管理Dao
 * 
 * @author xjung
 */
public interface UIEntryDateDao {
    /**
     * エントリー日付管理エンティティ
     */
    public static final Class BEAN = UIEntryDate.class;

    /**
     * 全国大会日付情報リスト取得時のパラメータ
     */
    public static final String select_ARGS = "entryCd, entryYear, entryKai";

    /**
     * 全国大会日付情報更新時のパラメータ
     */
    public static final String update_PERSISTENT_PROPS =
        "fromDt, ToDt, lastUser, lastPgm";

    /**
     * 全国大会日付情報更新【主キー変更】時のパラメータ
     */
    public static final String updatePKey_ARGS =
        "entity, entryYear, entryKai, lastTmsp";

    /**
     * 全国大会日付情報リストを取得する。
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai  エントリー回
     * @return List 全国大会日付情報リスト
     */
    public List select(String entryCd, String entryYear, String entryKai);

    /**
     * 全国大会日付情報を登録する。
     * @param uiEntryDate エントリー日付管理エンティティ
     * @return int 更新結果
     */
    public int insert(UIEntryDate uiEntryDate);

    /**
     * 全国大会日付情報を更新する。
     * @param uiEntryDate エントリー日付管理エンティティ
     * @return int 更新結果
     */
    public int update(UIEntryDate uiEntryDate);

    /**
     * 全国大会日付情報を更新する。【主キー変更】
     * @param entity    エントリー日付管理エンティティ
     * @param entryYear エントリー年
     * @param entryKai  エントリー回
     * @param lastTmsp  修正時タイプスタンプ
     * @return int 更新結果
     */
    public int updatePKey(
        UIEntryDate entity,
        String entryYear,
        String entryKai,
        Timestamp lastTmsp);
}