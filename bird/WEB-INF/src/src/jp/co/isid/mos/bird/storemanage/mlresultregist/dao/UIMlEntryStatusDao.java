/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlEntryStatus;

/**
 * マスターライセンスエントリ状況DAO
 * 
 * @author xyuchida
 */
public interface UIMlEntryStatusDao {

    public static final Class BEAN = UIMlEntryStatus.class;

    public static final String selectExamNoList_ARGS = "entryCd, entryYear, entryKai";
 
    /* 2006/08/30 miyagi 追加 */
    public static final String selectTwoYearExamNoList_ARGS = "entryCd, entryYear, lastYear";
    /* 2006/08/30 miyagi 追加ここまで */
    
    public static final String selectLicensedStaffList_ARGS = "entryCd, entryYear, entryKai, sysDate";

    /**
     * 受験番号リスト取得
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @return 受験番号リスト
     */
    public List selectExamNoList(String entryCd, String entryYear, String entryKai);
    
    /* 2006/08/30 miyagi 追加 */
    /**
     * ここ二年分の受験番号リスト取得
     * @param entryCd　　エントリーコード
     * @param entryYear　エントリー年
     * @param lastYear　 エントリー年 - 1
     * @return
     */
    public List selectTwoYearExamNoList(String entryCd, String entryYear, String lastYear);
    /* 2006/08/30 miyagi 追加ここまで */

    /**
     * ライセンス発行済スタッフリスト取得
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     * @param sysDate システム日付
     * @return ライセンス発行済スタッフリスト
     */
    public List selectLicensedStaffList(String entryCd, String entryYear, String entryKai, String sysDate);
}
