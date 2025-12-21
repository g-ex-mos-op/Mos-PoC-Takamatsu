package jp.co.isid.mos.bird.entry.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.common.entity.MstEntryMiseInfo;

/**
 * 既存エントリー系の店舗情報取得処理
 * @author xlee
 */
public interface MstEntryMiseInfoDao {
    
    public static final Class BEAN = MstEntryMiseInfo.class;
    
    public static final String getEntryMiseInfo_ARGS = "companyCd, onerCd, sysDate";
    
    /**
     * 店舗取得
     * @param companyCd　会社コード
     * @param onerCd　オーナーコード
     * @param sysDate　システム日付
     * @return 店舗情報リスト
     */
    public List getEntryMiseInfo(String companyCd, String onerCd, String sysDate);
}
