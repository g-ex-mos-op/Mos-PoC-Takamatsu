package jp.co.isid.mos.bird.config.scheduleregist.logic;

import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;

/**
 * 編集画面情報の取得ロジック インターフェイス
 * @author xnkusama
 */
public interface SearchScheduleLogic {

    /**
     * 実行
     * @param dto
     */
    public void execute(ScheduleRegistSessionDto dto);
}