package jp.co.isid.mos.bird.entry.projectplanmstregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.UICourseInfo;

/**
 * 事業方針説明会マスタ登録
 * 対象事業方針説明会情報 DAO
 * 
 * @author xkinu
 *
 */
public interface UICourseInfoDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = UICourseInfo.class;
    
    /**
     * 前年度以降の事業方針説明会の情報を取得する時のパラメータ
     * 
     * 1.エントリーコード
     * 2.エントリー年
     * 2.エントリー回
     * 
     */
    public static final String select_ARGS = "entryCd, entryYear, entryKai";
    /**
     * 前年度以降の事業方針説明会の情報を取得する時のパラメータ
     * 
     * 1.システム日付
     * 2.エントリーコード
     * 2.システム日付の前年度
     * 
     */
    public static final String selectList_ARGS = "sysDate, entryCd, sysLastNendo";
    /**
     * 指定事業方針説明会情報
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @return
     */
    public List select(String entryCd, String entryYear, String entryKai);
    /**
     * 条件画面出力情報検索処理
     * 
     * 前年度以降の事業方針説明会の情報を取得する
     * 
     * @param sysDate
     * @param entryCd
     * @param sysLastNendo
     * @return
     */
    public List selectList(String sysDate, String entryCd, String sysLastNendo);

}
