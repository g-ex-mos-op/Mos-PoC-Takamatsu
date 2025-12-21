/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.hanyoviewlist.entity.UIHanyoEntryInfo;


/**
 * 研修(出張/更新)エントリー者一覧情報(UIHanyoEntryListDao)
 * @author Nakajima
 */
public interface UIHanyoEntryListDao {

    public static final Class  BEAN = UIHanyoEntryInfo.class;
    public static final String getHanyoEntryInfo_ARGS = "sysdate,entryCd,entryYear,entryKai";

    /**
     * 研修(出張/更新)エントリー者一覧情報取得(getHanyoEntryInfo)
     * @param sysdate,entryCd,entryYear,entryKai
     * @return List 検索結果
     */
    public List getHanyoEntryInfo(String sysdate, String entryCd, String entryYear, String entryKai);

}

