package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnInterviewResult;


/**
 * マスターライセンス面接結果
 * @author kusama
 */
public interface TrnInterviewResultDao {

    public static final Class BEAN = TrnInterviewResult.class;

    public static final String getResult_ARGS = "entryYear, entryKai, companyCd, sibuCd, onerCd, miseCd, condType";
    public static final String getAllStaffResult_ARGS = "entryCd, entryYear, entryKai, companyCd, sibuCd, onerCd, miseCd";
    public static final String insertInterviewResult_ARGS = "uiEntryOwner";
    public static final String updateInterviewResult_ARGS = "uiEntryOwner";
    public static final String updateInterviewResult_PERSISTENT_PROPS 
            = "entryYear, entryKai, staffId, assesser,"
            + "sub2Chk1Byear, sub2Chk1Lyear, sub2Chk1Lkai, sub2Chk1LastRslt, sub2Chk1Result,"
            + "sub2Chk2Byear, sub2Chk2Lyear, sub2Chk2Lkai, sub2Chk2LastRslt, sub2Chk2Result,"
            + "sub2Chk3Byear, sub2Chk3Lyear, sub2Chk3Lkai, sub2Chk3LastRslt, sub2Chk3Result,"
            + "sub2Chk4Byear, sub2Chk4Lyear, sub2Chk4Lkai, sub2Chk4LastRslt, sub2Chk4Result,"
            + "sub2Chk5Byear, sub2Chk5Lyear, sub2Chk5Lkai, sub2Chk5LastRslt, sub2Chk5Result,"
            + "sub2Chk6Byear, sub2Chk6Lyear, sub2Chk6Lkai, sub2Chk6LastRslt, sub2Chk6Result,"
            + "sub2Chk7Byear, sub2Chk7Lyear, sub2Chk7Lkai, sub2Chk7LastRslt, sub2Chk7Result,"
            + "sub2Chk8Byear, sub2Chk8Lyear, sub2Chk8Lkai, sub2Chk8LastRslt, sub2Chk8Result,"
            + "sub2Chk9Byear, sub2Chk9Lyear, sub2Chk9Lkai, sub2Chk9LastRslt, sub2Chk9Result,"
            + "sub2Chk10Byear, sub2Chk10Lyear, sub2Chk10Lkai, sub2Chk10LastRslt, sub2Chk10Result,"
            + "lastUser, lastPgm";

    /**
     * 面接結果一覧（エントリー者のみ）の取得
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param sibuCd
     * @param onerCd
     * @param miseCd
     * @param condType
     * @return
     */
    public List getResult(String entryYear, String entryKai, String companyCd,
            String sibuCd, String onerCd, String miseCd, String condType);
    
    /**
     * 面接結果一覧（全スタッフ）の取得
     * 
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param sibuCd
     * @param onerCd
     * @param miseCd
     * @param searchType
     * @return
     */
    public List getAllStaffResult(String entryCd,
                                   String entryYear, 
                                   String entryKai,
                                   String companyCd,
                                   String sibuCd,
                                   String onerCd,
                                   String miseCd);
    
    /**
     * マスターライセンス面接結果登録
     * @param trnInterviewResult
     * @return
     */
    public int insertInterviewResult(TrnInterviewResult trnInterviewResult);

    /**
     * マスターライセンス面接結果更新
     * @param trnInterviewResult
     * @return
     */
    public int updateInterviewResult(TrnInterviewResult trnInterviewResult);
}