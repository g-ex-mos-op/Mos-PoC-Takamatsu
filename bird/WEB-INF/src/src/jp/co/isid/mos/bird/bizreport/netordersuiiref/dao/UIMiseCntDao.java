/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.netordersuiiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.UITabData;

/**
 * DAOyŒ•Ê“X•Ü”î•ñz
 * 
 * ì¬“ú:
 * @author
 *
 */
public interface UIMiseCntDao {
    public static final Class BEAN = UITabData.class;

    public static final String select_ARGS = "appYM, siteKbn, userTypeCd, limitFlg, userId, paramsDto, isCsv";
    /**
     * @param appYM(yyyyMM)
     * @param siteKbn ƒTƒCƒg‹æ•ª(‚¤‚İE‚â‚ÜE‚¨‚Ğ‚³‚Ü)
     * @param userTypeCd
     * @param limitFlg
     * @param userId
     * @param parameterDto
     * @param isCsv
     * @return
     */
    public List select(String appYM, String siteKbn, String userTypeCd, boolean limitFlg, String userId
    		, SuiiRefParameterDto parameterDto, boolean isCsv);
}
