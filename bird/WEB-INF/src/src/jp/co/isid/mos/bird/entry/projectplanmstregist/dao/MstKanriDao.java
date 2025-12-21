package jp.co.isid.mos.bird.entry.projectplanmstregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.MstKanri;

/**
 * 事業方針説明会マスタDao
 * 
 * @author xkinu
 *
 */
public interface MstKanriDao {   
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = MstKanri.class;
    
    /**
     * 条件に紐づく日付マスタを検索する時のパラメータ
     */
    public static final String select_ARGS = "entryCd, entryYear, entryKai";
    /**
     * 条件に紐づく日付マスタを検索する時のパラメータ
     */
    public static final String selectNewEntry_ARGS = "entryCd, entryYear, entryKai";
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 更新・削除(論理削除)登録処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS  = "entryTitle, note, sakujoFlg, lastUser, lastPgm";
    
    /**
     * 排他用マスタ検索時のパラメーター
     */
    public static final String selectCheckChanged_ARGS = "entity";
    /**
     * 更新・削除(論理削除)登録処理時のパラメータ
     */
    public static final String updateChangeYear_ARGS = "entity, lastYear, lastKai";
    
    public static final String updateChangeYear_PERSISTENT_PROPS  = "entryYear, entryKai, entryTitle, note, lastUser, lastPgm";
    
    /**
     * 条件に紐づくマスタを検索
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @return
     */
    public List select(String entryCd, String entryYear, String entryKai);
    /**
     * 条件に紐づく新規用マスタを検索
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @return
     */
    public List selectNewEntry(String entryCd, String entryYear, String entryKai);
    /**
     * 新規登録処理
     * 
     * @param entity 事業方針説明会マスタエンティティ
      */
    public void insert(MstKanri entity);
    /**
     * 更新登録処理
     * 
     * @param entity 事業方針説明会マスタエンティティ
     */
    public void update(MstKanri entity);
    /**
     * 排他用マスタ検索処理
     * 
     * @param entity 事業方針説明会マスタエンティティ
     * @return List
     */
    public List selectCheckChanged(MstKanri entity);
    /**
     * 更新登録処理
     * 
     * @param entity
     * @param lastYear
     * @param lastKai
     */
    public void updateChangeYear(MstKanri entity, String lastYear, String lastKai);
}
