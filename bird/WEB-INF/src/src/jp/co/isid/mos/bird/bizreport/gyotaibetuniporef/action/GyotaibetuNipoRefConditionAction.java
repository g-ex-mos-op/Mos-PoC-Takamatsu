package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.action;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;

public interface GyotaibetuNipoRefConditionAction {
    /** ƒAƒNƒVƒ‡ƒ“ID */
	public static final String ACTION_ID   = GyotaibetuNipoConstants.VIEW_ID+"A"; 
    
    public String initialize();
                   
    public String enforcement();
    public String callSvForm();
}
