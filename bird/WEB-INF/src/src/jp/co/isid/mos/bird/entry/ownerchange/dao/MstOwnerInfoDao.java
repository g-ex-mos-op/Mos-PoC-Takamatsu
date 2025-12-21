/*
 * 作成日:2007/01/16
 */
package jp.co.isid.mos.bird.entry.ownerchange.dao;

import jp.co.isid.mos.bird.entry.ownerchange.entity.MstOwnerInfo;
/**
 * オーナー情報Dao
 * @author xkonishi
 *
 */
public interface MstOwnerInfoDao{
    
    public static final Class BEAN = MstOwnerInfo.class;
    
    public static final String select_ARGS = "companyCd, miseCd";
    
    /**
     * オーナー情報検索
     * @param 会社コード
     * @param 店コード
     * @return オーナー情報
     */
    public MstOwnerInfo select(String companyCd, String miseCd);    
}
