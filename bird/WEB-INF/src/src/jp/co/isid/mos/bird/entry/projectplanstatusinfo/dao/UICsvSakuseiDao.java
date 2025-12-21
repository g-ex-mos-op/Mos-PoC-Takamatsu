package jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UICsvSakusei;

/**
 * ダウンロード対象情報取得
 * 
 * @author xamaruyama
 */
public interface UICsvSakuseiDao {
    
    public static final Class BEAN = UICsvSakusei.class;
    
    public static final String select_ARGS = "limit, userId, entryCd, entryYear, entryKai, sysDate" +
    ", companyCd, taishouJokenChoice, taishouListChoice, sibuListChoice, svCd, attendKbnChoice";
    /**
     * 
     */
    public static final String selectAll_ARGS = "limit, userId, entryCd, entryYear, entryKai, sysDate" +
    ", companyCd, taishouJokenChoice, taishouListChoice, sibuListChoice, svCd";

    public static final String selectMitouroku_ARGS = "limit, userId, entryCd, entryYear, entryKai, sysDate" +
            ", companyCd, taishouJokenChoice, taishouListChoice, sibuListChoice, svCd, attendKbnChoice";
    
    /**
     * ダウンロード対象情報取得
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @param companyCd
     * @param taishouJokenChoice
     * @param sibuListChoice
     * @param svCd
     * @param attendKbnChoice
     * @return
     */
    public List select(boolean limit, String userId, String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String taishouListChoice, String sibuListChoice
            , String svCd, String attendKbnChoice);
    
    /**
     * ダウンロード対象情報取得
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @param companyCd
     * @param taishouJokenChoice
     * @param sibuListChoice
     * @param svCd
     * @return
     */
    public List selectAll(boolean limit, String userId, String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String taishouListChoice, String sibuListChoice
            , String svCd);
    
    /**
     * ダウンロード対象情報取得
     * 未登録データ専用
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @param companyCd
     * @param taishouJokenChoice
     * @param sibuListChoice
     * @param svCd
     * @param attendKbnChoice
     * @return
     */
    public List selectMitouroku(boolean limit, String userId, String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String taishouListChoice
            , String sibuListChoice, String svCd, String attendKbnChoice);
    
}