package jp.co.isid.mos.bird.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
/**
 * 祝祭日取得インターフェイス
 * @author inazawa
 *
 */
public interface GetHolidayLogic {
    /**
     * 日付が祝祭日がどうか
     * @param 日付リスト
     * @return 日付の後に祝祭日名がついた日付
     * @exception ApplicationException
     */
    public List getHoliday(String date);

}
