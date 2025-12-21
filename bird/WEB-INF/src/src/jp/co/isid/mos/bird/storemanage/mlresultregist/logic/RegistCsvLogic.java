/*
 * ì¬“ú: 2006/07/21
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;

/**
 * CSV“o˜^ƒƒWƒbƒN
 * 
 * @author xyuchida
 */
public interface RegistCsvLogic {

    /**
     * CSV“o˜^
     * 
     * @param csvList CSVƒŒƒR[ƒhList
     */
    public void execute(List csvList, MlResultRegistDto mlResultRegistDto);
}
