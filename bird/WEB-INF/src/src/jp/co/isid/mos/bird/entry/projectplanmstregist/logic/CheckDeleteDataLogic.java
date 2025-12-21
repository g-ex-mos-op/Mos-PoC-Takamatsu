package jp.co.isid.mos.bird.entry.projectplanmstregist.logic;

import java.util.List;
import java.util.Map;

/**
 * 事業方針説明会マスタ登録
 * 削除処理不可判断ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface CheckDeleteDataLogic {

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public List execute(Map params);
}
