/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic;

import java.util.List;


/**
 *　キャンペーンタイトル取得ロジック
 * 
 * @author xlee
 */
public interface GetTitleInfoLogic {

    /**
     * キャンペーンタイトル取得
     * @param condFromDt アプリ日付FROM
     * @param condToDt アプリ日付TO
     * @return　キャンペーンタイトル
     */
    public List execute(String condFromDt, String condToDt);
}
