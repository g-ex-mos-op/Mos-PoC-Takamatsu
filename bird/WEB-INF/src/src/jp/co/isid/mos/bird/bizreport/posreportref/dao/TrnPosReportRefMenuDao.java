package jp.co.isid.mos.bird.bizreport.posreportref.dao;
    /**
     * @author inazawa
     * 2007/02/07
     * POS速報メニュー別Dao
     */

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.entity.TrnPosReportRefMenu;

public interface TrnPosReportRefMenuDao {
    /**
     * POS速報メニュー別情報エンティティクラス
     */
    public static final Class BEAN = TrnPosReportRefMenu.class;
    
    public static final String getPosReportRefMenu_ARGS = "companyCd,miseCd,latestDate,";
    
    //  POS速報時間帯情報
    List getPosReportRefMenu(String companyCd,String miseCd,String latestDate);
    
}
