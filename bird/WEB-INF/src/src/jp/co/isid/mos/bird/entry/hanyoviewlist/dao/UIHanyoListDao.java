/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.hanyoviewlist.entity.UIHanyoListDataInfo;


/**
 * 研修(全て/出張/更新)一覧情報(UIHanyoListDao)
 * @author Nakajima
 */
public interface UIHanyoListDao {

    public static final Class  BEAN = UIHanyoListDataInfo.class;
    public static final String getHanyoListInfo_ARGS = "sysDate,sysNextDate,entryCd";

    /**
     * 研修(全て/出張/更新)一覧情報取得(getHanyoListInfo)
     * @param sysDate,sysNextDate,entryCd
     * @return List 検索結果
     */
    public List getHanyoListInfo(String sysDate, String sysNextDate, String entryCd);

}

