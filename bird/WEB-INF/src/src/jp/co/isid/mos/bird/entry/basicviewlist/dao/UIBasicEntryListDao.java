/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.basicviewlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.basicviewlist.entity.UIBasicEntryInfo;


/**
 * ベーシック研修エントリー者一覧情報(UIBasicEntryListDao)
 * @author Nakajima
 */
public interface UIBasicEntryListDao {

    public static final Class  BEAN = UIBasicEntryInfo.class;
    public static final String getBasicEntryInfo_ARGS = "sysdate,entryCd,entryYear,entryKai";

    /**
     * ベーシック研修エントリー者一覧情報取得(getBasicEntryInfo)
     * @param sysdate,entryCd,entryYear,entryKai
     * @return List 検索結果
     */
    public List getBasicEntryInfo(String sysdate, String entryCd, String entryYear, String entryKai);

}

