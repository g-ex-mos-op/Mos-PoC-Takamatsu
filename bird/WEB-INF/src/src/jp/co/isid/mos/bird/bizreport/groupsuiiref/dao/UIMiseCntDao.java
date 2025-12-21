/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.groupsuiiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.UITabData;

/**
 * DAO【月別店舗数情報】
 * 
 * @author xkinu
 *
 */
public interface UIMiseCntDao {
    public static final Class BEAN = UITabData.class;

    public static final String select_ARGS = "appYm, siteKbn, userTypeCd, limitFlg, userId, parameterDto, isCsv";
    /**
     * 検索処理
     * 
     * @param appYm アプリ日付の年月
     * @param siteKbn サイト区分(うみ・やま・おひさま)
     * @param userTypeCd
     * @param limitFlg
     * @param userId
     * @param parameterDto
     * @param isCsv
     * @return
     */
    public List select(String appYm, String siteKbn, String userTypeCd, boolean limitFlg, String userId
    		, SuiiRefParameterDto parameterDto, boolean isCsv);

}
