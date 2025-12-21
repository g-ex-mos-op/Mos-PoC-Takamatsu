/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.dao;

import java.util.List;

import jp.co.isid.mos.bird.portal.top.entity.UISchedule;

/**
 * スケジュール情報
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public interface UIScheduleDao {
    public static final Class BEAN = UISchedule.class;
    public static final String select_ARGS = "fromDate, toDate,companyCdList";

    /**
     * スケジュール検索処理
     * @param fromDate       対象年月日From
     * @param toDate       対象年月日To
     * @param companyCdList[]  企業コード
     * @return List      検索結果
     */
    public List select(
            String fromDate, String toDate, String[] companyCdList);

}
