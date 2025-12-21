/*
 * 作成日:2007/02/08
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.poserrorref.entity.TrnShushinError;

/**
 * 集信エラー店舗情報DAO
 * @author xkonishi
 *
 */
public interface TrnShushinErrorDao {
    
    public static final Class BEAN = TrnShushinError.class;
    
    public static final String select_ARGS = "companyCd, miseCd, onerCd," +
            "userTypeCd, shuDt";
    
    /**
     * 集信エラー情報取得
     * @param companyCd  会社コード
     * @param miseCd     店コード
     * @param onerCd     オーナーコード
     * @param userTypeCd ユーザータイプコード
     * @param shuDt  集信日
     * @return 集信エラー情報リスト
     */
    public List select(String companyCd, String miseCd, String onerCd,
            String userTypeCd, String shuDt);
}