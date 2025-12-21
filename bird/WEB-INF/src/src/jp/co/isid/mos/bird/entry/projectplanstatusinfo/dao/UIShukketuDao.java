/*
 * 作成日: 2006/11/29
 */
package jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIShukketu;


/**
 * 全体のオーナー出欠席情報取得処理
 * 
 * @author xamaruayama
 */
public interface UIShukketuDao {

    public static final Class  BEAN = UIShukketu.class;
    /**
     * パラメーター情報：事業方針説明会の全体のオーナー出欠席情報取得
     */
    public static final String selectJigyo_ARGS = "entryCd, entryYear, entryKai" +
            ", sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd";
    /**
     * パラメーター情報：懇親会の全体のオーナー出欠席情報取得
     */
    public static final String selectKonshin_ARGS = "entryCd, entryYear, entryKai" +
            ", sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd";
    /**
     * パラメーター情報：共栄会の全体のオーナー出欠席情報取得
     */
    public static final String selectKyoei_ARGS = "entryCd, entryYear, entryKai" +
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
    public List selectJigyo(String entryCd, String entryYear, String entryKai, String sysDate
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
    public List selectKonshin(String entryCd, String entryYear, String entryKai, String sysDate
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
    public List selectKyoei(String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String sibuListChoice, String svCd);

}