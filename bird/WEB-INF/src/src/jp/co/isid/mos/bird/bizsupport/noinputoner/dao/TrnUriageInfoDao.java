package jp.co.isid.mos.bird.bizsupport.noinputoner.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.TrnUriageInfo;

/**
 * 売上情報DAO
 * 
 * @author Aspac
 */
public interface TrnUriageInfoDao {

    public static final Class BEAN = TrnUriageInfo.class;

    public static final String getUriage_ARGS = "eigyoDt, miseCd";

    /**
     * 売上情報取得
     * @param eigyoDt 営業年月
     * @param miseCd 店コード
     * @return 売上高
     */
    public List getUriage(String eigyoDt, List miseCd);

    
    
}
