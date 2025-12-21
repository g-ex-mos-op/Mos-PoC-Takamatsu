/*
 * 作成日: 2006/12/26
 *
 */
package jp.co.isid.mos.bird.entry.nationalentry.dao;

import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryOnerInfo;

/**
 * @author xlee
 *
 */
public interface UINatiEntryOnerInfoDao {
    
    public static final Class BEAN = UINatiEntryOnerInfo.class;
    
    public static final String getNatiEntryOnerInfo_ARGS = "companyCd, onerCd";
    
    /**
     * オーナー取得
     * @param companyCd 会社コード
     * @param onerCd　オーナーコード
     * @return　オーナー情報
     */
    public UINatiEntryOnerInfo getNatiEntryOnerInfo(String companyCd, String onerCd);
}
