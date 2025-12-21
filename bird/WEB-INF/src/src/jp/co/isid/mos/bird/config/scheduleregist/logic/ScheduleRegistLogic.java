package jp.co.isid.mos.bird.config.scheduleregist.logic;

import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;

/**
 * 登録 ロジック インターフェイス
 * @author xnkusama
 */
public interface ScheduleRegistLogic {

    /**
     * 実行
     * @param dto
     */
    public void execute(ScheduleRegistSessionDto dto);
}