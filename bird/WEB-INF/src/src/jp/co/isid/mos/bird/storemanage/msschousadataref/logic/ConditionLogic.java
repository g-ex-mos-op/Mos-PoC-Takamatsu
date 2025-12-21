package jp.co.isid.mos.bird.storemanage.msschousadataref.logic;

import java.util.Map;

import jp.co.isid.mos.bird.storemanage.msschousadataref.dto.MssChousaDataRefDto;


/**
 * ミステリーショッパーズ 店舗別調査
 * 条件画面情報生成ロジック インターフェース
 * 
 * @author xinazawa
 *
 */
public interface ConditionLogic {
    public Map execute(MssChousaDataRefDto dto) throws Exception;


}
