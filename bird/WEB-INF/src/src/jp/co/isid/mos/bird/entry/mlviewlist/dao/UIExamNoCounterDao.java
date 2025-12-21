/*
 * 作成日: 2006/08/14
 */
package jp.co.isid.mos.bird.entry.mlviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIExamNoCounter;


/**
 * マスターライセンス受験番号採番カウンターDAO
 * @author kusama
 */
public interface UIExamNoCounterDao {

    public static final Class  BEAN = UIExamNoCounter.class;
    public static final String getCounter_ARGS = "kbn";
    public static final String updateCounter_ARGS = "entity";
    public static final String updateCounter_PERSISTENT_PROPS = "noCounter, lastUser, lastPgm, lastTmsp";
    
    /**
     * カウンター取得
     * @return List 現在の受験番号Max値情報
     */
    public List getCounter(String kbn);
    
    
    /**
     * 採番カウンターテーブルの更新
     */
    public int updateCounter(UIExamNoCounter entity);
    
}

