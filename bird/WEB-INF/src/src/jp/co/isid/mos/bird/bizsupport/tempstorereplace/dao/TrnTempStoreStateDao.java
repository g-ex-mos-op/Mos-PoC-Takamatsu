package jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.TrnTempStoreStateList;


/**
 * ‰¼“X•ÜŠm’èó‹µDAO
 * 
 * @author Aspac
 */
public interface TrnTempStoreStateDao {

    public static final Class BEAN = TrnTempStoreStateList.class;

    
    public static final String getTempStoreStateKakutei_ARGS = "companyCd, fromDt, toDt, nendo, targetCd, code";
    public static final String getTempStoreStateMikakutei_ARGS = "companyCd, fromDt, toDt, nendo, targetCd, code";
    public static final String getTempStoreStateMitoroku_ARGS = "companyCd, fromDt, toDt, nendo, targetCd, code";
    public static final String getYosanInfo_ARGS = "companyCd, fromDt, toDt";

    
    /**
     * ‰¼“X•Ü’uŠ·ó‹µŠm’èƒŠƒXƒgæ“¾‚·‚é
     * @return List
     */
    public List getTempStoreStateKakutei(String companyCd, String fromDt, String toDt, String nendo, String targetCd, String code);
    
    
    /**
     * ‰¼“X•Ü’uŠ·ó‹µ–¢Šm’èƒŠƒXƒgæ“¾‚·‚é
     * @return List
     */
    public List getTempStoreStateMikakutei(String companyCd, String fromDt, String toDt, String nendo, String targetCd, String code);
    
    
    /**
     * ‰¼“X•Ü’uŠ·ó‹µ–¢“o˜^ƒŠƒXƒgæ“¾‚·‚é
     * @return List
     */
    public List getTempStoreStateMitoroku(String companyCd, String fromDt, String toDt, String nendo, String targetCd, String code);
    
    
    /**
     * —\Zî•ñ(—\Zİ’èŠJn“úA—\Z)‚ğæ“¾‚·‚é
     * @return List
     */
    public List getYosanInfo(String companyCd, String fromDt, String toDt);
    
    
}
