package jp.co.isid.mos.bird.bizreport.posreportregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIMiseChkInfo;

/**
 * 店舗チェック情報DAO
 * @author Aspac
 *
 */
public interface UIMiseChkInfoDao {

    public static final Class BEAN = UIMiseChkInfo.class;
   
    public static final String getMiseChkInfo_ARGS = "listMise, companyCd";
    
    /**
     * チェック用店舗リスト取得
     * @param list
     * @return
     */
    public List getMiseChkInfo(List listMise, String companyCd);

}

