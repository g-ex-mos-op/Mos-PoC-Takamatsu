package jp.co.isid.mos.bird.bizreport.posreportref.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
    /**
     * POS推移情報取得インターフェイス
     * @author inazawa
     * 2007/02/08
     */
public interface PosReportRefSuiiInfoLogic {
    /**
     * POS推移情報取得
     * @param posReportRefDto
     * @return メニュー別POS情報
     */
    public List execute(PosReportRefDto posReportRefDto);

}
