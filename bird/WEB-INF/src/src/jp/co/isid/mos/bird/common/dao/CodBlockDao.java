/*
 * 作成日: 2008/06/19
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.CodBlock;


/**
 * ブロック情報
 * 
 * @author xkinu
 */
public interface CodBlockDao {

    public static final Class BEAN = CodBlock.class;
    
    /**
     * ブロック情報の取得
     * @return List
     */
    public List select();
    
}