package jp.co.isid.mos.bird.bizreport.common.suiiref.dto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.ResultMap;

/**
 * „ˆÚ•\‹¤’ÊDTOyŒ‹‰Êî•ñMap•ÛŽz
 * 
 * ƒEƒBƒ“ƒhƒEID‚ðƒL[‚ÉDTOy„ˆÚ•\ Œ‹‰Êî•ñz‚ð•ÛŽ‚µ‚Ü‚·B
 * ì¬“ú:2013/03/29
 * @author xkinu
 *
 */
public class SuiiRefResultMapDto {
 
    /**
     *  DTOyŒŸõðŒî•ñz•ÛŽMap
     *  windowId‚ðƒL[‚ÉŠi”[‚³‚ê‚Ä‚¢‚Ü‚·B
     */
    private Map mapSearchParameter = new HashMap();
	/**
	 * DTOyŒŸõðŒî•ñz
	 * @return ƒNƒ‰ƒX•Ï”resultParameterDto ‚ð–ß‚µ‚Ü‚·B
	 */
	public SuiiRefParameterDto getParameterDto(int windowId) {
		return (SuiiRefParameterDto) mapSearchParameter.get(new Integer(windowId));
	}

	/**
	 * DTOyŒŸõðŒî•ñzÝ’èˆ—
	 * @param resultParameterDto
	 */
	public void setParameterDto(SuiiRefParameterDto resultParameterDto) {
		this.mapSearchParameter.put(new Integer(resultParameterDto.getWindowId()), resultParameterDto);
	}
    /**
     * DTOy„ˆÚ•\ Œ‹‰Êî•ñz•ÛŽMap
     * Å‘å‚TŒ‚ÌƒEƒBƒ“ƒhƒEID‚ÌŒ‹‰Êî•ñ‚ð•ÛŽ‚µ‚Ü‚·B
     */
    private Map resultDto = new ResultMap();


    /**
     * DTOy„ˆÚ•\ Œ‹‰Êî•ñz‚ðŽæ“¾‚µ‚Ü‚·B
     * @return DTOy„ˆÚ•\ Œ‹‰Êî•ñz
     */
    public SuiiRefResultDto getResultDto(int windowId) {
        Integer key = new Integer(windowId);
        SuiiRefResultDto resultDto = (SuiiRefResultDto) this.resultDto.get(key);
        if(resultDto != null) {
	        resultDto.setListFocusTabResult(null);
	        if(getListResult(windowId, resultDto.getFocusTab()) != null) {
	        	resultDto.setListFocusTabResult(getListResult(windowId, resultDto.getFocusTab()));
	        }
        }
        else {
            //“¯‚¶ƒEƒBƒ“ƒhƒE‚h‚c‚Å‰ß‹Ž‚ÌŒŸõŒ‹‰Ê‚ª‘¶Ý‚·‚éê‡‚ÍAíœ‚µ‚Ü‚·B
        	deleteResult(windowId);
        }
        return resultDto;
    }

    /**
     * DTOy„ˆÚ•\ Œ‹‰Êî•ñz‚ðÝ’è‚µ‚Ü‚·B
     * @param resultDto DTOy„ˆÚ•\ Œ‹‰Êî•ñz
     */
    public void setResultDto(int windowId, SuiiRefResultDto resultDto) {
        // Œ»ÝƒEƒCƒ“ƒhƒEID
        Integer key = new Integer(windowId);
        // ƒŠƒXƒgÝ’è
        this.resultDto.put(key, resultDto);
        //List[[ŒŸõŒ‹‰Ê]]‚ðV‚½‚È‚T¢‘ã‚É•ÛŽ‚µ‚Ü‚·B
        if(resultDto != null) {
        	setListResult(windowId, resultDto.getFocusTab(), resultDto.getListFocusTabResult());
        }
    }
    /**
     * List[[ŒŸõŒ‹‰Ê]]•ÛŽMap
     * Å‘å‚TŒ‚ÌƒEƒBƒ“ƒhƒEID+ƒ^ƒuƒR[ƒh‚ÌŒ‹‰Êî•ñ‚ð•ÛŽ‚µ‚Ü‚·B
     */
    private Map listResult = new ResultMap();
    /**
     * List[[ŒŸõŒ‹‰Ê]]
     * @param windowId
     * @param focusTab
     * @return
     */
    public List getListResult(int windowId, String focusTab) {
    	String key = String.valueOf(windowId)+"_"+focusTab;
    	return (List) listResult.get(key);
    }
    /**
     * List[[ŒŸõŒ‹‰Ê]]
     * @param windowId
     * @param focusTab
     * @param listResult
     */
    public void setListResult(int windowId, String focusTab, List listResult) {
    	String key = String.valueOf(windowId)+"_"+focusTab;
    	this.listResult.put(key, listResult);
    }
    /**
     * ‘ÎÛƒEƒBƒ“ƒhƒE‚h‚cŒŸõŒ‹‰Êíœˆ—
     * @param windowId
     */
    public void deleteResult(int windowId) {
        //“¯‚¶ƒEƒBƒ“ƒhƒE‚h‚c‚Å‰ß‹Ž‚ÌŒŸõŒ‹‰Ê‚ª‘¶Ý‚·‚éê‡‚ÍAíœ‚µ‚Ü‚·B
        if(!this.listResult.isEmpty()) {
        	String targetKeyHeader = String.valueOf(windowId)+"_";
        	Iterator iteKey = this.listResult.keySet().iterator();
        	while(iteKey.hasNext()) {
        		String deleteKey = (String)(iteKey.next());
        		if(deleteKey.indexOf(targetKeyHeader) >=0) {
        			iteKey.remove();
        		}
        	}
        }
    }
}