package jp.co.isid.mos.bird.bizsupport.plinfoview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.CodBlock;


/**
 * ブロックコード情報
 * @author kusama
 */
public interface CodBlockDao {

    public static final Class BEAN = CodBlock.class;

    /**
     * ブロックコード情報の取得
     * @return List
     */
    public List getBlock();
    
}