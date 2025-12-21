/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UINisugataInfo;

/**
 * 荷姿情報Dao
 * @author narita
 */
public interface UINisugataInfoDao {

    public static final Class BEAN = UINisugataInfo.class;
    
    /**
     * 荷姿情報リスト取得時のパラメータ
     */
    public static final String getNisugataList_ARGS = "";
    
    /**
     * 荷姿情報リスト取得
     * @return List 荷姿情報リスト
     */
    public List getNisugataList();
}
