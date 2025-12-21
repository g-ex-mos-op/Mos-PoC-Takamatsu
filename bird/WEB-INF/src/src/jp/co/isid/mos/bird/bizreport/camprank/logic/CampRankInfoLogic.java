package jp.co.isid.mos.bird.bizreport.camprank.logic;

import java.util.Map;

/**
 * 順位情報検索
 * @author xnkusama
 *
 */
public interface CampRankInfoLogic {

    /**
     * 指定されたキャンペーンのベスト１００情報を取得
     * @param companyCd
     * @param campId
     * @param fromDt
     * @param toDt
     * @param rankMode
     * @param condMode タブ
     * @param kikanIndex 対象日の選択インデックス
     */
    public Map execute(String comapnyCd, String campId, String fromDt, String toDt, String rankMode, String condMode, int kikanIndex);
}