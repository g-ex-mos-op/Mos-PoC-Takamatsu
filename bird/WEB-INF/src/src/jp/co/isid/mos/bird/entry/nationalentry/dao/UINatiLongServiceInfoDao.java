/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.dao;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiLongServiceInfo;

/**
 * @author xlee
 *
 */
public interface UINatiLongServiceInfoDao {
    
    public static final Class BEAN = UINatiLongServiceInfo.class;
    
    public static final String getLongServiceInfo_ARGS = "entryYear, sysDate, userTypeCd";
    
    /**
     * オーナー取得
     * @param companyCd 会社コード
     * @param onerCd　オーナーコード
     * @return　オーナー情報
     */
    public UINatiLongServiceInfo getLongServiceInfo(String entryYear, String sysDate, String userTypeCd);
}
