package jp.co.isid.mos.bird.analysis.posdata.logic;

import java.util.List;
import jp.co.isid.mos.bird.analysis.posdata.dto.PosDataFormDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;

public interface PosDataOutputLogic {
    
    /**
     * 条件部情報を取得する
     * @param userType	ユーザタイプ
     * @param userId	ユーザID
     * @param appDate	アプリ日付
     * @return Map     条件部情報
     */
    public List execute (PosDataFormDto dto,OwnerSearchDto onerDto);
}
