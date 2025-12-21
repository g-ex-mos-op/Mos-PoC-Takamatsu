package jp.co.isid.mos.bird.bizreport.campsuiiref.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;

/**
 * „ˆÚî•ñŒŸõæ“¾
 * @author xnkusama
 *
 */
public interface SearchLogic {

    /**
     * „ˆÚî•ñŒŸõæ“¾
     * @param sessionDto
     * @param requestDto
     */
    public List execute(SessionSuiiDto sessionDto, RequestSuiiDto requestDto);
}
