package jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.impl;

import java.util.List;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.common.UriMainteViewConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dao.UriMainteInfoDao;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.UriMainteInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 売上修正確認情報取得ロジック
 * @author xwatanabe
 */
public class UriMainteInfoLogicImpl implements UriMainteInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR009L01";

    /** 売上修正確認情報取得DAO */
    private UriMainteInfoDao uriMainteInfoDao;

    /**
     * 売上修正確認情報を取得する
     * @param  companyCd
     * @param  syuseiDate
     * @return List
     * @throws Exception 想定外エラー
     */
    public List execute(String companyCd, String syuseiDate, String sysDate) {

        //入力チェック
        validate(companyCd, syuseiDate);

        //DOA実行
        List list = uriMainteInfoDao.getUriMainteInfo(companyCd, syuseiDate, sysDate);

        //返却
        return list;
    }

    /**
     * 入力チェック
     */
    private void validate(String companyCd, String syuseiDate) {
        //会社コード
        if (companyCd == null || companyCd.length() == 0) {
            throw new NotNullException(UriMainteViewConstants.MSG_USER_COMPANYCD);
        }
        //修正日
        if (syuseiDate == null || syuseiDate.length() == 0) {
            throw new NotNullException(UriMainteViewConstants.MSG_USER_SYUSEIBI);
        }
    }

    /**
     * @return uriMainteInfoDao を戻します。
     */
    public UriMainteInfoDao getUriMainteInfoDao() {
        return uriMainteInfoDao;
    }

    /**
     * @param uriMainteInfoDao 設定する uriMainteInfoDao。
     */
    public void setUriMainteInfoDao(UriMainteInfoDao uriMainteInfoDao) {
        this.uriMainteInfoDao = uriMainteInfoDao;
    }
}
