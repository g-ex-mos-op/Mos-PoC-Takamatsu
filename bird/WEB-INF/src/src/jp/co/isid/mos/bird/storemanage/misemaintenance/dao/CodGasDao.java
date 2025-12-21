/*
 * 作成日: 2011/07/08
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodGas;

/**
 * ガス種別情報取得
 * 
 * 作成日:2011/07/08
 * @author xkinu
 *
 */
public interface CodGasDao {

    public static final Class BEAN = CodGas.class;
    /**
     * ガス種別種別情報の検索
     * @param String companyCd 管理会社企業コード
     * @param String miseCd 店舗コード
     * 
     * @return List
     */
    public List select();
    
}            
