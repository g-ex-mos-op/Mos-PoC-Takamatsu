/*
 * 作成日: 2006/04/21
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.CodCourse;


/**
 * コース情報
 * 
 * @author xkinu
 */
public interface CodCourseDao {

    public static final Class BEAN = CodCourse.class;
    public static final String select_ARGS = "entryCd, entryYear, entryKai, sysDate, usertypeCd";
    public static final String selectCourse_ARGS = "entryCd, entryYear, entryKai";
    
    /**
     * コース情報の取得
     * 
     * 結果登録期間内のコース情報を取得
     * 
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param sysDate システム日付
     * @param usertypeCd ユーザータイプコード
     * 
     * @return List
     */
    public List select(String entryCd, String entryYear, String entryKai, String sysDate, String usertypeCd);

    /**
     * コース情報の取得
     * 
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * 
     * @return CodCourse
     */
    public CodCourse selectCourse(String entryCd, String entryYear, String entryKai);
}