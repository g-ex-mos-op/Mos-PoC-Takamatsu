/*
 * 作成日: 2006/05/29
 */
package jp.co.isid.mos.bird.bizreport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.entity.CodBlock;


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