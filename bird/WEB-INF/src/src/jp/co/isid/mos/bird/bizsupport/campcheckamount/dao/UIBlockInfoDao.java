package jp.co.isid.mos.bird.bizsupport.campcheckamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UIBlockInfo;

/**
 * ブロック情報
 * 
 * @author xlee
 */
public interface UIBlockInfoDao {

    public static final Class BEAN = UIBlockInfo.class;

    /**
     *　ブロック情報の取得
     * @return ブロック情報
     */
    public List getBlockInfo();
}
