package jp.co.isid.mos.bird.storemanage.msstantorankref.logic;

import java.util.Map;

import jp.co.isid.mos.bird.storemanage.msstantorankref.dto.MssTantotenRankingRefDto;


/**
 * ミステリーショッパーズ 担当店ランキング
 * 条件画面情報生成ロジック インターフェース
 * 
 * @author xkinu
 *
 */
public interface ConditionLogic {
    public Map execute(MssTantotenRankingRefDto dto) throws Exception;
}
