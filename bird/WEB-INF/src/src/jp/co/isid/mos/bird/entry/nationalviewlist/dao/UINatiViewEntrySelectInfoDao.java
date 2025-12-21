/*
 * 作成日: 2014/05/09
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.dao;
import java.util.List;

import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UINatiViewEntrySelectInfo;


/**
 * エントリーセレクション情報の取得処理
 * @author xlee
 */
public interface UINatiViewEntrySelectInfoDao {
    
    public static final Class BEAN = UINatiViewEntrySelectInfo.class;
    
    public static final String getNatiViewEntrySelectInfo_ARGS = "entryCd, entryYear, entryKai, selectionKbn";
    
    /**
     *　エントリーセレクション情報の取得
     * @param entryCd エントリーコード
     * @param entryYear　エントリー年
     * @param entryKai　エントリー回
     * @return　エントリーセレクション情報
     */
    public List getNatiViewEntrySelectInfo(String entryCd, String entryYear, String entryKai, String selectionKbn);
}
