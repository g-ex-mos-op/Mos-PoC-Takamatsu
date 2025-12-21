package jp.co.isid.mos.bird.entry.mlviewlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlListDataInfo;


/**
 * マスターライセンス一覧情報(UIMlListDao)
 * @author Aspac
 */
public interface UIMlListDao {

    public static final Class  BEAN = UIMlListDataInfo.class;
    public static final String getMlListInfo_ARGS = "sysDate,sysNextDate,entryCd";

    /**
     * マスターライセンス一覧情報取得(getMlListInfo)
     * @param sysDate,sysNextDate,entryCd
     * @return List 検索結果
     */
    public List getMlListInfo(String sysDate, String sysNextDate, String entryCd);

}

