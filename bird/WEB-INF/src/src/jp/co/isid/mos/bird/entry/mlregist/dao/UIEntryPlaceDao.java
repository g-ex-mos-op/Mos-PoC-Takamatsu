/*
 * 作成日: 2006/06/09
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryPlace;

/**
 * エントリー受験地管理DAO
 * 
 * @author xyuchida
 */
public interface UIEntryPlaceDao {

    public static final Class BEAN = UIEntryPlace.class;

    public static final String getEntryPlaceList_ARGS = "entryCd, entryYear, entryKai";

    public int insertEntryPlaceList(List uiEntryPlaceList);

    public int updateEntryPlaceList(List uiEntryPlaceList);

    public List getEntryPlaceList(String entryCd, String entryYear, String entryKai);

    public List getDefaultPlaceList();
}
