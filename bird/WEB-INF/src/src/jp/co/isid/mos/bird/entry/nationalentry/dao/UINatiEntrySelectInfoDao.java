/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.dao;
import java.util.List;

import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntrySelectInfo;

/**
 * エントリーセレクション情報の取得処理
 * @author xlee
 */
public interface UINatiEntrySelectInfoDao {
    
    public static final Class BEAN = UINatiEntrySelectInfo.class;
    
    public static final String getNatiEntrySelectInfo_ARGS = "entryCd, entryYear, entryKai, selectionKbn";
    
    /**
     *　エントリーセレクション情報の取得
     * @param entryCd エントリーコード
     * @param entryYear　エントリー年
     * @param entryKai　エントリー回
     * @return　エントリーセレクション情報
     */
    public List getNatiEntrySelectInfo(String entryCd, String entryYear, String entryKai, String selectionKbn);
}
