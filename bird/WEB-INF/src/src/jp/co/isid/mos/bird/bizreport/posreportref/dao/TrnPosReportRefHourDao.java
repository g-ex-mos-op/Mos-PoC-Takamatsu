package jp.co.isid.mos.bird.bizreport.posreportref.dao;
    /**
     * @author inazawa
     * 2007/02/07
     * POS速報時間帯Dao
     */
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.entity.TrnPosReportRefHour;

public interface TrnPosReportRefHourDao {
    /**
     * POS速報時間帯情報エンティティクラス
     */
    public static final Class BEAN = TrnPosReportRefHour.class;
    
    public static final String getPosReportRefHour_ARGS = "companyCd,miseCd,latestDate,";
    
    //  POS速報時間帯情報
    List getPosReportRefHour(String companyCd,String miseCd,String latestDate);

}
