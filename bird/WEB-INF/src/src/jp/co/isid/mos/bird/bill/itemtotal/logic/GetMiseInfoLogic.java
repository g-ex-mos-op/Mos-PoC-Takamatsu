/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic;

import java.util.List;

/**
 * 店情報取得ロジック
 * 
 * @author xlee
 */
public interface GetMiseInfoLogic {

    /**
     * 店情報取得
     * 
     * @param　sysDate システムデータ
     * @param　onerCd オーナーコード
     * 
     * @return  店情報リスト
     */
    public List execute(String sysDate, String onerCd);
}
