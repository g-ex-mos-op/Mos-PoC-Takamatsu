/*
 * 作成日: 2006/07/20
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIMlResult;

/**
 * マスターライセンス結果状況DAO
 * 
 * @author xyuchida
 */
public interface UIMlResultDao {

    public static final Class BEAN = UIMlResult.class;
    public static final String selectTwoYearStaffId_ARGS = "examNo, entryYear, lastYear";

    /**
     * 受験結果登録
     * 
     * @param entity マスターライセンス結果状況Entity
     */
    public void updateTotalResult(UIMlResult entity);
    
    /**
     * 面接結果登録
     * @param entity マスターライセンス結果状況Entity
     */
    public void updateInterviewResult(UIMlResult entity);

    /**
     * 能力チェック結果登録
     * @param entity マスターライセンス結果状況Entity
     */
    public void updateAbilityCheckResult(UIMlResult entity);
    
    /**
     * 総合結果登録
     * @param entity マスターライセンス結果状況Entity
     */
    public void updateSynthesisResult(UIMlResult entity);
    /**
     * 過去２年分からの受験番号を取得
     * @param entity マスターライセンス結果状況Entity
     */
    public UIMlResult selectTwoYearStaffId(String examNo,String entryYear,String lastYear);
    

}
