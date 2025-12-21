package jp.co.isid.mos.bird.bizreport.campsuiiref.logic;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;

/**
 * キャンペーン情報設定
 * @author xnkusama
 *
 */
public interface GetCampMenuInfoLogic {

    /**
     * キャンペーン情報設定
     * @param requestDto
     */
    public void execute(RequestSuiiDto requestDto);
}
