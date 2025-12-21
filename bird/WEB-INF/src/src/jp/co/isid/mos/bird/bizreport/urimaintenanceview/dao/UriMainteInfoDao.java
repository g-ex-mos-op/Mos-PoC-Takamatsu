/*
 * 作成日: 2007/02/28
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.entity.UriMainteInfo;

/**
 * 売上修正確認情報取得
 * 
 * @author xwatanabe
 */
public interface UriMainteInfoDao {

    public static final Class BEAN = UriMainteInfo.class;

    public static final String getUriMainteInfo_ARGS = "companyCd, syuseiDate, sysDate";

    /**
     * 売上修正確認情報取得
     * @param String companyCd  会社コード
     * @param String syuseiDate 修正日
     * @param String sysDate    システム日付
     * @return List
     */
    public List getUriMainteInfo(String companyCd, String syuseiDate, String sysDate);
}
