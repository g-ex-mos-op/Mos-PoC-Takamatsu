package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UIYagoListInfo;

public interface UIYagoListInfoDao {

    public static final Class BEAN = UIYagoListInfo.class;
   
    public static final String getDtlYagoCode_ARGS = "yagoCd";
    
    /**
     * 屋号マスタ情報の取得
     * @param companyCd
     * @return
     */
    public List getMstYagoInfo();
    
    /**
     * 詳細屋号マスタ情報の取得
     * @param companyCd
     * @return
     */
    public List getDtlYagoInfo();
    
    /**
     * 詳細屋号コードの取得
     * @param companyCd
     * @return
     */
    public List getDtlYagoCode( String yagoCd);
}

