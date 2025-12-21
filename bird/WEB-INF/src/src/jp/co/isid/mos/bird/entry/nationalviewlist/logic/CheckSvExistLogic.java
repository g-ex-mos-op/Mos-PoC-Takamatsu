package jp.co.isid.mos.bird.entry.nationalviewlist.logic;

import java.util.Map;

/**
 * 全国大会申込状況確認画面
 * 
 * SVコード存在チェック ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface CheckSvExistLogic {

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public String execute(Map params);
}
