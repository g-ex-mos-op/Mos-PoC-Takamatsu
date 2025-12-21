/*
 * 作成日: 2007/1/9
 */
package jp.co.isid.mos.bird.entry.hanyoregist.dao;


import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryNotice;


/**
 * エントリー文言情報（UIEntryNoticeDao）
 * @author inazawa
 */
public interface UIEntryNoticeDao {

    public static final Class BEAN = UIEntryNotice.class;
    public static final String insertEntryNotice_ARGS  = "uIEntryNotice";
    public static final String updateEntryNotice_ARGS  = "uIEntryNotice";
    public static final String deleteEntryNotice_ARGS  = "uIEntryNotice";
    public static final String getEntryNotice_ARGS  = "entryCd, joinCharacterList";
    public static final String updateEntryNotice_PERSISTENT_PROPS = "notice2, lastUser, lastPgm";

    /**
     * エントリー文言情報の新規登録(insertEntryDate)
     * @param uIEntryNotice
     */
    public int insertEntryNotice(UIEntryNotice uIEntryNotice);

    /**
     * エントリー文言情報の更新(updateEntryDate)
     * @param uIEntryNotice
     */
    public int updateEntryNotice(UIEntryNotice uIEntryNotice);
    
    /**
     * エントリー文言情報の削除（論理削除）
     * @param uIEntryNotice
     */
    public int deleteEntryNotice(UIEntryNotice uIEntryNotice);
    
    /**
     * エントリー文言情報の取得
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @return
     */
    public UIEntryNotice getEntryNotice(String entryCd, List joinCharacterList);

}
