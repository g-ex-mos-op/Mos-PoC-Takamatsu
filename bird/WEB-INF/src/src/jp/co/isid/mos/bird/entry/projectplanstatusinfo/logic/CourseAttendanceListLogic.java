/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic;

import java.util.Map;

/**
 * 出欠席情報取得ロジック インターフェイス
 * @author xamaruyama
 */
public interface CourseAttendanceListLogic {
    /**
     * 実行処理
     * 
     * @param params パラメーター保持Map
     * @return
     */
    public Map execute(Map params);
}