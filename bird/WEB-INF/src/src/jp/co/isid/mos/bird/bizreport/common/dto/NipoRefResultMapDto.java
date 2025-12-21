package jp.co.isid.mos.bird.bizreport.common.dto;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.ResultMap;

/**
 * “ú•ñ‹¤’ÊDTOyŒ‹‰Êî•ñMap•Ûz
 * 
 * ƒEƒBƒ“ƒhƒEID‚ğƒL[‚ÉDTOy‰c‹Æ“ú•ñ Œ‹‰Êî•ñz‚ğ•Û‚µ‚Ü‚·B
 * 
 * @author xjung
 */
public class NipoRefResultMapDto {
 
    /**
     *  DTOyŒŸõğŒî•ñz•ÛMap
     *  windowId‚ğƒL[‚ÉŠi”[‚³‚ê‚Ä‚¢‚Ü‚·B
     */
    private Map mapSearchParameter = new HashMap();
	/**
	 * DTOyŒŸõğŒî•ñz
	 * @return ƒNƒ‰ƒX•Ï”resultParameterDto ‚ğ–ß‚µ‚Ü‚·B
	 */
	public NipoRefConditionParameterDto getParameterDto(int windowId) {
		return (NipoRefConditionParameterDto) mapSearchParameter.get(new Integer(windowId));
	}

	/**
	 * DTOyŒŸõğŒî•ñzİ’èˆ—
	 * @param resultParameterDto
	 */
	public void setParameterDto(	NipoRefConditionParameterDto resultParameterDto) {
		this.mapSearchParameter.put(new Integer(resultParameterDto.getWindowId()), resultParameterDto);
	}
    /**
     * DTOy‰c‹Æ“ú•ñ Œ‹‰Êî•ñz
     */
    private Map resultDto = new ResultMap();


    /**
     * DTOy‰c‹Æ“ú•ñ Œ‹‰Êî•ñz‚ğæ“¾‚µ‚Ü‚·B
     * @return DTOy‰c‹Æ“ú•ñ Œ‹‰Êî•ñz
     */
    public NipoRefResultDto getResultDto(int windowId) {
        Integer key = new Integer(windowId);
        return (NipoRefResultDto) resultDto.get(key);
    }

    /**
     * DTOy‰c‹Æ“ú•ñ Œ‹‰Êî•ñz‚ğİ’è‚µ‚Ü‚·B
     * @param resultDto DTOy‰c‹Æ“ú•ñ Œ‹‰Êî•ñz
     */
    public void setResultDto(int windowId, NipoRefResultDto resultDto) {
        // Œ»İƒEƒCƒ“ƒhƒEID
        Integer key = new Integer(windowId);
        // ƒŠƒXƒgİ’è
        this.resultDto.put(key, resultDto);
    }
}