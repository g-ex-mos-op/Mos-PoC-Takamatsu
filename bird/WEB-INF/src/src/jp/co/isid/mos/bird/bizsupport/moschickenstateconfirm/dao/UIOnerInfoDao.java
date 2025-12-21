/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity.UIOnerInfo;

/**
 * 対象オーナー情報 Dao
 * 
 * @author xkinu
 *
 */
public interface UIOnerInfoDao {
    /** エンティティクラス */
    public static final Class BEAN = UIOnerInfo.class;
    
    public static final String select_ARGS = "companyCd, onerCd";
    
    /**
     * 
     * @param companyCd
     * @param onerCd
     * @return
     */
    public List select(String companyCd, String onerCd);
}
