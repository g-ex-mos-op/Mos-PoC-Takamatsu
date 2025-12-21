package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIViewTenpoInfo;

public interface UIViewTenpoInfoDao {

    public Class BEAN = UIViewTenpoInfo.class;
    
    public static final String getOnerTenpoInfo_ARGS = "companyCd, userId, totalCd";
    
    public static final String getStaffTenpoInfo_ARGS = "companyCd, userId, totalCd";
    
    /**
     * オーナー店舗の情報取得
     * @param companyCd 会社コード
     * @param userId ユーザーID
     * @return オーナー店舗の店コードまたは支部取り込みコード
     */
    public List getOnerTenpoInfo(String companyCd, String userId, String totalCd);

    /**
     * 店舗の情報取得
     * @param companyCd 会社コード
     * @param userId ユーザーID
     * @return 店舗の店コードまたは支部取り込みコード
     */
    public List getStaffTenpoInfo(String companyCd, String userId, String totalCd);

    
    
}
