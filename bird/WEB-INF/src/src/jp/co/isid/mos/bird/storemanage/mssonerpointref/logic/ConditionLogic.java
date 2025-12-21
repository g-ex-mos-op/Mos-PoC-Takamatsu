package jp.co.isid.mos.bird.storemanage.mssonerpointref.logic;

import java.util.Map;

import jp.co.isid.mos.bird.storemanage.mssonerpointref.dto.MssOnerPointRankCsvDto;


/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * 条件画面情報生成ロジック インターフェース
 * 
 * @author xkinu
 *
 */
public interface ConditionLogic {
    public Map execute(MssOnerPointRankCsvDto dto) throws Exception;
}
