package jp.co.isid.mos.bird.entry.mlentry.dao;


import jp.co.isid.mos.bird.entry.mlentry.entity.UIResultState;

/**
 * エントリー状況（UIResultStateDao）
 * @author Aspac
 */
public interface UIResultStateDao {

    public static final Class BEAN = UIResultState.class;
    public static final String selectResultStateInfo_ARGS  = "staffId, entryYear, entryYearKai";
    public static final String insertResultState_ARGS  = "uiResultState";
    public static final String updateResultState_ARGS  = "uiResultState";
    public static final String deleteResultState_ARGS  = "totalLastYear, totalLastKai, staffId";
    public static final String updateResultState_PERSISTENT_PROPS 
    = "staffId, licenseKbn, examNo, reentryFlg, entryCount, reentryBaseYear,"
    + "totalLastYear, totalLastKai, totalResult,"
    + "sub1LastYear, sub1LastKai, sub1Result,"
    + "sub2LastYear, sub2LastKai, sub2Result,"
    + "sub3LastYear, sub3LastKai, sub3Result,"
    + "lastUser, lastPgm, lastTmsp";
    
    public static final String deleteResultState_SQL
    = "delete from BT32MLKR where TOTAL_LAST_YEAR = /*totalLastYear*/'2005' and TOTAL_LAST_KAI = /*totalLastKai*/'003' and STAFF_ID = /*staffId*/'99999901'";
    
    /**
     * マスターライセンス結果状況履歴の新規登録
     * @param 
     */
    public int insertResultState(UIResultState uiResultState);

    /**
     * マスターライセンス結果状況履歴の更新
     * @param 
     */
    public int updateResultState(UIResultState uiResultState);

    /**
     * マスターライセンス結果状況履歴の削除
     * @param 
     */
    public int deleteResultState(String totalLastYear, String totalLastKai, String staffId);

    /**
     * マスターライセンス結果状況履歴の検索
     * @param entryYear エントリー年
     * @param staffId スタッフID
     * @return UIResultState
     */
    public UIResultState selectResultStateInfo(String staffId, String entryYear, String entryYearKai);

    
}
