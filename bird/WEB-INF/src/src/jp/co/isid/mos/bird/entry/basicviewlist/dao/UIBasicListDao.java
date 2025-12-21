/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.basicviewlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.basicviewlist.entity.UIBasicListDataInfo;


/**
 * ベーシック研修一覧情報(UIBasicListDao)
 * @author Nakajima
 */
public interface UIBasicListDao {

    public static final Class  BEAN = UIBasicListDataInfo.class;
    public static final String getBasicListInfo_ARGS = "sysDate,sysNextDate,entryCd";

    /**
     * ベーシック研修一覧情報取得(getBasicListInfo)
     * @param sysDate,sysNextDate,entryCd
     * @return List 検索結果
     */
    public List getBasicListInfo(String sysDate, String sysNextDate, String entryCd);
    
}

