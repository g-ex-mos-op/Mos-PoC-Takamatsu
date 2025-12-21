package jp.co.isid.mos.bird.bizreport.posreportref.logic;

import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;

/**
 * 最新日付取得ロジック
 * @author inazawa
 * 2007/0207
 */
public interface LatestDateLogic {
    /**
     * 最新日付取得
     * @param posReportRefDto
     * @return 最新日付
     */
    public String execute(PosReportRefDto posReportRefDto);
}
