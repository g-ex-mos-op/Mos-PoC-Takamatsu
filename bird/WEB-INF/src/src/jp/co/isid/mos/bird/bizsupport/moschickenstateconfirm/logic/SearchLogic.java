package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic;

import java.util.List;
import java.util.Map;

/**
 * 予約・在庫状況確認(モスチキン用画面）
 * 検索結果の取得ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface SearchLogic {

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public List execute(Map params);
}
