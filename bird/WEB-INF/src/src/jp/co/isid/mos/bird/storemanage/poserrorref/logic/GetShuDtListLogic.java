/*
 * 作成日:2007/02/07
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.logic;

import java.util.List;

/**
 * POS集信エラー店舗一覧　集信日リスト取得ロジック
 * @author xkonishi
 *
 */
public interface GetShuDtListLogic {

    /**
     * 集信日取得ロジック
     * @param basicDate 基準日
     * @param days      日数
     * @return 集信日リスト
     */
    public List execute(String basicDate, int days);
}
