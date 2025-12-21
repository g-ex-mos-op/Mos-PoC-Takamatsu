package jp.co.isid.mos.bird.bizreport.moscardniporef.logic;

import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;

/**
 * LOGIC【検索条件取得】インターフェース
 * 
 * @author 
 */
public interface SearchLogic {

	/**
     * 検索条件を取得する
	 * @param dto  日報共通DTO【検索条件】
	 */
	public Map getSearchData(NipoRefConditionParameterDto dto);
}