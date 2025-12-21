package jp.co.isid.mos.bird.entry.nationalregist.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.entry.nationalregist.entity.UIEntryMst;

/**
 * エントリーマスタ管理Dao
 * 
 * @author xjung
 */
public interface UIEntryMstDao {
    /**
     * エントリーマスタ管理エンティティ
     */
    public static final Class BEAN = UIEntryMst.class;

    /**
     * 全国大会マスタ情報リスト取得時のパラメータ
     */
    public static final String selectNational_ARGS = "entryCd, entryYear";

    /**
     * エントリー回の最大値取得時のパラメータ
     */
    public static final String selectMaxEntryKai_ARGS = "entryCd, entryYear";

    /**
     * エントリー回の最大値取得時のクエリ
     */
    public static final String selectMaxEntryKai_QUERY =
        "SELECT MAX(entry_kai) AS MAX_ENTRY_KAI FROM br17entl"
        + " WHERE entry_cd=/*entryCd*/'15' AND entry_year=/*entryYear*/'2006'";

    /**
     * 全国大会日付情報登録時のパラメータ
     */
    public static final String insert_PERSISTENT_PROPS =
        "entryTitle, sakujoFlg, firstUser, firstPgm, firstTmsp, lastUser, lastPgm";

    /**
     * 全国大会マスタ情報更新時のパラメータ
     */
    public static final String update_PERSISTENT_PROPS =
        "entryTitle, sakujoFlg, lastUser, lastPgm";

    /**
     * 全国大会マスタ情報更新【主キー変更】時のパラメータ
     */
    public static final String updatePKey_ARGS =
        "entity, entryYear, entryKai, lastTmsp";

    /**
     * 削除フラグ更新時の更新対象パラメータ
     */
    public static final String updateDeleteFlg_PERSISTENT_PROPS =
        "sakujoFlg, lastUser, lastPgm";
    /**
     * 全国大会マスタ情報リストを取得する。
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @return List 全国大会マスタ情報リスト
     */
    public List selectNational(String entryCd, String entryYear);

    /**
     * エントリー回の最大値を取得する。
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @return int エントリ回MAX値
     */
    public int selectMaxEntryKai(String entryCd, String entryYear);

    /**
     * 全国大会マスタ情報を登録する。
     * @param uiEntryMst エントリーマスタ管理エンティティ
     * @return int 更新結果
     */
    public int insert(UIEntryMst uiEntryMst);

    /**
     * 全国大会マスタ情報を更新する。
     * @param uiEntryMst エントリーマスタ管理エンティティ
     * @return int 更新結果
     */
    public int update(UIEntryMst uiEntryMst);

    /**
     * 全国大会マスタ情報を更新する。【主キー変更】
     * @param entity    エントリーマスタ管理エンティティ
     * @param entryYear エントリー年
     * @param entryKai  エントリー回
     * @param lastTmsp  修正時タイプスタンプ
     * @return int 更新結果
     */
    public int updatePKey(
        UIEntryMst entity,
        String entryYear,
        String entryKai,
        Timestamp lastTmsp);

    /**
     * 削除フラグの更新を行う。
     * @param uiEntryMst エントリーマスタ管理エンティティ
     * @return int 更新結果
     */
    public int updateDeleteFlg(UIEntryMst uiEntryMst);
}