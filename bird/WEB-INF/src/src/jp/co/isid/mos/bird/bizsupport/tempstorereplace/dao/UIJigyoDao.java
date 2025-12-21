package jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.UIJigyoList;


/**
 * 事業リストDAO
 * 
 * @author Aspac
 */
public interface UIJigyoDao {

    public static final Class BEAN = UIJigyoList.class;

    
    public static final String getJigyoList_ARGS = "companyCd";

    
    /**
     * 事業リストを取得する
     * @param 会社コード
     * @return 事業リスト
     */
    public List getJigyoList(String companyCd);
    
    
}
