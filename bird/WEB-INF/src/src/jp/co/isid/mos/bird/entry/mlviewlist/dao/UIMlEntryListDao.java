package jp.co.isid.mos.bird.entry.mlviewlist.dao;

import java.util.List;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlEntryInfo;


/**
 * マスターライセンス申込状況一覧情報(UIMlEntryListDao)
 * @author Aspac
 */
public interface UIMlEntryListDao {

    public static final Class  BEAN = UIMlEntryInfo.class;
    public static final String getMlEntryInfo_ARGS = "sysdate,entryCd,entryYear,entryKai,searchSelectFlg,sibuCd,onerCd,miseCd";
    public static final String getMlEntryInfoSaiban_ARGS = "entryCd, entryYear, entryKai";
    public static final String updateEntity_PERSISTENT_PROPS = "examNo, lastUser, lastPgm, lastTmsp";
    
    /**
     * マスターライセンス申込状況一覧情報取得(getMlEntryInfo)
     * @param sysdate,entryCd,entryYear,entryKai
     * @return List 検索結果
     */
    public List getMlEntryInfo(String sysdate, String entryCd, String entryYear, String entryKai, String searchSelectFlg, String sibuCd, String onerCd, String miseCd);

    /**
     * マスターライセンス申込状況の取得（受験番号採番処理用）
     * @param entryCd,entryYear,entryKai
     * @return List
     */
    public List getMlEntryInfoSaiban(String entryCd, String entryYear, String entryKai);
    
    /**
     * マスターライセンス申込状況の更新
     * @param UIMlEntryInfo
     * @return int
     */
    public int updateEntity(UIMlEntryInfo entity);
}

