package jp.co.isid.mos.bird.bizreport.posreportref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.entity.TrnPosReportRef;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;


/**
 * @author inazawa
 * 2007/02/07
 * POS速報Dao
 */
public interface TrnPosReportRefDao {
    /**
     * POS速報情報エンティティクラス
     */
    public static final Class BEAN = TrnPosReportRef.class;
    
    public static final String getLatestDate_ARGS = "requestDto";
    public static final String getPosReportRefSuii_ARGS = "companyCd,miseCd,shuSysDate";
    public static final String getPosReportRefMise_ARGS = "companyCd,shuSysDate";
    /**
     * オーナー用店一覧POS売上検索
     * 作成日:2010/10/25
     */
    public static final String selectOnerMiseList_ARGS = "birdUserInfo, requestDto";
    /**
     * 本部用店一覧POS売上検索
     * 作成日:2010/10/25
     */
    public static final String selectHonbuMiseList_ARGS = "birdUserInfo, requestDto";

    //最新日付の取得
    String getLatestDate(PosReportRefDto requestDto);
    
    //店一覧POS売上
    List getPosReportRefMise(String companyCd, String shuSysDate);

    //  店別POS推移金額
    List getPosReportRefSuii(String companyCd, String miseCd, String shuSysDate);
    
    /**
     * オーナー用店一覧POS売上検索
     * 作成日:2010/10/25
     * 
     * @param birdUserInfo
     * @param requestDto
     * @return List[[POS速報(店舗一覧)]]
     */
    public List selectOnerMiseList(
    		  BirdUserInfo birdUserInfo
    		, PosReportRefDto requestDto);
    /**
     * 本部用店一覧POS売上検索
     * 作成日:2010/10/25
     * 
     * @param birdUserInfo
     * @param requestDto
     * @return List[[POS速報(店舗一覧)]]
     */
    public List selectHonbuMiseList(
    		  BirdUserInfo birdUserInfo
    		, PosReportRefDto requestDto);
    

}
