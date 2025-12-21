package jp.co.isid.mos.bird.entry.manageregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.manageregist.entity.UIEntrySelection;

/**
 * エントリーセレクション管理Dao
 * 
 * @author xjung
 *
 */
public interface UIEntrySelectionDao {
    /**
     * エントリーマスタ管理エンティティ
     */
    public static final Class BEAN = UIEntrySelection.class;

    /**
     * 全国店長勉強会セレクション情報リスト取得時のパラメータ
     */
    public static final String select_ARGS = "entryCd, entryYear, entryKai";

    /**
     * 全国店長勉強会セレクション情報更新時のパラメータ
     */
    public static final String update_PERSISTENT_PROPS = "selectionName, lastUser, lastPgm";

    /**
     * 全国店長勉強会セレクション情報更新【主キー変更】時のパラメータ
     */
    public static final String updatePKey_ARGS = "entity, entryYear, entryKai";

    /**
     * 全国店長勉強会セレクション情報リストを取得する。
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai  エントリー回
     * @return List 全国店長勉強会セレクション情報リスト
     */
    public List select(String entryCd, String entryYear, String entryKai);

    /**
     * 全国店長勉強会セレクション情報を登録する。
     * @param uiEntrySelection エントリーセレクション管理エンティティ
     * @return int 更新結果
     */
    public int insert(UIEntrySelection uiEntrySelection);

    /**
     * 全国店長勉強会セレクション情報を更新する。
     * @param uiEntrySelection エントリーセレクション管理エンティティ
     * @return int 更新結果
     */
    public int update(UIEntrySelection uiEntrySelection);

    /**
     * 全国店長勉強会セレクション情報を更新する。【主キー変更】
     * @param entity    エントリーセレクション管理エンティティ
     * @param entryYear エントリー年
     * @param entryKai  エントリー回
     * @return int 更新結果
     */
    public int updatePKey(UIEntrySelection entity, String entryYear, String entryKai);
}