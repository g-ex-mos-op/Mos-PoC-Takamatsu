package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic;

import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;

public interface GyotaibetuNipoSearchLogic {

	//public Map getSearchData(NipoRefSearchDto dto);
	
	public Map getSearchData(NipoRefConditionParameterDto dto);
}
