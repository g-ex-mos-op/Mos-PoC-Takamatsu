package jp.co.isid.mos.bird.bizreport.camprank.logic;

import jp.co.isid.mos.bird.bizreport.camprank.dto.CampRankCondDto;
import jp.co.isid.mos.bird.bizreport.camprank.dto.CampRankDto;

/**
 * ğŒ€–Ú‚Ìæ“¾İ’è
 * @author xnkusama
 *
 */
public interface ConditionLogic {

    /**
     * ğŒ€–Ú‚Ìæ“¾İ’è
     * @param dto
     * @return
     */
    public void execute(CampRankDto dto, CampRankCondDto requestDto);
}
