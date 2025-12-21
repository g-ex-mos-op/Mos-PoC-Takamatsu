/*
 * ì¬“ú: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.dao;

import java.util.List;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.CodSeikyuBnrui;

/**
 * ¿‹‘—p•ª—Ş
 * 
 * @author xwatanabe
 */
public interface CodSeikyuBnruiDao {

    public static final Class BEAN = CodSeikyuBnrui.class;
    
    //public static final String getSeikyuBnrui_ARGS    = "";

    /**
     * ¿‹‘—p•ª—Ş‚Ìæ“¾
     * @return List
     */
    public List getSeikyuBnrui();
}
