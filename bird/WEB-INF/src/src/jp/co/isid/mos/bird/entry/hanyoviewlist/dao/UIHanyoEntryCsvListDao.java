/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.hanyoviewlist.entity.UIHanyoEntryCsvInfo;


/**
 * 研修(出張/更新)エントリーCSV一覧情報(UIHanyoEntryCsvListDao)
 * @author Nakajima
 */
public interface UIHanyoEntryCsvListDao {

    public static final Class  BEAN = UIHanyoEntryCsvInfo.class;
    public static final String getHanyoEntryCsvInfo_ARGS = "sysdate,entryCd,entryYear,entryKai";

    /**
     * 研修(出張/更新)エントリーCSV一覧情報取得(getHanyoEntryCsvInfo)
     * @param sysdate,entryCd,entryYear,entryKai
     * @return List 検索結果
     */
    public List getHanyoEntryCsvInfo(String sysdate, String entryCd, String entryYear, String entryKai);

}
