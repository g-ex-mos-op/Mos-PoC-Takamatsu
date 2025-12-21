package jp.co.isid.mos.bird.config.scheduleregist.logic;

import jp.co.isid.mos.bird.config.scheduleregist.dto.ScheduleRegistSessionDto;

/**
 * スケジュール登録 条件項目取得ロジック インターフェイス
 * @author xnkusama
 */
public interface ScheduleRegistCondtionLogic {

    /**
     * 実行
     * @param dto
     */
    public void execute(ScheduleRegistSessionDto dto);
}