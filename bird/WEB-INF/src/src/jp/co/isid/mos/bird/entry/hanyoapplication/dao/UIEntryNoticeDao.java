/*
 * 作成日: 2007/1/9
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.dao;


import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryNotice;


/**
 * エントリー文言情報（UIEntryNoticeDao）
 * @author inazawa
 */
public interface UIEntryNoticeDao {

    public static final Class BEAN = UIEntryNotice.class;
    public static final String getEntryNotice_ARGS  = "entryCd,entryYear,entryKai";

    /**
     * エントリー文言情報の取得
     * @param uIEntryNotice
     */
    public String getEntryNotice(String entryCd,String entryYear,String entryKai);

}
