package jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.MstStoreInfo;


/**
 * 店舗情報DAO
 * 
 * @author Aspac
 */
public interface MstStoreDao {

    public static final Class BEAN = MstStoreInfo.class;

    
    public static final String getTempStoreReplaceAllowList_ARGS = "companyCd";

    
    /**
     * 店舗情報リストを取得する
     * @param 会社コード
     * @return 店舗情報リスト
     */
    public List getTempStoreReplaceAllowList(String companyCd);
    
    
}
