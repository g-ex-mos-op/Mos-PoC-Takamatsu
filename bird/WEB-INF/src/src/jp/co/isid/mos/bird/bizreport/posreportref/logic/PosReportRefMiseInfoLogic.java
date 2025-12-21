package jp.co.isid.mos.bird.bizreport.posreportref.logic;
/**
 * 店一覧POS件数取得インターフェイス
 * @author inazawa
 * 2007/02/07
 */
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;

public interface PosReportRefMiseInfoLogic {
   /** 店一覧POS件数取得
    * @param posReportRefDto
    * @return
    */
   public List execute(PosReportRefDto posReportRefDto);

}
