package jp.co.isid.mos.bird.bizsupport.noinputoner.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;

/**
 * 売上情報DAO
 * 
 * @author Aspac
 */
public interface TrnZenPLInfoDao {

    public static final Class BEAN = TrnPLInfo.class;

    public static final String getZenPLInfo_ARGS = "eigyoDt, companyCd, miseCd";

    
    /**
     * 前月PLデータ取得
     * @param eigyoDt 営業年月
     * @param miseCd 店コード
     * @return 売上高
     */
    public List getZenPLInfo(String eigyoDt, String companyCd, List miseCd);

    
    
}
