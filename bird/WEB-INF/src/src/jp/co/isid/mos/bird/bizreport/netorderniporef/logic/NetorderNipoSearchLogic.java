package jp.co.isid.mos.bird.bizreport.netorderniporef.logic;

import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;

/**
 * 検索条件取得ロジック・インターフェース
 * 
 * @author xnarita
 */
public interface NetorderNipoSearchLogic {

	/**
     * 検索条件を取得する
	 * @param dto  条件部情報Dto
	 * @return Map 検索条件情報
	 */
	public Map getSearchData(NipoRefConditionParameterDto dto);
}