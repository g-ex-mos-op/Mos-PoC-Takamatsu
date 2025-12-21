package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;

public interface GyotaibetuNipoRefSibuLogic {

    public List execute(Map paramMap);
    
    /**
     * ‘ÎÛ“X•Ü”‚Ìæ“¾
     * @param paramMap
     * @return count
     */
    public int getTenpoCount(Map paramMap);
    
    /**
     * ‰æ–Ê•\¦—p‚É®—‚·‚é
     * 
     * @param sibuList
     * @param companyCd
     * @param conditionDto
     * @return
     */
    public List editViewDataList(List sibuList, NipoRefConditionParameterDto conditionDto);
    
}
