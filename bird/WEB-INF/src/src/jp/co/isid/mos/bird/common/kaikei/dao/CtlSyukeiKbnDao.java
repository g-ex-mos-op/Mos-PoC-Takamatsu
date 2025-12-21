/*
 * 作成日: 2012/07/25
 */
package jp.co.isid.mos.bird.common.kaikei.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;


/**
 * 会計集計区分情報取得
 * 
 * @author xkawa
 */
public interface CtlSyukeiKbnDao {

    public static final Class BEAN = CtlSyukeiKbn.class;
    public static final String select_ARGS = "birdDisFlg";

    /**
     * 会計集計区分情報取得の取得
     * @param String birdDisFlg BIRD表示フラグ
     * @return List
     */
    public List select(String birdDisFlg);
    
    /**
     * 全集計区分検索
     * @return List
     */
    public List selectAll();

}            
