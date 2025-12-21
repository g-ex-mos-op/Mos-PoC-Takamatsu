package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic;

import java.util.List;
import java.util.Map;

/**
 * 予約・在庫状況確認(モスチキン用画面）条件項目情報の取得
 * ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface ConditionLogic {

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public List execute(Map params);

//add 2019/08/12 USI begin
    /**
     * @param ckanriNo
     * @return
     */
    public List getShokuList(String ckanriNo);
//add 2019/08/12 USI end
}
