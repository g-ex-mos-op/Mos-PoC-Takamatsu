package jp.co.isid.mos.bird.bizreport.posreportregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIPosReportMiseInfo;
/**
 * 
 * 更新日:2010/11/09 ASPAC Ｐ６対応仕様変更対応
 * 
 * @author xkinu
 *
 */
public interface UIPosReportMiseInfoDao {

    public static final Class BEAN = UIPosReportMiseInfo.class;
   
    /**　Ｐ６対応　店舗情報リスト取得 */
    public static final String select_ARGS = "companyCd, sysDate";
    
    
    /**
     * Ｐ６対応検索　
     * 店舗情報リスト取得
     * 
     * @param companyCd
     * @return
     */
    public List select(String companyCd, String sysdate);

}

