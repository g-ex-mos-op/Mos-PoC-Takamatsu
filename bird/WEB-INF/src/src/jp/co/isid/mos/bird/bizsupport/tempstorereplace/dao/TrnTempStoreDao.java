package jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.TrnTempStoreInfo;


/**
 * ‰¼“X•ÜŠm’èDAO
 * 
 * @author Aspac
 */
public interface TrnTempStoreDao {

    public static final Class BEAN = TrnTempStoreInfo.class;

    
    public static final String getMiseCd_ARGS = "kariCd";
    public static final String deleteTempStoreInfo_ARGS = "tempStoreInfo";

    
    /**
     * À“X”Ôæ“¾
     * @return “Xî•ñƒŠƒXƒg
     */
    public List getMiseCd(List kariCd);
    
    
    /**
     * ‰¼“X•ÜŠm’èTBL‚ğ“o˜^(’Ç‰Á)‚·‚é
     * @return “o˜^Œ”
     */
    public int insert(TrnTempStoreInfo tempStoreInfo);
    
    /**
     * ‰¼“X•ÜŠm’èTBL‚ğ“o˜^(XV)‚·‚é
     * @return XVŒ”
     */
    public int updateTempStoreInfo(TrnTempStoreInfo tempStoreInfo);
    
    
    /**
     * ‰¼“X•ÜŠm’èTBL‚ğíœ‚·‚é
     * @return void
     */
    public void deleteTempStoreInfo(TrnTempStoreInfo tempStoreInfo);
    
    
    
}
