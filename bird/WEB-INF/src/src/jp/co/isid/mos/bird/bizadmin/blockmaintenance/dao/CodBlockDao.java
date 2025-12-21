/*
 * 作成日: 2007/04/18
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.CodBlock;

/**
 * ブロック情報
 * 
 * @author xkinu
 */
public interface CodBlockDao {

    public static final Class BEAN = CodBlock.class;
    /**
     * 検索時のパラメータ
     */
    public static final String select_ARGS = "changeDataOnly";
    
    /**
     * ブロック情報の取得
     * 
     * @param changeDataOnly　変更可能データのみか否か
     * @return
     */
    public List select(boolean changeDataOnly);
    
}