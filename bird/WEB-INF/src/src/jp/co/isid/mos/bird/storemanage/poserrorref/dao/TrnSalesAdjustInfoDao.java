package jp.co.isid.mos.bird.storemanage.poserrorref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.poserrorref.entity.TrnSalesAdjustInfo;

/**
 * 売上精算管理情報DAO
 * @author xkonishi
 *
 */
public interface TrnSalesAdjustInfoDao {
    
    public static final Class BEAN = TrnSalesAdjustInfo.class;
    
    public static final String select_ARGS = "companyCd, miseCd, onerCd, userTypeCd, from, to";
    
    public static final String getCount_ARGS = "companyCd, miseCd, onerCd, userTypeCd, from, to";

    
    /**
     * 売上精算管理情報の検索
     * @param companyCd  会社コード
     * @param miseCd     店コード
     * @param onerCd     オーナーコード
     * @param userTypeCd ユーザータイプコード
     * @param from       年月(当月)
     * @param to         年月(前月)
     * @return 売上精算管理情報リスト
     */
    public List select(String companyCd, String miseCd, String onerCd,
            String userTypeCd, String from, String to);
    /**
     * 売上精算管理情報のレコード数検索
     * @param companyCd  会社コード
     * @param miseCd     店コード
     * @param onerCd     オーナーコード
     * @param userTypeCd ユーザータイプコード
     * @param from       年月(当月)
     * @param to         年月(前月)
     * @return レコード数
     */
    public int getCount(String companyCd, String miseCd, String onerCd,
            String userTypeCd, String from, String to);
}