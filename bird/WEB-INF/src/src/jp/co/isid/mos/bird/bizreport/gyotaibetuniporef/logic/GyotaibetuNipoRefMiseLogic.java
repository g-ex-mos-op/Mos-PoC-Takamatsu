package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;

public interface GyotaibetuNipoRefMiseLogic {

    public List execute(Map paramMap);
    
    /**
     * ‰æ–Ê•\¦—p‚É®—‚·‚é
     * 
     * @param miseList
     * @param conditionDto
     * @return
     */
    public List editViewDataList(List miseList, NipoRefConditionParameterDto conditionDto);
}
