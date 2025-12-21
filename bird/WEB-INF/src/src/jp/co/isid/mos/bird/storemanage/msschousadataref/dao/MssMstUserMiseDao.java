/*
 * 作成日: 2006/08/06
 */
package jp.co.isid.mos.bird.storemanage.msschousadataref.dao;

import java.util.List;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.MstUserMise;
/**
 * オーナー保有店舗
 * @author xylee
 */
public interface MssMstUserMiseDao {
    
    public static final Class BEAN = MstUserMise.class;
    
    public static final String selectMise_ARGS = "miseCd, companyCd";

    public static final String selectOnerMise_ARGS = "onerCd,companyCd";

    public static final String selectUserMise_ARGS = "userId,companyCd";
    

    /**
     * オーナータイプ以外ユーザー店舗の検索
     * @param onerCd,miseCd,companyCd
     * @return List
     */
    public List selectMise(String miseCd, String companyCd);
    
    /**
     * オーナータイプユーザー店舗の検索
     * @param onerCd,miseCd,companyCd
     * @return List
     */
    public List selectOnerMise(String onerCd, String companyCd);

    
    /**
     * オーナーユーザー保有店舗の検索
     * @param user_id
     * @return List
     */
    public List selectUserMise(String userId,String companyCd);

    

}