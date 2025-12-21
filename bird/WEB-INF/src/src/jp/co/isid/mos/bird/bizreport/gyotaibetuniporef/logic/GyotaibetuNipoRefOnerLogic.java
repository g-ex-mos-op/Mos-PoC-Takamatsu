package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic;

import java.util.List;
import java.util.Map;

public interface GyotaibetuNipoRefOnerLogic {

    public List execute(Map paramMap);
    
    /**
     * ‰æ–Ê•\¦—p‚É®—‚·‚é
     * @param sibuList
     * @param uriageList
     * @param yosanList
     * @return
     */
    public List editViewDataList(List miseList);
    
}
