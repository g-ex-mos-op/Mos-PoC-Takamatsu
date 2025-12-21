/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.basicviewlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.basicviewlist.entity.UIBasicEntryCsvInfo;


/**
 * ベーシック研修エントリーCSV一覧情報(UIBasicEntryCsvListDao)
 * @author Nakajima
 */
public interface UIBasicEntryCsvListDao {

    public static final Class  BEAN = UIBasicEntryCsvInfo.class;
    public static final String getBasicEntryCsvInfo_ARGS = "sysdate,entryCd,entryYear,entryKai";

    /**
     * ベーシック研修エントリーCSV一覧情報取得(getBasicEntryCsvInfo)
     * @param sysdate,entryCd,entryYear,entryKai
     * @return List 検索結果
     */
    public List getBasicEntryCsvInfo(String sysdate, String entryCd, String entryYear, String entryKai);

}

