/*
 * 作成日: 2006/08/06
 */
package jp.co.isid.mos.bird.storemanage.msschousadataref.dao;

import java.util.List;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.MstUserOner;
                                                                            

/**
 * オーナーユーザー保有店舗
 * @author K.INAZAWA(ASPAC)
 */
public interface MssMstUserOnerDao {

    public static final Class BEAN = MstUserOner.class;
    public static final String selectUserOner_ARGS = "onerCd,companyCd";

    /**
     * オーナーユーザー保有店舗の検索
     * @param MssChousaDateRefDto
     * @return List
     */
    public String selectUserOner(String onerCd,String companyCd);
   
}