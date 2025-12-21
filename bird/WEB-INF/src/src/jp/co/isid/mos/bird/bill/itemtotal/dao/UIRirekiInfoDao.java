/*
 * 作成日: 2006/08/21
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.dao;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.entity.UIRirekiInfo;

/**
 *　経歴情報取得DAO
 * 
 * @author xlee
 */
public interface UIRirekiInfoDao {

    public static final Class BEAN = UIRirekiInfo.class;
    
    public static final String getRirekiInfo_ARGS = "miseCd, shoJituCd, condYMStr, condYMEnd ";

    /**
     * 経歴情報を検索します。
     * 
     * １４ヶ月分のデータを取得する
     * 
     * @param miseCd パラメータ対象店舗
     * @param shoJituCd 実商品コード
     * @param　condYm　パラメータ対象年月:START
     * @param　condYm　パラメータ対象年月:END
     * @return 経歴情報　List
     */
    public List getRirekiInfo(String miseCd, String shoJituCd, String condYMStr, String condYMEnd);
}
