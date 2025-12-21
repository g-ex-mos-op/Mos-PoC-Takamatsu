package jp.co.isid.mos.bird.bizreport.moscardniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.MstSegmentInfo;


public interface MstSegmentInfoDao {

    public static final Class BEAN = MstSegmentInfo.class;
    
    public static final String getSegmentInfo_ARGS = "companyCd";
    
    /**
     * 管理会社企業に紐付くセグメント情報を取得
     * @param companyCd
     * @return
     */
    public List getSegmentInfo( String companyCd );
    

}
    
    