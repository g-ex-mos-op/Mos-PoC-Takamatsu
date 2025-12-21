/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.CodSMneuInfo;



/**
 * メニュー追加取得entity
 * 
 * @author inazawa
 */
public interface CodSMnueInfoDao {
    
    public static final Class BEAN = CodSMneuInfo.class;

    public static final String getMosChickenSmenu_ARGS = "companyCd,miseCd,menuNameKj,sysDt";

    /**
     * 商品追加用取得処理
     * @param miseNameKj
     * @return
     */
    public List getMosChickenSmenu(String companyCd,String miseCd,String menuNameKj,String sysDt);

}
