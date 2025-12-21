/*
 * 作成日: 2006/02/21
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstHDC;


/**
 * HDC受賞履歴
 * @author xnkusama
 */
public interface MstHDCDao {

    public static final Class BEAN = MstHDC.class;
    public static final String selectHDC_ARGS = "companyCd, miseCd";

    /**
     * HDC受賞履歴の検索
     * @param String companyCd 会社コード
     * @param String miseCd 店コード
     * @return List
     */
    public List selectHDC(String companyCd, String miseCd);
    
}            
