/*
 * ì¬“ú: 2007/02/26
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic;

import java.util.List;

/**
 * ”„ãC³î•ñæ“¾
 * @author xwatanabe
 *
 */
public interface UriMainteInfoLogic {

    /**
     * ”„ãC³î•ñ‚ğæ“¾‚·‚éB
     * @param  companyCd
     * @param  syuseiDate
     * @param  sysDate
     * @return List
     */
    public List execute(String companyCd, String syuseiDate, String sysDate);

}
