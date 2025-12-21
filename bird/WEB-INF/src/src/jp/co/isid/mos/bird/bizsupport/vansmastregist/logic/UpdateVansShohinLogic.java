/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic;

import java.util.List;
import java.sql.Timestamp;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 代表商品情報の登録・修正・削除ロジックインターフェース
 * @author narita
 */
public interface UpdateVansShohinLogic {

    /**
     * 代表商品情報の登録・修正・削除を行う
     * @param BirdUserInfo ログインユーザ情報
     * @param Timestamp    タイムスタンプ
     * @param shohinList 代表商品リスト
     * @return int 結果コード
     */
    public int execute(List shohinList,BirdUserInfo birdUserInfo,Timestamp currentTimestamp);
}
