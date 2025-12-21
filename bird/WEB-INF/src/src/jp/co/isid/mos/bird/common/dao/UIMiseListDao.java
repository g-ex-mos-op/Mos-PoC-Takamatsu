/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIMiseList;


/**
 * 指定オーナー対象店舗情報 Dao
 * 
 * ＜取得パターン＞
 * ・クローズ店を含まない取得 
 * ・クローズ店を含む取得
 * 
 * @author xkinu
 *
 */
public interface UIMiseListDao {
    /** エンティティクラス */
    public static final Class BEAN = UIMiseList.class;
    
    public static final String getCloseIn_ARGS  = "companyCd, onerCd, sysDate";
    public static final String getCloseInSortMise_ARGS  = "companyCd, onerCd, sysDate";
    public static final String getCloseNotIn_ARGS  = "companyCd, onerCd, sysDate";
    /**
     * クローズ店を含む対象店舗取得
     * 
     * @param companyCd
     * @param onerCd
     * @param sysDate
     * @return
     */
    public List getCloseIn(String companyCd, String onerCd ,String sysDate);
    
    /**
     * クローズ店を含む対象店舗取得
     * 
     * @param companyCd
     * @param onerCd
     * @param sysDate
     * @return
     */
    public List getCloseInSortMise(String companyCd, String onerCd ,String sysDate);
    
    /**
     * クローズ店を含めない店舗取得
     * @param companyCd
     * @param onerCd
     * @param sysDate
     * @return
     */
    public List getCloseNotIn(String companyCd, String onerCd ,String sysDate);
}
