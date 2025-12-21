package jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UISpotQuantityInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetSpotQuantityInfoLogic;

/**
 * 設定数量情報取得ロジック
 * 
 * @author xlee
 */
public class GetSpotQuantityInfoLogicImpl implements GetSpotQuantityInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS027L03";

    /**
     * スポット数量情報DAOを取得します。
     */
    private UISpotQuantityInfoDao uiSpotQuantityInfoDao;

    /**
     * スポット数量情報DAOを取得します。
     * @return スポット数量情報DAO
     */
    public UISpotQuantityInfoDao getUISpotQuantityInfoDao() {
        return uiSpotQuantityInfoDao;
    }

    /**
     * スポット数量情報DAOを設定します。
     * @param uiSpotQuantityInfoDao スポット数量情報DAO
     */
    public void setUISpotQuantityInfoDao(UISpotQuantityInfoDao uiSpotQuantityInfoDao) {
        this.uiSpotQuantityInfoDao = uiSpotQuantityInfoDao;
    }

    /**
     * スポット数量確認リストを取得
     * @param taishoCond 対象条件
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @param sibuCd 支部コード
     * @param blockCd ブロックコード
     * 
     * @return　設定数量確認情報
     */
    
    public List execute(String taishoCond, String companyCd, String cmpNo, String miseCd, String sibuCd, String blockCd, String sysDate, List onerCdList) {

    	List tmpResult = 
    		getUISpotQuantityInfoDao().getSpotQuantityInfo(taishoCond, companyCd, cmpNo, miseCd, sibuCd, blockCd, sysDate, onerCdList);

        return tmpResult;
    }
    
    /**
     *　スポット数量情報の取得:オーナーユーザ
     * @param cmpNo　キャンペーンNO
     * @param companyCd　企業コード
     * @param sysDate　システム日付
     * @param onerCd　オーナーコード
     * @return スポット数量情報
     */
    public List execute(String companyCd, String cmpNo, String sysDate, List onerCdList) {

    	List tmpResult = 
    		getUISpotQuantityInfoDao().getOnerSpotQuantityInfo(companyCd, cmpNo, sysDate, onerCdList);

        return tmpResult;
    }
}
