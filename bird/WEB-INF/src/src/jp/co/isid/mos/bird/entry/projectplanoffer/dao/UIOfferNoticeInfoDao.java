/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dao;

import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferNoticeInfo;

/**
 * @author xlee
 *
 */
public interface UIOfferNoticeInfoDao {
    
    public static final Class BEAN = UIOfferNoticeInfo.class;
    
    public static final String getOfferNoticeInfo_ARGS = "entryCd, entryYear, entryKai";
    
    /**
     * エントリー文言情報
     * @param entryCd　エントリーコード
     * @param entryYear　エントリー年
     * @param entryKai　エントリー回
     * @return UINoticeInfo　エントリー文言情報
     */
    public UIOfferNoticeInfo getOfferNoticeInfo(String entryCd, String entryYear, String entryKai);
}
