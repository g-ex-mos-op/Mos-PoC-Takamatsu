package jp.co.isid.mos.bird.bizsupport.codbalancelist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.codbalancelist.entity.UICodBalanceList;


/**
 * COD残高一覧Dao
 * 
 * @author xkinu
 */
public interface UICodBalanceListDao {

    public static final Class BEAN = UICodBalanceList.class;
    /**
     * COD残高一覧取得処理SQL引数
     */
    public static final String select_ARGS = "";
    
    public static final String selectCod_ARGS = "companyCd,onerCd";
    /**
     * COD残高一覧検索処理
     * 
     * @param companyCd
     * @param taishoCd
     * @return
     */
    public List select();
    
    /**
     * COD残高一覧検索処理
     * 
     * @param companyCd
     * @param onerCd
     * @return
     */
    public List selectCod(String companyCd, String onerCd);

    
}