package jp.co.isid.mos.bird.entry.mlentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryMst;


/**
 * マスターライセンス一覧情報(UIMlListDao)
 * @author Aspac
 */
public interface UIEntryMstDao {

    public static final Class  BEAN = UIEntryMst.class;
    public static final String getMlListInfo_ARGS = "sysDate, sysNextDate, entryCd, onerCd, userTypeCd";
    public static final String getEntryInfo_ARGS = "entryCd, entryYear, entryKai";

    /**
     * マスターライセンス一覧情報取得(getMlListInfo)
     * @param sysDate,sysNextDate,entryCd,onerCd
     * @return List 検索結果
     */
    public List getMlListInfo(String sysDate, String sysNextDate, String entryCd, String onerCd, String userTypeCd);
    
    /**
     * 指定条件のエントリーマスタ取得
     */
    public UIEntryMst getEntryInfo(String entryCd, String entryYear, String entryKai);
}