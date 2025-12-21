/*
 * 作成日: 2005/2/1
 */
package jp.co.isid.mos.bird.portal.top.dao;

import java.util.List;

import jp.co.isid.mos.bird.portal.top.entity.UISoku;

/**
 * 売上情報取得
 * 
 * 作成日:2009/01/06
 * @author xkinu
 *
 */
public interface UISokuDao {

    public static final Class BEAN = UISoku.class;
    public static final String selectBS09_ARGS = "appDate, appMonth, nendoStartDate, nendo, companyCdList";
    public static final String selectBT60_ARGS = "userTypeCd, userId, appDate, appMonth, companyCdList";

    /**
     * BS09から売上情報を取得
     * @param appDate       対象年月日
     * @param companyCdList[]  企業コード
     * @return List      検索結果
     */
    public List selectBS09(
            String appDate, String appMonth, String nendoStartDate, String nendo, 
            String[] companyCdList);
    /**
     * BT60から売上情報を取得
     * @param userTypeCd
     * @param userId
     * @param appDate
     * @param appMonth
     * @return
     */
    public List selectBT60(String userTypeCd, String userId,
            String appDate, String appMonth, String[] companyCdList);
}
