/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.MstCmenuInfo;


/**
 * マスタ登録用商品名称取得entity
 * @author inazawa
 *
 */
public interface MstCMnueInfoDao {
    
    public static final Class BEAN = MstCmenuInfo.class;
    
    public static final String getMosChickenInfo_ARGS = "ckanriNo,miseCd,companyCd";

    /**
     * 登録マスタ用商品取得処理
     * @param ckanriNo
     * @return
     */
    public List getMosChickenInfo(String ckanriNo,String miseCd,String companyCd);

}
