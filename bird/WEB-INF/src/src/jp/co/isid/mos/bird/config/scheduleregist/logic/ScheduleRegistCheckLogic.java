package jp.co.isid.mos.bird.config.scheduleregist.logic;

import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistRequestDto;
import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;

/**
 * 登録チェック ロジック インターフェイス
 * @author xnkusama
 */
public interface ScheduleRegistCheckLogic {

    /**
     * 実行
     * @param dto
     * @param reqDto
     */
    public void execute(ScheduleRegistSessionDto dto, ScheduleRegistRequestDto reqDto);
}