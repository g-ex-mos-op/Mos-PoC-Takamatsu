/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic;

import java.util.List;


/**
 *　キャンペーンタイトル取得ロジック
 * 
 * @author xlee
 */
public interface GetTitleInfoLogic {

    /**
     * キャンペーンタイトル取得
     * @param sysDate システム日付
     * @return　キャンペーンタイトル
     */
    public List execute(String sysDate);
}
