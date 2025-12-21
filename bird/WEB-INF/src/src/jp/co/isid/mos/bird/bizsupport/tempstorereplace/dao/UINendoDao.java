package jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.UINendoList;


/**
 * 年度リストDAO
 * 
 * @author Aspac
 */
public interface UINendoDao {

    public static final Class BEAN = UINendoList.class;

    
    public static final String getNendoListByCompany_ARGS = "companyCd";

    
    /**
     * 年度リストを取得する
     * @return 年度リスト
     */
    public List getNendoListByCampany(String companyCd);
    

    /**
     * 年度リストを取得する
     * @return 年度リスト
     */
    public List getNendoList();
    
}
