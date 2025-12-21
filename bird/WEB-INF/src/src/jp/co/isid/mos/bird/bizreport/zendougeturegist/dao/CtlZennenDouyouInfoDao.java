package jp.co.isid.mos.bird.bizreport.zendougeturegist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.entity.CtlZennenDouyouInfo;

public interface CtlZennenDouyouInfoDao {
    /**
     * 前年同月設定DAO
     */
    public static final Class BEAN = CtlZennenDouyouInfo.class;
    
    public static final String getZenDougetuInfo_ARGS = "companyCd,eigyoDt";
    public static final String insertZenDougetuInfo_ARGS = "entity";
    public static final String updateZenDougetuInfo_ARGS = "entity";
    
    //  前年同月設定情報
    List getZenDougetuInfo(String companyCd,String eigyoDt);
    //  前年同月設定登録
    int insertZenDougetuInfo(CtlZennenDouyouInfo entity);
    //  前年同月設定変更
    int updateZenDougetuInfo(CtlZennenDouyouInfo entity);

}
