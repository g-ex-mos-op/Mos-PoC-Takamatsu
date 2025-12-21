/*
 * 作成日: 2006/12/20
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIAttendAbsentCntInfo;


/**
 * 全体のオーナー出欠席情報取得処理
 * 
 * @author xamaruayama
 */
public interface UIAttendAbsentCntDao {

    public static final Class  BEAN = UIAttendAbsentCntInfo.class;
    /**
     * パラメーター情報：全体のオーナー出欠席情報取得
     */
    public static final String select_ARGS = "entryCd, entryYear, entryKai" +
            ", sysDate, companyCd, taishoJoken, fromDt, sibuCd, svCd";

    /**
     * 確認画面用
     * 全体のオーナー出欠席情報取得処理
     * 
     * 取得値
     *     参加オーナー
     *     不参加オーナー
     *     未登録オーナー
     *     出席人数
     *     
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
    public List select(String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishoJoken, String fromDt, String sibuCd, String svCd);
}