package jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UICourseShukketuListInfo;

/**
 * 事業方針説明会、懇親会、共栄会定時総会の
 * 出欠データを取得する。
 * @author xamaruyama
 */
public interface UICourseShukketuListInfoDao {

    public static final Class BEAN = UICourseShukketuListInfo.class;
    /**
     * パラメーター情報：事業方針説明会の全体のオーナー出欠席情報取得
     */
    public static final String selectJigyo_ARGS = "limit, userId, entryCd, entryYear, entryKai" +
            ", sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd";
    /**
     * パラメーター情報：懇親会の全体のオーナー出欠席情報取得
     */
    public static final String selectKonshin_ARGS = "limit, userId, entryCd, entryYear, entryKai" +
            ", sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd";
    /**
     * パラメーター情報：共栄会の全体のオーナー出欠席情報取得
     */
    public static final String selectKyoei_ARGS = "limit, userId, entryCd, entryYear, entryKai" +
            ", sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd";

    /**
     * 出欠席確認画面用
     * 事業方針説明会の全体のオーナー出欠席情報取得処理
     * 
     * 取得値
     *     出席オーナー
     *     欠席オーナー
     *     未登録オーナー
     *     出席人数
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
    public List selectJigyo(boolean limit, String userId, 
            String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String sibuListChoice, String svCd);
    /**
     * 出欠席確認画面用
     * 懇親会の全体のオーナー出欠席情報取得処理
     * 
     * 取得値
     *     出席オーナー
     *     欠席オーナー
     *     未登録オーナー
     *     出席人数
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
    public List selectKonshin(boolean limit, String userId, 
            String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String sibuListChoice, String svCd);
    /**
     * 出欠席確認画面用
     * 共栄会の全体のオーナー出欠席情報取得処理
     * 
     * 取得値
     *     出席オーナー
     *     欠席オーナー
     *     未登録オーナー
     *     出席人数
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
    public List selectKyoei(boolean limit, String userId,
            String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String sibuListChoice, String svCd);

}