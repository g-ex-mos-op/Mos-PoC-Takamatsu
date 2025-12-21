package jp.co.isid.mos.bird.analysis.kakouriage.logic;

import jp.co.isid.mos.bird.analysis.kakouriage.dto.KakoUriageDto;
import jp.co.isid.mos.bird.analysis.kakouriage.dto.KakoUriageReqDto;

/**
 * データ取得 ロジック
 * @author xnkusama
 *
 */
public interface SearchKakoUriageLogic {

    public void execute(KakoUriageDto dto, KakoUriageReqDto dtoReq);
    
}
