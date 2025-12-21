package jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.UISibuList;


/**
 * 支部リストDAO
 * 
 * @author Aspac
 */
public interface UISibuDao {

    public static final Class BEAN = UISibuList.class;

    
    public static final String getSibuList_ARGS = "companyCd";

    
    /**
     * 支部リストを取得する
     * @param 会社コード
     * @return 支部リスト
     */
    public List getSibuList(String companyCd);
    
    
}
