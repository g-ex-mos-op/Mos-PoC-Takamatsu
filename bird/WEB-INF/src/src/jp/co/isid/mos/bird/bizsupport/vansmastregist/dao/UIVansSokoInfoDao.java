/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIVansSokoInfo;

/**
 * 対象倉庫情報Dao
 * @author narita
 */
public interface UIVansSokoInfoDao {

    public static final Class BEAN = UIVansSokoInfo.class;
    
    /**
     * 対象倉庫情報リスト取得時のパラメータ
     */
    public static final String getVansSokoList_ARGS = "";
    
    /**
     * 対象倉庫情報リスト取得
     * @return List 対象倉庫情報リスト
     */
    public List getVansSokoList();
}
