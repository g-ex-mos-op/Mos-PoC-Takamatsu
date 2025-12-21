package jp.co.isid.mos.bird.entry.nationalviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UICsvStatusList;

/**
 * ダウンロード対象情報取得
 * 
 * @author xkinu
 */
public interface UICsvStatusListDao {
    
    public static final Class BEAN = UICsvStatusList.class;
    /**
     * 参加用 ＆ 不参加用SQLパラメーター
     */
    public static final String select_ARGS = "userId, limit, entryCd, entryYear, entryKai, sysDate" +
    ", companyCd, taishoJoken, sibuCd, svCd, attendKbn";
    /**
     * 未登録用SQLパラメーター
     */
    public static final String selectMitouroku_ARGS = "userId, limit, entryCd, entryYear, entryKai, sysDate" +
            ", companyCd, taishoJoken, sibuCd, svCd";
   
    /**
     * ダウンロード対象情報取得
     * 
     * @param userId
     * @param limit   支部制限判断値
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @param companyCd
     * @param taishoJokenChoice
     * @param sibuListChoice
     * @param svCd
     * @param attendKbnChoice
     * @return
     */
    public List select(String userId, boolean limit, String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishoJoken, String sibuListChoice
            , String svCd, String attendKbnChoice);
    
    /**
     * ダウンロード対象情報取得
     * ’未登録’データ専用
     * 
     * @param userId
     * @param limit   支部制限判断値
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @param companyCd
     * @param taishoJoken
     * @param sibuListChoice
     * @param svCd
     * @return
     */
    public List selectMitouroku(String userId, boolean limit, String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishoJoken
            , String sibuListChoice, String svCd);
    
}