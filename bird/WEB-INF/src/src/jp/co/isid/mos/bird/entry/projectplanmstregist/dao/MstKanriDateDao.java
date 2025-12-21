package jp.co.isid.mos.bird.entry.projectplanmstregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.MstKanriDate;

/**
 * 事業方針説明会日付マスタDao
 * 
 * @author xkinu
 *
 */
public interface MstKanriDateDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = MstKanriDate.class;
    
    /**
     * 条件に紐づく日付マスタを検索する時のパラメータ
     */
    public static final String select_ARGS = "entryCd, entryYear, entryKai";
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 更新登録処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS  = "fromDt, toDt, lastUser, lastPgm";
    /**
     * 排他用マスタ検索時のパラメーター
     */
    public static final String selectCheckChanged_ARGS = "entity";
    /**
     * 更新・削除(論理削除)登録処理時のパラメータ
     */
    public static final String updateChangeYear_ARGS = "entity, lastYear, lastKai";
    
    public static final String updateChangeYear_PERSISTENT_PROPS  = "entryYear, entryKai, fromDt, toDt, lastUser, lastPgm";
    
    /**
     * 条件に紐づく日付マスタを検索
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @return
     */
    public List select(String entryCd, String entryYear, String entryKai);
    /**
     * 新規登録処理
     * 
     * @param entity 事業方針説明会日付マスタエンティティ
     */
    public void insert(MstKanriDate entity);
    /**
     * 更新登録処理
     * 
     * @param entity 事業方針説明会日付マスタエンティティ
     */
    public void update(MstKanriDate entity);
    /**
     * 排他用マスタ検索処理
     * 
     * @param entity 事業方針説明会マスタエンティティ
     * @return List
     */
    public List selectCheckChanged(MstKanriDate entity);
    /**
     * 更新登録処理
     * 
     * @param entity
     * @param lastYear
     * @param lastKai
     */
    public void updateChangeYear(MstKanriDate entity, String lastYear, String lastKai);

}
