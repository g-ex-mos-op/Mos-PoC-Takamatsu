/*
 * 作成日: 2008/12/18
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstHanshaOner;


/**
 * 販社オーナー情報
 * 
 * @author xnkusama
 */
public interface MstHanshaOnerDao {

    public static final Class BEAN = MstHanshaOner.class;
    
    public static final String getHanshaOner_ARGS = "companyCd, onerCd";
    
    /**
     * 販社オーナー情報取得
     * @param companyCd
     * @param onerCd
     * @return
     */
    public List getHanshaOner(String companyCd, String onerCd);
    
}