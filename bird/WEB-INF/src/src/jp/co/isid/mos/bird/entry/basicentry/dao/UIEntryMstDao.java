/*
 * 作成日: 2006/06/20
 */
package jp.co.isid.mos.bird.entry.basicentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryMst;


/**
 * ベーシック研修一覧情報(UIBasicListDao)
 * @author kusama
 */
public interface UIEntryMstDao {

    public static final Class  BEAN = UIEntryMst.class;
    public static final String getBasicListInfo_ARGS = "sysDate, sysNextDate, entryCd, onerCd, userTypeCd";
    public static final String getEntryInfo_ARGS = "entryCd, entryYear, entryKai";

    /**
     * ベーシック研修一覧情報取得(getBasicListInfo)
     * @param sysDate,sysNextDate,entryCd,onerCd
     * @return List 検索結果
     */
    public List getBasicListInfo(String sysDate, String sysNextDate, String entryCd, String onerCd, String userTypeCd);
    
    /**
     * 指定条件のエントリーマスタ取得
     */
    public UIEntryMst getEntryInfo(String entryCd, String entryYear, String entryKai);
}