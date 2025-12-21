package jp.co.isid.mos.bird.bizsupport.noinputoner.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.TrnPLInfoList;

/**
 * @author Aspac
 */
public interface TrnPLInfoListDao {

    public static final Class BEAN = TrnPLInfoList.class;
    
    public static final String getPLInfo_ARGS       = "companyCd, plYm, closeMiseFlg";
    public static final String getPLInfoBySibu_ARGS = "companyCd, plYm, sibuCd, closeMiseFlg";
    
    public List getPLInfo(String companyCd, String plYm, boolean closeMiseFlg);
    public List getPLInfoBySibu(String companyCd, String plYm, String sibuCd, boolean closeMiseFlg);
}
