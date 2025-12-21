package jp.co.isid.mos.bird.entry.nationalviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UISibuOnerStatusList;

/**
 * 全国大会申込状況確認
 * 画面用支部別オーナー別出欠情報取得Dao
 * 
 * 出欠データを取得する。
 * @author xkinu
 */
public interface UISibuOnerStatusListDao {

    public static final Class BEAN = UISibuOnerStatusList.class;
    /**
     * パラメーター情報：オーナー全体出欠席情報取得
     */
    public static final String select_ARGS = "userId, limit, entryCd, entryYear, entryKai" +
            ", sysDate, companyCd, taishoJoken, fromDt, sibuCd, svCd";

    /**
     * 出欠席確認画面用
     * 全体のオーナー出欠席情報取得処理
     * 
     * 取得値
     *     出席オーナー
     *     欠席オーナー
     *     未登録オーナー
     *     出席人数
     *     
     * @param userId
     * @param limit   支部制限判断値
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @param companyCd
     * @param taishoJoken
     * @param fromDt
     * @param sibuCd
     * @param svCd
     * @return
     */
    public List select(String userId, boolean limit, String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishoJoken, String fromDt, String sibuCd, String svCd);
}