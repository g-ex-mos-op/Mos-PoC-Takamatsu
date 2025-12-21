package jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UISetQuantityInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetSetQuantityInfoLogic;

/**
 * 設定数量情報取得ロジック
 *
 * @author xlee
 */
public class GetSetQuantityInfoLogicImpl implements GetSetQuantityInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS027L04";

    /**
     * 設定数量情報DAOを取得します。
     */
    private UISetQuantityInfoDao uiSetQuantityInfoDao;

    /**
     * 設定数量情報DAOを取得します。
     * @return 設定数量情報DAO
     */
    public UISetQuantityInfoDao getUISetQuantityInfoDao() {
        return uiSetQuantityInfoDao;
    }

    /**
     * 設定数量情報DAOを設定します。
     * @param uiSetQuantityInfoDao 設定数量情報DAO
     */
    public void setUISetQuantityInfoDao(UISetQuantityInfoDao uiSetQuantityInfoDao) {
        this.uiSetQuantityInfoDao = uiSetQuantityInfoDao;
    }

    /**
     * 設定数量確認リストを取得
     * @param taishoCond 対象条件
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @param sibuCd 支部コード
     * @param blockCd ブロックコード
     * @param sysDate　システム日付
     * @param downloadFlag　ダウンロードフラグ
     * @return　設定数量確認情報
     */

    public List execute(String taishoCond, String companyCd, String miseCd, String sibuCd, String blockCd, String sysDate, List onerCdList,boolean downloadFlag) {

    	List tmpResult =
    		getUISetQuantityInfoDao().getSetQuantityInfo(taishoCond, companyCd, miseCd, sibuCd, blockCd, sysDate, onerCdList,downloadFlag);

        return tmpResult;
    }

    /**
     * 設定数量確認リストを取得:オーナーユーザ
     * @param companyCd 会社コード
     * @param sysDate　システム日付
     * @param onerCd　オーナーコード
     * @param downloadFlag　ダウンロードフラグ
     * @return　設定数量確認情報
     */
    public List execute(String companyCd, String sysDate, List onerCdList,boolean downloadFlag) {

    	List tmpResult =
    		getUISetQuantityInfoDao().getOnerSetQuantityInfo(companyCd, sysDate, onerCdList,downloadFlag);

        return tmpResult;
    }
}
