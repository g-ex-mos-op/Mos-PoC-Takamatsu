package jp.co.isid.mos.bird.analysis.shubetusuiiref.logic;

import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefReqDto;

/**
 * データ取得 ロジック
 * @author xnkusama
 *
 */
public interface SearchShubetuSuiiLogic {

    public void execute(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq);
    
}
